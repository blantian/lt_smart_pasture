package com.lantian.lib_docs.farmdoc.view.farmerdata.chengyuan.adapter;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lantian.lib_base.entity.module.response.farmer.plan.CaoyuanList;
import com.lantian.lib_docs.R;

import java.util.List;

/**
 * Created by Sherlock·Holmes on 2020/4/22
 */
public class FDCaoyuanAdapter extends BaseQuickAdapter<CaoyuanList, BaseViewHolder> {
    public FDCaoyuanAdapter(int layoutResId, @Nullable List<CaoyuanList> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, CaoyuanList item) {
        helper.setText(R.id.tTab,item.getArea());
        helper.setText(R.id.tInfo,item.getName());
    }
}
