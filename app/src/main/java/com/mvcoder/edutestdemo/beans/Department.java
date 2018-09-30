package com.mvcoder.edutestdemo.beans;

import com.mvcoder.edutestdemo.greendao.CollegeDao;
import com.mvcoder.edutestdemo.greendao.DaoSession;
import com.mvcoder.edutestdemo.greendao.DepartmentDao;
import com.mvcoder.edutestdemo.greendao.MajorDao;

import org.greenrobot.greendao.DaoException;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Index;
import org.greenrobot.greendao.annotation.Keep;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.ToMany;
import org.greenrobot.greendao.annotation.ToOne;
import org.greenrobot.greendao.annotation.Transient;

import java.util.List;

/**
 * 系别信息
 */
@Entity
public class Department {

    @Id
    private long departmentId;
    private String departmentName;

    @Index
    private long collegeId;

    @ToOne(joinProperty = "collegeId")
    private College college;
    //系别下面的专业
    @ToMany(referencedJoinProperty = "departmentId")
    private List<Major> majorList;

    @Index
    private long lastModified;

    @Transient
    private int state;

    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;
    /** Used for active entity operations. */
    @Generated(hash = 550071597)
    private transient DepartmentDao myDao;
    @Generated(hash = 882022047)
    public Department(long departmentId, String departmentName, long collegeId, long lastModified) {
        this.departmentId = departmentId;
        this.departmentName = departmentName;
        this.collegeId = collegeId;
        this.lastModified = lastModified;
    }
    @Generated(hash = 355406289)
    public Department() {
    }
    public long getDepartmentId() {
        return this.departmentId;
    }
    public void setDepartmentId(long departmentId) {
        this.departmentId = departmentId;
    }
    public String getDepartmentName() {
        return this.departmentName;
    }
    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }
    public long getCollegeId() {
        return this.collegeId;
    }
    public void setCollegeId(long collegeId) {
        this.collegeId = collegeId;
    }
    @Generated(hash = 567349805)
    private transient Long college__resolvedKey;
    /** To-one relationship, resolved on first access. */
    @Generated(hash = 252525426)
    public College getCollege() {
        long __key = this.collegeId;
        if (college__resolvedKey == null || !college__resolvedKey.equals(__key)) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            CollegeDao targetDao = daoSession.getCollegeDao();
            College collegeNew = targetDao.load(__key);
            synchronized (this) {
                college = collegeNew;
                college__resolvedKey = __key;
            }
        }
        return college;
    }
    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 1289718799)
    public void setCollege(@NotNull College college) {
        if (college == null) {
            throw new DaoException(
                    "To-one property 'collegeId' has not-null constraint; cannot set to-one to null");
        }
        synchronized (this) {
            this.college = college;
            collegeId = college.getCollegeId();
            college__resolvedKey = collegeId;
        }
    }
    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 87471206)
    public List<Major> getMajorList() {
        if (majorList == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            MajorDao targetDao = daoSession.getMajorDao();
            List<Major> majorListNew = targetDao
                    ._queryDepartment_MajorList(departmentId);
            synchronized (this) {
                if (majorList == null) {
                    majorList = majorListNew;
                }
            }
        }
        return majorList;
    }
    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    @Generated(hash = 89519902)
    public synchronized void resetMajorList() {
        majorList = null;
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
    @Generated(hash = 393361921)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getDepartmentDao() : null;
    }

    @Keep
    public void setMajorList(List<Major> majorList) {
        this.majorList = majorList;
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
