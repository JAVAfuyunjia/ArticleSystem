package com.articlesystem.test;

import com.articlesystem.entity.User;
import com.articlesystem.service.UserService;
import com.articlesystem.service.impl.UserServiceImpl;
import org.junit.Test;

/**
 * @author 云佳
 * @create 2022-10-25 19:57
 * @只管耕耘，莫问收获。
 */
public class TestUserService {
   UserService userService = new UserServiceImpl();
    @Test
    public void testInsert() {
        User user = new User(null, "付云佳", "3434343", "1502055543", null, null);
        boolean a = userService.insert(user);
    }
    @Test
    public void testGetUserByUserName(){
        User user = userService.getUserByUserName("付锦");

    }

    @Test
    public void testGetUserByUserId(){
        User user = userService.getUser(8);
        System.out.println(user);
    }
    @Test
    public void testUpdateUser(){
        User user = new User(8, "刘晓丽11", "7777777777", "15020586666", null,"localhost:8888");
        boolean rows = userService.update(user);
    }


}
