package com.lantian.lib_docs.farmdoc.view.farmerdata.chengyuan;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.lantian.lib_commin_ui.base.BaseFragmen;
import com.lantian.lib_docs.R;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

/**
 * Created by Sherlock·Holmes on 2020-04-03
 */
public class fragmentItem extends BaseFragmen {

    private TextView mTitle;
    private TextView mBtnAdd;
    private RecyclerView mFdmemberList;
    private SmartRefreshLayout mRefreshLayout;

    private static final String ARG_PARAM1 = "userid";
    private static final String ARG_PARAM3 = "param3";


    public static fragmentItem newInstance(int layoutType, String userid) {
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, userid);
        args.putInt(ARG_PARAM3, layoutType);
        fragmentItem fragment = new fragmentItem();
        fragment.setArguments(args);
        return fragment;
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fd_item, null);
        initView(view);
        return view;
    }

    private void initView(View view) {
        mTitle = view.findViewById(R.id.item_title);
        mBtnAdd = view.findViewById(R.id.btn_add);
        mFdmemberList = view.findViewById(R.id.fdmember_list);
        mRefreshLayout = view.findViewById(R.id.refreshLayout);
    }

}
