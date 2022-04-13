package com.fish.community.demo.controller;

import com.fish.community.demo.model.Articles;
import com.fish.community.demo.req.PublishArticleReq;
import com.fish.community.demo.resp.ArticleDetailResp;
import com.fish.community.demo.resp.ArticleListResp;
import com.fish.community.demo.resp.CommonResp;
import com.fish.community.demo.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

@Controller

@RequestMapping("/article")
@ResponseBody
public class ArticleController {

	@Autowired
	private ArticleService articleService;

	@GetMapping("/getArticleDetail/{id}")
	public CommonResp getArticleDetail(@PathVariable("id") long id) throws IOException {
		ArticleDetailResp articleDetailResp = articleService.getArticleDetail(id);
		CommonResp<ArticleDetailResp> articleDetailRespCommonResp = new CommonResp<>();
		articleDetailRespCommonResp.setContent(articleDetailResp);
		return articleDetailRespCommonResp;
	}

	@PostMapping("/publish")
	public CommonResp publishArticle(@RequestBody PublishArticleReq publishArticleReq, HttpServletRequest request) throws IOException {
		articleService.publishArticle(publishArticleReq, request);
		CommonResp<Object> objectCommonResp = new CommonResp<>();
		objectCommonResp.setMessage("文章发布成功");
		return objectCommonResp;
	}

	@GetMapping("/getArticleLists/{currentPage}/{listSize}/{userId}")
	public CommonResp getArticleLists(@PathVariable("currentPage") int currentPage,
									  @PathVariable("listSize") int listSize,
									  @PathVariable(value = "userId", required = false) Long userId){
		List<ArticleListResp> articleList = articleService.getArticleLists(currentPage, listSize, userId);
		CommonResp<List<ArticleListResp>> listCommonResp = new CommonResp<>();
		listCommonResp.setContent(articleList);
		return listCommonResp;
	}

}
