package com.lantian.lib_docs.farmdoc.view.havefarm;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.lantian.lib_base.MyApp;
import com.lantian.lib_base.database.greendao.FarmMsgResponseDao;
import com.lantian.lib_base.database.greendao.FarmSumDataDao;
import com.lantian.lib_base.database.greendao.PersonListDao;
import com.lantian.lib_base.file.sharedpreferences.SharedPreferencesUtils;
import com.lantian.lib_base.database.syncdata.SyncDataStatus;
import com.lantian.lib_base.entity.module.response.farmer.farmdatas.FarmSumData;
import com.lantian.lib_base.entity.module.response.farmer.farmlist.PersonList;
import com.lantian.lib_base.entity.module.response.farmer.farmmsg.FarmMsgResponse;
import com.lantian.lib_base.utils.BaseUtils;
import com.lantian.lib_base.utils.EventMessage;
import com.lantian.lib_commin_ui.base.BaseActivity;
import com.lantian.lib_commin_ui.dialog.AlertDialogUtil;
import com.lantian.lib_commin_ui.indicator.CHANNEL;
import com.lantian.lib_commin_ui.indicator.MyMagicIndicator;
import com.lantian.lib_commin_ui.viewpager.MyViewPager;
import com.lantian.lib_docs.R;
import com.lantian.lib_docs.farmdoc.adapter.HPagerAdapter;
import com.lantian.lib_docs.farmdoc.view.farmerdata.FarmerMsgActivity;
import com.lantian.lib_docs.farmdoc.view.farmerdata.chengyuan.FarmFDHomeActivity;
import com.lantian.lib_docs.farmdoc.view.havefarm.fragments.AreaFragment;
import com.lantian.lib_docs.farmdoc.view.havefarm.fragments.InOutFragment;
import com.lantian.lib_docs.farmdoc.view.havefarm.fragments.SubsidyFragment;
import com.lantian.lib_docs.service.SynDataService;
import com.lantian.lib_network.networkstatus.NetWorkStatus;
import com.lantian.lib_network.retrofit2.MyCallBack;
import com.lantian.lib_network.retrofit2.RetrofitHelper;
import com.lantian.lib_network.utils.NetworkUtils;

import net.lucode.hackware.magicindicator.MagicIndicator;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by Sherlock·Holmes on 2020-03-27
 */
public class HaveFarmActivity extends BaseActivity implements View.OnClickListener {


    @SuppressLint({"SimpleDateFormat"})
    private final SimpleDateFormat dateForNow = new SimpleDateFormat("yyyy-MM-dd");
    @SuppressLint({"SimpleDateFormat"})
    private final SimpleDateFormat lastDayForMonth = new SimpleDateFormat("yyyy-MM-31");
    @SuppressLint({"SimpleDateFormat"})
    private final SimpleDateFormat firstDayForMonth = new SimpleDateFormat("yyyy-MM-01");
    @SuppressLint({"SimpleDateFormat"})
    private final SimpleDateFormat firstDayForYear = new SimpleDateFormat("yyyy-01-01");
    @SuppressLint({"SimpleDateFormat"})
    private final SimpleDateFormat lastDayForYears = new SimpleDateFormat("yyyy-12-31");

    private final Date date = new Date();

    //指定首页标题
    private static final CHANNEL[] FARM_CHANNELS =
            new CHANNEL[]{
                    CHANNEL.MONTH,
                    CHANNEL.YERS,
                    CHANNEL.CUSTOM
            };

    private ImageView back;
    private TextView syndata;
    private ImageView mHeaderIcon;
    private TextView mUserName;
    private TextView mMPopulation;
    private LinearLayout mBtnMember;
    private TextView mInOutState;
    private LinearLayout mBtnTotal;
    private TextView mGreenArea;
    private LinearLayout mBtnArea;
    private TextView mMSubsidy;
    private LinearLayout mBtnCompensate;
    private FarmMsgResponseDao farmMsgResponseDao;
    private FarmSumDataDao farmSumDataDao;
    private LinearLayout mFarmerMsg;
    private ArrayList<Fragment> Inoutfragments;
    private ArrayList<Fragment> AreaFragments;
    private ArrayList<Fragment> SubsidyFragments;
    private MagicIndicator mInOutMagicIndicator,mAreaMagicIndicator,mSubsidyMagicIndicator;
    private MyViewPager mInOutViewPager,mAreaViewPager,mSubsidyViewPager;
    private HPagerAdapter hPagerAdapter;
    private SharedPreferencesUtils sharedPreferencesUtils;
    private PersonListDao personListDao;
    private List<PersonList> personLists;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_have_farm;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        initView();
        NetWorkStatus();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_compensate) {
            jumpTo(3);
        } else if (v.getId() == R.id.btn_area) {
            jumpTo(2);
        } else if (v.getId() == R.id.btn_total) {
            jumpTo(1);
        } else if (v.getId() == R.id.btn_member) {
            jumpTo(0);
        } else if (v.getId() == R.id.btn_save) {

        }else if (v.getId() == R.id.farmer_msg){
            FarmerMsgActivity.instance(HaveFarmActivity.this,FarmerMsgActivity.class,null);
        }else if (v.getId() == R.id.have_farm_bttn){
            HaveFarmActivity.this.finish();
        }else if (v.getId() ==R.id.synfarmdata){
            uploud();
        }
    }

    public void uploud(){
        String[] names = new String[]{"更新数据","取消"};
        AlertDialogUtil.TwoChoiceDialog(HaveFarmActivity.this, "友情提示", "离线有数据更新：" + personLists.size() + "条数据", names, new AlertDialogUtil.TwoChoiceHandle() {
            @Override
            public void onPositiveButtonHandle() {
                if (NetworkUtils.isConnected()){
                    if (personLists.size() != 0){
                        Intent intent = new Intent(HaveFarmActivity.this, SynDataService.class);
                        intent.setAction(SynDataService.SYNFARMDATA);
                        intent.putExtra("userid",MyApp.Userid);
                        startService(intent);
                    }else {
                        AlertDialogUtil.warningDialog(HaveFarmActivity.this,"提示","您暂时没有更新的数据哦！");
                    }
                }else {
                    AlertDialogUtil.warningDialog(HaveFarmActivity.this,"提示","您需要连接网络才能上传数据哦！");
                }

            }
            @Override
            public void onNegativeButtonHandle() {

            }
        });
    }

    /**
     * 判断网络
     */
    private void NetWorkStatus() {
        if (NetWorkStatus.isNetworkAvailable(this)) {
            RetrofitHelper.getApiService().getFarmSum(MyApp.Userid).enqueue(new MyCallBack<FarmSumData>() {
                @Override
                public void success(FarmSumData farmSumData) {
                    farmSumData.setUser_id(MyApp.Userid);
                    farmSumDataDao.insertOrReplace(farmSumData);
                    initData(farmSumData);
                }

                @Override
                public void failure(String msg) {

                }
            });
            /**无网络**/
        } else {
          initDataBase();
        }

    }

    private void initDataBase() {
        String userid = (String) sharedPreferencesUtils.getParam("user_id","");
        FarmSumData farmSumData =farmSumDataDao.queryBuilder().where(FarmSumDataDao.Properties.User_id.eq(userid)).unique();
        if (farmSumData !=null){
            initData(farmSumData);
        }else {
            AlertDialogUtil.warningDialog(this,"温馨提示！","请先加载数据哦！");
        }

    }

    /**给tab数据**/
    private void initData(FarmSumData farmSumData) {
        mMPopulation.setText(String.valueOf(farmSumData.getRenkou()));
        mInOutState.setText(farmSumData.getZonghui());
        if (farmSumData.getMianji() == null) {
            mGreenArea.setText("");
        } else {
            mGreenArea.setText(farmSumData.getMianji());
        }
        mMSubsidy.setText(farmSumData.getButie());
    }

    private void initView() {
        EventBus.getDefault().register(this);
        sharedPreferencesUtils = new SharedPreferencesUtils(HaveFarmActivity.this);
        farmMsgResponseDao = ((MyApp) getApplication()).getDaoSession().getFarmMsgResponseDao();
        farmSumDataDao = ((MyApp) getApplication()).getDaoSession().getFarmSumDataDao();
        back = findViewById(R.id.have_farm_bttn);
        syndata = findViewById(R.id.synfarmdata);
        back.setOnClickListener(this);
        mHeaderIcon = (ImageView) findViewById(R.id.header_icon);
        mHeaderIcon.setOnClickListener(this);
        mUserName = (TextView) findViewById(R.id.user_name);
        mUserName.setOnClickListener(this);
        mMPopulation = (TextView) findViewById(R.id.m_population);
        mMPopulation.setOnClickListener(this);
        mBtnMember = (LinearLayout) findViewById(R.id.btn_member);
        mBtnMember.setOnClickListener(this);
        mInOutState = (TextView) findViewById(R.id.in_out_state);
        mInOutState.setOnClickListener(this);
        mBtnTotal = (LinearLayout) findViewById(R.id.btn_total);
        mBtnTotal.setOnClickListener(this);
        mGreenArea = (TextView) findViewById(R.id.green_area);
        mGreenArea.setOnClickListener(this);
        mBtnArea = (LinearLayout) findViewById(R.id.btn_area);
        mBtnArea.setOnClickListener(this);
        mMSubsidy = (TextView) findViewById(R.id.m_subsidy);
        mMSubsidy.setOnClickListener(this);
        mBtnCompensate = (LinearLayout) findViewById(R.id.btn_compensate);
        mFarmerMsg = findViewById(R.id.farmer_msg);

        mInOutViewPager = findViewById(R.id.vp_in_out);
        mAreaViewPager = findViewById(R.id.vp_area);
        mSubsidyViewPager = findViewById(R.id.vp_subsidy);

        mSubsidyMagicIndicator = findViewById(R.id.magic_indicator3);
        mAreaMagicIndicator = findViewById(R.id.magic_indicator2);
        mInOutMagicIndicator = findViewById(R.id.magic_indicator_farm);

        mBtnMember.setOnClickListener(this);
        mBtnArea.setOnClickListener(this);
        mBtnCompensate.setOnClickListener(this);
        mBtnTotal.setOnClickListener(this);
        mBtnCompensate.setOnClickListener(this);
        mFarmerMsg.setOnClickListener(this);
        syndata.setOnClickListener(this);
        initInOutFragments();
        initAreaFragments();
        initSubsidyFragment();

        if(checkUploud()){
            syndata.setBackground(getResources().getDrawable(R.drawable.ic_action_syndata));
        }else {
            syndata.setBackground(getResources().getDrawable(R.drawable.ic_action_resyndata));
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    /**回调**/
    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true)
    public void referUi(EventMessage eventMessage){
        if (eventMessage.getMsg() ==1 && eventMessage.getMessage().equals("edit")){
            showToast("修改成功！");
        }else if (eventMessage.getMsg()==1&&eventMessage.getMessage().equals("add")){
            List<FarmMsgResponse> farmMsgResponses =farmMsgResponseDao.queryBuilder().where(FarmMsgResponseDao.Properties.Status.eq(SyncDataStatus.ADD.ordinal())).list();
            mMPopulation.setText(farmMsgResponses.size());
        }
    }

    /**
     * 检查更新数据
     */
    private boolean checkUploud() {
        personListDao = ((MyApp) BaseUtils.getContext()).getDaoSession().getPersonListDao();
         personLists = personListDao.queryBuilder().where(personListDao.queryBuilder().and(PersonListDao.Properties.User_id.eq(MyApp.Userid)
                 ,PersonListDao.Properties.Status.eq(SyncDataStatus.ADD.ordinal()))).list();
        if (personLists.size()!= 0) {
            return true;
        }else {
            return false;
        }
    }

    /**
     * 补贴
     */
    private void initSubsidyFragment() {
        SubsidyFragments = new ArrayList<>();
        SubsidyFragments.add(SubsidyFragment.newSubsidyInstance(3,
                firstDayForMonth.format(date),
                lastDayForMonth.format(date))
        );
        SubsidyFragments.add(SubsidyFragment.newSubsidyInstance(5,
                firstDayForYear.format(date),
                lastDayForYears.format(date))
        );

        SubsidyFragments.add(SubsidyFragment.newSubsidyInstance(6,
                firstDayForMonth.format(date),
                dateForNow.format(date))
        );
        hPagerAdapter = new HPagerAdapter(getSupportFragmentManager(),FARM_CHANNELS, SubsidyFragments);
        mSubsidyViewPager.setAdapter(hPagerAdapter);
        mSubsidyViewPager.setOffscreenPageLimit(Inoutfragments.size());
        mSubsidyViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position == SubsidyFragments.size()-1){
                    SubsidytimePicker(SubsidyFragments.get(2));
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        MyMagicIndicator.initSimpleMagicIndicator(this,FARM_CHANNELS, mSubsidyViewPager, mSubsidyMagicIndicator,20);

    }


    /**
     * 初始化fragments
     */
    private void initInOutFragments() {
        Inoutfragments = new ArrayList<>();
        Inoutfragments.add(InOutFragment.newInstance(3,
                firstDayForMonth.format(date),
                lastDayForMonth.format(date))
        );
        Inoutfragments.add(InOutFragment.newInstance(5,
                firstDayForYear.format(date),
                lastDayForYears.format(date))
        );

        Inoutfragments.add(InOutFragment.newInstance(6,
                firstDayForMonth.format(date),
                dateForNow.format(date))
        );
        hPagerAdapter = new HPagerAdapter(getSupportFragmentManager(),FARM_CHANNELS, Inoutfragments);
        mInOutViewPager.setAdapter(hPagerAdapter);
        mInOutViewPager.setOffscreenPageLimit(Inoutfragments.size());
        mInOutViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position == Inoutfragments.size()-1){
                    InOuttimePicker(Inoutfragments.get(2));
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        MyMagicIndicator.initSimpleMagicIndicator(this,FARM_CHANNELS, mInOutViewPager, mInOutMagicIndicator,20);

    }
    /**
     * 初始化fragments
     */
    private void initAreaFragments() {
        AreaFragments = new ArrayList<>();
        AreaFragments.add(AreaFragment.newAreaInstance(3,
                firstDayForMonth.format(date),
                lastDayForMonth.format(date))
        );
        AreaFragments.add(AreaFragment.newAreaInstance(5,
                firstDayForYear.format(date),
                lastDayForYears.format(date))

        );

        AreaFragments.add(AreaFragment.newAreaInstance(6,
                dateForNow.format(date),
                dateForNow.format(date))
        );
        hPagerAdapter = new HPagerAdapter(getSupportFragmentManager(),FARM_CHANNELS, AreaFragments);
        mAreaViewPager.setAdapter(hPagerAdapter);
        mAreaViewPager.setOffscreenPageLimit(AreaFragments.size());
        mAreaViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position == AreaFragments.size()-1){
                    AreatimePicker(AreaFragments.get(2));
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        MyMagicIndicator.initSimpleMagicIndicator(this,FARM_CHANNELS, mAreaViewPager, mAreaMagicIndicator,20);

    }



    private void jumpTo(int layoutType) {
        startActivity(new Intent(this,FarmFDHomeActivity.class).putExtra("selected",layoutType));
    }

    /**
     * AreaFragment日期选择器
     *
     * @param fragment
     */
    private void AreatimePicker(final Fragment fragment) {

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
                                    + dateForNow.format(startTime.getTime())
                                    + ","
                                    + dateForNow.format(endTime.getTime()));

                    ((AreaFragment)fragment).sendData(
                            dateForNow.format(startTime.getTime()),
                            dateForNow.format(endTime.getTime())
                    );
                }
            });
            dialogStart.show();
        } else {
           showToast( "系统不支持");
        }
    }
    /**
     * InOutFragment日期选择器
     *
     * @param fragment
     */
    private void InOuttimePicker(final Fragment fragment) {
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
                                    + dateForNow.format(startTime.getTime())
                                    + ","
                                    + dateForNow.format(endTime.getTime()));

                    ((InOutFragment)fragment).sendDate(
                            dateForNow.format(startTime.getTime()),
                            dateForNow.format(endTime.getTime())
                    );
                }
            });
            dialogStart.show();
        } else {
            showToast( "系统不支持");
        }
    }

    /**
     * SubsidytimePicker日期选择器
     *
     * @param fragment
     */
    private void SubsidytimePicker(final Fragment fragment) {
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
                                    + dateForNow.format(startTime.getTime())
                                    + ","
                                    + dateForNow.format(endTime.getTime()));

                    ((SubsidyFragment)fragment).sendData(
                            dateForNow.format(startTime.getTime()),
                            dateForNow.format(endTime.getTime())
                    );
                }
            });
            dialogStart.show();
        } else {
            showToast( "系统不支持");
        }
    }
}
