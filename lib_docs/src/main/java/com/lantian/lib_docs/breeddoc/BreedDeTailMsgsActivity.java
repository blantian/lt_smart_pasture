package com.lantian.lib_docs.breeddoc;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.lantian.lib_commin_ui.base.BaseActivity;
import com.lantian.lib_commin_ui.indicator.CHANNEL;
import com.lantian.lib_commin_ui.indicator.MyMagicIndicator;
import com.lantian.lib_commin_ui.viewpager.MyViewPager;
import com.lantian.lib_docs.BuildConfig;
import com.lantian.lib_docs.R;
import com.lantian.lib_docs.breeddoc.adapter.FPagerAdapter;
import com.lantian.lib_docs.breeddoc.fragment.BreedBaseMsgFragment;
import com.lantian.lib_docs.breeddoc.fragment.BreedDacMsgFragment;

import net.lucode.hackware.magicindicator.MagicIndicator;

import java.util.ArrayList;

import me.yokeyword.fragmentation.Fragmentation;

/**
 * Created by Sherlock·Holmes on 2020/4/28
 */
public class BreedDeTailMsgsActivity extends BaseActivity implements View.OnClickListener {

    private CHANNEL[] CHANNELS = new CHANNEL[]{
            CHANNEL.BAS_MSG,
            CHANNEL.DOC_MSG
//            CHANNEL.YAO_MSG,
//            CHANNEL.FANG_YI_MSG,
//            CHANNEL.CI_WEI_MSG,
//            CHANNEL.YIN_SHUI_MSG,
//            CHANNEL.FANG_MU_MSG,
//            CHANNEL.FAN_ZHI_MSG,
//            CHANNEL.RONG_CHAN_MSG,
//            CHANNEL.JIAO_YI_MSG,
//            CHANNEL.TU_ZAI_MSG,
//            CHANNEL.CHU_LAN_MSG,
//            CHANNEL.YI_CHANG_MSG,
//            CHANNEL.PEI_ZHONG_MSG,
//            CHANNEL.CHAN_ROU_MSG,
//            CHANNEL.CHENG_ZHANG_MSG
    };

    private MagicIndicator magicIndicator;
    private ImageView back;
    private MyViewPager viewPager;
    private TextView breedName;
    private ArrayList<Fragment> fragments;
    private FPagerAdapter hPagerAdapter;
    private BreedDacMsgFragment breedDacMsgFragment;
    private BreedBaseMsgFragment breedBaseMsgFragment;
    private String breedid;
    private Bundle bundle;
    private int docMsg;

    @Override
    public void onClick(View v) {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.actiivity_detail_msg;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        initView();
    }

    private void initView() {
        bundle = getIntent().getExtras();
        Fragmentation.builder().stackViewMode(Fragmentation.BUBBLE).debug(BuildConfig.DEBUG).install();
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        fragments = new ArrayList<>();
        breedName = findViewById(R.id.breed_detail_name);
        back =findViewById(R.id.bttn_detail_back);
        magicIndicator = findViewById(R.id.breed_detail_magicindicator);
        viewPager = findViewById(R.id.breed_detail_viewPager);
        MyMagicIndicator.initCommonPagerMagicIndicator(BreedDeTailMsgsActivity.this,CHANNELS,viewPager,magicIndicator);
        initFragment();
    }

    private void initFragment() {
        breedDacMsgFragment = BreedDacMsgFragment.getInstance();
        breedBaseMsgFragment = BreedBaseMsgFragment.getInstance();
        if (bundle != null){
            breedBaseMsgFragment.setArguments(bundle);
            breedDacMsgFragment.setArguments(bundle);
            docMsg = getIntent().getIntExtra("docMsg",-1);
        }

        if (!fragments.contains(breedBaseMsgFragment)){
            fragments.add(breedBaseMsgFragment);
        }
        if (!fragments.contains(breedDacMsgFragment)){
            fragments.add(breedDacMsgFragment);
        }
//        for (int i=0;i<CHANNELS.length-2;i++){
//            fragments.add(new AllBreedMsgFragment());
//        }
        hPagerAdapter = new FPagerAdapter(getSupportFragmentManager(),CHANNELS,fragments);
        viewPager.setAdapter(hPagerAdapter);
        if (docMsg == 2){
            viewPager.setCurrentItem(1);
        }
    }

}
