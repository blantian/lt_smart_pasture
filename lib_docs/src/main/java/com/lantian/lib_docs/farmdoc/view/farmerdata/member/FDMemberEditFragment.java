package com.lantian.lib_docs.farmdoc.view.farmerdata.member;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.lantian.lib_commin_ui.base.BaseFragmen;
import com.lantian.lib_docs.R;

/**
 * Created by Sherlock·Holmes on 2020-04-03
 */
public class FDMemberEditFragment extends BaseFragmen {

    private ImageView mPic;
    private EditText mEtCall;
    private EditText mEtName;
    private EditText mEtRelations;
    private EditText mEtPhone;
    private Spinner mSLabour;
    private Button mBtnSave;
    private Button mBtnDel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fdmember_edit, null);
        initView(view);
        return view;
    }

    private void initView(View view) {
        mPic = view.findViewById(R.id.pic);
        mEtCall = view.findViewById(R.id.et_call);
        mEtName = view.findViewById(R.id.et_name);
        mEtRelations = view.findViewById(R.id.et_relations);
        mBtnSave = view.findViewById(R.id.btn_save);
        mBtnDel = view.findViewById(R.id.btn_del);
        mEtPhone = view.findViewById(R.id.et_phone);
    }
}
