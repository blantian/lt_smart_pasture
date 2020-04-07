package com.lantian.lib_docs.farmdoc.view.farmerdata.subsidy;

import android.app.DatePickerDialog;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.lantian.lib_base.MyApp;
import com.lantian.lib_base.entity.module.response.farmer.subsidy.addSubsidy;
import com.lantian.lib_commin_ui.base.BaseFragmen;
import com.lantian.lib_docs.R;
import com.lantian.lib_network.retrofit2.MyCallBack;
import com.lantian.lib_network.retrofit2.RetrofitHelper;

import java.text.SimpleDateFormat;
import java.util.Calendar;


public class FDCompensateAddFragment extends BaseFragmen implements View.OnClickListener {

    private EditText mEtItem;
    private EditText mEtMoney;
    private TextView mEtTime;
    private Button mBtnSave;
    private addSubsidy addSubsidy;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fdcompensate_add, null);
        initView(view);
        return view;
    }

    private void initView(View view) {
       mEtTime = view.findViewById(R.id.et_time);
       mEtMoney = view.findViewById(R.id.et_money);
       mEtItem = view.findViewById(R.id.et_item);
       mBtnSave = view.findViewById(R.id.btn_save);
       mBtnSave.setOnClickListener(this);
       mEtTime.setOnClickListener(this);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_save){
            flatSubsidy();
            RetrofitHelper.getApiService().addSubsidy(addSubsidy.getUser_id(),
                    addSubsidy.getTitle(),
                    addSubsidy.getPrice(),
                    addSubsidy.getAddtime()).enqueue(new MyCallBack<addSubsidy>() {
                @Override
                public void success(addSubsidy addSubsidy) {
                }

                @Override
                public void failure(String msg) {

                }
            });
        }else if (v.getId() == R.id.et_time){
            timePicker();
        }
    }

    private void flatSubsidy() {
        addSubsidy = new addSubsidy();
        addSubsidy.setId(addSubsidy.hashCode() + "");
        addSubsidy.setTitle(mEtItem.getText().toString());
        addSubsidy.setPrice(mEtMoney.getText().toString());
        addSubsidy.setAddtime(mEtTime.getText().toString());
        addSubsidy.setUser_id(MyApp.Userid);
    }

    private void timePicker() {


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {

            final DatePickerDialog dialogStart = new DatePickerDialog(getContext());
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
