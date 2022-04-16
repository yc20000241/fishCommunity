package com.fish.community.demo.resp;

import com.fish.community.demo.model.Comment;
import lombok.Data;

import java.util.List;

@Data
public class CommentsResp {

	private List<Comment> commentList;
	private Long commentCount;
}
