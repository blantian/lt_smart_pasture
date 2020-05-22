package com.lantian.lt_smart_pasture.view.regist;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.Selection;
import android.text.Spannable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.text.method.TransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.lantian.lib_base.entity.module.response.regist.RegistResponse;
import com.lantian.lib_commin_ui.base.BaseActivity;
import com.lantian.lib_commin_ui.utils.CheckPhoneNum;
import com.lantian.lib_commin_ui.utils.SubmitControler;
import com.lantian.lib_network.retrofit2.MyCallBack;
import com.lantian.lib_network.retrofit2.RetrofitHelper;
import com.lantian.lt_smart_pasture.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Sherlock·Holmes on 2020-03-06
 */
public class RegistActivity extends BaseActivity implements View.OnClickListener, View.OnFocusChangeListener {


    @BindView(R.id.back)
    ImageButton mBack;
    @BindView(R.id.user_icon)
    ImageView userIcon;
    @BindView(R.id.user_name_sign)
    EditText userNameSign;
    @BindView(R.id.sign_show_erro)
    TextView signShowErro;
    @BindView(R.id.pass_icon)
    ImageView passIcon;
    @BindView(R.id.user_pass_sign)
    EditText userPassSign;
    @BindView(R.id.toggle_btton_sign)
    ToggleButton toggleBttonSign;
    @BindView(R.id.sign_show_erro_1)
    TextView signShowErro1;
    @BindView(R.id.sure_pass_icon)
    ImageView surePassIcon;
    @BindView(R.id.sure_pass_sign)
    EditText surePassSign;
    @BindView(R.id.toggle_btton_2)
    ToggleButton toggleBtton2;
    @BindView(R.id.sign_show_erro2)
    TextView signShowErro2;
    @BindView(R.id.phone_icon)
    ImageView phoneIcon;
    @BindView(R.id.user_phone_sign)
    EditText userPhoneSign;
    @BindView(R.id.sgin_show_erro3)
    TextView sginShowErro3;
    @BindView(R.id.check_box)
    CheckBox checkBox;
    @BindView(R.id.see_treaty)
    TextView seeTreaty;
    @BindView(R.id.btt_sign_up)
    Button bttSignUp;

    private static final int PHONE_TENGTH = 11;
    private static final int PASS_TENGTH =7;
    private  String phone;
    private String username;
    private String userpass;
    private String surepass;
    private String phoneMessage;
    private String userMessage;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_regist;
    }
    @Override
    protected void init(Bundle savedInstanceState) {
        ButterKnife.bind(this);
        initView();
    }
    private void initView() {
        SubmitControler submitControl = new SubmitControler();
        submitControl.initConroller();
        submitControl.addView(userNameSign,userPassSign,userPhoneSign,surePassSign,checkBox);
        submitControl.setSubmitButton(bttSignUp);
        bttSignUp.setOnClickListener(this);
        toggleBttonSign.setOnClickListener(this);
        toggleBtton2.setOnClickListener(this);
        userNameSign.setOnFocusChangeListener(this);
        userPassSign.setOnFocusChangeListener(this);
        surePassSign.setOnFocusChangeListener(this);
        userPhoneSign.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                 phone = userPhoneSign.getText().toString();
                if (!CheckPhoneNum.isMobileNO(phone) && phone.length()==PHONE_TENGTH) {
                    sginShowErro3.setText("手机号码格式不正确！");
                    userPhoneSign.setBackground(getResources().getDrawable(R.drawable.edittexterro));
                    phoneIcon.setBackground(getResources().getDrawable(R.drawable.ic_action_phone_erro));
                }else {
                    userPhoneSign.setBackground(getResources().getDrawable(R.drawable.edittext));
                    phoneIcon.setBackground(getResources().getDrawable(R.drawable.ic_action_phone));
                    sginShowErro3.setText("");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        phone = userPhoneSign.getText().toString();
        switch (v.getId()){
            case R.id.btt_sign_up:
                regist();
                break;
            case R.id.toggle_btton_sign:
                TransformationMethod method = userPassSign.getTransformationMethod();
                if (method == HideReturnsTransformationMethod.getInstance()) {
                    userPassSign.setTransformationMethod(PasswordTransformationMethod.getInstance());
                } else {
                    userPassSign.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }
                // 保证切换后光标位于文本末尾
                Spannable spanText = userPassSign.getText();
                if (spanText != null) {
                    Selection.setSelection(spanText, spanText.length());
                }
                break;
            case R.id.toggle_btton_2:
                TransformationMethod method2 = surePassSign.getTransformationMethod();
                if (method2 == HideReturnsTransformationMethod.getInstance()) {
                    surePassSign.setTransformationMethod(PasswordTransformationMethod.getInstance());
                } else {
                    surePassSign.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }
                // 保证切换后光标位于文本末尾
                Spannable spanText2 = surePassSign.getText();
                if (spanText2 != null) {
                    Selection.setSelection(spanText2, spanText2.length());
                }
                break;
            case R.id.back:
                RegistActivity.this.finish();
                break;
            case R.id.check_box:
                default:

        }
    }
    /**
     * 用户注册
     */
    private void regist() {
        phone = userPhoneSign.getText().toString();
        username = userNameSign.getText().toString().trim();
        userpass = userPassSign.getText().toString().trim();
        RetrofitHelper.getApiService().singn(username,userpass,phone).enqueue(new MyCallBack<RegistResponse>() {
            @Override
            public void success(RegistResponse registResponse) {
                Intent data =new Intent();
                data.putExtra("name", username);
                data.putExtra("pwd", userpass);
                setResult(Activity.RESULT_OK, data);
                finish();
            }

            @Override
            public void failure(String msg) {
                showToast(msg);
            }
        });

        }
    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        surepass = surePassSign.getText().toString();
        username = userNameSign.getText().toString();
        userpass = userPassSign.getText().toString();
        switch (v.getId()){
            case R.id.user_name_sign:
                /*if (!hasFocus && username.length()!=0){
                            signShowErro.setText(CheckMsg.getCheckMsg().CheckUser(username));
                            Log.e("UUUU",CheckMsg.getCheckMsg().CheckUser(username));
                            userNameSign.setBackground(getResources().getDrawable(R.drawable.edittexterro));
                            userIcon.setBackground(getResources().getDrawable(R.drawable.ic_action_user_erro));
                } else {
                            userNameSign.setBackground(getResources().getDrawable(R.drawable.edittext));
                            userIcon.setBackground(getResources().getDrawable(R.drawable.ic_action_user));
                            signShowErro.setText("");
                }
                 */
                if (!hasFocus && TextUtils.isEmpty(userNameSign.getText().toString().trim())){
                    signShowErro.setText("用户名不能为空！");
                    userNameSign.setBackground(getResources().getDrawable(R.drawable.edittexterro));
                    userIcon.setBackground(getResources().getDrawable(R.drawable.ic_action_user_erro));
                }else {
                    userNameSign.setBackground(getResources().getDrawable(R.drawable.edittext));
                    userIcon.setBackground(getResources().getDrawable(R.drawable.ic_action_user));
                    signShowErro.setText("");
                }
                break;
            case R.id.user_pass_sign:
                if (!hasFocus && TextUtils.isEmpty(userPassSign.getText().toString().trim())){
                    signShowErro1.setText("密码不能为空！");
                    userPassSign.setBackground(getResources().getDrawable(R.drawable.edittexterro));
                    passIcon.setBackground(getResources().getDrawable(R.drawable.ic_action_pass_erro));
                }else if (userpass.length()<PASS_TENGTH & userpass.length()!=0) {
                    signShowErro1.setText("密码太短！");
                    userPassSign.setBackground(getResources().getDrawable(R.drawable.edittexterro));
                    passIcon.setBackground(getResources().getDrawable(R.drawable.ic_action_pass_erro));
                }else {
                    userPassSign.setBackground(getResources().getDrawable(R.drawable.edittext));
                    passIcon.setBackground(getResources().getDrawable(R.drawable.ic_action_password));
                    signShowErro1.setText("");
                }
                break;
            case R.id.sure_pass_sign:
                if (!hasFocus && TextUtils.isEmpty(surePassSign.getText().toString().trim())){
                    signShowErro2.setText("密码不能为空！");
                    surePassSign.setBackground(getResources().getDrawable(R.drawable.edittexterro));
                    surePassIcon.setBackground(getResources().getDrawable(R.drawable.ic_action_pass_erro));
                }else {
                    userPassSign.setBackground(getResources().getDrawable(R.drawable.edittext));
                    surePassIcon.setBackground(getResources().getDrawable(R.drawable.ic_action_password));
                    signShowErro2.setText("");
                }
                if(!surepass.equals(userpass)&surepass.length()!=0){
                    signShowErro2.setText("您输入的密码不一致！");
                    surePassSign.setBackground(getResources().getDrawable(R.drawable.edittexterro));
                    surePassIcon.setBackground(getResources().getDrawable(R.drawable.ic_action_pass_erro));
                }else {
                    userPassSign.setBackground(getResources().getDrawable(R.drawable.edittext));
                    surePassIcon.setBackground(getResources().getDrawable(R.drawable.ic_action_password));
                    signShowErro2.setText("");
                }
            case R.id.user_phone_sign:
               /* if (!hasFocus && phone.length()==0){
                        phoneMessage = CheckMsg.getCheckMsg().CheckPhone(phone);
                        Log.e("phoneMessage",phoneMessage);
                        sginShowErro3.setText(phoneMessage);
                        userPhoneSign.setBackground(getResources().getDrawable(R.drawable.edittexterro));
                        phoneIcon.setBackground(getResources().getDrawable(R.drawable.ic_action_phone_erro));
                }else {
                        userPhoneSign.setBackground(getResources().getDrawable(R.drawable.edittext));
                        phoneIcon.setBackground(getResources().getDrawable(R.drawable.ic_action_phone));
                        sginShowErro3.setText("");
                }
                */
                break;
                default:

        }
    }


}

