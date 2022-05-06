package com.fish.community.demo.service;


import com.fish.community.demo.exception.BusinessException;
import com.fish.community.demo.exception.BusinessExceptionCode;
import com.fish.community.demo.mapper.*;
import com.fish.community.demo.model.*;
import com.fish.community.demo.req.PublishArticleReq;
import com.fish.community.demo.resp.ActiveAuthorResp;
import com.fish.community.demo.resp.ArticleDetailResp;
import com.fish.community.demo.resp.ArticleListResp;
import com.fish.community.demo.resp.UserInfoResp;
import com.fish.community.demo.util.CopyUtil;
import com.fish.community.demo.util.FileUtil;
import com.fish.community.demo.util.RedisUtil;
import com.fish.community.demo.util.RequestContext;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Service
public class ArticleService {

	@Autowired
	private ArticlesMapper articlesMapper;

	@Autowired
	private ArticlesExtMapper articlesExtMapper;

	@Autowired
	private UserMapper userMapper;

	@Autowired
	private UserinfoMapper userinfoMapper;

	@Autowired
	private RedisUtil redisUtil;

	@Autowired
	private UserinfoExtMapper userinfoExtMapper;

	@Autowired
	private NotificationMapper notificationMapper;

	private Articles hasArticle(long id){
		ArticlesExample articlesExample = new ArticlesExample();
		articlesExample.createCriteria().andIdEqualTo(id);
		List<Articles> articles = articlesMapper.selectByExample(articlesExample);
		if (articles.isEmpty())
			return null;
		else
			return articles.get(0);
	}

	@Transactional
	public ArticleDetailResp getArticleDetail(long id) throws IOException {
		ArticlesExample articlesExample = new ArticlesExample();
		articlesExample.createCriteria().andIdEqualTo(id);
		List<Articles> articles = articlesMapper.selectByExample(articlesExample);

		//文章列表为空则说明文章id不存在
		if(articles.isEmpty())
			throw new BusinessException(BusinessExceptionCode.ARTICLE_NOT_EXIst);
		Articles article = articles.get(0);
		//获取用户姓名
		UserinfoExample userinfoExample = new UserinfoExample();
		userinfoExample.createCriteria().andUserIdEqualTo(article.getAuthor());
		Userinfo userinfo = userinfoMapper.selectByExample(userinfoExample).get(0);

		//更新修改时间
		Long modifyTime = System.currentTimeMillis();
		Articles modifyTimeArticle = new Articles();
		modifyTimeArticle.setGmtModifiedTime(System.currentTimeMillis()+"");
		articlesMapper.updateByExampleSelective(modifyTimeArticle, articlesExample);

		//文章浏览量加1
		Articles articles1 = new Articles();
		articles1.setViewCount(article.getViewCount()+1);
		articlesMapper.updateByExampleSelective(articles1,articlesExample);

		//返回文章详情
		ArticleDetailResp articleDetailResp = CopyUtil.copy(article, ArticleDetailResp.class);
		//将时间戳转换为日期格式
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		articleDetailResp.setCreateTime(dateFormat.format(Long.valueOf(article.getGmtCreateTime())));
		articleDetailResp.setModifiedTime(dateFormat.format(modifyTime));
		articleDetailResp.setAuthorName(userinfo.getNick());
		//从文件读取内容
		articleDetailResp.setContent(FileUtil.ReadFile("./file"+articleDetailResp.getContent()));

		return articleDetailResp;
	}

	public void publishArticle(PublishArticleReq publishArticleReq,HttpServletRequest request) throws IOException {
		//判断文件路径是否正确
		if(!FileUtil.fileIsExist(publishArticleReq.getArticleImg()))
			throw new BusinessException(BusinessExceptionCode.FILE_PATH_NOT_EXIST);

		//通过token找到用户id
		UserExample userExample = new UserExample();
		userExample.createCriteria().andTokenEqualTo(request.getHeader("token"));
		User user = userMapper.selectByExample(userExample).get(0);

		//获取最后一个文章id的下一个自增量
		Articles last = articlesExtMapper.getLast();

		String articleContentFileName = user.getId()+"_"+(last.getId()+1)+".md";
		//将字符串写入文件
		FileUtil.writeToFile(publishArticleReq.getContent(), "article", articleContentFileName);

		Articles article = new Articles();
		article.setId(last.getId()+1);
		article.setTitle(publishArticleReq.getTitle());
		article.setGmtCreateTime(System.currentTimeMillis()+"");
		article.setGmtModifiedTime(System.currentTimeMillis()+"");
		article.setAuthor(user.getId());
		article.setTag(publishArticleReq.getTag());
		article.setContent("/article/"+articleContentFileName);
		article.setArticleImg(publishArticleReq.getArticleImg());
		article.setViewCount(0);
		article.setLikeCount(0);
		article.setDislikeCount(0);
		articlesMapper.insert(article);
	}

	public ArticleListResp getArticleLists(int currentPage, int listSize, Long userId, Integer sortKind) {
		PageHelper.startPage(currentPage, listSize);
		List<Articles> articlesList = null;
		String[][] orderByList = {{"gmt_create_time", "desc"},{"gmt_create_time", "asc"},
				{"view_count", "desc"}, {"view_count","asc"}};

		String isUserId = null;
		if(userId == null){
			isUserId = "";
		}else{
			isUserId = "where author="+userId;
		}
		articlesList = articlesExtMapper.selectAll(orderByList[sortKind-1][0],orderByList[sortKind-1][1], isUserId);
		//将时间戳转为日期格式
		articlesList = gmtTransToDateAndReadFile(articlesList,listSize);

		ArticleListResp articleListResp = new ArticleListResp();
		articleListResp.setArticles(articlesList);
		PageInfo<Articles> objectPageInfo = new PageInfo<>(articlesList);
		articleListResp.setTotalArticlesCount(objectPageInfo.getTotal());

		return articleListResp;
	}

	private List<Articles> gmtTransToDateAndReadFile(List<Articles> articlesList, int listSize) {
		if(articlesList.size() < listSize)
			listSize = articlesList.size();

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		for (int i = 0 ; i < listSize; i++){
			Articles articles = articlesList.get(i);
			articles.setGmtCreateTime(dateFormat.format(Long.valueOf(articles.getGmtCreateTime())));
			//从文件读取内容
			articles.setContent(FileUtil.ReadPartContentFromFile("./file"+articles.getContent()));
		}
		return articlesList;
	}

	public ArticleListResp searchByKey(String key, Integer currentPage, Integer listSize, Long userId, Integer sortKind) {
		List<Articles> articlesList = null;
		Integer start = (currentPage-1)*listSize;
		String isUserId = null;
		String[][] orderByList = {{"gmt_create_time", "desc"},{"gmt_create_time", "asc"},
				{"view_count", "desc"}, {"view_count","asc"}};

		if(userId == null)
			isUserId = "";
		else
			isUserId = "and author="+userId;


		articlesList = articlesExtMapper.searchArticleByKey(key, start, listSize, isUserId, orderByList[sortKind-1][0],orderByList[sortKind-1][1]);
		//获取总条数
		Integer total = articlesExtMapper.getCountSearchArticleByKey(key,isUserId);
		//将时间戳改为日期模式
		articlesList = gmtTransToDateAndReadFile(articlesList, listSize);

		total = (total==null ? 0 : total);
		ArticleListResp articleListResp = new ArticleListResp();
		articleListResp.setArticles(articlesList);
		articleListResp.setTotalArticlesCount((long)total);
		return articleListResp;
	}

	@Transactional
	public void likeArticle(long articleId, long userid) {
		Articles articles = hasArticle(articleId);
		if(articles == null)
			throw new BusinessException(BusinessExceptionCode.ARTICLE_NOT_EXIst);
		User user = hasUserId(userid);
		if(user == null)
			throw new BusinessException(BusinessExceptionCode.USER_NOT_REGISTER);
		//查看是否之前点过赞
		NotificationExample notificationExample = new NotificationExample();
		notificationExample.createCriteria().andActionIdEqualTo(userid).andArticleIdEqualTo(articleId).andKindEqualTo(1);
		List<Notification> notifications = notificationMapper.selectByExample(notificationExample);
		if(notifications.isEmpty()){
			articlesExtMapper.increaseLikeCount(articleId);
			Notification notification = new Notification();
			notification.setActionId(userid);
			notification.setAffectId(articles.getAuthor());
			notification.setArticleId(articles.getId());
			notification.setContent(articles.getTitle());
			notification.setKind(1);
			notification.setGmtCreate(System.currentTimeMillis()+"");
			notificationMapper.insertSelective(notification);
		}
		else
			throw new BusinessException(BusinessExceptionCode.TODAY_HAS_LIKEN_THE_ARTICLE);
	}

	private User hasUserId(long userid) {
		UserExample userExample = new UserExample();
		userExample.createCriteria().andIdEqualTo(userid);
		List<User> users = userMapper.selectByExample(userExample);
		if(users.isEmpty())
			return null;
		else
			return users.get(0);
	}

	public List<Articles> recommend(Integer count, Integer tag) {
		PageHelper.startPage(0, count);
		List<Articles> articles = articlesExtMapper.recommend(tag);
		return articles;
	}

	public List<UserInfoResp> activeAuthor(Integer count) {
		//获取前几条文章数最多的作者id
		PageHelper.startPage(0, count);
		ActiveAuthorResp[] authors = articlesExtMapper.activeAuthor();
		ArrayList<Long> longs = new ArrayList<>();
		for (ActiveAuthorResp author : authors) {
			longs.add(author.getAuthor());
		}
		//根据userId数组返回userinfos
		List<Userinfo> users = userinfoExtMapper.selectIdIn(longs);
		List<UserInfoResp> userInfoResps = CopyUtil.copyList(users, UserInfoResp.class);
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		for (UserInfoResp userInfoResp : userInfoResps) {
			userInfoResp.setGmtCreate(dateFormat.format(Long.valueOf(userInfoResp.getGmtCreate())));
		}

		return userInfoResps;
	}

	public void disLikeArticle(long id) {
		String ip = RequestContext.getRemoteAddr();
		if(redisUtil.validateRepeat("ARTICLE_LIKE"+id+"_"+ip, 3600*24))
			articlesExtMapper.decreaseLikeCount(id);
		else
			throw new BusinessException(BusinessExceptionCode.TODAY_HAS_LIKEN_THE_ARTICLE);
	}


}
