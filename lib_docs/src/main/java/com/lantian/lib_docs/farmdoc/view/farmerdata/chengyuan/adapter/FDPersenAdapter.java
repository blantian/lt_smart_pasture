package com.lantian.lib_docs.farmdoc.view.farmerdata.chengyuan.adapter;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lantian.lib_base.entity.module.response.farmer.farmlist.PersonList;
import com.lantian.lib_docs.R;

import java.util.List;

/**
 * Created by Sherlock·Holmes on 2020/4/22
 */
public class FDPersenAdapter extends BaseQuickAdapter<PersonList, BaseViewHolder> {

    public FDPersenAdapter(int layoutResId, @Nullable List<PersonList> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, PersonList item) {
        helper.setText(R.id.tTab,item.getPer_relations());
        helper.setText(R.id.tInfo,item.getName());
    }
}
