package com.fish.community.demo.mapper;

import com.fish.community.demo.model.Comment;
import com.fish.community.demo.model.CommentExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface CommentExtMapper {

    List<Comment> selectByArticleIdAndCommentId(Long articleId, Long rootId, Long parentId);

	List<Comment> selectByArticleIdAndRootId(Long articleId, Long rootId);
}