package com.lantian.lib_base.entity.module.response.breeds;

import com.lantian.lib_base.database.greendao.ListConverter;

import org.greenrobot.greendao.annotation.Convert;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

import java.util.List;

/**
 * Created by Sherlock·Holmes on 2020/4/13
 */
@Entity
public class BreedsOfUser {
    @Id
    private String user_id;

    private int status;
    private String message;
    private int code;
    @Convert(converter = ListConverter.class,columnType = String.class)
    private List<DataBean> data;
    @Generated(hash = 1256324065)
    public BreedsOfUser(String user_id, int status, String message, int code,
            List<DataBean> data) {
        this.user_id = user_id;
        this.status = status;
        this.message = message;
        this.code = code;
        this.data = data;
    }

    @Generated(hash = 552794280)
    public BreedsOfUser() {
    }
    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public static class DataBean {
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
}
