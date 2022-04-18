package com.fish.community.demo.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class CommentReq {

	@ApiModelProperty(value = "为-1时为对文章回复，或一级评论的id")
	private Long parentId;

	@NotEmpty(message = "评论内容不能为空")
	private String content;

	@NotNull
	@ApiModelProperty(value = "为评论者的userId")
	private Long commenterId;

	@NotNull
	private Long articleId;

	@ApiModelProperty(value = "为被评论者的userId")
	private Long commentedId;

	@ApiModelProperty(value = "一级评论下多级评论的总id")
	private Long rootId;
}
