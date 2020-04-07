package com.lantian.lib_docs.farmdoc.view.home;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.PagerAdapter;

import com.lantian.lib_commin_ui.base.BaseActivity;
import com.lantian.lib_commin_ui.indicator.CHANNEL;
import com.lantian.lib_commin_ui.indicator.MyMagicIndicator;
import com.lantian.lib_commin_ui.viewpager.MyViewPager;
import com.lantian.lib_docs.R;
import com.lantian.lib_docs.farmdoc.adapter.HPagerAdapter;
import com.lantian.lib_docs.farmdoc.view.farmerdata.BaseMsgFragment;
import com.lantian.lib_docs.farmdoc.view.farmerdata.CompleteMsgFragment;

import net.lucode.hackware.magicindicator.MagicIndicator;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sherlock·Holmes on 2020-03-18
 */
public class FarmHomeActivity extends BaseActivity {


    //指定首页标题
    private static final CHANNEL[] CHANNELS =
            new CHANNEL[]{
                    CHANNEL.FARM_MSG,
                    CHANNEL.COM_MSG,
            };

    private MagicIndicator magicIndicator;
    private PagerAdapter pagerAdapter;
    private MyViewPager viewPager;
    private List<Fragment> fragmentList;
    private Fragment baseMsgFragment,completeMsgFragment;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_farm;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        initView();
    }

    private void initView() {
        magicIndicator = findViewById(R.id.magic_indicator);
        viewPager = findViewById(R.id.viewPager);
        fragmentList = new ArrayList<>();
        baseMsgFragment = BaseMsgFragment.newInstance();
        completeMsgFragment = CompleteMsgFragment.newInstance();
        pagerAdapter = new HPagerAdapter(getSupportFragmentManager(), CHANNELS);
        viewPager.setAdapter(pagerAdapter);
        viewPager.setOffscreenPageLimit(fragmentList.size());
        intiFragmens();
        initMagicIndicator();
    }

    private void initMagicIndicator() {
        MyMagicIndicator.initMagicIndicator(this,CHANNELS,viewPager,magicIndicator,16);
    }

    private void intiFragmens() {
        if (!fragmentList.contains(baseMsgFragment)){
            fragmentList.add(baseMsgFragment);
        }
        if (!fragmentList.contains(completeMsgFragment)){
            fragmentList.add(completeMsgFragment);
        }
    }

}
