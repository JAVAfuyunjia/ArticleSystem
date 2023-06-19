package com.articlesystem.service.impl;

import com.articlesystem.Utils.MyUtils;
import com.articlesystem.Utils.PageUtils;
import com.articlesystem.dao.ArticleDao;
import com.articlesystem.dao.CommentDao;
import com.articlesystem.entity.Article;
import com.articlesystem.entity.Attachment;
import com.articlesystem.service.ArticleService;
import java.util.Date;
import java.util.List;

/**
 * @author 云佳
 * @create 2022-10-27 21:23
 * @只管耕耘，莫问收获。
 */
public class ArticleServiceImpl implements ArticleService {
    ArticleDao articleDao =  new ArticleDao();
    CommentDao commentDao =  new CommentDao();

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
        String summaryText = MyUtils.cleanHtmlTag(article.getArticleContent());
        if (summaryText.length() > summaryLength) {
            String summary = summaryText.substring(0, summaryLength);
            article.setArticleSummary(summary);
        } else {
            article.setArticleSummary(summaryText);
        }
        Integer articleId = articleDao.insert(article);


        // 添加文章与分类的关系
        articleDao.insertArticleRefCategory(article.getCategoryId(),articleId);
        // 添加文章与附件的关系
        if(article.getFileId() != -1 && article.getFileId() != null){
            articleDao.insertArticleRefAttachment(article.getFileId(),articleId);
        }
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

    @Override
    public PageUtils<Article> getArticlePageInfoByUserId(int currentPage, int pageSize, Integer userId) {
        return articleDao.getArticlePageInfoByUserId(currentPage,pageSize,userId);
    }

    @Override
    public void deleteArticleByArticleId(int articleId) {
        articleDao.deleteArticleByArticleId(articleId);
        // 删除文章分类关系
        articleDao.deleteArticleRefCategory(articleId);
        // 删除文章下的评论
        commentDao.deleteCommentByArticleId(articleId);
    }

    @Override
    public int getCategoryIdByArticleId(int articleId) {
        return articleDao.getCategoryIdByArticleId(articleId);
    }

    @Override
    public void articleUpdate(Article article) {

        //文章摘要
        int summaryLength = 150;
        String summaryText = MyUtils.cleanHtmlTag(article.getArticleContent());
        if (summaryText.length() > summaryLength) {
            String summary = summaryText.substring(0, summaryLength);
            article.setArticleSummary(summary);
        } else {
            article.setArticleSummary(summaryText);
        }
        articleDao.articleUpdate(article);

        // 更新分类关系
        articleDao.articleRefCategoryUpdate(article.getCategoryId(),article.getArticleId());
        // 如果附件Id不为空，再次添加附件
        if(article.getFileId() != -1){
            articleDao.insertArticleRefAttachment(article.getFileId(),article.getArticleId());
        }

    }

    @Override
    public List<Integer> getArticleIdsByUserId(int userId) {
        return articleDao.getArticleIdsByUserId(userId);
    }

    @Override
    public List<Article> getTenArticleRandom() {
        return articleDao.getTenArticleRandom();
    }

    @Override
    public int addAttachment(Attachment attachment) {
        return articleDao.addAttachment(attachment);
    }

    /**
     * 通过文章Id获取所有附件
     * @param articleId
     * @return
     */
    @Override
    public List<Attachment> getAttachmentsByArticleId(int articleId) {
        return articleDao.getAttachmentsByArticleId(articleId);
    }

    @Override
    public void deleteAttachmentByAttachmentId(int AttachmentId) {
       articleDao.deleteAttachmentByAttachmentId(AttachmentId);
    }
}
