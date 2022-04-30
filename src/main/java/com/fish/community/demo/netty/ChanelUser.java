package com.fish.community.demo.netty;

import io.netty.channel.Channel;
import lombok.Data;

@Data
public class ChanelUser {

	private Long uuid;
	private Long senderId;
}
