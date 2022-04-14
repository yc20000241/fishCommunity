package com.fish.community.demo.controller;

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

	@RequestMapping(value = {"/getArticleLists/{currentPage}/{listSize}/{userId}", "/getArticleLists/{currentPage}/{listSize}"},method = RequestMethod.GET)
	public CommonResp getArticleLists(@PathVariable(value = "currentPage") Integer currentPage,
									  @PathVariable(value = "listSize", required = false) Integer listSize,
									  @PathVariable(value = "userId", required = false) Long userId){
		if(listSize == null)
			listSize = 5;

		ArticleListResp articleLists = articleService.getArticleLists(currentPage, listSize, userId);
		CommonResp<ArticleListResp> listCommonResp = new CommonResp<>();
		listCommonResp.setContent(articleLists);
		return listCommonResp;
	}

	@RequestMapping(value = {"/search/{currentPage}/{listSize}/{userId}", "/search/{currentPage}/{listSize}" }, method = RequestMethod.GET)
	public CommonResp searchByKey(@RequestParam("key") String key,
								  @PathVariable(value = "currentPage") Integer currentPage,
								  @PathVariable(value = "listSize", required = false) Integer listSize,
								  @PathVariable(value = "userId", required = false) Long userId){
		if(listSize == null)
			listSize = 5;

		ArticleListResp articleLists = articleService.searchByKey(key, currentPage, listSize, userId);
		CommonResp<ArticleListResp> listCommonResp = new CommonResp<>();
		listCommonResp.setContent(articleLists);
		return listCommonResp;
	}

	@RequestMapping(value = {"/search/tag/{currentPage}/{listSize}/{userId}/{tag}", "/search/tag/{currentPage}/{listSize}/{tag}" }, method = RequestMethod.GET)
	public CommonResp searchByTag(@PathVariable("tag") Integer tag,
								  @PathVariable(value = "currentPage") Integer currentPage,
								  @PathVariable(value = "listSize", required = false) Integer listSize,
								  @PathVariable(value = "userId", required = false) Long userId){
		if(listSize == null)
			listSize = 5;

		ArticleListResp articleLists = articleService.searchByTag(tag, currentPage, listSize, userId);
		CommonResp<ArticleListResp> listCommonResp = new CommonResp<>();
		listCommonResp.setContent(articleLists);
		return listCommonResp;
	}
}
