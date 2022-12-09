package com.articlesystem.dao;


import com.articlesystem.Utils.JDBCUtils;
import com.articlesystem.entity.User;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * @author 云佳
 * @create 2022-10-24 21:42
 * @只管耕耘，莫问收获。
 */
public class UserDao {
   private QueryRunner queryRunner = new QueryRunner();

    /**
     * 将用户注册信息存入数据中。
      * @param user
     * @return
     */
    public int insert(User user) {
        int rows = 0;
        Connection connection = null;
        try {
            connection = JDBCUtils.getConnection();
            String sql = "INSERT INTO as_user(user_name,user_pass,user_phone_number) VALUES(?,?,?)";
            rows = queryRunner.update(connection, sql, user.getUserName(), user.getUserPass(), user.getUserPhoneNumber());


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {

            if(connection != null){
                JDBCUtils.releaseConnection(connection);
            }
        }

        return rows;
    }

    public User getUserByUserName(String username) {
        User user = null;
        Connection connection = null;
        try {
            connection = JDBCUtils.getConnection();
            String sql = "select user_id AS userId,user_name AS userName,user_phone_number AS userPhoneNumber,user_pass AS userPass,user_role AS userRole,user_avatar AS userAvatar FROM as_user WHERE user_name = ?";

            BeanHandler<User> userBeanHandler = new BeanHandler<>(User.class);

            user = queryRunner.query(connection, sql, userBeanHandler, username);
        } catch (SQLException throwables) {

            throwables.printStackTrace();
        }finally {
            if(connection != null){
                JDBCUtils.releaseConnection(connection);
            }
        }


        return user;


    }

    public User getUserByUserId(int userId) {

        User user = null;
        Connection connection = null;
        try {
            connection = JDBCUtils.getConnection();
            String sql = "select user_id AS userId,user_name AS userName,user_phone_number AS userPhoneNumber,user_pass AS userPass,user_role AS userRole,user_avatar AS userAvatar FROM as_user WHERE user_id = ?";

            BeanHandler<User> userBeanHandler = new BeanHandler<>(User.class);

            user = queryRunner.query(connection, sql, userBeanHandler, userId);
        } catch (SQLException throwables) {

            throwables.printStackTrace();
        }finally {
            if(connection != null){
                JDBCUtils.releaseConnection(connection);
            }
        }

        return user;
    }

    /**
     * 更新用户信息
     * @param user
     */
    public int update(User user) {
        Connection connection = null;
        int rows =0;
        try {
            connection = JDBCUtils.getConnection();
            String sql = "UPDATE  as_user SET user_name=?," +
                                            "user_pass=?," +
                                            " user_phone_number=?" +
                                            ",user_avatar=? " +
                                            " WHERE user_id = ?;";
            rows = queryRunner.update(connection, sql, user.getUserName(), user.getUserPass(), user.getUserPhoneNumber(), user.getUserAvatar(), user.getUserId());


        } catch (SQLException throwables) {

            throwables.printStackTrace();
        }

    return rows;
    }


    /**
     * 更新用户信息不重新设置密码
     * @param user
     */
    public int updateNoNewPassword(User user) {
        Connection connection = null;
        int rows =0;
        try {
            connection = JDBCUtils.getConnection();
            String sql = "UPDATE  as_user SET user_name=?," +
                    " user_phone_number=?" +
                    ",user_avatar=? " +
                    " WHERE user_id = ?;";
            rows = queryRunner.update(connection, sql, user.getUserName(), user.getUserPhoneNumber(), user.getUserAvatar(), user.getUserId());
        } catch (SQLException throwables) {

            throwables.printStackTrace();
        }

        return rows;
    }

    public User getAuthorById(int authorId) {
        User user = null;
        Connection connection = null;
        try {
            connection = JDBCUtils.getConnection();
            String sql = "select user_id AS userId,user_name AS userName,user_phone_number AS userPhoneNumber,user_pass AS userPass,user_role AS userRole,user_avatar AS userAvatar FROM as_user WHERE user_id= ?";

            BeanHandler<User> userBeanHandler = new BeanHandler<>(User.class);

            user = queryRunner.query(connection, sql, userBeanHandler, authorId);
        } catch (SQLException throwables) {

            throwables.printStackTrace();
        }finally {
            if(connection != null){
                JDBCUtils.releaseConnection(connection);
            }
        }


        return user;



    }

    // 获取所有用户信息
    public List<User> getAllUser() {

        List<User> users = null;
        Connection connection = null;
        BeanListHandler<User> userBeanListHandler = new BeanListHandler<>(User.class);
        try {
            connection = JDBCUtils.getConnection();
            String sql = "select user_id AS userId,user_name AS userName,user_phone_number AS userPhoneNumber,user_pass AS userPass,user_role AS userRole,user_avatar AS userAvatar FROM as_user;";


            users = queryRunner.query(connection, sql, userBeanListHandler);
        } catch (SQLException throwables) {

            throwables.printStackTrace();
        }finally {
            if(connection != null){
                JDBCUtils.releaseConnection(connection);
            }
        }
        return users;
    }

    public void deleteUserByUserId(int userId) {
        Connection connection = null;
        try {
            connection = JDBCUtils.getConnection();
            String sql = "DELETE FROM `as_user` WHERE user_id = ?;";

            int row = queryRunner.update(connection, sql, userId);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            if(connection != null){
                JDBCUtils.releaseConnection(connection);
            }
        }

    }

    public void updateRole(int userId, String userRole) {

        Connection connection = null;
        try {
            connection = JDBCUtils.getConnection();
            String sql = "UPDATE `as_user` SET user_role=? WHERE user_id=?;";



            int row = queryRunner.update(connection, sql, userRole,userId);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            if(connection != null){
                JDBCUtils.releaseConnection(connection);
            }
        }

    }
}
