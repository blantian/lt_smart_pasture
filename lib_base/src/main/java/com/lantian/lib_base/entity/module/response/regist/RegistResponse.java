package com.lantian.lib_base.entity.module.response.regist;

/**
 * Created by Sherlock·Holmes on 2020-03-17
 */

public class RegistResponse {

    /**
     * code : 400
     * message : 用户名已存在!
     * data : null
     */
    private int code;
    private String message;
    private Object data;

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

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
