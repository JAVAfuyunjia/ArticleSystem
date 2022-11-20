package com.articlesystem.service;

import com.articlesystem.entity.Comment;

import java.util.List;

/**
 * @author 云佳
 * @create 2022-11-20 21:56
 * @往之不谏，来者可追。
 */
public interface CommentService {
    void commentInsert(Comment comment);

    List<Comment> getCommentListByArticleId(int articleId);
}
