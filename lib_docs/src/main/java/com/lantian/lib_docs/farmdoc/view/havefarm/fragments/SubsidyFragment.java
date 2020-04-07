package com.lantian.lib_docs.farmdoc.view.havefarm.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.lantian.lib_base.MyApp;
import com.lantian.lib_base.entity.module.response.breeds.CountCompensate;
import com.lantian.lib_commin_ui.base.BaseFragmen;
import com.lantian.lib_docs.R;
import com.lantian.lib_network.retrofit2.MyCallBack;
import com.lantian.lib_network.retrofit2.RetrofitHelper;

/**
 * Created by Sherlock·Holmes on 2020-04-02
 */
public class SubsidyFragment extends BaseFragmen {
    private TextView mFarmDatas;
    private TextView fTitel;
    private TextView sTitel;
    private TextView fInfo;
    private TextView sInfo;
    private String startTime = "";
    private String endTime = "";
    private int pageType;

    /**
     * 3 月
     * 5 年
     * 6(自定义)
     *
     * @param pageType
     * @return
     */
    public static SubsidyFragment newSubsidyInstance(int pageType, String startTime, String endTime) {
        Bundle args = new Bundle();
        args.putInt("pageType", pageType);
        args.putString("startTime", startTime);
        args.putString("endTime", endTime);
        SubsidyFragment fragment = new SubsidyFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.item_farm_data,null);
        initView(view);
        initSubsidyData();
        return view;

    }

    /**
     * 初始化view
     * @param view
     */
    private void initView(View view) {
        mFarmDatas = view.findViewById(R.id.farm_datas);
        fTitel = view.findViewById(R.id.titel_income);
        fInfo = view.findViewById(R.id.income_sum);
        sInfo = view.findViewById(R.id.out_sum);
        sTitel = view.findViewById(R.id.out_sum);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    public void initSubsidyData() {
        Bundle arguments = getArguments();
        if (arguments != null) {
            pageType = arguments.getInt("pageType");
            startTime = arguments.getString("startTime");
            endTime = arguments.getString("endTime");
            refreshViews();
            initCountsData();
        }
    }

    private void initCountsData() {
        RetrofitHelper.getApiService().getCompensate(MyApp.Userid,String.valueOf(pageType),startTime,endTime).enqueue(new MyCallBack<CountCompensate>() {
            @Override
            public void success(CountCompensate countCompensate) {
                flatsubsidyView(countCompensate);
            }

            @Override
            public void failure(String msg) {

            }
        });
    }
    /**
     * 渲染总收入支出页面
     * @param result
     */
    private void flatsubsidyView(CountCompensate result) {
        flatData("总补贴", result.getPrice(), null, null);
    }

    private void refreshViews() {
        //日期范围
        mFarmDatas.setText(String.format("%s ~ %s", startTime, endTime));
    }

    /**
     * 接收到数据后的数据填充
     */
    private void flatData(String t_1, String info_1, String t_2, String info_2) {
        mFarmDatas.setText(startTime + "~" + endTime);

        fTitel.setVisibility(View.INVISIBLE);
        fInfo.setVisibility(View.INVISIBLE);

        if (t_1 != null){
            fTitel.setVisibility(View.VISIBLE);
            fTitel.setText(t_1);
        }

        if (info_1 != null){
            fInfo.setVisibility(View.VISIBLE);
            fInfo.setText(info_1);
        }

        sTitel.setVisibility(View.INVISIBLE);
        sInfo.setVisibility(View.INVISIBLE);

        if (t_2 != null) {
            sTitel.setVisibility(View.VISIBLE);
            sTitel.setText(t_2);
        }
        if (sInfo != null) {
            sInfo.setVisibility(View.VISIBLE);
            sInfo.setText(info_2);
        }
    }

    /**
     * 外部选择传入日期
     * @param startTime
     * @param endTime
     */
    public void sendData(String startTime, String endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
        refreshViews();
    }
}
