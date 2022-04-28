package com.fish.community.demo.resp;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginEmailResp {
	private String emailToken;
}
