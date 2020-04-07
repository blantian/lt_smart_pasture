package com.lantian.lt_smart_pasture.view.product;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.lantian.lib_base.MyApp;
import com.lantian.lib_base.database.greendao.LoginResponseDao;
import com.lantian.lib_base.database.greendao.ProductResponseDao;
import com.lantian.lib_base.database.sharedpreferences.SharedPreferencesUtils;
import com.lantian.lib_base.entity.module.response.product.ProductResponse;
import com.lantian.lib_commin_ui.base.BaseFragmen;
import com.lantian.lib_image_loader.downpic.DownloadPic;
import com.lantian.lib_network.networkstatus.NetWorkStatus;
import com.lantian.lib_network.retrofit2.MyCallBack;
import com.lantian.lib_network.retrofit2.RetrofitHelper;
import com.lantian.lt_smart_pasture.R;
import com.lantian.lt_smart_pasture.view.product.adapter.RecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


/**
 * Created by Sherlock·Holmes on 2020-02-24
 */
public class HomePageFragment extends BaseFragmen {

    @BindView(R.id.username)
    TextView username;
    @BindView(R.id.liusername)
    TextView liusername;
    @BindView(R.id.home_list)
    RecyclerView homeList;

    private Context mContext;
    private String MARKING = "5";
    private final static String TAG = "HomePageFragment:";
    private List<ProductResponse> mProductResponses = new ArrayList<>();
    private int[] mIcon = new int[]{R.drawable.ic_action_farmdoc};
    private String[] mTitel = new String[]{"养殖档案"};
    private SharedPreferencesUtils sharedPreferencesUtils;
    private ProductResponseDao productResponseDao;
    private LoginResponseDao loginResponseDao;
    /**
     * 单例模式初始化fragment
     * @return
     */
    public static Fragment newInstance() {
        HomePageFragment fragment = new HomePageFragment();
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_homepage, null);
        homeList = rootView.findViewById(R.id.home_list);
        homeList.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        return rootView;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        productResponseDao =((MyApp)getActivity().getApplication()).getDaoSession().getProductResponseDao();
        loginResponseDao = ((MyApp)getActivity().getApplication()).getDaoSession().getLoginResponseDao();
        sharedPreferencesUtils = new SharedPreferencesUtils(getContext());
        initData();
    }

    /**
     * 请求数据并传入adpter更新UI
     */
    public void initData() {

        if (!NetWorkStatus.isNetworkAvailable(getContext())) {

            /**注释代码可以在后期使用**/

              Object userid = sharedPreferencesUtils.getParam("user_id","");
              List<ProductResponse> productResponses = productResponseDao.queryBuilder().where(ProductResponseDao.Properties.User_id.eq(userid)).list();
              getActivity().runOnUiThread(new Runnable() {
                  @Override
                  public void run() {
                      homeList.setAdapter(new RecyclerViewAdapter(productResponses,getContext()));
                  }
              });


        } else {
            if (MyApp.isAdmin.equals(MARKING)) {
                ProductResponse maProductResponse = new ProductResponse();
                maProductResponse.setProduct_name(mTitel[0]);
                maProductResponse.setProduct_icon(mIcon[0]);
                maProductResponse.setProduct_id(0 + "");
                mProductResponses.add(maProductResponse);
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        homeList.setAdapter(new RecyclerViewAdapter(mProductResponses, getContext()));
                    }
                });
            } else {
                RetrofitHelper.getApiService().getHome(MyApp.Userid)
                        .enqueue(new MyCallBack<ArrayList<ProductResponse>>() {
                            @Override
                            public void success(ArrayList<ProductResponse> productResponses) {
                                //todo 数据保存
                                DownloadPic.getInstance().DataProcess(productResponses);
                                getActivity().runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        homeList.setAdapter(new RecyclerViewAdapter(productResponses, getContext()));
                                    }
                                });
                            }

                            @Override
                            public void failure(String msg) {
                                showToast(msg);
                            }
                        });
            }
        }
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        //取消注册
    }



}
