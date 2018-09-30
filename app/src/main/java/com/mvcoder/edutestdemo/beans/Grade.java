package com.mvcoder.edutestdemo.beans;

import com.mvcoder.edutestdemo.greendao.DaoSession;
import com.mvcoder.edutestdemo.greendao.GradeDao;
import com.mvcoder.edutestdemo.greendao.SchoolClassDao;

import org.greenrobot.greendao.DaoException;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Index;
import org.greenrobot.greendao.annotation.Keep;
import org.greenrobot.greendao.annotation.ToMany;
import org.greenrobot.greendao.annotation.Transient;

import java.util.Date;
import java.util.List;

/**
 * 年级信息
 */
@Entity
public class Grade {
    @Id
    private long gradeId;
    private String gradeName;
    //开学时间
    private Date enrolDate;

    //班级信息
    @ToMany(referencedJoinProperty = "gradeId")
    private List<SchoolClass> classList;

    @Index
    private long lastModified;

    @Transient
    private int state;

    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;
    /** Used for active entity operations. */
    @Generated(hash = 681281562)
    private transient GradeDao myDao;

    @Generated(hash = 1077974083)
    public Grade(long gradeId, String gradeName, Date enrolDate, long lastModified) {
        this.gradeId = gradeId;
        this.gradeName = gradeName;
        this.enrolDate = enrolDate;
        this.lastModified = lastModified;
    }

    @Generated(hash = 2042976393)
    public Grade() {
    }

    public void setClassList(List<SchoolClass> classList) {
        this.classList = classList;
    }

    public long getGradeId() {
        return this.gradeId;
    }

    public void setGradeId(long gradeId) {
        this.gradeId = gradeId;
    }

    public String getGradeName() {
        return this.gradeName;
    }

    public void setGradeName(String gradeName) {
        this.gradeName = gradeName;
    }

    public Date getEnrolDate() {
        return this.enrolDate;
    }

    public void setEnrolDate(Date enrolDate) {
        this.enrolDate = enrolDate;
    }

    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 1716369730)
    public List<SchoolClass> getClassList() {
        if (classList == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            SchoolClassDao targetDao = daoSession.getSchoolClassDao();
            List<SchoolClass> classListNew = targetDao
                    ._queryGrade_ClassList(gradeId);
            synchronized (this) {
                if (classList == null) {
                    classList = classListNew;
                }
            }
        }
        return classList;
    }

    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    @Generated(hash = 1102699023)
    public synchronized void resetClassList() {
        classList = null;
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
    @Generated(hash = 1187286414)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getGradeDao() : null;
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
