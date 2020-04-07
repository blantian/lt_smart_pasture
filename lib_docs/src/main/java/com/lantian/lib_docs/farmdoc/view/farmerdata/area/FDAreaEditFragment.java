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
public class FDAreaEditFragment extends BaseFragmen implements View.OnClickListener {


    private EditText et_name;
    private Spinner s_type;
    private EditText et_area;
    private Spinner s_status;
    private Button btn_del;
    private Button btn_save;
    private addPlan plant;
    private String id;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fdarea_edit, null);
        initView(view);
        return view;
    }

    private void initView(View view) {
        et_name = view.findViewById(R.id.et_name);
        et_area = view.findViewById(R.id.et_area);
        s_status = view.findViewById(R.id.s_status);
        btn_del =view.findViewById(R.id.btn_del);
        btn_save = view.findViewById(R.id.btn_save);
        s_type = view.findViewById(R.id.s_type);

        btn_save.setOnClickListener(this);
        btn_del.setOnClickListener(this);


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (getArguments() != null) {
            id = getArguments().getString("id");
        }
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

        }else if (v.getId() == R.id.btn_del){
            RetrofitHelper.getApiService().delSte("[" + id + "]").enqueue(new MyCallBack<String>() {
                @Override
                public void success(String s) {
                    showToast(s);
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
        plant.setName(et_name.getText().toString());
        plant.setArea(et_area.getText().toString());
        plant.setArea_type(String.valueOf(s_type.getSelectedItemPosition() + 1));
        plant.setSte_type(String.valueOf(s_status.getSelectedItemPosition() + 1));
    }
}
