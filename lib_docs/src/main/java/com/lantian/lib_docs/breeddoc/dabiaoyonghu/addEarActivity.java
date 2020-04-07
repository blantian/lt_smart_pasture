package com.lantian.lib_docs.breeddoc.dabiaoyonghu;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lantian.lib_base.entity.module.response.breeds.EarTag;
import com.lantian.lib_commin_ui.base.BaseActivity;
import com.lantian.lib_docs.R;
import com.lantian.lib_docs.breeddoc.dabiaoyonghu.adapter.EarTagAdapter;
import com.lantian.lib_network.retrofit2.MyCallBack;
import com.lantian.lib_network.retrofit2.RetrofitHelper;

import java.util.ArrayList;

/**
 * Created by Sherlock·Holmes on 2020-04-01
 */
public class addEarActivity extends BaseActivity implements View.OnClickListener {

    private DrawerLayout drawerLayout;
    private ImageView mEarBtnBack;
    private EditText mEarTagSearch;
    private TextView mBtnEarAdd;
    private ImageView mBreedHeadIcon;
    private EditText mBreedWight;
    private Spinner mBreedSex;
    private Button mSave;
    private Bundle bundle;

    private EditText mDrawerEarTagSearch;
    private TextView mBtnDrawerSearch;
    private Button mBttnDrawerReadCode;
    private Button mBttnDrawerClearCode;
    private RecyclerView mEarDrawerList;
    private Button mBttnAddEar;
    private Button mBttnEarCancel;
    private ArrayList<EarTag> earTags;
    private EarTagAdapter earTagAdapter;
    private GridLayoutManager gridLayoutManager;

    private static final String ALL = "0";
    private static final String NORMAL = "1";
    private static  final String CANCELLATION ="2";
    private String userid;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_add_ear_drawer;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        initView();
    }

    private void initView() {
        bundle = getIntent().getExtras();
        mDrawerEarTagSearch = findViewById(R.id.drawer_ear_tag_search);
        mBtnDrawerSearch = findViewById(R.id.btn_drawer_search);
        mBttnDrawerClearCode = findViewById(R.id.bttn_drawer_clear_code);
        mBttnDrawerReadCode = findViewById(R.id.bttn_drawer_read_code);
        mEarDrawerList = findViewById(R.id.ear_drawer_list);
        mBttnAddEar = findViewById(R.id.bttn_add_ear);
        mBttnEarCancel = findViewById(R.id.bttn_ear_cancel);
        drawerLayout = findViewById(R.id.addDrawerLayout);
        mEarBtnBack = findViewById(R.id.ear_btn_back);
        mEarTagSearch = findViewById(R.id.add_ear_tag_search);
        mSave = findViewById(R.id.save);
        mBreedHeadIcon = findViewById(R.id.breed_head_icon);
        mBreedWight = findViewById(R.id.breed_wight);
        mBreedSex = findViewById(R.id.breed_sex);
        mBtnEarAdd = findViewById(R.id.btn_ear_add);
        gridLayoutManager = new GridLayoutManager(this,1);
        mBtnEarAdd.setOnClickListener(this);
        initData();
    }

    private void initData() {
        if (bundle != null) {
            mEarTagSearch.setText(bundle.getString("earCode"));
            mBreedWight.setText(bundle.getString("weight"));
            userid = bundle.getString("userid");
            Glide.with(addEarActivity.this).load(bundle.getString("img")).into(mBreedHeadIcon);
        }
    }

    @Override 
    public void onClick(View v) {
        if (v.getId() == R.id.btn_ear_add) {
            drawerLayout.openDrawer(Gravity.LEFT);
            initEarData();
        }
    }

    private void initEarData() {
        RetrofitHelper.getApiService().getEarTag(userid,NORMAL).enqueue(new MyCallBack<ArrayList<EarTag>>() {
            @Override
            public void success(ArrayList<EarTag> earTags) {
                initAdapter(earTags);
            }

            @Override
            public void failure(String msg) {

            }
        });
    }

    private void initAdapter(ArrayList<EarTag> earTag) {
        mEarDrawerList.setLayoutManager(gridLayoutManager);
        earTagAdapter = new EarTagAdapter(R.layout.item_ear_tag,earTag);
        mEarDrawerList.setAdapter(earTagAdapter);
        earTagAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {

            }
        });
    }


}
