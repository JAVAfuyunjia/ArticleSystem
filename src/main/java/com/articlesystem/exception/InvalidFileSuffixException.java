package com.articlesystem.exception;

/**
 * @author 云佳
 * @create 2022-10-31 18:42
 * @只管耕耘，莫问收获。
 */
public class InvalidFileSuffixException extends RuntimeException{
    public InvalidFileSuffixException() {
    }

    public InvalidFileSuffixException(String message) {
        super(message);
    }

    public InvalidFileSuffixException(String message, RuntimeException e) {
        super(message, e);
    }
}
