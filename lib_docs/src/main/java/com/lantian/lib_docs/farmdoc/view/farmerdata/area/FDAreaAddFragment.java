package com.lantian.lib_docs.farmdoc.view.farmerdata.area;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.lantian.lib_base.MyApp;
import com.lantian.lib_base.entity.module.response.farmer.plan.addPlan;
import com.lantian.lib_commin_ui.base.BaseFragmen;
import com.lantian.lib_docs.R;
import com.lantian.lib_network.retrofit2.MyCallBack;
import com.lantian.lib_network.retrofit2.RetrofitHelper;

/**
 * Created by Sherlock·Holmes on 2020-04-03
 */
public class FDAreaAddFragment extends BaseFragmen implements View.OnClickListener {

    private EditText mEtName;
    private Spinner mSType;
    private EditText mEtArea;
    private Spinner mSStatus;
    private Button mBtnSave;
    private addPlan plant;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fdarea_add, null);
        initView(view);
        return view;
    }

    private void initView(View view) {
        mEtName = view.findViewById(R.id.et_name);
        mSType = view.findViewById(R.id.s_type);
        mEtArea = view.findViewById(R.id.et_area);
        mBtnSave = view.findViewById(R.id.btn_save);
        mSStatus = view.findViewById(R.id.s_status);
        mBtnSave.setOnClickListener(this);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_save){
            flatPlant();
            RetrofitHelper.getApiService().addSte(plant.getUser_id()
                    ,plant.getName(),plant.getArea(),plant.getArea_type()+"",
                    plant.getSte_type()+"").enqueue(new MyCallBack<addPlan>() {
                @Override
                public void success(addPlan addPlan) {
                    showToast("保存成功");
                }

                @Override
                public void failure(String msg) {

                }
            });
        }
    }

    private void flatPlant() {
        plant = new addPlan();
        plant.setId(plant.hashCode() + "");
        plant.setUser_id(MyApp.Userid);
        plant.setName(mEtName.getText().toString());
        plant.setArea(mEtArea.getText().toString());
        plant.setArea_type(String.valueOf(mSType.getSelectedItemPosition() + 1));
        plant.setSte_type(String.valueOf(mSStatus.getSelectedItemPosition() + 1));
    }
}
