package com.fish.community.demo.netty;


import io.netty.channel.Channel;

import java.util.HashMap;

public class ChanelUUIDRel {
	private static HashMap<Channel,ChanelUser> manage = new HashMap<>();

	public static HashMap<Channel, ChanelUser> getManage() {
		return manage;
	}

	public static void setManage(HashMap<Channel, ChanelUser> manage) {
		ChanelUUIDRel.manage = manage;
	}

	public static  void put(Channel channel, ChanelUser chanelUser){
		manage.put(channel,chanelUser);
	}

	public static ChanelUser get(Channel channel){
		return manage.get(channel);
	}
}
