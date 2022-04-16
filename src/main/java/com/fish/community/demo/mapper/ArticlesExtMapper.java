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

    List<Articles> selectAll(String order, String descOrAsc, String isUserId);

	List<Articles> searchArticleByKey(String key, Integer start, Integer listSize, String isUserId, String order, String descOrAsc);

	Integer getCountSearchArticleByKey(String key, String isUserId);

	List<Articles> searchArticleByTag(Integer tag, Integer start, Integer listSize);

	List<Articles> searchPersonalArticleByTag(Integer tag, Integer start, Integer listSize, Long userId);

	Integer getCountSearchArticleByTag(Integer tag);

	Integer getCountSearchPersonalArticleByTag(Integer tag, Long userId);
}