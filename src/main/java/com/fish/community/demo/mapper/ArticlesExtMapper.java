package com.fish.community.demo.mapper;

import com.fish.community.demo.model.Articles;
import com.fish.community.demo.model.ArticlesExample;
import com.fish.community.demo.resp.ActiveAuthorResp;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Mapper
@Component
public interface ArticlesExtMapper {

    Articles getLast();

    List<Articles> selectAll(String order, String descOrAsc, String isUserId);

	List<Articles> searchArticleByKey(String key, Integer start, Integer listSize, String isUserId, String order, String descOrAsc);

	Integer getCountSearchArticleByKey(String key, String isUserId);

	void increaseLikeCount(long id);

	List<Articles> selectHotArticle();

	List<Articles> recommend(Integer tag);

	ActiveAuthorResp[] activeAuthor();

	void decreaseLikeCount(long id);

	Integer getCountByAuthor(Long userId);

	List<Articles> selectIdIn(ArrayList<Long> longs);
}