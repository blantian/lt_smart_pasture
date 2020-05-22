package com.lantian.lib_docs.breeddoc.dabiaoyonghu;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.lantian.lib_base.MyApp;
import com.lantian.lib_base.database.greendao.BreedIndexDao;
import com.lantian.lib_base.entity.module.response.breeds.BreedIndex;
import com.lantian.lib_base.utils.BaseUtils;
import com.lantian.lib_commin_ui.base.ActivityManagerUtil;
import com.lantian.lib_commin_ui.base.BaseActivity;
import com.lantian.lib_docs.R;
import com.lantian.lib_docs.breeddoc.adapter.NewBreedAdapter;
import com.lantian.lib_network.retrofit2.MyCallBack;
import com.lantian.lib_network.retrofit2.RetrofitHelper;
import com.lantian.lib_network.utils.NetworkUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sherlock·Holmes on 2020-03-31
 */
public class SingleBreedActivity extends BaseActivity implements View.OnClickListener {

    private static final String DAO_BIAO_USER = "5";
    private Context context;
    private ImageView mBBtnBack;
    private String Userid;
    private TextView mBreedName;
    private TextView mRemakeDate;
    private RecyclerView mRecyclerBreedsList;
    private Bundle bundle;
    private BreedIndexDao breedIndexDao;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_dabiao_breeds_doc;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        initView();
    }

    private void initView() {
        breedIndexDao = ((MyApp) BaseUtils.getContext()).getDaoSession().getBreedIndexDao();
        bundle = getIntent().getExtras();
        mBBtnBack = findViewById(R.id.b_btn_back);
        mBreedName = findViewById(R.id.breed_name);
        mRemakeDate = findViewById(R.id.remake_date);
        mRecyclerBreedsList = findViewById(R.id.recycler_breeds_list);
        mRecyclerBreedsList.setLayoutManager(new GridLayoutManager(this,1));
        mBBtnBack.setOnClickListener(this);
        initData();
    }

    private void initData() {
        String breedid =bundle.getString("breed_id");
        String breedname = bundle.getString("breed_name");
        mBreedName.setText(breedname);
        /**打标用户**/
        if (MyApp.isAdmin.equals(DAO_BIAO_USER)){
            Userid = MyApp.DaBiaoUserid;
            /**普通用户**/
        }else {
            Userid = MyApp.Userid;
        }

        if (NetworkUtils.isConnected()){
            getBreedData(breedid,Userid);
        }else {
            getDataBaseData(Userid);
        }

    }

    /**
     * 数据库获取养殖牲畜详细信息
     * @param userid
     */
    private void getDataBaseData(String userid) {
        List<BreedIndex> breedIndexList = breedIndexDao.queryBuilder().where(BreedIndexDao.Properties.User_id.eq(userid)).list();
        mRecyclerBreedsList.setAdapter(new NewBreedAdapter(breedIndexList,context,bundle));
    }

    /**
     * 获取用户下的养殖详细信息
     * @param breedid 牲畜种类id
     * @param userid 牧户id
     */
    private void getBreedData(String breedid,String userid) {
        RetrofitHelper.getApiService().getBreedIndex(userid,breedid,"1").enqueue(new MyCallBack<ArrayList<BreedIndex>>() {
            @Override
            public void success(final ArrayList<BreedIndex> breedIndices) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mRecyclerBreedsList.setAdapter(new NewBreedAdapter(breedIndices,context,bundle));
                    }
                });
            }

            @Override
            public void failure(String msg) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        if (v.getId() ==R.id.b_btn_back){
            ActivityManagerUtil.getAppManager().finishActivity();
        }
    }
}
