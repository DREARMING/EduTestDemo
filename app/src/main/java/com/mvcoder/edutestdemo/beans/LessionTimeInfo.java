package com.mvcoder.edutestdemo.beans;

import com.mvcoder.edutestdemo.greendao.DaoSession;
import com.mvcoder.edutestdemo.greendao.LessionTimeInfoDao;
import com.mvcoder.edutestdemo.greendao.TimeTableDao;

import org.greenrobot.greendao.DaoException;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Index;
import org.greenrobot.greendao.annotation.Keep;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.ToOne;
import org.greenrobot.greendao.annotation.Transient;

import java.util.Date;

/**
 * 课节信息
 */
@Entity
public class LessionTimeInfo {

    @Id
    private long lessionId;
    //第几节/下标
    private int index;
    //课节名称，举例：第一节
    private String lessionName;
    //课节上课时间
    private Date startTime;
    //课节结束时间
    private Date endTime;

    @Index
    private long timeTableId;

    @ToOne(joinProperty = "timeTableId")
    private TimeTable timeTable;

    @Index
    private long lastModified;

    @Transient
    private int state;

    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;
    /** Used for active entity operations. */
    @Generated(hash = 360523897)
    private transient LessionTimeInfoDao myDao;

    @Generated(hash = 1408620597)
    public LessionTimeInfo(long lessionId, int index, String lessionName, Date startTime, Date endTime,
            long timeTableId, long lastModified) {
        this.lessionId = lessionId;
        this.index = index;
        this.lessionName = lessionName;
        this.startTime = startTime;
        this.endTime = endTime;
        this.timeTableId = timeTableId;
        this.lastModified = lastModified;
    }

    @Generated(hash = 1552669881)
    public LessionTimeInfo() {
    }

    public long getLessionId() {
        return this.lessionId;
    }

    public void setLessionId(long lessionId) {
        this.lessionId = lessionId;
    }

    public int getIndex() {
        return this.index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getLessionName() {
        return this.lessionName;
    }

    public void setLessionName(String lessionName) {
        this.lessionName = lessionName;
    }

    public Date getStartTime() {
        return this.startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return this.endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public long getTimeTableId() {
        return this.timeTableId;
    }

    public void setTimeTableId(long timeTableId) {
        this.timeTableId = timeTableId;
    }

    @Generated(hash = 259654209)
    private transient Long timeTable__resolvedKey;

    /** To-one relationship, resolved on first access. */
    @Generated(hash = 944737254)
    public TimeTable getTimeTable() {
        long __key = this.timeTableId;
        if (timeTable__resolvedKey == null
                || !timeTable__resolvedKey.equals(__key)) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            TimeTableDao targetDao = daoSession.getTimeTableDao();
            TimeTable timeTableNew = targetDao.load(__key);
            synchronized (this) {
                timeTable = timeTableNew;
                timeTable__resolvedKey = __key;
            }
        }
        return timeTable;
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 348520273)
    public void setTimeTable(@NotNull TimeTable timeTable) {
        if (timeTable == null) {
            throw new DaoException(
                    "To-one property 'timeTableId' has not-null constraint; cannot set to-one to null");
        }
        synchronized (this) {
            this.timeTable = timeTable;
            timeTableId = timeTable.getTimeTableId();
            timeTable__resolvedKey = timeTableId;
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
    @Generated(hash = 88564219)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getLessionTimeInfoDao() : null;
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

}
