package com.fish.community.demo.service;


import com.fish.community.demo.exception.BusinessException;
import com.fish.community.demo.exception.BusinessExceptionCode;
import com.fish.community.demo.mapper.ArticlesExtMapper;
import com.fish.community.demo.mapper.ArticlesMapper;
import com.fish.community.demo.mapper.UserMapper;
import com.fish.community.demo.model.Articles;
import com.fish.community.demo.model.ArticlesExample;
import com.fish.community.demo.model.User;
import com.fish.community.demo.model.UserExample;
import com.fish.community.demo.req.PublishArticleReq;
import com.fish.community.demo.resp.ArticleDetailResp;
import com.fish.community.demo.resp.ArticleListResp;
import com.fish.community.demo.util.CopyUtil;
import com.fish.community.demo.util.FileUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

@Service
public class ArticleService {

	@Autowired
	private ArticlesMapper articlesMapper;

	@Autowired
	private ArticlesExtMapper articlesExtMapper;

	@Autowired
	private UserMapper userMapper;

	public ArticleDetailResp getArticleDetail(long id) throws IOException {
		ArticlesExample articlesExample = new ArticlesExample();
		articlesExample.createCriteria().andIdEqualTo(id);
		List<Articles> articles = articlesMapper.selectByExample(articlesExample);

		//文章列表为空则说明文章id不存在
		if(articles.isEmpty())
			throw new BusinessException(BusinessExceptionCode.ARTICLE_NOT_EXIst);
		Articles article = articles.get(0);
		//获取用户姓名
		UserExample userExample = new UserExample();
		userExample.createCriteria().andIdEqualTo(article.getAuthor());
		User user = userMapper.selectByExample(userExample).get(0);

		//更新修改时间
		Long modifyTime = System.currentTimeMillis();
		Articles modifyTimeArticle = new Articles();
		modifyTimeArticle.setGmtModifiedTime(System.currentTimeMillis()+"");
		articlesMapper.updateByExampleSelective(modifyTimeArticle, articlesExample);

		//返回文章详情
		ArticleDetailResp articleDetailResp = CopyUtil.copy(article, ArticleDetailResp.class);
		//将时间戳转换为日期格式
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		articleDetailResp.setCreateTime(dateFormat.format(Long.valueOf(article.getGmtCreateTime())));
		articleDetailResp.setModifiedTime(dateFormat.format(modifyTime));
		articleDetailResp.setAuthorName(user.getName());
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

		articlesMapper.insert(article);
	}

	public ArticleListResp getArticleLists(int currentPage, int listSize, Long userId) {
		ArticlesExample articlesExample = new ArticlesExample();
		PageHelper.startPage(currentPage, listSize);
		List<Articles> articlesList = null;
		if(userId == null){
			articlesList = articlesExtMapper.selectAll();
		}else{
			articlesExample.createCriteria().andAuthorEqualTo(userId);
			articlesList = articlesMapper.selectByExample(articlesExample);
		}
		//将时间戳转为日期格式
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		for (int i = 0 ; i < listSize; i++){
			Articles articles = articlesList.get(i);
			articles.setGmtCreateTime(dateFormat.format(Long.valueOf(articles.getGmtCreateTime())));
		}

		ArticleListResp articleListResp = new ArticleListResp();
		articleListResp.setArticles(articlesList);
		PageInfo<Articles> objectPageInfo = new PageInfo<>(articlesList);
		articleListResp.setTotalArticlesCount(objectPageInfo.getTotal());

		return articleListResp;
	}
}
