package com.lantian.lib_base.entity.module.response.breeds;

import java.io.Serializable;

/**
 * Created by Sherlock·Holmes on 2020-04-01
 */
public class EarTag implements Serializable {

    /**
     * id : 6877
     * number : 152725201900000000036702
     * type : 1
     * addtime : 2019-11-16 10:24:38
     * user_id : 29650
     * user_type : 2
     */

    private String id;
    private String number;
    private String type;
    private String addtime;
    private String user_id;
    private String user_type;

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
}
