package com.lantian.lt_smart_pasture.view.home.drawer;

import android.widget.ListView;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.drawerlayout.widget.DrawerLayout;

import com.lantian.lt_smart_pasture.MainActivity;
import com.lantian.lt_smart_pasture.R;
import com.lantian.lt_smart_pasture.view.home.HomeActivity;
import com.lantian.lt_smart_pasture.view.home.adapter.DrawerAdapter;
import com.lantian.lt_smart_pasture.view.home.listener.DrawerItemClickListener;

/**
 * Created by Sherlock·Holmes on 2020-03-14
 */
public class DrawLayoutProxy {

    private HomeActivity homeActivity;
    private DrawerAdapter m_daMenu;
    private ListView m_lvMenu;
    private DrawerLayout m_dlMenu;

    public DrawLayoutProxy(HomeActivity homeActivity) {
        this.homeActivity = homeActivity;
        m_dlMenu = (DrawerLayout)homeActivity.findViewById(R.id.drawer_layout);

        ActionBarDrawerToggle mDrawerToggle;
        m_lvMenu = (ListView)homeActivity.findViewById(R.id.left_drawer);
        m_daMenu = new DrawerAdapter(homeActivity);
        m_lvMenu.setAdapter(m_daMenu);
        m_lvMenu.setOnItemClickListener(new DrawerItemClickListener(homeActivity));
    }
    public DrawerAdapter getDrawerAdapter(){
        return m_daMenu;
    }
}
