package com.lantian.lt_smart_pasture.view.mine;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.lantian.lib_base.MyApp;
import com.lantian.lib_base.entity.PersonalInfo;
import com.lantian.lib_base.entity.module.response.userinfo.UserInfo;
import com.lantian.lib_commin_ui.base.BaseActivity;
import com.lantian.lib_network.retrofit2.MyCallBack;
import com.lantian.lib_network.retrofit2.RetrofitHelper;
import com.lantian.lt_smart_pasture.R;

/**
 * Created by Sherlock·Holmes on 2020-04-03
 */
public class personalActivity extends BaseActivity implements View.OnClickListener {

    private ImageView mEarBtnBack;
    private ImageView mUserhead;
    private ImageView mPersonalHead;
    private TextView mPersonalName;
    private TextView mPersonalName0;
    private TextView mShowpersonalname;
    private TextView mShowpersonalsex;
    private TextView mShowpersonalphone;
    private TextView mShowpersonalemail;
    private TextView mShowpersonalidcard;
    private TextView mShowpersonalage;
    private TextView mShowpersonaladress;
    private Button mPersonaledite;
    private PersonalInfo personalInfo;

    private String shengData;
    private String shiData;
    private String xianData;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_personal_msg;
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
        mUserhead = (ImageView) findViewById(R.id.userhead);
        mPersonalHead = (ImageView) findViewById(R.id.personal_head);
        mPersonalName = (TextView) findViewById(R.id.personal_name);
        mPersonalName0 = (TextView) findViewById(R.id.personal_name0);
        mShowpersonalname = (TextView) findViewById(R.id.showpersonalname);
        mShowpersonalsex = (TextView) findViewById(R.id.showpersonalsex);
        mShowpersonalphone = (TextView) findViewById(R.id.showpersonalphone);
        mShowpersonalemail = (TextView) findViewById(R.id.showpersonalemail);
        mShowpersonalidcard = (TextView) findViewById(R.id.showpersonalidcard);
        mShowpersonalage = (TextView) findViewById(R.id.showpersonalage);
        mShowpersonaladress = (TextView) findViewById(R.id.showpersonaladress);
        mPersonaledite = (Button) findViewById(R.id.personaledite);
        mPersonaledite.setOnClickListener(this);
        initData();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.personaledite:
                EdaitPerMsgActivity.instance(this,EdaitPerMsgActivity.class,null);
                break;
        }
    }

    public void initData(){
        RetrofitHelper.getApiService().getUserInfo(MyApp.Userid).enqueue(new MyCallBack<UserInfo>() {
            @Override
            public void success(UserInfo userInfo) {
                initTextView(userInfo);
            }

            @Override
            public void failure(String msg) {

            }
        });
    }

    private void initTextView(UserInfo userInfo) {
        mPersonalName.setText(userInfo.getUsername());
        mPersonalName0.setText(userInfo.getUsername());
        mShowpersonalname.setText(userInfo.getUsername());
        if (userInfo.getSex().equals("1")) {
            mShowpersonalsex.setText("男");
        } else {
            mShowpersonalsex.setText("女");
        }
        mShowpersonalphone.setText(userInfo.getPhone());
        if (userInfo.getEmail().equals("")) {
            mShowpersonalemail.setText("请完善信息");
        } else {
            mShowpersonalemail.setText(userInfo.getEmail());
        }
        mShowpersonalidcard.setText(userInfo.getId_card());
        mShowpersonalage.setText(userInfo.getAge());
        shengData = userInfo.getShengdata();
        xianData = userInfo.getXiandata();
        shiData = userInfo.getShidata();
        mShowpersonaladress.setText(shengData + shiData + xianData + userInfo.getAddress());
    }

    public void flatpersonal(){
        personalInfo = new PersonalInfo();
        personalInfo.setName(mShowpersonalname.toString().trim());
        personalInfo.setPhone(mShowpersonalphone.toString().trim());
        personalInfo.setEmail(mShowpersonalemail.toString().trim());
        personalInfo.setId_card(mShowpersonalidcard.toString().trim());
    }
}
