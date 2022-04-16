package com.fish.community.demo.req;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class UserInfoReq {

	@NotNull
	private Long userId;

	@NotEmpty
	private String name;

	@NotEmpty
	private String password;

	@NotEmpty
	private String img_url;

	@NotNull
	private Integer sex;

	@NotEmpty
	private String sign;
}
