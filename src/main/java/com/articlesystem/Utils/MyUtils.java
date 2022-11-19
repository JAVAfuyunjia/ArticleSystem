package com.articlesystem.Utils;

import com.articlesystem.entity.Msg;
import com.google.gson.Gson;

import java.io.PrintWriter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 常用的工具方法。
 *
 * @author 云佳
 * @create 2022-10-20 15:26
 * @只管耕耘，莫问收获。
 */
public class MyUtils {
    /**
     * 获得Md5加密
     *
     * @param str 原字符串
     * @return 加密后的字符串
     */
    public static String strToMd5(String str) {
        String md5Str = null;
        if (str != null && str.length() != 0) {
            try {
                MessageDigest md = MessageDigest.getInstance("MD5");
                md.update(str.getBytes());
                byte b[] = md.digest();

                int i;
                StringBuffer buf = new StringBuffer("");
                for (int offset = 0; offset < b.length; offset++) {
                    i = b[offset];
                    if (i < 0) {
                        i += 256;
                    }
                    if (i < 16) {
                        buf.append("0");
                    }
                    buf.append(Integer.toHexString(i));
                }
                //32位
                md5Str = buf.toString();
                //16位
                //md5Str = buf.toString().substring(8, 24);
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
        }
        return md5Str;
    }

    /**
     * 转化Msg实例对象为Json字符串，meanwhile将字符输出给前端。
     * @param msg
     * @param writer
     * @return
     */
    public static boolean JsonResultToWrite(Msg msg, PrintWriter writer) {
        try {
            // 1.将对象转为JSON字符串
            String json = new Gson().toJson(msg);

            // 2.将JSON结果作为参数返回到Ajax的回调函数。
            writer.write(json);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            // 3.关闭流。
            writer.close();
        }
        return true;
    }


    public static boolean JsonResultToWrite(String json, PrintWriter writer) {
        try {


            writer.write(json);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            // 3.关闭流。
            writer.close();
        }
        return true;
    }
}
