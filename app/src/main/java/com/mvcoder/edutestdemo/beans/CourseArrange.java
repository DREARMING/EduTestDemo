package com.mvcoder.edutestdemo.beans;

import com.mvcoder.edutestdemo.greendao.CourseArrangeDao;
import com.mvcoder.edutestdemo.greendao.CourseDao;
import com.mvcoder.edutestdemo.greendao.DaoSession;
import com.mvcoder.edutestdemo.greendao.LessionTimeInfoDao;
import com.mvcoder.edutestdemo.greendao.RoomDao;
import com.mvcoder.edutestdemo.greendao.SchoolClassDao;
import com.mvcoder.edutestdemo.greendao.TermTableDao;
import com.mvcoder.edutestdemo.greendao.UserDao;

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
 * 课程信息安排
 */
/*个人选修和通识课的查表问题，尚未解决*/
@Entity(
        indexes = {
                @Index("lessionInfoId"),@Index("termId"),
                @Index("day"),@Index("roomId"),
                @Index("courseId"),@Index("schoolClassId"),
                @Index("teacherId")
        }
)
public class CourseArrange {

    @Id
    private long arrageId;
    //课节信息id
    private long lessionInfoId;
    //学期id
    private long termId;
    //周几
    private int day;
    //课室id
    private long roomId;
    //科目id
    private long courseId;
    //开始周
    private int startWeek;
    //结束周
    private int endWeek;
    //任课老师id
    private long teacherId;
    //班级id
    private long schoolClassId;

    @Index
    private long lastModified;

    @Transient
    private int state;

    @ToOne(joinProperty = "lessionInfoId")
    private LessionTimeInfo lessionTimeInfo;

    @ToOne(joinProperty = "roomId")
    private Room room;

    @ToOne(joinProperty = "courseId")
    private Course course;

    @ToOne(joinProperty = "schoolClassId")
    private SchoolClass schoolClass;

    @ToOne(joinProperty = "termId")
    private TermTable termTable;

    @ToOne(joinProperty = "teacherId")
    private User teacher;


/** Used to resolve relations */
@Generated(hash = 2040040024)
private transient DaoSession daoSession;
/** Used for active entity operations. */
@Generated(hash = 1653280118)
private transient CourseArrangeDao myDao;

@Generated(hash = 269680972)
public CourseArrange(long arrageId, long lessionInfoId, long termId, int day, long roomId,
        long courseId, int startWeek, int endWeek, long teacherId, long schoolClassId,
        long lastModified) {
    this.arrageId = arrageId;
    this.lessionInfoId = lessionInfoId;
    this.termId = termId;
    this.day = day;
    this.roomId = roomId;
    this.courseId = courseId;
    this.startWeek = startWeek;
    this.endWeek = endWeek;
    this.teacherId = teacherId;
    this.schoolClassId = schoolClassId;
    this.lastModified = lastModified;
}

@Generated(hash = 1764964794)
public CourseArrange() {
}

public long getArrageId() {
    return this.arrageId;
}

public void setArrageId(long arrageId) {
    this.arrageId = arrageId;
}

public long getLessionInfoId() {
    return this.lessionInfoId;
}

public void setLessionInfoId(long lessionInfoId) {
    this.lessionInfoId = lessionInfoId;
}

public long getTermId() {
    return this.termId;
}

public void setTermId(long termId) {
    this.termId = termId;
}

public int getDay() {
    return this.day;
}

public void setDay(int day) {
    this.day = day;
}

public long getRoomId() {
    return this.roomId;
}

public void setRoomId(long roomId) {
    this.roomId = roomId;
}

public long getCourseId() {
    return this.courseId;
}

public void setCourseId(long courseId) {
    this.courseId = courseId;
}

public int getStartWeek() {
    return this.startWeek;
}

public void setStartWeek(int startWeek) {
    this.startWeek = startWeek;
}

public int getEndWeek() {
    return this.endWeek;
}

public void setEndWeek(int endWeek) {
    this.endWeek = endWeek;
}

public long getTeacherId() {
    return this.teacherId;
}

public void setTeacherId(long teacherId) {
    this.teacherId = teacherId;
}

public long getSchoolClassId() {
    return this.schoolClassId;
}

public void setSchoolClassId(long schoolClassId) {
    this.schoolClassId = schoolClassId;
}

@Generated(hash = 1100434224)
private transient Long lessionTimeInfo__resolvedKey;

/** To-one relationship, resolved on first access. */
@Generated(hash = 252884143)
public LessionTimeInfo getLessionTimeInfo() {
    long __key = this.lessionInfoId;
    if (lessionTimeInfo__resolvedKey == null
            || !lessionTimeInfo__resolvedKey.equals(__key)) {
        final DaoSession daoSession = this.daoSession;
        if (daoSession == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        LessionTimeInfoDao targetDao = daoSession.getLessionTimeInfoDao();
        LessionTimeInfo lessionTimeInfoNew = targetDao.load(__key);
        synchronized (this) {
            lessionTimeInfo = lessionTimeInfoNew;
            lessionTimeInfo__resolvedKey = __key;
        }
    }
    return lessionTimeInfo;
}

/** called by internal mechanisms, do not call yourself. */
@Generated(hash = 1727070658)
public void setLessionTimeInfo(@NotNull LessionTimeInfo lessionTimeInfo) {
    if (lessionTimeInfo == null) {
        throw new DaoException(
                "To-one property 'lessionInfoId' has not-null constraint; cannot set to-one to null");
    }
    synchronized (this) {
        this.lessionTimeInfo = lessionTimeInfo;
        lessionInfoId = lessionTimeInfo.getLessionId();
        lessionTimeInfo__resolvedKey = lessionInfoId;
    }
}

@Generated(hash = 170076450)
private transient Long room__resolvedKey;

/** To-one relationship, resolved on first access. */
@Generated(hash = 960690775)
public Room getRoom() {
    long __key = this.roomId;
    if (room__resolvedKey == null || !room__resolvedKey.equals(__key)) {
        final DaoSession daoSession = this.daoSession;
        if (daoSession == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        RoomDao targetDao = daoSession.getRoomDao();
        Room roomNew = targetDao.load(__key);
        synchronized (this) {
            room = roomNew;
            room__resolvedKey = __key;
        }
    }
    return room;
}

/** called by internal mechanisms, do not call yourself. */
@Generated(hash = 1839969206)
public void setRoom(@NotNull Room room) {
    if (room == null) {
        throw new DaoException(
                "To-one property 'roomId' has not-null constraint; cannot set to-one to null");
    }
    synchronized (this) {
        this.room = room;
        roomId = room.getRoomId();
        room__resolvedKey = roomId;
    }
}

@Generated(hash = 13676306)
private transient Long course__resolvedKey;

/** To-one relationship, resolved on first access. */
@Generated(hash = 1785301675)
public Course getCourse() {
    long __key = this.courseId;
    if (course__resolvedKey == null || !course__resolvedKey.equals(__key)) {
        final DaoSession daoSession = this.daoSession;
        if (daoSession == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        CourseDao targetDao = daoSession.getCourseDao();
        Course courseNew = targetDao.load(__key);
        synchronized (this) {
            course = courseNew;
            course__resolvedKey = __key;
        }
    }
    return course;
}

/** called by internal mechanisms, do not call yourself. */
@Generated(hash = 2131312831)
public void setCourse(@NotNull Course course) {
    if (course == null) {
        throw new DaoException(
                "To-one property 'courseId' has not-null constraint; cannot set to-one to null");
    }
    synchronized (this) {
        this.course = course;
        courseId = course.getCourseId();
        course__resolvedKey = courseId;
    }
}

@Generated(hash = 1834750234)
private transient Long schoolClass__resolvedKey;

/** To-one relationship, resolved on first access. */
@Generated(hash = 306502768)
public SchoolClass getSchoolClass() {
    long __key = this.schoolClassId;
    if (schoolClass__resolvedKey == null
            || !schoolClass__resolvedKey.equals(__key)) {
        final DaoSession daoSession = this.daoSession;
        if (daoSession == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        SchoolClassDao targetDao = daoSession.getSchoolClassDao();
        SchoolClass schoolClassNew = targetDao.load(__key);
        synchronized (this) {
            schoolClass = schoolClassNew;
            schoolClass__resolvedKey = __key;
        }
    }
    return schoolClass;
}

/** called by internal mechanisms, do not call yourself. */
@Generated(hash = 1344981571)
public void setSchoolClass(@NotNull SchoolClass schoolClass) {
    if (schoolClass == null) {
        throw new DaoException(
                "To-one property 'schoolClassId' has not-null constraint; cannot set to-one to null");
    }
    synchronized (this) {
        this.schoolClass = schoolClass;
        schoolClassId = schoolClass.getClassId();
        schoolClass__resolvedKey = schoolClassId;
    }
}

@Generated(hash = 368132914)
private transient Long termTable__resolvedKey;

/** To-one relationship, resolved on first access. */
@Generated(hash = 595572882)
public TermTable getTermTable() {
    long __key = this.termId;
    if (termTable__resolvedKey == null
            || !termTable__resolvedKey.equals(__key)) {
        final DaoSession daoSession = this.daoSession;
        if (daoSession == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        TermTableDao targetDao = daoSession.getTermTableDao();
        TermTable termTableNew = targetDao.load(__key);
        synchronized (this) {
            termTable = termTableNew;
            termTable__resolvedKey = __key;
        }
    }
    return termTable;
}

/** called by internal mechanisms, do not call yourself. */
@Generated(hash = 1578338365)
public void setTermTable(@NotNull TermTable termTable) {
    if (termTable == null) {
        throw new DaoException(
                "To-one property 'termId' has not-null constraint; cannot set to-one to null");
    }
    synchronized (this) {
        this.termTable = termTable;
        termId = termTable.getTermId();
        termTable__resolvedKey = termId;
    }
}

@Generated(hash = 155140967)
private transient Long teacher__resolvedKey;

/** To-one relationship, resolved on first access. */
@Generated(hash = 1001475539)
public User getTeacher() {
    long __key = this.teacherId;
    if (teacher__resolvedKey == null || !teacher__resolvedKey.equals(__key)) {
        final DaoSession daoSession = this.daoSession;
        if (daoSession == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        UserDao targetDao = daoSession.getUserDao();
        User teacherNew = targetDao.load(__key);
        synchronized (this) {
            teacher = teacherNew;
            teacher__resolvedKey = __key;
        }
    }
    return teacher;
}

/** called by internal mechanisms, do not call yourself. */
@Generated(hash = 381318198)
public void setTeacher(@NotNull User teacher) {
    if (teacher == null) {
        throw new DaoException(
                "To-one property 'teacherId' has not-null constraint; cannot set to-one to null");
    }
    synchronized (this) {
        this.teacher = teacher;
        teacherId = teacher.getUserId();
        teacher__resolvedKey = teacherId;
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
@Generated(hash = 1162875500)
public void __setDaoSession(DaoSession daoSession) {
    this.daoSession = daoSession;
    myDao = daoSession != null ? daoSession.getCourseArrangeDao() : null;
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
