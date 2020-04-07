package com.lantian.lib_docs.breeddoc.dabiaoyonghu;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lantian.lib_base.MyApp;
import com.lantian.lib_base.entity.module.response.farmer.farmlist.FarmListResponse;
import com.lantian.lib_commin_ui.base.BaseActivity;
import com.lantian.lib_docs.R;
import com.lantian.lib_docs.breeddoc.HaveBreedActivity;
import com.lantian.lib_docs.breeddoc.dabiaoyonghu.adapter.RFarmListAdapter;
import com.lantian.lib_network.retrofit2.MyCallBack;
import com.lantian.lib_network.retrofit2.RetrofitHelper;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sherlock·Holmes on 2020-03-18
 */

public class FarmListActivity extends BaseActivity{

    private RecyclerView farmList;

    private boolean isErr = false;
    private int currentPage = 0;
    private int PAGE_COUNT = 10;
    private RFarmListAdapter rfarmListAdapter;
    private GridLayoutManager mLinearLayoutManager;
    private SmartRefreshLayout refreshView;
    private List<FarmListResponse> list;
    private Bundle bundle;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_farm_list;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        initView();
        initData();
    }


    private void initView() {
        farmList = findViewById(R.id.farm_list);
        refreshView = findViewById(R.id.refreshView);
    }

    /**
     * 请求接口数据
     */

    private void initData() {
        RetrofitHelper.getApiService().getFarmList(MyApp.Userid)
                .enqueue(new MyCallBack<ArrayList<FarmListResponse>>() {
                    @Override
                    public void success(ArrayList<FarmListResponse> farmListResponses) {
                        if (farmListResponses.size() != 0) {
                            initadapter(farmListResponses);
                        }
                    }

                    @Override
                    public void failure(String msg) {
                        showToast(msg);
                    }
                });
    }

    private void initadapter(final ArrayList<FarmListResponse> data) {
        mLinearLayoutManager = new GridLayoutManager(this, 1);
        farmList.setLayoutManager(mLinearLayoutManager);

        rfarmListAdapter = new RFarmListAdapter(R.layout.farmlist_item, data);
        farmList.setAdapter(rfarmListAdapter);
        rfarmListAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Log.e("点击位置", position + "");
                bundle = new Bundle();
                bundle.putSerializable("data", data.get(position));
                HaveBreedActivity.instance(FarmListActivity.this, HaveBreedActivity.class, bundle);
            }
        });

    }

}
