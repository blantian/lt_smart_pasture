package com.lantian.lib_docs.breeddoc.dabiaoyonghu.adapter;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lantian.lib_base.entity.module.response.breeds.EarTag;
import com.lantian.lib_docs.R;

import java.util.List;

/**
 * Created by Sherlock·Holmes on 2020/4/6
 */
public class EarTagAdapter extends BaseQuickAdapter<EarTag, BaseViewHolder> {

    public EarTagAdapter(int layoutResId, @Nullable List<EarTag> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, EarTag item) {
        helper.setText(R.id.eraTag,item.getNumber())
                .addOnClickListener(R.id.checkEarCode);
    }
}
