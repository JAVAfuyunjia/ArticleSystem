package com.articlesystem.entity;

public class User {
    private Integer userId;

    private String userName;

    private String userPass;

    private String userPhoneNumber;

    private String userRole;

    private String userAvatar;

    public User() {
    }

    public User(Integer userId, String userName, String userPass, String userPhoneNumber, String userRole, String userAvatar) {
        this.userId = userId;
        this.userName = userName;
        this.userPass = userPass;
        this.userPhoneNumber = userPhoneNumber;
        this.userRole = userRole;
        this.userAvatar = userAvatar;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getUserPass() {
        return userPass;
    }

    public void setUserPass(String userPass) {
        this.userPass = userPass == null ? null : userPass.trim();
    }

    public String getUserPhoneNumber() {
        return userPhoneNumber;
    }

    public void setUserPhoneNumber(String userPhoneNumber) {
        this.userPhoneNumber = userPhoneNumber == null ? null : userPhoneNumber.trim();
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole == null ? null : userRole.trim();
    }

    public String getUserAvatar() {
        return userAvatar;
    }

    public void setUserAvatar(String userAvatar) {
        this.userAvatar = userAvatar == null ? null : userAvatar.trim();
    }
}