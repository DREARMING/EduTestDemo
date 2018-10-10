package com.mvcoder.edutestdemo.beans;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Index;
import org.greenrobot.greendao.annotation.Keep;
import org.greenrobot.greendao.annotation.Transient;

import java.util.Date;

@Entity
public class User {

    @Id
    private long userId;
    private String username;
    private String password;
    private String nickname;
    private int sex;  //1男，2女，0未设置
    private String avartar;//头像路径
    private String phone;
    private String email;

    private int type;//用户类型（0未设置，1管理员，2教师）

    @Index(unique = true)
    private String number;//学号、职工号
    private int classRoomId; //班级

    private String post;//职位
    private String jobTitle; //职称

    // MySelf
    private String remember_token;
    private Date loginTime;
    private boolean isLogin;

    private String token;

    @Index
    private long lastModified;

    @Transient
    private int state;

    @Generated(hash = 645616089)
    public User(long userId, String username, String password, String nickname,
            int sex, String avartar, String phone, String email, int type,
            String number, int classRoomId, String post, String jobTitle,
            String remember_token, Date loginTime, boolean isLogin, String token,
            long lastModified) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.nickname = nickname;
        this.sex = sex;
        this.avartar = avartar;
        this.phone = phone;
        this.email = email;
        this.type = type;
        this.number = number;
        this.classRoomId = classRoomId;
        this.post = post;
        this.jobTitle = jobTitle;
        this.remember_token = remember_token;
        this.loginTime = loginTime;
        this.isLogin = isLogin;
        this.token = token;
        this.lastModified = lastModified;
    }
    @Generated(hash = 586692638)
    public User() {
    }
    public long getUserId() {
        return this.userId;
    }
    public void setUserId(long userId) {
        this.userId = userId;
    }
    public String getUsername() {
        return this.username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return this.password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getNickname() {
        return this.nickname;
    }
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
    public int getSex() {
        return this.sex;
    }
    public void setSex(int sex) {
        this.sex = sex;
    }
    public String getAvartar() {
        return this.avartar;
    }
    public void setAvartar(String avartar) {
        this.avartar = avartar;
    }
    public String getPhone() {
        return this.phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getEmail() {
        return this.email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public int getType() {
        return this.type;
    }
    public void setType(int type) {
        this.type = type;
    }
    public String getNumber() {
        return this.number;
    }
    public void setNumber(String number) {
        this.number = number;
    }
    public int getClassRoomId() {
        return this.classRoomId;
    }
    public void setClassRoomId(int classRoomId) {
        this.classRoomId = classRoomId;
    }
    public String getPost() {
        return this.post;
    }
    public void setPost(String post) {
        this.post = post;
    }
    public String getJobTitle() {
        return this.jobTitle;
    }
    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }
    public String getRemember_token() {
        return this.remember_token;
    }
    public void setRemember_token(String remember_token) {
        this.remember_token = remember_token;
    }
    public Date getLoginTime() {
        return this.loginTime;
    }
    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }
    public boolean getIsLogin() {
        return this.isLogin;
    }
    public void setIsLogin(boolean isLogin) {
        this.isLogin = isLogin;
    }
    public long getLastModified() {
        return this.lastModified;
    }
    public void setLastModified(long lastModified) {
        this.lastModified = lastModified;
    }
    @Keep
    public int getState() {
        return state;
    }

    @Keep
    public void setState(int state) {
        this.state = state;
    }
    public String getToken() {
        return this.token;
    }
    public void setToken(String token) {
        this.token = token;
    }


}
