package com.lantian.lib_lan.device.model;

import com.xuhao.didi.socket.common.interfaces.common_interfacies.server.IClient;

/**
 * Created by Sherlock·Holmes on 2020/5/15
 */
public class PostMessage {
    private String message;
    private int status;
    private LogBean logBean;
    private IClient iClient;

    public PostMessage(String message,int status){
        this.message = message;
        this.status= status;
    }


    public PostMessage(String message,LogBean logBean,IClient iClient){
        this.message = message;
        this.logBean = logBean;
        this.iClient = iClient;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public LogBean getLogBean() {
        return logBean;
    }

    public void setLogBean(LogBean logBean) {
        this.logBean = logBean;
    }

    public IClient getiClient() {
        return iClient;
    }

    public void setiClient(IClient iClient) {
        this.iClient = iClient;
    }
}
