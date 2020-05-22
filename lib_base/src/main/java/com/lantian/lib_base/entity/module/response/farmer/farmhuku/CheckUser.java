package com.lantian.lib_base.entity.module.response.farmer.farmhuku;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by Sherlock·Holmes on 2020/4/27
 */
@Entity
public class CheckUser {

    @Id
    private String user_id;
    private String have;

    @Generated(hash = 2111315416)
    public CheckUser(String user_id, String have) {
        this.user_id = user_id;
        this.have = have;
    }

    @Generated(hash = 1065819587)
    public CheckUser() {
    }

    public String getHave() {
        return have;
    }

    public void setHave(String have) {
        this.have = have;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }
}
