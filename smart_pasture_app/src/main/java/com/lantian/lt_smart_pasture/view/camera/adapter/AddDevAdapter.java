package com.lantian.lt_smart_pasture.view.camera.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.lantian.lib_lan.camera.control.DevManageGuider;
import com.lantian.lib_lan.camera.control.SDKGuider;
import com.lantian.lt_smart_pasture.view.camera.DevInfoActivity;

import java.util.ArrayList;

/**
 * Created by Sherlock·Holmes on 2020-03-13
 */
public class AddDevAdapter extends BaseAdapter {

    ArrayList<String> mAlDevinfoList = new ArrayList<String>() ;
    DevInfoActivity mDevInfoActivity;
    private int mIDevInde = -1;

    public AddDevAdapter(DevInfoActivity devInfoActivity, int iDevIndex){
        mDevInfoActivity = devInfoActivity;
        mIDevInde = iDevIndex;
        initDevInfoList(SDKGuider.sdkGuider.manageGuider.getDevList().get(iDevIndex));
    }

    private void initDevInfoList(DevManageGuider.DeviceItem devItem) {
        mAlDevinfoList.add("DevName:" + devItem.m_szDevName);
        mAlDevinfoList.add("IP:" + devItem.m_struNetInfo.m_szIp);
        mAlDevinfoList.add("Port:" + devItem.m_struNetInfo.m_szPort);
        mAlDevinfoList.add("State:"
                + (devItem.m_struDevState.m_iLogState == 1 ? "<on-line>" : "<off-line>")
                + (devItem.m_struDevState.m_iAlarmState == 1 ? "<open-alarm>" : "<close-alarm>")
        );
    }

    @Override
    public int getCount() {
        return mAlDevinfoList.size();
    }

    @Override
    public Object getItem(int position) {
        return mAlDevinfoList.get(position) ;
    }

    @Override
    public long getItemId(int position) {
        return position ;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView view = new TextView(mDevInfoActivity);
        view.setText((String)getItem(position));
        return view;
    }
}
