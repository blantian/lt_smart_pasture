package com.lantian.lib_docs.breeddoc.dabiaoyonghu;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.internal.LinkedTreeMap;
import com.lantian.lib_base.entity.module.response.breeds.BreedIndex;
import com.lantian.lib_base.entity.module.response.breeds.BreedsList;
import com.lantian.lib_base.type.NullStringEmptyTypeAdapterFactory;
import com.lantian.lib_base.utils.Utils;
import com.lantian.lib_commin_ui.base.BaseActivity;
import com.lantian.lib_docs.R;
import com.lantian.lib_docs.breeddoc.GetBigPicActivity;
import com.lantian.lib_docs.breeddoc.dabiaoyonghu.adapter.EarCodeAdapter;
import com.lantian.lib_network.retrofit2.MyCallBack;
import com.lantian.lib_network.retrofit2.RetrofitHelper;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Sherlock·Holmes on 2020-04-01
 */
public class EarCodeActivity extends BaseActivity implements View.OnClickListener {

    private ImageView mEarTagBtnBack;
    private TextView mDabiaoBreedName;
    private TextView mDabiaoAddBreed;
    private TextView mTagNum;
    private EditText mEarTagSearch;
    private TextView mBtnSearch;
    private Button mBttnReadEarCode;
    private Button mBttnClearEarCode;
    private TextView mBtnSex;
    private TextView mBtnBreeding;
    private TextView mBtnWight;
    private RecyclerView mEarBreedsList;
    private GridLayoutManager gridLayoutManager;
    private Bundle bundle;
    private String breedName;
    private LinearLayout mAddBreedEar;
    private EarCodeAdapter earCodeAdapter;
    private BreedsList breedIndex;
    private ArrayList<BreedsList> breedIndexLists;
    private String userid;
    private String id;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_dabiao_add_ear_tag;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        initView();
    }

    private void initView() {
        bundle = getIntent().getExtras();
        mEarBreedsList = findViewById(R.id.ear_breeds_list);
        mEarTagBtnBack = findViewById(R.id.ear_tag_btn_back);
        mDabiaoBreedName = findViewById(R.id.ear_breed_name);
        mTagNum = findViewById(R.id.tag_num);
        mEarTagSearch = findViewById(R.id.ear_tag_search);
        mBtnSearch = findViewById(R.id.btn_search);
        mBttnReadEarCode = findViewById(R.id.bttn_read_ear_code);
        mBttnClearEarCode = findViewById(R.id.bttn_clear_ear_code);
        mBtnSex = findViewById(R.id.btn_sex);
        mBtnBreeding = findViewById(R.id.btn_breeding);
        mBtnWight = findViewById(R.id.btn_wight);
        mAddBreedEar = findViewById(R.id.add_breed_ear);
        mAddBreedEar.setOnClickListener(this);
        mBtnSearch.setOnClickListener(this);
        initData();
        getAPIdata();
    }

    private void initAdapter(ArrayList<BreedsList> breedIndexLists) {
        gridLayoutManager = new GridLayoutManager(this,1);
        mEarBreedsList.setLayoutManager(gridLayoutManager);
        earCodeAdapter = new EarCodeAdapter(R.layout.breed_list_item,breedIndexLists);
        mEarBreedsList.setAdapter(earCodeAdapter);
        earCodeAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                String path = earCodeAdapter.getData().get(position).getImg();
                bundle.putString("img",path);
                GetBigPicActivity.instance(EarCodeActivity.this,GetBigPicActivity.class,bundle);
            }
        });
        earCodeAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                String path = earCodeAdapter.getData().get(position).getImg();
                bundle.putString("img",path);
                bundle.putString("earCode",earCodeAdapter.getData().get(position).getEartag().getNumber());
                bundle.putString("weight",earCodeAdapter.getData().get(position).getWeight());
                bundle.putString("sex",earCodeAdapter.getData().get(position).getType());
                bundle.putString("userid",earCodeAdapter.getData().get(position).getUser_id());
                addEarActivity.instance(EarCodeActivity.this,addEarActivity.class,bundle);
            }
        });
    }

    private void getAPIdata() {
        flatBreedIndex();
        RetrofitHelper.getApiService().getAllBreedIndex(breedIndex.getBreed_id(),breedIndex.getUser_id()).enqueue(new MyCallBack<LinkedTreeMap>() {
            @Override
            public void success(LinkedTreeMap linkedTreeMap) {
                breedIndexLists = new ArrayList<>();
                Gson gson = new GsonBuilder().enableComplexMapKeySerialization()
                        .registerTypeAdapterFactory(new NullStringEmptyTypeAdapterFactory()).create();
                LinkedTreeMap<String, LinkedTreeMap> map = linkedTreeMap;
                map.remove("typet");
                for (Object o:map.keySet()){
                    String toJson = gson.toJson(map.get(o));
                    BreedsList breedIndexList = gson.fromJson(toJson, BreedsList.class);
                    if (breedIndexList.getEartag() != null){
                        breedIndexLists.add(breedIndexList);
                    }
                }
                initAdapter(breedIndexLists);

            }
            @Override
            public void failure(String msg) {

            }
        });
    }

    private void initData() {
        Serializable serializable = bundle.getSerializable("BreedIndex");
        breedName = bundle.getString("breed_name");
        mDabiaoBreedName.setText(breedName);
        if (serializable != null) {
            BreedIndex breedIndex = (BreedIndex) serializable;
            mTagNum.setText(breedIndex.getNumber());
            userid = breedIndex.getUser_id();
            id = breedIndex.getId();
        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId()==R.id.add_breed_ear){
            addEarActivity.instance(Utils.getContext(),addEarActivity.class,bundle);
        }if (v.getId() == R.id.btn_search){
           if (earCodeAdapter.getData().size()!=0){
               for (int i = 0;i<earCodeAdapter.getData().size();i++){
                  String earTag =  earCodeAdapter.getData().get(i).getEartag().getNumber();
                   if (mEarTagSearch.getText().toString()
                           .equals(earTag)){
                       BreedsList breedsList = breedIndexLists.get(i);
                       System.out.print(breedsList);
                       breedIndexLists.clear();
                       breedIndexLists.add(breedsList);
                       initAdapter(breedIndexLists);
                   }else {
                       showToast("请输入准确的耳标号吗！");
                   }
               }
           }
        }if (v.getId() == R.id.ear_tag_search){
            mEarTagSearch.setFocusable(true);
        }
    }

    private void flatBreedIndex(){
        breedIndex = new BreedsList();
        breedIndex.setUser_id(userid);
        breedIndex.setBreed_id(id);
    }
}
