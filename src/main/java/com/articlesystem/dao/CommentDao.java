package com.articlesystem.dao;

import com.articlesystem.Utils.JDBCUtils;
import com.articlesystem.entity.Article;
import com.articlesystem.entity.Comment;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 云佳
 * @create 2022-11-20 21:57
 * @往之不谏，来者可追。
 */
public class CommentDao {
    QueryRunner queryRunner = new QueryRunner();

    /**
     * 添加评论
     * @param comment
     * @return
     */
    public Integer commentInsert(Comment comment) {

        Integer primaryKey = null;
        Connection connection = null;
        PreparedStatement ps;
        ResultSet rs;

        try {
            connection = JDBCUtils.getConnection();
            String sql = "INSERT INTO as_comment(comment_article_id,comment_author_name,comment_author_avatar,comment_content,comment_create_time) " +
                    "VALUES(?,?,?,?,?);";
            ps = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            ps.setInt(1,comment.getCommentArticleId());
            ps.setString(2,comment.getCommentAuthorName());
            ps.setString(3,comment.getCommentAuthorAvatar());
            ps.setString(4,comment.getCommentContent());
            ps.setTimestamp(5, new Timestamp(System.currentTimeMillis()));

            ps.execute();

            rs = ps.getGeneratedKeys();

            if(rs.next()){
                primaryKey = rs.getInt(1);

            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            if(connection != null){
                JDBCUtils.releaseConnection(connection);

            }
        }
        return primaryKey;
    }

    /**
     * 获取comments通过articleId
     * @param articleId
     * @return
     */
    public List<Comment> getCommentListByArticleId(int articleId) {
        List<Comment> comments = null;
        BeanListHandler<Comment> commentBeanListHandler = new BeanListHandler<>(Comment.class);
        Connection connection =null;

        try {
            connection = JDBCUtils.getConnection();
            String sql = "SELECT comment_id AS commentId,comment_article_id AS commentArticleId,comment_author_name AS commentAuthorName,comment_author_avatar AS commentAuthorAvatar,comment_content AS commentContent,comment_create_time AS commentCreateTime FROM `as_comment` " +
                    "WHERE comment_article_id = ? " +
                    "ORDER BY commentCreateTime  ASC;";

            comments = queryRunner.query(connection, sql, commentBeanListHandler, articleId);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            if(connection != null){
                JDBCUtils.releaseConnection(connection);

            }
        }
        return comments;
    }

    /**
     * 通过id删除评论
     * @param commentId
     */
    public void deleteCommentByCommentId(int commentId) {

        Connection connection =null;

        try {
            connection = JDBCUtils.getConnection();
            String sql = "DELETE FROM `as_comment` WHERE comment_id = ?;";
            int row = queryRunner.update(connection, sql, commentId);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            if(connection != null){
                JDBCUtils.releaseConnection(connection);
            }
        }
    }


    /**
     * 通过文章id删除该文章下的所有评论
     * @param articleId
     */
    public void deleteCommentByArticleId(int articleId) {

        Connection connection =null;

        try {
            connection = JDBCUtils.getConnection();
            String sql = "DELETE FROM `as_comment` WHERE comment_article_id = ?;";
            int row = queryRunner.update(connection, sql, articleId);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            if(connection != null){
                JDBCUtils.releaseConnection(connection);
            }
        }
    }
}
