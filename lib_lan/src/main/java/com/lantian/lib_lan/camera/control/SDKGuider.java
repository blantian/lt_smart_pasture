package com.lantian.lib_lan.camera.control;

import android.util.Log;

import com.hikvision.netsdk.HCNetSDK;
import com.lantian.lib_lan.camera.netsdk.jna.HCNetSDKByJNA;
import com.lantian.lib_lan.camera.netsdk.jna.HCNetSDKJNAInstance;
import com.sun.jna.Pointer;


/**
 * Created by Sherlock·Holmes on 2020-03-13
 * 初始化SDK
 */
public class SDKGuider {

    static public SDKGuider sdkGuider = new SDKGuider();

    /**设备管理接口**/
    public DevManageGuider manageGuider = new DevManageGuider();

    /**设备预览接口**/
    public DevPreviewGuider m_comPreviewGuider = new DevPreviewGuider();

    public SDKGuider(){
        initNetSdk_jni();
    }

    @Override
    public void finalize()
    {
        cleanupNetSdk_jni();
    }

    /**
     * @fn GetLastError_jni
     * @return 返回错误码
     * @brief 获取SDK错误码
     */
    public int GetLastError_jni()
    {
        return HCNetSDK.getInstance().NET_DVR_GetLastError();
    }

    /**
     * @fn initNetSdk_jni
     * @return 成功初始化NetSDK,返回True,否则False
     * @brief NetSDK初始化.
     */
    private boolean initNetSdk_jni() {
        // init net sdk
        if (!HCNetSDK.getInstance().NET_DVR_Init())
        {
            Log.e("[NetSDKSimpleDemo]", "HCNetSDK init is failed!");
            return false;
        }
        HCNetSDK.getInstance().NET_DVR_SetLogToFile(3, "/mnt/sdcard/sdklog/", true);


        int dwType = HCNetSDKByJNA.NET_SDK_LOCAL_CFG_TYPE.NET_DVR_LOCAL_CFG_TYPE_GENERAL;
        HCNetSDKByJNA.NET_DVR_LOCAL_GENERAL_CFG struGeneralCfg = new HCNetSDKByJNA.NET_DVR_LOCAL_GENERAL_CFG();
        Pointer lpInBuff = struGeneralCfg.getPointer();
        struGeneralCfg.byAlarmJsonPictureSeparate = 1;
        struGeneralCfg.write();
        boolean bRet = HCNetSDKJNAInstance.getInstance().NET_DVR_SetSDKLocalCfg(dwType, lpInBuff);
        if(!bRet)
        {
            System.out.println("NET_DVR_SetSDKLocalCfg(NET_SDK_LOCAL_CFG_TYPE_CHECK_DEV) failed:" + HCNetSDKJNAInstance.getInstance().NET_DVR_GetLastError());
        }
        else
        {
            System.out.println("NET_DVR_SetSDKLocalCfg(NET_SDK_LOCAL_CFG_TYPE_CHECK_DEV) succ!");
        }
        return true;
    }


    /**
     * @fn cleanupNetSdk_jni
     * @return 成功返回True,否则False
     * @brief NetSDK反初始化.
     */
    private boolean cleanupNetSdk_jni()
    {
        // init net sdk
        if (!HCNetSDK.getInstance().NET_DVR_Cleanup())
        {
            Log.e("[NetSDKSimpleDemo]", "HCNetSDK cleanup is failed!");
            return false;
        }
        return true;
    }
}
