package com.fish.community.demo.resp;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticleDetailResp {

	private String title;
	private String createTime;
	private String modifiedTime;
	private String authorName;
	private Integer viewCount;
	private Integer likeCount;
	private Integer tag;
	private String content;
}
