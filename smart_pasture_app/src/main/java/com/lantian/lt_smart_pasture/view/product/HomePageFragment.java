package com.lantian.lt_smart_pasture.view.product;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
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
import com.lantian.lib_base.entity.module.response.login.LoginResponse;
import com.lantian.lib_base.entity.module.response.product.ProductResponse;
import com.lantian.lib_base.file.sharedpreferences.SharedPreferencesUtils;
import com.lantian.lib_base.thread.ThreadPoolManager;
import com.lantian.lib_base.utils.EventMessage;
import com.lantian.lib_commin_ui.base.BaseFragmen;
import com.lantian.lib_commin_ui.dialog.AlertDialogUtil;
import com.lantian.lib_image_loader.downpic.DownloadPic;
import com.lantian.lib_lan.device.view.DevHomeActivity;
import com.lantian.lib_network.dialog.DialogUtils;
import com.lantian.lib_network.networkstatus.NetWorkStatus;
import com.lantian.lib_network.retrofit2.MyCallBack;
import com.lantian.lib_network.retrofit2.RetrofitHelper;
import com.lantian.lib_network.utils.EventNetMessage;
import com.lantian.lt_smart_pasture.R;
import com.lantian.lt_smart_pasture.view.product.adapter.RecyclerViewAdapter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


/**
 * Created by Sherlock·Holmes on 2020-02-24
 */
public class HomePageFragment extends BaseFragmen{

    @BindView(R.id.username)
    TextView username;
    @BindView(R.id.liusername)
    TextView liusername;
    @BindView(R.id.home_list)
    RecyclerView homeList;

    private Context mContext;
    private static final String MARKING = "5";
    private final static String TAG = "HomePageFragment:";
    private List<ProductResponse> mProductResponses = new ArrayList<>();
    private int[] mIcon = new int[]{R.drawable.ic_action_farmdoc};
    private String[] mTitel = new String[]{"养殖档案"};
    private SharedPreferencesUtils sharedPreferencesUtils;
    private ProductResponseDao productResponseDao;
    private LoginResponseDao loginResponseDao;
    private String DA_BIAO_USER = "5";
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
        EventBus.getDefault().register(this);
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
        Object isAdmin = sharedPreferencesUtils.getParam("isAdmin","");
        Object userid = sharedPreferencesUtils.getParam("user_id","");

        if (!NetWorkStatus.isNetworkAvailable(getContext())) {

              LoginResponse loginResponse = loginResponseDao.queryBuilder().where(LoginResponseDao.Properties.User_id.eq(String.valueOf(userid))).unique();
              List<ProductResponse> productResponses = productResponseDao.queryBuilder().where(ProductResponseDao.Properties.User_id.eq(String.valueOf(userid))).list();
              if (loginResponse.getUserdata().getIs_admin().equals(DA_BIAO_USER)){
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
              }else {
                  getActivity().runOnUiThread(new Runnable() {
                      @Override
                      public void run() {
                          homeList.setAdapter(new RecyclerViewAdapter(productResponses,getContext()));
                      }
                  });
              }
        } else {
            if (String.valueOf(isAdmin).equals(MARKING)) {
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
        EventBus.getDefault().unregister(this);
    }


    /**
     * 回调处理
     * @param eventMessage
     */
    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void getMessage(EventMessage eventMessage){
        if (eventMessage.getMsg()==1&&eventMessage.getMessage().equals("kong")){
            AlertDialogUtil.warningDialog(getActivity(),"温馨提示","您未加载数据呢！");
        }else if (eventMessage.getMsg() ==1&&eventMessage.getMessage().equals("none")){
            AlertDialogUtil.warningDialog(getActivity(),"温馨提示","您先线上登录，加载数据哦！");
        }else if (eventMessage.getMsg()==1&&eventMessage.getMessage().equals("nobreed")){
            AlertDialogUtil.warningDialog(getActivity(),"温馨提示","您先线上登录，加载数据哦！");
        }
    }

    /**
     * 判断网络状态
     * @param netMessage
     */
    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void getNetType(EventNetMessage netMessage){
        if (netMessage.getMsg()==1){
            switch (netMessage.getNetworkType()){
                case NETWORK_LAN:
                    Log.e("LAN","局域网");
                    DialogUtils dialogUtils = new DialogUtils();
                    dialogUtils.showProgress(getContext(),"正在切换局域网模式...");
                    ThreadPoolManager.getInstance().execute(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                DevHomeActivity.instance(getContext(),DevHomeActivity.class,null);
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                    break;
                default:
            }
        }
    }


}
