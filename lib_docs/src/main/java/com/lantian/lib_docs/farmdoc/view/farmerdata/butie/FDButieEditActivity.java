package com.lantian.lib_docs.farmdoc.view.farmerdata.butie;


import android.app.DatePickerDialog;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.lantian.lib_base.MyApp;
import com.lantian.lib_base.entity.module.response.farmer.butie.ButieList;
import com.lantian.lib_base.utils.EventMessage;
import com.lantian.lib_commin_ui.base.ActivityManagerUtil;
import com.lantian.lib_commin_ui.base.BaseActivity;
import com.lantian.lib_docs.R;
import com.lantian.lib_network.retrofit2.MyCallBack;
import com.lantian.lib_network.retrofit2.RetrofitHelper;

import org.greenrobot.eventbus.EventBus;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class FDButieEditActivity extends BaseActivity implements View.OnClickListener {


    private EditText mEtItem;
    private EditText mEtMoney;
    private TextView mEtTime;
    private ImageView back;
    private Button mBtnDel;
    private Button mBtnSave;
    private ButieList addSubsidy;
    private String id;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_fdcompensate_edit;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        initView();
    }

    private void initView() {
            back = findViewById(R.id.compensate_btn_back);
            mEtTime = findViewById(R.id.et_time);
            mEtMoney = findViewById(R.id.et_money);
            mEtItem = findViewById(R.id.et_item);
            mBtnSave = findViewById(R.id.btn_save);
            mBtnDel = findViewById(R.id.btn_del);
            mBtnSave.setOnClickListener(this);
            mEtTime.setOnClickListener(this);
            mBtnDel.setOnClickListener(this);
            back.setOnClickListener(this);
            getBundle();
    }

    private void getBundle() {
        Bundle bundle = getIntent().getExtras();
        ButieList butieList =(ButieList) bundle.getSerializable("butie");
        id = butieList.getId();
        mEtItem.setText(butieList.getTitle());
        mEtMoney.setText(butieList.getPrice());
        mEtTime.setText(butieList.getAddtime());
    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_save){
            flatSubsidy();
            editButie();
        }else if (v.getId() == R.id.et_time){
            timePicker();
        }else if (v.getId() == R.id.btn_del){
            delButie();
        }else if (v.getId()==R.id.compensate_btn_back){
            ActivityManagerUtil.getAppManager().finishActivity();
        }
    }

    /**
     * 删除补贴
     */
    private void delButie() {
        RetrofitHelper.getApiService().delButie("[" + id + "]").enqueue(new MyCallBack<String>() {
            @Override
            public void success(String s) {
                if (s != null){
                    EventBus.getDefault().postSticky(new EventMessage(1,""));
                    ActivityManagerUtil.getAppManager().finishActivity();
                }
            }
            @Override
            public void failure(String msg) {

            }
        });
    }

    /**
     * 修改补贴
     */
    private void editButie() {
        RetrofitHelper.getApiService().editButie(MyApp.Userid,addSubsidy.getTitle()
                ,addSubsidy.getPrice(),addSubsidy.getAddtime(),addSubsidy.getId()).enqueue(new MyCallBack<ButieList>() {
            @Override
            public void success(ButieList butieList) {
                if (butieList !=null){
                    EventBus.getDefault().postSticky(new EventMessage(1,""));
                    ActivityManagerUtil.getAppManager().finishActivity();
                }
            }

            @Override
            public void failure(String msg) {
                showToast(msg);
            }
        });
    }


    private void flatSubsidy() {
        addSubsidy = new ButieList();
        addSubsidy.setId(id);
        addSubsidy.setTitle(mEtItem.getText().toString());
        addSubsidy.setPrice(mEtMoney.getText().toString());
        addSubsidy.setAddtime(mEtTime.getText().toString());
        addSubsidy.setUser_id(MyApp.Userid);
    }

    /**
     * 日期选择
     */
    private void timePicker() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            final DatePickerDialog dialogStart = new DatePickerDialog(this);
            dialogStart.setOnDateSetListener(new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    Calendar time = Calendar.getInstance();
                    time.set(Calendar.YEAR, year);
                    time.set(Calendar.MONTH, month);
                    time.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                    mEtTime.setText(new SimpleDateFormat("YYYY-MM-dd").format(time.getTime()));
                }
            });
            dialogStart.show();
        } else {
            showToast("系统不支持");
        }


    }

}

