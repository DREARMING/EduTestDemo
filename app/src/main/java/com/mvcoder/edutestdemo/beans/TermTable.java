package com.mvcoder.edutestdemo.beans;

import com.mvcoder.edutestdemo.greendao.DaoSession;
import com.mvcoder.edutestdemo.greendao.TermTableDao;
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

@Entity
public class TermTable {
    //学期id
    @Id
    private long termId;
    //学期名字
    private String termName;

    //学期起始日期
    private Date startDate;

    //学期天数
    private int days;

    private int weekNums;

    //学期结束日期
    private Date endDate;

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
    @Generated(hash = 1793089222)
    private transient TermTableDao myDao;

    @Generated(hash = 582340178)
    public TermTable(long termId, String termName, Date startDate, int days, int weekNums, Date endDate,
            long timeTableId, long lastModified) {
        this.termId = termId;
        this.termName = termName;
        this.startDate = startDate;
        this.days = days;
        this.weekNums = weekNums;
        this.endDate = endDate;
        this.timeTableId = timeTableId;
        this.lastModified = lastModified;
    }

    @Generated(hash = 2142321081)
    public TermTable() {
    }

    public long getTermId() {
        return this.termId;
    }

    public void setTermId(long termId) {
        this.termId = termId;
    }

    public String getTermName() {
        return this.termName;
    }

    public void setTermName(String termName) {
        this.termName = termName;
    }

    public Date getStartDate() {
        return this.startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public int getDays() {
        return this.days;
    }

    public void setDays(int days) {
        this.days = days;
    }

    public int getWeekNums() {
        return this.weekNums;
    }

    public void setWeekNums(int weekNums) {
        this.weekNums = weekNums;
    }

    public Date getEndDate() {
        return this.endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
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
    @Generated(hash = 109238203)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getTermTableDao() : null;
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
