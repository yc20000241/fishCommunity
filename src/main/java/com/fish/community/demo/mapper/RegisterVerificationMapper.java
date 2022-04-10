package com.fish.community.demo.mapper;

import com.fish.community.demo.model.RegisterVerification;
import com.fish.community.demo.model.RegisterVerificationExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface RegisterVerificationMapper {
    long countByExample(RegisterVerificationExample example);

    int deleteByExample(RegisterVerificationExample example);

    int deleteByPrimaryKey(Long id);

    int insert(RegisterVerification record);

    int insertSelective(RegisterVerification record);

    List<RegisterVerification> selectByExample(RegisterVerificationExample example);

    RegisterVerification selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") RegisterVerification record, @Param("example") RegisterVerificationExample example);

    int updateByExample(@Param("record") RegisterVerification record, @Param("example") RegisterVerificationExample example);

    int updateByPrimaryKeySelective(RegisterVerification record);

    int updateByPrimaryKey(RegisterVerification record);
}