package com.lantian.lib_lan.camera.netsdk.jna;

import com.sun.jna.Native;

/**
 * Created by Sherlock·Holmes on 2020-03-13
 */
public enum HCNetSDKJNAInstance {

    /**
     * classs
     */
    CLASS;
    private static HCNetSDKByJNA netSdk = null;
    /**
     * get the instance of HCNetSDK
     * @return the instance of HCNetSDK
     */
    public static HCNetSDKByJNA getInstance()
    {
        if (null == netSdk)
        {
            synchronized (HCNetSDKByJNA.class)
            {
                netSdk = (HCNetSDKByJNA) Native.loadLibrary("hcnetsdk",
                        HCNetSDKByJNA.class);
            }
        }
        return netSdk;
    }
}
