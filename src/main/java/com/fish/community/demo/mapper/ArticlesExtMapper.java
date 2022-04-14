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

	Integer getCountSearchArticleByKey(String key);

	Integer getCountSearchPersonalArticleByKey(String key, Long userId);

	List<Articles> searchArticleByTag(Integer tag, Integer start, Integer listSize);

	List<Articles> searchPersonalArticleByTag(Integer tag, Integer start, Integer listSize, Long userId);

	Integer getCountSearchArticleByTag(Integer tag);

	Integer getCountSearchPersonalArticleByTag(Integer tag, Long userId);
}