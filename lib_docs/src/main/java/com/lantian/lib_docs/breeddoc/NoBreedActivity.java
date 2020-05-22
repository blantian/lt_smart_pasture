package com.lantian.lib_docs.breeddoc;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.lantian.lib_base.MyApp;
import com.lantian.lib_base.entity.module.response.farmer.farmlist.HuzhuList;
import com.lantian.lib_commin_ui.base.ActivityManagerUtil;
import com.lantian.lib_commin_ui.base.BaseActivity;
import com.lantian.lib_commin_ui.dialog.AlertDialogUtil;
import com.lantian.lib_docs.R;
import com.lantian.lib_docs.farmdoc.view.home.FarmHomeActivity;
import com.lantian.lib_network.retrofit2.MyCallBack;
import com.lantian.lib_network.retrofit2.RetrofitHelper;

import java.util.ArrayList;

/**
 * Created by Sherlock·Holmes on 2020-03-19
 */
public class NoBreedActivity extends BaseActivity implements View.OnClickListener {

    private ImageView farmerdocBack;
    private Button creatFarmdocBttn;
    private boolean status = false;
    private String breed_id;
    private String[] names = new String[]{"确定","取消"};

    @Override
    protected int getLayoutId() {
        return R.layout.activity_new_breed;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        initView();
    }

    private void initView() {
        farmerdocBack = findViewById(R.id.farm_btn_back);
        creatFarmdocBttn = findViewById(R.id.creatFarmdocBttn);
        farmerdocBack.setOnClickListener(this);
        creatFarmdocBttn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId()==R.id.farm_btn_back){
            ActivityManagerUtil.getAppManager().finishActivity();
        }else if (v.getId() ==R.id.creatFarmdocBttn){
            status = checkHuku();
            if (status){
                AlertDialogUtil.TwoChoiceDialog(NoBreedActivity.this, "温馨提示！", "您还没建立牧户档案，建立牧户档案后才能建立养殖档案哦。是否马上建立牧户档案？", names, new AlertDialogUtil.TwoChoiceHandle() {
                    @Override
                    public void onPositiveButtonHandle() {
                        Bundle bundle = new Bundle();
                        bundle.putString("NoFarm","0");
                        FarmHomeActivity.instance(NoBreedActivity.this,FarmHomeActivity.class,bundle);
                        ActivityManagerUtil.getAppManager().finishActivity();
                    }
                    @Override
                    public void onNegativeButtonHandle() {

                    }
                });
            }else {
                AddNewBreedActivity.instance(NoBreedActivity.this,AddNewBreedActivity.class,null);
                ActivityManagerUtil.getAppManager().finishActivity();
            }

        }
    }


    /**
     * 检查用户是否建立牧户档案
     * @return
     */
    private boolean checkHuku(){
        RetrofitHelper.getApiService().getHuZhu(MyApp.Userid).enqueue(new MyCallBack<ArrayList<HuzhuList>>() {
            @Override
            public void success(ArrayList<HuzhuList> huzhuLists) {
                if (huzhuLists!=null){
                    if (huzhuLists.size()==0){
                        status = false;
                    }else {
                        breed_id = huzhuLists.get(0).getId();
                        status =true;
                    }
                }else {
                    status = true;
                }
            }

            @Override
            public void failure(String msg) {

            }
        });
        return status;
    }

}
