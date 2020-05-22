package com.lantian.lib_base.entity.module.response.breeds;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by Sherlock·Holmes on 2020/4/26
 */
@Entity
public class Pinzhong {

    /**
     * id : 6
     * name : 杜泊
     * addtime : null
     * memberid : 0
     * shenhe : 1
     */

    @Id
    private String id;
    private String name;
    private String addtime;
    private String memberid;
    private String shenhe;

    @Generated(hash = 540917477)
    public Pinzhong(String id, String name, String addtime, String memberid,
            String shenhe) {
        this.id = id;
        this.name = name;
        this.addtime = addtime;
        this.memberid = memberid;
        this.shenhe = shenhe;
    }

    @Generated(hash = 2052291953)
    public Pinzhong() {
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

    public String getAddtime() {
        return addtime;
    }

    public void setAddtime(String addtime) {
        this.addtime = addtime;
    }

    public String getMemberid() {
        return memberid;
    }

    public void setMemberid(String memberid) {
        this.memberid = memberid;
    }

    public String getShenhe() {
        return shenhe;
    }

    public void setShenhe(String shenhe) {
        this.shenhe = shenhe;
    }
}
