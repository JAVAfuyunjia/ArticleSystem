package com.articlesystem.servlet;

import com.articlesystem.Utils.MyUtils;
import com.articlesystem.entity.Msg;
import com.articlesystem.entity.User;
import com.articlesystem.service.UserService;
import com.articlesystem.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;

/**
 * @author 云佳
 * @create 2022-10-19 10:13
 * @只管耕耘，莫问收获。
 */
@WebServlet(urlPatterns = "/userServlet")
public class UserServlet extends HttpServlet {
    UserService userService = new UserServiceImpl();

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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

    /**
     * 自动登录
     * @param request
     * @param response
     * @throws IOException
     * @throws ServletException
     */

    public void autoLogin(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        String username = request.getParameter("logname");
        String password = request.getParameter("logpassword");

        String passwordToMd5 = MyUtils.strToMd5(password);

        Msg msg;
        User user = userService.getUserByUserName(username);
        if (user != null && passwordToMd5.equals(user.getUserPass())) {
            msg = Msg.success();
            request.getSession().setAttribute("user", user);
        } else {
            msg = Msg.fail();
        }

        // 返回数据给前端。
        MyUtils.JsonResultToWrite(msg, response.getWriter());

    }

    /**
     * 用户注册
     *
     * @param request
     * @param response
     * @throws IOException
     */
    public void Register(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 1.获取注用户册数据。
        String loginName = request.getParameter("loginName");
        String loginPhoneNum = request.getParameter("loginPhoneNum");
        String loginPassword = request.getParameter("loginPassword");

        // 2.封装数据。
        User user = new User(null, loginName, loginPassword, loginPhoneNum, null, null);

        // 3.将数据插入数据。
        boolean can = userService.insert(user);

        // 4.封装返回数据。
        Msg msg;
        if (can) {
            msg = Msg.success();
        } else {
            msg = Msg.fail();
        }

        // 5.返回结果数据给前端。
        MyUtils.JsonResultToWrite(msg, response.getWriter());

    }

    /**
     * 登录验证
     *
     * @param request
     * @param response
     * @throws IOException
     */

    public void checkLogin(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        String username = request.getParameter("logname");
        String password = request.getParameter("logpassword");
        String rememberme = request.getParameter("rememberme");

        String passwordToMd5 = MyUtils.strToMd5(password);

        Msg msg;
        User user = userService.getUserByUserName(username);
        if (user != null && passwordToMd5.equals(user.getUserPass())) {
            msg = Msg.success();
            request.getSession().setAttribute("user", user);

            if (rememberme != null) {
                //创建两个Cookie对象
                Cookie nameCookie = new Cookie("username", username);
                //设置Cookie的有效期为3天
                nameCookie.setMaxAge(60 * 60 * 24 * 3);
                Cookie pwdCookie = new Cookie("password", password);
                pwdCookie.setMaxAge(60 * 60 * 24 * 3);
                response.addCookie(nameCookie);
                response.addCookie(pwdCookie);
            }
        } else {
            msg = Msg.fail();
        }

        // 返回数据给前端。
        MyUtils.JsonResultToWrite(msg, response.getWriter());

    }

    /**
     * 用户更改信息。
     *
     * @param request
     * @param response
     * @throws IOException
     */

    public void userUpdate(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String userName = request.getParameter("userName");
        String userPhone = request.getParameter("userPhone");
        String userAvatar = request.getParameter("userAvatar");
        Integer userId = Integer.parseInt(request.getParameter("userId"));
        String originalUserPass = request.getParameter("originalUserPass");

        String newUserPass = request.getParameter("newUserPass");

        Msg msg;
        boolean isCan = false;

        String userPass = userService.getUserByUserId(userId).getUserPass();
        String originalUserPassToMd5 = MyUtils.strToMd5(originalUserPass);
        // 新密码为空则不修改密码
        if(newUserPass != ""){
            // 加密新密码
            String newUserPassToMd5 = MyUtils.strToMd5(newUserPass);

            // 密码校验
            if (originalUserPassToMd5.equals(userPass)) {
                User user = new User(userId, userName, newUserPassToMd5, userPhone, null, userAvatar);
                isCan = userService.update(user);
            }
        }else{
            // 密码校验
            if (originalUserPassToMd5.equals(userPass)) {

                User user = new User(userId, userName, "", userPhone, null, userAvatar);
                isCan = userService.updateNoNewPassword(user);
            }
        }

        if (isCan) {
            msg = Msg.success().add("mesage", "用户更新成功！！");
        } else {
            msg = Msg.fail().add("mesage", "用户更新失败！！");

        }
        // 返回数据给前端。
        MyUtils.JsonResultToWrite(msg, response.getWriter());


    }

    /**
     * 获取当前登录user用户信息。
     *
     * @param request
     * @param response
     * @throws IOException
     */

    public void getUser(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        int userId;
        Msg msg ;
        User user ;
        try {
            userId = ((User) request.getSession().getAttribute("user")).getUserId();
            user = userService.getUserByUserId(userId);
            msg = Msg.success().add("user", user);

        } catch (NullPointerException e) {
            msg = Msg.success().add("user", "no");
        }
        // 返回数据给前端。
        MyUtils.JsonResultToWrite(msg, response.getWriter());
    }


    /**
     * 通过Id获取user用户信息
     * @param request
     * @param response
     * @throws IOException
     * @throws ServletException
     */
    public void getAuthorById(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        int authorId = Integer.parseInt(request.getParameter("authorId"));

        User user = userService.getUserByUserId(authorId);

        Msg msg = Msg.success().add("author", user);

        // 返回数据给前端。
        MyUtils.JsonResultToWrite(msg, response.getWriter());
    }

    /**
     * 验证用户名是否重复
     * @param request
     * @param response
     * @throws IOException
     * @throws ServletException
     */
    public void verifyUserName(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String userName = request.getParameter("userName");
        Msg msg ;
        User user = userService.getUserByUserName(userName);
        if(user == null){
            msg = Msg.success().add("userName", "noRepeat");
        }else{
            msg = Msg.success().add("userName", "Repeat");
        }

        // 返回数据给前端。
        MyUtils.JsonResultToWrite(msg, response.getWriter());
    }

    /**
     * 获取所有user用户信息。
     *
     * @param request
     * @param response
     * @throws IOException
     */

    public void getUsers(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Msg msg ;
        List<User> users;
        try {
            users = userService.getUsers();
            msg = Msg.success().add("users", users);

        } catch (Exception e) {
            msg = Msg.success().add("users", "no");
        }
        // 返回数据给前端。
        MyUtils.JsonResultToWrite(msg, response.getWriter());
    }

    /**
     * 通过用户Id 删除用户
     * @param request
     * @param response
     * @throws IOException
     * @throws ServletException
     */
    public void deleteUserByUserId(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        int userId = Integer.parseInt(request.getParameter("userId"));

        userService.deleteUserByUserId(userId);
    }

    public void updateRole(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        int userId = Integer.parseInt(request.getParameter("userId"));
        String userRole = request.getParameter("role");

        userService.updateRole(userId,userRole);
        request.getRequestDispatcher("/WEB-INF/view/userManage.html").forward(request,response);
    }
}

