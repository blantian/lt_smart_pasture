package com.lantian.lib_docs.farmdoc.view.farmerdata;

import android.os.Bundle;
import android.text.TextUtils;
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
import com.lantian.lib_base.file.sharedpreferences.SharedPreferencesUtils;
import com.lantian.lib_base.database.syncdata.SyncDataStatus;
import com.lantian.lib_base.entity.items.Item;
import com.lantian.lib_base.entity.module.response.farmer.farmhuku.HukuFind;
import com.lantian.lib_base.entity.module.response.farmer.farmlist.HuzhuList;
import com.lantian.lib_base.entity.module.response.farmer.farmmsg.FarmMsgResponse;
import com.lantian.lib_base.utils.EventMessage;
import com.lantian.lib_commin_ui.base.ActivityManagerUtil;
import com.lantian.lib_commin_ui.base.BaseFragmen;
import com.lantian.lib_commin_ui.spinner.SpinnerAdapter;
import com.lantian.lib_docs.R;
import com.lantian.lib_docs.farmdoc.view.havefarm.HaveFarmActivity;
import com.lantian.lib_network.networkstatus.NetWorkStatus;
import com.lantian.lib_network.retrofit2.MyCallBack;
import com.lantian.lib_network.retrofit2.RetrofitHelper;

import org.greenrobot.eventbus.EventBus;

import java.io.Serializable;
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
    private HuzhuList huzhuList;
    private String huzhuid;
    private FarmerMsgActivity farmerMsgActivity;
    private SharedPreferencesUtils sharedPreferencesUtils;

    public static Fragment newInstance(HuzhuList huzhuList) {
        Bundle args = new Bundle();
        if (huzhuList != null){
            args.putSerializable("data",huzhuList);
            BaseMsgFragment fragment = new BaseMsgFragment();
            fragment.setArguments(args);
            return fragment;
        }else {
            BaseMsgFragment fragment = new BaseMsgFragment();
            fragment.setArguments(args);
            return fragment;
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.base_farmmsg_fragmen, null);
        sharedPreferencesUtils = new SharedPreferencesUtils(getContext());
        farmMsgResponseDao = ((MyApp)getActivity().getApplication()).getDaoSession().getFarmMsgResponseDao();
        mEtName = view.findViewById(R.id.et_name);
        mSelectGuanxi = view.findViewById(R.id.select_guanxi);
        mEtPhone = view.findViewById(R.id.et_phone);
        mSelectLaodong = view.findViewById(R.id.select_laodong);
        mBtnSave = view.findViewById(R.id.btn_save);
        mBtnSave.setOnClickListener(this);
        initSpinner();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        farmerMsgActivity = new FarmerMsgActivity();
        initData();
    }


    private void initData() {
        Bundle bundle = getArguments();
        /**
         * 已经有牧户档案的用户
         */
        if (bundle != null){
            Serializable serializable = bundle.getSerializable("data");
            if (serializable != null){
                huzhuList = (HuzhuList) serializable;
                mEtName.setText(huzhuList.getName());
                huzhuid = huzhuList.getId();
                if (huzhuList.getRelations() !=null){
                    mSelectGuanxi.setSelection(Integer.parseInt(huzhuList.getRelations())-1);
                }
                mEtPhone.setText(huzhuList.getTel());
                if (huzhuList.getLabour_type() !=null){
                    mSelectLaodong.setSelection(Integer.parseInt(huzhuList.getLabour_type())-1);
                }
            }
            /**
             * 新用户
             */
        }else {

        }
    }
    private void CheckNetWork() {
        if (!NetWorkStatus.isNetworkAvailable(getContext())){
            addMsgToDatabase();
        }else {
            if (huzhuid !=null){
                flatHuku();
                editMsg();
            }else {
                flatAddHuku();
                addBaseMsg();
            }
        }
    }

    /**添加到数据库**/
    private void addMsgToDatabase() {
        flatAddHuku();
        String userid = (String) sharedPreferencesUtils.getParam("user_id","");
        FarmMsgResponse farmMsgResponse = new FarmMsgResponse();
        farmMsgResponse.setUser_id(userid);
        farmMsgResponse.setName(huzhuList.getName());
        farmMsgResponse.setRelations(huzhuList.getRelations());
        farmMsgResponse.setTel(userphone);
        farmMsgResponse.setLabour_type(huzhuList.getLabour_type());
        farmMsgResponse.setStatus(SyncDataStatus.ADD.ordinal());
        farmMsgResponseDao.insertOrReplace(farmMsgResponse);
        EventBus.getDefault().postSticky(new EventMessage(1,"add"));
        HaveFarmActivity.instance(getContext(),HaveFarmActivity.class,null);
        ActivityManagerUtil.getAppManager().finishActivity(farmerMsgActivity);
    }

    /**
     * 添加户主信息
     */
    public void addBaseMsg(){
        flatAddHuku();
        RetrofitHelper.getApiService().farmMsgResponse(MyApp.Userid,
                huzhuList.getName(),
                huzhuList.getRelations(),userphone,
                huzhuList.getLabour_type()).enqueue(new MyCallBack<FarmMsgResponse>() {
            @Override
            public void success(FarmMsgResponse farmMsgResponse) {
                if (farmMsgResponse != null){
                    farmMsgResponseDao.insertOrReplace(farmMsgResponse);
                    HaveFarmActivity.instance(getContext(),HaveFarmActivity.class,null);
                    ActivityManagerUtil.getAppManager().finishActivity(farmerMsgActivity);
                }
            }
            @Override
            public void failure(String msg) {
                showToast(msg);
            }
        });
    }

    /**
     * 修改信息
     */
    public void editMsg(){
        flatHuku();
        RetrofitHelper.getApiService().editBaseFarmMsg(MyApp.Userid,huzhuList.getName(),
                huzhuList.getRelations(),huzhuList.getTel(),
                huzhuList.getLabour_type(),huzhuList.getId()).enqueue(new MyCallBack<HukuFind>(){
            @Override
            public void success(HukuFind hukuFind) {
                HaveFarmActivity.instance(getContext(),HaveFarmActivity.class,null);
                ActivityManagerUtil.getAppManager().finishActivity(farmerMsgActivity);
            }
            @Override
            public void failure(String msg) {
                showToast(msg);
            }
        });
    }

    /**
     * 修改信息
     */
    private void flatHuku(){
        username = mEtName.getText().toString().trim();
        userphone = mEtPhone.getText().toString().trim();
        if (TextUtils.isEmpty( mEtName.getText())){
            showToast("用户名不能为空！");
        } else if (TextUtils.isEmpty(mEtPhone.getText())) {
            showToast("手机号不能为空！");
        }else {
            huzhuList.setId(huzhuid);
            huzhuList.setName(username);
            huzhuList.setTel(userphone);
            huzhuList.setRelations(((Item) mSelectGuanxi.getSelectedItem()).getValue());
            huzhuList.setLabour_type(((Item) mSelectGuanxi.getSelectedItem()).getValue());
        }

    }

    /**
     * 添加牧户
     */
    private void flatAddHuku(){
        huzhuList = new HuzhuList();
        username = mEtName.getText().toString().trim();
        userphone = mEtPhone.getText().toString().trim();
        if (TextUtils.isEmpty(username)){
            showToast("用户名不能为空！");
        } else if (TextUtils.isEmpty(userphone)){
            showToast("手机号不能为空！");
        }else {
            huzhuList.setName(username);
            huzhuList.setTel(userphone);
            huzhuList.setRelations(((Item) mSelectGuanxi.getSelectedItem()).getValue());
            huzhuList.setLabour_type(((Item) mSelectGuanxi.getSelectedItem()).getValue());
        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_save) {
                CheckNetWork();
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

        if (getActivity()!=null){
            mSelectGuanxi.setAdapter(new SpinnerAdapter(getActivity(), android.R.layout.simple_list_item_1, relations));
            mSelectLaodong.setAdapter(new SpinnerAdapter(getActivity(), android.R.layout.simple_list_item_1, forces));
        }
    }


}
