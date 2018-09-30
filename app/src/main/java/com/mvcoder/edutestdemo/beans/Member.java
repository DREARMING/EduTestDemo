package com.mvcoder.edutestdemo.beans;

import com.mvcoder.edutestdemo.greendao.DaoSession;
import com.mvcoder.edutestdemo.greendao.MemberDao;
import com.mvcoder.edutestdemo.greendao.UserGroupDao;

import org.greenrobot.greendao.DaoException;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Index;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.ToOne;

/**
 * Created by mvcoder on 2017/10/30.
 */
@Entity
public class Member{

    //个人表
    @Index
    private long userId;

    @Id
    private long memberId;

    @Index
    private long groupId;
    private String username;
    private String nickname;
    private String avatar;
    private boolean isMaster;

    @ToOne(joinProperty = "groupId")
    private UserGroup group;

    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;

    /** Used for active entity operations. */
    @Generated(hash = 1200613910)
    private transient MemberDao myDao;

    @Generated(hash = 2027528059)
    public Member(long userId, long memberId, long groupId, String username,
            String nickname, String avatar, boolean isMaster) {
        this.userId = userId;
        this.memberId = memberId;
        this.groupId = groupId;
        this.username = username;
        this.nickname = nickname;
        this.avatar = avatar;
        this.isMaster = isMaster;
    }

    @Generated(hash = 367284327)
    public Member() {
    }

    public long getUserId() {
        return this.userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getMemberId() {
        return this.memberId;
    }

    public void setMemberId(long memberId) {
        this.memberId = memberId;
    }

    public long getGroupId() {
        return this.groupId;
    }

    public void setGroupId(long groupId) {
        this.groupId = groupId;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public boolean getIsMaster() {
        return this.isMaster;
    }

    public void setIsMaster(boolean isMaster) {
        this.isMaster = isMaster;
    }

    @Generated(hash = 201187923)
    private transient Long group__resolvedKey;

    /** To-one relationship, resolved on first access. */
    @Generated(hash = 2102299090)
    public UserGroup getGroup() {
        long __key = this.groupId;
        if (group__resolvedKey == null || !group__resolvedKey.equals(__key)) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            UserGroupDao targetDao = daoSession.getUserGroupDao();
            UserGroup groupNew = targetDao.load(__key);
            synchronized (this) {
                group = groupNew;
                group__resolvedKey = __key;
            }
        }
        return group;
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 356587348)
    public void setGroup(@NotNull UserGroup group) {
        if (group == null) {
            throw new DaoException(
                    "To-one property 'groupId' has not-null constraint; cannot set to-one to null");
        }
        synchronized (this) {
            this.group = group;
            groupId = group.getGroupId();
            group__resolvedKey = groupId;
        }
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#delete(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 128553479)
    public void delete() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.delete(this);
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#refresh(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 1942392019)
    public void refresh() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.refresh(this);
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#update(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 713229351)
    public void update() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.update(this);
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 1742104579)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getMemberDao() : null;
    }

}
