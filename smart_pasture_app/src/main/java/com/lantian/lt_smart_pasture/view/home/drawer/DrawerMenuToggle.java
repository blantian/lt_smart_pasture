package com.lantian.lt_smart_pasture.view.home.drawer;

import android.app.Activity;
import android.view.View;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.drawerlayout.widget.DrawerLayout;

/**
 * Created by Sherlock·Holmes on 2020-03-14
 */
public class DrawerMenuToggle extends ActionBarDrawerToggle {
    public DrawerMenuToggle(Activity activity, DrawerLayout drawerLayout, int openDrawerContentDescRes, int closeDrawerContentDescRes) {
        super(activity, drawerLayout, openDrawerContentDescRes, closeDrawerContentDescRes);
    }



    @Override
    public void onDrawerClosed(View view) {
        super.onDrawerClosed(view);

    }

    /** 当侧滑菜单完全打开时，这个方法被回调 */
    @Override
    public void onDrawerOpened(View drawerView) {
        super.onDrawerOpened(drawerView);

    }
}
