package com.lantian.lib_docs.farmdoc.view.farmerdata;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.lantian.lib_commin_ui.dialog.SelectDialog;
import com.lantian.lib_commin_ui.base.BaseFragmen;
import com.lantian.lib_docs.R;
import com.lantian.lib_network.networkstatus.NetWorkStatus;
import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.bean.ImageItem;
import com.lzy.imagepicker.ui.ImageGridActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sherlock·Holmes on 2020-03-27
 */
public class CompleteMsgFragment extends BaseFragmen implements View.OnClickListener {


    private final int IMAGE_PICKER_1 = 2100;
    private final int IMAGE_PICKER_2 = 2200;

    private String img2 = "";
    private String img1 = "";

    private ImageView mIdcard1;
    private ImageView mIdcard2;
    private TextView mIdtext;
    private EditText mEtIdCard;
    private EditText mEtIdName;
    private Spinner mSSex;
    private EditText mEtIdBirth;
    private EditText mEtIdAge;
    private Spinner mSIdProvinces;
    private Spinner mSIdCity;
    private Spinner mSIdSeat;
    private EditText mEtIdDetails;
    private Button mBtnSave;

    public static Fragment newInstance() {
        CompleteMsgFragment fragment = new CompleteMsgFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.complete_farmmsg_fragment, null);
        initView(view);
        return view;
    }

    private void initView(View view) {
        mIdcard1 = view.findViewById(R.id.idcard1);
        mIdcard2 = view.findViewById(R.id.idcard2);
        mEtIdCard = view.findViewById(R.id.et_idCard);
        mEtIdName = view.findViewById(R.id.et_idName);
        mSSex = view.findViewById(R.id.s_sex);
        mEtIdBirth =  view.findViewById(R.id.et_idBirth);
        mEtIdAge = view.findViewById(R.id.et_idAge);
        mSIdProvinces = view.findViewById(R.id.s_idProvinces);
        mSIdCity = view.findViewById(R.id.s_idCity);
        mSIdSeat = view.findViewById(R.id.s_idSeat);
        mEtIdDetails = view.findViewById(R.id.et_idDetails);
        mBtnSave = view.findViewById(R.id.btn_save);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mIdcard1.setOnClickListener(this);
        mIdcard2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        final Intent intent = new Intent(getContext(), ImageGridActivity.class);
        if (v.getId() == R.id.idcard1) {
            List<String> picname = new ArrayList<>();
            picname.add("拍照");
            picname.add("相册");
            showDialog(new SelectDialog.SelectDialogListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    switch (position){
                        case 0:
                            intent.putExtra(ImageGridActivity.EXTRAS_TAKE_PICKERS, true); // 是否是直接打开相机
                            startActivityForResult(intent, IMAGE_PICKER_1);
                            break;
                        case 1:
                            Intent intent1 = new Intent(getContext(), ImageGridActivity.class);
                            startActivityForResult(intent1, IMAGE_PICKER_1);
                        default:
                            break;
                    }
                }
            },picname);
        }else if (v.getId()==R.id.idcard2){
            List<String> picname = new ArrayList<>();
            picname.add("拍照");
            picname.add("相册");
            showDialog(new SelectDialog.SelectDialogListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    switch (position){
                        case 0:
                            intent.putExtra(ImageGridActivity.EXTRAS_TAKE_PICKERS, true); // 是否是直接打开相机
                            startActivityForResult(intent, IMAGE_PICKER_2);
                            break;
                        case 1:
                            Intent intent1 = new Intent(getContext(), ImageGridActivity.class);
                            startActivityForResult(intent1, IMAGE_PICKER_2);
                        default:
                            break;
                    }
                }
            },picname);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == ImagePicker.RESULT_CODE_ITEMS){
            if (data !=null){
                ArrayList<ImageItem> imageItems = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_RESULT_ITEMS);
                String img_ = imageItems.get(0).path;
                switch (requestCode) {
                    case IMAGE_PICKER_1:
                        img1 = img_;
                        Glide.with(CompleteMsgFragment.this).load(img1).into(mIdcard1);
                        break;
                    case IMAGE_PICKER_2:
                        img2 = img_;
                        Glide.with(CompleteMsgFragment.this).load(img2).into(mIdcard2);
                        break;
                        default:
                }
                //网络有效才上传
                if (NetWorkStatus.isNetworkAvailable(getContext()))
                    uploadCard(img_);
            } else {
                showToast("无数据");
            }
        }
    }


    /**
     * 照片选项显示dilog
     * @param listener
     * @param names
     * @return
     */
    private SelectDialog showDialog(SelectDialog.SelectDialogListener listener, List<String> names) {
        SelectDialog dialog = new SelectDialog(getActivity(), R.style
                .transparentFrameWindowStyle,
                listener, names);
        if (!getActivity().isFinishing()) {
            dialog.show();
        }
        return dialog;
    }
    private void uploadCard(String img_) {

    }
}
