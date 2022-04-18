package com.fish.community.demo.controller;

import com.fish.community.demo.req.CommentReq;
import com.fish.community.demo.resp.CommentsResp;
import com.fish.community.demo.resp.CommonResp;
import com.fish.community.demo.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/comment")
@ResponseBody
public class CommentController {

	@Autowired
	private CommentService commentService;

	@PostMapping("/sendComment")
	public CommonResp sendComment(@Validated @RequestBody CommentReq commentReq){
		if(commentReq.getParentId() == null)
			commentReq.setParentId((long)-1);
		if(commentReq.getRootId() == null)
			commentReq.setRootId((long)-1);

		commentService.sendComment(commentReq);
		CommonResp<Object> objectCommonResp = new CommonResp<>();
		objectCommonResp.setMessage("评论成功");
		return objectCommonResp;
	}
	/** parentId = -1   rootId = -1  文章的一级评论
	 *  parentId = -1   rootId != -1 文章的二级评论
	 *  parentId != -1   rootId != -1
	 */
	@RequestMapping(value = {"getCommentsList/{articleId}/{parentId}/{rootId}/{page}/{pageSize}","getCommentsList/{articleId}/{parentId}/{rootId}/{page}"}, method = RequestMethod.GET)
	public CommonResp getCommentsList(@PathVariable("articleId") Long articleId,
									  @PathVariable(value = "rootId") Long rootId,
									  @PathVariable(value = "parentId") Long parentId,
									  @PathVariable("page") Integer page,
									  @PathVariable(value = "pageSize", required = false) Integer pageSize){
		pageSize = (pageSize==null ? 5 : pageSize);

		CommentsResp comments = commentService.getCommentsList(articleId, rootId, page, pageSize, parentId);
		CommonResp<CommentsResp> commentsRespCommonResp = new CommonResp<>();
		commentsRespCommonResp.setContent(comments);

		return commentsRespCommonResp;
	}
}
