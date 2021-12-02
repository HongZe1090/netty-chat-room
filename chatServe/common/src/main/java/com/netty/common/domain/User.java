package com.netty.common.domain;

/**
 * @创建人 HongZe
 * @创建时间 2021/12/2
 * @描述 用户基础信息类
 */
public class User {
    // 用户id
    private String userId;
    // 用户名
    private String userName;
    // 密码
    private String password;
    // 性别
    private String sex;
    // 头像路径
    private String image;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
