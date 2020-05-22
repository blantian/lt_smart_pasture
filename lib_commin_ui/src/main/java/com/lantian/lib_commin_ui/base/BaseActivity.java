package com.lantian.lib_commin_ui.base;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;

import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.lantian.lib_base.utils.ToastUtils;
import com.lantian.lib_commin_ui.utils.StatusBarUtil;
import com.trello.rxlifecycle2.components.support.RxFragmentActivity;

/**
 * Created by Sherlock·Holmes on 2020-02-11
 */
public abstract class BaseActivity extends RxFragmentActivity{

    protected ActivityManagerUtil appManager = ActivityManagerUtil.getAppManager();
    // 类名
    private String contextString;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        appManager.addActivity(this);
        StatusBarUtil.statusBarLightMode(this);
        setContentView(getLayoutId());
        init(savedInstanceState);
    }

    /**
     * 页面跳转
     * @param context
     * @param cType
     * @param args
     */
    public static void instance(Context context, Class<?> cType, Bundle args){
        Intent intent = new Intent(context, cType);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK );
        if(args !=null){
            intent.putExtras(args);
        }
        context.startActivity(intent);
    }
    protected void showToast(String msg) {
        ToastUtils.show(msg);
    }

    /**
     * 绑定布局文件
     * @return
     */
    protected abstract @LayoutRes int getLayoutId();

    /**
     * 初始化
     * @param savedInstanceState
     */
    protected abstract void init(Bundle savedInstanceState);

    /**
     * 申请指定的权限.
     */
    public void requestPermission(int code, String... permissions) {
        ActivityCompat.requestPermissions(this, permissions, code);
    }

    /**
     * 判断是否有指定的权限
     */
    public boolean hasPermission(String... permissions) {

        for (String permisson : permissions) {
            if (ContextCompat.checkSelfPermission(this, permisson) != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions,
                                           int[] grantResults) {
        switch (requestCode) {
            case AppConstants.WRITE_READ_EXTERNAL_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    doSDCardPermission();
                }
                break;
            case AppConstants.HARDWEAR_CAMERA_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    doCameraPermission();
                }
                break;
                default:
        }
    }

    /**
     * 处理整个应用用中的SDCard业务
     */
    public void doSDCardPermission() {
    }

    public void doCameraPermission() {
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        appManager.finishActivity(this);
    }

}
