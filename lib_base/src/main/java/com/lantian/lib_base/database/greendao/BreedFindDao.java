package com.lantian.lib_base.database.greendao;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.lantian.lib_base.entity.module.response.breeds.BreedFind;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "BREED_FIND".
*/
public class BreedFindDao extends AbstractDao<BreedFind, String> {

    public static final String TABLENAME = "BREED_FIND";

    /**
     * Properties of entity BreedFind.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Status = new Property(0, int.class, "status", false, "STATUS");
        public final static Property Id = new Property(1, String.class, "id", true, "ID");
        public final static Property User_id = new Property(2, String.class, "user_id", false, "USER_ID");
        public final static Property Hukou_id = new Property(3, String.class, "hukou_id", false, "HUKOU_ID");
        public final static Property Breedclass_id = new Property(4, String.class, "breedclass_id", false, "BREEDCLASS_ID");
        public final static Property Number = new Property(5, String.class, "number", false, "NUMBER");
        public final static Property Acquisition_time = new Property(6, String.class, "acquisition_time", false, "ACQUISITION_TIME");
        public final static Property Title = new Property(7, String.class, "title", false, "TITLE");
        public final static Property Age = new Property(8, String.class, "age", false, "AGE");
        public final static Property Become_time = new Property(9, String.class, "become_time", false, "BECOME_TIME");
        public final static Property Become_price = new Property(10, String.class, "become_price", false, "BECOME_PRICE");
        public final static Property Price = new Property(11, String.class, "price", false, "PRICE");
        public final static Property Addtime = new Property(12, String.class, "addtime", false, "ADDTIME");
        public final static Property Shenhe = new Property(13, String.class, "shenhe", false, "SHENHE");
        public final static Property Img = new Property(14, String.class, "img", false, "IMG");
        public final static Property Common = new Property(15, String.class, "common", false, "COMMON");
        public final static Property Mother = new Property(16, String.class, "mother", false, "MOTHER");
        public final static Property Sheng = new Property(17, String.class, "sheng", false, "SHENG");
        public final static Property Shi = new Property(18, String.class, "shi", false, "SHI");
        public final static Property Xian = new Property(19, String.class, "xian", false, "XIAN");
        public final static Property Variety_id = new Property(20, String.class, "variety_id", false, "VARIETY_ID");
        public final static Property Supplier_id = new Property(21, String.class, "supplier_id", false, "SUPPLIER_ID");
        public final static Property User_idt = new Property(22, String.class, "user_idt", false, "USER_IDT");
        public final static Property Birthday = new Property(23, String.class, "birthday", false, "BIRTHDAY");
        public final static Property Insurance_type = new Property(24, String.class, "insurance_type", false, "INSURANCE_TYPE");
        public final static Property Weight = new Property(25, String.class, "weight", false, "WEIGHT");
        public final static Property Dizhi = new Property(26, String.class, "dizhi", false, "DIZHI");
        public final static Property Type = new Property(27, String.class, "type", false, "TYPE");
        public final static Property Typet = new Property(28, int.class, "typet", false, "TYPET");
        public final static Property Variety = new Property(29, String.class, "variety", false, "VARIETY");
        public final static Property Supplier = new Property(30, String.class, "supplier", false, "SUPPLIER");
        public final static Property Gong = new Property(31, String.class, "gong", false, "GONG");
        public final static Property Mu = new Property(32, String.class, "mu", false, "MU");
    }


    public BreedFindDao(DaoConfig config) {
        super(config);
    }
    
    public BreedFindDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"BREED_FIND\" (" + //
                "\"STATUS\" INTEGER NOT NULL ," + // 0: status
                "\"ID\" TEXT PRIMARY KEY NOT NULL ," + // 1: id
                "\"USER_ID\" TEXT," + // 2: user_id
                "\"HUKOU_ID\" TEXT," + // 3: hukou_id
                "\"BREEDCLASS_ID\" TEXT," + // 4: breedclass_id
                "\"NUMBER\" TEXT," + // 5: number
                "\"ACQUISITION_TIME\" TEXT," + // 6: acquisition_time
                "\"TITLE\" TEXT," + // 7: title
                "\"AGE\" TEXT," + // 8: age
                "\"BECOME_TIME\" TEXT," + // 9: become_time
                "\"BECOME_PRICE\" TEXT," + // 10: become_price
                "\"PRICE\" TEXT," + // 11: price
                "\"ADDTIME\" TEXT," + // 12: addtime
                "\"SHENHE\" TEXT," + // 13: shenhe
                "\"IMG\" TEXT," + // 14: img
                "\"COMMON\" TEXT," + // 15: common
                "\"MOTHER\" TEXT," + // 16: mother
                "\"SHENG\" TEXT," + // 17: sheng
                "\"SHI\" TEXT," + // 18: shi
                "\"XIAN\" TEXT," + // 19: xian
                "\"VARIETY_ID\" TEXT," + // 20: variety_id
                "\"SUPPLIER_ID\" TEXT," + // 21: supplier_id
                "\"USER_IDT\" TEXT," + // 22: user_idt
                "\"BIRTHDAY\" TEXT," + // 23: birthday
                "\"INSURANCE_TYPE\" TEXT," + // 24: insurance_type
                "\"WEIGHT\" TEXT," + // 25: weight
                "\"DIZHI\" TEXT," + // 26: dizhi
                "\"TYPE\" TEXT," + // 27: type
                "\"TYPET\" INTEGER NOT NULL ," + // 28: typet
                "\"VARIETY\" TEXT," + // 29: variety
                "\"SUPPLIER\" TEXT," + // 30: supplier
                "\"GONG\" TEXT," + // 31: gong
                "\"MU\" TEXT);"); // 32: mu
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"BREED_FIND\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, BreedFind entity) {
        stmt.clearBindings();
        stmt.bindLong(1, entity.getStatus());
 
        String id = entity.getId();
        if (id != null) {
            stmt.bindString(2, id);
        }
 
        String user_id = entity.getUser_id();
        if (user_id != null) {
            stmt.bindString(3, user_id);
        }
 
        String hukou_id = entity.getHukou_id();
        if (hukou_id != null) {
            stmt.bindString(4, hukou_id);
        }
 
        String breedclass_id = entity.getBreedclass_id();
        if (breedclass_id != null) {
            stmt.bindString(5, breedclass_id);
        }
 
        String number = entity.getNumber();
        if (number != null) {
            stmt.bindString(6, number);
        }
 
        String acquisition_time = entity.getAcquisition_time();
        if (acquisition_time != null) {
            stmt.bindString(7, acquisition_time);
        }
 
        String title = entity.getTitle();
        if (title != null) {
            stmt.bindString(8, title);
        }
 
        String age = entity.getAge();
        if (age != null) {
            stmt.bindString(9, age);
        }
 
        String become_time = entity.getBecome_time();
        if (become_time != null) {
            stmt.bindString(10, become_time);
        }
 
        String become_price = entity.getBecome_price();
        if (become_price != null) {
            stmt.bindString(11, become_price);
        }
 
        String price = entity.getPrice();
        if (price != null) {
            stmt.bindString(12, price);
        }
 
        String addtime = entity.getAddtime();
        if (addtime != null) {
            stmt.bindString(13, addtime);
        }
 
        String shenhe = entity.getShenhe();
        if (shenhe != null) {
            stmt.bindString(14, shenhe);
        }
 
        String img = entity.getImg();
        if (img != null) {
            stmt.bindString(15, img);
        }
 
        String common = entity.getCommon();
        if (common != null) {
            stmt.bindString(16, common);
        }
 
        String mother = entity.getMother();
        if (mother != null) {
            stmt.bindString(17, mother);
        }
 
        String sheng = entity.getSheng();
        if (sheng != null) {
            stmt.bindString(18, sheng);
        }
 
        String shi = entity.getShi();
        if (shi != null) {
            stmt.bindString(19, shi);
        }
 
        String xian = entity.getXian();
        if (xian != null) {
            stmt.bindString(20, xian);
        }
 
        String variety_id = entity.getVariety_id();
        if (variety_id != null) {
            stmt.bindString(21, variety_id);
        }
 
        String supplier_id = entity.getSupplier_id();
        if (supplier_id != null) {
            stmt.bindString(22, supplier_id);
        }
 
        String user_idt = entity.getUser_idt();
        if (user_idt != null) {
            stmt.bindString(23, user_idt);
        }
 
        String birthday = entity.getBirthday();
        if (birthday != null) {
            stmt.bindString(24, birthday);
        }
 
        String insurance_type = entity.getInsurance_type();
        if (insurance_type != null) {
            stmt.bindString(25, insurance_type);
        }
 
        String weight = entity.getWeight();
        if (weight != null) {
            stmt.bindString(26, weight);
        }
 
        String dizhi = entity.getDizhi();
        if (dizhi != null) {
            stmt.bindString(27, dizhi);
        }
 
        String type = entity.getType();
        if (type != null) {
            stmt.bindString(28, type);
        }
        stmt.bindLong(29, entity.getTypet());
 
        String variety = entity.getVariety();
        if (variety != null) {
            stmt.bindString(30, variety);
        }
 
        String supplier = entity.getSupplier();
        if (supplier != null) {
            stmt.bindString(31, supplier);
        }
 
        String gong = entity.getGong();
        if (gong != null) {
            stmt.bindString(32, gong);
        }
 
        String mu = entity.getMu();
        if (mu != null) {
            stmt.bindString(33, mu);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, BreedFind entity) {
        stmt.clearBindings();
        stmt.bindLong(1, entity.getStatus());
 
        String id = entity.getId();
        if (id != null) {
            stmt.bindString(2, id);
        }
 
        String user_id = entity.getUser_id();
        if (user_id != null) {
            stmt.bindString(3, user_id);
        }
 
        String hukou_id = entity.getHukou_id();
        if (hukou_id != null) {
            stmt.bindString(4, hukou_id);
        }
 
        String breedclass_id = entity.getBreedclass_id();
        if (breedclass_id != null) {
            stmt.bindString(5, breedclass_id);
        }
 
        String number = entity.getNumber();
        if (number != null) {
            stmt.bindString(6, number);
        }
 
        String acquisition_time = entity.getAcquisition_time();
        if (acquisition_time != null) {
            stmt.bindString(7, acquisition_time);
        }
 
        String title = entity.getTitle();
        if (title != null) {
            stmt.bindString(8, title);
        }
 
        String age = entity.getAge();
        if (age != null) {
            stmt.bindString(9, age);
        }
 
        String become_time = entity.getBecome_time();
        if (become_time != null) {
            stmt.bindString(10, become_time);
        }
 
        String become_price = entity.getBecome_price();
        if (become_price != null) {
            stmt.bindString(11, become_price);
        }
 
        String price = entity.getPrice();
        if (price != null) {
            stmt.bindString(12, price);
        }
 
        String addtime = entity.getAddtime();
        if (addtime != null) {
            stmt.bindString(13, addtime);
        }
 
        String shenhe = entity.getShenhe();
        if (shenhe != null) {
            stmt.bindString(14, shenhe);
        }
 
        String img = entity.getImg();
        if (img != null) {
            stmt.bindString(15, img);
        }
 
        String common = entity.getCommon();
        if (common != null) {
            stmt.bindString(16, common);
        }
 
        String mother = entity.getMother();
        if (mother != null) {
            stmt.bindString(17, mother);
        }
 
        String sheng = entity.getSheng();
        if (sheng != null) {
            stmt.bindString(18, sheng);
        }
 
        String shi = entity.getShi();
        if (shi != null) {
            stmt.bindString(19, shi);
        }
 
        String xian = entity.getXian();
        if (xian != null) {
            stmt.bindString(20, xian);
        }
 
        String variety_id = entity.getVariety_id();
        if (variety_id != null) {
            stmt.bindString(21, variety_id);
        }
 
        String supplier_id = entity.getSupplier_id();
        if (supplier_id != null) {
            stmt.bindString(22, supplier_id);
        }
 
        String user_idt = entity.getUser_idt();
        if (user_idt != null) {
            stmt.bindString(23, user_idt);
        }
 
        String birthday = entity.getBirthday();
        if (birthday != null) {
            stmt.bindString(24, birthday);
        }
 
        String insurance_type = entity.getInsurance_type();
        if (insurance_type != null) {
            stmt.bindString(25, insurance_type);
        }
 
        String weight = entity.getWeight();
        if (weight != null) {
            stmt.bindString(26, weight);
        }
 
        String dizhi = entity.getDizhi();
        if (dizhi != null) {
            stmt.bindString(27, dizhi);
        }
 
        String type = entity.getType();
        if (type != null) {
            stmt.bindString(28, type);
        }
        stmt.bindLong(29, entity.getTypet());
 
        String variety = entity.getVariety();
        if (variety != null) {
            stmt.bindString(30, variety);
        }
 
        String supplier = entity.getSupplier();
        if (supplier != null) {
            stmt.bindString(31, supplier);
        }
 
        String gong = entity.getGong();
        if (gong != null) {
            stmt.bindString(32, gong);
        }
 
        String mu = entity.getMu();
        if (mu != null) {
            stmt.bindString(33, mu);
        }
    }

    @Override
    public String readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1);
    }    

    @Override
    public BreedFind readEntity(Cursor cursor, int offset) {
        BreedFind entity = new BreedFind( //
            cursor.getInt(offset + 0), // status
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // id
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // user_id
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // hukou_id
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // breedclass_id
            cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5), // number
            cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6), // acquisition_time
            cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7), // title
            cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8), // age
            cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9), // become_time
            cursor.isNull(offset + 10) ? null : cursor.getString(offset + 10), // become_price
            cursor.isNull(offset + 11) ? null : cursor.getString(offset + 11), // price
            cursor.isNull(offset + 12) ? null : cursor.getString(offset + 12), // addtime
            cursor.isNull(offset + 13) ? null : cursor.getString(offset + 13), // shenhe
            cursor.isNull(offset + 14) ? null : cursor.getString(offset + 14), // img
            cursor.isNull(offset + 15) ? null : cursor.getString(offset + 15), // common
            cursor.isNull(offset + 16) ? null : cursor.getString(offset + 16), // mother
            cursor.isNull(offset + 17) ? null : cursor.getString(offset + 17), // sheng
            cursor.isNull(offset + 18) ? null : cursor.getString(offset + 18), // shi
            cursor.isNull(offset + 19) ? null : cursor.getString(offset + 19), // xian
            cursor.isNull(offset + 20) ? null : cursor.getString(offset + 20), // variety_id
            cursor.isNull(offset + 21) ? null : cursor.getString(offset + 21), // supplier_id
            cursor.isNull(offset + 22) ? null : cursor.getString(offset + 22), // user_idt
            cursor.isNull(offset + 23) ? null : cursor.getString(offset + 23), // birthday
            cursor.isNull(offset + 24) ? null : cursor.getString(offset + 24), // insurance_type
            cursor.isNull(offset + 25) ? null : cursor.getString(offset + 25), // weight
            cursor.isNull(offset + 26) ? null : cursor.getString(offset + 26), // dizhi
            cursor.isNull(offset + 27) ? null : cursor.getString(offset + 27), // type
            cursor.getInt(offset + 28), // typet
            cursor.isNull(offset + 29) ? null : cursor.getString(offset + 29), // variety
            cursor.isNull(offset + 30) ? null : cursor.getString(offset + 30), // supplier
            cursor.isNull(offset + 31) ? null : cursor.getString(offset + 31), // gong
            cursor.isNull(offset + 32) ? null : cursor.getString(offset + 32) // mu
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, BreedFind entity, int offset) {
        entity.setStatus(cursor.getInt(offset + 0));
        entity.setId(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setUser_id(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setHukou_id(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setBreedclass_id(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setNumber(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
        entity.setAcquisition_time(cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6));
        entity.setTitle(cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7));
        entity.setAge(cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8));
        entity.setBecome_time(cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9));
        entity.setBecome_price(cursor.isNull(offset + 10) ? null : cursor.getString(offset + 10));
        entity.setPrice(cursor.isNull(offset + 11) ? null : cursor.getString(offset + 11));
        entity.setAddtime(cursor.isNull(offset + 12) ? null : cursor.getString(offset + 12));
        entity.setShenhe(cursor.isNull(offset + 13) ? null : cursor.getString(offset + 13));
        entity.setImg(cursor.isNull(offset + 14) ? null : cursor.getString(offset + 14));
        entity.setCommon(cursor.isNull(offset + 15) ? null : cursor.getString(offset + 15));
        entity.setMother(cursor.isNull(offset + 16) ? null : cursor.getString(offset + 16));
        entity.setSheng(cursor.isNull(offset + 17) ? null : cursor.getString(offset + 17));
        entity.setShi(cursor.isNull(offset + 18) ? null : cursor.getString(offset + 18));
        entity.setXian(cursor.isNull(offset + 19) ? null : cursor.getString(offset + 19));
        entity.setVariety_id(cursor.isNull(offset + 20) ? null : cursor.getString(offset + 20));
        entity.setSupplier_id(cursor.isNull(offset + 21) ? null : cursor.getString(offset + 21));
        entity.setUser_idt(cursor.isNull(offset + 22) ? null : cursor.getString(offset + 22));
        entity.setBirthday(cursor.isNull(offset + 23) ? null : cursor.getString(offset + 23));
        entity.setInsurance_type(cursor.isNull(offset + 24) ? null : cursor.getString(offset + 24));
        entity.setWeight(cursor.isNull(offset + 25) ? null : cursor.getString(offset + 25));
        entity.setDizhi(cursor.isNull(offset + 26) ? null : cursor.getString(offset + 26));
        entity.setType(cursor.isNull(offset + 27) ? null : cursor.getString(offset + 27));
        entity.setTypet(cursor.getInt(offset + 28));
        entity.setVariety(cursor.isNull(offset + 29) ? null : cursor.getString(offset + 29));
        entity.setSupplier(cursor.isNull(offset + 30) ? null : cursor.getString(offset + 30));
        entity.setGong(cursor.isNull(offset + 31) ? null : cursor.getString(offset + 31));
        entity.setMu(cursor.isNull(offset + 32) ? null : cursor.getString(offset + 32));
     }
    
    @Override
    protected final String updateKeyAfterInsert(BreedFind entity, long rowId) {
        return entity.getId();
    }
    
    @Override
    public String getKey(BreedFind entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(BreedFind entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
