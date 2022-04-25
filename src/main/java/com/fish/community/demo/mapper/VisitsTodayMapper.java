package com.fish.community.demo.mapper;

import com.fish.community.demo.model.VisitsToday;
import com.fish.community.demo.model.VisitsTodayExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface VisitsTodayMapper {
    long countByExample(VisitsTodayExample example);

    int deleteByExample(VisitsTodayExample example);

    int deleteByPrimaryKey(Long id);

    int insert(VisitsToday record);

    int insertSelective(VisitsToday record);

    List<VisitsToday> selectByExample(VisitsTodayExample example);

    VisitsToday selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") VisitsToday record, @Param("example") VisitsTodayExample example);

    int updateByExample(@Param("record") VisitsToday record, @Param("example") VisitsTodayExample example);

    int updateByPrimaryKeySelective(VisitsToday record);

    int updateByPrimaryKey(VisitsToday record);
}