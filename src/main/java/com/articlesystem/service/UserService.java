package com.articlesystem.service;

import com.articlesystem.entity.User;

/**
 * @author 云佳
 * @create 2022-10-24 21:27
 * @只管耕耘，莫问收获。
 */
public interface UserService {

    boolean insert(User user);

    User getUserByUserName(String username);

    User getUser(int userId);

    boolean update(User user);
}
