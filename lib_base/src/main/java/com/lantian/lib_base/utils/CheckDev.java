package com.lantian.lib_base.utils;

import android.content.Context;
import android.content.res.Configuration;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;

/**
 * Created by Sherlock·Holmes on 2020/4/26
 */
public class CheckDev {

    /**
     * 通过尺寸判断当前设备是否为pad
     * @return
     */
    public static boolean isPad() {
        WindowManager wm = (WindowManager) BaseUtils.getContext().getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        // 屏幕宽度  
        float screenWidth = display.getWidth();
        // 屏幕高度  
        float screenHeight = display.getHeight();
        DisplayMetrics dm = new DisplayMetrics();
        display.getMetrics(dm);
        double x = Math.pow(dm.widthPixels / dm.xdpi, 2);
        double y = Math.pow(dm.heightPixels / dm.ydpi, 2);
        // 屏幕尺寸  
        double screenInches = Math.sqrt(x + y);
        // 大于6尺寸则为Pad  
        if (screenInches >= 6.0) {
            return true;
        }
        return false;
    }

    /**
     * 判断当前设备是手机还是平板，代码来自 Google I/O App for Android
     *
     * @param context
     * @return 平板返回 True，手机返回 False
     */
    public static boolean isPad(Context context) {
        return (context.getResources().getConfiguration().screenLayout
                & Configuration.SCREENLAYOUT_SIZE_MASK)
                >= Configuration.SCREENLAYOUT_SIZE_LARGE;
    }

        }
