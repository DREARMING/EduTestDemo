package com.mvcoder.edutestdemo.beans;

import com.mvcoder.edutestdemo.greendao.DaoSession;
import com.mvcoder.edutestdemo.greendao.GradeDao;
import com.mvcoder.edutestdemo.greendao.MajorDao;
import com.mvcoder.edutestdemo.greendao.SchoolClassDao;

import org.greenrobot.greendao.DaoException;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Index;
import org.greenrobot.greendao.annotation.Keep;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.ToOne;
import org.greenrobot.greendao.annotation.Transient;

/**
 * 班级信息
 */
@Entity
public class SchoolClass {
    @Id
    private long classId;
    private String className;
    private int stuNum;

    @Index
    private long gradeId;
    @ToOne(joinProperty = "gradeId")
    private Grade grade;

    @Index
    private long majorId;

    @ToOne(joinProperty = "majorId")
    private Major major;

    @Index
    private long lastModified;

    @Transient
    private int state;

    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;
    /** Used for active entity operations. */
    @Generated(hash = 2010654448)
    private transient SchoolClassDao myDao;

    @Generated(hash = 188507744)
    public SchoolClass(long classId, String className, int stuNum, long gradeId, long majorId,
            long lastModified) {
        this.classId = classId;
        this.className = className;
        this.stuNum = stuNum;
        this.gradeId = gradeId;
        this.majorId = majorId;
        this.lastModified = lastModified;
    }

    @Generated(hash = 974720362)
    public SchoolClass() {
    }

    public long getClassId() {
        return this.classId;
    }

    public void setClassId(long classId) {
        this.classId = classId;
    }

    public String getClassName() {
        return this.className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public int getStuNum() {
        return this.stuNum;
    }

    public void setStuNum(int stuNum) {
        this.stuNum = stuNum;
    }

    public long getGradeId() {
        return this.gradeId;
    }

    public void setGradeId(long gradeId) {
        this.gradeId = gradeId;
    }

    public long getMajorId() {
        return this.majorId;
    }

    public void setMajorId(long majorId) {
        this.majorId = majorId;
    }

    @Generated(hash = 74796936)
    private transient Long grade__resolvedKey;

    /** To-one relationship, resolved on first access. */
    @Generated(hash = 951518568)
    public Grade getGrade() {
        long __key = this.gradeId;
        if (grade__resolvedKey == null || !grade__resolvedKey.equals(__key)) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            GradeDao targetDao = daoSession.getGradeDao();
            Grade gradeNew = targetDao.load(__key);
            synchronized (this) {
                grade = gradeNew;
                grade__resolvedKey = __key;
            }
        }
        return grade;
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 1048855833)
    public void setGrade(@NotNull Grade grade) {
        if (grade == null) {
            throw new DaoException(
                    "To-one property 'gradeId' has not-null constraint; cannot set to-one to null");
        }
        synchronized (this) {
            this.grade = grade;
            gradeId = grade.getGradeId();
            grade__resolvedKey = gradeId;
        }
    }

    @Generated(hash = 222280288)
    private transient Long major__resolvedKey;

    /** To-one relationship, resolved on first access. */
    @Generated(hash = 1294626647)
    public Major getMajor() {
        long __key = this.majorId;
        if (major__resolvedKey == null || !major__resolvedKey.equals(__key)) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            MajorDao targetDao = daoSession.getMajorDao();
            Major majorNew = targetDao.load(__key);
            synchronized (this) {
                major = majorNew;
                major__resolvedKey = __key;
            }
        }
        return major;
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 968315507)
    public void setMajor(@NotNull Major major) {
        if (major == null) {
            throw new DaoException(
                    "To-one property 'majorId' has not-null constraint; cannot set to-one to null");
        }
        synchronized (this) {
            this.major = major;
            majorId = major.getMajorId();
            major__resolvedKey = majorId;
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
    @Generated(hash = 1447365948)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getSchoolClassDao() : null;
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
