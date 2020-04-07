package com.lantian.lt_smart_pasture.view.camera;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.lantian.lib_lan.camera.control.DevManageGuider;
import com.lantian.lib_lan.camera.control.SDKGuider;
import com.lantian.lib_lan.camera.model.DBDevice;
import com.lantian.lt_smart_pasture.R;
import com.lantian.lib_commin_ui.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Sherlock·Holmes on 2020-03-13
 */
public class AddDevActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.camera_name)
    EditText cameraName;
    @BindView(R.id.camera_ip)
    EditText cameraIp;
    @BindView(R.id.add_camera_bttn)
    Button addCameraBttn;
    @BindView(R.id.cancel_bttn)
    Button cancelBttn;
    @BindView(R.id.camera_port)
    EditText cameraPort;
    @BindView(R.id.camera_user)
    EditText cameraUser;
    @BindView(R.id.camera_pass)
    EditText cameraPass;

    String DevName = "192.168.1.64";
    String Ip = "192.168.1.64";
    String Port = "8000";
    String UserName = "admin";
    String PassWord= "hik12345";

    @Override
    protected int getLayoutId() {
        return R.layout.activity_add_camera;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        cameraName.setText(DevName);
        cameraIp.setText(Ip);
        cameraPort.setText(Port);
        cameraUser.setText(UserName);
        cameraPass.setText(PassWord);
        addCameraBttn.setOnClickListener(this);
        cancelBttn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.add_camera_bttn:
                DevManageGuider.DeviceItem deviceItem = SDKGuider.sdkGuider.manageGuider.new DeviceItem();
                deviceItem.m_szDevName = cameraName.getText().toString();
                deviceItem.m_struNetInfo = SDKGuider.sdkGuider.manageGuider.new DevNetInfo(
                        cameraIp.getText().toString(),
                        cameraPort.getText().toString(),
                        cameraUser.getText().toString(),
                        cameraPass.getText().toString());
                if(deviceItem.m_szDevName.isEmpty())
                {
                    deviceItem.m_szDevName = deviceItem.m_struNetInfo.m_szIp;
                }
                if (SDKGuider.sdkGuider.manageGuider.login_v40_jna(deviceItem.m_szDevName, deviceItem.m_struNetInfo)) {
                    showToast("添加设备成功！");
                    // <20190516>: need flass dev listview.
                    if(DBDevice.getInstance(null)!=null)
                    {
                        DBDevice.getInstance(null).insertDevice(deviceItem);
                    }
                    AddDevActivity.this.finish();
                } else {
                    showToast("添加设备失败："+ SDKGuider.sdkGuider.GetLastError_jni());
                }
                break;
            case R.id.cancel_bttn:
                int i = 0;
                showToast("Click Cancel"+(++i)+"times");
                AddDevActivity.this.finish();
            default:
        }
    }

}
