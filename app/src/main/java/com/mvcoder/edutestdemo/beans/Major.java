package com.mvcoder.edutestdemo.beans;

import com.mvcoder.edutestdemo.greendao.DaoSession;
import com.mvcoder.edutestdemo.greendao.DepartmentDao;
import com.mvcoder.edutestdemo.greendao.MajorDao;
import com.mvcoder.edutestdemo.greendao.SchoolClassDao;

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
 * 专业信息
 */
@Entity
public class Major {

    @Id
    private long majorId;
    private String majorName;

    @Transient
    private College college;

    @Index
    private long departmentId;

    @ToOne(joinProperty = "departmentId")
    private Department department;

    //专业下面的年级
    @ToMany(referencedJoinProperty = "majorId")
    private List<SchoolClass> schoolClassList;
    //学年
    private int studyYears;

    @Index
    private long lastModified;

    @Transient
    private int state;

    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;
    /** Used for active entity operations. */
    @Generated(hash = 536121865)
    private transient MajorDao myDao;
    @Generated(hash = 573100834)
    public Major(long majorId, String majorName, long departmentId, int studyYears, long lastModified) {
        this.majorId = majorId;
        this.majorName = majorName;
        this.departmentId = departmentId;
        this.studyYears = studyYears;
        this.lastModified = lastModified;
    }
    @Generated(hash = 818744244)
    public Major() {
    }
    public long getMajorId() {
        return this.majorId;
    }
    public void setMajorId(long majorId) {
        this.majorId = majorId;
    }
    public String getMajorName() {
        return this.majorName;
    }
    public void setMajorName(String majorName) {
        this.majorName = majorName;
    }
    public long getDepartmentId() {
        return this.departmentId;
    }
    public void setDepartmentId(long departmentId) {
        this.departmentId = departmentId;
    }
    public int getStudyYears() {
        return this.studyYears;
    }
    public void setStudyYears(int studyYears) {
        this.studyYears = studyYears;
    }
    @Generated(hash = 340684718)
    private transient Long department__resolvedKey;
    /** To-one relationship, resolved on first access. */
    @Generated(hash = 768418633)
    public Department getDepartment() {
        long __key = this.departmentId;
        if (department__resolvedKey == null
                || !department__resolvedKey.equals(__key)) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            DepartmentDao targetDao = daoSession.getDepartmentDao();
            Department departmentNew = targetDao.load(__key);
            synchronized (this) {
                department = departmentNew;
                department__resolvedKey = __key;
            }
        }
        return department;
    }
    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 589438205)
    public void setDepartment(@NotNull Department department) {
        if (department == null) {
            throw new DaoException(
                    "To-one property 'departmentId' has not-null constraint; cannot set to-one to null");
        }
        synchronized (this) {
            this.department = department;
            departmentId = department.getDepartmentId();
            department__resolvedKey = departmentId;
        }
    }
    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 1661932958)
    public List<SchoolClass> getSchoolClassList() {
        if (schoolClassList == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            SchoolClassDao targetDao = daoSession.getSchoolClassDao();
            List<SchoolClass> schoolClassListNew = targetDao
                    ._queryMajor_SchoolClassList(majorId);
            synchronized (this) {
                if (schoolClassList == null) {
                    schoolClassList = schoolClassListNew;
                }
            }
        }
        return schoolClassList;
    }
    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    @Generated(hash = 1021386605)
    public synchronized void resetSchoolClassList() {
        schoolClassList = null;
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
    @Generated(hash = 2065352517)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getMajorDao() : null;
    }

    public void setSchoolClassList(List<SchoolClass> schoolClassList) {
        this.schoolClassList = schoolClassList;
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
