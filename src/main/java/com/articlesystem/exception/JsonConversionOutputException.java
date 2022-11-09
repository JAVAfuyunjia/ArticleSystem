package com.articlesystem.exception;

/**
 * @author 云佳
 * @create 2022-10-25 22:29
 * @只管耕耘，莫问收获。
 */
public class JsonConversionOutputException extends RuntimeException{
    public JsonConversionOutputException() {
    }

    public JsonConversionOutputException(String msg) {
        super(msg);
    }
    public JsonConversionOutputException(String msg,RuntimeException e) {
        super(msg,e);

    }



}
