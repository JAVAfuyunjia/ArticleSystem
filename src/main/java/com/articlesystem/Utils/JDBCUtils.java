package com.articlesystem.Utils;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author 云佳
 * @create 2022-10-12 11:08
 * @只管耕耘，莫问收获。
 */
public class JDBCUtils {

    private static DataSource dataSource = null;



    static{
        // 数据源创建一次。
        dataSource = new ComboPooledDataSource("articlesystem");
    }

    /**
     * 释放连接。
     * @param connection
     */
    public static void releaseConnection(Connection connection){
        if(connection != null){
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 释放连接。
     * @param ps
     */
    public static void releaseConnection(PreparedStatement ps){
        if(ps != null){
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * 获取数据库连接。
     * @return
     * @throws SQLException
     */
    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();


    }

    /**
     *
     * @param resultSet
     * @param preparedStatement
     */
    public static void release(ResultSet resultSet, PreparedStatement preparedStatement){
        if(resultSet != null){
            try {
                resultSet.close();
            } catch (SQLException throwables) {
                System.out.println("数据库连接错误");
                throwables.printStackTrace();
            }
        }
        if (preparedStatement != null){
            try {
                preparedStatement.close();
            } catch (SQLException throwables) {
                System.out.println("数据库连接错误");
                throwables.printStackTrace();
            }
        }
    }

    /**
     * 释放连接。
     * @param rs
     */
    public static void releaseConnection(ResultSet rs){
        if(rs != null){
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
