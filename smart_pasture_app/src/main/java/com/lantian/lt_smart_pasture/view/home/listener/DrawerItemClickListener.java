package com.lantian.lt_smart_pasture.view.home.listener;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.lantian.lib_base.utils.ToastUtils;
import com.lantian.lt_smart_pasture.view.home.HomeActivity;

/**
 * Created by Sherlock·Holmes on 2020-03-14
 */
public class DrawerItemClickListener implements ListView.OnItemClickListener {

    HomeActivity homeActivity;

    public DrawerItemClickListener(HomeActivity homeActivity){
        this.homeActivity = homeActivity;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        selectItem(position);
    }

    public void selectItem(int position){
        Toast.makeText(homeActivity, "menu item"+ String.valueOf(position), Toast.LENGTH_SHORT).show();
    }
}
