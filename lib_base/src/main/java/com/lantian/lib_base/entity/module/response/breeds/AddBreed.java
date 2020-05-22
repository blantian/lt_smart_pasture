package com.lantian.lib_base.entity.module.response.breeds;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

import java.io.Serializable;

/**
 * Created by Sherlock·Holmes on 2020/4/27
 */
@Entity
public class AddBreed implements Serializable {

    /**
     * id : 134
     * user_id : 29667
     * hukou_id : 83
     * breedclass_id : 14
     * number : 50
     * acquisition_time : 2020-04-05 00:00:00
     * title : jiaqin
     * age : 22
     * become_time : 2020-05-31 00:00:00
     * become_price : 1500.00
     * price : 1500.00
     * addtime : 2020-04-27 23:04:07
     * shenhe : null
     * img : http://admin.fengtaiiot.com/uploadImage/5ea700a38b284.jpg
     * common : 0
     * mother : 0
     * sheng : 66
     * shi : 87
     * xian : 249
     * variety_id : 6
     * supplier_id : 1
     * user_idt : 0
     * birthday : 2020-04-05
     * insurance_type : 1
     * weight : 0
     * dizhi : 呼和浩特
     * type : 1
     */

    private static final long serialVersionUID = 1L;
    @Id
    private String id;
    private int status;

    private String user_id;
    private String hukou_id;
    private String breedclass_id;
    private String number;
    private String acquisition_time;
    private String title;
    private String age;
    private String become_time;
    private String become_price;
    private String price;
    private String addtime;
    private String shenhe;
    private String img;
    private String common;
    private String mother;
    private String sheng;
    private String shi;
    private String xian;
    private String variety_id;
    private String supplier_id;
    private String user_idt;
    private String birthday;
    private String insurance_type;
    private String weight;
    private String dizhi;
    private String type;

    @Generated(hash = 451711143)
    public AddBreed(String id, int status, String user_id, String hukou_id,
            String breedclass_id, String number, String acquisition_time,
            String title, String age, String become_time, String become_price,
            String price, String addtime, String shenhe, String img, String common,
            String mother, String sheng, String shi, String xian, String variety_id,
            String supplier_id, String user_idt, String birthday,
            String insurance_type, String weight, String dizhi, String type) {
        this.id = id;
        this.status = status;
        this.user_id = user_id;
        this.hukou_id = hukou_id;
        this.breedclass_id = breedclass_id;
        this.number = number;
        this.acquisition_time = acquisition_time;
        this.title = title;
        this.age = age;
        this.become_time = become_time;
        this.become_price = become_price;
        this.price = price;
        this.addtime = addtime;
        this.shenhe = shenhe;
        this.img = img;
        this.common = common;
        this.mother = mother;
        this.sheng = sheng;
        this.shi = shi;
        this.xian = xian;
        this.variety_id = variety_id;
        this.supplier_id = supplier_id;
        this.user_idt = user_idt;
        this.birthday = birthday;
        this.insurance_type = insurance_type;
        this.weight = weight;
        this.dizhi = dizhi;
        this.type = type;
    }

    @Generated(hash = 1190366764)
    public AddBreed() {
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

    public String getBreedclass_id() {
        return breedclass_id;
    }

    public void setBreedclass_id(String breedclass_id) {
        this.breedclass_id = breedclass_id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getAcquisition_time() {
        return acquisition_time;
    }

    public void setAcquisition_time(String acquisition_time) {
        this.acquisition_time = acquisition_time;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getBecome_time() {
        return become_time;
    }

    public void setBecome_time(String become_time) {
        this.become_time = become_time;
    }

    public String getBecome_price() {
        return become_price;
    }

    public void setBecome_price(String become_price) {
        this.become_price = become_price;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
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

    public String getCommon() {
        return common;
    }

    public void setCommon(String common) {
        this.common = common;
    }

    public String getMother() {
        return mother;
    }

    public void setMother(String mother) {
        this.mother = mother;
    }

    public String getSheng() {
        return sheng;
    }

    public void setSheng(String sheng) {
        this.sheng = sheng;
    }

    public String getShi() {
        return shi;
    }

    public void setShi(String shi) {
        this.shi = shi;
    }

    public String getXian() {
        return xian;
    }

    public void setXian(String xian) {
        this.xian = xian;
    }

    public String getVariety_id() {
        return variety_id;
    }

    public void setVariety_id(String variety_id) {
        this.variety_id = variety_id;
    }

    public String getSupplier_id() {
        return supplier_id;
    }

    public void setSupplier_id(String supplier_id) {
        this.supplier_id = supplier_id;
    }

    public String getUser_idt() {
        return user_idt;
    }

    public void setUser_idt(String user_idt) {
        this.user_idt = user_idt;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getInsurance_type() {
        return insurance_type;
    }

    public void setInsurance_type(String insurance_type) {
        this.insurance_type = insurance_type;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getDizhi() {
        return dizhi;
    }

    public void setDizhi(String dizhi) {
        this.dizhi = dizhi;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
