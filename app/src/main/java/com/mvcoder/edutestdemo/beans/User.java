package com.mvcoder.edutestdemo.beans;

import java.util.Date;

public class User {

    private String username;
    private String password;
    private String nickname;
    private int sex;  //1男，2女，0未设置
    private String avartar;//头像路径
    private String phone;
    private String email;

    private int type;//用户类型（0未设置，1学生，2教师）
    private String number;//学号、职工号
    private String classRoomId; //班级

    private String post;//职位
    private String jobTitle; //职称

    // MySelf
    private String remember_token;
    private Date loginTime;
    private boolean isLogin;

}
