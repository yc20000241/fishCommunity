package com.fish.community.demo.controller;

import com.fish.community.demo.req.PublishArticleReq;
import com.fish.community.demo.resp.ArticleDetailResp;
import com.fish.community.demo.resp.CommonResp;
import com.fish.community.demo.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Controller

@RequestMapping("/article")
@ResponseBody
public class ArticleController {

	@Autowired
	private ArticleService articleService;

	@GetMapping("/getArticleDetail/{id}")
	public CommonResp getArticleDetail(@PathVariable("id") long id){
		ArticleDetailResp articleDetailResp = articleService.getArticleDetail(id);
		CommonResp<ArticleDetailResp> articleDetailRespCommonResp = new CommonResp<>();
		articleDetailRespCommonResp.setContent(articleDetailResp);
		return articleDetailRespCommonResp;
	}

	@PostMapping("/publish")
	public CommonResp publishArticle(PublishArticleReq publishArticleReq, HttpServletRequest request) throws IOException {
		articleService.publishArticle(publishArticleReq, request);
		CommonResp<Object> objectCommonResp = new CommonResp<>();
		objectCommonResp.setMessage("文章发布成功");
		return objectCommonResp;
	}

}
