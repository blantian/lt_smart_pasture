package com.lantian.lib_docs.breeddoc.dabiaoyonghu;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.lantian.lib_base.entity.module.response.breeds.BreedIndex;
import com.lantian.lib_base.utils.Utils;
import com.lantian.lib_commin_ui.base.BaseActivity;
import com.lantian.lib_docs.R;

import java.io.Serializable;

/**
 * Created by Sherlock·Holmes on 2020-04-01
 */
public class DabiaoBreedDocActivity extends BaseActivity implements View.OnClickListener {


    private ImageView mDabiaoBtnBack;
    private TextView mDabiaoBreedName;
    private TextView mDabiaoBreedByTime;
    private ImageView mBreedHead;
    private TextView mDabiaoBreedTitel;
    private TextView mDabiaoBreedNumber;
    private TextView mDabiaoBreedOut;
    private TextView mDabiaoBreedsDocMsg;
    private Bundle bundle;
    private String breedName;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_dabiao_tabs;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        initView();
    }

    private void initView() {
        bundle =getIntent().getExtras();
        mDabiaoBreedByTime = findViewById(R.id.dabiao_breed_by_time);
        mDabiaoBreedName = findViewById(R.id.dabiao_breed_name);
        mBreedHead = findViewById(R.id.dabiao_breed_head);
        mDabiaoBtnBack = findViewById(R.id.dabiao_btn_back);
        mDabiaoBreedTitel = findViewById(R.id.dabiao_breed_titel);
        mDabiaoBreedNumber = findViewById(R.id.dabiao_breed_number);
        mDabiaoBreedOut = findViewById(R.id.dabiao_breed_out);
        mDabiaoBreedsDocMsg = findViewById(R.id.dabiao_breeds_doc_msg);
        mDabiaoBtnBack.setOnClickListener(this);
        mDabiaoBreedsDocMsg.setOnClickListener(this);
        initData();
    }

    private void initData() {
        Serializable serializable = bundle.getSerializable("BreedIndex");
        breedName = bundle.getString("breed_name");
        if (serializable != null){
            BreedIndex breedIndex =(BreedIndex)serializable;
            mDabiaoBreedByTime.setText(breedIndex.getBecome_time());
            mDabiaoBreedOut.setText(breedIndex.getPrice());
            mDabiaoBreedNumber.setText(breedIndex.getNumber());
            mDabiaoBreedTitel.setText(breedIndex.getTitle());
            mDabiaoBreedName.setText(breedName);
        }

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.dabiao_btn_back) {
            DabiaoBreedDocActivity.this.finish();
        }else if (v.getId() == R.id.dabiao_breeds_doc_msg){
            EarCodeActivity.instance(Utils.getContext(),EarCodeActivity.class,bundle);
        }
    }
}
