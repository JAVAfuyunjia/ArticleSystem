package com.articlesystem.service.impl;

import com.articlesystem.dao.ArticleDao;
import com.articlesystem.entity.Article;
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
}
