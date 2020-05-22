package com.lantian.lib_base.entity.module.response.farmer.butie;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;

import java.io.Serializable;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by Sherlock·Holmes on 2020/4/22
 */
@Entity
public class ButieList implements Serializable {

    /**
     * id : 33
     * user_id : 29579
     * hukou_id : 42
     * title : 白云基金
     * price : 1500.00
     * addtime : 2019-12-21 00:00:00
     * shenhe : null
     */

    private static final long serialVersionUID = 1L;
    @Id
    private String id;
    private int status;

    private String user_id;
    private String hukou_id;
    private String title;
    private String price;
    private String addtime;
    private String shenhe;

    @Generated(hash = 1678789452)
    public ButieList(String id, int status, String user_id, String hukou_id,
            String title, String price, String addtime, String shenhe) {
        this.id = id;
        this.status = status;
        this.user_id = user_id;
        this.hukou_id = hukou_id;
        this.title = title;
        this.price = price;
        this.addtime = addtime;
        this.shenhe = shenhe;
    }

    @Generated(hash = 534251152)
    public ButieList() {
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
