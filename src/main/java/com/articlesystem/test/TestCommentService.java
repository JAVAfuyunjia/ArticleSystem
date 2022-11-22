package com.articlesystem.test;

import com.articlesystem.dao.CommentDao;
import com.articlesystem.entity.Comment;
import org.junit.Test;

/**
 * @author 云佳
 * @create 2022-11-21 0:05
 * @往之不谏，来者可追。
 */
public class TestCommentService {
    CommentDao commentDao =  new CommentDao();
    @Test
    public void testCommentInsert(){
        for (int i = 0; i < 10; i++) {
            Comment comment = new Comment();
            comment.setCommentContent("正丝好文章"+i);
            comment.setCommentAuthorName("杨亚锥");
            comment.setCommentAuthorAvatar("http://localhost:8080/ArticleSystem/ArticleSystemImg/166885601063082088.jpg");
            comment.setCommentArticleId(390);
            Integer key = commentDao.commentInsert(comment);
        }

    }

    @Test
    public void testDeleteCommentByCommentId(){
        commentDao.deleteCommentByCommentId(3);
    }
}
