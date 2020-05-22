package com.lantian.lib_docs.breeddoc.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.lantian.lib_commin_ui.indicator.CHANNEL;

import java.util.List;

/**
 * Created by Sherlock·Holmes on 2020-03-27
 */
public class FPagerAdapter extends FragmentPagerAdapter {

    private CHANNEL[] docChannels;
    private List<Fragment> fragmentList;

    public FPagerAdapter(@NonNull FragmentManager fm, CHANNEL[] behavior, List<Fragment> fragmentList){
        super(fm);
        docChannels = behavior;
        this.fragmentList = fragmentList;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        int type = docChannels[position].getValue();

        switch (type){
            case CHANNEL.BAS_MSG_ID:
                return fragmentList.get(0);
            case CHANNEL.DOC_MSG_ID:
                return fragmentList.get(1);
            case CHANNEL.YAO_MSG_ID:
                return fragmentList.get(2);
            case CHANNEL.FANG_YI_MSG_ID:
                return fragmentList.get(3);
            case CHANNEL.CI_WEI_MSG_ID:
                return fragmentList.get(4);
            case CHANNEL.YIN_SHUI_ID:
                return fragmentList.get(5);
            case CHANNEL.FANG_MU_MSG_ID:
                return fragmentList.get(6);
            case CHANNEL.FAN_ZHI_MSG_ID:
                return fragmentList.get(7);
            case CHANNEL.RONG_CHAN_MSG_ID:
                return fragmentList.get(8);
            case CHANNEL.JIAO_YI_MSG_ID:
                return fragmentList.get(9);
            case CHANNEL.TU_ZAI_MSG_ID:
                return fragmentList.get(10);
            case CHANNEL.CHU_LAN_MSG_ID:
                return fragmentList.get(11);
            case CHANNEL.YI_CHANG_MSG_ID:
                return fragmentList.get(12);
            case CHANNEL.PEI_ZHONG_MSG_ID:
                return fragmentList.get(13);
            case CHANNEL.CHAN_ROU_MSG_ID:
                return fragmentList.get(14);
            case CHANNEL.CHENG_ZHANG_MSG_ID:
                return fragmentList.get(15);
                default:
        }
        return null;
    }

    @Override
    public int getCount() {
        return docChannels ==null ? 0 : docChannels.length;
    }

}
