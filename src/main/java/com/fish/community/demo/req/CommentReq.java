package com.fish.community.demo.req;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class CommentReq {

	private Long parentId;

	@NotEmpty(message = "评论内容不能为空")
	private String content;

	@NotNull
	private Long commenterId;

	@NotNull
	private Long articleId;
}
