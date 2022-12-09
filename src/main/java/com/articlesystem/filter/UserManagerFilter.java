package com.articlesystem.filter;

import com.articlesystem.entity.User;
import com.articlesystem.enums.UserRole;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author 云佳
 * @create 2022-11-27 10:35
 * @往之不谏，来者可追。
 */
public class UserManagerFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse =(HttpServletResponse)response;

        HttpSession session = httpServletRequest.getSession();
        User user = (User)session.getAttribute("user");


        if(user != null){
            if(UserRole.ADMIN.getValue().equals(user.getUserRole())){
                filterChain.doFilter(request,response);

            }else {
                httpServletRequest.getRequestDispatcher("/WEB-INF/view/managerProfile.html").forward(request,response);
            }
        }else{
            httpServletResponse.sendRedirect("login.html");
        }
    }

    @Override
    public void destroy() {

    }
}
