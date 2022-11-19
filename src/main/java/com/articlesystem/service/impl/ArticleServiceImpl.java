package com.articlesystem.service.impl;

import com.articlesystem.Utils.PageUtils;
import com.articlesystem.dao.ArticleDao;
import com.articlesystem.entity.Article;
import com.articlesystem.entity.User;
import com.articlesystem.service.ArticleService;

import java.util.ArrayList;
import java.util.Date;

/**
 * @author 云佳
 * @create 2022-10-27 21:23
 * @只管耕耘，莫问收获。
 */
public class ArticleServiceImpl implements ArticleService {
    ArticleDao articleDao =  new ArticleDao();
    @Override
    public ArrayList<Article> recentArticles(Integer userId, int limit) {

        ArrayList<Article> articles = articleDao.listArticleByLimit(userId,limit);
        return articles;
    }

    /**
     * 插入文章和对应关系
     * @param article
     */
    @Override
    public void insertArticle(Article article) {
        // 插入文章
        article.setArticleCreateTime(new Date());
        //文章摘要
        int summaryLength = 150;
        String summaryText = article.getArticleContent();
        if (summaryText.length() > summaryLength) {
            String summary = summaryText.substring(0, summaryLength);
            article.setArticleSummary(summary);
        } else {
            article.setArticleSummary(summaryText);
        }
        Integer articleId = articleDao.insert(article);


        // 添加文章与分类的关系
        articleDao.insertArticleRefCategory(article.getCategoryId(),articleId);

    }

    /**
     * 通过关键字查询文章list
     * @param keyword
     * @param currentPage
     * @param pageSize
     * @return
     */
    @Override
    public PageUtils<Article> getArticlePageInfo(String keyword, int currentPage, int pageSize) {


        return articleDao.getArticlePageInfo(keyword, currentPage, pageSize);
    }

    /**
     * 通过categoryId查询文章list
     * @param categoryId
     * @param currentPage
     * @param pageSize
     * @return
     */
    @Override
    public PageUtils<Article> getArticlePageInfoByCategoryId(int categoryId, int currentPage, int pageSize) {
        return articleDao.getArticlePageInfoByCategoryId(categoryId,currentPage,pageSize);
    }

    @Override
    public Article getArticleByArticleId(int articleId) {

        Article article = articleDao.getArticleByArticleId(articleId);
        return article;
    }


}
