package com.mvcoder.edutestdemo.beans;

import java.io.Serializable;
import java.util.List;

/**
 * Created by mvcoder on 2017/10/30.
 */

public class UserGroup implements Serializable {

    //个人表
    private int userId;

    private int groupId;
    private String groupName;
    private int groupNum;
    private boolean isMaster;
    private String avatar;
    private List<Member> memberList;

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public int getGroupNum() {
        return groupNum;
    }

    public void setGroupNum(int groupNum) {
        this.groupNum = groupNum;
    }

    public boolean isMaster() {
        return isMaster;
    }

    public void setMaster(boolean master) {
        isMaster = master;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public List<Member> getMemberList() {
        return memberList;
    }

    public void setMenberList(List<Member> memberList) {
        this.memberList = memberList;
    }
}
