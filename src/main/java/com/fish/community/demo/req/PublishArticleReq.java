package com.fish.community.demo.req;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PublishArticleReq {

	@NotEmpty
	private String title;

	@Max(3)
	@Min(0)
	private Integer tag;

	@NotEmpty
	private String content;

	@NotEmpty
	private String articleImg;
}
