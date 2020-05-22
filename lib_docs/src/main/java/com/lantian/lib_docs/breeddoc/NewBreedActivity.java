package com.lantian.lib_docs.breeddoc;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.lantian.lib_base.MyApp;
import com.lantian.lib_base.database.greendao.BreedIndexDao;
import com.lantian.lib_base.file.sharedpreferences.SharedPreferencesUtils;
import com.lantian.lib_base.entity.module.response.breeds.BreedIndex;
import com.lantian.lib_base.utils.EventMessage;
import com.lantian.lib_base.utils.BaseUtils;
import com.lantian.lib_commin_ui.base.ActivityManagerUtil;
import com.lantian.lib_commin_ui.base.BaseActivity;
import com.lantian.lib_commin_ui.dialog.AlertDialogUtil;
import com.lantian.lib_docs.R;
import com.lantian.lib_docs.breeddoc.adapter.NewBreedAdapter;
import com.lantian.lib_network.retrofit2.MyCallBack;
import com.lantian.lib_network.retrofit2.RetrofitHelper;
import com.lantian.lib_network.utils.NetworkUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sherlock·Holmes on 2020-03-31
 */
public class NewBreedActivity extends BaseActivity implements View.OnClickListener {


    private Context context;
    private ImageView mBnBtnBack;
    private TextView mBreedName;
    private TextView mNremakeDate;
    private RecyclerView mNRecyclerBreedsList;
    private Button mNewBreedDoc;
    private Bundle bundle;
    private BreedIndexDao breedIndexDao;
    private SharedPreferencesUtils sharedPreferencesUtils;
    private String breedid;
    private String userid;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_normol_breed;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        initView();
    }

    private void initView() {
        EventBus.getDefault().register(this);
        breedIndexDao = ((MyApp) BaseUtils.getContext()).getDaoSession().getBreedIndexDao();
        sharedPreferencesUtils = new SharedPreferencesUtils(NewBreedActivity.this);
        bundle = getIntent().getExtras();
        mBnBtnBack = findViewById(R.id.bn_btn_back);
        mBreedName = findViewById(R.id.n_breed_name);
        mNewBreedDoc = findViewById(R.id.new_breed_doc);
        mNremakeDate = findViewById(R.id.nremake_date);
        mNewBreedDoc.setOnClickListener(this);
        mBnBtnBack.setOnClickListener(this);
        mNRecyclerBreedsList = findViewById(R.id.n_recycler_breeds_list);
        mNRecyclerBreedsList.setLayoutManager(new GridLayoutManager(this,1));
        initData();
    }

    private void initData() {
        if (bundle !=null){
            breedid =bundle.getString("breed_id");
            String breedname = bundle.getString("breed_name");
            userid = (String) sharedPreferencesUtils.getParam("user_id","");
            mBreedName.setText(breedname);
            //判断网络
            if (NetworkUtils.isConnected()){
                getBreedData(breedid);
            }else {
                getDatabase();
            }

        }

    }

    private void getDatabase() {
        final List<BreedIndex> breedIndex = breedIndexDao.queryBuilder().where(breedIndexDao.queryBuilder().and(BreedIndexDao.Properties.User_id.eq(userid)
                ,BreedIndexDao.Properties.Breedclass_id.eq(breedid))).list();
        if (breedIndex!=null){
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    mNRecyclerBreedsList.setAdapter(new NewBreedAdapter(breedIndex,context,bundle));
                }
            });
        }else {
            AlertDialogUtil.warningDialog(this,"温馨提示！","请先加载数据哦！");
        }

    }

    /**
     * 页面刷新回调函数
     * @param eventMessage
     */
    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true)
    public void refUI(EventMessage eventMessage){
        if (eventMessage.getMsg()==1&&eventMessage.getMessage().equals("add")){
            Log.e("ceshi","1234");
            if (bundle !=null){
                String breedid =bundle.getString("breed_id");
                getBreedData(breedid);
            }
        }
    }

    /**
     * 获取牲畜信息
     * @param breedid
     */
    private void getBreedData(String breedid) {
        RetrofitHelper.getApiService().getBreedIndex(MyApp.Userid,breedid,"1").enqueue(new MyCallBack<ArrayList<BreedIndex>>() {
            @Override
            public void success(final ArrayList<BreedIndex> breedIndices) {
                breedIndexDao.insertOrReplaceInTx(breedIndices);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mNRecyclerBreedsList.setAdapter(new NewBreedAdapter(breedIndices,context,bundle));
                    }
                });
            }

            @Override
            public void failure(String msg) {
                showToast(msg);
            }
        });
    }

    @Override
    public void onClick(View v) {
        if (v.getId() ==R.id.new_breed_doc){
            AddNewBreedActivity.instance(this,AddNewBreedActivity.class,null);
            ActivityManagerUtil.getAppManager().finishActivity();
        }if (v.getId()==R.id.bn_btn_back){
            ActivityManagerUtil.getAppManager().finishActivity();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
