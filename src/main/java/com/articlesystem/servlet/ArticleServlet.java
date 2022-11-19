package com.articlesystem.servlet;

import com.articlesystem.Utils.ArticleSystemConstant;
import com.articlesystem.Utils.MyUtils;
import com.articlesystem.Utils.PageUtils;
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
    public void articleInsert(HttpServletRequest request,HttpServletResponse response) throws IOException {
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

        MyUtils.JsonResultToWrite(Msg.success(),response.getWriter());
    }

    /**
     * 查询文章
     * @param request
     * @param response
     * @throws IOException
     */
    public void getArticles(HttpServletRequest request,HttpServletResponse response) throws IOException {
        int currentPage = (request.getParameter("currentPage") == null) ? 1 : Integer.parseInt(request.getParameter("currentPage"));
        String keyword = (request.getParameter("keyword") == null) ? "" : (request.getParameter("keyword"));
        int pageSize = ArticleSystemConstant.PAGE_SIZE;

        PageUtils<Article> pageInfo = articleService.getArticlePageInfo(keyword, currentPage, pageSize);
        //System.out.print(pageInfo.toString());
        MyUtils.JsonResultToWrite(pageInfo.toString(),response.getWriter());

    }

    /**
     * 通过文章ID获取文章对象
     * @param request
     * @param response
     * @throws IOException
     */
    public void getArticleByArticleId(HttpServletRequest request,HttpServletResponse response) throws IOException {
        int articleId = Integer.parseInt(request.getParameter("articleId"));
        Article article = articleService.getArticleByArticleId(articleId);
        Msg msg = Msg.success().add("article",article);
        MyUtils.JsonResultToWrite(msg,response.getWriter());
    }

    /**
     * 通过category获取文章list
     * @param request
     * @param response
     * @throws IOException
     */
    public void getArticlesByCategoryId(HttpServletRequest request,HttpServletResponse response) throws IOException {

        int categoryId = Integer.parseInt(request.getParameter("categoryId"));
        int currentPage = (request.getParameter("currentPage") == null) ? 1 : Integer.parseInt(request.getParameter("currentPage"));
        int pageSize = ArticleSystemConstant.PAGE_SIZE;


        PageUtils<Article> pageInfo = articleService.getArticlePageInfoByCategoryId(categoryId,currentPage,pageSize);
        //System.out.print(pageInfo.toString());
        MyUtils.JsonResultToWrite(pageInfo.toString(),response.getWriter());

    }

    /**
     * 查看文章全文
     * @param request
     * @param response
     * @throws IOException
     * @throws ServletException
     */
    public void toArticleView(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException {


        request.getRequestDispatcher("/WEB-INF/view/ArticleView.html").forward(request,response);
    }







}
