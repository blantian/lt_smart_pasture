package com.lantian.lib_docs.breeddoc.adapter;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.lantian.lib_base.file.sharedpreferences.SharedPreferencesUtils;
import com.lantian.lib_base.entity.module.response.breeds.BreedsOfUser;
import com.lantian.lib_base.utils.BaseUtils;
import com.lantian.lib_docs.R;
import com.lantian.lib_docs.breeddoc.NewBreedActivity;
import com.lantian.lib_docs.breeddoc.dabiaoyonghu.SingleBreedActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sherlock·Holmes on 2020-03-31
 */
public class HaveBreedAdapter extends RecyclerView.Adapter<HaveBreedAdapter.HaveBreedHoder> {

    private static final String DAO_BIAO_USER = "5";
    private Context context;
    /**分用户所用**/
    private String userid;
    private List<BreedsOfUser.DataBean> breeds = new ArrayList<>();
    private SharedPreferencesUtils sharedPreferencesUtils;

    public HaveBreedAdapter(List<BreedsOfUser.DataBean> breeds, Context context, String userid){
        this.breeds  = breeds;
        this.context =  context;
        this.userid = userid;
    }

    @NonNull
    @Override
    public HaveBreedHoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_breeds, parent, false);
        return new HaveBreedHoder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HaveBreedHoder holder, int position) {
        sharedPreferencesUtils = new SharedPreferencesUtils(BaseUtils.getContext());
        final Object isAdmin = sharedPreferencesUtils.getParam("isAdmin","");
        final BreedsOfUser.DataBean breed = breeds.get(position);
        holder.Showbreedsname.setText(breed.getName());
        holder.showbreedsnumber.setText(breed.getCount());
        holder.breeds.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("breed_id",breed.getId());
                bundle.putString("breed_name",breed.getName());
                bundle.putString("user_id",userid);
                if (String.valueOf(isAdmin).equals(DAO_BIAO_USER)){
                    /**打标用户牲畜列表页面**/
                    SingleBreedActivity.instance(BaseUtils.getContext(), SingleBreedActivity.class,bundle);
                }else {
                    /**普通用户牲畜列表页面**/
                    NewBreedActivity.instance(BaseUtils.getContext(),NewBreedActivity.class,bundle);
                }


            }
        });
    }

    @Override
    public int getItemCount() {
        return breeds.size();
    }

    public class HaveBreedHoder extends RecyclerView.ViewHolder {

        private TextView Showbreedsname;
        private TextView showbreedsnumber;
        private LinearLayout breeds;

        public HaveBreedHoder(@NonNull View itemView) {
            super(itemView);
            Showbreedsname = itemView.findViewById(R.id.showbreedsname);
            showbreedsnumber = itemView.findViewById(R.id.showbreedsnumber);
            breeds = itemView.findViewById(R.id.breeds);

        }
    }
}
