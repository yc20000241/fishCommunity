package com.fish.community.demo.resp;

import com.fish.community.demo.model.User;
import com.fish.community.demo.model.Userinfo;
import lombok.Data;

@Data
public class CommentDTOResp {

	private Long id;
	private Long parentId;
	private Integer type;
	private String gmtCreate;
	private Long likeCount;
	private Long commentCount;
	private Long commenterId;
	private Long articleId;
	private String content;
	private Userinfo commentUserInfo;
	private Userinfo commentedUserInfo;
}
