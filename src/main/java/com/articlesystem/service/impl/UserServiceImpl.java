package com.articlesystem.service.impl;

import com.articlesystem.Utils.MyUtils;
import com.articlesystem.dao.CommentDao;
import com.articlesystem.dao.UserDao;
import com.articlesystem.entity.User;
import com.articlesystem.service.ArticleService;
import com.articlesystem.service.CommentService;
import com.articlesystem.service.UserService;
import com.sun.org.apache.bcel.internal.generic.RETURN;

import java.util.List;

/**
 * @author 云佳
 * @create 2022-10-24 21:27
 * @只管耕耘，莫问收获。
 */
public class UserServiceImpl implements UserService {
    UserDao userDao = new UserDao();
    ArticleService articleService  =new ArticleServiceImpl();
    CommentService commentService = new CommentServiceImpl();

    @Override
    public boolean insert(User user) {
        // 1.将密码进行MD5加密。
        String userPasswordByMD5 = MyUtils.strToMd5(user.getUserPass());

        user.setUserPass(userPasswordByMD5);
        int rows = userDao.insert(user);

        if(rows == 0){
            return false;
        }

        return true ;
    }

    @Override
    public User getUserByUserName(String username) {

        User user = userDao.getUserByUserName(username);

        return user;
     }

    @Override
    public User getUserByUserId(int userId) {
        return userDao.getUserByUserId(userId);
    }

    @Override
    public boolean update(User user) {
        int rows = userDao.update(user);

        if(rows == 0){
            return false;
        }
        return true;
    }

    @Override
    public boolean updateNoNewPassword(User user) {

        int rows = userDao.updateNoNewPassword(user);
        if(rows == 0){
            return false;
        }
        return true;
    }

    @Override
    public List<User> getUsers() {
        List<User> allUser = userDao.getAllUser();
        User userAdmin = null;
        for (User user:allUser
             ) {
            if("admin".equals(user.getUserName()) && "admin".equals(user.getUserRole())){
                userAdmin = user;
            }
        }
        allUser.remove(userAdmin);
        return allUser;
    }

    @Override
    public void deleteUserByUserId(int userId) {
        // 删除该用户的所有评论
        User user= userDao.getUserByUserId(userId);
        String userName = user.getUserName();
        // 删除评论通过用户名
        commentService.deleteCommentByUserName(userName);
        // 删除用户
        userDao.deleteUserByUserId(userId);
        // 删除该用户下的所有的文章
        List<Integer> articleIds = articleService.getArticleIdsByUserId(userId);
        if(articleIds != null && articleIds.size() >0){
            for (Integer articleId:articleIds
            ) {
                articleService.deleteArticleByArticleId(articleId);
            }
        }


    }

    @Override
    public void updateRole(int userId, String userRole) {
        userDao.updateRole(userId,userRole);
    }

}
