package com.lantian.lib_docs.farmdoc.view.farmerdata.area;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import com.lantian.lib_base.MyApp;
import com.lantian.lib_base.entity.items.Item;
import com.lantian.lib_base.entity.module.response.farmer.plan.CaoyuanList;
import com.lantian.lib_base.utils.EventMessage;
import com.lantian.lib_commin_ui.base.ActivityManagerUtil;
import com.lantian.lib_commin_ui.base.BaseActivity;
import com.lantian.lib_commin_ui.spinner.SpinnerAdapter;
import com.lantian.lib_docs.R;
import com.lantian.lib_network.dialog.DialogUtils;
import com.lantian.lib_network.retrofit2.MyCallBack;
import com.lantian.lib_network.retrofit2.RetrofitHelper;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;

/**
 * Created by Sherlock·Holmes on 2020-04-03
 */
public class FDAreaAddActivity extends BaseActivity implements View.OnClickListener {

    private EditText mEtName;
    private Spinner s_status;
    private EditText mEtArea;
    private ImageView back;
    private Spinner s_type;
    private Button mBtnSave;
    private CaoyuanList plant;
    private DialogUtils dialogUtils;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_fdarea_add;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        initView();
        initSpinner();
    }
    private void initView() {
        dialogUtils = new DialogUtils();
        back = findViewById(R.id.caoyuan_btn_back);
        mEtName = findViewById(R.id.et_name);
        s_type = findViewById(R.id.s_type);
        mEtArea = findViewById(R.id.et_area);
        mBtnSave = findViewById(R.id.btn_save);
        s_status = findViewById(R.id.s_status);
        mBtnSave.setOnClickListener(this);
        back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_save){
            addCaoyuan();
        }
        else if (v.getId() ==R.id.caoyuan_btn_back){
            ActivityManagerUtil.getAppManager().finishActivity();
        }
    }

    /**
     * 添加草原
     */
    private void addCaoyuan() {
        flatPlant();
        dialogUtils.showProgress(FDAreaAddActivity.this,"正在添加...");
        RetrofitHelper.getApiService().addCaoyuanList(MyApp.Userid,plant.getName(),
                plant.getArea(),plant.getArea_type(),plant.getSte_type()).enqueue(new MyCallBack<CaoyuanList>() {
            @Override
            public void success(CaoyuanList caoyuanList) {
                if (caoyuanList !=null){
                    dialogUtils.dismissProgress();
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

    private void flatPlant() {
        plant = new CaoyuanList();
        plant.setId(plant.hashCode() + "");
        plant.setUser_id(MyApp.Userid);
        plant.setName(mEtName.getText().toString());
        plant.setArea(mEtArea.getText().toString());
        plant.setArea_type(((Item)s_type.getSelectedItem()).getValue());
        plant.setSte_type(((Item)s_status.getSelectedItem()).getValue());
    }

    private void initSpinner(){
        ArrayList<Item> caoyuanType = new ArrayList<>();
        ArrayList<Item> caoyuanStatus = new ArrayList<>();

        caoyuanType.add(new Item("操场","1"));
        caoyuanType.add(new Item("浇地","2"));

        caoyuanStatus.add(new Item("自用","1"));
        caoyuanStatus.add(new Item("出租","2"));
        caoyuanStatus.add(new Item("闲置","3"));

        s_status.setAdapter(new SpinnerAdapter(this,R.layout.spinner_item,caoyuanStatus));
        s_type.setAdapter(new SpinnerAdapter(this,R.layout.spinner_item,caoyuanType));

    }
}
