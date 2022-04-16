package com.fish.community.demo.controller;

import com.fish.community.demo.req.UserInfoReq;
import com.fish.community.demo.resp.CommonResp;
import com.fish.community.demo.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@ResponseBody
@RequestMapping("/userInfo")
public class UserInfoController {

	@Autowired
	private UserInfoService userInfoService;

	@PostMapping("/modifyUserInfo")
	public CommonResp modifyUserInfo(@Validated @RequestBody UserInfoReq userInfoReq){

		userInfoService.updateUserInfo(userInfoReq);

		return null;
	}
}
