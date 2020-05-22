package com.lantian.lib_base.entity.module.response.farmer.farmlist;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;

import java.io.Serializable;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by Sherlock·Holmes on 2020-04-02
 */
@Entity
public class HuzhuList implements Serializable {

    /**
     * id : 65
     * name : lantian
     * relations : 1
     * tel : 13009500492
     * labour_type : 1
     * idcard_front : null
     * idcard_side : null
     * idcard_front_angle : 0
     * idcard_side_angle : 0
     * idcard_name : null
     * idcard_gender : null
     * birth_date : null
     * idcard : null
     * sheng : null
     * shi : null
     * xian : null
     * xiangxi : null
     * dizhi :
     * addtime : 2020-03-28 09:03:07
     * update_time : null
     * shenhe : null
     * user_id : 29644
     * age : 0
     * created_at : 0000-00-00 00:00:00
     */



    private static final long serialVersionUID = 1L;
    @Id
    private String id;
    private int status;

    private String name;
    private String relations;
    private String tel;
    private String labour_type;
    private String idcard_front;
    private String idcard_side;
    private String idcard_front_angle;
    private String idcard_side_angle;
    private String idcard_name;
    private String idcard_gender;
    private String birth_date;
    private String idcard;
    private String sheng;
    private String shi;
    private String xian;
    private String  xiangxi;
    private String dizhi;
    private String addtime;
    private String update_time;
    private String shenhe;
    private String user_id;
    private String age;
    private String created_at;

    @Generated(hash = 1419229906)
    public HuzhuList(String id, int status, String name, String relations,
            String tel, String labour_type, String idcard_front, String idcard_side,
            String idcard_front_angle, String idcard_side_angle, String idcard_name,
            String idcard_gender, String birth_date, String idcard, String sheng,
            String shi, String xian, String xiangxi, String dizhi, String addtime,
            String update_time, String shenhe, String user_id, String age,
            String created_at) {
        this.id = id;
        this.status = status;
        this.name = name;
        this.relations = relations;
        this.tel = tel;
        this.labour_type = labour_type;
        this.idcard_front = idcard_front;
        this.idcard_side = idcard_side;
        this.idcard_front_angle = idcard_front_angle;
        this.idcard_side_angle = idcard_side_angle;
        this.idcard_name = idcard_name;
        this.idcard_gender = idcard_gender;
        this.birth_date = birth_date;
        this.idcard = idcard;
        this.sheng = sheng;
        this.shi = shi;
        this.xian = xian;
        this.xiangxi = xiangxi;
        this.dizhi = dizhi;
        this.addtime = addtime;
        this.update_time = update_time;
        this.shenhe = shenhe;
        this.user_id = user_id;
        this.age = age;
        this.created_at = created_at;
    }

    @Generated(hash = 126824492)
    public HuzhuList() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRelations() {
        return relations;
    }

    public void setRelations(String relations) {
        this.relations = relations;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getLabour_type() {
        return labour_type;
    }

    public void setLabour_type(String labour_type) {
        this.labour_type = labour_type;
    }

    public String getIdcard_front() {
        return idcard_front;
    }

    public void setIdcard_front(String idcard_front) {
        this.idcard_front = idcard_front;
    }

    public String getIdcard_side() {
        return idcard_side;
    }

    public void setIdcard_side(String idcard_side) {
        this.idcard_side = idcard_side;
    }

    public String getIdcard_front_angle() {
        return idcard_front_angle;
    }

    public void setIdcard_front_angle(String idcard_front_angle) {
        this.idcard_front_angle = idcard_front_angle;
    }

    public String getIdcard_side_angle() {
        return idcard_side_angle;
    }

    public void setIdcard_side_angle(String idcard_side_angle) {
        this.idcard_side_angle = idcard_side_angle;
    }

    public String getIdcard_name() {
        return idcard_name;
    }

    public void setIdcard_name(String idcard_name) {
        this.idcard_name = idcard_name;
    }

    public String getIdcard_gender() {
        return idcard_gender;
    }

    public void setIdcard_gender(String idcard_gender) {
        this.idcard_gender = idcard_gender;
    }

    public String getBirth_date() {
        return birth_date;
    }

    public void setBirth_date(String birth_date) {
        this.birth_date = birth_date;
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

    public String getSheng() {
        return sheng;
    }

    public void setSheng(String sheng) {
        this.sheng = sheng;
    }

    public String getShi() {
        return shi;
    }

    public void setShi(String shi) {
        this.shi = shi;
    }

    public String getXian() {
        return xian;
    }

    public void setXian(String xian) {
        this.xian = xian;
    }

    public String getXiangxi() {
        return xiangxi;
    }

    public void setXiangxi(String xiangxi) {
        this.xiangxi = xiangxi;
    }

    public String getDizhi() {
        return dizhi;
    }

    public void setDizhi(String dizhi) {
        this.dizhi = dizhi;
    }

    public String getAddtime() {
        return addtime;
    }

    public void setAddtime(String addtime) {
        this.addtime = addtime;
    }

    public String getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(String update_time) {
        this.update_time = update_time;
    }

    public String getShenhe() {
        return shenhe;
    }

    public void setShenhe(String shenhe) {
        this.shenhe = shenhe;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
