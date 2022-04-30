package com.fish.community.demo.mapper;

import com.fish.community.demo.model.ChatMsg;
import com.fish.community.demo.model.ChatMsgExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface ChatMsgExtMapper {

    List<ChatMsg> selectCommonChats(Long uuid);

	Long insert(ChatMsg chatMsg);
}