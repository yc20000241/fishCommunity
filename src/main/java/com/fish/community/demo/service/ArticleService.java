package com.fish.community.demo.service;


import com.fish.community.demo.exception.BusinessException;
import com.fish.community.demo.exception.BusinessExceptionCode;
import com.fish.community.demo.mapper.ArticlesMapper;
import com.fish.community.demo.mapper.UserMapper;
import com.fish.community.demo.model.Articles;
import com.fish.community.demo.model.ArticlesExample;
import com.fish.community.demo.model.User;
import com.fish.community.demo.model.UserExample;
import com.fish.community.demo.resp.ArticleDetailResp;
import com.fish.community.demo.util.CopyUtil;
import com.fish.community.demo.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

@Service
public class ArticleService {

	@Autowired
	private ArticlesMapper articlesMapper;

	@Autowired
	private UserMapper userMapper;

	public ArticleDetailResp getArticleDetail(long id) {
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
		modifyTimeArticle.setGmtModifiedTime(System.currentTimeMillis());
		articlesMapper.updateByExampleSelective(modifyTimeArticle, articlesExample);

		//返回文章详情
		ArticleDetailResp articleDetailResp = CopyUtil.copy(article, ArticleDetailResp.class);
		//将时间戳转换为日期格式
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		articleDetailResp.setCreateTime(dateFormat.format(Long.valueOf(article.getGmtCreateTime())));
		articleDetailResp.setModifiedTime(dateFormat.format(modifyTime));
		articleDetailResp.setAuthorName(user.getName());

		return articleDetailResp;
	}

	public void publishArticle(HttpServletRequest request,
							   String title,
							   int tag,
							   MultipartFile file
	) throws IOException {
		//通过token找到用户id
		UserExample userExample = new UserExample();
		userExample.createCriteria().andTokenEqualTo(request.getHeader("token"));
		User user = userMapper.selectByExample(userExample).get(0);

		Articles article = new Articles();
		article.setTitle(title);
		article.setContent(FileUtil.downFile(file,"article",user.getId()));
		article.setGmtCreateTime(System.currentTimeMillis());
		article.setGmtModifiedTime(System.currentTimeMillis());
		article.setAuthor(user.getId());
		article.setTag(tag);

		articlesMapper.insert(article);
	}
}
