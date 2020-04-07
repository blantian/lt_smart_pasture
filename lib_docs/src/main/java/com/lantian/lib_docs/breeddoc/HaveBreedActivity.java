package com.lantian.lib_docs.breeddoc;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.lantian.lib_base.MyApp;
import com.lantian.lib_base.entity.module.response.breeds.Breeds;
import com.lantian.lib_base.entity.module.response.farmer.farmlist.FarmListResponse;
import com.lantian.lib_commin_ui.base.BaseActivity;
import com.lantian.lib_commin_ui.indicator.CHANNEL;
import com.lantian.lib_commin_ui.pager_indictor.ScaleTransitionPagerTitleView;
import com.lantian.lib_commin_ui.viewpager.MyViewPager;
import com.lantian.lib_docs.R;
import com.lantian.lib_docs.breeddoc.adapter.HaveBreedAdapter;
import com.lantian.lib_docs.breeddoc.fragment.HomeChartFragment;
import com.lantian.lib_docs.farmdoc.adapter.HPagerAdapter;
import com.lantian.lib_network.networkstatus.NetWorkStatus;
import com.lantian.lib_network.retrofit2.MyCallBack;
import com.lantian.lib_network.retrofit2.RetrofitHelper;

import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.ViewPagerHelper;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.SimplePagerTitleView;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Sherlock·Holmes on 2020-03-19
 */
public class HaveBreedActivity extends BaseActivity implements View.OnClickListener {

    @SuppressLint({"SimpleDateFormat"})
    private final SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
    @SuppressLint({"SimpleDateFormat"})
    private final SimpleDateFormat dateFormonth = new SimpleDateFormat("yyyy-MM-31");
    @SuppressLint({"SimpleDateFormat"})
    private final SimpleDateFormat firstDayFormatter = new SimpleDateFormat("yyyy-MM-01");
    @SuppressLint({"SimpleDateFormat"})
    private final SimpleDateFormat dayFormatter = new SimpleDateFormat("yyyy-01-01");
    @SuppressLint({"SimpleDateFormat"})
    private final SimpleDateFormat dayForyers = new SimpleDateFormat("yyyy-12-31");
    private final Date date = new Date();

    //指定首页标题
    private static final CHANNEL[] BREED_CHANNELS =
            new CHANNEL[]{
                    CHANNEL.MONTH,
                    CHANNEL.YERS,
                    CHANNEL.CUSTOM
            };


    private static final String DAO_BIAO_USER = "5";
    private TextView userName;
    private ImageView head;
    private RecyclerView breedsTable;
    private Context context;
    private Bundle bundle = new Bundle();
    private String userid;
    private MagicIndicator mMagicIndicator;
    private MyViewPager mMyviewPager;
    private HPagerAdapter hPagerAdapter;
    private ArrayList<Fragment> fragments;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_have_breed;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        initView();
    }

    private void initView() {
        userName = findViewById(R.id.buser_name);
        breedsTable = findViewById(R.id.breeds_table);
        head = findViewById(R.id.cio_head);
        mMyviewPager = (MyViewPager) findViewById(R.id.myviewPager);
        mMagicIndicator = findViewById(R.id.my_magic_indicator);
        mMyviewPager.setOnClickListener(this);
        breedsTable.setLayoutManager(new GridLayoutManager(this, 3));
        initData();
        initFragments();

    }

    private void initData() {
        if (NetWorkStatus.isNetworkAvailable(this)) {
            /**打标用户**/
            if (MyApp.isAdmin.equals(DAO_BIAO_USER)) {
                bundle = getIntent().getExtras();
                Serializable serializable = bundle.getSerializable("data");
                if (serializable != null) {
                    FarmListResponse farmListResponse = (FarmListResponse) serializable;
                    userName.setText(farmListResponse.getUsername());
                    Glide.with(this).load(farmListResponse.getAvatar()).into(head);
                    userid = farmListResponse.getUser_id();
                    CheckBreeds(userid);
                }
                /**非打标用户**/
            } else {
                userid = MyApp.Userid;
                CheckBreeds(userid);
            }
        } else {
            /**数据库查找信息**/

        }

    }


    /**
     * 初始化fragments
     */
    private void initFragments() {
        fragments = new ArrayList<>();
        fragments.add(HomeChartFragment.newInstance(3,
                dayFormatter.format(date),
                dayForyers.format(date),userid)
        );
        fragments.add(HomeChartFragment.newInstance(5,
                firstDayFormatter.format(date),
                dateFormonth.format(date),userid)
        );

        fragments.add(HomeChartFragment.newInstance(6,
                firstDayFormatter.format(date),
                dateFormatter.format(date),userid)
        );
        hPagerAdapter = new HPagerAdapter(getSupportFragmentManager(),BREED_CHANNELS,fragments);
        mMyviewPager.setAdapter(hPagerAdapter);
        mMyviewPager.setOffscreenPageLimit(fragments.size());
        initMagicIndicator();
    }

    /**
     * 初始化MagicIndicator
     */
    private void initMagicIndicator() {
        CommonNavigator commonNavigator = new CommonNavigator(HaveBreedActivity.this);
        //居中titel
        commonNavigator.setAdjustMode(true);
        commonNavigator.setAdapter(new CommonNavigatorAdapter() {
            @Override
            public int getCount() {
                return BREED_CHANNELS == null ? 0 : BREED_CHANNELS.length;
            }

            @Override
            public IPagerTitleView getTitleView(Context context, final int index) {
                final SimplePagerTitleView simplePagerTitleView = new ScaleTransitionPagerTitleView(context);
                simplePagerTitleView.setText(BREED_CHANNELS[index].getKey());
                //标题字体大小
                simplePagerTitleView.setTextSize(16);
                //标题加粗
                simplePagerTitleView.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
                simplePagerTitleView.setNormalColor(Color.parseColor("#FFFFFF"));
                simplePagerTitleView.setSelectedColor(Color.parseColor("#2BF3DF"));
                simplePagerTitleView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mMyviewPager.setCurrentItem(index);
                        if (simplePagerTitleView.getText().equals("自定义")){
                            timePicker((HomeChartFragment) fragments.get(2));
                        }
                    }
                });
                return simplePagerTitleView;
            }

            @Override
            public IPagerIndicator getIndicator(Context context) {
                return null;
            }

            @Override
            public float getTitleWeight(Context context, int index) {
                return 1.0f;
            }
        });
        mMagicIndicator.setNavigator(commonNavigator);
        ViewPagerHelper.bind(mMagicIndicator, mMyviewPager);

    }


    /**
     * 检查牧户养殖的牲畜
     *
     * @param userid
     */
    private void CheckBreeds(final String userid) {
        RetrofitHelper.getApiService().getBreeds(userid).enqueue(new MyCallBack<ArrayList<Breeds>>() {
            @Override
            public void success(ArrayList<Breeds> breeds) {
                ArrayList<Breeds> br = new ArrayList<>();
                for (int i = 0; i < breeds.size(); i++) {
                    if (breeds.get(i).getCount() != null) {
                        br.add(breeds.get(i));
                    }
                }
                breedsTable.setAdapter(new HaveBreedAdapter(br, context, userid));
            }

            @Override
            public void failure(String msg) {
                showToast("出错");
            }

        });
    }

    @Override
    public void onClick(View v) {

    }

    /**
     * 日期选择器
     *
     * @param fragment
     */
    private void timePicker(final HomeChartFragment fragment) {
        final Calendar startTime = Calendar.getInstance();
        final Calendar endTime = Calendar.getInstance();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            final DatePickerDialog dialogStart = new DatePickerDialog(this);
            final DatePickerDialog dialogEnd = new DatePickerDialog(this);
            dialogStart.setOnDateSetListener(new DatePickerDialog.OnDateSetListener() {
                @Override
                public final void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
                    startTime.set(1, year);
                    startTime.set(2, month);
                    startTime.set(5, dayOfMonth);

                    dialogStart.dismiss();
                    dialogEnd.show();
                }
            });
            dialogEnd.setOnDateSetListener(new DatePickerDialog.OnDateSetListener() {
                @Override
                public final void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    dialogEnd.dismiss();
                    endTime.set(1, year);
                    endTime.set(2, month);
                    endTime.set(5, dayOfMonth);

                    showToast(
                            "已选择"
                                    + dateFormatter.format(startTime.getTime())
                                    + ","
                                    + dateFormatter.format(endTime.getTime()));

                    fragment.sendDate(
                            dateFormatter.format(startTime.getTime()),
                            dateFormatter.format(endTime.getTime())
                    );
                }
            });
            dialogStart.show();
        } else {
            showToast("系统不支持");
        }
    }

}
