package com.articlesystem.test;

import com.articlesystem.Utils.JDBCUtils;
import org.junit.Test;

import java.sql.SQLException;

/**
 * @author fu云佳
 * @create 2022-10-12 22:20
 * @只管耕耘，莫问收获。
 */
public class JDBCUtilsTest {
    /**
     * 测试获取数据库连接。
     * @throws SQLException
     */
    @Test
    public void test() throws SQLException {
        System.out.println(JDBCUtils.getConnection());
    }
}
