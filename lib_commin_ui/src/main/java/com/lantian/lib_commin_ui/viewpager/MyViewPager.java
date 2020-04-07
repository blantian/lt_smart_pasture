package com.lantian.lib_commin_ui.viewpager;

import android.content.Context;
import android.util.AttributeSet;

import androidx.viewpager.widget.ViewPager;

/**
 * Created by Sherlock·Holmes on 2020-03-31
 */
public class MyViewPager extends ViewPager {

    private MyViewPagerHelper helper;
    public MyViewPager(Context context) {
        this(context, null);
    }

    public MyViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        helper = new MyViewPagerHelper(this);
    }

    @Override
    public void setCurrentItem(int item) {
        setCurrentItem(item, true);
    }

    @Override
    public void setCurrentItem(int item, boolean smoothScroll) {
        MyScroller scroller = helper.getScroller();
        //这里设置大于等于1，意思是2个相邻的tab点击也不出现闪动。可以根据需求自定义
        if (Math.abs(getCurrentItem() - item) >= 1) {
            scroller.setNoDuration(true);
            super.setCurrentItem(item, smoothScroll);
            //这里的设置false为切换后恢复默认的滑动状态，如果取消下面代码，当你滑动viewPager时，就不会有滑动切换效果了。
            scroller.setNoDuration(false);
        } else {
            scroller.setNoDuration(false);
            super.setCurrentItem(item, smoothScroll);
        }
    }
}
