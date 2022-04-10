package com.fish.community.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	@Bean
	public Docket docket(){
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(apiinfo())
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.fish.community.demo.controller"))
				.build();
	}

	private ApiInfo apiinfo() {
		Contact contact = new Contact("yc001","","510218929@qq.com");
		return new ApiInfo(
				"咸鱼社区文档",
				"fish document",
				"1.0",
				"",
				contact,
				"Apache 2.0",
				"http://www.apache.org/licenses/LICENSE-2.0",
				new ArrayList()
		);
	}
}
