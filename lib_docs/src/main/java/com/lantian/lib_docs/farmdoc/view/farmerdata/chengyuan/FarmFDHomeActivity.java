package com.lantian.lib_docs.farmdoc.view.farmerdata.chengyuan;

import android.os.Bundle;

import com.lantian.lib_commin_ui.base.BaseActivity;
import com.lantian.lib_commin_ui.indicator.CHANNEL;
import com.lantian.lib_commin_ui.indicator.MyMagicIndicator;
import com.lantian.lib_commin_ui.viewpager.MyViewPager;
import com.lantian.lib_docs.R;

import net.lucode.hackware.magicindicator.MagicIndicator;

/**
 * Created by Sherlock·Holmes on 2020-04-03
 */
public class FarmFDHomeActivity extends BaseActivity {


    //指定首页标题
    private static final CHANNEL[] FDHOME_CHANNELS =
            new CHANNEL[]{
                    CHANNEL.CENGYUAN,
                    CHANNEL.SHOUZHI,
                    CHANNEL.CAOYUAN,
                    CHANNEL.MUHUBUTIE
            };

    private MagicIndicator mFdMagicindicator;

    private MyViewPager mFdViewPager;

    @Override
    protected int getLayoutId() {
        return R.layout.famr_fd_home;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        initView();
    }

    private void initView() {
        mFdMagicindicator = (MagicIndicator) findViewById(R.id.fd_magicindicator);
        mFdViewPager = (MyViewPager) findViewById(R.id.fd_viewPager);
        MyMagicIndicator.initMagicIndicator(this,FDHOME_CHANNELS,mFdViewPager,mFdMagicindicator,18);
    }
}
