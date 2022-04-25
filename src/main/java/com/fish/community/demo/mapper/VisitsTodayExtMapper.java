package com.fish.community.demo.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;


@Mapper
@Component
public interface VisitsTodayExtMapper {
    Integer todayVisit(String date);
}