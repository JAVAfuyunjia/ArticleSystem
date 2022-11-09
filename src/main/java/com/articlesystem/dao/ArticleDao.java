package com.articlesystem.dao;

import com.articlesystem.Utils.JDBCUtils;
import com.articlesystem.Utils.MyUtils;
import com.articlesystem.entity.Article;
import com.articlesystem.entity.ArticleId;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 通过userId查询文章,如果UserId为null,则查询所有的文章。
 * @author 云佳
 * @create 2022-10-27 21:25
 * @往之不谏，来者可追。
 */
public class ArticleDao {
    QueryRunner queryRunner = new QueryRunner();

    /**
     * 获取所有文章
     *
     * @param userId
     * @param limit
     * @return
     */
    public ArrayList<Article> listArticleByLimit(Integer userId, int limit) {
        List<Article> articles = null;
        BeanListHandler<Article> articleBeanListHandler = new BeanListHandler<>(Article.class);
        Connection connection =null;

        try {
            connection = JDBCUtils.getConnection();
            if(userId == null){
                String sql = "";
                articles = queryRunner.query(connection, sql, articleBeanListHandler, limit);

            }else {
                String sql = "";
                 articles = queryRunner.query(connection, sql, articleBeanListHandler, userId, limit);

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            if(connection != null){
            JDBCUtils.releaseConnection(connection);

            }
        }



        return (ArrayList<Article>) articles;
    }

    /**
     * 添加文章到数据库
     * @param article
     * @return primaryKey
     */

    public Integer insert(Article article) {
        Integer primaryKey = null;
         Connection connection = null;
        PreparedStatement ps =null;
        ResultSet rs = null;
        ResultSetHandler<Object> beanHandler = new BeanHandler<Object>(Object.class);

        try {
            connection = JDBCUtils.getConnection();
            String sql = "INSERT INTO " +
                    "`as_article`(article_user_id,article_title,article_content,article_create_time,article_thumbnail) " +
                    "VALUES(?,?,?,?,?);";
            ps = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            ps.setInt(1,article.getArticleUserId());
            ps.setString(2,article.getArticleTitle());
            ps.setString(3,article.getArticleContent());
            ps.setTimestamp(4, new Timestamp(System.currentTimeMillis()));
            ps.setString(5,article.getArticleThumbnail());


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
     * 添加文章和分类的关系
     * @param categoryId
     * @param articleId
     */
    public void insertArticleRefCategory(Integer categoryId, Integer articleId) {
        Connection connection = null;

        try {
            connection = JDBCUtils.getConnection();
            String sql = "INSERT INTO `as_article_category_ref`(article_id,category_id) VALUES(?,?);";

            Object[] params = {articleId,categoryId};

            queryRunner.update(connection, sql, params);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            if(connection != null){
                JDBCUtils.releaseConnection(connection);

            }
        }
    }
}
