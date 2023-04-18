package org.example.entity;

import java.security.Principal;

public class User implements Principal {

    public User(Integer userId, String userName, String password){
        this.userId=userId;
        this.userName=userName;
        this.password=password;
    }
    private Integer userId;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    private String userName;
    private String password;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Override
    public String getName() {
        return null;
    }
}
