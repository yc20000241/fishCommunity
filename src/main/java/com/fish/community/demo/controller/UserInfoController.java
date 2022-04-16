package com.fish.community.demo.controller;

import com.fish.community.demo.resp.CommonResp;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@ResponseBody
@RequestMapping("/userInfo")
public class UserInfoController {

	@PostMapping("/modifyUserInfo")
	public CommonResp modifyUserInfo(){


		return null;
	}
}
