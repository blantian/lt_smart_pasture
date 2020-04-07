package com.lantian.lt_smart_pasture.view.mine;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;

import com.lantian.lib_commin_ui.base.BaseActivity;
import com.lantian.lt_smart_pasture.R;

/**
 * Created by Sherlock·Holmes on 2020-04-03
 */
public class AboutAsActiviity extends BaseActivity {

    private ImageView mEarBtnBack;
    private EditText mAboutAsLocation;
    private EditText mAboutAsUname;
    private EditText mAboutAsPhone;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_about_as;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        initView();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) and run LayoutCreator again
    }

    private void initView() {
        mEarBtnBack = (ImageView) findViewById(R.id.ear_btn_back);
        mAboutAsLocation = (EditText) findViewById(R.id.about_as_location);
        mAboutAsUname = (EditText) findViewById(R.id.about_as_Uname);
        mAboutAsPhone = (EditText) findViewById(R.id.about_as_phone);
    }

}
