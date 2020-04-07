package com.lantian.lib_docs.breeddoc.dabiaoyonghu.adapter;

import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lantian.lib_base.entity.module.response.breeds.BreedsList;
import com.lantian.lib_base.utils.Utils;
import com.lantian.lib_docs.R;

import java.util.List;

/**
 * Created by Sherlock·Holmes on 2020-04-04
 */
public class EarCodeAdapter extends BaseQuickAdapter<BreedsList, BaseViewHolder> {

    public EarCodeAdapter(int layoutResId, @Nullable List<BreedsList> data) {
        super(layoutResId, data);
    }


    @Override
    protected void convert(BaseViewHolder helper, BreedsList item) {

            if (item.getBreed().getType().equals("1")){
                item.getBreed().setType("母");
            }else {
                item.getBreed().setType("公");
            }
            helper.setText(R.id.earCode,item.getEartag().getNumber())
                    .setText(R.id.earTitle,item.getBreed().getTitle())
                    .setText(R.id.earAge,item.getAge()+"天")
                    .setText(R.id.earBreed,item.getBreed().getMother()+"个")
                    .setText(R.id.earWeight,item.getWeight()+"kg")
                    .setText(R.id.sexOfBreed,item.getBreed().getType())
                    .addOnClickListener(R.id.earHeader);
            Glide.with(Utils.getContext()).load(item.getImg()).into((ImageView)helper.getView(R.id.earHeader));

    }
}
