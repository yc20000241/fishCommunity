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
import org.springframework.transaction.annotation.Transactional;

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

	@Autowired
	private NotificationMapper notificationMapper;

	@Transactional
	public void sendComment(CommentReq commentReq) {
		hasArticleId(commentReq.getArticleId());
		hasUserId(commentReq.getCommenterId());
		if(commentReq.getRootId() != -1){
			hasUserId(commentReq.getCommentedId());
			hasCommentId(commentReq.getRootId());
		}

		if(commentReq.getParentId() != -1){
			hasCommentId(commentReq.getParentId());
		}else {// 将commentedId设为文章发布者的id
			ArticlesExample articlesExample = new ArticlesExample();
			articlesExample.createCriteria().andIdEqualTo(commentReq.getArticleId());
			Articles articles = articlesMapper.selectByExample(articlesExample).get(0);
			commentReq.setCommentedId(articles.getAuthor());
		}


		Comment comment = CopyUtil.copy(commentReq, Comment.class);
		comment.setGmtCreate(System.currentTimeMillis()+"");
		comment.setLikeCount((long)0);
		comment.setType(0);//0未读 1已读
		comment.setCommentCount((long)0);
		commentMapper.insert(comment);

		//改变评论数
		CommentExample commentExample = new CommentExample();
		if(commentReq.getRootId()!=-1){//二级评论，增加一级评论数量
			commentExample.createCriteria().andIdEqualTo(commentReq.getRootId());
			Comment comment2 = commentMapper.selectByExample(commentExample).get(0);

			Comment comment1 = new Comment();
			comment1.setCommentCount(comment2.getCommentCount()+1);
			commentMapper.updateByExampleSelective(comment1, commentExample);
		}

		//插入消息表
		Notification notification = new Notification();
		notification.setGmtCreate(comment.getGmtCreate());
		notification.setKind(2);
		notification.setContent(comment.getContent());
		notification.setArticleId(comment.getArticleId());
		notification.setAffectId(comment.getCommenterId());
		notification.setActionId(comment.getCommentedId());
		notificationMapper.insertSelective(notification);

	}

	private void hasCommentId(Long rootId) {
		CommentExample commentExample = new CommentExample();
		commentExample.createCriteria().andIdEqualTo(rootId);
		List<Comment> comments = commentMapper.selectByExample(commentExample);
		if(comments.isEmpty())
			throw new BusinessException(BusinessExceptionCode.PARENT_COMMENT_NOT_EXIST);
	}

	public CommentsResp getCommentsList(Long articleId, Long rootId, Integer page, Integer pageSize, Long parentId) {
		//判断文章是否存在
		hasArticleId(articleId);
		//判断评论是否存在
		if(rootId != -1){
			CommentExample commentExample = new CommentExample();
			commentExample.createCriteria().andIdEqualTo(rootId);
			List<Comment> comments = commentMapper.selectByExample(commentExample);
			if(comments.isEmpty())
				throw new BusinessException(BusinessExceptionCode.COMMENT_NOT_EXIST);
		}

		PageHelper.startPage(page, pageSize);
		List<Comment> commentList = null;
		if(rootId == -1)//根评论id为-1则返回一级评论
			commentList = commentExtMapper.selectByArticleIdAndCommentId(articleId, rootId, parentId);
		else//根评论id不为-1则返回多级评论
			commentList = commentExtMapper.selectByArticleIdAndRootId(articleId, rootId);
		PageInfo<Comment> objectPageInfo = new PageInfo<>(commentList);

		List<CommentDTOResp> commentDTORespList = new ArrayList();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Userinfo userinfo = null;
		for (Comment comment : commentList) {
			CommentDTOResp commentDTOResp = CopyUtil.copy(comment, CommentDTOResp.class);
			//查出评论人的相关信息
			UserinfoExample userinfoExample = new UserinfoExample();
			userinfoExample.createCriteria().andUserIdEqualTo(comment.getCommenterId());
			userinfo = userinfoMapper.selectByExample(userinfoExample).get(0);
			commentDTOResp.setCommentUserInfo(userinfo);
			//查出被评论人的相关信息
			UserinfoExample userinfoExample1 = new UserinfoExample();
			userinfoExample1.createCriteria().andUserIdEqualTo(comment.getCommentedId());
			userinfo = userinfoMapper.selectByExample(userinfoExample1).get(0);
			commentDTOResp.setCommentedUserInfo(userinfo);
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
