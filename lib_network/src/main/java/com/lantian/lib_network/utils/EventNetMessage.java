package com.lantian.lib_network.utils;

/**
 * Created by Sherlock·Holmes on 2020/5/8
 */
public class EventNetMessage {

    private int msg;
    private NetworkType networkType;

    public EventNetMessage(int type,NetworkType networkType){
        this.msg = type;
        this.networkType= networkType;
    }

    public int getMsg() {
        return msg;
    }

    public void setMsg(int msg) {
        this.msg = msg;
    }

    public NetworkType getNetworkType() {
        return networkType;
    }

    public void setNetworkType(NetworkType networkType) {
        this.networkType = networkType;
    }
}
