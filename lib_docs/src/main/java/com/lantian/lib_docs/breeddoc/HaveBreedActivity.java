package com.lantian.lib_docs.breeddoc;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.lantian.lib_base.MyApp;
import com.lantian.lib_base.database.greendao.BreedIndexDao;
import com.lantian.lib_base.database.greendao.BreedsListDao;
import com.lantian.lib_base.database.greendao.BreedsOfUserDao;
import com.lantian.lib_base.database.greendao.EarTagDao;
import com.lantian.lib_base.database.greendao.editEarTagDao;
import com.lantian.lib_base.file.sharedpreferences.SharedPreferencesUtils;
import com.lantian.lib_base.database.syncdata.SyncDataStatus;
import com.lantian.lib_base.entity.module.response.breeds.BreedIndex;
import com.lantian.lib_base.entity.module.response.breeds.BreedsOfUser;
import com.lantian.lib_base.entity.module.response.breeds.EarTag;
import com.lantian.lib_base.entity.module.response.breeds.editEarTag;
import com.lantian.lib_base.entity.module.response.farmer.farmlist.FarmListResponse;
import com.lantian.lib_base.utils.BaseUtils;
import com.lantian.lib_base.utils.EventMessage;
import com.lantian.lib_commin_ui.base.ActivityManagerUtil;
import com.lantian.lib_commin_ui.base.BaseActivity;
import com.lantian.lib_commin_ui.dialog.AlertDialogUtil;
import com.lantian.lib_commin_ui.indicator.CHANNEL;
import com.lantian.lib_commin_ui.pager_indictor.ScaleTransitionPagerTitleView;
import com.lantian.lib_commin_ui.viewpager.MyViewPager;
import com.lantian.lib_docs.R;
import com.lantian.lib_docs.breeddoc.adapter.HaveBreedAdapter;
import com.lantian.lib_docs.breeddoc.fragment.HomeChartFragment;
import com.lantian.lib_docs.farmdoc.adapter.HPagerAdapter;
import com.lantian.lib_docs.service.SynDataService;
import com.lantian.lib_docs.utils.AdressUtils;
import com.lantian.lib_network.common.ErroCode;
import com.lantian.lib_network.dialog.DialogUtils;
import com.lantian.lib_network.networkstatus.NetWorkStatus;
import com.lantian.lib_network.retrofit2.MyCallBack;
import com.lantian.lib_network.retrofit2.RetrofitHelper;
import com.lantian.lib_network.utils.NetworkUtils;

import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.ViewPagerHelper;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.SimplePagerTitleView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Sherlock·Holmes on 2020-03-19
 */
public class HaveBreedActivity extends BaseActivity implements View.OnClickListener {

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
    private static final CHANNEL[] BREED_CHANNELS =
            new CHANNEL[]{
                    CHANNEL.MONTH,
                    CHANNEL.YERS,
                    CHANNEL.CUSTOM
            };


    private static final String DAO_BIAO_USER = "5";
    private TextView userName;
    private ImageView head;
    private ImageView back;
    private RecyclerView breedsTable;
    private Context context;
    private Bundle bundle = new Bundle();
    private String userid;
    private MagicIndicator mMagicIndicator;
    private MyViewPager mMyviewPager;
    private HPagerAdapter hPagerAdapter;
    private ArrayList<Fragment> fragments;
    private BreedIndexDao breedIndexDao;
    private BreedsOfUserDao breedsOfUserDao;
    private editEarTagDao mEditEarTagDao;
    private SharedPreferencesUtils sharedPreferencesUtils;
    private List<BreedsOfUser.DataBean> breeds;
    private ArrayList<String> user_ids;
    private List<String> breed_class_ids;
    private List<BreedIndex> breedIndexList;
    private List<editEarTag> mEditeEarTag;
    private BreedsListDao breedsListDao;
    private EarTagDao earTagDao;
    private DialogUtils dialogUtils;
    private String NORMAL = "1";
    private String[] str;
    private String[] strs;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_have_breed;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        initView();
    }

    private void initView() {
        AdressUtils adressUtils = new AdressUtils();
        adressUtils.initData(HaveBreedActivity.this);
        /**页面刷新回调**/
        EventBus.getDefault().register(this);

        breedIndexDao = ((MyApp) BaseUtils.getContext()).getDaoSession().getBreedIndexDao();
        breedsOfUserDao =((MyApp) BaseUtils.getContext()).getDaoSession().getBreedsOfUserDao();
        breedsListDao = ((MyApp) BaseUtils.getContext()).getDaoSession().getBreedsListDao();
        mEditEarTagDao = ((MyApp) BaseUtils.getContext()).getDaoSession().getEditEarTagDao();

        earTagDao = ((MyApp) BaseUtils.getContext()).getDaoSession().getEarTagDao();
        back = findViewById(R.id.haveBreedBack);
        dialogUtils = new DialogUtils();
        userName = findViewById(R.id.buser_name);
        breedsTable = findViewById(R.id.breeds_table);
        head = findViewById(R.id.cio_head);
        mMyviewPager = findViewById(R.id.myviewPager);
        mMagicIndicator = findViewById(R.id.my_magic_indicator);
        mMyviewPager.setOnClickListener(this);
        back.setOnClickListener(this);
        breedsTable.setLayoutManager(new GridLayoutManager(this, 3));
        breeds = new ArrayList<>();
        breedIndexList = new ArrayList<>();
        breed_class_ids = new ArrayList<>();
        mEditeEarTag = new ArrayList<>();
        initData();
        initFragments();



    }

    private void initData() {
        sharedPreferencesUtils = new SharedPreferencesUtils(BaseUtils.getContext());
        final Object isAdmin = sharedPreferencesUtils.getParam("isAdmin", "");
        MyApp.isAdmin = (String) isAdmin;
        /**打标用户**/
        if (MyApp.isAdmin.equals(DAO_BIAO_USER)) {
        bundle = getIntent().getExtras();
        Serializable serializable = bundle.getSerializable("data");
            if (serializable != null) {
            //todo 有网络状态
            if (NetWorkStatus.isNetworkAvailable(this)) {
                        FarmListResponse farmListResponse = (FarmListResponse) serializable;
                        userName.setText(farmListResponse.getUsername());
                        Glide.with(this).load(farmListResponse.getAvatar()).into(head);
                        userid = farmListResponse.getUser_id();
                        getBreedClassId();
                        Log.e("userid",userid);
                        getIds();
                //todo 无网络状态
            } else {
                /**数据库查找信息**/
                FarmListResponse farmListResponse = (FarmListResponse) serializable;
                userName.setText(farmListResponse.getUsername());
                Glide.with(this).load(farmListResponse.getAvatar()).into(head);
                userid = farmListResponse.getUser_id();
                initFragments();
                BreedsOfUser breedsOfUser  = breedsOfUserDao.queryBuilder().where(BreedsOfUserDao.Properties.User_id.eq(userid)).unique();
                    for (int i=0;i<breedsOfUser.getData().size();i++){
                        if (breedsOfUser.getData().get(i).getCount()!=null){
                            breeds.add(breedsOfUser.getData().get(i));
                            breed_class_ids.add(breedsOfUser.getData().get(i).getId());
                        }
                    }
                    breedsTable.setAdapter(new HaveBreedAdapter(breeds, context, String.valueOf(userid)));
             }
          }
            /**非打标用户**/
        } else {
            if (NetworkUtils.isConnected()){
                userid = MyApp.Userid;
                Log.e("普通用户",userid);
                CheckBreeds(userid);
            }else {
                userid = (String) sharedPreferencesUtils.getParam("user_id","");
                getBreedClassId();
            }

        }
    }

    /**
     * 获取户主养殖的养殖信息
     */
    private void getBreedClassId() {
        BreedsOfUser breedsOfUser  = breedsOfUserDao.queryBuilder().where(BreedsOfUserDao.Properties.User_id.eq(userid)).unique();
        for (int i=0;i<breedsOfUser.getData().size();i++){
            if (breedsOfUser.getData().get(i).getCount()!=null){
                breeds.add(breedsOfUser.getData().get(i));
                breed_class_ids.add(breedsOfUser.getData().get(i).getId());
            }
            breedsTable.setAdapter(new HaveBreedAdapter(breeds, context, userid));

        }
    }


    /**
     * 打标用户
     * 获取所有用户的id
     */
    private void getIds(){
        str = new String[]{"当前用户","全部用户"};
        user_ids = new ArrayList<>();
        List<BreedsOfUser> breedsOfUsers = breedsOfUserDao.queryBuilder().list();
        for (int i = 0; i < breedsOfUsers.size(); i++) {
            user_ids.add(breedsOfUsers.get(i).getUser_id());
        }
        /**检查本地是否有没有更新的数据**/
        checkUpdata();
        getThisUserEarTag(userid);
        /**判断数据库是否有数据**/
        List<BreedIndex>  breedIndexList = breedIndexDao.queryBuilder().where(BreedIndexDao.Properties.User_id.eq(userid)).list();
        if (breedIndexList.size() == 0) {
            if (!isFinishing()){
                AlertDialogUtil.TwoChoiceDialog(HaveBreedActivity.this, "数据加载", "请选择加载数据选项", str, new AlertDialogUtil.TwoChoiceHandle() {
                    @Override
                    public void onPositiveButtonHandle() {
                        /**下载指定用户信息**/
                        getThisUserOfBreeds();
                        getThisUserEarTag(userid);
                    }
                    @Override
                    public void onNegativeButtonHandle() {
                        Intent intent = new Intent(HaveBreedActivity.this, SynDataService.class);
                        //intent.setAction(SynDataService.START_SYN);
                        //intent.putStringArrayListExtra("user_ids",user_ids);
                        startService(intent);
                      //  getAllBreedsDetailedInfo();
                       // getAllUserBreedsMsg();
                    }
                });
            }
        }else {
            return;
        }
    }

    /**
     * 检查用户在本地是否有更新的数据
     */
    private void checkUpdata(){
        strs = new String[]{"确定","取消"};
        List<editEarTag> editEarTags = mEditEarTagDao.queryBuilder().where(mEditEarTagDao.queryBuilder()
                .and(editEarTagDao.Properties.User_id.eq(userid),editEarTagDao.Properties.Status.eq(SyncDataStatus.ADD.ordinal()))).list();
        if (editEarTags.size()!=0){
            AlertDialogUtil.TwoChoiceDialog(HaveBreedActivity.this, "温馨提示", "您有"+editEarTags.size()+"条数据未同步，请先上传数据到云服务器！", strs, new AlertDialogUtil.TwoChoiceHandle() {
                @Override
                public void onPositiveButtonHandle() {
                    dialogUtils.showProgress(HaveBreedActivity.this,"数据正在上传,请稍等...");
                    Intent intent = new Intent(HaveBreedActivity.this,SynDataService.class);
                    intent.setAction(SynDataService.CHECK);
                    intent.putExtra("userid",userid);
                    startService(intent);
                }
                @Override
                public void onNegativeButtonHandle() {
                    Intent intent = new Intent(HaveBreedActivity.this,SynDataService.class);
                    intent.setAction(SynDataService.CHECK);
                    intent.putExtra("userid",userid);
                    startService(intent);
                }
            });
        }
    }

    /**
     * EvetBus 回调，判断是否上传完数据
     * @param eventMessage
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void refreshUiEventBus(EventMessage eventMessage){
        /**api刷新数据**/
        if (eventMessage.getMsg() == 3){
           dialogUtils.dismissProgress();
        }
    }

    /**
     * 获取当前用户的耳标
     * @param userid
     */
    private void getThisUserEarTag(final String userid) {
        final List<EarTag> lcoearTags =  earTagDao.queryBuilder().where(EarTagDao.Properties.User_id.eq(userid)).list();
        RetrofitHelper.getApiService().getEarTag(userid,NORMAL).enqueue(new MyCallBack<ArrayList<EarTag>>() {
            @Override
            public void success(ArrayList<EarTag> earTags) {
                if (lcoearTags.size()!=earTags.size()){
                    earTagDao.insertOrReplaceInTx(earTags);
                }
            }
            @Override
            public void failure(String msg) {
                showToast(msg);
            }
        });
    }

    /**
     * 获取当前用户下养殖牲畜的详细信息
     */
    private void getThisUserOfBreeds(){
            for (int i =0;i<breed_class_ids.size();i++){
                getBreedData(breed_class_ids.get(i),userid);
        }
    }

    /**
     * 初始化fragments
     */
    private void initFragments() {
        fragments = new ArrayList<>();
        fragments.add(HomeChartFragment.newInstance(3,
                firstDayForMonth.format(date),
                lastDayForMonth.format(date),userid)
        );
        fragments.add(HomeChartFragment.newInstance(5,
                firstDayForYear.format(date),
                lastDayForYears.format(date),userid)
        );

        fragments.add(HomeChartFragment.newInstance(6,
                firstDayForMonth.format(date),
                dateForNow.format(date),userid)
        );
        hPagerAdapter = new HPagerAdapter(getSupportFragmentManager(),BREED_CHANNELS,fragments);
        mMyviewPager.setAdapter(hPagerAdapter);
        mMyviewPager.setOffscreenPageLimit(fragments.size());
        initMagicIndicator();
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
                breedIndexDao.insertOrReplaceInTx(breedIndices);
            }
            @Override
            public void failure(String msg) {

            }
        });
    }


    /**
     * 检查牧户养殖的牲畜
     *
     * @param userid
     */
    private void CheckBreeds(final String userid) {
        RetrofitHelper.getApiService().getBreeds(userid).enqueue(new Callback<BreedsOfUser>() {
            @Override
            public void onResponse(Call<BreedsOfUser> call, Response<BreedsOfUser> response) {
                if (response.body().getCode() == ErroCode.SUCCESS){
                    if (!MyApp.isAdmin.equals(DAO_BIAO_USER)){
                        BreedsOfUser breedsOfUsers = response.body();
                        breedsOfUsers.setUser_id(MyApp.Userid);
                        breedsOfUserDao.insertOrReplace(breedsOfUsers);
                       }
                        for (int i =0;i<response.body().getData().size();i++){
                            if (response.body().getData().get(i).getCount()!=null){
                                breeds.add(response.body().getData().get(i));
                            }
                        }
                    breedsTable.setAdapter(new HaveBreedAdapter(breeds, context, userid));
                }
            }
            @Override
            public void onFailure(Call<BreedsOfUser> call, Throwable t) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        if (v.getId()==R.id.haveBreedBack){
            ActivityManagerUtil.getAppManager().finishActivity();
        }

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
                                    + dateForNow.format(startTime.getTime())
                                    + ","
                                    + dateForNow.format(endTime.getTime()));

                    fragment.sendDate(
                            dateForNow.format(startTime.getTime()),
                            dateForNow.format(endTime.getTime())
                    );
                }
            });
            dialogStart.show();
        } else {
            showToast("系统不支持");
        }
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
