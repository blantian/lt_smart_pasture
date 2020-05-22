package com.lantian.lib_base.entity.module.response.farmer.plan;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;

import java.io.Serializable;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by Sherlock·Holmes on 2020/4/22
 */
@Entity
public class CaoyuanList implements Serializable {
    /**
     * id : 42
     * user_id : 29579
     * hukou_id : 42
     * area : 150
     * area_type : 2
     * name : 呼伦贝尔
     * addtime : 2019-12-20 23:12:47
     * shenhe : null
     * ste_type : 1
     * farmid : null
     * ali_type : 1
     */

    private static final long serialVersionUID = 1L;
    @Id
    private String id;
    private int status;

    private String user_id;
    private String hukou_id;
    private String area;
    private String area_type;
    private String name;
    private String addtime;
    private String shenhe;
    private String ste_type;
    private String farmid;
    private String ali_type;

    @Generated(hash = 1891290162)
    public CaoyuanList(String id, int status, String user_id, String hukou_id,
            String area, String area_type, String name, String addtime,
            String shenhe, String ste_type, String farmid, String ali_type) {
        this.id = id;
        this.status = status;
        this.user_id = user_id;
        this.hukou_id = hukou_id;
        this.area = area;
        this.area_type = area_type;
        this.name = name;
        this.addtime = addtime;
        this.shenhe = shenhe;
        this.ste_type = ste_type;
        this.farmid = farmid;
        this.ali_type = ali_type;
    }

    @Generated(hash = 1129207079)
    public CaoyuanList() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getHukou_id() {
        return hukou_id;
    }

    public void setHukou_id(String hukou_id) {
        this.hukou_id = hukou_id;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getArea_type() {
        return area_type;
    }

    public void setArea_type(String area_type) {
        this.area_type = area_type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddtime() {
        return addtime;
    }

    public void setAddtime(String addtime) {
        this.addtime = addtime;
    }

    public String getShenhe() {
        return shenhe;
    }

    public void setShenhe(String shenhe) {
        this.shenhe = shenhe;
    }

    public String getSte_type() {
        return ste_type;
    }

    public void setSte_type(String ste_type) {
        this.ste_type = ste_type;
    }

    public String getFarmid() {
        return farmid;
    }

    public void setFarmid(String farmid) {
        this.farmid = farmid;
    }

    public String getAli_type() {
        return ali_type;
    }

    public void setAli_type(String ali_type) {
        this.ali_type = ali_type;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
