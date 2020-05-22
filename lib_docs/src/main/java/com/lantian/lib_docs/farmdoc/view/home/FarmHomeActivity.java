package com.lantian.lib_docs.farmdoc.view.home;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.PagerAdapter;

import com.lantian.lib_base.MyApp;
import com.lantian.lib_base.entity.module.response.farmer.farmlist.HuzhuList;
import com.lantian.lib_base.utils.EventMessage;
import com.lantian.lib_commin_ui.base.ActivityManagerUtil;
import com.lantian.lib_commin_ui.base.BaseActivity;
import com.lantian.lib_commin_ui.indicator.CHANNEL;
import com.lantian.lib_commin_ui.indicator.MyMagicIndicator;
import com.lantian.lib_commin_ui.viewpager.MyViewPager;
import com.lantian.lib_docs.R;
import com.lantian.lib_docs.farmdoc.adapter.HPagerAdapter;
import com.lantian.lib_docs.farmdoc.view.farmerdata.BaseMsgFragment;
import com.lantian.lib_docs.farmdoc.view.farmerdata.CompleteMsgFragment;
import com.lantian.lib_network.retrofit2.MyCallBack;
import com.lantian.lib_network.retrofit2.RetrofitHelper;

import net.lucode.hackware.magicindicator.MagicIndicator;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sherlock·Holmes on 2020-03-18
 */
public class FarmHomeActivity extends BaseActivity implements View.OnClickListener {


    //指定首页标题
    private static final CHANNEL[] CHANNELS =
            new CHANNEL[]{
                    CHANNEL.FARM_MSG,
                    CHANNEL.COM_MSG,
            };

    private int type =0;
    private MagicIndicator magicIndicator;
    private PagerAdapter pagerAdapter;
    private MyViewPager viewPager;
    private List<Fragment> fragmentList;
    private Fragment baseMsgFragment,completeMsgFragment;
    private ImageView back;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_farm;
    }
    @Override
    protected void init(Bundle savedInstanceState) {
        initView();
    }
    private void initView() {
        Bundle bundle = getIntent().getExtras();
        EventBus.getDefault().register(this);
        back = findViewById(R.id.bttn_back);
        magicIndicator = findViewById(R.id.magic_indicator);
        viewPager = findViewById(R.id.viewPager);
        fragmentList = new ArrayList<>();
        back.setOnClickListener(this);
        if (bundle !=null){
            String Nofarm = bundle.getString("NoFarm");
            type = getIntent().getIntExtra("type", -1);
            if (Nofarm != null&&Nofarm.equals("0")){
                initData(null);
            }else {
                getHuKu();
            }
        }else {
            getHuKu();
        }
        initMagicIndicator();
    }

    /**
     * 页面回调
     * @param eventMessage
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void refreshUiEventBus(EventMessage eventMessage){
        if (eventMessage.getMsg()==8) {
            Log.e("ceshi","1");
            type = 1;
        }
    }

    private void initMagicIndicator() {
        MyMagicIndicator.initSimpleMagicIndicator(this,CHANNELS,viewPager,magicIndicator,16);
    }

    /**
     * 获取户主信息
     */
    private void getHuKu(){
        RetrofitHelper.getApiService().getHuZhu(MyApp.Userid).enqueue(new MyCallBack<ArrayList<HuzhuList>>() {
            @Override
            public void success(ArrayList<HuzhuList> huzhuLists) {
                HuzhuList huzhuList = huzhuLists.get(0);
                initData(huzhuList);
            }
            @Override
            public void failure(String msg) {
                showToast(msg);
            }
        });
    }

    private void initData(HuzhuList huzhuList) {
        intiFragmens(huzhuList);
    }

    private void intiFragmens(HuzhuList huzhuList) {
        baseMsgFragment = BaseMsgFragment.newInstance(huzhuList);
        completeMsgFragment = CompleteMsgFragment.newInstance(huzhuList);

        if (!fragmentList.contains(baseMsgFragment)){
            fragmentList.add(baseMsgFragment);
        }
        if (!fragmentList.contains(completeMsgFragment)){
            fragmentList.add(completeMsgFragment);
        }
        pagerAdapter = new HPagerAdapter(getSupportFragmentManager(), CHANNELS,fragmentList);
        viewPager.setAdapter(pagerAdapter);
        viewPager.setOffscreenPageLimit(fragmentList.size());
        if (type ==1){
            viewPager.setCurrentItem(type);
        }

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.bttn_back){
            ActivityManagerUtil.getAppManager().finishActivity();
        }
    }
}
