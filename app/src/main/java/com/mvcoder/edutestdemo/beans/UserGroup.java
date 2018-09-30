package com.mvcoder.edutestdemo.beans;

import com.mvcoder.edutestdemo.greendao.DaoSession;
import com.mvcoder.edutestdemo.greendao.MemberDao;
import com.mvcoder.edutestdemo.greendao.UserGroupDao;

import org.greenrobot.greendao.DaoException;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Index;
import org.greenrobot.greendao.annotation.ToMany;

import java.util.List;

/**
 * Created by mvcoder on 2017/10/30.
 */
@Entity
public class UserGroup{

    //个人表
    @Index
    private int userId;
    @Id
    private long groupId;
    private String groupName;
    private int groupNum;
    private boolean isMaster;
    private String avatar;

    @ToMany(referencedJoinProperty = "groupId")
    private List<Member> memberList;
    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;
    /** Used for active entity operations. */
    @Generated(hash = 41919848)
    private transient UserGroupDao myDao;

    @Generated(hash = 2120074517)
    public UserGroup(int userId, long groupId, String groupName, int groupNum,
            boolean isMaster, String avatar) {
        this.userId = userId;
        this.groupId = groupId;
        this.groupName = groupName;
        this.groupNum = groupNum;
        this.isMaster = isMaster;
        this.avatar = avatar;
    }

    @Generated(hash = 322975169)
    public UserGroup() {
    }

    public int getUserId() {
        return this.userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public long getGroupId() {
        return this.groupId;
    }

    public void setGroupId(long groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return this.groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public int getGroupNum() {
        return this.groupNum;
    }

    public void setGroupNum(int groupNum) {
        this.groupNum = groupNum;
    }

    public boolean getIsMaster() {
        return this.isMaster;
    }

    public void setIsMaster(boolean isMaster) {
        this.isMaster = isMaster;
    }

    public String getAvatar() {
        return this.avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 1368967065)
    public List<Member> getMemberList() {
        if (memberList == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            MemberDao targetDao = daoSession.getMemberDao();
            List<Member> memberListNew = targetDao
                    ._queryUserGroup_MemberList(groupId);
            synchronized (this) {
                if (memberList == null) {
                    memberList = memberListNew;
                }
            }
        }
        return memberList;
    }

    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    @Generated(hash = 1794216443)
    public synchronized void resetMemberList() {
        memberList = null;
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
    @Generated(hash = 1010668111)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getUserGroupDao() : null;
    }

}
