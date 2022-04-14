package com.fish.community.demo.resp;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginResp {
	private Long id;

	private boolean loginSuccess;

	private String token;

	private String image_url;
}
