package com.lantian.lib_lan.subfield.adapter;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lantian.lib_lan.R;
import com.lantian.lib_lan.device.model.LogBean;

import java.util.List;

/**
 * Created by Sherlock·Holmes on 2020/5/19
 */
public class LogAdapter extends BaseQuickAdapter<LogBean, BaseViewHolder> {



    public LogAdapter(int layoutResId, @Nullable List<LogBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, LogBean item) {
        helper.setText(R.id.time,item.mTime).setText(R.id.logtext,item.mLog);
    }
}
