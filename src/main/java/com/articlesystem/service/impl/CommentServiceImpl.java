package com.articlesystem.service.impl;

import com.articlesystem.dao.CommentDao;
import com.articlesystem.entity.Comment;
import com.articlesystem.service.CommentService;
import java.util.List;

/**
 * @author 云佳
 * @create 2022-11-20 21:56
 * @往之不谏，来者可追。
 */
public class CommentServiceImpl implements CommentService {

  CommentDao commentDao = new CommentDao();

  @Override
  public void commentInsert(Comment comment) {
    // 插入评论
    Integer commentId = commentDao.commentInsert(comment);
  }

  @Override
  public List<Comment> getCommentListByArticleId(int articleId) {
    return commentDao.getCommentListByArticleId(articleId);
  }

  @Override
  public void deleteCommentByCommentId(int commentId) {
    commentDao.deleteCommentByCommentId(commentId);
  }

  @Override
  public void deleteCommentByUserName(String userName) {
    commentDao.deleteCommentByUserName(userName);
  }
}
