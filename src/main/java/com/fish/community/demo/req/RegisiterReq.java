package com.fish.community.demo.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisiterReq {

	@Email
	String email;

	@Pattern(regexp = "\\d{18}", message = "邮箱验证码token错误")
	@ApiModelProperty(value = "邮箱验证码标识")
	String emailToken;

	@Pattern(regexp = "\\d{6}", message = "邮箱验证码错误")
	@ApiModelProperty(value = "邮箱验证码")
	String emailVerification;

}
