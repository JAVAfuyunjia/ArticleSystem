package com.articlesystem.dao;

import com.articlesystem.Utils.JDBCUtils;
import com.articlesystem.Utils.PageUtils;
import com.articlesystem.entity.Article;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ColumnListHandler;
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
                    "`as_article`(article_user_id,article_title,article_content,article_create_time,article_thumbnail,article_summary) " +
                    "VALUES(?,?,?,?,?,?);";
            ps = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            ps.setInt(1,article.getArticleUserId());
            ps.setString(2,article.getArticleTitle());
            ps.setString(3,article.getArticleContent());
            ps.setTimestamp(4, new Timestamp(System.currentTimeMillis()));
            ps.setString(5,article.getArticleThumbnail());
            ps.setString(6,article.getArticleSummary());

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

    /**
     * 获取所有文章，带关键字
     * @param keyword
     * @param currentPage
     * @param pageSize
     * @return
     */
    public PageUtils<Article> getArticlePageInfo(String keyword, int currentPage, int pageSize) {
        Connection connection = null;
        int startIndex = (currentPage-1)*pageSize;
        PageUtils<Article> articlesPageInfo = null;
        try {
            connection = JDBCUtils.getConnection();
            String sql = "SELECT a.article_id AS articleId,article_user_id AS articleUserId,article_title AS articleTitle,article_is_comment AS articleIsComment,article_create_time AS articleCreateTime,article_summary AS articleSummary,article_thumbnail AS articleThumbnail,category_name AS categoryName " +
                    "FROM as_article a JOIN as_article_category_ref ac ON a.article_id = ac.article_id JOIN as_category c ON ac.category_id = c.category_id " +
                    "WHERE a.article_title LIKE '%"+keyword+"%' "+"or a.article_summary LIKE '%"+keyword+"%'" +" ORDER BY a.article_create_time desc "+
                    "LIMIT ?,?;";
            Object[] param = {startIndex,pageSize};
            BeanListHandler<Article> articleBeanListHandler = new BeanListHandler<>(Article.class);
            List<Article> articleList = queryRunner.query(connection, sql, articleBeanListHandler, param);

            String countSql = "SELECT COUNT(*) FROM as_article a WHERE a.article_title LIKE '%"+keyword+"%' "+" or a.article_summary LIKE '%"+keyword+"%';";
            ScalarHandler<Object> scalarHandler = new ScalarHandler<>();
            Long totalNum = (long) queryRunner.query(connection, countSql, scalarHandler);


            articlesPageInfo = new PageUtils<Article>();
            articlesPageInfo.setList(articleList);
            articlesPageInfo.setCurrentPage(currentPage);
            articlesPageInfo.setPageSize(pageSize);
            articlesPageInfo.setTotalNum(Integer.parseInt(totalNum.toString()));


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            if(connection != null){
                JDBCUtils.releaseConnection(connection);

            }
        }


        return articlesPageInfo;
    }

    /**
     * 通过categoryId获取文章list
     * @param categoryId
     * @param currentPage
     * @param pageSize
     * @return
     */
    public PageUtils<Article> getArticlePageInfoByCategoryId(int categoryId, int currentPage, int pageSize) {

        Connection connection = null;
        int startIndex = (currentPage-1)*pageSize;
        PageUtils<Article> articlesPageInfo = null;
        try {
            connection = JDBCUtils.getConnection();
            BeanListHandler<Article> articleBeanListHandler = new BeanListHandler<>(Article.class);
            String sql = "SELECT a.article_id AS articleId,article_user_id AS articleUserId,article_title AS articleTitle,article_is_comment AS articleIsComment,article_create_time AS articleCreateTime,article_summary AS articleSummary,article_thumbnail AS articleThumbnail,category_name AS categoryName " +
                    "FROM as_article a JOIN as_article_category_ref ac ON a.article_id = ac.article_id JOIN as_category c ON ac.category_id = c.category_id " +
                    "WHERE ac.category_id = ? " +"ORDER BY a.article_create_time desc "+
                    "LIMIT ?,?;";
            Object[] param = {categoryId,startIndex,pageSize};

            List<Article> articleList = queryRunner.query(connection, sql, articleBeanListHandler, param);

            String countSql = "SELECT COUNT(*) FROM as_article_category_ref ac WHERE ac.category_id =?;";
            ScalarHandler<Object> scalarHandler = new ScalarHandler<>();
            Long totalNum = (long) queryRunner.query(connection, countSql, scalarHandler,categoryId);


            articlesPageInfo = new PageUtils<Article>();
            articlesPageInfo.setList(articleList);
            articlesPageInfo.setCurrentPage(currentPage);
            articlesPageInfo.setPageSize(pageSize);
            articlesPageInfo.setTotalNum(Integer.parseInt(totalNum.toString()));



        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            if (connection != null){
                JDBCUtils.releaseConnection(connection);
            }
        }
        return articlesPageInfo;
    }

    /**
     * 通过ArticleId查询文章对象
     * @param articleId
     * @return
     */
    public Article getArticleByArticleId(int articleId) {
        Connection connection = null;
        BeanHandler<Article> articleBeanHandler = new BeanHandler<>(Article.class);
        Article article = null;
        try {
            connection = JDBCUtils.getConnection();
            String sql = "SELECT article_id AS articleId,article_user_id AS articleUserId,article_title AS articleTitle,article_is_comment AS articleIsComment,article_create_time AS articleCreateTime,article_summary AS articleSummary,article_thumbnail AS articleThumbnail,article_content AS articleContent" +
                    " FROM as_article a " +
                    "WHERE  article_id= ? ";
            article = queryRunner.query(connection, sql, articleBeanHandler, articleId);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            if(connection != null){
                JDBCUtils.releaseConnection(connection);

            }
        }
        return article;
    }

    /**
     * 通过用户Id查询用户的所有文章
     * @param currentPage
     * @param pageSize
     * @param userId
     * @return
     */
    public PageUtils<Article> getArticlePageInfoByUserId(int currentPage, int pageSize, Integer userId) {
        Connection connection = null;
        int startIndex = (currentPage-1)*pageSize;
        PageUtils<Article> articlesPageInfo = null;
        try {
            connection = JDBCUtils.getConnection();
            String sql = "SELECT a.article_id AS articleId,article_user_id AS articleUserId,article_title AS articleTitle,article_is_comment AS articleIsComment,article_create_time AS articleCreateTime,article_summary AS articleSummary,article_thumbnail AS articleThumbnail,category_name AS categoryName " +
                    "FROM as_article a JOIN as_article_category_ref ac ON a.article_id = ac.article_id JOIN as_category c ON ac.category_id = c.category_id " +
                    "WHERE a.article_user_id = ?" +" ORDER BY a.article_create_time desc "+
                    "LIMIT ?,?;";
            Object[] param = {userId,startIndex,pageSize};
            BeanListHandler<Article> articleBeanListHandler = new BeanListHandler<>(Article.class);
            List<Article> articleList = queryRunner.query(connection, sql, articleBeanListHandler, param);

            String countSql = "SELECT COUNT(*) FROM as_article a WHERE a.article_user_id = "+userId+";";
            ScalarHandler<Object> scalarHandler = new ScalarHandler<>();
            Long totalNum = (long) queryRunner.query(connection, countSql, scalarHandler);


            articlesPageInfo = new PageUtils<Article>();
            articlesPageInfo.setList(articleList);
            articlesPageInfo.setCurrentPage(currentPage);
            articlesPageInfo.setPageSize(pageSize);
            articlesPageInfo.setTotalNum(Integer.parseInt(totalNum.toString()));


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            if(connection != null){
                JDBCUtils.releaseConnection(connection);

            }
        }


        return articlesPageInfo;
    }

    /**
     * 通过id删除文章
     * @param articleId
     */
    public void deleteArticleByArticleId(int articleId) {
        Connection connection = null;
        try {
            connection = JDBCUtils.getConnection();
            String sql = "DELETE FROM `as_article` WHERE article_id = ?;";

            int row = queryRunner.update(connection, sql, articleId);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            if(connection != null){
                JDBCUtils.releaseConnection(connection);
            }
        }
    }

    /**
     * 通过文章id删除文章与分类的关系
     * @param articleId
     */
    public void deleteArticleRefCategory(int articleId) {
        Connection connection = null;
        try {
            connection = JDBCUtils.getConnection();
            String sql = "DELETE FROM `as_article_category_ref` WHERE article_id = ?;";

            int row = queryRunner.update(connection, sql, articleId);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            if(connection != null){
                JDBCUtils.releaseConnection(connection);
            }
        }
    }


    /**
     * 通过ArticleId获取categoryId
     * @param articleId
     * @return
     */
    public int getCategoryIdByArticleId(int articleId) {
        int categoryId =-1;
        Connection connection = null;
        try {
            connection = JDBCUtils.getConnection();
            String sql = "SELECT category_id FROM as_article_category_ref WHERE article_id =?;";
            ScalarHandler<Object> scalarHandler = new ScalarHandler<>();
            categoryId = (int) queryRunner.query(connection, sql, scalarHandler,articleId);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            if(connection != null){
                JDBCUtils.releaseConnection(connection);

            }
        }
        return categoryId;

    }

    /**
     * 更新文章
     * @param article
     */
    public void articleUpdate(Article article) {
        Connection connection = null;
        try {
            connection = JDBCUtils.getConnection();
            String sql = "UPDATE `as_article` SET  article_title=?,article_content=?,article_thumbnail=?,article_summary=? WHERE article_id =?;";
            Object[] param = {article.getArticleTitle(),article.getArticleContent(),article.getArticleThumbnail(),article.getArticleSummary(),article.getArticleId()};

            int row = queryRunner.update(connection, sql, param);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            if(connection != null){
                JDBCUtils.releaseConnection(connection);
            }
        }
    }

    /**
     * 更新关系表
     * @param
     */
    public void articleRefCategoryUpdate(int categoryId,int articleId) {
        Connection connection = null;
        try {
            connection = JDBCUtils.getConnection();
            String sql = "UPDATE `as_article_category_ref` SET  category_id=? WHERE article_id =? ;";

            int row = queryRunner.update(connection, sql,categoryId,articleId );
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            if(connection != null){
                JDBCUtils.releaseConnection(connection);
            }
        }
    }

    /**
     * 通过用户ID获取文章Id集合
     * @param userId
     * @return
     */
    public List<Integer> getArticleIdsByUserId(int userId) {
        Connection connection = null;
        List<Integer> articleIds = new ArrayList<>();
        try {
            connection = JDBCUtils.getConnection();
            String sql = "SELECT `article_id` FROM `as_article` WHERE article_user_id=?;";

            List<Object> results = queryRunner.query(connection, sql, new ColumnListHandler<>("article_id"),userId);
            for (Object i:results
                 ) {
                articleIds.add((Integer)i);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            if(connection != null){
                JDBCUtils.releaseConnection(connection);
            }
        }

        return articleIds;

    }


    public List<Article> getTenArticleRandom() {
        Connection connection = null;
        BeanListHandler<Article> articleBeanListHandler = new BeanListHandler<>(Article.class);
        List<Article> articles =null;
        try {
            connection = JDBCUtils.getConnection();

            String sql = "SELECT\n" +
                    "\ta.article_id AS articleId,\n" +
                    "\tarticle_user_id AS articleUserId,\n" +
                    "\tarticle_title AS articleTitle,\n" +
                    "\tarticle_is_comment AS articleIsComment,\n" +
                    "\tarticle_create_time AS articleCreateTime,\n" +
                    "\tarticle_summary AS articleSummary,\n" +
                    "\tarticle_thumbnail AS articleThumbnail\n" +
                    "\t\n" +
                    "FROM\n" +
                    "\tas_article a\n" +
                    "WHERE\n" +
                    "\tarticle_id >= ( SELECT floor( RAND() * ( SELECT MAX( article_id ) FROM `as_article` ) ) ) \n" +
                    "ORDER BY\n" +
                    "\ta.article_id \n" +
                    "\tLIMIT 10;";
            articles = queryRunner.query(connection, sql, articleBeanListHandler);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            JDBCUtils.releaseConnection(connection);
        }
        return articles;


    }
}
