package com.lantian.lib_base.entity.module.response.breeds;

/**
 * Created by Sherlock·Holmes on 2020/4/6
 */
public class pid {

    /**
     * id : 72018
     * breed_id : 63
     * eartag_id : 3548
     * addtime : 2019-11-06 12:11:39
     * type : 2
     * pid : 0
     * weight : 34
     * age : 309
     * type_in : 3
     * time : 2019-10-17 00:00:00
     * img : http://admin.fengtaiiot.com/uploadImage/5dc24ce4d1074.jpg
     * user_id : 29542
     * qrcode : BXR2019110655549850.png
     * length :
     * update_userid : 29547
     * edittime : 2019-11-06 12:32:40
     * hah : 1
     * number : 0
     * sretype : 0
     * sretypein : 0
     * userbr_id : 0
     * eartag : {"id":"3548","number":"152725201900000000008482","type":"2","addtime":"2019-10-30 19:25:45","user_id":"29542","user_type":"2"}
     */

    private String id;
    private String breed_id;
    private String eartag_id;
    private String addtime;
    private String type;
    private String pid;
    private String weight;
    private String age;
    private String type_in;
    private String time;
    private String img;
    private String user_id;
    private String qrcode;
    private String length;
    private String update_userid;
    private String edittime;
    private String hah;
    private String number;
    private String sretype;
    private String sretypein;
    private String userbr_id;
    private EartagBean eartag;

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

    public EartagBean getEartag() {
        return eartag;
    }

    public void setEartag(EartagBean eartag) {
        this.eartag = eartag;
    }

    public static class EartagBean {
        /**
         * id : 3548
         * number : 152725201900000000008482
         * type : 2
         * addtime : 2019-10-30 19:25:45
         * user_id : 29542
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
}
