package com.lantian.lt_smart_pasture.view.home.adapter;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.lantian.lib_lan.camera.control.DevManageGuider;
import com.lantian.lib_lan.camera.control.SDKGuider;
import com.lantian.lib_lan.camera.model.DBDevice;
import com.lantian.lib_lan.camera.model.DeviceRecorder;
import com.lantian.lt_smart_pasture.R;
import com.lantian.lt_smart_pasture.view.camera.AddDevActivity;
import com.lantian.lt_smart_pasture.view.camera.DevInfoActivity;
import com.lantian.lt_smart_pasture.view.home.HomeActivity;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Sherlock·Holmes on 2020-03-14
 */
public class DrawerAdapter extends BaseAdapter {

    HomeActivity homeActivity;
    DeviceRecorder deviceRecorder;

    ArrayList<DrawerMenuItem> m_dlDevs = new ArrayList<DrawerMenuItem>() ;
    int device_list_offset = 0;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            flushDeviceList();
        }
    };

    public class DrawerMenuItem {
        String m_szDevName ;
        String m_szIP;
        int m_iDevIcon ;
        public DrawerMenuItem(String szDevName, String szIP, int iDevIcon){
            m_szDevName = szDevName;
            m_iDevIcon = iDevIcon;
            m_szIP = szIP;
        }
    }

    /**
     * 线程
     */
    Timer timer = new Timer();
    TimerTask timerTask = new TimerTask() {
        @Override
        public void run() {
            Message message = new Message();
            message.what = 1;
            handler.sendMessage(message);
        }
    };


    public DrawerAdapter(HomeActivity mainActivity){
        this.homeActivity = mainActivity ;
        timer.schedule(timerTask,0,2000);

        deviceRecorder = new DeviceRecorder(mainActivity);
        ArrayList<DevManageGuider.DeviceItem> ldev = new ArrayList<DevManageGuider.DeviceItem>();
        ldev.addAll(deviceRecorder.readDeviceRecord());
        device_list_offset = ldev.size();
        if(DBDevice.getInstance(null)!=null)
        {
            ldev.addAll(DBDevice.getInstance(null).getAllDevices());
        }
        SDKGuider.sdkGuider.manageGuider.setDevList(ldev);
        DrawerAdapter.this.notifyDataSetChanged();
    }

    public void flushDeviceList(){
        DrawerAdapter.this.m_dlDevs.clear();
        for (DevManageGuider.DeviceItem item:
                SDKGuider.sdkGuider.manageGuider.getDevList()
        ) {
            m_dlDevs.add(new DrawerMenuItem(item.m_szDevName, item.m_struNetInfo.m_szIp, R.drawable.ic_action_dev_add_cam));
        }
        DrawerAdapter.this.notifyDataSetChanged();
    }


    @Override
    public int getCount() {
        return m_dlDevs.size();
    }

    @Override
    public Object getItem(int position) {
        return m_dlDevs.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    class MyOnCreateContextMenuListener implements android.view.View.OnCreateContextMenuListener{
        @Override
        public void onCreateContextMenu(ContextMenu var1, View var2, android.view.ContextMenu.ContextMenuInfo var3) {
            MenuItem item;
            String szItemText;
            switch (SDKGuider.sdkGuider.manageGuider.getDevList().get(SDKGuider.sdkGuider.manageGuider.getCurrSelectDevIndex()).m_struDevState.m_iLogState){
                case 0:
                    szItemText = "login";
                    break;
                case 1:
                    szItemText = "logout";
                    break;
                case 2:
                    szItemText = "relogin";
                    break;
                default:
                    szItemText = "Unknown";
            }
            item = var1.add(0, 0, 0, szItemText);
            item.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener(){
                @Override
                public boolean onMenuItemClick(MenuItem var1){
//                    Toast.makeText(m_mainActivity, "Pos" + m_iLastSelectPos, Toast.LENGTH_SHORT).show();
                    switch (SDKGuider.sdkGuider.manageGuider.getDevList().get(SDKGuider.sdkGuider.manageGuider.getCurrSelectDevIndex()).m_struDevState.m_iLogState){
                        case 1:
                            if(SDKGuider.sdkGuider.manageGuider.logout_jni(SDKGuider.sdkGuider.manageGuider.getCurrSelectDevIndex())){
                                Toast.makeText(homeActivity, "logout succ", Toast.LENGTH_SHORT).show();
                            }else{
                                Toast.makeText(homeActivity, "logout failed with " + SDKGuider.sdkGuider.GetLastError_jni(), Toast.LENGTH_SHORT).show();
                            }
                            break;
                        case 0:case 2:
                            if(SDKGuider.sdkGuider.manageGuider.login_v40_jna_with_index(SDKGuider.sdkGuider.manageGuider.getCurrSelectDevIndex())){
                                Toast.makeText(homeActivity, "login succ", Toast.LENGTH_SHORT).show();
                            }else{
                                Toast.makeText(homeActivity, "login failed with " + SDKGuider.sdkGuider.GetLastError_jni(), Toast.LENGTH_SHORT).show();
                            }
                            break;
                        default:
                            break;
                    }
                    return true;
                }
            });



            item = var1.add(0, 1, 0, "delete");
            item.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener(){
                @Override
                public boolean onMenuItemClick(MenuItem var1){
                    switch (SDKGuider.sdkGuider.manageGuider.getDevList().get(SDKGuider.sdkGuider.manageGuider.getCurrSelectDevIndex()).m_struDevState.m_iLogState){
                        case 1: {
                            if (!SDKGuider.sdkGuider.manageGuider.logout_jni(SDKGuider.sdkGuider.manageGuider.getCurrSelectDevIndex())) {
                                return false;
                            }

                        }
                        break;
                        default:
                            break;
                    }
                    if (DBDevice.getInstance(null) != null) {
                        if(SDKGuider.sdkGuider.manageGuider.getCurrSelectDevIndex()-device_list_offset>=0)
                        {
                            DBDevice.getInstance(null).removeDeviceById(SDKGuider.sdkGuider.manageGuider.getCurrSelectDev().m_szUUID);
                        }
                    }
                    SDKGuider.sdkGuider.manageGuider.getDevList().remove(SDKGuider.sdkGuider.manageGuider.getCurrSelectDevIndex());
                    flushDeviceList();
                    return true;
                }
            });

            item = var1.add(0,2,0,"info");
            item.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener(){
                @Override
                public boolean onMenuItemClick(MenuItem var1){
//                    Toast.makeText(m_mainActivity, "Pos" + m_iLastSelectPos, Toast.LENGTH_SHORT).show();
                    Bundle bundle = new Bundle();
                    bundle.putInt("DevIndex", SDKGuider.sdkGuider.manageGuider.getCurrSelectDevIndex());
                    AddDevActivity.instance(homeActivity, DevInfoActivity.class, bundle);
                    return true;
                }
            });
        }
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = LayoutInflater.from(homeActivity).inflate(R.layout.dev_items, parent, false);
        View listView = homeActivity.findViewById(R.id.left_drawer);
        if(view != null){
            ImageView img = view.findViewById(R.id.image);
            TextView camera_name = view.findViewById(R.id.dev_name);
            TextView camera_ip = view.findViewById(R.id.dev_ip);

            camera_name.setText(m_dlDevs.get(position).m_szDevName);
            camera_ip.setText(m_dlDevs.get(position).m_szIP);
            img.setImageDrawable(homeActivity.getResources().getDrawable(m_dlDevs.get(position).m_iDevIcon));


            ((ListView)listView).setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                    m_iLastSelectPos = position;
                    SDKGuider.sdkGuider.manageGuider.setCurrSelectDevIndex(position);
                }
            });

            ((ListView)listView).setOnItemLongClickListener(new AdapterView.OnItemLongClickListener(){
                @Override
                public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
                                               int arg2, long arg3) {
                    SDKGuider.sdkGuider.manageGuider.setCurrSelectDevIndex(arg2);
                    return false;
                }
            });

            listView.setOnCreateContextMenuListener(new MyOnCreateContextMenuListener());
        }
        return view ;
    }
}
