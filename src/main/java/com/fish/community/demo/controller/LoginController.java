package com.fish.community.demo.controller;

import com.fish.community.demo.model.RegisterVerification;
import com.fish.community.demo.req.UserReq;
import com.fish.community.demo.resp.CommonResp;
import com.fish.community.demo.resp.LoginResp;
import com.fish.community.demo.service.LoginService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.regex.Pattern;


@Controller
@RequestMapping("/login")
@ResponseBody
public class LoginController {

	@Autowired
	private LoginService loginService;

	@PostMapping("/byPassword")
	@ApiOperation("使用密码登录")
	public CommonResp loginWithPassword(@Validated @RequestBody UserReq userReq){
		LoginResp loginResp = loginService.loginWithPassword(userReq);
		CommonResp<LoginResp> loginRespCommonResp = new CommonResp<>();

		if(loginResp.isLoginSuccess()){
			loginRespCommonResp.setContent(loginResp);
			loginRespCommonResp.setSuccess(true);
		}
		else
			loginRespCommonResp.setSuccess(false);


		return loginRespCommonResp;
	}


	@PostMapping("/byEmail")
	public CommonResp loginWithEmail(@Validated @RequestBody UserReq userReq){
		LoginResp loginResp = loginService.loginWithEmail(userReq);
		CommonResp<LoginResp> loginRespCommonResp = new CommonResp<>();

		if(loginResp.isLoginSuccess()){
			loginRespCommonResp.setContent(loginResp);
			loginRespCommonResp.setSuccess(true);
		}
		else
			loginRespCommonResp.setSuccess(false);


		return loginRespCommonResp;
	}

	@GetMapping("/getVerification/{email}")
	@ApiOperation("发送登录验证码")
	public CommonResp getVerification(@PathVariable("email") String email){

		CommonResp commonResp = new CommonResp();

		boolean isMatch = Pattern.matches("\\d{5,11}@qq\\.com", email);
		if(!isMatch) {
			commonResp.setMessage("邮箱格式不正确");
			return commonResp;
		}

		RegisterVerification verification = loginService.getQQVerification(email);

		if(verification == null){
			commonResp.setMessage("验证码错误");
		}else {
			commonResp.setMessage("验证码发送成功");
			commonResp.setContent(verification);
		}
		return commonResp;
	}
}
