package com.lantian.lt_smart_pasture.view.mine;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.lantian.lib_commin_ui.base.BaseActivity;
import com.lantian.lt_smart_pasture.R;

/**
 * Created by Sherlock·Holmes on 2020-04-03
 */
public class EdaitPerMsgActivity extends BaseActivity implements View.OnClickListener {


    private ImageView mPerBack;
    private ImageView mPerHeaderIcon;
    private EditText mPerName;
    private EditText mPerEmail;
    private Spinner mPerSex;
    private EditText mPerPhone;
    private Spinner mXiugaiSheng;
    private Spinner mXiugaiShi;
    private Spinner mXiugaiXian;
    private EditText mXiangxidiizhi;
    private Button mBtnSave;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_edit_personal_msg;
    }

    @Override
    protected void init(Bundle savedInstanceState) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) and run LayoutCreator again
    }

    private void initView() {
        mPerBack = (ImageView) findViewById(R.id.per_back);
        mPerHeaderIcon = (ImageView) findViewById(R.id.per_header_icon);
        mPerName = (EditText) findViewById(R.id.per_name);
        mPerEmail = (EditText) findViewById(R.id.per_email);
        mPerSex = (Spinner) findViewById(R.id.per_sex);
        mPerPhone = (EditText) findViewById(R.id.per_phone);
        mXiugaiSheng = (Spinner) findViewById(R.id.xiugai_sheng);
        mXiugaiShi = (Spinner) findViewById(R.id.xiugai_shi);
        mXiugaiXian = (Spinner) findViewById(R.id.xiugai_xian);
        mXiangxidiizhi = (EditText) findViewById(R.id.xiangxidiizhi);
        mBtnSave = (Button) findViewById(R.id.btn_save);

        mBtnSave.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_save:

                break;
            case R.id.per_back:
                EdaitPerMsgActivity.this.finish();
                break;
                default:
        }
    }

    private void submit() {
        // validate
        String name = mPerName.getText().toString().trim();
        if (TextUtils.isEmpty(name)) {
            Toast.makeText(this, "请输入姓名", Toast.LENGTH_SHORT).show();
            return;
        }

        String email = mPerEmail.getText().toString().trim();
        if (TextUtils.isEmpty(email)) {
            Toast.makeText(this, "请输入姓名", Toast.LENGTH_SHORT).show();
            return;
        }

        String phone = mPerPhone.getText().toString().trim();
        if (TextUtils.isEmpty(phone)) {
            Toast.makeText(this, "请输入手机号码", Toast.LENGTH_SHORT).show();
            return;
        }

        String xiangxidiizhiString = mXiangxidiizhi.getText().toString().trim();
        if (TextUtils.isEmpty(xiangxidiizhiString)) {
            Toast.makeText(this, "请输入姓名", Toast.LENGTH_SHORT).show();
            return;
        }


    }
}
