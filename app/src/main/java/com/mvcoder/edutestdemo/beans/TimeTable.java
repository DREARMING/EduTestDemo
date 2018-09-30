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
import org.greenrobot.greendao.annotation.ToMany;
import org.greenrobot.greendao.annotation.Transient;

import java.util.List;

/**
 * 作息表，描述某学期的上课时间安排、包括学期的始末、周末
 */
@Entity
public class TimeTable {

    @Id
    private long timeTableId;
    private String tableName;

    private int lessionNums;

    //每一节课的时间安排
    @ToMany(referencedJoinProperty = "timeTableId")
    private List<LessionTimeInfo> lessionTimeList;

    @Index
    private long lastModified;

    @Transient
    private int state;

    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;
    /** Used for active entity operations. */
    @Generated(hash = 174307367)
    private transient TimeTableDao myDao;

    @Generated(hash = 2126108818)
    public TimeTable(long timeTableId, String tableName, int lessionNums, long lastModified) {
        this.timeTableId = timeTableId;
        this.tableName = tableName;
        this.lessionNums = lessionNums;
        this.lastModified = lastModified;
    }

    @Generated(hash = 388123705)
    public TimeTable() {
    }

    public long getTimeTableId() {
        return this.timeTableId;
    }

    public void setTimeTableId(long timeTableId) {
        this.timeTableId = timeTableId;
    }

    public String getTableName() {
        return this.tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public int getLessionNums() {
        return this.lessionNums;
    }

    public void setLessionNums(int lessionNums) {
        this.lessionNums = lessionNums;
    }

    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 1154944349)
    public List<LessionTimeInfo> getLessionTimeList() {
        if (lessionTimeList == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            LessionTimeInfoDao targetDao = daoSession.getLessionTimeInfoDao();
            List<LessionTimeInfo> lessionTimeListNew = targetDao
                    ._queryTimeTable_LessionTimeList(timeTableId);
            synchronized (this) {
                if (lessionTimeList == null) {
                    lessionTimeList = lessionTimeListNew;
                }
            }
        }
        return lessionTimeList;
    }

    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    @Generated(hash = 1922450740)
    public synchronized void resetLessionTimeList() {
        lessionTimeList = null;
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
    @Generated(hash = 1601800267)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getTimeTableDao() : null;
    }

    @Keep
    public void setLessionTimeList(List<LessionTimeInfo> lessionTimeList) {
        this.lessionTimeList = lessionTimeList;
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
