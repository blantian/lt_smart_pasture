package com.lantian.lib_docs.breeddoc.adapter;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.lantian.lib_base.MyApp;
import com.lantian.lib_base.entity.module.response.breeds.BreedIndex;
import com.lantian.lib_base.utils.Utils;
import com.lantian.lib_docs.R;
import com.lantian.lib_docs.breeddoc.BreedAllActivity;
import com.lantian.lib_docs.breeddoc.dabiaoyonghu.DabiaoBreedDocActivity;

import java.util.ArrayList;

/**
 * Created by Sherlock·Holmes on 2020-03-31
 */
public class NewBreedAdapter extends RecyclerView.Adapter<NewBreedAdapter.NewBreedHoder> {

    private ArrayList<BreedIndex> breedIndices;
    private Context context;
    private Bundle mBundle;

    public NewBreedAdapter(ArrayList<BreedIndex> breedIndices, Context context,Bundle bundle) {
        this.breedIndices = breedIndices;
        this.context = context;
        mBundle = bundle;
    }

    @NonNull
    @Override
    public NewBreedHoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_breedlist, null);
        return new NewBreedHoder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewBreedHoder holder, int position) {
        final BreedIndex breedIndex = breedIndices.get(position);
        holder.mBreedPriess.setText(breedIndex.getPrice());
        holder.mBreedNumber.setText(breedIndex.getNumber());
        holder.mBreedsByTime.setText(breedIndex.getAddtime());
        holder.mByName.setText(breedIndex.getTitle());
        holder.mNextPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBundle.putSerializable("BreedIndex",breedIndex);
                if (MyApp.isAdmin.equals("5")){
                    DabiaoBreedDocActivity.instance(Utils.getContext(),DabiaoBreedDocActivity.class,mBundle);
                }else {
                    BreedAllActivity.instance(Utils.getContext(),BreedAllActivity.class,mBundle);
                }

            }
        });
    }

    @Override
    public int getItemCount() {
        return breedIndices.size();
    }


    public class NewBreedHoder extends RecyclerView.ViewHolder {

        private TextView mByName;
        private TextView mBreedsByTime;
        private ImageView mBreedHead;
        private TextView mBreedNumber;
        private TextView mBreedPriess;
        private RelativeLayout mNextPage;

        public NewBreedHoder(@NonNull View itemView) {
            super(itemView);
            mByName = itemView.findViewById(R.id.breed_name);
            mBreedHead = itemView.findViewById(R.id.breed_head);
            mBreedsByTime = itemView.findViewById(R.id.breeds_by_time);
            mBreedNumber = itemView.findViewById(R.id.breed_number);
            mBreedPriess = itemView.findViewById(R.id.breed_priess);
            mNextPage = itemView.findViewById(R.id.next_page);
        }
    }
}
