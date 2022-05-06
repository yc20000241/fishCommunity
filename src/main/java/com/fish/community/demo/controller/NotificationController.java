package com.fish.community.demo.controller;

import com.fish.community.demo.model.Notification;
import com.fish.community.demo.resp.CommonResp;
import com.fish.community.demo.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@ResponseBody
@RequestMapping("/notification")
public class NotificationController {

	@Autowired
	private NotificationService notificationService;

	@GetMapping("/getNotifications")
	public CommonResp getNotifications(HttpServletRequest request){
		List<Notification> notificationRespList = notificationService.getNotifications(request);
		CommonResp<List<Notification>> listCommonResp = new CommonResp<>();
		listCommonResp.setContent(notificationRespList);
		return listCommonResp;
	}

	@GetMapping("readNotification/{notificationId}")
	public CommonResp readNotification(@PathVariable("notificationId") Long notificationId){
		notificationService.readNotification(notificationId);
		CommonResp<Object> objectCommonResp = new CommonResp<>();
		return objectCommonResp;
	}
}
