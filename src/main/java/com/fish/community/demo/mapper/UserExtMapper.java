package com.fish.community.demo.mapper;

import com.fish.community.demo.model.User;
import com.fish.community.demo.model.UserExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Mapper
public interface UserExtMapper {

    List<User> selectIdIn(ArrayList<Long> longs);
}