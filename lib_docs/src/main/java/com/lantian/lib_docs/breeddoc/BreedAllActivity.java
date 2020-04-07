package com.lantian.lib_docs.breeddoc;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.lantian.lib_base.entity.module.response.breeds.BreedIndex;
import com.lantian.lib_commin_ui.base.BaseActivity;
import com.lantian.lib_docs.R;

import java.io.Serializable;

/**
 * Created by Sherlock·Holmes on 2020-04-02
 */
public class BreedAllActivity extends BaseActivity implements View.OnClickListener {

    private ImageView mBreedBtnBack;
    private TextView mBreedAllName;
    private TextView mBreedAllByTime;
    private ImageView mAllBreedHead;
    private TextView mBreedAllTitel;
    private TextView mBreedAllNumber;
    private TextView mDbreedAllOut;
    private TextView mAllbasemsg;
    private TextView mAlldocmsg;
    private Bundle bundle;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_breed_all;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        initView();
    }

    private void initView() {
        bundle = getIntent().getExtras();
        mBreedBtnBack = findViewById(R.id.breed_btn_back);
        mBreedAllName = findViewById(R.id.breed_all_name);
        mBreedAllByTime = findViewById(R.id.breed_all_by_time);
        mAllBreedHead = findViewById(R.id.all_breed_head);
        mBreedAllTitel = findViewById(R.id.breed_all_titel);
        mBreedAllNumber = findViewById(R.id.breed_all_number);
        mDbreedAllOut = findViewById(R.id.dbreed_all_out);
        mAllbasemsg = findViewById(R.id.allbasemsg);
        mAlldocmsg = findViewById(R.id.alldocmsg);

        mAllbasemsg.setOnClickListener(this);
        mAlldocmsg.setOnClickListener(this);
        mBreedBtnBack.setOnClickListener(this);

        initData();

    }

    private void initData() {
        Serializable o = bundle.getSerializable("BreedIndex");
        Object name = bundle.get("breed_name");
        if (o != null){
            BreedIndex breedIndex = (BreedIndex) o;
            mBreedAllTitel.setText(breedIndex.getTitle());
            mDbreedAllOut.setText(breedIndex.getPrice());
            mBreedAllByTime.setText(breedIndex.getBecome_time());
            mBreedAllNumber.setText(breedIndex.getNumber());
            mBreedAllName.setText(String.valueOf(name));
        }

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.allbasemsg){


        }else if (v.getId() == R.id.alldocmsg){


        }else if (v.getId() == R.id.breed_btn_back){
            BreedAllActivity.this.finish();
        }
    }
}
