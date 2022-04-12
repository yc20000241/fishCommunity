package com.fish.community.demo.mapper;

import com.fish.community.demo.model.Articles;
import com.fish.community.demo.model.ArticlesExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ArticlesMapper {
    long countByExample(ArticlesExample example);

    int deleteByExample(ArticlesExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Articles record);

    int insertSelective(Articles record);

    List<Articles> selectByExampleWithBLOBs(ArticlesExample example);

    List<Articles> selectByExample(ArticlesExample example);

    Articles selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Articles record, @Param("example") ArticlesExample example);

    int updateByExampleWithBLOBs(@Param("record") Articles record, @Param("example") ArticlesExample example);

    int updateByExample(@Param("record") Articles record, @Param("example") ArticlesExample example);

    int updateByPrimaryKeySelective(Articles record);

    int updateByPrimaryKeyWithBLOBs(Articles record);

    int updateByPrimaryKey(Articles record);
}