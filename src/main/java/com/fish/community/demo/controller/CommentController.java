package com.fish.community.demo.controller;

import com.fish.community.demo.model.Comment;
import com.fish.community.demo.req.CommentReq;
import com.fish.community.demo.resp.CommentsResp;
import com.fish.community.demo.resp.CommonResp;
import com.fish.community.demo.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

		commentService.sendComment(commentReq);
		CommonResp<Object> objectCommonResp = new CommonResp<>();
		objectCommonResp.setMessage("评论成功");
		return objectCommonResp;
	}

	@GetMapping("getCommentsList/{articleId}/{commentId}/{page}/{pageSize}")
	public CommonResp getCommentsList(@PathVariable("articleId") Long articleId,
									  @PathVariable(value = "commentId", required = false) Long commentId,
									  @PathVariable("page") Integer page,
									  @PathVariable("pageSize") Integer pageSize){
		CommentsResp comments = commentService.getCommentsList(articleId, commentId, page, pageSize);
		CommonResp<CommentsResp> commentsRespCommonResp = new CommonResp<>();
		commentsRespCommonResp.setContent(comments);

		return commentsRespCommonResp;
	}
}
