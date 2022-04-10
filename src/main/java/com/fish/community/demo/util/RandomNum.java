package com.fish.community.demo.util;

import java.util.Random;

public class RandomNum {

	public static String getRandomNum(){
		String str = "0123456789";
		StringBuilder sb = new StringBuilder(6);
		for (int i = 0; i < 6; i++) {
			char ch = str.charAt(new Random().nextInt(str.length()));
			sb.append(ch);
		}
		return sb.toString();
	}
}
