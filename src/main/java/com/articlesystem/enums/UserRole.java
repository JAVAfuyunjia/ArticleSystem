package com.articlesystem.enums;

/**
 * @author 云佳
 * @create 2022-10-27 20:58
 * @只管耕耘，莫问收获。
 */
public enum UserRole {

    ADMIN("admin", "管理员"),

    USER("user", "用户");

    private String value;

    private String message;

    UserRole(String value, String message) {
        this.value = value;
        this.message = message;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
