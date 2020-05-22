package com.lantian.lib_docs.breeddoc.dabiaoyonghu;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lantian.lib_base.MyApp;
import com.lantian.lib_base.database.greendao.BreedsOfUserDao;
import com.lantian.lib_base.database.greendao.FarmListResponseDao;
import com.lantian.lib_base.file.sharedpreferences.SharedPreferencesUtils;
import com.lantian.lib_base.entity.module.response.breeds.BreedsOfUser;
import com.lantian.lib_base.entity.module.response.farmer.farmlist.FarmListResponse;
import com.lantian.lib_base.thread.ThreadPoolManager;
import com.lantian.lib_base.utils.BaseUtils;
import com.lantian.lib_commin_ui.base.BaseActivity;
import com.lantian.lib_docs.R;
import com.lantian.lib_docs.breeddoc.HaveBreedActivity;
import com.lantian.lib_docs.breeddoc.dabiaoyonghu.adapter.RFarmListAdapter;
import com.lantian.lib_network.common.ErroCode;
import com.lantian.lib_network.dialog.DialogUtils;
import com.lantian.lib_network.retrofit2.MyCallBack;
import com.lantian.lib_network.retrofit2.RetrofitHelper;
import com.lantian.lib_network.utils.NetworkUtils;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Sherlock·Holmes on 2020-03-18
 */

public class FarmListActivity extends BaseActivity{

    private RecyclerView farmList;
    private RFarmListAdapter rfarmListAdapter;
    private GridLayoutManager mLinearLayoutManager;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private List<FarmListResponse> list;
    private FarmListResponseDao farmListResponseDao;
    private SharedPreferencesUtils sharedPreferencesUtils;
    /**用来存储所有牧户人员的用户id**/
    private List<String> userids;
    private BreedsOfUserDao breedsOfUserDao;
    private String userid;
    private Bundle bundle;
    private DialogUtils dialogUtils;

    private int page;
    private boolean isLoadMore;
    private boolean hasBanner;



    @Override
    protected int getLayoutId() {
        return R.layout.activity_farm_list;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        initView();
        Net();
    }


    private void initView() {
        farmListResponseDao = ((MyApp) BaseUtils.getContext()).getDaoSession().getFarmListResponseDao();
        breedsOfUserDao =((MyApp) BaseUtils.getContext()).getDaoSession().getBreedsOfUserDao();
        sharedPreferencesUtils = new SharedPreferencesUtils(this);
        userid = (String) sharedPreferencesUtils.getParam("user_id","");
        userids = new ArrayList<>();
        farmList = findViewById(R.id.farm_list);
       // mSwipeRefreshLayout = findViewById(R.id.spl_farm);
        dialogUtils = new DialogUtils();
        //mSwipeRefreshLayout.setOnRefreshListener(this);
       // mSwipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary, R.color.colorAccent);

    }

    /**
     * 判断网络状态
     */
    private void Net(){
        if (NetworkUtils.isConnected()){
            initData();
        }else {
            initDataBase();
        }
    }

    /**
     * 本地
     */
    private void initDataBase() {
       list = farmListResponseDao.queryBuilder().where(FarmListResponseDao.Properties.DBuser_id.eq(userid)).list();
       initadapter(list);
    }

    /**
     * 请求接口数据
     */
    private void initData() {
        /**牧户列表信息**/
        RetrofitHelper.getApiService().getFarmList(userid)
                .enqueue(new MyCallBack<ArrayList<FarmListResponse>>() {
                    @Override
                    public void success(ArrayList<FarmListResponse> farmListResponses) {
                        if (farmListResponses.size() != 0) {
                            for (int i=0;i<farmListResponses.size();i++){
                                farmListResponses.get(i).setDBuser_id(userid);
                                /**获取所有用户的id**/
                                userids.add(farmListResponses.get(i).getUser_id());
                            }
                            farmListResponseDao.insertOrReplaceInTx(farmListResponses);
                            getBreeds(userids);
                            initadapter(farmListResponses);
                        }
                    }

                    @Override
                    public void failure(String msg) {
                        showToast(msg);
                    }
                });
    }

    /**
     * 给Adapter数据源
     * @param data 数据源（用户列表）
     */
    private void initadapter(final List<FarmListResponse> data) {
        mLinearLayoutManager = new GridLayoutManager(this, 1);
        farmList.setLayoutManager(mLinearLayoutManager);
        //rfarmListAdapter.openLoadAnimation();
        //rfarmListAdapter.setOnLoadMoreListener(this,farmList);
        rfarmListAdapter = new RFarmListAdapter(R.layout.farmlist_item, data);
        farmList.setAdapter(rfarmListAdapter);
        rfarmListAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                bundle = new Bundle();
                /**获取被选用户的信息**/
                bundle.putSerializable("data", data.get(position));
                MyApp.DaBiaoUserid = rfarmListAdapter.getData().get(position).getUser_id();
                sharedPreferencesUtils.setParam("db_userid",rfarmListAdapter.getData().get(position).getUser_id());
                Log.e("dabiao",MyApp.DaBiaoUserid);
                HaveBreedActivity.instance(FarmListActivity.this, HaveBreedActivity.class, bundle);
            }
        });

    }


    /**获取所有用户下面养殖的牲畜信息**/
    public void getBreeds(final List<String> alluserids){
        dialogUtils.showProgress(this,"正在加载数据");
        ThreadPoolManager.getInstance().execute(new Runnable() {
            @Override
            public void run() {
                for (int i = 0;i<alluserids.size();i++){
                   CheckBreeds(alluserids.get(i));
                }
                dialogUtils.dismissProgress();
            }
        });

    }

    /**
     * 检查牧户养殖的牲畜
     * @param userid 牧户id
     */
    private void CheckBreeds(final String userid) {
        RetrofitHelper.getApiService().getBreeds(userid).enqueue(new Callback<BreedsOfUser>() {
            @Override
            public void onResponse(Call<BreedsOfUser> call, Response<BreedsOfUser> response) {
                if (response.body().getCode() == ErroCode.SUCCESS){
                    BreedsOfUser breedsOfUser = response.body();
                    breedsOfUser.setUser_id(userid);
                    breedsOfUserDao.insertOrReplace(breedsOfUser);
                }
            }
            @Override
            public void onFailure(Call<BreedsOfUser> call, Throwable t) {

            }
        });
    }
}
