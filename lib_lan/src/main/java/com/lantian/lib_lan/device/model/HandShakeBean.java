package com.lantian.lib_lan.device.model;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Sherlock·Holmes on 2020/5/20
 */
public class HandShakeBean extends DefaultSendBean {

    public HandShakeBean() {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("cmd", 54);
            jsonObject.put("handshake", "你连接到兰天的服务器了");
            content = jsonObject.toString();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
