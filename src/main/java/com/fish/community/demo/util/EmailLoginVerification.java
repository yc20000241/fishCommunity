package com.fish.community.demo.util;

import lombok.Data;

import java.lang.ref.PhantomReference;

@Data
public class EmailLoginVerification {

	private Long gmtTime;
	private Long times;
}
