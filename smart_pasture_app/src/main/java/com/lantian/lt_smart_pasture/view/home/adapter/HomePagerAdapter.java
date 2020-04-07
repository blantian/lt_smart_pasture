package com.lantian.lt_smart_pasture.view.home.adapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.lantian.lib_commin_ui.indicator.CHANNEL;
import com.lantian.lt_smart_pasture.view.mine.MineFragment;
import com.lantian.lt_smart_pasture.view.mypasture.MyPastureFragment;
import com.lantian.lt_smart_pasture.view.product.HomePageFragment;

/**
 * HomePagerAdapter
 * Created by Sherlock·Holmes on 2020-02-11
 */
public class HomePagerAdapter extends FragmentPagerAdapter {

    private CHANNEL[] mList;

    public HomePagerAdapter(FragmentManager fm, CHANNEL[] datas) {
        super(fm);
        mList = datas;
    }

    /**
     * 这种方式，避免一次性创建所有的framgent
     * @param position
     * @return
     */
    @Override
    public Fragment getItem(int position) {
        int type = mList[position].getValue();
        switch (type) {
            case CHANNEL.MINE_CENTER_ID:
                return MineFragment.newInstance();
            case CHANNEL.HOME_PAGE_ID:
                return HomePageFragment.newInstance();
            case CHANNEL.MY_PASTURE_ID:
                return MyPastureFragment.newInstance();
        }
        return null;
    }

    @Override
    public int getCount() {
        return mList == null ? 0 : mList.length;
    }
}
