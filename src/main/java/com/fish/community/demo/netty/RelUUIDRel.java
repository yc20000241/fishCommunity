package com.fish.community.demo.netty;


import java.util.HashMap;

public class RelUUIDRel {
	private static HashMap<Long, UserChanelRel> manage = new HashMap<>();

	public static  void put(Long uuid,UserChanelRel userChanelRel){
		manage.put(uuid,userChanelRel);
	}

	public static UserChanelRel get(Long senderId){
		return manage.get(senderId);
	}
}
