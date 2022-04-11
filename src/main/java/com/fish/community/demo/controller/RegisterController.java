package com.fish.community.demo.controller;

import com.fish.community.demo.resp.CommonResp;
import com.fish.community.demo.req.RegisiterReq;
import com.fish.community.demo.resp.RegisterGetVerificationResp;
import com.fish.community.demo.service.RegisterService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.regex.Pattern;

@Controller
@ResponseBody
@RequestMapping("/register")
public class RegisterController {

	@Autowired
	private RegisterService registerService;

	@PostMapping("")
	@ApiOperation("使用邮箱快速注册")
	public CommonResp regisiter(@Validated @RequestBody RegisiterReq regisiterResp){
		CommonResp<RegisiterReq> objectCommonResp = new CommonResp<RegisiterReq>();
		registerService.registerUser(regisiterResp);
		objectCommonResp.setMessage("注册成功");

		return objectCommonResp;
	}

	@GetMapping("/getQQVerification/{email}")
	@ApiOperation("用qq邮箱获取验证码")
	public CommonResp getQQVerification(@PathVariable("email") String email){
		CommonResp<RegisterGetVerificationResp> commonResp = new CommonResp<>();
		boolean isMatch = Pattern.matches("^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$", email);
		if(!isMatch) {
			commonResp.setMessage("邮箱格式不正确");
			return commonResp;
		}

		RegisterGetVerificationResp registerGetVerificationResp = registerService.getQQVerification(email);
		commonResp.setMessage("验证码发送成功");
		commonResp.setContent(registerGetVerificationResp);


		return commonResp;
	}
}
