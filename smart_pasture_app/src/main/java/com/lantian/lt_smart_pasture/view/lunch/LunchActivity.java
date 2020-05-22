package com.lantian.lt_smart_pasture.view.lunch;

import android.os.Bundle;
import android.util.Log;

import com.lantian.lib_base.file.sharedpreferences.SharedPreferencesHelper;
import com.lantian.lib_commin_ui.base.ActivityManagerUtil;
import com.lantian.lib_commin_ui.base.BaseActivity;
import com.lantian.lib_lan.device.view.DevHomeActivity;
import com.lantian.lib_network.networkstatus.NetWorkStatus;
import com.lantian.lt_smart_pasture.R;
import com.lantian.lt_smart_pasture.view.home.HomeActivity;
import com.lantian.lt_smart_pasture.view.login.LoginActivity;

/**
 * Created by Sherlock·Holmes on 2020/5/10
 */
public class LunchActivity extends BaseActivity {
    @Override
    protected int getLayoutId() {
        return R.layout.activity_lunch;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        initView();
    }
    private void initView() {
        if (NetWorkStatus.isNetWorkStatus(this)==5){
            Log.e("LAN","局域网");
            LunchActivity.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(1000);
                        if (!SharedPreferencesHelper.get(LunchActivity.this,"user_name","").equals("")){
                            DevHomeActivity.instance(LunchActivity.this,DevHomeActivity.class,null);
                            ActivityManagerUtil.getAppManager().finishActivity();
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }else {
            LunchActivity.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(1000);
                        if (!SharedPreferencesHelper.get(LunchActivity.this,"user_name","").equals("")){
                            HomeActivity.instance(LunchActivity.this,HomeActivity.class,null);
                            ActivityManagerUtil.getAppManager().finishActivity();
                        }else {
                            LoginActivity.instance(LunchActivity.this,LoginActivity.class,null);
                            ActivityManagerUtil.getAppManager().finishActivity();
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }


    }
}
