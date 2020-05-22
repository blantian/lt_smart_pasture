package com.lantian.lt_smart_pasture.view.login;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Selection;
import android.text.Spannable;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.text.method.TransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.lantian.lib_base.MyApp;
import com.lantian.lib_base.database.greendao.LoginResponseDao;
import com.lantian.lib_base.file.sharedpreferences.SharedPreferencesHelper;
import com.lantian.lib_base.file.sharedpreferences.SharedPreferencesUtils;
import com.lantian.lib_base.entity.module.response.login.LoginResponse;
import com.lantian.lib_base.utils.BaseUtils;
import com.lantian.lib_commin_ui.base.BaseActivity;
import com.lantian.lib_commin_ui.dialog.AlertDialogUtil;
import com.lantian.lib_commin_ui.utils.SubmitControler;
import com.lantian.lib_network.common.BasicResponse;
import com.lantian.lib_network.common.ErroCode;
import com.lantian.lib_network.common.ResponseObserver;
import com.lantian.lib_network.networkstatus.NetWorkStatus;
import com.lantian.lib_network.retrofit2.MyCallBack;
import com.lantian.lib_network.retrofit2.RetrofitHelper;
import com.lantian.lib_network.utils.RxUtil;
import com.lantian.lt_smart_pasture.R;
import com.lantian.lt_smart_pasture.view.home.HomeActivity;
import com.lantian.lt_smart_pasture.view.regist.RegistActivity;
import com.lantian.lt_smart_pasture.view.remakepass.RemakePassActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Sherlock·Holmes on 2020-03-06
 */
public class LoginActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.progressbar)
    ProgressBar mProgressbar;
    @BindView(R.id.user_name_login)
    EditText userNameLogin;
    @BindView(R.id.passerro)
    ImageView mPasserro;
    @BindView(R.id.user_pass_login)
    EditText userPassLogin;
    @BindView(R.id.togglebtton)
    ToggleButton mTogglebtton;
    @BindView(R.id.showerro)
    TextView mShowerro;
    @BindView(R.id.bttn_sign_login)
    TextView bttnSignLogin;
    @BindView(R.id.remmber_pass)
    TextView mRemmberPass;
    @BindView(R.id.bttn_loign_in)
    Button mBttnLoignIn;
    @BindView(R.id.company_support)
    TextView mCompaySupport;

    private String account;
    private String password;
    private static final int TWO_G = 2;
    private static final int THERE_G = 3;
    private static final int FORE_G = 4;
    private static final int WIFI = 1;
    private static final int LAN = 5;

    private String[] buttnStr = {"确定","取消"};
    private String HAS_ACTICATE = "2";

    private List<LoginResponse> localData =new ArrayList<>();
    private static final String TAG = "LoginActivity";
    private static final int REQUEST_CODE_GO_TO_REGIST = 1;
    private static final int REQUEST_CODE_GO_TO_REMAKPASS = 2;
    private LoginResponseDao loginResponseDao;
    private SharedPreferencesUtils sharedPreferencesUtils;


    @Override
    protected int getLayoutId() {
       return R.layout.activity_login;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        ButterKnife.bind(this);
        initView();
    }


    private void initView() {
        loginResponseDao =((MyApp)getApplication()).getDaoSession().getLoginResponseDao();
        sharedPreferencesUtils = new SharedPreferencesUtils(this);
        SubmitControler submitControler = new SubmitControler();
        submitControler.initConroller();
        submitControler.addView(userNameLogin,userPassLogin);
        submitControler.setSubmitButton(mBttnLoignIn);
        mBttnLoignIn.setOnClickListener(this);
        bttnSignLogin.setOnClickListener(this);
        mRemmberPass.setOnClickListener(this);
        mTogglebtton.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bttn_loign_in:
                NetWorkStatus();
                sharedPreferencesUtils.setParam("username",userNameLogin.getText().toString());
                sharedPreferencesUtils.setParam("password",userPassLogin.getText().toString());
                SharedPreferencesHelper.put(LoginActivity.this,"user_name",userNameLogin.getText().toString());
                SharedPreferencesHelper.put(LoginActivity.this,"password",userPassLogin.getText().toString());
                break;
                /**隐藏显示密码**/
            case R.id.togglebtton:
                TransformationMethod method = userPassLogin.getTransformationMethod();
                if (method == HideReturnsTransformationMethod.getInstance()) {
                    userPassLogin.setTransformationMethod(PasswordTransformationMethod.getInstance());
                } else {
                    userPassLogin.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }
                // 保证切换后光标位于文本末尾
                Spannable spanText = userPassLogin.getText();
                if (spanText != null) {
                    Selection.setSelection(spanText, spanText.length());
                }
                break;
            case R.id.bttn_sign_login:
                Intent intent = new Intent(LoginActivity.this, RegistActivity.class);
                startActivityForResult(intent, REQUEST_CODE_GO_TO_REGIST);
                break;
            case R.id.remmber_pass:
                Intent intents = new Intent(LoginActivity.this, RemakePassActivity.class);
                startActivityForResult(intents, REQUEST_CODE_GO_TO_REMAKPASS);
                break;
            default:
        }
    }

    /**
     * 回调函数
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case REQUEST_CODE_GO_TO_REGIST:
                if (resultCode == Activity.RESULT_OK) {
                    //则获取data中的账号和密码  动态设置到EditText中
                    String name = data.getStringExtra("name");
                    String pwd = data.getStringExtra("pwd");
                    userNameLogin.setText(name);
                    userPassLogin.setText(pwd);
                }
                break;
            case REQUEST_CODE_GO_TO_REMAKPASS:
                if (resultCode == Activity.RESULT_OK) {
                    //则获取data中的账号和密码  动态设置到EditText中
                    String name = data.getStringExtra("rname");
                    String pwd = data.getStringExtra("rpwd");
                    userNameLogin.setText(name);
                    userPassLogin.setText(pwd);
                }
                break;
            default:
                break;
        }
    }

    /**
     * Post请求
     */
   public void login(){
        RetrofitHelper.getApiService()
                .login(userNameLogin.getText().toString(),userPassLogin.getText().toString())
                .compose(RxUtil.rxSchedulerHelper(this,true))
                .subscribe(new ResponseObserver<BasicResponse<LoginResponse>>() {
                    @Override
                    public void onSuccess(BasicResponse<LoginResponse> response) {
                        if (response.getStatus() == ErroCode.SUCCESS) {
                            MyApp.Userid = response.getData().getUser_id();
                            sharedPreferencesUtils.setParam("user_id",response.getData().getUser_id());
                            sharedPreferencesUtils.setParam("isAdmin",response.getData().getUserdata().getIs_admin());
                            MyApp.isAdmin = response.getData().getUserdata().getIs_admin();
                            loginResponseDao.insertOrReplace(response.getData());

                            if (response.getData().getUserdata().getAppact_type().equals(HAS_ACTICATE)){
                                HomeActivity.instance(LoginActivity.this,HomeActivity.class,null);
                            }else {
                                /**激活用户**/
                                AlertDialogUtil.TwoChoiceDialog(LoginActivity.this, "激活用户", "您是否要激活用户？", buttnStr, new AlertDialogUtil.TwoChoiceHandle() {
                                    @Override
                                    public void onPositiveButtonHandle() {
                                        activateUser();
                                        HomeActivity.instance(LoginActivity.this,HomeActivity.class,null);
                                    }
                                    @Override
                                    public void onNegativeButtonHandle() {
                                        HomeActivity.instance(LoginActivity.this,HomeActivity.class,null);
                                    }
                                });
                            }

                        }else {
                            showToast(response.getMessage());
                        }
                    }
                });
    }

    /**
     * 激活用户
     */
    private void activateUser() {
        RetrofitHelper.getApiService().activateUser(MyApp.Userid).enqueue(new MyCallBack<String>() {
            @Override
            public void success(String s) {
            }
            @Override
            public void failure(String msg) {
            }
        });
    }


    /**
     * 判断网络状态
     */
    public void NetWorkStatus(){
       if (NetWorkStatus.isNetworkAvailable(this)){
          int status = NetWorkStatus.isNetWorkStatus(this);
          Log.e("Status","" + status);
          /**互联网**/
           if (status == WIFI || status == FORE_G || status == THERE_G){
               login();
              /**局域网**/
           }else if (status == LAN){
               AlertDialogUtil.TwoChoiceDialog(this, "局域网", "您处于局域网状态，是否离线登录？",
                       buttnStr, new AlertDialogUtil.TwoChoiceHandle() {
                   @Override
                   public void onPositiveButtonHandle() {
                       CheckUser();
                       HomeActivity.instance(LoginActivity.this,HomeActivity.class,null);
                   }
                   @Override
                   public void onNegativeButtonHandle() {
                   }
               });
               /**2G或者无网络**/
           }else {
               AlertDialogUtil.TwoChoiceDialog(this, "网络不佳", "您的网络不佳，是否离线登录？",
                       buttnStr, new AlertDialogUtil.TwoChoiceHandle() {
                           @Override
                           public void onPositiveButtonHandle() {
                               //CheckUser();
                           }
                           @Override
                           public void onNegativeButtonHandle() {

                           }
                       });
           }
       }else {
           Log.e("没网络：","**********");
           AlertDialogUtil.TwoChoiceDialog(this, "网络不佳", "您的网络不佳，是否离线登录？",
                   buttnStr, new AlertDialogUtil.TwoChoiceHandle() {
                       @Override
                       public void onPositiveButtonHandle() {
                           CheckUser();
                       }
                       @Override
                       public void onNegativeButtonHandle() {

                       }
                   });
       }
    }

    /**
     * 离线登录
     */
    private void CheckUser() {
        account = userNameLogin.getText().toString();
        password = userPassLogin.getText().toString();
        LoginResponse loginResponse = loginResponseDao.queryBuilder().where(LoginResponseDao.Properties.Username.eq(account)).unique();
        if (loginResponse == null){
            AlertDialogUtil.warningDialog(LoginActivity.this,"友情提示","请先有网络状态下登录，加载数据哦！");
        }else {
                if (loginResponse.getUsername().equals(account)&&
                        loginResponse.getUserdata().getPassword().equals(BaseUtils.getMD5(password))){
                    HomeActivity.instance(LoginActivity.this,HomeActivity.class,null);
                }else {
                    showToast("用户名或密码错误！");
                }
        }

    }
    
    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onStop() {
        super.onStop();
        finish();
    }
}
