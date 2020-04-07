package com.lantian.lib_docs.breeddoc.dabiaoyonghu;

import android.content.Context;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.lantian.lib_base.MyApp;
import com.lantian.lib_base.entity.module.response.breeds.BreedIndex;
import com.lantian.lib_commin_ui.base.BaseActivity;
import com.lantian.lib_docs.R;
import com.lantian.lib_docs.breeddoc.adapter.NewBreedAdapter;
import com.lantian.lib_network.retrofit2.MyCallBack;
import com.lantian.lib_network.retrofit2.RetrofitHelper;

import java.util.ArrayList;

/**
 * Created by Sherlock·Holmes on 2020-03-31
 */
public class SingleBreedActivity extends BaseActivity {

    private static final String DAO_BIAO_USER = "5";
    private Context context;
    private ImageView mBBtnBack;
    private String Userid;
    private TextView mBreedName;
    private TextView mRemakeDate;
    private RecyclerView mRecyclerBreedsList;
    private Bundle bundle;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_dabiao_breeds_doc;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        initView();
    }

    private void initView() {
        bundle = getIntent().getExtras();
        mBBtnBack = findViewById(R.id.b_btn_back);
        mBreedName = findViewById(R.id.breed_name);
        mRemakeDate = findViewById(R.id.remake_date);
        mRecyclerBreedsList = findViewById(R.id.recycler_breeds_list);
        mRecyclerBreedsList.setLayoutManager(new GridLayoutManager(this,1));
        initData();
    }

    private void initData() {
        String breedid =bundle.getString("breed_id");
        String breedname = bundle.getString("breed_name");
        String userid = bundle.getString("user_id");
        mBreedName.setText(breedname);
        if (MyApp.isAdmin.equals(DAO_BIAO_USER)){
            Userid = userid;
        }else {
            Userid = MyApp.Userid;
        }
        getBreedData(breedid,Userid);
    }

    private void getBreedData(String breedid,String userid) {
        RetrofitHelper.getApiService().getBreedIndex(userid,breedid,"1").enqueue(new MyCallBack<ArrayList<BreedIndex>>() {
            @Override
            public void success(final ArrayList<BreedIndex> breedIndices) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mRecyclerBreedsList.setAdapter(new NewBreedAdapter(breedIndices,context,bundle));
                    }
                });
            }

            @Override
            public void failure(String msg) {

            }
        });
    }

}
