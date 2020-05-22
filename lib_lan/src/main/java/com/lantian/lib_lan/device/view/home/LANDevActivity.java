package com.lantian.lib_lan.device.view.home;

import android.graphics.PixelFormat;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.hikvision.netsdk.NET_DVR_PREVIEWINFO;
import com.lantian.lib_commin_ui.base.BaseActivity;
import com.lantian.lib_lan.R;
import com.lantian.lib_lan.camera.control.DevManageGuider;
import com.lantian.lib_lan.camera.control.SDKGuider;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Created by Sherlock·Holmes on 2020-03-13
 */
public class LANDevActivity extends BaseActivity implements View.OnClickListener, SurfaceHolder.Callback {



    private Spinner mBychannelSpinner;
    private Spinner mBystreamSpinner;
    private SurfaceView SurfacePreviewPlay;
    private Button startStream;
    private Button takePicture;
    private Button stopStream;

    private int mPreviewHandle= -1;
    private int mSelectChannel = -1;
    private int mSelectStreamType = -1;
    // return by NET_DVR_Login_v30
    private int mUserID = -1;

    // analog channel nums
    private int mByChanNum = 0;
    //start analog channel
    private int mByStartChan = 0;

    //digital channel nums
    private int mIPChanNum = 0;
    //start digital channel
    private int mByStartDChan = 0;

    private List<String> mDataListChannel, mDataListStream;
    private ArrayAdapter<String> mStreamtypeAdapter, mArrchannelAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_camera_sureface;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        // TODO: add setContentView(...) invocation
        initView();
    }

    /**
     * 初始化组件
     */
    private void initView() {
        mBychannelSpinner = findViewById(R.id.select_channel);
        mBystreamSpinner = findViewById(R.id.select_stream_type);
        SurfacePreviewPlay = findViewById(R.id.Surface_Preview_Play);
        startStream = findViewById(R.id.start_stream);
        takePicture = findViewById(R.id.take_picture);
        stopStream = findViewById(R.id.stop_stream);

       startStream.setOnClickListener(this);
        stopStream.setOnClickListener(this);
        takePicture.setOnClickListener(this);
        /**获取设备信息对象**/
        DevManageGuider.DeviceItem deviceInfo = SDKGuider.sdkGuider.manageGuider.getCurrSelectDev();
        if (deviceInfo == null){
            showToast("获取设备对象信息失败！");
        }
        mUserID = deviceInfo.m_lUserID;
        mByChanNum = deviceInfo.m_struDeviceInfoV40_jna.struDeviceV30.byChanNum;
        mByStartChan = deviceInfo.m_struDeviceInfoV40_jna.struDeviceV30.byStartChan;
        mIPChanNum = deviceInfo.m_struDeviceInfoV40_jna.struDeviceV30.byIPChanNum + deviceInfo.m_struDeviceInfoV40_jna.struDeviceV30.byHighDChanNum * 256;
        mByStartDChan = deviceInfo.m_struDeviceInfoV40_jna.struDeviceV30.byStartChan;
        int iAnalogStartChan = mByStartChan;
        int iDigitalStartChan = mByStartDChan;
        mDataListChannel = new ArrayList<String>();

        /**添加连接的设备**/
       for(int idexChanNum = 0;idexChanNum < mByChanNum; idexChanNum++) {
            mDataListChannel.add("ACamera_"+ iAnalogStartChan);
            iAnalogStartChan++;
        }

        mArrchannelAdapter= new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, mDataListChannel);
        mArrchannelAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mBychannelSpinner.setAdapter(mArrchannelAdapter);

        mDataListStream = new ArrayList<String>();
        mDataListStream.add("主流");
        mDataListStream.add("子流");
        mDataListStream.add("第三流");

        mStreamtypeAdapter = new ArrayAdapter<String>(LANDevActivity.this, android.R.layout.simple_spinner_item, mDataListStream);
        mStreamtypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mBystreamSpinner.setAdapter(mStreamtypeAdapter);

        //下拉点击点击事件
        mBystreamSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mSelectStreamType = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }

        });

        mBychannelSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String text = mBychannelSpinner.getItemAtPosition(position).toString();
                mSelectChannel = Integer.valueOf(GetChannel(text)).intValue();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        SurfacePreviewPlay.getHolder().addCallback(this);
        SurfacePreviewPlay.setZOrderOnTop(true);

    }

    /**
     *
     * @param inPutStr
     * @return
     */
    public String GetChannel(String inPutStr) {
        String regEx="[^0-9]";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(inPutStr);
        return m.replaceAll("").trim();
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        SurfacePreviewPlay.getHolder().setFormat(PixelFormat.TRANSLUCENT);
        if (-1 == mPreviewHandle) {
            return;
        }
        Surface surface = holder.getSurface();
        if (surface.isValid()) {
            if (-1 == SDKGuider.sdkGuider.m_comPreviewGuider.RealPlaySurfaceChanged_jni(mPreviewHandle, 0, holder))
            {
                showToast("NET_DVR_PlayBackSurfaceChanged"+ SDKGuider.sdkGuider.GetLastError_jni());
            }
        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        if (-1 == mPreviewHandle) {
            return;
        }
        if (holder.getSurface().isValid()) {
            if (-1 == SDKGuider.sdkGuider.m_comPreviewGuider.RealPlaySurfaceChanged_jni(mPreviewHandle, 0, null))
            {
                showToast("NET_DVR_RealPlaySurfaceChanged" + SDKGuider.sdkGuider.GetLastError_jni());
            }
        }
    }

    @Override
    protected void onDestroy() {
        if(mPreviewHandle != -1){
            SDKGuider.sdkGuider.m_comPreviewGuider.RealPlay_Stop_jni(mPreviewHandle);
            mPreviewHandle = -1;
        }
        super.onDestroy();
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.start_stream) {
            if (mPreviewHandle != -1) {
                SDKGuider.sdkGuider.m_comPreviewGuider.RealPlay_Stop_jni(mPreviewHandle);
            }
            NET_DVR_PREVIEWINFO struPlayInfo = new NET_DVR_PREVIEWINFO();
            struPlayInfo.lChannel = mSelectChannel;
            struPlayInfo.dwStreamType = mSelectStreamType;
            struPlayInfo.bBlocked = 1;
            struPlayInfo.hHwnd = SurfacePreviewPlay.getHolder();
            mPreviewHandle = SDKGuider.sdkGuider.m_comPreviewGuider.RealPlay_V40_jni(mUserID, struPlayInfo, null);
            if (mPreviewHandle < 0) {
                showToast("NET_DVR_RealPlay_V40 fail, Err:" + SDKGuider.sdkGuider.GetLastError_jni());
                return;
            }
            showToast("NET_DVR_RealPlay_V40 Succ ");
        } else if (id == R.id.stop_stream) {
            if (!SDKGuider.sdkGuider.m_comPreviewGuider.RealPlay_Stop_jni(mPreviewHandle)) {
                showToast("NET_DVR_StopRealPlay m_iPreviewHandle：" + mPreviewHandle
                        + "  error:" + SDKGuider.sdkGuider.GetLastError_jni());
                return;
            }
            mPreviewHandle = -1;
            showToast("NET_DVR_StopRealPlay：停止成功");
        } else if (id == R.id.take_picture) {
            if (mPreviewHandle < 0) {
                showToast("please start preview first：请先开始");
                return;
            }
            String galleryPath = Environment.getExternalStorageDirectory()
                    + File.separator + Environment.DIRECTORY_DCIM
                    + File.separator + "Camera" + File.separator + "test.png";

            Log.e("path", galleryPath);

            if (!SDKGuider.sdkGuider.m_comPreviewGuider.RealPlay_Snap(mPreviewHandle, galleryPath)) {
                showToast("NET_DVR_CapturePicture：抓拍失败, Err:" + SDKGuider.sdkGuider.GetLastError_jni());
                return;
            }
            showToast("NET_DVR_CapturePicture：抓拍成功");
        }

    }

}
