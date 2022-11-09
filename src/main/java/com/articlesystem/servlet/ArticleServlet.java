package com.articlesystem.servlet;

import com.articlesystem.Utils.MyUtils;
import com.articlesystem.entity.Article;
import com.articlesystem.entity.Msg;
import com.articlesystem.entity.User;
import com.articlesystem.service.ArticleService;
import com.articlesystem.service.UserService;
import com.articlesystem.service.impl.ArticleServiceImpl;
import com.articlesystem.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;

/**
 * @author 云佳
 * @create 2022-10-19 10:13
 * @只管耕耘，莫问收获。
 */
@WebServlet(urlPatterns = "/article")
public class ArticleServlet extends HttpServlet {
    ArticleService articleService  = new ArticleServiceImpl();

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String methodName = request.getParameter("method");

        try {
            Method method = getClass().getDeclaredMethod(methodName, HttpServletRequest.class,
                    HttpServletResponse.class);
            method.setAccessible(true);
            method.invoke(this,request,response);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }

    }

    /**
     * 插入文章
     * @param request
     * @param response
     * @throws IOException
     * @throws ServletException
     */
    public void articleInsert(HttpServletRequest request,HttpServletResponse response) {
        String articleTitle = request.getParameter("articleTitle");
        String articleContent = request.getParameter("articleContent");
        String articleThumbnail = request.getParameter("articleThumbnail");
        String articleCategoryId = request.getParameter("articleCategoryId");
        String userId = request.getParameter("userId");

        Article article = new Article();
        article.setArticleTitle(articleTitle);
        article.setArticleContent(articleContent);
        article.setArticleThumbnail(articleThumbnail);
        article.setCategoryId(Integer.parseInt(articleCategoryId));
        article.setArticleUserId(Integer.parseInt(userId));


        articleService.insertArticle(article);


    }





}
