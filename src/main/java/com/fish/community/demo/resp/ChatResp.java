package com.fish.community.demo.resp;

import lombok.Data;

@Data
public class ChatResp {

	private Long senderId;
	private Long receiverId;
	private String content;
	private Integer sign;
	private String img_url;
	private String nick;
}
