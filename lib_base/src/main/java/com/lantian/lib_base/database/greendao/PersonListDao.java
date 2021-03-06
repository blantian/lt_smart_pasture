package com.lantian.lib_base.database.greendao;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.lantian.lib_base.entity.module.response.farmer.farmlist.PersonList;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "PERSON_LIST".
*/
public class PersonListDao extends AbstractDao<PersonList, String> {

    public static final String TABLENAME = "PERSON_LIST";

    /**
     * Properties of entity PersonList.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, String.class, "id", true, "ID");
        public final static Property Status = new Property(1, int.class, "status", false, "STATUS");
        public final static Property User_id = new Property(2, String.class, "user_id", false, "USER_ID");
        public final static Property Hukou_id = new Property(3, String.class, "hukou_id", false, "HUKOU_ID");
        public final static Property Img = new Property(4, String.class, "img", false, "IMG");
        public final static Property Call = new Property(5, String.class, "call", false, "CALL");
        public final static Property Name = new Property(6, String.class, "name", false, "NAME");
        public final static Property Per_relations = new Property(7, String.class, "per_relations", false, "PER_RELATIONS");
        public final static Property Tel = new Property(8, String.class, "tel", false, "TEL");
        public final static Property Labour_type = new Property(9, String.class, "labour_type", false, "LABOUR_TYPE");
        public final static Property Addtime = new Property(10, String.class, "addtime", false, "ADDTIME");
        public final static Property Shenhe = new Property(11, String.class, "shenhe", false, "SHENHE");
        public final static Property Age = new Property(12, String.class, "age", false, "AGE");
    }


    public PersonListDao(DaoConfig config) {
        super(config);
    }
    
    public PersonListDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"PERSON_LIST\" (" + //
                "\"ID\" TEXT PRIMARY KEY NOT NULL ," + // 0: id
                "\"STATUS\" INTEGER NOT NULL ," + // 1: status
                "\"USER_ID\" TEXT," + // 2: user_id
                "\"HUKOU_ID\" TEXT," + // 3: hukou_id
                "\"IMG\" TEXT," + // 4: img
                "\"CALL\" TEXT," + // 5: call
                "\"NAME\" TEXT," + // 6: name
                "\"PER_RELATIONS\" TEXT," + // 7: per_relations
                "\"TEL\" TEXT," + // 8: tel
                "\"LABOUR_TYPE\" TEXT," + // 9: labour_type
                "\"ADDTIME\" TEXT," + // 10: addtime
                "\"SHENHE\" TEXT," + // 11: shenhe
                "\"AGE\" TEXT);"); // 12: age
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"PERSON_LIST\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, PersonList entity) {
        stmt.clearBindings();
 
        String id = entity.getId();
        if (id != null) {
            stmt.bindString(1, id);
        }
        stmt.bindLong(2, entity.getStatus());
 
        String user_id = entity.getUser_id();
        if (user_id != null) {
            stmt.bindString(3, user_id);
        }
 
        String hukou_id = entity.getHukou_id();
        if (hukou_id != null) {
            stmt.bindString(4, hukou_id);
        }
 
        String img = entity.getImg();
        if (img != null) {
            stmt.bindString(5, img);
        }
 
        String call = entity.getCall();
        if (call != null) {
            stmt.bindString(6, call);
        }
 
        String name = entity.getName();
        if (name != null) {
            stmt.bindString(7, name);
        }
 
        String per_relations = entity.getPer_relations();
        if (per_relations != null) {
            stmt.bindString(8, per_relations);
        }
 
        String tel = entity.getTel();
        if (tel != null) {
            stmt.bindString(9, tel);
        }
 
        String labour_type = entity.getLabour_type();
        if (labour_type != null) {
            stmt.bindString(10, labour_type);
        }
 
        String addtime = entity.getAddtime();
        if (addtime != null) {
            stmt.bindString(11, addtime);
        }
 
        String shenhe = entity.getShenhe();
        if (shenhe != null) {
            stmt.bindString(12, shenhe);
        }
 
        String age = entity.getAge();
        if (age != null) {
            stmt.bindString(13, age);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, PersonList entity) {
        stmt.clearBindings();
 
        String id = entity.getId();
        if (id != null) {
            stmt.bindString(1, id);
        }
        stmt.bindLong(2, entity.getStatus());
 
        String user_id = entity.getUser_id();
        if (user_id != null) {
            stmt.bindString(3, user_id);
        }
 
        String hukou_id = entity.getHukou_id();
        if (hukou_id != null) {
            stmt.bindString(4, hukou_id);
        }
 
        String img = entity.getImg();
        if (img != null) {
            stmt.bindString(5, img);
        }
 
        String call = entity.getCall();
        if (call != null) {
            stmt.bindString(6, call);
        }
 
        String name = entity.getName();
        if (name != null) {
            stmt.bindString(7, name);
        }
 
        String per_relations = entity.getPer_relations();
        if (per_relations != null) {
            stmt.bindString(8, per_relations);
        }
 
        String tel = entity.getTel();
        if (tel != null) {
            stmt.bindString(9, tel);
        }
 
        String labour_type = entity.getLabour_type();
        if (labour_type != null) {
            stmt.bindString(10, labour_type);
        }
 
        String addtime = entity.getAddtime();
        if (addtime != null) {
            stmt.bindString(11, addtime);
        }
 
        String shenhe = entity.getShenhe();
        if (shenhe != null) {
            stmt.bindString(12, shenhe);
        }
 
        String age = entity.getAge();
        if (age != null) {
            stmt.bindString(13, age);
        }
    }

    @Override
    public String readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0);
    }    

    @Override
    public PersonList readEntity(Cursor cursor, int offset) {
        PersonList entity = new PersonList( //
            cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0), // id
            cursor.getInt(offset + 1), // status
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // user_id
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // hukou_id
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // img
            cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5), // call
            cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6), // name
            cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7), // per_relations
            cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8), // tel
            cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9), // labour_type
            cursor.isNull(offset + 10) ? null : cursor.getString(offset + 10), // addtime
            cursor.isNull(offset + 11) ? null : cursor.getString(offset + 11), // shenhe
            cursor.isNull(offset + 12) ? null : cursor.getString(offset + 12) // age
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, PersonList entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0));
        entity.setStatus(cursor.getInt(offset + 1));
        entity.setUser_id(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setHukou_id(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setImg(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setCall(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
        entity.setName(cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6));
        entity.setPer_relations(cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7));
        entity.setTel(cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8));
        entity.setLabour_type(cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9));
        entity.setAddtime(cursor.isNull(offset + 10) ? null : cursor.getString(offset + 10));
        entity.setShenhe(cursor.isNull(offset + 11) ? null : cursor.getString(offset + 11));
        entity.setAge(cursor.isNull(offset + 12) ? null : cursor.getString(offset + 12));
     }
    
    @Override
    protected final String updateKeyAfterInsert(PersonList entity, long rowId) {
        return entity.getId();
    }
    
    @Override
    public String getKey(PersonList entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(PersonList entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
