package com.fish.community.demo.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class FocusUserReq {

	@NotNull
	private Long focusOnUser;

	@NotNull
	private Long followedUser;


}
