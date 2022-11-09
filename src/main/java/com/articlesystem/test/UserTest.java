package com.articlesystem.test;

import com.articlesystem.entity.User;
import org.junit.Test;

import java.util.Optional;

/**
 * @author 云佳
 * @create 2022-10-26 9:52
 * @只管耕耘，莫问收获。
 */
public class UserTest {
    @Test
    public void test01(){
        User user = null;

        Optional<User> user1 = Optional.ofNullable(user);

        if (null == user){
            System.out.println("user为空");
        }


    }
}
