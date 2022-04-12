package com.fish.community.demo.req;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PublishArticleReq {

	@NotEmpty
	private String title;

	@Min(0)
	private Integer tag;

	@NotEmpty
	private String content;

}
