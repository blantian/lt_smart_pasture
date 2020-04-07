package com.lantian.lib_docs.breeddoc;

import android.content.Context;
import android.os.Bundle;
import android.widget.Button;
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
public class NewBreedActivity extends BaseActivity {


    private Context context;
    private ImageView mBnBtnBack;
    private TextView mBreedName;
    private TextView mNremakeDate;
    private RecyclerView mNRecyclerBreedsList;
    private Button mNewBreedDoc;
    private Bundle bundle;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_normol_breed;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        initView();
    }

    private void initView() {
        bundle = getIntent().getExtras();
        mBnBtnBack = findViewById(R.id.bn_btn_back);
        mBreedName = findViewById(R.id.n_breed_name);
        mNewBreedDoc = findViewById(R.id.new_breed_doc);
        mNremakeDate = findViewById(R.id.nremake_date);
        mNRecyclerBreedsList = findViewById(R.id.n_recycler_breeds_list);
        mNRecyclerBreedsList.setLayoutManager(new GridLayoutManager(this,1));
        initData();
    }

    private void initData() {
        String breedid =bundle.getString("breed_id");
        String breedname = bundle.getString("breed_name");
        mBreedName.setText(breedname);
        getBreedData(breedid);
    }

    private void getBreedData(String breedid) {
        RetrofitHelper.getApiService().getBreedIndex(MyApp.Userid,breedid,"1").enqueue(new MyCallBack<ArrayList<BreedIndex>>() {
            @Override
            public void success(final ArrayList<BreedIndex> breedIndices) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mNRecyclerBreedsList.setAdapter(new NewBreedAdapter(breedIndices,context,bundle));
                    }
                });
            }

            @Override
            public void failure(String msg) {

            }
        });
    }

}
