package com.lantian.lib_base.entity.module.response.regist;

/**
 * Created by Sherlock·Holmes on 2020-03-20
 */
public class CheckUsernameResponse {

    /**
     * code : 200
     * message : 用户名能用!
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
