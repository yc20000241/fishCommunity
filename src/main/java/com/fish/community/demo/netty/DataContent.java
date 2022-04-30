package com.fish.community.demo.netty;

import lombok.Data;

import java.io.Serializable;

@Data
public class DataContent implements Serializable {

	private Integer action;
	private Chat chat;
	private Long uuid;
	private Long createrId;
}
