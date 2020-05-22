package com.lantian.lib_base.entity.module.response.breeds;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Index;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by Sherlock·Holmes on 2020/4/10
 */
@Entity(indexes = {@Index(value = "id DESC,status DESC,user_id",unique = true)})
public class addEarTag {

    /**
     * id : 74239
     * breed_id : 82
     * eartag_id : 7108
     * addtime : 2019-11-16 20:11:37
     * type : 1
     * pid : 0
     * weight : 123
     * age : 13
     * birthday : null
     * type_in : 1
     * time : 2019-11-30 00:00:00
     * survival : null
     * img : http://admin.fengtaiiot.com/uploadImage/5e9023993bf10.jpg
     * user_id : 29579
     * qrcode : BXR2019111653511015.png
     * length :
     * update_userid : 29547
     * edittime : 2020-04-10 15:43:35
     * hah : 1
     * breeds_id : null
     * number : 0
     * sretype : 0
     * sretypein : 0
     * userbr_id : 0
     */

    private  int status;
    @Id
    private String id;

    private String breed_id;
    private String eartag_id;
    private String addtime;
    private String type;
    private String pid;
    private String weight;
    private String age;
    private String birthday;
    private String type_in;
    private String time;
    private String survival;
    private String img;
    private String user_id;
    private String qrcode;
    private String length;
    private String update_userid;
    private String edittime;
    private String hah;
    private String breeds_id;
    private String number;
    private String sretype;
    private String sretypein;
    private String userbr_id;

    @Generated(hash = 1603164604)
    public addEarTag(int status, String id, String breed_id, String eartag_id,
            String addtime, String type, String pid, String weight, String age,
            String birthday, String type_in, String time, String survival,
            String img, String user_id, String qrcode, String length,
            String update_userid, String edittime, String hah, String breeds_id,
            String number, String sretype, String sretypein, String userbr_id) {
        this.status = status;
        this.id = id;
        this.breed_id = breed_id;
        this.eartag_id = eartag_id;
        this.addtime = addtime;
        this.type = type;
        this.pid = pid;
        this.weight = weight;
        this.age = age;
        this.birthday = birthday;
        this.type_in = type_in;
        this.time = time;
        this.survival = survival;
        this.img = img;
        this.user_id = user_id;
        this.qrcode = qrcode;
        this.length = length;
        this.update_userid = update_userid;
        this.edittime = edittime;
        this.hah = hah;
        this.breeds_id = breeds_id;
        this.number = number;
        this.sretype = sretype;
        this.sretypein = sretypein;
        this.userbr_id = userbr_id;
    }

    @Generated(hash = 5131744)
    public addEarTag() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBreed_id() {
        return breed_id;
    }

    public void setBreed_id(String breed_id) {
        this.breed_id = breed_id;
    }

    public String getEartag_id() {
        return eartag_id;
    }

    public void setEartag_id(String eartag_id) {
        this.eartag_id = eartag_id;
    }

    public String getAddtime() {
        return addtime;
    }

    public void setAddtime(String addtime) {
        this.addtime = addtime;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getType_in() {
        return type_in;
    }

    public void setType_in(String type_in) {
        this.type_in = type_in;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getSurvival() {
        return survival;
    }

    public void setSurvival(String survival) {
        this.survival = survival;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getQrcode() {
        return qrcode;
    }

    public void setQrcode(String qrcode) {
        this.qrcode = qrcode;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public String getUpdate_userid() {
        return update_userid;
    }

    public void setUpdate_userid(String update_userid) {
        this.update_userid = update_userid;
    }

    public String getEdittime() {
        return edittime;
    }

    public void setEdittime(String edittime) {
        this.edittime = edittime;
    }

    public String getHah() {
        return hah;
    }

    public void setHah(String hah) {
        this.hah = hah;
    }

    public String getBreeds_id() {
        return breeds_id;
    }

    public void setBreeds_id(String breeds_id) {
        this.breeds_id = breeds_id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getSretype() {
        return sretype;
    }

    public void setSretype(String sretype) {
        this.sretype = sretype;
    }

    public String getSretypein() {
        return sretypein;
    }

    public void setSretypein(String sretypein) {
        this.sretypein = sretypein;
    }

    public String getUserbr_id() {
        return userbr_id;
    }

    public void setUserbr_id(String userbr_id) {
        this.userbr_id = userbr_id;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
