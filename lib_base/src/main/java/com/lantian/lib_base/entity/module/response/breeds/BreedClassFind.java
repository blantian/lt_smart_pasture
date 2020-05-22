package com.lantian.lib_base.entity.module.response.breeds;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by Sherlock·Holmes on 2020-03-31
 */

@Entity
public class BreedClassFind {

    /**
     * id : 9
     * name : 绵羊
     * addtime : 0000-00-00 00:00:00
     * shenhe : 1
     * img : null
     * memberid : 0
     */
    @Id
    private String id;
    private int status;
    private String name;
    private String addtime;
    private String shenhe;
    private String img;
    private String memberid;

    @Generated(hash = 2128343899)
    public BreedClassFind(String id, int status, String name, String addtime,
            String shenhe, String img, String memberid) {
        this.id = id;
        this.status = status;
        this.name = name;
        this.addtime = addtime;
        this.shenhe = shenhe;
        this.img = img;
        this.memberid = memberid;
    }

    @Generated(hash = 1015654385)
    public BreedClassFind() {
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

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getMemberid() {
        return memberid;
    }

    public void setMemberid(String memberid) {
        this.memberid = memberid;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
