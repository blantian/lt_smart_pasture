package com.lantian.lib_base.entity.module.response.breeds;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Index;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by Sherlock·Holmes on 2020-04-02
 */

@Entity(indexes = {@Index(value = "id DESC,status DESC,user_id DESC",unique = true)})
public class CountCompensate {

    /**
     * id : 5
     * user_id : 29480
     * hukou_id : 4
     * title : 创业扶持
     * price : 10000.00
     * addtime : 2019-04-03 00:00:00
     * shenhe : null
     */


    private int status;
    private String id;
    @Id
    private String user_id;
    private String hukou_id;
    private String title;
    private String price;
    private String addtime;
    private String shenhe;

    @Generated(hash = 1190657361)
    public CountCompensate(int status, String id, String user_id, String hukou_id,
            String title, String price, String addtime, String shenhe) {
        this.status = status;
        this.id = id;
        this.user_id = user_id;
        this.hukou_id = hukou_id;
        this.title = title;
        this.price = price;
        this.addtime = addtime;
        this.shenhe = shenhe;
    }

    @Generated(hash = 99236961)
    public CountCompensate() {
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
