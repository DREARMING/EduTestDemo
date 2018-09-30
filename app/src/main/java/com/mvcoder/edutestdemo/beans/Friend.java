package com.mvcoder.edutestdemo.beans;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Index;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class Friend {

    //个人表，是谁的联系人表
    @Index
    private long userId;

    @Id
    private long friendId; //可能为空，平台的id不一致
    private String friendName;
    private String nickname;
    private String avatar;
    @Generated(hash = 1981922013)
    public Friend(long userId, long friendId, String friendName, String nickname,
            String avatar) {
        this.userId = userId;
        this.friendId = friendId;
        this.friendName = friendName;
        this.nickname = nickname;
        this.avatar = avatar;
    }
    @Generated(hash = 287143722)
    public Friend() {
    }
    public long getUserId() {
        return this.userId;
    }
    public void setUserId(long userId) {
        this.userId = userId;
    }
    public long getFriendId() {
        return this.friendId;
    }
    public void setFriendId(long friendId) {
        this.friendId = friendId;
    }
    public String getFriendName() {
        return this.friendName;
    }
    public void setFriendName(String friendName) {
        this.friendName = friendName;
    }
    public String getNickname() {
        return this.nickname;
    }
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
    public String getAvatar() {
        return this.avatar;
    }
    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }


}
