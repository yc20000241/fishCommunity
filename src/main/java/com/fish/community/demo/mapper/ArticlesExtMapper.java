package com.fish.community.demo.mapper;

import com.fish.community.demo.model.Articles;
import com.fish.community.demo.model.ArticlesExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface ArticlesExtMapper {

    Articles getLast();

    List<Articles> selectAll();

	int getTotalArticleCount();

	int getTotalPersonalArticleCount(Long userId);

	List<Articles> searchArticleByKey(String key, Integer start, Integer listSize);

	List<Articles> searchPersonalArticleByKey(String key, Integer start, Integer listSize, Long userId);
}