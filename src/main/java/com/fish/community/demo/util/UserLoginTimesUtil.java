package com.fish.community.demo.util;

import lombok.Data;

import java.util.HashMap;

@Data
public class UserLoginTimesUtil {

	private static HashMap<String,EmailLoginVerification> manager = new HashMap<>();

	public static void push(String user_agent, EmailLoginVerification emailLoginVerification){
		manager.put(user_agent,emailLoginVerification);
	}

	public static EmailLoginVerification get(String user_agent){
		return manager.get(user_agent);
	}
}
