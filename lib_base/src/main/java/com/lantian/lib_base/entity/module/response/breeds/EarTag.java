package com.lantian.lib_base.entity.module.response.breeds;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Index;

import java.io.Serializable;

/**
 * Created by Sherlock·Holmes on 2020-04-01
 */
@Entity(indexes = {@Index(value = "id DESC,status DESC,user_id",unique = true)})
public class EarTag implements Serializable {


    /**
     * id : 6877
     * number : 152725201900000000036702
     * type : 1
     * addtime : 2019-11-16 10:24:38
     * user_id : 29650
     * user_type : 2
     */

    private static final long serialVersionUID = 1L;
    private int status;
    @Id
    private String id;
    private String number;
    private String type;
    private String addtime;
    private String user_id;
    private String user_type;

    @Generated(hash = 551346217)
    public EarTag(int status, String id, String number, String type, String addtime,
            String user_id, String user_type) {
        this.status = status;
        this.id = id;
        this.number = number;
        this.type = type;
        this.addtime = addtime;
        this.user_id = user_id;
        this.user_type = user_type;
    }
    @Generated(hash = 63547937)
    public EarTag() {
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAddtime() {
        return addtime;
    }

    public void setAddtime(String addtime) {
        this.addtime = addtime;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getUser_type() {
        return user_type;
    }

    public void setUser_type(String user_type) {
        this.user_type = user_type;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
