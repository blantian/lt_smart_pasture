package com.lantian.lib_docs.farmdoc.view.havefarm.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.lantian.lib_base.MyApp;
import com.lantian.lib_base.entity.module.response.farmer.farmdatas.FarmInCome;
import com.lantian.lib_base.entity.module.response.farmer.plan.countPlantsum;
import com.lantian.lib_commin_ui.base.BaseFragmen;
import com.lantian.lib_docs.R;
import com.lantian.lib_network.retrofit2.MyCallBack;
import com.lantian.lib_network.retrofit2.RetrofitHelper;

/**
 * Created by Sherlock·Holmes on 2020-03-30
 */
public class FDHomeItemFragment extends BaseFragmen {

    private static final String ARG_PARAM0 = "param0";
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String ARG_PARAM3 = "param3";

    private TextView mFarmDatas;
    private TextView fTitel;
    private TextView sTitel;
    private TextView fInfo;
    private TextView sInfo;
    private String startTime = "";
    private String endTime = "";

    private int layoutType = 0;
    private int type = 0;

    public static FDHomeItemFragment newInstance(int layoutType, int type, String param1, String param2) {
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        args.putInt(ARG_PARAM3, layoutType);
        args.putInt(ARG_PARAM0, type);
        FDHomeItemFragment fragment = new FDHomeItemFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null) {
            startTime = bundle.getString(ARG_PARAM1);
            endTime = bundle.getString(ARG_PARAM2);
            layoutType = bundle.getInt(ARG_PARAM3);
            type = bundle.getInt(ARG_PARAM0);
        }

    }


    public void sendData(String startTime,String endTime){
        this.startTime = startTime;
        this.endTime = endTime;
        initSelect();

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.item_farm_data, null);
        initView(view);
        initData();
        return view;
    }

    private void initData() {
        initSelect();
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

    private void initSelect() {
        switch (layoutType) {
            case 0:
                switch (type) {
                    case 0:
                        flatInOut("3");
                        break;
                    case 1:
                        flatInOut("5");
                        break;
                    case 2:
                        flatInOut("");
                        break;
                    default:
                }
                break;
            case 1:
                switch (type) {
                    case 0:
                        flatArea("3");
                        break;
                    case 1:
                        flatArea("5");
                        break;
                    case 2:
                        flatArea("");
                        break;
                    default:
                }
                break;
            case 2:
                switch (type) {
                    case 0:
                        flatCompensate("3");
                        break;
                    case 1:
                        flatCompensate("5");
                        break;
                    case 2:
                        flatCompensate("");
                        break;
                    default:
                }
                break;
            default:
        }
    }

    /**
     * 牧户补贴数据请求
     * @param s
     */
    private void flatCompensate(String s) {

    }

    /**
     * 经营草原面积数据请求
     * @param s
     */
    private void flatArea(String s) {
        RetrofitHelper.getApiService().getPlanSum(MyApp.Userid,s,startTime,endTime).enqueue(new MyCallBack<countPlantsum>() {
            @Override
            public void success(countPlantsum countPlantsum) {
                flatAreaView(countPlantsum);
            }

            @Override
            public void failure(String msg) {

            }
        });
    }

    /**
     * 收入支出数据请求
     * @param s
     */
    private void flatInOut(String s) {
        RetrofitHelper.getApiService().getFarmIncome(MyApp.Userid,s,startTime,endTime).enqueue(new MyCallBack<FarmInCome>() {
            @Override
            public void success(FarmInCome farmInCome) {
                setDataToTextView(farmInCome);
            }

            @Override
            public void failure(String msg) {
            }
        });
    }

    /**
     * 渲染总收入支出页面
     * @param farmInCome
     */
    private void setDataToTextView(FarmInCome farmInCome) {
            flatData("总收入",farmInCome.getIncome(),"总支出",farmInCome.getExpenditure());
    }

    private void flatAreaView(countPlantsum result) {
        flatData("总面积", result.getPlant(), "总种类", result.getPlantclass());
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

}
