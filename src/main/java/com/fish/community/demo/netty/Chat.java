package com.fish.community.demo.netty;

import lombok.Data;

import java.io.Serializable;

@Data
public class Chat implements Serializable {

	private Long senderId;
	private Long receiverId;
	private String content;
	private Integer sign;
}
