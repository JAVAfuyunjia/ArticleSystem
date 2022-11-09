package com.articlesystem.mapper;

import com.articlesystem.entity.User;

public interface UserMapper {
    int insert(User record);

    int insertSelective(User record);
}