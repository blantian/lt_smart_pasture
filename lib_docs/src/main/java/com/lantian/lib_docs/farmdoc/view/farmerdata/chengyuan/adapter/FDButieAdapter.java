package com.lantian.lib_docs.farmdoc.view.farmerdata.chengyuan.adapter;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lantian.lib_base.entity.module.response.farmer.butie.ButieList;
import com.lantian.lib_docs.R;

import java.util.List;

/**
 * Created by Sherlock·Holmes on 2020/4/22
 */
public class FDButieAdapter extends BaseQuickAdapter<ButieList, BaseViewHolder> {
    public FDButieAdapter(int layoutResId, @Nullable List<ButieList> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ButieList item) {
        helper.setText(R.id.tTab,item.getPrice());
        helper.setText(R.id.tInfo,item.getTitle());
    }
}
