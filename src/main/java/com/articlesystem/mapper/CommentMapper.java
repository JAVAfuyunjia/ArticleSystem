package com.articlesystem.mapper;

import com.articlesystem.entity.Comment;

public interface CommentMapper {
    int insert(Comment record);

    int insertSelective(Comment record);
}