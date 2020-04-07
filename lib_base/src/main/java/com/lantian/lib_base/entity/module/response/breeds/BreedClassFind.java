package com.lantian.lib_base.entity.module.response.breeds;

/**
 * Created by Sherlock·Holmes on 2020-03-31
 */
public class BreedClassFind {

    /**
     * id : 9
     * name : 绵羊
     * addtime : 0000-00-00 00:00:00
     * shenhe : 1
     * img : null
     * memberid : 0
     */

    private String id;
    private String name;
    private String addtime;
    private String shenhe;
    private Object img;
    private String memberid;

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

    public Object getImg() {
        return img;
    }

    public void setImg(Object img) {
        this.img = img;
    }

    public String getMemberid() {
        return memberid;
    }

    public void setMemberid(String memberid) {
        this.memberid = memberid;
    }
}
