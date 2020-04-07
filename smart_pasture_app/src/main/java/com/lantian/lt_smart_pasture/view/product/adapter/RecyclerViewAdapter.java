package com.lantian.lt_smart_pasture.view.product.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.lantian.lib_base.MyApp;
import com.lantian.lib_base.entity.module.response.breeds.CountBreedTure;
import com.lantian.lib_base.entity.module.response.product.ProductResponse;
import com.lantian.lib_docs.breeddoc.dabiaoyonghu.FarmListActivity;
import com.lantian.lib_docs.breeddoc.HaveBreedActivity;
import com.lantian.lib_docs.breeddoc.NoBreedActivity;
import com.lantian.lib_docs.farmdoc.view.havefarm.HaveFarmActivity;
import com.lantian.lib_docs.farmdoc.view.home.FarmHomeActivity;
import com.lantian.lib_image_loader.loadpic.ImageLoaderManager;
import com.lantian.lib_network.retrofit2.MyCallBack;
import com.lantian.lib_network.retrofit2.RetrofitHelper;
import com.lantian.lt_smart_pasture.R;

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

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_item, parent, false);
        return new RecyclerViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
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
                                    if (MyApp.isAdmin.equals("5")){
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

    public void CheckUser(){
        RetrofitHelper.getApiService().checkUser(MyApp.Userid).enqueue(new MyCallBack<String>() {
            @Override
            public void success(String s) {
                if (s == null){
                    FarmHomeActivity.instance(context, FarmHomeActivity.class, null);
                }else {
                    HaveFarmActivity.instance(context,HaveFarmActivity.class,null);
                }
             }
            @Override
            public void failure(String msg) {
            }
        });
    }

    private void CheckBreedDoc() {
        RetrofitHelper.getApiService().getCountBreed(MyApp.Userid).enqueue(new MyCallBack<CountBreedTure>() {
            @Override
            public void success(CountBreedTure countBreedTure) {
                if (countBreedTure.getBreedcont().equals("0")){
                    NoBreedActivity.instance(context,NoBreedActivity.class,null);
                }else {
                    HaveBreedActivity.instance(context,HaveBreedActivity.class,null);
                }
            }

            @Override
            public void failure(String msg) {

            }
        });
    }
}
