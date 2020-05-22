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
import com.lantian.lib_base.entity.items.Item;
import com.lantian.lib_base.entity.module.response.farmer.farmhuku.HukuFind;
import com.lantian.lib_base.entity.module.response.farmer.farmlist.HuzhuList;
import com.lantian.lib_base.utils.EventMessage;
import com.lantian.lib_commin_ui.base.BaseFragmen;
import com.lantian.lib_commin_ui.dialog.SelectDialog;
import com.lantian.lib_commin_ui.spinner.SpinnerAdapter;
import com.lantian.lib_docs.R;
import com.lantian.lib_docs.farmdoc.view.havefarm.HaveFarmActivity;
import com.lantian.lib_docs.utils.AdressUtils;
import com.lantian.lib_docs.utils.UplodPicUtil;
import com.lantian.lib_image_loader.loadpic.ImageLoaderManager;
import com.lantian.lib_network.dialog.DialogUtils;
import com.lantian.lib_network.networkstatus.NetWorkStatus;
import com.lantian.lib_network.retrofit2.MyCallBack;
import com.lantian.lib_network.retrofit2.RetrofitHelper;
import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.bean.ImageItem;
import com.lzy.imagepicker.ui.ImageGridActivity;

import org.greenrobot.eventbus.EventBus;

import java.io.Serializable;
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
    private DialogUtils dialogUtils;
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
    private HuzhuList huzhuList;
    private String id;
    private String idnum;
    private String name;
    private int sex;
    private String birth;
    private String age;
    private int sheng;
    private int shi;
    private int xian;
    private String xiangxi;

    public static Fragment newInstance(HuzhuList huzhuList) {
        Bundle args = new Bundle();
        if (huzhuList!=null){
            args.putSerializable("data",huzhuList);
            CompleteMsgFragment fragment = new CompleteMsgFragment();
            fragment.setArguments(args);
            return fragment;
        }else {
            CompleteMsgFragment fragment = new CompleteMsgFragment();
            fragment.setArguments(args);
            return fragment;
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.complete_farmmsg_fragment, null);
        initView(view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initSpinner();
        mIdcard1.setOnClickListener(this);
        mIdcard2.setOnClickListener(this);
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
        mBtnSave.setOnClickListener(this);
        dialogUtils = new DialogUtils();
        initData();
    }

    /**
     * 显示数据
     */
    private void initData() {
        Bundle args = getArguments();
        /**
         * 已有数据的用户
         */
        if (args!=null){
            Serializable data = args.getSerializable("data");
            if (data != null){
                huzhuList = (HuzhuList) data;
                AdressUtils.bindAddressSpinners(getContext(),huzhuList,mSIdProvinces,mSIdCity,mSIdSeat);
                if (huzhuList.getId()!=null){
                    id = huzhuList.getId();
                }
                if (huzhuList.getIdcard_front()!=null&&huzhuList.getIdcard_side()!=null){
                    img1 = huzhuList.getIdcard_front();
                    img2 = huzhuList.getIdcard_side();
                    ImageLoaderManager.getInstance().displayImageForView(mIdcard1,img1);
                    ImageLoaderManager.getInstance().displayImageForView(mIdcard2,img2);
                }
                if (huzhuList.getIdcard()!=null){
                    idnum = huzhuList.getIdcard();
                    mEtIdCard.setText(idnum);
                }
                if (huzhuList.getName() !=null){
                    name = huzhuList.getName();
                    mEtIdName.setText(name);
                }
                if (huzhuList.getIdcard_gender()!=null){
                    sex = Integer.parseInt(huzhuList.getIdcard_gender())-1;
                    mSSex.setSelection(sex);
                }
                if (huzhuList.getBirth_date()!=null){
                    birth = huzhuList.getBirth_date();
                    mEtIdBirth.setText(birth);
                }
                if (huzhuList.getAge()!=null){
                    age = huzhuList.getAge();
                    mEtIdAge.setText(age);
                }
                if (huzhuList.getXiangxi()!=null){
                    xiangxi = huzhuList.getXiangxi();
                    mEtIdDetails.setText(xiangxi);
                }

            }
            /**
             * 新用户
             */
        }else {
            comMsg();
        }

    }

    /**
     * 完善信息
     */
    private void comMsg() {

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
        } else if (v.getId() ==R.id.btn_save){
            if (!huzhuList.getName().equals("")){
                editComMsg();
            }

        }
    }

    /**
     * 修改信息
     */
    private void editComMsg() {
        flatCompMsg();
        RetrofitHelper.getApiService().editFarmMsg(id,img1,img2, huzhuList.getName(), huzhuList.getIdcard_gender()
                , huzhuList.getBirth_date(), huzhuList.getIdcard(), huzhuList.getSheng(), huzhuList.getShi(),
                huzhuList.getXian(), huzhuList.getXiangxi(), huzhuList.getAge()).enqueue(new MyCallBack<HukuFind>() {
            @Override
            public void success(HukuFind hukuFind) {
                if (hukuFind !=null){
                    EventBus.getDefault().postSticky(new EventMessage(1,"edit"));
                    HaveFarmActivity.instance(getContext(),HaveFarmActivity.class,null);
                }
            }

            @Override
            public void failure(String msg) {
                showToast(msg);
            }
        });
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
                        Glide.with(CompleteMsgFragment.this).load(img_).into(mIdcard1);
                        break;
                    case IMAGE_PICKER_2:
                        img2 = img_;
                        Glide.with(CompleteMsgFragment.this).load(img_).into(mIdcard2);
                        break;
                        default:
                }
                //网络有效才上传
                if (NetWorkStatus.isNetworkAvailable(getContext())) {
                    uploadCard(img_);
                }
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

    /**
     * 上传
     * @param img_
     */
    private void uploadCard(String img_) {
        UplodPicUtil.upLodPic(img_,getContext(),dialogUtils);
    }

    /**
     * 初始化选项
     */
    private void initSpinner() {
        ArrayList<Item> forces = new ArrayList<>();
        forces.add(new Item("女", "1"));
        forces.add(new Item("男", "2"));
        mSSex.setAdapter(new SpinnerAdapter(getContext()
                , android.R.layout.simple_list_item_1, forces));

    }


    private void flatCompMsg(){
        huzhuList = new HuzhuList();
        huzhuList.setIdcard(mEtIdCard.getText().toString().trim());
        huzhuList.setName(mEtIdName.getText().toString().trim());
        huzhuList.setIdcard_gender(((Item)mSSex.getSelectedItem()).getValue());
        huzhuList.setBirth_date(mEtIdBirth.getText().toString().trim());
        huzhuList.setAge(mEtIdAge.getText().toString().trim());
        huzhuList.setSheng(((Item)mSIdProvinces.getSelectedItem()).getValue());
        huzhuList.setShi(((Item)mSIdCity.getSelectedItem()).getValue());
        huzhuList.setXian(((Item)mSIdSeat.getSelectedItem()).getValue());
        huzhuList.setXiangxi(mEtIdDetails.getText().toString().trim());

    }
}
