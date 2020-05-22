package com.lantian.lib_docs.farmdoc.view.farmerdata;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lantian.lib_base.MyApp;
import com.lantian.lib_base.entity.module.response.farmer.farmlist.HuzhuList;
import com.lantian.lib_base.utils.EventMessage;
import com.lantian.lib_commin_ui.base.BaseActivity;
import com.lantian.lib_docs.R;
import com.lantian.lib_docs.farmdoc.view.home.FarmHomeActivity;
import com.lantian.lib_image_loader.loadpic.ImageLoaderManager;
import com.lantian.lib_network.networkstatus.NetWorkStatus;
import com.lantian.lib_network.retrofit2.MyCallBack;
import com.lantian.lib_network.retrofit2.RetrofitHelper;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;

/**
 * Created by Sherlock·Holmes on 2020-03-29
 */
public class FarmerMsgActivity extends BaseActivity implements View.OnClickListener {

    private LinearLayout mLayoutHeader;
    private TextView mEdit;
    private TextView mEtName;
    private TextView mSRelation;
    private TextView mEtPhone;
    private TextView mSForce;
    private TextView mEdit2;
    private ImageView mIdcard1;
    private ImageView mIdcard2;
    private TextView mIdtext;
    private TextView mText;
    private RelativeLayout mCard;
    private TextView mEtSex;
    private TextView mEtIdBirth;
    private TextView mEtIdAge;
    private TextView mEtIdDetails;
    private ImageView back;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_farmer_msg;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        initView();
    }

    private void initView() {
        mEtName = findViewById(R.id.msg_et_name);
        mSRelation = findViewById(R.id.s_relation);
        mEtPhone = findViewById(R.id.msg_et_phone);
        mSForce = findViewById(R.id.s_force);
        mIdcard1 = findViewById(R.id.msg_idcard1);
        mIdcard2 = findViewById(R.id.msg_idcard2);
        mEtSex = findViewById(R.id.et_sex);
        mEtIdBirth = findViewById(R.id.et_id_Birth);
        mEtIdDetails = findViewById(R.id.et_idDetails);
        back = findViewById(R.id.msg_btn_back);
        mEdit = findViewById(R.id.msg_edit);
        mEdit2 = findViewById(R.id.msg_edit2);
        mEtIdAge = findViewById(R.id.et_idAge);
        mEdit.setOnClickListener(this);
        mEdit2.setOnClickListener(this);
        back.setOnClickListener(this);
        initData();
    }

    private void initData() {
        if (NetWorkStatus.isNetworkAvailable(this)){
            RetrofitHelper.getApiService().getHuZhu(MyApp.Userid).enqueue(new MyCallBack<ArrayList<HuzhuList>>() {
                @Override
                public void success(ArrayList<HuzhuList> huzhuLists) {
                    setDataTotextView(huzhuLists);
                }

                @Override
                public void failure(String msg) {

                }
            });

        }else {

        }
    }

    private void setDataTotextView(ArrayList<HuzhuList> huzhuList) {
       for (HuzhuList huzhu:huzhuList){
           if (huzhu.getRelations().equals("1")){
               mSRelation.setText("户主");
           }else if (huzhu.getRelations().equals("2")){
               mSRelation.setText("妻子");
           }else if (huzhu.getRelations().equals("3")){
               mSRelation.setText("儿子");
           }else if (huzhu.getRelations().equals("4")){
               mSRelation.setText("女儿");
           }
           mEtName.setText(huzhu.getName());
           mEtPhone.setText(huzhu.getTel());
           mEtIdDetails.setText(huzhu.getXiangxi());
           if (huzhu.getLabour_type().equals("1")){
               mSForce.setText("是");
           }else if (huzhu.getLabour_type().equals("2")){
               mSForce.setText("否");
           }
           if (huzhu.getIdcard_front()!=null&&huzhu.getIdcard_side()!=null){
               ImageLoaderManager.getInstance().displayImageForView(mIdcard1,huzhu.getIdcard_front());
               ImageLoaderManager.getInstance().displayImageForView(mIdcard2,huzhu.getIdcard_side());
           }
           if (huzhu.getIdcard_gender()!=null&&huzhu.getIdcard_gender().equals("1")){
               mEtSex.setText("男");
           }else if (huzhu.getIdcard_gender()!=null&&huzhu.getIdcard_gender().equals("2")){
               mEtSex.setText("女");
           }
           if (huzhu.getBirth_date()!=null){
               mEtIdBirth.setText(huzhu.getBirth_date());
           }
           if (huzhu.getAge()!=null){
               mEtIdAge.setText(huzhu.getAge());
           }
           if (huzhu.getXiangxi()!=null){
               mEtIdDetails.setText(huzhu.getXiangxi());
           }
       }
    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.msg_btn_back){
            FarmerMsgActivity.this.finish();
        }else if (v.getId()==R.id.msg_edit){
            FarmHomeActivity.instance(this,FarmHomeActivity.class,null);
        }else if (v.getId() ==R.id.msg_edit2){
            EventBus.getDefault().post(new EventMessage(8,"1"));
            startActivity(
                    new Intent(FarmerMsgActivity.this, FarmHomeActivity.class).putExtra("type", 1));
            finish();
        }
    }
}
