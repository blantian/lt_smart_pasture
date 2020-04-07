package com.lantian.lib_docs.farmdoc.view.farmerdata;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.lantian.lib_base.MyApp;
import com.lantian.lib_base.database.greendao.FarmMsgResponseDao;
import com.lantian.lib_base.entity.items.Item;
import com.lantian.lib_base.entity.module.response.farmer.farmmsg.FarmMsgResponse;
import com.lantian.lib_commin_ui.base.BaseFragmen;
import com.lantian.lib_commin_ui.spinner.SpinnerAdapter;
import com.lantian.lib_docs.R;
import com.lantian.lib_docs.farmdoc.view.havefarm.HaveFarmActivity;
import com.lantian.lib_network.networkstatus.NetWorkStatus;
import com.lantian.lib_network.retrofit2.MyCallBack;
import com.lantian.lib_network.retrofit2.RetrofitHelper;

import java.util.ArrayList;

/**
 * Created by Sherlock·Holmes on 2020-03-27
 */
public class BaseMsgFragment extends BaseFragmen implements View.OnClickListener {


    private EditText mEtName;
    private Spinner mSelectGuanxi;
    private EditText mEtPhone;
    private Spinner mSelectLaodong;
    private ImageView mBelowbackground;
    private Button mBtnSave;
    private FarmMsgResponseDao farmMsgResponseDao;
    private String username;
    private String userphone;

    public static Fragment newInstance() {
        BaseMsgFragment fragment = new BaseMsgFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.base_farmmsg_fragmen, null);
        farmMsgResponseDao = ((MyApp)getActivity().getApplication()).getDaoSession().getFarmMsgResponseDao();
        mEtName = view.findViewById(R.id.et_name);
        mSelectGuanxi = view.findViewById(R.id.select_guanxi);
        mEtPhone = view.findViewById(R.id.et_phone);
        mSelectLaodong = view.findViewById(R.id.select_laodong);
        mBtnSave = view.findViewById(R.id.btn_save);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        intiListner();
        initSpinner();
    }

    private void intiListner() {
        mBtnSave.setOnClickListener(this);
    }


    private void CheckNetWork() {
        if (!NetWorkStatus.isNetworkAvailable(getContext())){

        }else {
            addBaseMsg();

        }
    }

    public void addBaseMsg(){
        username = mEtName.getText().toString().trim();
        userphone = mEtPhone.getText().toString().trim();
        RetrofitHelper.getApiService().farmMsgResponse(MyApp.Userid,
                username,
                ((Item) mSelectGuanxi.getSelectedItem()).getValue(),userphone,
                ((Item) mSelectLaodong.getSelectedItem()).getValue()).enqueue(new MyCallBack<FarmMsgResponse>() {
            @Override
            public void success(FarmMsgResponse farmMsgResponse) {
                farmMsgResponseDao.insertOrReplace(farmMsgResponse);
                showToast(farmMsgResponse.getName());
                HaveFarmActivity.instance(getContext(),HaveFarmActivity.class,null);
            }

            @Override
            public void failure(String msg) {
                showToast(msg);
            }
        });

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_save) {
            CheckNetWork();
        }else if (v.getId()== R.id.select_guanxi){

        }else if (v.getId() == R.id.select_laodong){

        }
    }

    /**
     * 初始化选项
     */
    private void initSpinner() {

        ArrayList<Item> relations = new ArrayList<>();
        relations.add(new Item("户主", "1"));
        relations.add(new Item("妻子", "2"));
        relations.add(new Item("儿子", "3"));
        relations.add(new Item("女儿", "4"));

        ArrayList<Item> forces = new ArrayList<>();
        forces.add(new Item("是", "1"));
        forces.add(new Item("否", "2"));

        mSelectGuanxi.setAdapter(new SpinnerAdapter(getContext(), android.R.layout.simple_list_item_1, relations));

        mSelectLaodong.setAdapter(new SpinnerAdapter(getContext(), android.R.layout.simple_list_item_1, forces));

    }


}
