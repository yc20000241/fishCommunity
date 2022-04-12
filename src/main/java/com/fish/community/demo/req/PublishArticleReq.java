package com.fish.community.demo.req;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PublishArticleReq {

	@NotEmpty
	private String title;

	@NotNull
	private Integer tag;

	@NotNull
	private MultipartFile file;
}
