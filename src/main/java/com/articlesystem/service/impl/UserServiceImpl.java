package com.articlesystem.service.impl;

import com.articlesystem.Utils.MyUtils;
import com.articlesystem.dao.UserDao;
import com.articlesystem.entity.User;
import com.articlesystem.service.UserService;
import com.sun.org.apache.bcel.internal.generic.RETURN;

/**
 * @author 云佳
 * @create 2022-10-24 21:27
 * @只管耕耘，莫问收获。
 */
public class UserServiceImpl implements UserService {
    UserDao userDao = new UserDao();

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

}
