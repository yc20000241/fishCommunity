package com.fish.community.demo.controller;

import com.fish.community.demo.exception.BusinessException;
import com.fish.community.demo.exception.BusinessExceptionCode;
import com.fish.community.demo.req.PublishArticleReq;
import com.fish.community.demo.resp.CommonResp;
import com.fish.community.demo.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@ResponseBody
@RequestMapping("/article")
public class ArticleController {

	@Autowired
	private ArticleService articleService;

	@PostMapping("/publish")
	public CommonResp publishArticle(@Validated @RequestBody PublishArticleReq publishArticleReq, HttpServletRequest request){
		articleService.publishArticle(request, publishArticleReq);
		CommonResp<Object> objectCommonResp = new CommonResp<>();
		objectCommonResp.setMessage("文章发布成功");
		return objectCommonResp;
	}

}
