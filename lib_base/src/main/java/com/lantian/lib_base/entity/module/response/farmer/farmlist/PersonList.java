package com.lantian.lib_base.entity.module.response.farmer.farmlist;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

import java.io.Serializable;

/**
 * Created by Sherlock·Holmes on 2020/4/22
 */
@Entity
public class PersonList implements Serializable {

    /**
     * id : 37
     * user_id : 29579
     * hukou_id : 42
     * img : http://admin.fengtaiiot.com/uploadImage/5dd956b36ab18.png
     * call : 兔
     * name : 狼
     * per_relations : 太太太太爷爷
     * tel : 15647131997
     * labour_type : 2
     * addtime : 2019-11-24 00:11:52
     * shenhe : null
     * age : 0
     */
    private static final long serialVersionUID = 1L;
    @Id
    private String id;
    private int status;

    private String user_id;
    private String hukou_id;
    private String img;
    private String call;
    private String name;
    private String per_relations;
    private String tel;
    private String labour_type;
    private String addtime;
    private String shenhe;
    private String age;

    @Generated(hash = 1783123313)
    public PersonList(String id, int status, String user_id, String hukou_id,
            String img, String call, String name, String per_relations, String tel,
            String labour_type, String addtime, String shenhe, String age) {
        this.id = id;
        this.status = status;
        this.user_id = user_id;
        this.hukou_id = hukou_id;
        this.img = img;
        this.call = call;
        this.name = name;
        this.per_relations = per_relations;
        this.tel = tel;
        this.labour_type = labour_type;
        this.addtime = addtime;
        this.shenhe = shenhe;
        this.age = age;
    }

    @Generated(hash = 644400090)
    public PersonList() {
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

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getCall() {
        return call;
    }

    public void setCall(String call) {
        this.call = call;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPer_relations() {
        return per_relations;
    }

    public void setPer_relations(String per_relations) {
        this.per_relations = per_relations;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getLabour_type() {
        return labour_type;
    }

    public void setLabour_type(String labour_type) {
        this.labour_type = labour_type;
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

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
