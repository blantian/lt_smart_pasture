package com.lantian.lib_docs.farmdoc.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.lantian.lib_commin_ui.indicator.CHANNEL;
import com.lantian.lib_docs.farmdoc.view.farmerdata.BaseMsgFragment;
import com.lantian.lib_docs.farmdoc.view.farmerdata.CompleteMsgFragment;

import java.util.List;

/**
 * Created by Sherlock·Holmes on 2020-03-27
 */
public class HPagerAdapter extends FragmentPagerAdapter {

    private CHANNEL[] docChannels;
    private List<Fragment> fragmentList;

    public HPagerAdapter(@NonNull FragmentManager fm, CHANNEL[] behavior) {
        super(fm);
        docChannels = behavior;
    }

    public HPagerAdapter(@NonNull FragmentManager fm, CHANNEL[] behavior,List<Fragment> fragmentList){
        super(fm);
        docChannels = behavior;
        this.fragmentList = fragmentList;
    }


    @NonNull
    @Override
    public Fragment getItem(int position) {
        int type = docChannels[position].getValue();
        switch (type){
            case CHANNEL.FARM_MSG_ID:
                return BaseMsgFragment.newInstance();
            case CHANNEL.COM_MSG_ID:
                return CompleteMsgFragment.newInstance();
            case CHANNEL.YERS_ID:
                return fragmentList.get(0);
            case CHANNEL.MONTH_ID:
                return fragmentList.get(1);
            case CHANNEL.CUSTOM_ID:
                return fragmentList.get(2);
                default:
        }
        return null;
    }

    @Override
    public int getCount() {
        return docChannels ==null ? 0 : docChannels.length;
    }

}
