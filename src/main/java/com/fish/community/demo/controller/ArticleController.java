package com.fish.community.demo.controller;

import com.fish.community.demo.exception.BusinessException;
import com.fish.community.demo.exception.BusinessExceptionCode;
import com.fish.community.demo.model.Articles;
import com.fish.community.demo.req.PublishArticleReq;
import com.fish.community.demo.resp.ArticleDetailResp;
import com.fish.community.demo.resp.ArticleListResp;
import com.fish.community.demo.resp.CommonResp;
import com.fish.community.demo.resp.UserInfoResp;
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

	@GetMapping("/likeArticle/{articleId}/{userid}")
	public CommonResp likeArticle(@PathVariable("articleId") long articleId, @PathVariable("userid") Long userid){
		articleService.likeArticle(articleId, userid);
		CommonResp<Object> objectCommonResp = new CommonResp<>();
		objectCommonResp.setMessage("点赞成功");
		return objectCommonResp;
	}

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
	public CommonResp getArticleLists(@RequestParam(value = "sortKind", defaultValue = "1", required = false) Integer sortKind,
									  @PathVariable(value = "currentPage") Integer currentPage,
									  @PathVariable(value = "listSize", required = false) Integer listSize,
									  @PathVariable(value = "userId", required = false) Long userId){
		if(listSize == null)
			listSize = 5;

		ArticleListResp articleLists = articleService.getArticleLists(currentPage, listSize, userId, sortKind);
		CommonResp<ArticleListResp> listCommonResp = new CommonResp<>();
		listCommonResp.setContent(articleLists);
		return listCommonResp;
	}

	@RequestMapping(value = {"/search/{currentPage}/{listSize}/{userId}", "/search/{currentPage}/{listSize}" }, method = RequestMethod.GET)
	public CommonResp searchByKey(@RequestParam("key") String key,
								  @RequestParam(value = "sortKind", defaultValue = "1") Integer sortKind,
								  @PathVariable(value = "currentPage") Integer currentPage,
								  @PathVariable(value = "listSize", required = false) Integer listSize,
								  @PathVariable(value = "userId", required = false) Long userId){
		listSize = (listSize==null ? 5 : listSize);

		ArticleListResp articleLists = articleService.searchByKey(key, currentPage, listSize, userId, sortKind);
		CommonResp<ArticleListResp> listCommonResp = new CommonResp<>();
		listCommonResp.setContent(articleLists);
		return listCommonResp;
	}

	@GetMapping("/recommend/{tag}/{count}")
	public CommonResp recommend(@PathVariable("count") Integer count,
								@PathVariable("tag") Integer tag){
		if(tag == null || tag > 3 || tag < 0)
			throw new BusinessException(BusinessExceptionCode.ARTICLE_TAG_NOT_EXIST);

		List<Articles> articles = articleService.recommend(count, tag);
		CommonResp<List<Articles>> listCommonResp = new CommonResp<>();
		listCommonResp.setContent(articles);
		return listCommonResp;
	}

	@GetMapping("/activeAuthor/{count}")
	public CommonResp activeAuthor(@PathVariable("count") Integer count){
		List<UserInfoResp> users = articleService.activeAuthor(count);
		CommonResp<List<UserInfoResp>> listCommonResp = new CommonResp<>();
		listCommonResp.setContent(users);
		return listCommonResp;
	}

	@GetMapping("/disLikeArticle/{id}")
	public CommonResp disLikeArticle(@PathVariable("id") long id){
		articleService.disLikeArticle(id);
		CommonResp<Object> objectCommonResp = new CommonResp<>();
		objectCommonResp.setMessage("点踩成功");
		return objectCommonResp;
	}

}
