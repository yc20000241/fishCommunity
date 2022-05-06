package com.fish.community.demo.service;

import com.fish.community.demo.mapper.*;
import com.fish.community.demo.model.Notification;
import com.fish.community.demo.model.NotificationExample;
import com.fish.community.demo.model.User;
import com.fish.community.demo.model.UserExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
public class NotificationService {

	@Autowired
	private NotificationExtMapper notificationExtMapper;

	@Autowired
	private NotificationMapper notificationMapper;

	@Autowired
	private UserMapper userMapper;

	public List<Notification> getNotifications(HttpServletRequest request) {
		//根据token查出affected_id
		UserExample userExample = new UserExample();
		userExample.createCriteria().andTokenEqualTo(request.getHeader("token"));
		User user = userMapper.selectByExample(userExample).get(0);

		List<Notification> notifications = notificationExtMapper.getNotificationByAffectId(user.getId());

		return notifications;
	}

	public void readNotification(Long notificationId) {
		Notification notification = new Notification();
		notification.setSign(1);

		NotificationExample notificationExample = new NotificationExample();
		notificationExample.createCriteria().andIdEqualTo(notificationId);
		notificationMapper.updateByExampleSelective(notification,notificationExample);
	}
}
