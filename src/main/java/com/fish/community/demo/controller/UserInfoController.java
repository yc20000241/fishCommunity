package com.fish.community.demo.controller;

import com.fish.community.demo.model.Userinfo;
import com.fish.community.demo.req.FocusUserReq;
import com.fish.community.demo.req.UserInfoReq;
import com.fish.community.demo.resp.CommonResp;
import com.fish.community.demo.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Controller
@ResponseBody
@RequestMapping("/userInfo")
public class UserInfoController {

	@Autowired
	private UserInfoService userInfoService;

	@PostMapping("/modifyUserInfo")
	public CommonResp modifyUserInfo(@Validated @RequestBody UserInfoReq userInfoReq){

		userInfoService.updateUserInfo(userInfoReq);
		CommonResp<Object> objectCommonResp = new CommonResp<>();
		objectCommonResp.setMessage("更新成功");
		return objectCommonResp;
	}

	@GetMapping("/getUserInfo/{userId}")
	public CommonResp getUserInfo(@PathVariable("userId") long userId){

		Userinfo userinfo = userInfoService.getUserInfo(userId);
		CommonResp<Userinfo> userinfoCommonResp = new CommonResp<>();
		userinfoCommonResp.setContent(userinfo);
		return userinfoCommonResp;
	}

	@PostMapping("/focusUser")
	public CommonResp focusUser(@Validated @RequestBody FocusUserReq focusUserReq){

		boolean flag = userInfoService.focusUser(focusUserReq);
		CommonResp<Object> objectCommonResp = new CommonResp<>();
		objectCommonResp.setMessage(flag ? "关注成功" : "取消关注成功");
		return objectCommonResp;
	}

}
