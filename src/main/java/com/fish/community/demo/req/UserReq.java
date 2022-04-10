package com.fish.community.demo.req;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.Null;
import javax.validation.constraints.Pattern;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserReq {

	String name;

	String password;

	@Email
	String email;

	String emailToken;

	String emailVerification;
}
