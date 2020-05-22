package com.lantian.lib_base.database.greendao;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.lantian.lib_base.entity.module.response.farmer.plan.countPlantsum;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "COUNT_PLANTSUM".
*/
public class countPlantsumDao extends AbstractDao<countPlantsum, String> {

    public static final String TABLENAME = "COUNT_PLANTSUM";

    /**
     * Properties of entity countPlantsum.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Userid = new Property(0, String.class, "userid", true, "USERID");
        public final static Property Status = new Property(1, int.class, "status", false, "STATUS");
        public final static Property Plant = new Property(2, String.class, "plant", false, "PLANT");
        public final static Property Plantclass = new Property(3, String.class, "plantclass", false, "PLANTCLASS");
    }


    public countPlantsumDao(DaoConfig config) {
        super(config);
    }
    
    public countPlantsumDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"COUNT_PLANTSUM\" (" + //
                "\"USERID\" TEXT PRIMARY KEY NOT NULL ," + // 0: userid
                "\"STATUS\" INTEGER NOT NULL ," + // 1: status
                "\"PLANT\" TEXT," + // 2: plant
                "\"PLANTCLASS\" TEXT);"); // 3: plantclass
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"COUNT_PLANTSUM\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, countPlantsum entity) {
        stmt.clearBindings();
 
        String userid = entity.getUserid();
        if (userid != null) {
            stmt.bindString(1, userid);
        }
        stmt.bindLong(2, entity.getStatus());
 
        String plant = entity.getPlant();
        if (plant != null) {
            stmt.bindString(3, plant);
        }
 
        String plantclass = entity.getPlantclass();
        if (plantclass != null) {
            stmt.bindString(4, plantclass);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, countPlantsum entity) {
        stmt.clearBindings();
 
        String userid = entity.getUserid();
        if (userid != null) {
            stmt.bindString(1, userid);
        }
        stmt.bindLong(2, entity.getStatus());
 
        String plant = entity.getPlant();
        if (plant != null) {
            stmt.bindString(3, plant);
        }
 
        String plantclass = entity.getPlantclass();
        if (plantclass != null) {
            stmt.bindString(4, plantclass);
        }
    }

    @Override
    public String readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0);
    }    

    @Override
    public countPlantsum readEntity(Cursor cursor, int offset) {
        countPlantsum entity = new countPlantsum( //
            cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0), // userid
            cursor.getInt(offset + 1), // status
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // plant
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3) // plantclass
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, countPlantsum entity, int offset) {
        entity.setUserid(cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0));
        entity.setStatus(cursor.getInt(offset + 1));
        entity.setPlant(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setPlantclass(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
     }
    
    @Override
    protected final String updateKeyAfterInsert(countPlantsum entity, long rowId) {
        return entity.getUserid();
    }
    
    @Override
    public String getKey(countPlantsum entity) {
        if(entity != null) {
            return entity.getUserid();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(countPlantsum entity) {
        return entity.getUserid() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}