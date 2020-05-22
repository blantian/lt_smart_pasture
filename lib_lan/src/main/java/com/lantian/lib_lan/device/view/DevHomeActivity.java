package com.lantian.lib_lan.device.view;

import android.annotation.SuppressLint;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.lantian.lib_base.MyApp;
import com.lantian.lib_base.database.greendao.DevsDao;
import com.lantian.lib_base.entity.items.Devs;
import com.lantian.lib_base.file.sharedpreferences.SharedPreferencesHelper;
import com.lantian.lib_base.utils.BaseUtils;
import com.lantian.lib_commin_ui.base.ActivityManagerUtil;
import com.lantian.lib_commin_ui.base.BaseActivity;
import com.lantian.lib_commin_ui.dialog.AlertDialogUtil;
import com.lantian.lib_commin_ui.indicator.CHANNEL;
import com.lantian.lib_commin_ui.indicator.MyMagicIndicator;
import com.lantian.lib_commin_ui.viewpager.MyViewPager;
import com.lantian.lib_lan.R;
import com.lantian.lib_lan.camera.control.DevManageGuider;
import com.lantian.lib_lan.camera.control.SDKGuider;
import com.lantian.lib_lan.camera.model.DBDevice;
import com.lantian.lib_lan.device.model.PostMessage;
import com.lantian.lib_lan.device.view.home.DevStatus;
import com.lantian.lib_lan.device.view.home.adapter.BottomTabsAdapter;
import com.lantian.lib_lan.device.view.home.adapter.DevAdapter;
import com.lantian.lib_lan.device.view.home.adapter.TitleAdapter;
import com.lantian.lib_lan.device.view.home.fragment.FGDevs;
import com.lantian.lib_lan.device.view.home.fragment.Mine;
import com.lantian.lib_network.receiver.NetStateChangeReceiver;
import com.lantian.lib_network.receiver.interfaces.NetStateChangeObserver;
import com.lantian.lib_network.utils.NetworkType;

import net.lucode.hackware.magicindicator.MagicIndicator;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sherlock·Holmes on 2020/5/12
 */
public class DevHomeActivity extends BaseActivity implements NetStateChangeObserver, View.OnClickListener, BottomNavigationView.OnNavigationItemSelectedListener, DevStatus, ViewPager.OnPageChangeListener {

    private static final CHANNEL[] LANCHANNELS =
            new CHANNEL[]{
                    CHANNEL.LANHOME,
                    CHANNEL.LANMINE
    };

    private BottomNavigationView bottomNavigationView;
    private MyViewPager viewPager;
    private MagicIndicator magicIndicator;
    private List<Fragment> fragmentList;
    private BottomTabsAdapter bottomTabsAdapter;
    private TitleAdapter titleAdapter;
    private ImageView devAdd;
    private String IP;
    private String PORT;
    private String Username;
    private String PassWord;
    private String Devname;
    private List<Devs> devs;
    private String videocamera = "videocamera";
    private String columns = "columns";
    private String others = "others";
    private List<String> status = new ArrayList<>();
    private DevsDao devsDao;
    DevManageGuider.DeviceItem mDeviceInfo = null;
    private DevAdapter devAdapter;
    private DBDevice m_dbDev;




    @Override
    protected int getLayoutId() {
        return R.layout.activity_lan_home;
    }


    @Override
    protected void init(Bundle savedInstanceState) {
        initView();
    }

    @SuppressLint({"NewApi", "ResourceType"})
    private void initView() {
        m_dbDev = DBDevice.getInstance(this);
        devsDao = ((MyApp) BaseUtils.getContext()).getDaoSession().getDevsDao();
        fragmentList = new ArrayList<>();
        devs = new ArrayList<>();
        NetStateChangeReceiver.registerReceiver(this);
        bottomNavigationView = findViewById(R.id.lan_bnv);
        viewPager = findViewById(R.id.lanviewPager);
        viewPager.setOffscreenPageLimit(fragmentList.size());
        magicIndicator = findViewById(R.id.title_items);
        devAdd = findViewById(R.id.dev_add);
        devAdd.setOnClickListener(this);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        bottomNavigationView.setSelectedItemId(R.id.navigation_dev);
        Resources resource = getResources();
        bottomNavigationView.setItemTextColor(resource.getColorStateList(R.drawable.selector_lan_navigation,null));

        if (!fragmentList.contains(FGDevs.getDevsFragment())){
            fragmentList.add(FGDevs.getDevsFragment());
        }
        if (!fragmentList.contains(Mine.getMineFragment())){
            fragmentList.add(Mine.getMineFragment());
        }
        titleAdapter = new TitleAdapter(getSupportFragmentManager(),LANCHANNELS);
        bottomTabsAdapter = new BottomTabsAdapter(getSupportFragmentManager(),fragmentList);
        viewPager.setAdapter(titleAdapter);
        viewPager.setAdapter(bottomTabsAdapter);
        MyMagicIndicator.initSimpleMagicIndicator(this,LANCHANNELS,viewPager,magicIndicator,20);

    }

    @Override
    protected void onPause() {
        super.onPause();
        NetStateChangeReceiver.unRegisterObserver(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        NetStateChangeReceiver.registerObserver(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        NetStateChangeReceiver.unRegisterReceiver(this);
    }
    @Override
    public void onNetDisconnected() {

    }

    @Override
    public void onNetConnected(NetworkType networkType) {
        if (networkType.ordinal()==NetworkType.NETWORK_WIFI.ordinal()||networkType.ordinal() == NetworkType.NETWORK_4G.ordinal()||
        networkType.ordinal() == NetworkType.NETWORK_3G.ordinal()||networkType.ordinal()==NetworkType.NETWORK_2G.ordinal()){
            ActivityManagerUtil.getAppManager().finishActivity();
        }
    }

    @Override
    public void onClick(final View v) {
        if (v.getId()==R.id.dev_add){
            AlertDialogUtil.addcevdialog(this, new AlertDialogUtil.Buttons(){

                @Override
                public void sendMsgg(String msg) {
                    if (msg.equals("null")){
                        showToast("请选择设备！");
                    }else if (msg.equals("name_null")){
                        showToast("请输入用户名！");
                    }else if (msg.equals("ip_null")){
                        showToast("请输入IP地址！");
                    }else if (msg.equals("port_null")){
                        showToast("请输入设备端口号！");
                    }else if (msg.equals("username_null")){
                        showToast("请输入设备登录用户名！");
                    }else if (msg.equals("userpass_null")){
                        showToast("请输入设备登录密码！");
                    }
                }

                @Override
                public void senddata(String devname, String ip, String port, String username, String pass) {
                    Devname = devname;
                    IP = ip;
                    PORT = port;
                    Username = username;
                    PassWord =  pass;
                }


                @Override
                public void VideoCamera() {
                  if (status.size() !=0){
                      status.clear();
                      status.add(videocamera);
                  }else {
                      status.add(videocamera);
                  }
                }
                @Override
                public void Columns() {
                    if (status.size()!=0){
                        status.clear();
                        status.add(columns);
                    }else {
                        status.add(columns);
                    }

                }

                @Override
                public void Others() {
                  if (status.size()!=0){
                      status.clear();
                      status.add(others);
                  }else {
                      status.add(others);
                  }
                }

                @Override
                public void Add() {
                    Log.e("add","添加 ");
                    /**添加摄像头**/
                    if (status.size() != 0){
                        if (status.get(0).equals("videocamera")){
                            DevManageGuider.DeviceItem deviceItem = SDKGuider.sdkGuider.manageGuider.new DeviceItem();
                            deviceItem.m_szDevName = Devname;
                            Log.e("MSG",IP +" "+ PORT+" " + PassWord+" " + Username + " "+Devname);
                            deviceItem.m_struNetInfo = SDKGuider.sdkGuider.manageGuider.new DevNetInfo(
                                    IP,PORT,Username,PassWord);
                            if(deviceItem.m_szDevName.isEmpty())
                            {
                                deviceItem.m_szDevName = deviceItem.m_struNetInfo.m_szIp;
                            }
                            if (SDKGuider.sdkGuider.manageGuider.login_v40_jna(deviceItem.m_szDevName, deviceItem.m_struNetInfo)) {
                                showToast("添加设备成功！");
                                Log.e("SUCC","添加设备成功");
                                if(DBDevice.getInstance(null)!=null)
                                {
                                    DBDevice.getInstance(null).insertDevice(deviceItem);
                                    EventBus.getDefault().postSticky(new PostMessage(FGDevs.CAMERA,1));
                                }

                            } else {
                                int i = SDKGuider.sdkGuider.GetLastError_jni();
                                Log.e("i",i+"");
                                switch (i){
                                    case 1:
                                        Log.e("1","用户名密码错误");
                                        showToast("用户名密码错误!");
                                        break;
                                    case 7:
                                        Log.e("1","用户名密码错误");
                                        showToast("连接设备失败!");
                                        break;
                                    case 29:
                                        Log.e("1","用户名密码错误");
                                        showToast("设备操作失败！");
                                        break;
                                    default:
                                }
                            }
                            /**添加分栏设备**/
                        }else if (status.get(0).equals("columns")){
                            Devs devs = new Devs();
                            devs.setIp(IP);
                            devs.setPort(PORT);
                            devs.setDevname(Devname);
                            devs.setUserid(String.valueOf(SharedPreferencesHelper.get(DevHomeActivity.this,"user_name","")));
                            devs.setDevkind(1);
                            devsDao.insertOrReplace(devs);
                            EventBus.getDefault().postSticky(new PostMessage(FGDevs.SUBFIELD,2));
                            /**其他设备**/
                        }else if (status.get(0).equals("others")){
                            Devs devs = new Devs();
                            devs.setIp(IP);
                            devs.setPort(PORT);
                            devs.setDevname(Devname);
                            devs.setUserid(String.valueOf(SharedPreferencesHelper.get(DevHomeActivity.this,"user_name","")));
                            devs.setDevkind(2);
                            devsDao.insertOrReplace(devs);
                            EventBus.getDefault().postSticky(new PostMessage(FGDevs.OTHER,3));
                        }
                    }else {
                        showToast("请选择设备选项！");
                    }


                }

                @Override
                public void Cancel() {

                }

            });

        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        if (menuItem.getItemId() == R.id.navigation_mine) {
            viewPager.setCurrentItem(1);
            return true;
        }else if (menuItem.getItemId() ==R.id.navigation_dev){
            viewPager.setCurrentItem(0);
            return true;
        }
        return false;
    }

    @Override
    public boolean isOnlineOrChooseDev() {
        mDeviceInfo = SDKGuider.sdkGuider.manageGuider.getCurrSelectDev();
        if(mDeviceInfo == null) {
            showToast("获取设备失败！");
            return false;
        }else {
            if(mDeviceInfo.m_struDevState.m_iLogState != 1){
                showToast("请先登录设备！");
                return false;
            }
        }
        return true;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        //bottomNavigationView.getMenu().getItem(position).setChecked(true);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
