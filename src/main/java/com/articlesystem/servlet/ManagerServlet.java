package com.articlesystem.servlet;

import com.articlesystem.entity.Article;
import com.articlesystem.entity.User;
import com.articlesystem.enums.UserRole;
import com.articlesystem.service.ArticleService;
import com.articlesystem.service.impl.ArticleServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * 做个各种页面的转发
 * @author 云佳
 * @create 2022-10-27 15:08
 * @只管耕耘，莫问收获。
 */

@WebServlet(urlPatterns = "/manager")
public class ManagerServlet extends HttpServlet {

    private  final long serialVersionUID = 1L;

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

    public void managerArticleInsert(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException {
        request.getRequestDispatcher("/WEB-INF/view/managerArticleInsert.html").forward(request,response);

    }

    public void managerArticles(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException {
        request.getRequestDispatcher("/WEB-INF/view/managerArticles.html").forward(request,response);

    }
    public void managerHome(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException {
        request.getRequestDispatcher("/WEB-INF/view/managerHome.html").forward(request,response);

    }
    public void managerProfile(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException {
        request.getRequestDispatcher("/WEB-INF/view/managerProfile.html").forward(request,response);

    }

    public void logout(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException {
        request.getSession().invalidate();

        response.sendRedirect("login.html");
    }




}
