package com.fish.community.demo.controller;

import com.fish.community.demo.exception.BusinessException;
import com.fish.community.demo.exception.BusinessExceptionCode;
import com.fish.community.demo.model.Userinfo;
import com.fish.community.demo.req.FocusUserReq;
import com.fish.community.demo.req.UserInfoReq;
import com.fish.community.demo.resp.CommonResp;
import com.fish.community.demo.resp.UserInfoResp;
import com.fish.community.demo.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

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
	public CommonResp getUserInfo(@PathVariable("userId") long userId, HttpServletRequest request){
		UserInfoResp userinfo = userInfoService.getUserInfo(userId, request);
		CommonResp<UserInfoResp> userinfoCommonResp = new CommonResp<>();
		userinfoCommonResp.setContent(userinfo);
		return userinfoCommonResp;
	}

	@PostMapping("/focusUser")
	public CommonResp focusUser(@Validated @RequestBody FocusUserReq focusUserReq){
		if(focusUserReq.getFocusOnUser() == focusUserReq.getFollowedUser())
			throw new BusinessException(BusinessExceptionCode.FOUCUS_NOT_MYSELF);

		boolean flag = userInfoService.focusUser(focusUserReq);
		CommonResp<Object> objectCommonResp = new CommonResp<>();
		objectCommonResp.setMessage(flag ? "关注成功" : "取消关注成功");
		return objectCommonResp;
	}

	@GetMapping("/focusList/{id}")
	public CommonResp focusList(@PathVariable("id") Long id){
		List<Userinfo> userinfoList = userInfoService.focusList(id);
		CommonResp<List<Userinfo>> listCommonResp = new CommonResp<>();
		listCommonResp.setContent(userinfoList);
		return listCommonResp;
	}

	@GetMapping("/followedList/{id}")
	public CommonResp followedList(@PathVariable("id") Long id){
		List<Userinfo> userinfoList = userInfoService.followedList(id);
		CommonResp<List<Userinfo>> listCommonResp = new CommonResp<>();
		listCommonResp.setContent(userinfoList);
		return listCommonResp;
	}

}
