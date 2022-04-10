package com.fish.community.demo.req;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Null;
import javax.validation.constraints.Pattern;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserReq {

	String name;

	String password;

	@Pattern(regexp = "\\d{5,11}@qq\\.com", message = "qq邮箱错误")
	String email;

	String emailToken;

	String emailVerification;
}
