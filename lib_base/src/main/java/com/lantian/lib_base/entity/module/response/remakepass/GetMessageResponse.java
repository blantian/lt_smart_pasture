package com.lantian.lib_base.entity.module.response.remakepass;

/**
 * Created by Sherlock·Holmes on 2020-03-18
 */
public class GetMessageResponse {
    /**
     * code : 200
     * message : 发送成功!
     * data : 513521
     */

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

}
