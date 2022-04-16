package com.fish.community.demo.service;

import com.fish.community.demo.exception.BusinessException;
import com.fish.community.demo.exception.BusinessExceptionCode;
import com.fish.community.demo.mapper.*;
import com.fish.community.demo.model.*;
import com.fish.community.demo.req.CommentReq;
import com.fish.community.demo.resp.CommentDTOResp;
import com.fish.community.demo.resp.CommentsResp;
import com.fish.community.demo.util.CopyUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Service
public class CommentService {

	@Autowired
	private CommentMapper commentMapper;

	@Autowired
	private UserMapper userMapper;

	@Autowired
	private ArticlesMapper articlesMapper;

	@Autowired
	private CommentExtMapper commentExtMapper;

	@Autowired
	private UserinfoMapper userinfoMapper;

	public void sendComment(CommentReq commentReq) {
		//查看parentId对应的评论是否存在，不存在则置为-1
		if(commentReq.getParentId() != -1){
			CommentExample commentExample = new CommentExample();
			commentExample.createCriteria().andCommenterIdEqualTo(commentReq.getParentId());
			List<Comment> comments = commentMapper.selectByExample(commentExample);
			if(comments.isEmpty())
				commentReq.setParentId((long)-1);
		}

		hasArticleId(commentReq.getArticleId());

		hasUserId(commentReq.getCommenterId());

		Comment comment = CopyUtil.copy(commentReq, Comment.class);
		comment.setGmtCreate(System.currentTimeMillis()+"");
		comment.setLikeCount((long)0);
		comment.setType(0);//0未读 1已读

		commentMapper.insert(comment);
	}

	public CommentsResp getCommentsList(Long articleId, Long commentId, Integer page, Integer pageSize) {
		//判断文章是否存在
		hasArticleId(articleId);
		//判断评论是否存在
		if(commentId != -1){
			CommentExample commentExample = new CommentExample();
			commentExample.createCriteria().andIdEqualTo(commentId);
			List<Comment> comments = commentMapper.selectByExample(commentExample);
			if(comments.isEmpty())
				throw new BusinessException(BusinessExceptionCode.COMMENT_NOT_EXIST);
		}

		PageHelper.startPage(page, pageSize);
		List<Comment> commentList = commentExtMapper.selectByArticleIdAndCommentId(articleId, commentId);
		PageInfo<Comment> objectPageInfo = new PageInfo<>(commentList);

		UserinfoExample userinfoExample = new UserinfoExample();
		List<CommentDTOResp> commentDTORespList = new ArrayList();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		for (Comment comment : commentList) {
			CommentDTOResp commentDTOResp = CopyUtil.copy(comment, CommentDTOResp.class);
			//查出评论人的相关信息
			userinfoExample.createCriteria().andUserIdEqualTo(comment.getCommenterId());
			Userinfo userinfo = userinfoMapper.selectByExample(userinfoExample).get(0);
			commentDTOResp.setUserinfo(userinfo);
			//将时间戳改为日期格式
			commentDTOResp.setGmtCreate(dateFormat.format(Long.valueOf(commentDTOResp.getGmtCreate())));
			commentDTORespList.add(commentDTOResp);
		}

		CommentsResp commentsResp = new CommentsResp();
		commentsResp.setCommentList(commentDTORespList);
		commentsResp.setCommentCount(objectPageInfo.getTotal());

		return commentsResp;
	}

	public void hasArticleId(Long articleId){
		//查看评论文章是否存在
		ArticlesExample articlesExample = new ArticlesExample();
		articlesExample.createCriteria().andIdEqualTo(articleId);
		List<Articles> articles = articlesMapper.selectByExample(articlesExample);
		if(articles.isEmpty())
			throw new BusinessException(BusinessExceptionCode.COMMENT_ARTICLE_NOT_EXISTS);
	}

	public void hasUserId(Long userId){
		//检查评论人是否存在
		UserExample userExample = new UserExample();
		userExample.createCriteria().andIdEqualTo(userId);
		List<User> users = userMapper.selectByExample(userExample);
		if(users.isEmpty())
			throw new BusinessException(BusinessExceptionCode.COMMENTOR_NOT_EXISTS);
	}

}