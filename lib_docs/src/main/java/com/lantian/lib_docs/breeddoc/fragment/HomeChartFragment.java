package com.lantian.lib_docs.breeddoc.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.lantian.lib_base.MyApp;
import com.lantian.lib_base.database.greendao.BreedsDao;
import com.lantian.lib_base.database.greendao.CountBreedTureDao;
import com.lantian.lib_base.database.greendao.CountBreeddatasDao;
import com.lantian.lib_base.database.greendao.InOutDao;
import com.lantian.lib_base.file.sharedpreferences.SharedPreferencesUtils;
import com.lantian.lib_base.entity.module.response.breeds.Breeds;
import com.lantian.lib_base.entity.module.response.breeds.CountBreedTure;
import com.lantian.lib_base.entity.module.response.breeds.CountBreeddatas;
import com.lantian.lib_base.entity.module.response.breeds.InOut;
import com.lantian.lib_base.utils.BaseUtils;
import com.lantian.lib_commin_ui.base.BaseFragmen;
import com.lantian.lib_docs.R;
import com.lantian.lib_network.retrofit2.MyCallBack;
import com.lantian.lib_network.retrofit2.RetrofitHelper;
import com.lantian.lib_network.utils.NetworkUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sherlock·Holmes on 2020-04-01
 */
public class HomeChartFragment extends BaseFragmen {

    private TextView mDateRange;
    private TextView mIncomeText;
    private TextView mExpenditureText;
    private TextView mBreedText;
    private TextView mSlaughtText;
    private TextView mOutText;
    private TextView mInText;
    private PieChart mChart;
    private InOutDao inOutDao;
    private CountBreedTureDao countBreedTureDao;
    private CountBreeddatasDao countBreeddatasDao;
    private SharedPreferencesUtils sharedPreferencesUtils;

    private String startTime = "";
    private String endTime = "";
    private String userid ="";
    private int pageType;
    private PieDataSet dataSet;
    private BreedsDao breedsDao;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fr_breed_data_show, null);

        inOutDao = ((MyApp) BaseUtils.getContext()).getDaoSession().getInOutDao();
        countBreedTureDao = ((MyApp) BaseUtils.getContext()).getDaoSession().getCountBreedTureDao();
        countBreeddatasDao = ((MyApp) BaseUtils.getContext()).getDaoSession().getCountBreeddatasDao();
        breedsDao = ((MyApp)BaseUtils.getContext()).getDaoSession().getBreedsDao();

        sharedPreferencesUtils = new SharedPreferencesUtils(getContext());

        mDateRange = view.findViewById(R.id.dateRange);
        mIncomeText =view.findViewById(R.id.incomeText);
        mExpenditureText = view.findViewById(R.id.expenditureText);
        mBreedText = view.findViewById(R.id.breedText);
        mSlaughtText = view.findViewById(R.id.slaughtText);
        mOutText = view.findViewById(R.id.outText);
        mChart = view.findViewById(R.id.chart);
        mInText = view.findViewById(R.id.inText);
        mBreedText = view.findViewById(R.id.breedText);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initData();
    }

    /**
     * 3 月
     * 5 年
     * 6(自定义)
     *
     * @param pageType
     * @return
     */
    public static HomeChartFragment newInstance(int pageType, String startTime, String endTime,String userid) {
        Bundle args = new Bundle();
        args.putInt("pageType", pageType);
        args.putString("startTime", startTime);
        args.putString("endTime", endTime);
        args.putString("userid",userid);
        HomeChartFragment fragment = new HomeChartFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public void initData() {
        Bundle arguments = getArguments();
        if (arguments != null) {
            pageType = arguments.getInt("pageType");
            startTime = arguments.getString("startTime");
            endTime = arguments.getString("endTime");
            /**判断用户种类**/
            if (MyApp.isAdmin.equals("5")){
                userid = MyApp.DaBiaoUserid;
            }else {
                userid = arguments.getString("userid");
            }


            /**网络状态**/
            if (NetworkUtils.isConnected()){

                refreshViews();
                initChart();
                initCountsData();
                /**断网状态**/
            }else {
                setDataBaseData();
                initChart();
                refreshViews();
            }
        }
    }

    /**
     *  数据库数据
     */
    private void setDataBaseData() {
        String db_userid = (String) sharedPreferencesUtils.getParam("user_id","");
        String db_isAdmin = (String) sharedPreferencesUtils.getParam("isAdmin","");
        String db_dabiaouserid = (String) sharedPreferencesUtils.getParam("db_userid","");
        if (db_isAdmin.equals("5")){
            userid =db_dabiaouserid;
        }else {
            userid = db_userid;
        }
        /**输入支出**/
        InOut inOut = inOutDao.queryBuilder().where(InOutDao.Properties.Userid.eq(userid)).unique();
        if (inOut !=null){
            mIncomeText.setText(inOut.getIncome() + "元");
            mExpenditureText.setText(inOut.getExpenditure() + "元");
        }
        /**养殖数量**/
        CountBreeddatas countBreeddatas = countBreeddatasDao.queryBuilder().where(CountBreeddatasDao.Properties.Userid.eq(userid)).unique();
        if (countBreeddatas !=null){
            mInText.setText(countBreeddatas.getBreeddata2());
            mOutText.setText(countBreeddatas.getBreeddata1());
            mSlaughtText.setText(countBreeddatas.getBreeddata3());
        }
        /**交易数量**/
        CountBreedTure countBreedTure = countBreedTureDao.queryBuilder().where(CountBreedTureDao.Properties.Userid.eq(userid)).unique();
        if (countBreedTure !=null){
            mBreedText.setText(countBreedTure.getBreedcont());
        }

    }


    private void refreshViews() {
        //日期范围
        mDateRange.setText(String.format("%s ~ %s", startTime, endTime));
        fetchChartData();
    }

    /**
     * 初始化图表
     */
    private void fetchChartData() {
            if (NetworkUtils.isConnected()){
                if (userid != null){
                RetrofitHelper.getApiService().getBreedStatistics(userid,
                        pageType + "",
                        startTime,
                        endTime).enqueue(new MyCallBack<ArrayList<Breeds>>() {
                    @Override
                    public void success(ArrayList<Breeds> breeds) {
                        List<Breeds> breedsList = new ArrayList<>();
                        for (int i = 0;i<breeds.size();i++){
                                breeds.get(i).setUserid(MyApp.Userid);
                        }
                        breedsDao.insertOrReplaceInTx(breeds);
                        setDataInChart(breeds);
                    }
                    @Override
                    public void failure(String msg) {

                    }
                });
            }
                /**无网络模式**/
        }else {
                String db_userid = (String) sharedPreferencesUtils.getParam("user_id","");
                Log.e("starttaime",startTime);
                Log.e("endtime",endTime);
               List<Breeds> breeds = breedsDao.queryBuilder().where(breedsDao.queryBuilder()
                       .and(BreedsDao.Properties.Userid.eq(db_userid),BreedsDao.Properties.Addtime.ge(startTime),BreedsDao.Properties.Addtime.le(endTime))).list();
               setDataInChart(breeds);
            }
    }

    /**
     * 给 Chart组件数据
     * **/
    private void setDataInChart(List<Breeds> breeds) {
        /**判断组件睡是否有数据，有则晴空数据，并添加**/
        if (mChart != null){
            int entryCount = dataSet.getEntryCount();
            for (int i = 0;i<entryCount;i++){
                dataSet.removeFirst();
                dataSet.notifyDataSetChanged();
                mChart.notifyDataSetChanged();
            }
            for (Breeds br : breeds){
                int value = Integer.parseInt(br.getCount() == null ? "0" : br.getCount());
                if (value == 0){
                    continue;
                }
                dataSet.addEntry(new PieEntry(value, br.getName()));
            }
            if (dataSet.getEntryCount() == 0) {
                //showToast("数据为空");
                dataSet.addEntry(new PieEntry(0, "没有数据"));
            }
            dataSet.notifyDataSetChanged();

            mChart.notifyDataSetChanged();
            mChart.invalidate();
        }
    }

    private void initChart() {
        List<PieEntry> entries = new ArrayList<>();
        dataSet = new PieDataSet(entries, "");

        ArrayList<Integer> colors = new ArrayList<>();

        int defaultColor = BaseUtils.getContext()
                .getResources()
                .getColor(com.lantian.lib_commin_ui.R.color.afterSelector);

        // 饼图色
        colors.add(Color.rgb(114, 188, 223));
        colors.add(Color.rgb(255, 123, 124));
        colors.add(Color.rgb(57, 135, 200));
        colors.add(Color.rgb(59, 125, 255));
        colors.add(Color.rgb(194, 125, 145));
        colors.add(Color.WHITE);
        colors.add(Color.RED);
        colors.add(Color.YELLOW);
        colors.add(Color.rgb(255, 125, 225));
        colors.add(Color.rgb(134, 125, 135));


        dataSet.setColors(colors);
        dataSet.setSelectionShift(14f);
        //设置间距
        dataSet.setSliceSpace(0);
        //设置显示数据的大小
        dataSet.setValueTextSize(18);
        dataSet.setValueLineVariableLength(true);
        dataSet.setValueTextColors(colors);
        //线的颜色
        dataSet.setValueLineColor(Color.rgb(205, 205, 205));
        //内圈线
        dataSet.setValueLinePart1Length(0.5f);
        //外圈线
        dataSet.setValueLinePart2Length(0.3f);

        dataSet.setYValuePosition(PieDataSet.ValuePosition.OUTSIDE_SLICE);
        dataSet.setXValuePosition(PieDataSet.ValuePosition.OUTSIDE_SLICE);

        Legend legend = mChart.getLegend();
        //设置比例图样式 圆
        legend.setForm(Legend.LegendForm.LINE);
        legend.setTextColor(defaultColor);
        //设置比例图位置
        legend.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);
        legend.setTextSize(16f);

        //图中标签
        mChart.setEntryLabelColor(defaultColor);
        mChart.setEntryLabelTextSize(14f);
        //关闭描述
        mChart.getDescription().setEnabled(false);
        //关闭中空洞
        mChart.setDrawHoleEnabled(false);
        //设置动画
        mChart.animateXY(500, 500);
        mChart.setData(new PieData(dataSet));
        mChart.invalidate();
    }

    /**
     * 收入、支出
     */
    private void initCountsData() {
        if (userid !=null){
            RetrofitHelper.getApiService().countInOut(userid, null,
                    null, null, null).enqueue(new MyCallBack<InOut>() {
                @Override
                public void success(InOut inOut) {
                    if (inOut !=null){
                        if (mIncomeText != null && mExpenditureText != null){
                            mIncomeText.setText(inOut.getIncome() + "元");
                            mExpenditureText.setText(inOut.getExpenditure() + "元");
                            /**插入数据库**/
                            if (userid!=null){
                                inOut.setUserid(userid);
                                inOutDao.insertOrReplace(inOut);
                            }
                        }
                    }
                }

                @Override
                public void failure(String msg) {
                    showToast("获取收入支出数据失败！");
                }
            });

            /**
             * 屠宰数量、出栏数量、未出栏数量
             *
             */
            RetrofitHelper.getApiService().getBreedDatas(userid)
                    .enqueue(new MyCallBack<CountBreeddatas>() {
                        @Override
                        public void success(CountBreeddatas countBreeddatas) {
                            if (countBreeddatas != null){
                                if (mInText != null && mOutText !=null && mSlaughtText != null){
                                    mInText.setText(countBreeddatas.getBreeddata2());
                                    mOutText.setText(countBreeddatas.getBreeddata1());
                                    mSlaughtText.setText(countBreeddatas.getBreeddata3());
                                    if (userid !=null){
                                        countBreeddatas.setUserid(userid);
                                        /**插入数据库**/
                                        countBreeddatasDao.insertOrReplace(countBreeddatas);
                                    }
                                }
                            }
                        }

                        @Override
                        public void failure(String msg) {
                            showToast("获取交易数量失败！");
                        }
                    });

            RetrofitHelper.getApiService().getCountBreedture(userid).enqueue(new MyCallBack<CountBreedTure>() {
                @Override
                public void success(CountBreedTure countBreedTure) {
                    if (countBreedTure != null){
                        if (userid != null){
                            countBreedTure.setUserid(userid);
                            countBreedTureDao.insertOrReplace(countBreedTure);
                        }
                        mBreedText.setText(countBreedTure.getBreedcont());
                    }

                }

                @Override
                public void failure(String msg) {

                }
            });
        }
    }

    /**
     * 外部选择传入日期
     * @param startTime
     * @param endTime
     */
    public void sendDate(String startTime, String endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
        refreshViews();
    }

}
