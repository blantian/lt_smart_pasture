package com.lantian.lib_lan.camera.control;

import android.util.Log;

import com.hikvision.netsdk.HCNetSDK;
import com.hikvision.netsdk.NET_DVR_DEVICEINFO_V30;
import com.lantian.lib_lan.camera.netsdk.jna.HCNetSDKByJNA;
import com.lantian.lib_lan.camera.netsdk.jna.HCNetSDKJNAInstance;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.UUID;
import java.util.regex.Pattern;

/**
 * Created by Sherlock·Holmes on 2020-03-13
 */
public class DevManageGuider implements Serializable {

    /**端口范围常量**/
    private static Pattern PATTERN = Pattern.compile("[1-9][0-9]*");

    /**
     * @brief 设备状态对象
     */
    public class DeviceState implements Serializable {
        public int m_iLogState = 0; // 0-offline, 1-online, 2-dropoff
        public int m_iAlarmState = 0; // 0-alarmclosed, 1-alarmopen

        public void reset(){
            m_iLogState = 0;
            m_iAlarmState = 0;
        }
    }


    /**
     * 添加设备
     */
    public class DevNetInfo implements Serializable {
        public String m_szIp;
        public String m_szPort;
        public String m_szUserName;
        public String m_szPassword;

        public DevNetInfo(){}

        /**
         * 初始化设备信息
         * @param szIp
         * @param szPort
         * @param szUserName
         * @param szPassWorld
         */
        public DevNetInfo(String szIp, String szPort, String szUserName, String szPassWorld){
            m_szIp = szIp;
            m_szPort = szPort;
            m_szUserName = szUserName;
            m_szPassword = szPassWorld;
        }

        /**
         * 判断IP格式和范围
         */
        public boolean checkIp(){
            if(m_szIp.length() < 7 || m_szIp.length() > 15 || "".equals(m_szIp))
            {
                return false;
            }
            if (m_szIp != null && !m_szIp.isEmpty()) {
                // 定义正则表达式
                String regex = "^(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|[1-9])\\.(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\.(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\.(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)$";
                // 判断ip地址是否与正则表达式匹配
                return m_szIp.matches(regex);
            }
            return false;
        }

        /**
         * 判断端口格式和范围
         * @return
         */
        public boolean checkPort() {
            return PATTERN.matcher(m_szPort).matches();
        }

        public boolean checkNetInfo(){
            return checkIp()&&checkPort()&&!m_szUserName.isEmpty()&&!m_szPassword.isEmpty();
        }
    }


    /**
     * @brief 设备信息对象
     */
    public class DeviceItem implements Serializable {

        public String m_szUUID;
        public String m_szDevName;
        public int m_lUserID = -1;
        public byte m_byLoginFlag = -1; // 设备登录方式，0-jni, 1-jna
        public DeviceState m_struDevState = new DeviceState();
        public DevNetInfo m_struNetInfo;
        public HCNetSDKByJNA.NET_DVR_DEVICEINFO_V40 m_struDeviceInfoV40_jna;
        public NET_DVR_DEVICEINFO_V30 m_struDeviceInfoV30_jni;

        public DeviceItem() {
            m_szUUID = UUID.randomUUID().toString();
        }
        public DeviceItem(String szUUID) {
            m_szUUID = szUUID;
        }
    }

    private ArrayList<DeviceItem> mAlDevList = new ArrayList<DeviceItem>();
    private int mICurrSelectDevIndex = -1;

    /**
     * @fn getCurrSelectDev
     * @return [DeviceItem] 设备信息对象
     * @brief 获取当前选中设备的信息对象
     */
    public DeviceItem getCurrSelectDev(){
        if(mICurrSelectDevIndex < 0 || mICurrSelectDevIndex >= mAlDevList.size()){
            return null;
        }
        return mAlDevList.get(mICurrSelectDevIndex);
    }

    /**
     * @fn getDevList
     * @return [ArrayList<DeviceItem>] 设备列表
     * @brief 获取设备列表
     */
    public ArrayList<DeviceItem> getDevList(){
        return mAlDevList;
    }

    /**
     * @fn setCurrSelectDevIndex
     * @param iCurrSelectDevIndex
     * @return None.
     * @brief 设置当前选中设备的列表序号.
     */
    public void setCurrSelectDevIndex(int iCurrSelectDevIndex){
        mICurrSelectDevIndex = iCurrSelectDevIndex;
    }

    /**
     * @fn getCurrSelectDevIndex
     * @return [int] 设备索引号
     * @brief 获取当前选中的设备在设备列表中的索引号.
     */
    public int getCurrSelectDevIndex(){
        return mICurrSelectDevIndex;
    }


    /**
     * setDevList
     * @param alDevList
     */
    public void setDevList(ArrayList<DeviceItem> alDevList){
        mAlDevList = alDevList;
    }


    /**
     * @fn login_v40_jna_with_index
     * @param iDevIndex 设备列表中的索引号
     * @return 登陆成功返回true,否则false
     * @brief 当设备已经添加到设备列表之后，可以通过在列表中的索引号进行登陆
     */
    public boolean login_v40_jna_with_index(int iDevIndex){
        // 验证参数有效性
        if(iDevIndex<0||iDevIndex>= mAlDevList.size()) {
            Log.w("[NetSDKSimpleDemo]", "logout_jna failed with error param");
            return false;
        }
        DeviceItem devItem = mAlDevList.get(iDevIndex);
        if(devItem.m_struDevState.m_iLogState == 1){
            return true;
        }
        // call hcnetsdk jna login API.
        HCNetSDKByJNA.NET_DVR_USER_LOGIN_INFO loginInfo = new HCNetSDKByJNA.NET_DVR_USER_LOGIN_INFO();
        System.arraycopy(devItem.m_struNetInfo.m_szIp.getBytes(), 0, loginInfo.sDeviceAddress, 0, devItem.m_struNetInfo.m_szIp.length());
        System.arraycopy(devItem.m_struNetInfo.m_szUserName.getBytes(), 0, loginInfo.sUserName, 0, devItem.m_struNetInfo.m_szUserName.length());
        System.arraycopy(devItem.m_struNetInfo.m_szPassword.getBytes(), 0, loginInfo.sPassword, 0, devItem.m_struNetInfo.m_szPassword.length());
        loginInfo.wPort = (short)Integer.parseInt(devItem.m_struNetInfo.m_szPort);

        HCNetSDKByJNA.NET_DVR_DEVICEINFO_V40 deviceInfo = new HCNetSDKByJNA.NET_DVR_DEVICEINFO_V40();
        loginInfo.write();
        int lUserID = HCNetSDKJNAInstance.getInstance().NET_DVR_Login_V40(loginInfo.getPointer(), deviceInfo.getPointer());
        if(lUserID < 0)
        {
            Log.e("[NetSDKSimpleDemo]","NET_DVR_Login_V40 failed with:" + HCNetSDKJNAInstance.getInstance().NET_DVR_GetLastError());
            return false;
        }

        // add a DeviceItem to device list.
        deviceInfo.read();
        devItem.m_lUserID = lUserID;
        devItem.m_struDevState.m_iLogState = 1;
        devItem.m_struDeviceInfoV40_jna = deviceInfo;
        Log.i("[NetSDKSimpleDemo]","NET_DVR_Login_V40 succ with:" + lUserID);
        return true;
    }


    /**
     *
     * @param szDevName 设备名称
     * @param struDevNetInfo 设备登录的网络参数
     * @return 登陆成功返回true,否则false
     */
    public boolean login_v40_jna(String szDevName, DevNetInfo struDevNetInfo){
        // 验证参数有效性
        if(!struDevNetInfo.checkIp() || !struDevNetInfo.checkPort() || struDevNetInfo.m_szUserName.isEmpty() || struDevNetInfo.m_szPassword.isEmpty()){
            System.out.println("login_v40_jna failed with error param");
            return false;
        }

        // call hcnetsdk jna login API.
        HCNetSDKByJNA.NET_DVR_USER_LOGIN_INFO loginInfo = new HCNetSDKByJNA.NET_DVR_USER_LOGIN_INFO();
        System.arraycopy(struDevNetInfo.m_szIp.getBytes(), 0, loginInfo.sDeviceAddress, 0, struDevNetInfo.m_szIp.length());
        System.arraycopy(struDevNetInfo.m_szUserName.getBytes(), 0, loginInfo.sUserName, 0, struDevNetInfo.m_szUserName.length());
        System.arraycopy(struDevNetInfo.m_szPassword.getBytes(), 0, loginInfo.sPassword, 0, struDevNetInfo.m_szPassword.length());
        loginInfo.wPort = (short)Integer.parseInt(struDevNetInfo.m_szPort);
        HCNetSDKByJNA.NET_DVR_DEVICEINFO_V40 deviceInfo = new HCNetSDKByJNA.NET_DVR_DEVICEINFO_V40();
        loginInfo.write();
        int lUserID = HCNetSDKJNAInstance.getInstance().NET_DVR_Login_V40(loginInfo.getPointer(), deviceInfo.getPointer());
        if(lUserID < 0)
        {
            Log.e("[NetSDKSimpleDemo]","NET_DVR_Login_V40 failed with:" + HCNetSDKJNAInstance.getInstance().NET_DVR_GetLastError());
            return false;
        }

        // add a DeviceItem to device list.
        deviceInfo.read();
        DeviceItem devItem = new DeviceItem();
        devItem.m_byLoginFlag = 1;
        devItem.m_lUserID = lUserID;
        if (szDevName.isEmpty()){
            devItem.m_szDevName = struDevNetInfo.m_szIp;
        }
        devItem.m_struDevState.m_iLogState = 1;
        devItem.m_struNetInfo = struDevNetInfo;
        devItem.m_struDeviceInfoV40_jna = deviceInfo;
        mAlDevList.add(devItem);
        Log.i("[NetSDKSimpleDemo]","NET_DVR_Login_V40 succ with:" + lUserID);
        return true;
    }

    public boolean logout_jni(int iDevIndex) {
        if(iDevIndex<0||iDevIndex>= mAlDevList.size()) {
            Log.w("[NetSDKSimpleDemo]", "logout_jna failed with error param");
            return false;
        }
        DeviceItem devItem = mAlDevList.get(iDevIndex);
        boolean ret = HCNetSDK.getInstance().NET_DVR_Logout_V30(devItem.m_lUserID);
        if (!ret){
            Log.e("[NetSDKSimpleDemo]","NET_DVR_Logout failed with:" + HCNetSDKJNAInstance.getInstance().NET_DVR_GetLastError());
        }

        devItem.m_struDevState.reset();
        devItem.m_struDeviceInfoV30_jni = null;
        devItem.m_struDeviceInfoV40_jna = null;
        return true;
    }
}
