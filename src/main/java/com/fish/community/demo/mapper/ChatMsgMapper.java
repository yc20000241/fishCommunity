package com.fish.community.demo.mapper;

import com.fish.community.demo.model.ChatMsg;
import com.fish.community.demo.model.ChatMsgExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface ChatMsgMapper {
    long countByExample(ChatMsgExample example);

    int deleteByExample(ChatMsgExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ChatMsg record);

    int insertSelective(ChatMsg record);

    List<ChatMsg> selectByExample(ChatMsgExample example);

    ChatMsg selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") ChatMsg record, @Param("example") ChatMsgExample example);

    int updateByExample(@Param("record") ChatMsg record, @Param("example") ChatMsgExample example);

    int updateByPrimaryKeySelective(ChatMsg record);

    int updateByPrimaryKey(ChatMsg record);
}