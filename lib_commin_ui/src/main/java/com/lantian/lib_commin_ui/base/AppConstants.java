package com.lantian.lib_commin_ui.base;

import android.Manifest;

/**
 * app常量类
 * Created by Sherlock·Holmes on 2020-03-17
 */
public class AppConstants {

    /**
     * 权限常量相关
     */
    public static final int WRITE_READ_EXTERNAL_CODE = 0x01;
    /**
     * 读写权限
     */
    public static final String[] WRITE_READ_EXTERNAL_PERMISSION = new String[] {
            Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE
    };

    /**
     * 相机
     */
    public static final int HARDWEAR_CAMERA_CODE = 0x02;
    public static final String[] HARDWEAR_CAMERA_PERMISSION =
            new String[] { Manifest.permission.CAMERA };
}
