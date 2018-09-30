package com.mvcoder.edutestdemo.beans;

import com.mvcoder.edutestdemo.greendao.CollegeDao;
import com.mvcoder.edutestdemo.greendao.DaoSession;
import com.mvcoder.edutestdemo.greendao.DepartmentDao;

import org.greenrobot.greendao.DaoException;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Index;
import org.greenrobot.greendao.annotation.Keep;
import org.greenrobot.greendao.annotation.ToMany;
import org.greenrobot.greendao.annotation.Transient;

import java.util.List;

@Entity
public class College {

    @Id
    private long collegeId;
    private String collegeName;

    //学院下面的专业，有可能空，暂时以下面哥参数为准，即以系为准
    @Transient
    private List<Major> majorList;

    @ToMany(referencedJoinProperty = "collegeId")
    private List<Department> departmentList;

    @Index
    private long lastModified;

    @Transient
    private int state;

    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;
    /** Used for active entity operations. */
    @Generated(hash = 875272731)
    private transient CollegeDao myDao;

    @Generated(hash = 883529664)
    public College(long collegeId, String collegeName, long lastModified) {
        this.collegeId = collegeId;
        this.collegeName = collegeName;
        this.lastModified = lastModified;
    }

    @Generated(hash = 1594492655)
    public College() {
    }

    public long getCollegeId() {
        return this.collegeId;
    }

    public void setCollegeId(long collegeId) {
        this.collegeId = collegeId;
    }

    public String getCollegeName() {
        return this.collegeName;
    }

    public void setCollegeName(String collegeName) {
        this.collegeName = collegeName;
    }

    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 1937415089)
    public List<Department> getDepartmentList() {
        if (departmentList == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            DepartmentDao targetDao = daoSession.getDepartmentDao();
            List<Department> departmentListNew = targetDao
                    ._queryCollege_DepartmentList(collegeId);
            synchronized (this) {
                if (departmentList == null) {
                    departmentList = departmentListNew;
                }
            }
        }
        return departmentList;
    }

    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    @Generated(hash = 195971045)
    public synchronized void resetDepartmentList() {
        departmentList = null;
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
    @Generated(hash = 265041045)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getCollegeDao() : null;
    }

    @Keep
    public void setDepartmentList(List<Department> departmentList) {
        this.departmentList = departmentList;
    }

    @Keep
    public int getState() {
        return state;
    }

    @Keep
    public void setState(int state) {
        this.state = state;
    }

    public long getLastModified() {
        return this.lastModified;
    }

    public void setLastModified(long lastModified) {
        this.lastModified = lastModified;
    }
}
