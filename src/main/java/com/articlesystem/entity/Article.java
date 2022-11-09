package com.articlesystem.entity;

import java.awt.print.PrinterAbortException;
import java.util.Date;

public class Article {
    private long article_id;

    private Integer articleUserId;

    private String articleTitle;

    private Integer articleIsComment;

    private Date articleCreateTime;

    private String articleThumbnail;

    private String articleContent;

    private String articleSummary;

    private int categoryId;

    public Article() {
    }

    public long getArticle_id() {
        return article_id;
    }

    public Integer getArticleUserId() {
        return articleUserId;
    }

    public String getArticleTitle() {
        return articleTitle;
    }

    public Integer getArticleIsComment() {
        return articleIsComment;
    }

    public Date getArticleCreateTime() {
        return articleCreateTime;
    }

    public String getArticleThumbnail() {
        return articleThumbnail;
    }

    public String getArticleContent() {
        return articleContent;
    }

    public String getArticleSummary() {
        return articleSummary;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setArticle_id(long article_id) {
        this.article_id = article_id;
    }

    public void setArticleUserId(Integer articleUserId) {
        this.articleUserId = articleUserId;
    }

    public void setArticleTitle(String articleTitle) {
        this.articleTitle = articleTitle;
    }

    public void setArticleIsComment(Integer articleIsComment) {
        this.articleIsComment = articleIsComment;
    }

    public void setArticleCreateTime(Date articleCreateTime) {
        this.articleCreateTime = articleCreateTime;
    }

    public void setArticleThumbnail(String articleThumbnail) {
        this.articleThumbnail = articleThumbnail;
    }

    public void setArticleContent(String articleContent) {
        this.articleContent = articleContent;
    }

    public void setArticleSummary(String articleSummary) {
        this.articleSummary = articleSummary;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }
}