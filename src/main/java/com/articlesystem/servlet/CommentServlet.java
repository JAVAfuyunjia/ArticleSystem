package com.articlesystem.servlet;

import com.articlesystem.Utils.MyUtils;
import com.articlesystem.entity.Article;
import com.articlesystem.entity.Comment;
import com.articlesystem.entity.Msg;
import com.articlesystem.service.CommentService;
import com.articlesystem.service.impl.CommentServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;

/**
 * @author 云佳
 * @create 2022-11-20 21:54
 * @往之不谏，来者可追。
 */
@WebServlet(urlPatterns = "/comment")
public class CommentServlet extends HttpServlet {
    CommentService commentService =  new CommentServiceImpl();
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
     * 添加评论
     * @param request
     * @param response
     * @throws IOException
     */
    public void commentInsert(HttpServletRequest request,HttpServletResponse response) throws IOException {
        String commentContent = request.getParameter("commentContent");
        String commentAuthorName = request.getParameter("commentAuthorName");
        String commentAuthorAvatar = request.getParameter("commentAuthorAvatar");
        int commentArticleId = Integer.parseInt(request.getParameter("commentArticleId"));

        Comment comment = new Comment();

        comment.setCommentArticleId(commentArticleId);
        comment.setCommentContent(commentContent);
        comment.setCommentAuthorAvatar(commentAuthorAvatar);
        comment.setCommentAuthorName(commentAuthorName);

        commentService.commentInsert(comment);
    }

    public void getCommentListByArticleId(HttpServletRequest request,HttpServletResponse response) throws IOException {
        int articleId = Integer.parseInt(request.getParameter("articleId"));
        List<Comment> comments = commentService.getCommentListByArticleId(articleId);

        Msg msg = Msg.success().add("comments",comments);
        MyUtils.JsonResultToWrite(msg,response.getWriter());
    }


}
