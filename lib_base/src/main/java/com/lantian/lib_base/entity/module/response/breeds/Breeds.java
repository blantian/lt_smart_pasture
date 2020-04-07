package com.lantian.lib_base.entity.module.response.breeds;

import android.os.Parcel;

/**
 * Created by Sherlock·Holmes on 2020-03-31
 */
public class Breeds {

    /**
     * id : 14
     * name : 家禽
     * addtime : 0000-00-00 00:00:00
     * shenhe : 1
     * img : null
     * memberid : 0
     * count : null
     */

    private String id;
    private String name;
    private String addtime;
    private String shenhe;
    private String img;
    private String memberid;
    private String count;

    protected Breeds(Parcel in) {
        id = in.readString();
        name = in.readString();
        addtime = in.readString();
        shenhe = in.readString();
        memberid = in.readString();
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

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }
}
