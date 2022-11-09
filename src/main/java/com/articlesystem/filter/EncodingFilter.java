package com.articlesystem.filter;

import javax.servlet.*;
import java.io.IOException;

/**
 * @author 云佳
 * @create 2022-10-17 21:49
 * @只管耕耘，莫问收获。
 */
public class EncodingFilter implements Filter {

    /**
     * Default constructor.
     */
    public EncodingFilter() {
        // TODO Auto-generated constructor stub
    }

    /**
     * @see Filter#destroy()
     */
    public void destroy() {
        // TODO Auto-generated method stub
    }

    /**
     * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
     */
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        String encoding = filterConfig.getServletContext().getInitParameter("encoding");
        request.setCharacterEncoding(encoding);
        response.setCharacterEncoding(encoding);
        // response.setContentType("text/html;charset=UTF-8");
        chain.doFilter(request, response);
    }

    private FilterConfig filterConfig = null;

    public void init(FilterConfig fConfig) throws ServletException {
        this.filterConfig = fConfig;
    }
}
