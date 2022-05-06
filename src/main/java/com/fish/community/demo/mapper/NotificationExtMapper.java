package com.fish.community.demo.mapper;

import com.fish.community.demo.model.Notification;
import com.fish.community.demo.model.NotificationExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface NotificationExtMapper {

    List<Notification> getNotificationByAffectId(Long userId);
}