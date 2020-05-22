package com.lantian.lt_smart_pasture.view.product.adapter;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.lantian.lib_base.MyApp;
import com.lantian.lib_base.database.greendao.CheckUserDao;
import com.lantian.lib_base.database.greendao.CountBreedTureDao;
import com.lantian.lib_base.file.sharedpreferences.SharedPreferencesUtils;
import com.lantian.lib_base.database.syncdata.SyncDataStatus;
import com.lantian.lib_base.entity.module.response.breeds.CountBreedTure;
import com.lantian.lib_base.entity.module.response.farmer.farmhuku.CheckUser;
import com.lantian.lib_base.entity.module.response.product.ProductResponse;
import com.lantian.lib_base.utils.EventMessage;
import com.lantian.lib_base.utils.BaseUtils;
import com.lantian.lib_docs.breeddoc.HaveBreedActivity;
import com.lantian.lib_docs.breeddoc.NoBreedActivity;
import com.lantian.lib_docs.breeddoc.dabiaoyonghu.FarmListActivity;
import com.lantian.lib_docs.farmdoc.view.havefarm.HaveFarmActivity;
import com.lantian.lib_docs.farmdoc.view.home.FarmHomeActivity;
import com.lantian.lib_image_loader.loadpic.ImageLoaderManager;
import com.lantian.lib_network.networkstatus.NetWorkStatus;
import com.lantian.lib_network.retrofit2.MyCallBack;
import com.lantian.lib_network.retrofit2.RetrofitHelper;
import com.lantian.lib_network.utils.NetworkUtils;
import com.lantian.lt_smart_pasture.R;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

/**
 * Created by Sherlock·Holmes on 2020-03-03
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.RecyclerViewHolder> {
    private final int[] localicons = new int[]{R.drawable.ic_action_watching,
            R.drawable.ic_action_statellite,
            R.drawable.ic_action_farmerdoc,
            R.drawable.ic_action_plantdoc,
            R.drawable.ic_action_medicinedoc,
            R.drawable.ic_action_farmdoc};
    private final String[] localtitles = new String[]{"监控中心",
            "卫星放牧", "牧户档案", "  种植档案", "医疗档案", "养殖档案"};
    private List<ProductResponse> homeResults ;
    private SharedPreferencesUtils sharedPreferencesUtils;
    private CountBreedTureDao countBreedTureDao;
    private CheckUserDao checkUserDao;
    private Context context;
    private int size = 0;

    /**
     * 构造函数
     * @param productResponses
     * @param context
     */
    public RecyclerViewAdapter(List<ProductResponse> productResponses, Context context){
        this.homeResults = productResponses;
        this.context = context;
        if (productResponses.size() ==0){
            EventBus.getDefault().postSticky(new EventMessage(1,"none"));
        }else {
            /** 把本地图片加载进去**/
            if ((!productResponses.get(0).getProduct_name().equals("养殖档案"))) {
                for (int i = 0; i < localicons.length; i++) {
                    ProductResponse productResponse = new ProductResponse();
                    productResponse.setProduct_icon(localicons[i]);
                    productResponse.setProduct_name(localtitles[i]);
                    /**设置编号**/
                    if (i == 0) {
                        productResponse.setProduct_id(0 + "");
                    }
                    if (i == 1) {
                        productResponse.setProduct_id(1 + "");
                    }
                    if (i == 2) {
                        productResponse.setProduct_id(2 + "");
                    }
                    if (i == 3) {
                        productResponse.setProduct_id(3 + "");
                    }
                    if (i == 4) {
                        productResponse.setProduct_id(4 + "");
                    }
                    if (i == 5) {
                        productResponse.setProduct_id(5 + "");
                    }
                    productResponses.add(productResponse);
                }
            }else {
                return;
            }
        }

    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_item, parent, false);
        return new RecyclerViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
        sharedPreferencesUtils = new SharedPreferencesUtils(BaseUtils.getContext());
        Object isAdmin = sharedPreferencesUtils.getParam("isAdmin","");
        final ProductResponse item = homeResults.get(position);
            if (position >= homeResults.size() - localicons.length) {
                holder.icons.setImageResource(item.getProduct_icon());
            } else {
                ImageLoaderManager.getInstance().displayImageForView(holder.icons, item.getProduct_pic());
            }
            holder.name.setText(item.getProduct_name());
            holder.icons.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    for (int i = 0; i < homeResults.size(); i++) {
                        if (item.getProduct_id().equals(homeResults.get(i).getProduct_id())) {
                            switch (item.getProduct_name()) {
                                case "养殖档案":
                                    if (String.valueOf(isAdmin).equals("5")){
                                        FarmListActivity.instance(context, FarmListActivity.class, null);
                                    }else {
                                        CheckBreedDoc();
                                    }
                                    break;
                                case "牧户档案":
                                   CheckUser();
                                default:
                            }
                        }
                    }
                }
            });
    }

    @Override
    public int getItemCount() {
        return homeResults.size();
    }
    class RecyclerViewHolder extends RecyclerView.ViewHolder{
        private ImageView icons;
        private TextView name;
        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            icons = itemView.findViewById(R.id.item_icon);
            name = itemView.findViewById(R.id.item_name);
        }
    }

    /**
     * 检查是否有牧户
     */
    public void CheckUser(){
        Object userid = sharedPreferencesUtils.getParam("user_id","");
        checkUserDao = ((MyApp) BaseUtils.getContext()).getDaoSession().getCheckUserDao();
        CheckUser checkUser = new CheckUser();
        checkUser.setUser_id(MyApp.Userid);
        if (NetworkUtils.isConnected()){
            RetrofitHelper.getApiService().checkUser(MyApp.Userid).enqueue(new MyCallBack<String>() {
                @Override
                public void success(String s) {
                    if (s == null){
                        checkUser.setHave("0");
                        checkUserDao.insertOrReplace(checkUser);
                        Bundle bundle = new Bundle();
                        bundle.putString("NoFarm","0");
                        FarmHomeActivity.instance(context, FarmHomeActivity.class, bundle);
                    }else {
                        checkUser.setHave(s);
                        checkUserDao.insertOrReplace(checkUser);
                        HaveFarmActivity.instance(context,HaveFarmActivity.class,null);
                    }
                }
                @Override
                public void failure(String msg) {
                }
            });
        }else {
            CheckUser userdata = checkUserDao.queryBuilder().where(CheckUserDao.Properties.User_id.eq(String.valueOf(userid))).unique();
            if (userdata!=null){
                if (userdata.getHave().equals("0")){
                    FarmHomeActivity.instance(context, FarmHomeActivity.class, null);
                }else {
                    HaveFarmActivity.instance(context,HaveFarmActivity.class,null);
                }
            }else {
                EventBus.getDefault().postSticky(new EventMessage(1,"kong"));
            }

        }
    }

    /**
     * 检查用户是否有养殖牲畜
     * 来决定跳到哪个页面（有养殖信息的页面/新建养殖信息的页面）
     */
    private void CheckBreedDoc() {
        countBreedTureDao = ((MyApp) BaseUtils.getContext()).getDaoSession().getCountBreedTureDao();
        if (NetWorkStatus.isNetworkAvailable(BaseUtils.getContext())){
            RetrofitHelper.getApiService().getCountBreed(MyApp.Userid).enqueue(new MyCallBack<CountBreedTure>() {
                @Override
                public void success(CountBreedTure countBreedTure) {
                    if (countBreedTure !=null){
                        CountBreedTure countBreed = new CountBreedTure();
                        countBreed.setUserid(MyApp.Userid);
                        countBreed.setStatus(SyncDataStatus.ADD.ordinal());
                        countBreedTureDao.insertOrReplace(countBreed);
                        if (countBreedTure.getBreedcont().equals("0")){
                            NoBreedActivity.instance(context,NoBreedActivity.class,null);
                        }else {
                            getFarmInfo();
                            HaveBreedActivity.instance(context,HaveBreedActivity.class,null);
                        }
                    }
                }

                @Override
                public void failure(String msg) {

                }
            });
        }else {
             Object userid = sharedPreferencesUtils.getParam("user_id","");
             CountBreedTure countBreedTure = countBreedTureDao.queryBuilder().where(CountBreedTureDao.Properties.Userid.eq(String.valueOf(userid))).unique();
             if (countBreedTure != null){
                 if (countBreedTure.getBreedcont().equals("0")){
                     NoBreedActivity.instance(context,NoBreedActivity.class,null);
                 }else {
                     HaveBreedActivity.instance(context,HaveBreedActivity.class,null);
                 }
             }else {
                 EventBus.getDefault().postSticky(new EventMessage(1,"nobreed"));
             }
        }
    }

    /**
     * 获取用户信息
     */
    private void getFarmInfo() {
    }
}
