package com.articlesystem.service;

import com.articlesystem.Utils.PageUtils;
import com.articlesystem.entity.Article;
import com.articlesystem.entity.User;

import java.util.ArrayList;

/**
 * @author 云佳
 * @create 2022-10-27 21:23
 * @只管耕耘，莫问收获。
 */
public interface ArticleService {

    void insertArticle(Article article);

    PageUtils<Article> getArticlePageInfo(String keyword, int currentPage, int pageSize);

    PageUtils<Article> getArticlePageInfoByCategoryId(int categoryId, int currentPage, int pageSize);

    Article getArticleByArticleId(int articleId);

    PageUtils<Article> getArticlePageInfoByUserId(int currentPage, int pageSize, Integer userId);

    void deleteArticleByArticleId(int articleId);


    int getCategoryIdByArticleId(int articleId);

    void articleUpdate(Article article);
}
