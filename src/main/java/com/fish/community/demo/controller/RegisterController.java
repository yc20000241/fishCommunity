package com.fish.community.demo.controller;

import com.fish.community.demo.model.RegisterVerification;
import com.fish.community.demo.resp.CommonResp;
import com.fish.community.demo.req.RegisiterReq;
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

	@Autowired
	private CommonResp commonResp;

	@PostMapping("")
	@ApiOperation("使用游戏快速注册")
	public CommonResp regisiter(@Validated @RequestBody RegisiterReq regisiterResp){
		CommonResp<RegisiterReq> objectCommonResp = new CommonResp<RegisiterReq>();
		int flag = registerService.registerUser(regisiterResp);
		if(flag != 0){
			objectCommonResp.setSuccess(false);
			if(flag == 1)
				objectCommonResp.setMessage("验证码错误");
			else if(flag == 2)
				objectCommonResp.setMessage("邮箱已被注册");
			else
				objectCommonResp.setMessage("验证码过期或不存在");
		}else {
			objectCommonResp.setMessage("注册成功");
		}

		return objectCommonResp;
	}

	@GetMapping("/getQQVerification")
	@ApiOperation("用qq邮箱获取验证码")
	public CommonResp getQQVerification(@RequestParam("email") String email){
		boolean isMatch = Pattern.matches("\\d{5,11}@qq\\.com", email);
		if(!isMatch) {
			commonResp.setMessage("邮箱格式不正确");
			return commonResp;
		}

		RegisterVerification verification = registerService.getQQVerification(email);

		if(verification == null){
			commonResp.setMessage("邮箱已被注册");
		}else {
			commonResp.setMessage("验证码发送成功");
			commonResp.setContent(verification);
		}
		return commonResp;
	}
}
