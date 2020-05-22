package com.lantian.lib_docs.farmdoc.view.farmerdata.chengyuan;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;

import com.lantian.lib_base.MyApp;
import com.lantian.lib_commin_ui.base.ActivityManagerUtil;
import com.lantian.lib_commin_ui.base.BaseActivity;
import com.lantian.lib_commin_ui.indicator.CHANNEL;
import com.lantian.lib_commin_ui.indicator.MyMagicIndicator;
import com.lantian.lib_commin_ui.viewpager.MyViewPager;
import com.lantian.lib_docs.R;
import com.lantian.lib_docs.farmdoc.adapter.HPagerAdapter;

import net.lucode.hackware.magicindicator.MagicIndicator;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sherlock·Holmes on 2020/4/22
 */
public class FarmFDHomeActivity extends BaseActivity implements View.OnClickListener {


    private static final CHANNEL[] ITEMS =
            new CHANNEL[]{
                    CHANNEL.CENGYUAN,
                    CHANNEL.SHOUZHI,
                    CHANNEL.CAOYUAN,
                    CHANNEL.MUHUBUTIE
            };
    private ImageView mItemsBttnBack;
    private MagicIndicator mItemMagicIndicator;
    private MyViewPager mItemViewPager;
    private List<Fragment> fragmentList;
    private HPagerAdapter hPagerAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_farm_items;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        initView();
    }

    private void initView() {
        mItemsBttnBack = (ImageView) findViewById(R.id.items_bttn_back);
        mItemsBttnBack.setOnClickListener(this);
        mItemMagicIndicator = (MagicIndicator) findViewById(R.id.item_magic_indicator);
        mItemMagicIndicator.setOnClickListener(this);
        mItemViewPager = (MyViewPager) findViewById(R.id.item_viewPager);
        mItemViewPager.setOnClickListener(this);
        initFragments();
    }

    private void initFragments(){
        fragmentList = new ArrayList<>();
        fragmentList.add(FragmentItem.newInstance(0, MyApp.Userid));
        fragmentList.add(FragmentItem.newInstance(1,MyApp.Userid));
        fragmentList.add(FragmentItem.newInstance(2,MyApp.Userid));
        fragmentList.add(FragmentItem.newInstance(3,MyApp.Userid));
        hPagerAdapter = new HPagerAdapter(getSupportFragmentManager(),ITEMS,fragmentList);
        mItemViewPager.setAdapter(hPagerAdapter);
        mItemViewPager.setOffscreenPageLimit(fragmentList.size());
        /**初始化指示器**/
        MyMagicIndicator.initSimpleMagicIndicator(this,ITEMS,mItemViewPager,mItemMagicIndicator,16);
        mItemViewPager.setCurrentItem(getIntent().getIntExtra("selected",0));
    }


    @Override
    public void onClick(View v) {
        if (v.getId()==R.id.items_bttn_back){
            ActivityManagerUtil.getAppManager().finishActivity();
        }
    }
}
