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
    private long fr_id;
    private String fr_frusername;
    private String fr_remark;
    private long fr_fgid;
    @Generated(hash = 666880776)
    public Friend(long userId, long fr_id, String fr_frusername, String fr_remark,
            long fr_fgid) {
        this.userId = userId;
        this.fr_id = fr_id;
        this.fr_frusername = fr_frusername;
        this.fr_remark = fr_remark;
        this.fr_fgid = fr_fgid;
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
    public long getFr_id() {
        return this.fr_id;
    }
    public void setFr_id(long fr_id) {
        this.fr_id = fr_id;
    }
    public String getFr_frusername() {
        return this.fr_frusername;
    }
    public void setFr_frusername(String fr_frusername) {
        this.fr_frusername = fr_frusername;
    }
    public String getFr_remark() {
        return this.fr_remark;
    }
    public void setFr_remark(String fr_remark) {
        this.fr_remark = fr_remark;
    }
    public long getFr_fgid() {
        return this.fr_fgid;
    }
    public void setFr_fgid(long fr_fgid) {
        this.fr_fgid = fr_fgid;
    }



}
