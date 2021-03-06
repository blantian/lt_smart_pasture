package com.lantian.lib_base.database.greendao;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.lantian.lib_base.entity.module.response.img.Imgs;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "IMGS".
*/
public class ImgsDao extends AbstractDao<Imgs, String> {

    public static final String TABLENAME = "IMGS";

    /**
     * Properties of entity Imgs.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property User_id = new Property(0, String.class, "user_id", false, "USER_ID");
        public final static Property Path = new Property(1, String.class, "path", true, "PATH");
        public final static Property Stutas = new Property(2, int.class, "stutas", false, "STUTAS");
    }


    public ImgsDao(DaoConfig config) {
        super(config);
    }
    
    public ImgsDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"IMGS\" (" + //
                "\"USER_ID\" TEXT," + // 0: user_id
                "\"PATH\" TEXT PRIMARY KEY NOT NULL ," + // 1: path
                "\"STUTAS\" INTEGER NOT NULL );"); // 2: stutas
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"IMGS\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, Imgs entity) {
        stmt.clearBindings();
 
        String user_id = entity.getUser_id();
        if (user_id != null) {
            stmt.bindString(1, user_id);
        }
 
        String path = entity.getPath();
        if (path != null) {
            stmt.bindString(2, path);
        }
        stmt.bindLong(3, entity.getStutas());
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, Imgs entity) {
        stmt.clearBindings();
 
        String user_id = entity.getUser_id();
        if (user_id != null) {
            stmt.bindString(1, user_id);
        }
 
        String path = entity.getPath();
        if (path != null) {
            stmt.bindString(2, path);
        }
        stmt.bindLong(3, entity.getStutas());
    }

    @Override
    public String readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1);
    }    

    @Override
    public Imgs readEntity(Cursor cursor, int offset) {
        Imgs entity = new Imgs( //
            cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0), // user_id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // path
            cursor.getInt(offset + 2) // stutas
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, Imgs entity, int offset) {
        entity.setUser_id(cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0));
        entity.setPath(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setStutas(cursor.getInt(offset + 2));
     }
    
    @Override
    protected final String updateKeyAfterInsert(Imgs entity, long rowId) {
        return entity.getPath();
    }
    
    @Override
    public String getKey(Imgs entity) {
        if(entity != null) {
            return entity.getPath();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(Imgs entity) {
        return entity.getPath() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
