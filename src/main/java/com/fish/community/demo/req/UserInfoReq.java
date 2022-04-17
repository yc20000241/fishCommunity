package com.fish.community.demo.req;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class UserInfoReq {

	@NotNull
	private Long userId;

	@NotEmpty
	private String nick;

	private String newPassword;

	@NotEmpty
	private String password;

	private String imgUrl;

	@NotNull
	private Integer sex;

	@NotEmpty
	private String sign;
}
