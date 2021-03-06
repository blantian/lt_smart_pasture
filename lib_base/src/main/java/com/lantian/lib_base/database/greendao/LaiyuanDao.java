package com.lantian.lib_base.database.greendao;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.lantian.lib_base.entity.module.response.breeds.Laiyuan;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "LAIYUAN".
*/
public class LaiyuanDao extends AbstractDao<Laiyuan, String> {

    public static final String TABLENAME = "LAIYUAN";

    /**
     * Properties of entity Laiyuan.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, String.class, "id", true, "ID");
        public final static Property Name = new Property(1, String.class, "name", false, "NAME");
        public final static Property Addtime = new Property(2, String.class, "addtime", false, "ADDTIME");
        public final static Property Shenhe = new Property(3, String.class, "shenhe", false, "SHENHE");
        public final static Property Memberid = new Property(4, String.class, "memberid", false, "MEMBERID");
    }


    public LaiyuanDao(DaoConfig config) {
        super(config);
    }
    
    public LaiyuanDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"LAIYUAN\" (" + //
                "\"ID\" TEXT PRIMARY KEY NOT NULL ," + // 0: id
                "\"NAME\" TEXT," + // 1: name
                "\"ADDTIME\" TEXT," + // 2: addtime
                "\"SHENHE\" TEXT," + // 3: shenhe
                "\"MEMBERID\" TEXT);"); // 4: memberid
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"LAIYUAN\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, Laiyuan entity) {
        stmt.clearBindings();
 
        String id = entity.getId();
        if (id != null) {
            stmt.bindString(1, id);
        }
 
        String name = entity.getName();
        if (name != null) {
            stmt.bindString(2, name);
        }
 
        String addtime = entity.getAddtime();
        if (addtime != null) {
            stmt.bindString(3, addtime);
        }
 
        String shenhe = entity.getShenhe();
        if (shenhe != null) {
            stmt.bindString(4, shenhe);
        }
 
        String memberid = entity.getMemberid();
        if (memberid != null) {
            stmt.bindString(5, memberid);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, Laiyuan entity) {
        stmt.clearBindings();
 
        String id = entity.getId();
        if (id != null) {
            stmt.bindString(1, id);
        }
 
        String name = entity.getName();
        if (name != null) {
            stmt.bindString(2, name);
        }
 
        String addtime = entity.getAddtime();
        if (addtime != null) {
            stmt.bindString(3, addtime);
        }
 
        String shenhe = entity.getShenhe();
        if (shenhe != null) {
            stmt.bindString(4, shenhe);
        }
 
        String memberid = entity.getMemberid();
        if (memberid != null) {
            stmt.bindString(5, memberid);
        }
    }

    @Override
    public String readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0);
    }    

    @Override
    public Laiyuan readEntity(Cursor cursor, int offset) {
        Laiyuan entity = new Laiyuan( //
            cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // name
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // addtime
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // shenhe
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4) // memberid
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, Laiyuan entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0));
        entity.setName(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setAddtime(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setShenhe(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setMemberid(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
     }
    
    @Override
    protected final String updateKeyAfterInsert(Laiyuan entity, long rowId) {
        return entity.getId();
    }
    
    @Override
    public String getKey(Laiyuan entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(Laiyuan entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
