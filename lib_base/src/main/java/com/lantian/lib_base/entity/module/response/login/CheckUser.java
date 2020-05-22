package com.lantian.lib_base.entity.module.response.login;

/**
 * Created by Sherlock·Holmes on 2020-03-27
 */
public class CheckUser {

    /**
     * code : 200
     * message : 获取成功!
     * data : 2
     */
    private String userid;
    private int code;
    private String message;
    private int data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }
}
