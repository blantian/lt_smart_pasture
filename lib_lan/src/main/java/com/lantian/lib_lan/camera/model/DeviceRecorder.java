package com.lantian.lib_lan.camera.model;

import android.app.Activity;
import android.content.res.XmlResourceParser;

import com.lantian.lib_lan.R;
import com.lantian.lib_lan.camera.control.DevManageGuider;
import com.lantian.lib_lan.camera.control.SDKGuider;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sherlock·Holmes on 2020-03-13
 */
public class DeviceRecorder {

    Activity m_mainAct;
    public DeviceRecorder(Activity mainAct) {
        m_mainAct = mainAct;
    }
    /**
     * @return
     * @throws Exception
     */
    public ArrayList<DevManageGuider.DeviceItem> readDeviceRecord(){
        ArrayList<DevManageGuider.DeviceItem> lConfig = new ArrayList<DevManageGuider.DeviceItem>();
        XmlResourceParser parser;
        try {
            parser = m_mainAct.getResources().getXml(R.xml.device_list);
            //循环直到文档结束
            while (parser.next() != XmlPullParser.END_DOCUMENT) {
                if ("device".equals(parser.getName())) {
                    int i = parser.getAttributeCount();
                    DevManageGuider.DeviceItem dev = SDKGuider.sdkGuider.manageGuider.new DeviceItem();
                    dev.m_struNetInfo = SDKGuider.sdkGuider.manageGuider.new DevNetInfo();
                    while(i>0){
                        if(parser.getAttributeName(i-1).equals("ip")){
                            dev.m_struNetInfo.m_szIp = parser.getAttributeValue(i-1);
                        }
                        else if(parser.getAttributeName(i-1).equals("port")){
                            dev.m_struNetInfo.m_szPort = parser.getAttributeValue(i-1);
                        }
                        else if(parser.getAttributeName(i-1).equals("user")){
                            dev.m_struNetInfo.m_szUserName = parser.getAttributeValue(i-1);
                        }
                        else if(parser.getAttributeName(i-1).equals("password")){
                            dev.m_struNetInfo.m_szPassword = parser.getAttributeValue(i-1);
                        }
                        else if(parser.getAttributeName(i-1).equals("name")){
                            dev.m_szDevName = parser.getAttributeValue(i-1);
                        }
                        i--;
                    }
                    if(dev.m_szDevName != null && dev.m_struNetInfo.checkNetInfo()){
                        lConfig.add(dev);
                    }
                }
            }
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lConfig;
    }
    public void updateDeviceRecord(List<DevManageGuider.DeviceItem> devList){

    }
}
