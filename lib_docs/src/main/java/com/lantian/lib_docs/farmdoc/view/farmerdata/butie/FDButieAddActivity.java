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


public class FDButieAddActivity extends BaseActivity implements View.OnClickListener {

    private EditText mEtItem;
    private EditText mEtMoney;
    private TextView mEtTime;
    private Button mBtnSave;
    private ImageView back;
    private ButieList butieList;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_fdcompensate_add;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        initView();
    }

    private void initView() {
        back = findViewById(R.id.caoyuanadd_btn_back);
        mEtTime = findViewById(R.id.et_time);
        mEtMoney = findViewById(R.id.et_money);
        mEtItem = findViewById(R.id.et_item);
        mBtnSave = findViewById(R.id.btn_save);
        mBtnSave.setOnClickListener(this);
        back.setOnClickListener(this);
        mEtTime.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_save){
            addButie();
        }else if (v.getId() == R.id.et_time){
            timePicker();
        }else if (v.getId()==R.id.caoyuanadd_btn_back){
            ActivityManagerUtil.getAppManager().finishActivity();
        }
    }

    /**
     * 添加补贴
     */
    private void addButie() {
        flatBuite();
        RetrofitHelper.getApiService().addButie(MyApp.Userid,butieList.getTitle()
                ,butieList.getPrice(),butieList.getAddtime()).enqueue(new MyCallBack<ButieList>() {
            @Override
            public void success(ButieList butieList) {
                if (butieList !=null){
                    EventBus.getDefault().postSticky(new EventMessage(1,""));
                    ActivityManagerUtil.getAppManager().finishActivity();
                }
            }
            @Override
            public void failure(String msg) {

            }
        });
    }

    private void flatBuite() {
        butieList = new ButieList();
        butieList.setTitle(mEtItem.getText().toString());
        butieList.setPrice(mEtMoney.getText().toString());
        butieList.setAddtime(mEtTime.getText().toString());
    }

    /**
     * 时间选择
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
