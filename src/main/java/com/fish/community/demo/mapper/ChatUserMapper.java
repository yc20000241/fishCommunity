package com.fish.community.demo.mapper;

import com.fish.community.demo.model.ChatUser;
import com.fish.community.demo.model.ChatUserExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface ChatUserMapper {
    long countByExample(ChatUserExample example);

    int deleteByExample(ChatUserExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ChatUser record);

    int insertSelective(ChatUser record);

    List<ChatUser> selectByExample(ChatUserExample example);

    ChatUser selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") ChatUser record, @Param("example") ChatUserExample example);

    int updateByExample(@Param("record") ChatUser record, @Param("example") ChatUserExample example);

    int updateByPrimaryKeySelective(ChatUser record);

    int updateByPrimaryKey(ChatUser record);
}