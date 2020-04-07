package com.lantian.lt_smart_pasture.view.mine;

import android.os.Bundle;
import android.widget.ImageView;

import androidx.recyclerview.widget.RecyclerView;

import com.lantian.lib_commin_ui.base.BaseActivity;
import com.lantian.lt_smart_pasture.R;

/**
 * Created by Sherlock·Holmes on 2020-04-03
 */
public class findActivity extends BaseActivity {


    private ImageView mEarBtnBack;
    private RecyclerView mFindList;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_find;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        initView();
    }

    private void initView() {
        mEarBtnBack = (ImageView) findViewById(R.id.ear_btn_back);
        mFindList = (RecyclerView) findViewById(R.id.find_list);
    }
}
