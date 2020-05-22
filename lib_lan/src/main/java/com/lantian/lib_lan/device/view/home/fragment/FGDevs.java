package com.lantian.lib_lan.device.view.home.fragment;

import android.annotation.SuppressLint;
import android.app.Service;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lantian.lib_base.MyApp;
import com.lantian.lib_base.database.greendao.DevsDao;
import com.lantian.lib_base.entity.items.Devs;
import com.lantian.lib_base.file.sharedpreferences.SharedPreferencesHelper;
import com.lantian.lib_base.utils.BaseUtils;
import com.lantian.lib_commin_ui.base.BaseFragmen;
import com.lantian.lib_commin_ui.dialog.AlertDialogUtil;
import com.lantian.lib_lan.R;
import com.lantian.lib_lan.camera.control.DevManageGuider;
import com.lantian.lib_lan.camera.control.SDKGuider;
import com.lantian.lib_lan.camera.model.DBDevice;
import com.lantian.lib_lan.device.model.PostMessage;
import com.lantian.lib_lan.device.view.home.LANDevActivity;
import com.lantian.lib_lan.device.view.home.adapter.DevAdapter;
import com.lantian.lib_lan.subfield.SubfieldActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sherlock·Holmes on 2020/5/12
 */
public class FGDevs extends BaseFragmen {

    private RecyclerView devlist;
    private DevAdapter devAdapter;
    private List<Devs> mDevs;
    private DevsDao devsDao;
    private ArrayList<DevManageGuider.DeviceItem> ldev;
    private GridLayoutManager gridLayoutManager;
    private int status;
    private LayoutInflater layoutInflater;
    private String userid;

    public static final String CAMERA ="camera";
    public static final String SUBFIELD = "subfield";
    public static final String OTHER = "other";
    private int devsitem = 0;

    public static Fragment getDevsFragment(){
        FGDevs fragment = new FGDevs();
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dev_fragment,container,false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        EventBus.getDefault().register(this);
        userid = String.valueOf(SharedPreferencesHelper.get(getContext(),"user_name",""));
        devsDao = ((MyApp)BaseUtils.getContext()).getDaoSession().getDevsDao();
        gridLayoutManager = new GridLayoutManager(getContext(),2);
        ldev = new ArrayList<DevManageGuider.DeviceItem>();
        mDevs = new ArrayList<Devs>();
        devlist = view.findViewById(R.id.devs_list);
        /**从数据亏获取所有设备**/
        if(DBDevice.getInstance(null)!=null)
        {
            ldev.addAll(DBDevice.getInstance(null).getAllDevices());
        }
        SDKGuider.sdkGuider.manageGuider.setDevList(ldev);
        getdevs();

    }

    /**获取设备**/
    private void getdevs() {
        /**摄像头**/
        Devs devs = new Devs();
        if (SDKGuider.sdkGuider.manageGuider.getDevList().size() != 0){
            for (DevManageGuider.DeviceItem item: SDKGuider.sdkGuider.manageGuider.getDevList()) {
                Log.e("status",status + "");
                devs.setIp(item.m_struNetInfo.m_szIp);
                Log.e("IP",item.m_struNetInfo.m_szIp+ " ");
                devs.setDevname(item.m_szDevName);
                devs.setIcon(R.drawable.ic_action_dev_camero);
                devs.setDevkind(0);
                SDKGuider.sdkGuider.manageGuider.setCurrSelectDevIndex(status);
                switch(SDKGuider.sdkGuider.manageGuider.getDevList().get(status).m_struDevState.m_iLogState){
                    case 0:
                        devs.setStatus(0);
                        break;
                    case 1:
                        devs.setStatus(1);
                        break;
                    case 2:
                        devs.setStatus(2);
                        break;
                    default:
                }
                mDevs.add(devs);
                status++;
              }
           }

            /**分栏设备**/
            List<Devs> devsList = devsDao.queryBuilder().where(DevsDao.Properties.Userid.eq(userid)).list();
            if (devsList.size()!=0){
                for (Devs d:devsList){
                    if (d.getDevkind() == 1){
                        d.setIcon(R.drawable.ic_action_dev_columns);
                    }else if (d.getDevkind() == 2){
                        d.setIcon(R.drawable.ic_action_dev);
                    }
                    d.setStatus(1);
                    mDevs.add(d);
                }
            }
        initDapter(mDevs);
    }

    /**检查设备状态**/
    private void checkstatus(){
        Devs devs = new Devs();
        status =0;
        if (mDevs.size()!=0){
            mDevs.clear();
        }
        if (devAdapter.getData().size()!=0){
            devAdapter.getData().clear();
        }
        for (DevManageGuider.DeviceItem item: SDKGuider.sdkGuider.manageGuider.getDevList()) {
            switch(SDKGuider.sdkGuider.manageGuider.getDevList().get(status).m_struDevState.m_iLogState){
                case 0:
                    devs.setStatus(0);
                    break;
                case 1:
                    devs.setStatus(1);
                    break;
                case 2:
                    devs.setStatus(2);
                    break;
                default:
            }
            devs.setIp(item.m_struNetInfo.m_szIp);
            devs.setName(item.m_szDevName);
            devs.setIcon(R.drawable.video_camero);
            mDevs.add(devs);
            status++;
        }
    }


    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true)
    public void getMsg(PostMessage postMessage){
        if (postMessage.getMessage().equals(CAMERA) && postMessage.getStatus()==1){
            devsitem = 1;
            flushDeviceList();
        }else if (postMessage.getMessage().equals(SUBFIELD) && postMessage.getStatus() == 2){
            devsitem =2;
            flushDeviceList();
        }else if (postMessage.getMessage().equals(OTHER) && postMessage.getStatus() ==3){
            devsitem = 3;
            flushDeviceList();
        }
    }
    /**adapter操作**/
    private void initDapter(List<Devs> mDevs) {
        devAdapter = new DevAdapter(R.layout.devs_item,mDevs);
        devlist.setLayoutManager(gridLayoutManager);
        devlist.setAdapter(devAdapter);
        devAdapter.setOnItemLongClickListener(new BaseQuickAdapter.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(BaseQuickAdapter adapter, View view, int position) {
                    SDKGuider.sdkGuider.manageGuider.setCurrSelectDevIndex(position);
                    Log.e("click","点击" + position);
                    Vibrator vib = (Vibrator) BaseUtils.getContext().getSystemService(Service.VIBRATOR_SERVICE);
                    vib.vibrate(100);
                    ImageButton buttnDel = view.findViewById(R.id.dev_del);
                    buttnDel.setVisibility(View.VISIBLE);
                    return true;
                }
        });
        devAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Devs devs = devAdapter.getData().get(position);
                Vibrator vib = (Vibrator) BaseUtils.getContext().getSystemService(Service.VIBRATOR_SERVICE);
                vib.vibrate(100);
                ImageButton buttnDel = view.findViewById(R.id.dev_del);
                if (buttnDel.getVisibility() == View.VISIBLE){
                    buttnDel.setVisibility(View.INVISIBLE);
                }else {
                    if (devs.getDevkind()==0){
                        SDKGuider.sdkGuider.manageGuider.setCurrSelectDevIndex(position);
                        initDevCam();
                    }else if (devs.getDevkind()==1){
                        Log.e("FEN","1");
                        initDevSubfield(devs.getIp());
                    }

                }
            }
        });
        devAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                Log.e("DEL","删除");
                Log.e("pos",position+"");
                ImageButton buttnDel = view.findViewById(R.id.dev_del);
                deletedev(position);
                if (buttnDel.getVisibility() == View.VISIBLE){
                    buttnDel.setVisibility(View.INVISIBLE);
                }
            }
        });
    }

    /**分栏设备**/
    private void initDevSubfield(String ip) {
        Bundle bundle = new Bundle();
        bundle.putString("IP",ip);
        SubfieldActivity.instance(getContext(),SubfieldActivity.class,bundle);
    }

    /**删除设备
     * @return**/
    private void deletedev(int p) {
         int devkind = devAdapter.getData().get(p).getDevkind();
         if ( devkind== 0){
             switch (SDKGuider.sdkGuider.manageGuider.getDevList().get(p).m_struDevState.m_iLogState) {
                 case 1: {
                     if (!SDKGuider.sdkGuider.manageGuider.logout_jni(SDKGuider.sdkGuider.manageGuider.getCurrSelectDevIndex())) {

                     }
                 }
                 break;
                 default:
                     break;
             }
             if (DBDevice.getInstance(null) != null) {
                 Log.e("Index",SDKGuider.sdkGuider.manageGuider.getCurrSelectDevIndex()+"");
                 DBDevice.getInstance(null).removeDeviceById(SDKGuider.sdkGuider.manageGuider.getCurrSelectDev().m_szUUID);
             }
             SDKGuider.sdkGuider.manageGuider.getDevList().remove(p);
         }else if (devkind == 1){
             devsDao.deleteByKey(devAdapter.getData().get(p).getIp());
         }else {
             devsDao.deleteByKey(devAdapter.getData().get(p).getIp());
         }
        flushDeviceList();

        }

    /**刷新adapter**/
    private void flushDeviceList() {
        devAdapter.getData().clear();
        status = 0;
        /**摄像头**/
        Devs devs = new Devs();
        for (DevManageGuider.DeviceItem item: SDKGuider.sdkGuider.manageGuider.getDevList()) {
            devs.setIp(item.m_struNetInfo.m_szIp);
            devs.setDevname(item.m_szDevName);
            devs.setIcon(R.drawable.ic_action_dev_camero);
            SDKGuider.sdkGuider.manageGuider.setCurrSelectDevIndex(status);
            switch(SDKGuider.sdkGuider.manageGuider.getDevList().get(status).m_struDevState.m_iLogState){
                case 0:
                    devs.setStatus(0);
                    break;
                case 1:
                    devs.setStatus(1);
                    break;
                case 2:
                    devs.setStatus(2);
                    break;
                default:
            }
            mDevs.add(devs);
            status++;
        }
        /**分栏设备**/
        List<Devs> devsList = devsDao.queryBuilder().where(DevsDao.Properties.Userid.eq(userid)).list();
            for (Devs d:devsList){
                if (d.getDevkind() ==1){
                    d.setIcon(R.drawable.ic_action_dev_columns);
                }else {
                    d.setIcon(R.drawable.ic_action_dev);
                }
                d.setDevkind(1);
                d.setStatus(1);
                mDevs.add(d);
            }
            devAdapter.setNewData(mDevs);
    }

    @SuppressLint({"NewApi", "ResourceType"})
    private void initDevCam() {
        String szItemText;
        switch (SDKGuider.sdkGuider.manageGuider.getDevList().get(SDKGuider.sdkGuider.manageGuider.getCurrSelectDevIndex()).m_struDevState.m_iLogState){
            case 0:
                szItemText = "offline";
                Log.e("offline","设备不在线");
                String[] buttons = new String[]{"确定","取消"};
                AlertDialogUtil.TwoChoiceDialog(getActivity(), "温馨提示！", "您的设备不在线，是否进行登录？", buttons, new AlertDialogUtil.TwoChoiceHandle() {
                    @Override
                    public void onPositiveButtonHandle() {
                        if (SDKGuider.sdkGuider.manageGuider.login_v40_jna_with_index(SDKGuider.sdkGuider.manageGuider.getCurrSelectDevIndex())){
                            LANDevActivity.instance(getContext(),LANDevActivity.class,null);
                        }else {
                            
                        }
                    }

                    @Override
                    public void onNegativeButtonHandle() {

                    }
                });
                break;
            case 1:
                Log.e("online","设备在线");
                LANDevActivity.instance(getContext(),LANDevActivity.class,null);
                szItemText = "online";
                break;
            case 2:
                szItemText = "dropoff";
                
                break;
            default:
                szItemText = "Unknown";
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }
}
