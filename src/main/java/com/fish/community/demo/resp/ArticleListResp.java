package com.fish.community.demo.resp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticleListResp {
	private Long id;
	private String title;
	private String content;
	private String gmtCreateTime;
	private String gmtModifiedTime;
	private Long author;
	private Integer viewCount;
	private Integer likeCount;
	private Integer tag;
}
