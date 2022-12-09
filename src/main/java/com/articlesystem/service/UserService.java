package com.articlesystem.service;

import com.articlesystem.entity.User;

import java.util.List;

/**
 * @author 云佳
 * @create 2022-10-24 21:27
 * @只管耕耘，莫问收获。
 */
public interface UserService {

    boolean insert(User user);

    User getUserByUserName(String username);

    User getUserByUserId(int userId);

    boolean update(User user);


    boolean updateNoNewPassword(User user);

    List<User> getUsers();

    void deleteUserByUserId(int userId);

    void updateRole(int userId, String userRole);
}
