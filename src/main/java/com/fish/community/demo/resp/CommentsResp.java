package com.fish.community.demo.resp;

import lombok.Data;

import java.util.List;

@Data
public class CommentsResp {

	private List<CommentDTOResp> commentList;
	private Long commentCount;
}
