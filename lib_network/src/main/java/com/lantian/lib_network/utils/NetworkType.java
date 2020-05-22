package com.lantian.lib_network.utils;

/**
 * Created by Sherlock·Holmes on 2020/4/20
 */
public enum NetworkType {

    NETWORK_WIFI("WiFi"),
    NETWORK_LAN("LAN"),
    NETWORK_4G("4G"),
    NETWORK_2G("2G"),
    NETWORK_3G("3G"),
    NETWORK_UNKNOWN("Unknown"),
    NETWORK_NO("No network");

    private String desc;
    NetworkType(String desc) {
        this.desc = desc;
    }
    @Override
    public String toString() {
        return desc;
    }
}
