package com.articlesystem.test;

import com.articlesystem.Utils.MyUtils;
import org.junit.Test;

import java.sql.Date;

/**
 * @author 云佳
 * @create 2022-10-20 15:28
 * @只管耕耘，莫问收获。
 */
public class MyTest {
    @Test
    public void testMd5() {

        System.out.println(MyUtils.strToMd5("付云佳"));
    }
}

