package com.fish.community.demo.mapper;

import com.fish.community.demo.model.Userinfo;
import com.fish.community.demo.model.UserinfoExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Mapper
@Component
public interface UserinfoExtMapper {
    List<Userinfo> selectIdIn(ArrayList<Long> longs);

	void incFoucsCount(Long id, Integer i);

	void incFollowedCount(Long id, Integer i);
}