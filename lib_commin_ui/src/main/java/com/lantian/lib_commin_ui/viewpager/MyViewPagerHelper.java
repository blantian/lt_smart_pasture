package com.lantian.lib_commin_ui.viewpager;

import androidx.viewpager.widget.ViewPager;

import java.lang.reflect.Field;

/**
 * Created by Sherlock·Holmes on 2020-03-31
 */
public class MyViewPagerHelper {

    ViewPager viewPager;
    MyScroller scroller;

    public MyViewPagerHelper(ViewPager viewPager) {
        this.viewPager = viewPager;
        init();
    }

    public MyScroller getScroller() {
        return scroller;
    }
    private void init() {
        scroller = new MyScroller(viewPager.getContext());
        Class<ViewPager> cl = ViewPager.class;
        try {
            //利用反射设置mScroller域为自己定义的WScroller，这里的命名（‘mScroller’）不能随意改
            Field field = cl.getDeclaredField("mScroller");
            field.setAccessible(true);
            field.set(viewPager, scroller);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
