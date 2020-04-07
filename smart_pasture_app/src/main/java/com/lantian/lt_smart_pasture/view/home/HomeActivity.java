package com.lantian.lt_smart_pasture.view.home;

import android.annotation.SuppressLint;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.Gravity;
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
import com.lantian.lib_commin_ui.base.BaseActivity;
import com.lantian.lib_commin_ui.indicator.CHANNEL;
import com.lantian.lib_commin_ui.indicator.MyMagicIndicator;
import com.lantian.lib_commin_ui.viewpager.MyViewPager;
import com.lantian.lib_lan.camera.control.DevManageGuider;
import com.lantian.lib_lan.camera.control.SDKGuider;
import com.lantian.lib_lan.camera.model.DBDevice;
import com.lantian.lt_smart_pasture.R;
import com.lantian.lt_smart_pasture.view.camera.AddDevActivity;
import com.lantian.lt_smart_pasture.view.camera.LANDevActivity;
import com.lantian.lt_smart_pasture.view.camera.dev.DevStatus;
import com.lantian.lt_smart_pasture.view.home.adapter.BottomNavigationAdapter;
import com.lantian.lt_smart_pasture.view.home.adapter.HomePagerAdapter;
import com.lantian.lt_smart_pasture.view.home.drawer.DrawLayoutProxy;
import com.lantian.lt_smart_pasture.view.login.LoginActivity;
import com.lantian.lt_smart_pasture.view.mine.MineFragment;
import com.lantian.lt_smart_pasture.view.mypasture.MyPastureFragment;
import com.lantian.lt_smart_pasture.view.product.HomePageFragment;

import net.lucode.hackware.magicindicator.MagicIndicator;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 首页Activity
 * Created by Sherlock·Holmes on 2020-02-11
 */
public class HomeActivity extends BaseActivity implements View.OnClickListener, BottomNavigationView.OnNavigationItemSelectedListener,
        ViewPager.OnPageChangeListener, ViewPager.OnTouchListener, DevStatus {

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
    @BindView(R.id.dvis)
    LinearLayout mCamera;
    @BindView(R.id.left_drawer_layout)
    LinearLayout leftDrawerLayout;
    @BindView(R.id.add_camera)
    LinearLayout addCamera;
    @BindView(R.id.toggle_view)
    TextView toggleView;
    @BindView(R.id.title_layout)
    RelativeLayout titleLayout;
    @BindView(R.id.add_dev)
    TextView addDev;

    private List<Fragment> fragmentList;
    private HomePagerAdapter mAdapter;
    private BottomNavigationAdapter mBottomNavigationAdapter;
    private Fragment mMineFragment;
    private Fragment mMyPastureFragment;
    private Fragment mHomePageFragment;

    DevManageGuider.DeviceItem mDeviceInfo = null;
    private DrawLayoutProxy m_dlDraw;
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
        intiView();
    }

    @SuppressLint({"NewApi", "ResourceType"})
    private void intiView() {
        fragmentList = new ArrayList<>();
        mMineFragment = MineFragment.newInstance();
        mMyPastureFragment = MyPastureFragment.newInstance();
        mHomePageFragment = HomePageFragment.newInstance();
        m_dbDev = DBDevice.getInstance(this);
        m_dlDraw = new DrawLayoutProxy(this);
        toggleView.setOnClickListener(this);
        mCamera.setOnClickListener(this);
        addDev.setOnClickListener(this);

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
        //initMagicIndicator();
        MyMagicIndicator.initMagicIndicator(this,CHANNELS,mViewPager,mMagicIndicator,20);
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
            case R.id.dvis:
                if(isOnlineOrChooseDev()){
                    LANDevActivity.instance(this, LANDevActivity.class, null);
                }
                break;
            case R.id.add_dev:
                AddDevActivity.instance(this,AddDevActivity.class,null);
            case R.id.toggle_view:
                drawerLayout.openDrawer(Gravity.LEFT);
                break;
            default:
        }
        mViewPager.setCurrentItem(1);
        mBtnNavigationView.getMenu().getItem(1).setChecked(true);
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
     * 设备的判断
     * @return
     */
    @Override
    public boolean isOnlineOrChooseDev() {
        mDeviceInfo = SDKGuider.sdkGuider.manageGuider.getCurrSelectDev();
        if(mDeviceInfo == null) {
            showToast("获取设备失败！");
            return false;
        }else {
            if(mDeviceInfo.m_struDevState.m_iLogState != 1){
                showToast("请先登录设备！");
                return false;
            }
        }
        return true;
    }
}
