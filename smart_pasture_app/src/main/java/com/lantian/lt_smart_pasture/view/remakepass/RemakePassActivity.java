package com.lantian.lt_smart_pasture.view.remakepass;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.lantian.lib_commin_ui.base.BaseActivity;
import com.lantian.lt_smart_pasture.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Sherlock·Holmes on 2020-03-06
 */
public class RemakePassActivity extends BaseActivity {
    @BindView(R.id.signprogressbar)
    ProgressBar signprogressbar;
    @BindView(R.id.back)
    ImageButton back;
    @BindView(R.id.phone_num)
    EditText phoneNum;
    @BindView(R.id.getmessage)
    TextView getmessage;
    @BindView(R.id.rempass)
    ImageView rempass;
    @BindView(R.id.remake_pass)
    EditText remakePass;
    @BindView(R.id.retogglebtton)
    ToggleButton retogglebtton;
    @BindView(R.id.remshowerro)
    TextView remshowerro;
    @BindView(R.id.remsurepass)
    ImageView remsurepass;
    @BindView(R.id.sure_remake_pass)
    EditText sureRemakePass;
    @BindView(R.id.retogglebtton2)
    ToggleButton retogglebtton2;
    @BindView(R.id.remshowerro1)
    TextView remshowerro1;
    @BindView(R.id.remphone)
    ImageView remphone;
    @BindView(R.id.message_code)
    EditText messageCode;
    @BindView(R.id.remshowerro2)
    TextView remshowerro2;
    @BindView(R.id.belowbackground)
    ImageView belowbackground;
    @BindView(R.id.company_supports)
    TextView companySupports;
    @BindView(R.id.bttn_remake_pass)
    Button bttnRemakePass;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_remake_pass;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
    }
}
