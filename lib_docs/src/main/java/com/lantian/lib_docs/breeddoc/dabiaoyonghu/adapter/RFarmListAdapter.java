package com.lantian.lib_docs.breeddoc.dabiaoyonghu.adapter;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lantian.lib_base.entity.module.response.farmer.farmlist.FarmListResponse;
import com.lantian.lib_base.utils.BaseUtils;
import com.lantian.lib_docs.R;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * Created by Sherlock·Holmes on 2020-04-04
 */
public class RFarmListAdapter extends BaseQuickAdapter<FarmListResponse, BaseViewHolder> {

    public RFarmListAdapter(int layoutResId, @Nullable List<FarmListResponse> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(@NotNull BaseViewHolder baseViewHolder, FarmListResponse farmListResponse) {
        baseViewHolder.setText(R.id.farm_list_names,farmListResponse.getUsername())
                .setText(R.id.farm_list_tells,farmListResponse.getPhone());
                Glide.with(BaseUtils.getContext()).load(farmListResponse.getAvatar()).into((ImageView)baseViewHolder.getView(R.id.farm_List_header_icon));
    }


}
