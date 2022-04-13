package com.fish.community.demo.resp;

import com.fish.community.demo.model.Articles;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticleListResp {
	private List<Articles> articles;
	private Long totalArticlesCount;
}
