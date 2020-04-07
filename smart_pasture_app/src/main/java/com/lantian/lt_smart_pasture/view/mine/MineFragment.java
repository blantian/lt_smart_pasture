package com.lantian.lt_smart_pasture.view.mine;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.lantian.lib_commin_ui.base.ActivityManagerUtil;
import com.lantian.lib_commin_ui.dialog.AlertDialogUtil;
import com.lantian.lt_smart_pasture.R;

/**
 * Created by Sherlock·Holmes on 2020-02-24
 */
public class MineFragment extends Fragment implements View.OnClickListener {

    private ImageView mEarBtnBack;
    private ImageView mUserhead;
    private ImageView mIcHead;
    private TextView mIcHeadName;
    private TextView mWelcomeTo;
    private TextView mPerusername;
    private TextView mBttnFind;
    private TextView mBttnMyMsg;
    private TextView mBttnAboutUs;
    private Button mBttnQuit;
    private String[] buttnStr = {"确定","取消"};

    public static Fragment newInstance() {
        MineFragment fragment = new MineFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_mine, null);
        initView(rootView);
        return rootView;
    }

    private void initView(View rootView) {
        mBttnFind = rootView.findViewById(R.id.bttn_find);
        mBttnMyMsg = rootView.findViewById(R.id.bttn_my_msg);
        mBttnAboutUs = rootView.findViewById(R.id.bttn_about_us);
        mBttnQuit = rootView.findViewById(R.id.bttn_quit);

        mBttnQuit.setOnClickListener(this);
        mBttnAboutUs.setOnClickListener(this);
        mBttnMyMsg.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.bttn_quit){
            AlertDialogUtil.TwoChoiceDialog(getContext(), "退出程序", "您是否退出程序？",
                    buttnStr, new AlertDialogUtil.TwoChoiceHandle() {
                        @Override
                        public void onPositiveButtonHandle() {
                            ActivityManagerUtil.getAppManager().AppExit(getContext());
                        }
                        @Override
                        public void onNegativeButtonHandle() {
                        }
                    });

        }else if (v.getId() == R.id.bttn_find){
            findActivity.instance(getContext(),findActivity.class,null);
        }else if (v.getId() ==R.id.bttn_about_us){
            AboutAsActiviity.instance(getContext(),AboutAsActiviity.class,null);
        }else if (v.getId() == R.id.bttn_my_msg){
            personalActivity.instance(getContext(),personalActivity.class,null);
        }
    }
}
