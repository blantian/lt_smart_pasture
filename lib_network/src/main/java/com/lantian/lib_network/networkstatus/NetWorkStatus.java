package com.lantian.lib_network.networkstatus;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;
import android.util.Log;
import com.lantian.lib_network.common.NetConstans;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * 手机连接的网络类型
 * Created by Sherlock·Holmes on 2020-03-17
 */
public class NetWorkStatus {

    /**
     *判断网络综合评测
     * @param context
     * @return { 0x01：无服务、1：WI-FI、2：2G、3：3G、4：4G}
     */
    public static int isNetWorkStatus(Context context) {
        int accessNetStatus = NetConstans.NETWORK_CLASS_UNKNOWN;
        Runtime runtime = Runtime.getRuntime();
        Process ipProcess = null;
        try {
            ipProcess = runtime.exec("ping -c 1 61.135.169.125");
            InputStream input = ipProcess.getInputStream();
            BufferedReader in = new BufferedReader(new InputStreamReader(input));
            StringBuffer stringBuffer = new StringBuffer();
            String content = "";
            while ((content = in.readLine()) != null) {
                stringBuffer.append(content);
            }
            Log.e("------ping-----", "result content : " + stringBuffer.toString());
            int exitValue = ipProcess.waitFor();
            if (exitValue == 0) {
                /**
                 * 网络正常
                 * 判断是否4G或者WI-FI
                 * */
                accessNetStatus = getNetWorkStatus(context);
                Log.e("NetType",String.valueOf(accessNetStatus));
            } else {
                accessNetStatus = NetConstans.ACCESS_NET_FAIL;
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        } finally {
            if (ipProcess != null) {
                ipProcess.destroy();
                Log.e("----result---", "result = " + accessNetStatus);
            }
            runtime.gc();
        }
        return accessNetStatus;
    }


    /**
     * 判断是否是 WI-FI 或者4G
     * @param context
     * @return
     */
    public static int getNetWorkStatus(Context context) {
        int netWorkType = NetConstans.NETWORK_CLASS_UNKNOWN;
        ConnectivityManager connectivityManager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            int type = networkInfo.getType();
            if (type == ConnectivityManager.TYPE_WIFI) {

                netWorkType = NetConstans.NETWORK_WIFI;
            } else if (type == ConnectivityManager.TYPE_MOBILE) {
                netWorkType = getNetWorkClass(context);
            }
        }

        return netWorkType;
    }

    /**
     * 判断是4G/3G/2G
     * @param context
     * @return
     */
    public static int getNetWorkClass(Context context) {

        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        /**
         * 判断网络类型
         */
        switch (telephonyManager.getNetworkType()) {
            case TelephonyManager.NETWORK_TYPE_GPRS:
            case TelephonyManager.NETWORK_TYPE_EDGE:
            case TelephonyManager.NETWORK_TYPE_CDMA:
            case TelephonyManager.NETWORK_TYPE_1xRTT:
            case TelephonyManager.NETWORK_TYPE_IDEN:
                return NetConstans.NETWORK_CLASS_2_G;

            case TelephonyManager.NETWORK_TYPE_UMTS:
            case TelephonyManager.NETWORK_TYPE_EVDO_0:
            case TelephonyManager.NETWORK_TYPE_EVDO_A:
            case TelephonyManager.NETWORK_TYPE_HSDPA:
            case TelephonyManager.NETWORK_TYPE_HSUPA:
            case TelephonyManager.NETWORK_TYPE_HSPA:
            case TelephonyManager.NETWORK_TYPE_EVDO_B:
            case TelephonyManager.NETWORK_TYPE_EHRPD:
            case TelephonyManager.NETWORK_TYPE_HSPAP:
                return NetConstans.NETWORK_CLASS_3_G;
            case TelephonyManager.NETWORK_TYPE_LTE:
                return NetConstans.NETWORK_CLASS_4_G;

            default:
                return NetConstans.NETWORK_CLASS_UNKNOWN;
        }
    }

    /**
     * 判断当前有没有联网状态
     * @param context
     * @return
     */
    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivity = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity == null) {
            return false;
        } else {
            NetworkInfo[] info = connectivity.getAllNetworkInfo();
            if (info != null) {
                for (int i = 0; i < info.length; i++) {
                    if (info[i].getState() == NetworkInfo.State.CONNECTED
                            || info[i].getState() == NetworkInfo.State.CONNECTING) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

}
