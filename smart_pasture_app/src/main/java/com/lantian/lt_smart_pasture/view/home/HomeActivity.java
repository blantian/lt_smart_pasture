package com.lantian.lt_smart_pasture.view.home;

import android.annotation.SuppressLint;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.lantian.lib_commin_ui.base.BaseActivity;
import com.lantian.lib_commin_ui.indicator.CHANNEL;
import com.lantian.lib_commin_ui.indicator.MyMagicIndicator;
import com.lantian.lib_commin_ui.viewpager.MyViewPager;
import com.lantian.lib_lan.camera.control.DevManageGuider;
import com.lantian.lib_lan.camera.model.DBDevice;
import com.lantian.lib_lan.device.view.DevHomeActivity;
import com.lantian.lib_network.receiver.NetStateChangeReceiver;
import com.lantian.lib_network.receiver.interfaces.NetStateChangeObserver;
import com.lantian.lib_network.utils.EventNetMessage;
import com.lantian.lib_network.utils.NetworkType;
import com.lantian.lt_smart_pasture.R;
import com.lantian.lt_smart_pasture.view.home.adapter.BottomNavigationAdapter;
import com.lantian.lt_smart_pasture.view.home.adapter.HomePagerAdapter;
import com.lantian.lt_smart_pasture.view.login.LoginActivity;
import com.lantian.lt_smart_pasture.view.mine.MineFragment;
import com.lantian.lt_smart_pasture.view.mypasture.MyPastureFragment;
import com.lantian.lt_smart_pasture.view.product.HomePageFragment;

import net.lucode.hackware.magicindicator.MagicIndicator;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 首页Activity
 * Created by Sherlock·Holmes on 2020-02-11
 */
public class HomeActivity extends BaseActivity implements View.OnClickListener, BottomNavigationView.OnNavigationItemSelectedListener,
        ViewPager.OnPageChangeListener, ViewPager.OnTouchListener, NetStateChangeObserver {

    //指定首页标题
    private static final CHANNEL[] CHANNELS =
            new CHANNEL[]{
                    CHANNEL.HOME,
                    CHANNEL.PASTURE,
                    CHANNEL.MINE
            };
    /**
     * bind view
     */
    @BindView(R.id.magic_indicator)
    MagicIndicator mMagicIndicator;
    @BindView(R.id.myviewPager)
    MyViewPager mViewPager;
    @BindView(R.id.btn_navigation_view)
    BottomNavigationView mBtnNavigationView;
    @BindView(R.id.ma_iv_index)
    ImageView mMaIvIndex;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;
    @BindView(R.id.left_drawer_layout)
    LinearLayout leftDrawerLayout;
    @BindView(R.id.add_camera)
    LinearLayout addCamera;
    @BindView(R.id.toggle_view)
    TextView toggleView;
    @BindView(R.id.title_layout)
    RelativeLayout titleLayout;
    @BindView(R.id.my_devs)
    FloatingActionButton devs;

    private List<Fragment> fragmentList;
    private HomePagerAdapter mAdapter;
    private BottomNavigationAdapter mBottomNavigationAdapter;
    private Fragment mMineFragment;
    private Fragment mMyPastureFragment;
    private Fragment mHomePageFragment;

    DevManageGuider.DeviceItem mDeviceInfo = null;
    private DBDevice m_dbDev;
    private LoginActivity LoginActivity;
    String check;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_home;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        ButterKnife.bind(this);
        NetStateChangeReceiver.registerReceiver(this);
        intiView();
    }

    @SuppressLint({"NewApi", "ResourceType"})
    private void intiView() {
        fragmentList = new ArrayList<>();
        mMineFragment = MineFragment.newInstance();
        mMyPastureFragment = MyPastureFragment.newInstance();
        mHomePageFragment = HomePageFragment.newInstance();
        m_dbDev = DBDevice.getInstance(this);
        toggleView.setOnClickListener(this);
        devs.setOnClickListener(this);

        if (!fragmentList.contains(mMineFragment)) {
            fragmentList.add(mMineFragment);
        }
        if (!fragmentList.contains(mMyPastureFragment)) {
            fragmentList.add(mMyPastureFragment);
        }
        if (!fragmentList.contains(mHomePageFragment)) {
            fragmentList.add(mHomePageFragment);
        }

        //初始化Adapter
        mBottomNavigationAdapter = new BottomNavigationAdapter(getSupportFragmentManager(), fragmentList);
        mAdapter = new HomePagerAdapter(getSupportFragmentManager(), CHANNELS);
        mBtnNavigationView.setSelectedItemId(R.id.navigation_home);
        mViewPager.setAdapter(mBottomNavigationAdapter);
        mViewPager.setAdapter(mAdapter);
        mBtnNavigationView.setOnNavigationItemSelectedListener(this);
        mViewPager.addOnPageChangeListener(this);
        mViewPager.setOffscreenPageLimit(fragmentList.size());
        mBtnNavigationView.setItemTextColor(null);
        mBtnNavigationView.setItemIconTintList(null);
        mMaIvIndex.setOnClickListener(this);
        Resources resources = getResources();
        mBtnNavigationView.setItemTextColor(resources.getColorStateList(R.drawable.selector_bottom_navigation, null));
        MyMagicIndicator.initSimpleMagicIndicator(this,CHANNELS,mViewPager,mMagicIndicator,14);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.toggle_view:
                drawerLayout.openDrawer(Gravity.LEFT);
                break;
            case R.id.ma_iv_index:
                mViewPager.setCurrentItem(1);
                mBtnNavigationView.getMenu().getItem(1).setChecked(true);
                break;
            default:
            case R.id.my_devs:
                DevHomeActivity.instance(HomeActivity.this,DevHomeActivity.class,null);
        }
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return false;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        mBtnNavigationView.getMenu().getItem(position).setChecked(true);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        switch (menuItem.getItemId()) {
            case R.id.navigation_home:
                mViewPager.setCurrentItem(0);
                break;
            case R.id.ma_iv_index:
                mViewPager.setCurrentItem(1);
                break;
            case R.id.navigation_notifications:
                mViewPager.setCurrentItem(2);

                break;
                default:
        }
        return true;
    }


    /**
     *取消返回键
     * @param keyCode
     * @param event
     * @return
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK
                && event.getAction() == KeyEvent.ACTION_DOWN) {
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onPause() {
        super.onPause();
        NetStateChangeReceiver.unRegisterObserver(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        NetStateChangeReceiver.registerObserver(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        NetStateChangeReceiver.unRegisterReceiver(this);
    }

    @Override
    public void onNetDisconnected() {

    }

    @Override
    public void onNetConnected(NetworkType networkType) {
        Log.e("TYPE",networkType.toString());
        EventBus.getDefault().postSticky(new EventNetMessage(1,networkType));
    }
}
