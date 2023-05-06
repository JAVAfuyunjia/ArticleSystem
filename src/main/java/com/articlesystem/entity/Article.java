package com.articlesystem.entity;

import com.alibaba.fastjson.JSON;

import java.util.Date;

public class Article {

    private int articleId;

    private Integer articleUserId;

    private String articleTitle;

    private Integer articleIsComment;

    private Date articleCreateTime;

    private String articleThumbnail;

    private String articleContent;

    private String articleSummary;

    private String categoryName;

    private int categoryId;

    private String authorName;

    private Integer fileId;

    public Integer getFileId() {
        return fileId;
    }

    public void setFileId(Integer fileId) {
        this.fileId = fileId;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getAuthorName() {
        return authorName;
    }

    public int getArticleId() {
        return articleId;
    }

    public void setArticleId(int articleId) {
        this.articleId = articleId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }



    public Article() {
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

    public String toString(){
        return JSON.toJSONString(this);
    }


}
