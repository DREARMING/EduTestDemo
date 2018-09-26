package com.mvcoder.edutestdemo.beans;

import com.mvcoder.edutestdemo.greendao.ClassBuildingDao;
import com.mvcoder.edutestdemo.greendao.DaoSession;
import com.mvcoder.edutestdemo.greendao.FloorDao;

import org.greenrobot.greendao.DaoException;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.ToMany;

import java.util.List;

/**
 * 教学楼
 */
@Entity
public class ClassBuilding {

    //学校id、分校
    private int scroolId;

    @Id
    private long buildingId;

    @NotNull
    private String buildingName;
    private String extra;

    //教学楼的楼层列表
    @ToMany(referencedJoinProperty = "buildingId")
    private List<Floor> floorList;

    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;

    /** Used for active entity operations. */
    @Generated(hash = 885001485)
    private transient ClassBuildingDao myDao;

    @Generated(hash = 1065718309)
    public ClassBuilding(int scroolId, long buildingId,
            @NotNull String buildingName, String extra) {
        this.scroolId = scroolId;
        this.buildingId = buildingId;
        this.buildingName = buildingName;
        this.extra = extra;
    }

    @Generated(hash = 774320108)
    public ClassBuilding() {
    }

    public int getScroolId() {
        return this.scroolId;
    }

    public void setScroolId(int scroolId) {
        this.scroolId = scroolId;
    }

    public long getBuildingId() {
        return this.buildingId;
    }

    public void setBuildingId(long buildingId) {
        this.buildingId = buildingId;
    }

    public String getBuildingName() {
        return this.buildingName;
    }

    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName;
    }

    public String getExtra() {
        return this.extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }

    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 1893818673)
    public List<Floor> getFloorList() {
        if (floorList == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            FloorDao targetDao = daoSession.getFloorDao();
            List<Floor> floorListNew = targetDao
                    ._queryClassBuilding_FloorList(buildingId);
            synchronized (this) {
                if (floorList == null) {
                    floorList = floorListNew;
                }
            }
        }
        return floorList;
    }

    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    @Generated(hash = 2093495330)
    public synchronized void resetFloorList() {
        floorList = null;
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
    @Generated(hash = 1033090167)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getClassBuildingDao() : null;
    }


    public void setFloorList(List<Floor> floorList) {
        this.floorList = floorList;
    }
}
