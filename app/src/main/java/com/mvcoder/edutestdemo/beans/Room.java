package com.mvcoder.edutestdemo.beans;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.ToOne;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.DaoException;
import com.mvcoder.edutestdemo.greendao.DaoSession;
import com.mvcoder.edutestdemo.greendao.FloorDao;
import com.mvcoder.edutestdemo.greendao.RoomDao;

@Entity
public class Room {

    @Id
    private long roomId;

    private long floorId;

    @ToOne(joinProperty = "floorId")
    private Floor floor;

    @NotNull
    private String roomName;
    //教室类型
    private int type;
    private String extra;

    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;

    /** Used for active entity operations. */
    @Generated(hash = 740313876)
    private transient RoomDao myDao;
    @Generated(hash = 1065268702)
    public Room(long roomId, long floorId, @NotNull String roomName, int type,
            String extra) {
        this.roomId = roomId;
        this.floorId = floorId;
        this.roomName = roomName;
        this.type = type;
        this.extra = extra;
    }
    @Generated(hash = 703125385)
    public Room() {
    }
    public long getRoomId() {
        return this.roomId;
    }
    public void setRoomId(long roomId) {
        this.roomId = roomId;
    }
    public long getFloorId() {
        return this.floorId;
    }
    public void setFloorId(long floorId) {
        this.floorId = floorId;
    }
    public String getRoomName() {
        return this.roomName;
    }
    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }
    public int getType() {
        return this.type;
    }
    public void setType(int type) {
        this.type = type;
    }
    public String getExtra() {
        return this.extra;
    }
    public void setExtra(String extra) {
        this.extra = extra;
    }
    @Generated(hash = 665446363)
    private transient Long floor__resolvedKey;
    /** To-one relationship, resolved on first access. */
    @Generated(hash = 843513633)
    public Floor getFloor() {
        long __key = this.floorId;
        if (floor__resolvedKey == null || !floor__resolvedKey.equals(__key)) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            FloorDao targetDao = daoSession.getFloorDao();
            Floor floorNew = targetDao.load(__key);
            synchronized (this) {
                floor = floorNew;
                floor__resolvedKey = __key;
            }
        }
        return floor;
    }
    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 1121189555)
    public void setFloor(@NotNull Floor floor) {
        if (floor == null) {
            throw new DaoException(
                    "To-one property 'floorId' has not-null constraint; cannot set to-one to null");
        }
        synchronized (this) {
            this.floor = floor;
            floorId = floor.getFloorId();
            floor__resolvedKey = floorId;
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
    @Generated(hash = 1185512297)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getRoomDao() : null;
    }
}
