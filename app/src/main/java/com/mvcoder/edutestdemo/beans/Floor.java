package com.mvcoder.edutestdemo.beans;

import com.mvcoder.edutestdemo.greendao.ClassBuildingDao;
import com.mvcoder.edutestdemo.greendao.DaoSession;
import com.mvcoder.edutestdemo.greendao.FloorDao;
import com.mvcoder.edutestdemo.greendao.RoomDao;

import org.greenrobot.greendao.DaoException;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.ToMany;
import org.greenrobot.greendao.annotation.ToOne;

import java.util.List;

@Entity
public class Floor {

    @Id
    private long floorId;
    @NotNull
    private String floorName;

    private long buildingId;
    @ToOne(joinProperty = "buildingId")
    private ClassBuilding building;

    //楼层的教室列表
    @ToMany(referencedJoinProperty = "floorId")
    private List<Room> roomList;
    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;
    /** Used for active entity operations. */
    @Generated(hash = 1434678494)
    private transient FloorDao myDao;



    @Generated(hash = 447118560)
    public Floor(long floorId, @NotNull String floorName, long buildingId) {
        this.floorId = floorId;
        this.floorName = floorName;
        this.buildingId = buildingId;
    }



    @Generated(hash = 451009308)
    public Floor() {
    }



    @Generated(hash = 747514706)
    private transient Long building__resolvedKey;



    /*
    *
    *
    * */

    public void setRoomList(List<Room> roomList) {
        this.roomList = roomList;
    }



    public long getFloorId() {
        return this.floorId;
    }



    public void setFloorId(long floorId) {
        this.floorId = floorId;
    }



    public String getFloorName() {
        return this.floorName;
    }



    public void setFloorName(String floorName) {
        this.floorName = floorName;
    }



    public long getBuildingId() {
        return this.buildingId;
    }



    public void setBuildingId(long buildingId) {
        this.buildingId = buildingId;
    }



    /** To-one relationship, resolved on first access. */
    @Generated(hash = 960740775)
    public ClassBuilding getBuilding() {
        long __key = this.buildingId;
        if (building__resolvedKey == null || !building__resolvedKey.equals(__key)) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            ClassBuildingDao targetDao = daoSession.getClassBuildingDao();
            ClassBuilding buildingNew = targetDao.load(__key);
            synchronized (this) {
                building = buildingNew;
                building__resolvedKey = __key;
            }
        }
        return building;
    }



    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 392888501)
    public void setBuilding(@NotNull ClassBuilding building) {
        if (building == null) {
            throw new DaoException(
                    "To-one property 'buildingId' has not-null constraint; cannot set to-one to null");
        }
        synchronized (this) {
            this.building = building;
            buildingId = building.getBuildingId();
            building__resolvedKey = buildingId;
        }
    }



    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 18058839)
    public List<Room> getRoomList() {
        if (roomList == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            RoomDao targetDao = daoSession.getRoomDao();
            List<Room> roomListNew = targetDao._queryFloor_RoomList(floorId);
            synchronized (this) {
                if (roomList == null) {
                    roomList = roomListNew;
                }
            }
        }
        return roomList;
    }



    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    @Generated(hash = 811059884)
    public synchronized void resetRoomList() {
        roomList = null;
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
    @Generated(hash = 162344899)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getFloorDao() : null;
    }
}
