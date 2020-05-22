package com.lantian.lib_base.entity.module.response.breeds;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by Sherlock·Holmes on 2020/4/26
 */
@Entity
public class Laiyuan {

    /**
     * id : 3
     * name : 牧户
     * addtime : null
     * shenhe : 1
     * memberid : 0
     */

    @Id
    private String id;
    private String name;
    private String addtime;
    private String shenhe;
    private String memberid;

    @Generated(hash = 1783534700)
    public Laiyuan(String id, String name, String addtime, String shenhe,
            String memberid) {
        this.id = id;
        this.name = name;
        this.addtime = addtime;
        this.shenhe = shenhe;
        this.memberid = memberid;
    }

    @Generated(hash = 1537986908)
    public Laiyuan() {
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

    public String getShenhe() {
        return shenhe;
    }

    public void setShenhe(String shenhe) {
        this.shenhe = shenhe;
    }

    public String getMemberid() {
        return memberid;
    }

    public void setMemberid(String memberid) {
        this.memberid = memberid;
    }
}
