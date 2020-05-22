package com.lantian.lib_lan.device.model;

import java.io.Serializable;
import java.text.SimpleDateFormat;

/**
 * Created by Sherlock·Holmes on 2020/5/19
 */
public class LogBean implements Serializable {

    public String mTime;
    public String mLog;
    public String mWho;

    public LogBean(long time, String log) {
        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
        mTime = format.format(time);
        mLog = log;
    }

}
