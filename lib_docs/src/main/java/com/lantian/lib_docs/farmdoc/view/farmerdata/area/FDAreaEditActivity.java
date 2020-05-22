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
import com.lantian.lib_network.retrofit2.MyCallBack;
import com.lantian.lib_network.retrofit2.RetrofitHelper;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;

/**
 * Created by Sherlock·Holmes on 2020-04-03
 */
public class FDAreaEditActivity extends BaseActivity implements View.OnClickListener {


    private EditText et_name;
    private Spinner s_type;
    private EditText et_area;
    private Spinner s_status;
    private Button btn_del;
    private Button btn_save;
    private CaoyuanList plant;
    private ImageView back;
    private String id;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_fdarea_edit;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        initView();
    }

    private void initView() {
        back = findViewById(R.id.caoyuan_btn_back);
        et_name = findViewById(R.id.et_name);
        et_area = findViewById(R.id.et_area);
        s_status = findViewById(R.id.s_status);
        btn_del =findViewById(R.id.btn_del);
        btn_save = findViewById(R.id.btn_save);
        s_type = findViewById(R.id.s_type);
        btn_save.setOnClickListener(this);
        btn_del.setOnClickListener(this);
        back.setOnClickListener(this);
        initSpinner();
        getBundle();


    }

    private void getBundle() {
        Bundle bundle = getIntent().getExtras();
        if (bundle!=null){
            CaoyuanList caoyanlist = (CaoyuanList) bundle.getSerializable("caoyuan");
            id = caoyanlist.getId();
            et_area.setText(caoyanlist.getArea());
            et_name.setText(caoyanlist.getName());
            s_type.setSelection(Integer.parseInt(caoyanlist.getArea_type())-1);
            s_status.setSelection(Integer.parseInt(caoyanlist.getSte_type())-1);

        }
    }

    @Override
    public void onClick(View v) {
        flatPlant();
        if (v.getId() == R.id.btn_save){
            editCaoyuanList();
        }else if (v.getId() == R.id.btn_del){
            delCaoyuanList();
        }
    }

    /**
     * 删除草原
     */
    private void delCaoyuanList() {
        RetrofitHelper.getApiService().delCaoyuanList("["+id+"]").enqueue(new MyCallBack<String>() {
            @Override
            public void success(String s) {
                if (s !=null){
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

    /**
     * 修改草原
     */
    private void editCaoyuanList() {
        RetrofitHelper.getApiService().editCaoyuanList(MyApp.Userid,plant.getName(),plant.getArea(),
                plant.getArea_type(),plant.getSte_type(),plant.getId()).enqueue(new MyCallBack<CaoyuanList>() {
            @Override
            public void success(CaoyuanList caoyuanList) {
                if (caoyuanList !=null){
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
        plant.setId(id);
        plant.setUser_id(MyApp.Userid);
        plant.setName(et_name.getText().toString());
        plant.setArea(et_area.getText().toString());
        plant.setArea_type(((Item)s_type.getSelectedItem()).getValue());
        plant.setSte_type(((Item)s_type.getSelectedItem()).getValue());
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
