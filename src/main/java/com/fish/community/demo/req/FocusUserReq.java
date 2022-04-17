package com.fish.community.demo.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class FocusUserReq {

	@NotNull
	private Long focus_on_user;

	@NotNull
	private Long followed_user;

	@NotNull
	@ApiModelProperty(value = "true为关注,false为取消关注")
	private boolean focus;

}
