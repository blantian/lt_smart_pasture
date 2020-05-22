package com.lantian.lib_base.utils;

/**
 * Created by Sherlock·Holmes on 2020/4/10
 */
public class EventMessage {

    private int msg;
    private String message;
    private boolean updataCom;

    public EventMessage(int msg,String message){
        this.msg = msg;
        this.message = message;
    }

    public EventMessage(boolean updataCom){
        this.updataCom = updataCom;
    }
    public int getMsg() {
        return msg;
    }

    public void setMsg(int msg) {
        this.msg = msg;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isUpdataCom() {
        return updataCom;
    }

    public void setUpdataCom(boolean updataCom) {
        this.updataCom = updataCom;
    }
}
