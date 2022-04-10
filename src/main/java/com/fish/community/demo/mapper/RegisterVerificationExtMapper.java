package com.fish.community.demo.mapper;

import com.fish.community.demo.model.RegisterVerification;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface RegisterVerificationExtMapper {

    void deleteInIds(List<Long> arr);

    List<RegisterVerification> selectAll();
}