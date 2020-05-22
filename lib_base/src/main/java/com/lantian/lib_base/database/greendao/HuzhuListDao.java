package com.lantian.lib_base.database.greendao;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.lantian.lib_base.entity.module.response.farmer.farmlist.HuzhuList;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "HUZHU_LIST".
*/
public class HuzhuListDao extends AbstractDao<HuzhuList, String> {

    public static final String TABLENAME = "HUZHU_LIST";

    /**
     * Properties of entity HuzhuList.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, String.class, "id", true, "ID");
        public final static Property Status = new Property(1, int.class, "status", false, "STATUS");
        public final static Property Name = new Property(2, String.class, "name", false, "NAME");
        public final static Property Relations = new Property(3, String.class, "relations", false, "RELATIONS");
        public final static Property Tel = new Property(4, String.class, "tel", false, "TEL");
        public final static Property Labour_type = new Property(5, String.class, "labour_type", false, "LABOUR_TYPE");
        public final static Property Idcard_front = new Property(6, String.class, "idcard_front", false, "IDCARD_FRONT");
        public final static Property Idcard_side = new Property(7, String.class, "idcard_side", false, "IDCARD_SIDE");
        public final static Property Idcard_front_angle = new Property(8, String.class, "idcard_front_angle", false, "IDCARD_FRONT_ANGLE");
        public final static Property Idcard_side_angle = new Property(9, String.class, "idcard_side_angle", false, "IDCARD_SIDE_ANGLE");
        public final static Property Idcard_name = new Property(10, String.class, "idcard_name", false, "IDCARD_NAME");
        public final static Property Idcard_gender = new Property(11, String.class, "idcard_gender", false, "IDCARD_GENDER");
        public final static Property Birth_date = new Property(12, String.class, "birth_date", false, "BIRTH_DATE");
        public final static Property Idcard = new Property(13, String.class, "idcard", false, "IDCARD");
        public final static Property Sheng = new Property(14, String.class, "sheng", false, "SHENG");
        public final static Property Shi = new Property(15, String.class, "shi", false, "SHI");
        public final static Property Xian = new Property(16, String.class, "xian", false, "XIAN");
        public final static Property Xiangxi = new Property(17, String.class, "xiangxi", false, "XIANGXI");
        public final static Property Dizhi = new Property(18, String.class, "dizhi", false, "DIZHI");
        public final static Property Addtime = new Property(19, String.class, "addtime", false, "ADDTIME");
        public final static Property Update_time = new Property(20, String.class, "update_time", false, "UPDATE_TIME");
        public final static Property Shenhe = new Property(21, String.class, "shenhe", false, "SHENHE");
        public final static Property User_id = new Property(22, String.class, "user_id", false, "USER_ID");
        public final static Property Age = new Property(23, String.class, "age", false, "AGE");
        public final static Property Created_at = new Property(24, String.class, "created_at", false, "CREATED_AT");
    }


    public HuzhuListDao(DaoConfig config) {
        super(config);
    }
    
    public HuzhuListDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"HUZHU_LIST\" (" + //
                "\"ID\" TEXT PRIMARY KEY NOT NULL ," + // 0: id
                "\"STATUS\" INTEGER NOT NULL ," + // 1: status
                "\"NAME\" TEXT," + // 2: name
                "\"RELATIONS\" TEXT," + // 3: relations
                "\"TEL\" TEXT," + // 4: tel
                "\"LABOUR_TYPE\" TEXT," + // 5: labour_type
                "\"IDCARD_FRONT\" TEXT," + // 6: idcard_front
                "\"IDCARD_SIDE\" TEXT," + // 7: idcard_side
                "\"IDCARD_FRONT_ANGLE\" TEXT," + // 8: idcard_front_angle
                "\"IDCARD_SIDE_ANGLE\" TEXT," + // 9: idcard_side_angle
                "\"IDCARD_NAME\" TEXT," + // 10: idcard_name
                "\"IDCARD_GENDER\" TEXT," + // 11: idcard_gender
                "\"BIRTH_DATE\" TEXT," + // 12: birth_date
                "\"IDCARD\" TEXT," + // 13: idcard
                "\"SHENG\" TEXT," + // 14: sheng
                "\"SHI\" TEXT," + // 15: shi
                "\"XIAN\" TEXT," + // 16: xian
                "\"XIANGXI\" TEXT," + // 17: xiangxi
                "\"DIZHI\" TEXT," + // 18: dizhi
                "\"ADDTIME\" TEXT," + // 19: addtime
                "\"UPDATE_TIME\" TEXT," + // 20: update_time
                "\"SHENHE\" TEXT," + // 21: shenhe
                "\"USER_ID\" TEXT," + // 22: user_id
                "\"AGE\" TEXT," + // 23: age
                "\"CREATED_AT\" TEXT);"); // 24: created_at
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"HUZHU_LIST\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, HuzhuList entity) {
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
 
        String relations = entity.getRelations();
        if (relations != null) {
            stmt.bindString(4, relations);
        }
 
        String tel = entity.getTel();
        if (tel != null) {
            stmt.bindString(5, tel);
        }
 
        String labour_type = entity.getLabour_type();
        if (labour_type != null) {
            stmt.bindString(6, labour_type);
        }
 
        String idcard_front = entity.getIdcard_front();
        if (idcard_front != null) {
            stmt.bindString(7, idcard_front);
        }
 
        String idcard_side = entity.getIdcard_side();
        if (idcard_side != null) {
            stmt.bindString(8, idcard_side);
        }
 
        String idcard_front_angle = entity.getIdcard_front_angle();
        if (idcard_front_angle != null) {
            stmt.bindString(9, idcard_front_angle);
        }
 
        String idcard_side_angle = entity.getIdcard_side_angle();
        if (idcard_side_angle != null) {
            stmt.bindString(10, idcard_side_angle);
        }
 
        String idcard_name = entity.getIdcard_name();
        if (idcard_name != null) {
            stmt.bindString(11, idcard_name);
        }
 
        String idcard_gender = entity.getIdcard_gender();
        if (idcard_gender != null) {
            stmt.bindString(12, idcard_gender);
        }
 
        String birth_date = entity.getBirth_date();
        if (birth_date != null) {
            stmt.bindString(13, birth_date);
        }
 
        String idcard = entity.getIdcard();
        if (idcard != null) {
            stmt.bindString(14, idcard);
        }
 
        String sheng = entity.getSheng();
        if (sheng != null) {
            stmt.bindString(15, sheng);
        }
 
        String shi = entity.getShi();
        if (shi != null) {
            stmt.bindString(16, shi);
        }
 
        String xian = entity.getXian();
        if (xian != null) {
            stmt.bindString(17, xian);
        }
 
        String xiangxi = entity.getXiangxi();
        if (xiangxi != null) {
            stmt.bindString(18, xiangxi);
        }
 
        String dizhi = entity.getDizhi();
        if (dizhi != null) {
            stmt.bindString(19, dizhi);
        }
 
        String addtime = entity.getAddtime();
        if (addtime != null) {
            stmt.bindString(20, addtime);
        }
 
        String update_time = entity.getUpdate_time();
        if (update_time != null) {
            stmt.bindString(21, update_time);
        }
 
        String shenhe = entity.getShenhe();
        if (shenhe != null) {
            stmt.bindString(22, shenhe);
        }
 
        String user_id = entity.getUser_id();
        if (user_id != null) {
            stmt.bindString(23, user_id);
        }
 
        String age = entity.getAge();
        if (age != null) {
            stmt.bindString(24, age);
        }
 
        String created_at = entity.getCreated_at();
        if (created_at != null) {
            stmt.bindString(25, created_at);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, HuzhuList entity) {
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
 
        String relations = entity.getRelations();
        if (relations != null) {
            stmt.bindString(4, relations);
        }
 
        String tel = entity.getTel();
        if (tel != null) {
            stmt.bindString(5, tel);
        }
 
        String labour_type = entity.getLabour_type();
        if (labour_type != null) {
            stmt.bindString(6, labour_type);
        }
 
        String idcard_front = entity.getIdcard_front();
        if (idcard_front != null) {
            stmt.bindString(7, idcard_front);
        }
 
        String idcard_side = entity.getIdcard_side();
        if (idcard_side != null) {
            stmt.bindString(8, idcard_side);
        }
 
        String idcard_front_angle = entity.getIdcard_front_angle();
        if (idcard_front_angle != null) {
            stmt.bindString(9, idcard_front_angle);
        }
 
        String idcard_side_angle = entity.getIdcard_side_angle();
        if (idcard_side_angle != null) {
            stmt.bindString(10, idcard_side_angle);
        }
 
        String idcard_name = entity.getIdcard_name();
        if (idcard_name != null) {
            stmt.bindString(11, idcard_name);
        }
 
        String idcard_gender = entity.getIdcard_gender();
        if (idcard_gender != null) {
            stmt.bindString(12, idcard_gender);
        }
 
        String birth_date = entity.getBirth_date();
        if (birth_date != null) {
            stmt.bindString(13, birth_date);
        }
 
        String idcard = entity.getIdcard();
        if (idcard != null) {
            stmt.bindString(14, idcard);
        }
 
        String sheng = entity.getSheng();
        if (sheng != null) {
            stmt.bindString(15, sheng);
        }
 
        String shi = entity.getShi();
        if (shi != null) {
            stmt.bindString(16, shi);
        }
 
        String xian = entity.getXian();
        if (xian != null) {
            stmt.bindString(17, xian);
        }
 
        String xiangxi = entity.getXiangxi();
        if (xiangxi != null) {
            stmt.bindString(18, xiangxi);
        }
 
        String dizhi = entity.getDizhi();
        if (dizhi != null) {
            stmt.bindString(19, dizhi);
        }
 
        String addtime = entity.getAddtime();
        if (addtime != null) {
            stmt.bindString(20, addtime);
        }
 
        String update_time = entity.getUpdate_time();
        if (update_time != null) {
            stmt.bindString(21, update_time);
        }
 
        String shenhe = entity.getShenhe();
        if (shenhe != null) {
            stmt.bindString(22, shenhe);
        }
 
        String user_id = entity.getUser_id();
        if (user_id != null) {
            stmt.bindString(23, user_id);
        }
 
        String age = entity.getAge();
        if (age != null) {
            stmt.bindString(24, age);
        }
 
        String created_at = entity.getCreated_at();
        if (created_at != null) {
            stmt.bindString(25, created_at);
        }
    }

    @Override
    public String readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0);
    }    

    @Override
    public HuzhuList readEntity(Cursor cursor, int offset) {
        HuzhuList entity = new HuzhuList( //
            cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0), // id
            cursor.getInt(offset + 1), // status
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // name
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // relations
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // tel
            cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5), // labour_type
            cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6), // idcard_front
            cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7), // idcard_side
            cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8), // idcard_front_angle
            cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9), // idcard_side_angle
            cursor.isNull(offset + 10) ? null : cursor.getString(offset + 10), // idcard_name
            cursor.isNull(offset + 11) ? null : cursor.getString(offset + 11), // idcard_gender
            cursor.isNull(offset + 12) ? null : cursor.getString(offset + 12), // birth_date
            cursor.isNull(offset + 13) ? null : cursor.getString(offset + 13), // idcard
            cursor.isNull(offset + 14) ? null : cursor.getString(offset + 14), // sheng
            cursor.isNull(offset + 15) ? null : cursor.getString(offset + 15), // shi
            cursor.isNull(offset + 16) ? null : cursor.getString(offset + 16), // xian
            cursor.isNull(offset + 17) ? null : cursor.getString(offset + 17), // xiangxi
            cursor.isNull(offset + 18) ? null : cursor.getString(offset + 18), // dizhi
            cursor.isNull(offset + 19) ? null : cursor.getString(offset + 19), // addtime
            cursor.isNull(offset + 20) ? null : cursor.getString(offset + 20), // update_time
            cursor.isNull(offset + 21) ? null : cursor.getString(offset + 21), // shenhe
            cursor.isNull(offset + 22) ? null : cursor.getString(offset + 22), // user_id
            cursor.isNull(offset + 23) ? null : cursor.getString(offset + 23), // age
            cursor.isNull(offset + 24) ? null : cursor.getString(offset + 24) // created_at
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, HuzhuList entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0));
        entity.setStatus(cursor.getInt(offset + 1));
        entity.setName(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setRelations(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setTel(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setLabour_type(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
        entity.setIdcard_front(cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6));
        entity.setIdcard_side(cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7));
        entity.setIdcard_front_angle(cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8));
        entity.setIdcard_side_angle(cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9));
        entity.setIdcard_name(cursor.isNull(offset + 10) ? null : cursor.getString(offset + 10));
        entity.setIdcard_gender(cursor.isNull(offset + 11) ? null : cursor.getString(offset + 11));
        entity.setBirth_date(cursor.isNull(offset + 12) ? null : cursor.getString(offset + 12));
        entity.setIdcard(cursor.isNull(offset + 13) ? null : cursor.getString(offset + 13));
        entity.setSheng(cursor.isNull(offset + 14) ? null : cursor.getString(offset + 14));
        entity.setShi(cursor.isNull(offset + 15) ? null : cursor.getString(offset + 15));
        entity.setXian(cursor.isNull(offset + 16) ? null : cursor.getString(offset + 16));
        entity.setXiangxi(cursor.isNull(offset + 17) ? null : cursor.getString(offset + 17));
        entity.setDizhi(cursor.isNull(offset + 18) ? null : cursor.getString(offset + 18));
        entity.setAddtime(cursor.isNull(offset + 19) ? null : cursor.getString(offset + 19));
        entity.setUpdate_time(cursor.isNull(offset + 20) ? null : cursor.getString(offset + 20));
        entity.setShenhe(cursor.isNull(offset + 21) ? null : cursor.getString(offset + 21));
        entity.setUser_id(cursor.isNull(offset + 22) ? null : cursor.getString(offset + 22));
        entity.setAge(cursor.isNull(offset + 23) ? null : cursor.getString(offset + 23));
        entity.setCreated_at(cursor.isNull(offset + 24) ? null : cursor.getString(offset + 24));
     }
    
    @Override
    protected final String updateKeyAfterInsert(HuzhuList entity, long rowId) {
        return entity.getId();
    }
    
    @Override
    public String getKey(HuzhuList entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(HuzhuList entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}