package com.lantian.lib_base.database.greendao;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.lantian.lib_base.entity.module.response.breeds.BreedClassFind;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "BREED_CLASS_FIND".
*/
public class BreedClassFindDao extends AbstractDao<BreedClassFind, String> {

    public static final String TABLENAME = "BREED_CLASS_FIND";

    /**
     * Properties of entity BreedClassFind.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, String.class, "id", true, "ID");
        public final static Property Status = new Property(1, int.class, "status", false, "STATUS");
        public final static Property Name = new Property(2, String.class, "name", false, "NAME");
        public final static Property Addtime = new Property(3, String.class, "addtime", false, "ADDTIME");
        public final static Property Shenhe = new Property(4, String.class, "shenhe", false, "SHENHE");
        public final static Property Img = new Property(5, String.class, "img", false, "IMG");
        public final static Property Memberid = new Property(6, String.class, "memberid", false, "MEMBERID");
    }


    public BreedClassFindDao(DaoConfig config) {
        super(config);
    }
    
    public BreedClassFindDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"BREED_CLASS_FIND\" (" + //
                "\"ID\" TEXT PRIMARY KEY NOT NULL ," + // 0: id
                "\"STATUS\" INTEGER NOT NULL ," + // 1: status
                "\"NAME\" TEXT," + // 2: name
                "\"ADDTIME\" TEXT," + // 3: addtime
                "\"SHENHE\" TEXT," + // 4: shenhe
                "\"IMG\" TEXT," + // 5: img
                "\"MEMBERID\" TEXT);"); // 6: memberid
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"BREED_CLASS_FIND\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, BreedClassFind entity) {
        stmt.clearBindings();
 
        String id = entity.getId();
        if (id != null) {
            stmt.bindString(1, id);
        }
        stmt.bindLong(2, entity.getStatus());
 
        String name = entity.getName();
        if (name != null) {
            stmt.bindString(3, name);
        }
 
        String addtime = entity.getAddtime();
        if (addtime != null) {
            stmt.bindString(4, addtime);
        }
 
        String shenhe = entity.getShenhe();
        if (shenhe != null) {
            stmt.bindString(5, shenhe);
        }
 
        String img = entity.getImg();
        if (img != null) {
            stmt.bindString(6, img);
        }
 
        String memberid = entity.getMemberid();
        if (memberid != null) {
            stmt.bindString(7, memberid);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, BreedClassFind entity) {
        stmt.clearBindings();
 
        String id = entity.getId();
        if (id != null) {
            stmt.bindString(1, id);
        }
        stmt.bindLong(2, entity.getStatus());
 
        String name = entity.getName();
        if (name != null) {
            stmt.bindString(3, name);
        }
 
        String addtime = entity.getAddtime();
        if (addtime != null) {
            stmt.bindString(4, addtime);
        }
 
        String shenhe = entity.getShenhe();
        if (shenhe != null) {
            stmt.bindString(5, shenhe);
        }
 
        String img = entity.getImg();
        if (img != null) {
            stmt.bindString(6, img);
        }
 
        String memberid = entity.getMemberid();
        if (memberid != null) {
            stmt.bindString(7, memberid);
        }
    }

    @Override
    public String readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0);
    }    

    @Override
    public BreedClassFind readEntity(Cursor cursor, int offset) {
        BreedClassFind entity = new BreedClassFind( //
            cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0), // id
            cursor.getInt(offset + 1), // status
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // name
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // addtime
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // shenhe
            cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5), // img
            cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6) // memberid
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, BreedClassFind entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0));
        entity.setStatus(cursor.getInt(offset + 1));
        entity.setName(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setAddtime(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setShenhe(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setImg(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
        entity.setMemberid(cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6));
     }
    
    @Override
    protected final String updateKeyAfterInsert(BreedClassFind entity, long rowId) {
        return entity.getId();
    }
    
    @Override
    public String getKey(BreedClassFind entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(BreedClassFind entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
