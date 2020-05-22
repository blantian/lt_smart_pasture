package com.lantian.lib_lan.device.view.home.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.lantian.lib_commin_ui.indicator.CHANNEL;
import com.lantian.lib_lan.device.view.home.fragment.FGDevs;
import com.lantian.lib_lan.device.view.home.fragment.Mine;

/**
 * Created by Sherlock·Holmes on 2020/5/12
 */
public class TitleAdapter extends FragmentPagerAdapter {
    private CHANNEL[] lanchannelList;

    public TitleAdapter(@NonNull FragmentManager fm, CHANNEL[] lanchannels) {
        super(fm);
        this.lanchannelList =lanchannels;

    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        int type = lanchannelList[position].getValue();
        switch (type){
            case CHANNEL.LAN_HOME_ID:
                return FGDevs.getDevsFragment();
                case CHANNEL.LAN_MINE_ID:
                    return Mine.getMineFragment();
            default:
        }
        return null;
    }

    @Override
    public int getCount() {
        return  lanchannelList == null ? 0 : lanchannelList.length;
    }
}
