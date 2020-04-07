package com.lantian.lib_docs.breeddoc;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

import com.lantian.lib_commin_ui.base.BaseActivity;
import com.lantian.lib_docs.R;

/**
 * Created by Sherlock·Holmes on 2020-03-19
 */
public class NoBreedActivity extends BaseActivity {
    private ImageView farmerdocBack;
    private Button creatFarmdocBttn;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_new_breed;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        initView();
    }

    private void initView() {
        farmerdocBack = findViewById(R.id.farm_btn_back);
        creatFarmdocBttn = findViewById(R.id.creatFarmdocBttn);
    }

}
