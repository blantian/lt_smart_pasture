package com.lantian.lib_lan.device.view.home.adapter;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lantian.lib_base.entity.items.Devs;
import com.lantian.lib_lan.R;

import java.util.List;

/**
 * Created by Sherlock·Holmes on 2020/5/9
 */
public class DevAdapter extends BaseQuickAdapter<Devs, BaseViewHolder> {

    public DevAdapter(int layoutResId, @Nullable List<Devs> data) {
        super(layoutResId, data);

    }

    @Override
    protected void convert(BaseViewHolder helper, Devs item) {
        helper.setText(R.id.dev_name, item.getDevname()).setImageResource(R.id.dev_icon,
                item.getIcon());
        helper.setText(R.id.dev_ip,item.getIp());
        if (item.getStatus()==0){
            helper.setText(R.id.dev_status,"离线");
        }else if (item.getStatus() == 1){
            helper.setText(R.id.dev_status,"在线");
        }else if (item.getStatus() ==2){
            helper.setText(R.id.dev_status,"断线");
        }
        helper.addOnClickListener(R.id.dev_del);

    }

}
