package com.articlesystem.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;

/**
 * 做个各种页面的转发
 *
 * @author 云佳
 * @create 2022-10-27 15:08
 * @只管耕耘，莫问收获。
 */

@WebServlet(urlPatterns = "/manager")
public class ManagerServlet extends HttpServlet {

  private final long serialVersionUID = 1L;

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    doPost(request, response);
  }

  //manager?method=logout
  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    String methodName = request.getParameter("method");

    try {
      Method method = getClass().getDeclaredMethod(methodName, HttpServletRequest.class,
          HttpServletResponse.class);
      method.setAccessible(true);
      method.invoke(this, request, response);
    } catch (Exception e) {
      e.printStackTrace();
      throw new RuntimeException();
    }
  }

  public void managerArticleInsert(HttpServletRequest request, HttpServletResponse response)
      throws IOException, ServletException {
    request.getRequestDispatcher("/WEB-INF/view/managerArticleInsert.html")
        .forward(request, response);

  }

  public void managerArticles(HttpServletRequest request, HttpServletResponse response)
      throws IOException, ServletException {
    request.getRequestDispatcher("/WEB-INF/view/managerArticles.html").forward(request, response);

  }

  public void managerHome(HttpServletRequest request, HttpServletResponse response)
      throws IOException, ServletException {
    request.getRequestDispatcher("/WEB-INF/view/managerHome.html").forward(request, response);

  }

  public void managerProfile(HttpServletRequest request, HttpServletResponse response)
      throws IOException, ServletException {
    request.getRequestDispatcher("/WEB-INF/view/managerProfile.html").forward(request, response);

  }

  public void articleReview(HttpServletRequest request, HttpServletResponse response)
      throws IOException, ServletException {
    request.getRequestDispatcher("/WEB-INF/view/articleReview.html").forward(request, response);

  }


  public void logout(HttpServletRequest request, HttpServletResponse response)
      throws IOException, ServletException {
    request.getSession().removeAttribute("user");
    request.getSession().invalidate();

    Cookie[] cookies = request.getCookies();
    for (Cookie cookie :
        cookies) {
      cookie.setMaxAge(0);
      response.addCookie(cookie);
    }

    response.sendRedirect("login.html");
  }

  public void toHome(HttpServletRequest request, HttpServletResponse response)
      throws IOException, ServletException {

    response.sendRedirect("index.html");
  }

  public void managerToEdit(HttpServletRequest request, HttpServletResponse response)
      throws IOException, ServletException {
    request.getRequestDispatcher("/WEB-INF/view/managerArticleEdit.html")
        .forward(request, response);

  }


}
