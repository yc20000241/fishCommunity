package com.fish.community.demo.netty;


import java.util.HashMap;

public class ChannelUUIDRel {
	private static HashMap<Long, UserChanelRel> manage = new HashMap<>();

	public static  void put(Long senderId,UserChanelRel userChanelRel){
		manage.put(senderId,userChanelRel);
	}

	public static UserChanelRel get(Long senderId){
		return manage.get(senderId);
	}
}
