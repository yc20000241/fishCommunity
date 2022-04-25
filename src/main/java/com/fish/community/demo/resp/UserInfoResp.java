package com.fish.community.demo.resp;

import lombok.Data;

@Data
public class UserInfoResp {

	private Long id;
	private Long userId;
	private Integer sex;
	private Integer pageViews;
	private Integer focusOnCount;
	private String imgUrl;
	private Integer followedCount;
	private String sign;
	private String nick;
	private Integer articleCount;
	private String gmtCreate;
	private boolean isFocus;
}
