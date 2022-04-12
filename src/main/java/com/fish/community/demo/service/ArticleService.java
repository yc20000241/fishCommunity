package com.fish.community.demo.service;


import com.fish.community.demo.mapper.ArticlesMapper;
import com.fish.community.demo.mapper.UserMapper;
import com.fish.community.demo.model.Articles;
import com.fish.community.demo.model.User;
import com.fish.community.demo.model.UserExample;
import com.fish.community.demo.req.PublishArticleReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
public class ArticleService {

	@Autowired
	private ArticlesMapper articlesMapper;

	@Autowired
	private UserMapper userMapper;

	public void publishArticle(HttpServletRequest request, PublishArticleReq publishArticleReq) {
		//通过token找到用户id
		UserExample userExample = new UserExample();
		userExample.createCriteria().andTokenEqualTo(request.getHeader("token"));
		User user = userMapper.selectByExample(userExample).get(0);

		Articles article = new Articles();
		article.setTitle(publishArticleReq.getTitle());
		article.setContent(publishArticleReq.getContent());
		article.setGmtCreateTime(System.currentTimeMillis());
		article.setGmtModifiedTime(System.currentTimeMillis());
		article.setAuthor(user.getId());
		article.setTag(1);

		articlesMapper.insert(article);
	}
}
