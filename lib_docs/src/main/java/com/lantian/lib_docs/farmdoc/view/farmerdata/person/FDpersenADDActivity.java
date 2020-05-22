package com.lantian.lib_docs.farmdoc.view.farmerdata.person;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.lantian.lib_base.MyApp;
import com.lantian.lib_base.database.greendao.PersonListDao;
import com.lantian.lib_base.file.sharedpreferences.SharedPreferencesUtils;
import com.lantian.lib_base.database.syncdata.SyncDataStatus;
import com.lantian.lib_base.entity.items.Item;
import com.lantian.lib_base.entity.module.response.farmer.farmlist.PersonList;
import com.lantian.lib_base.utils.EventMessage;
import com.lantian.lib_base.utils.BaseUtils;
import com.lantian.lib_commin_ui.base.ActivityManagerUtil;
import com.lantian.lib_commin_ui.base.BaseActivity;
import com.lantian.lib_commin_ui.dialog.SelectDialog;
import com.lantian.lib_commin_ui.spinner.SpinnerAdapter;
import com.lantian.lib_docs.R;
import com.lantian.lib_docs.utils.UplodPicUtil;
import com.lantian.lib_network.dialog.DialogUtils;
import com.lantian.lib_network.retrofit2.MyCallBack;
import com.lantian.lib_network.retrofit2.RetrofitHelper;
import com.lantian.lib_network.utils.NetworkUtils;
import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.bean.ImageItem;
import com.lzy.imagepicker.ui.ImageGridActivity;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sherlock·Holmes on 2020-04-03
 */
public class FDpersenADDActivity extends BaseActivity implements View.OnClickListener {

    private ImageView mPic;
    private EditText mEtCall;
    private EditText mEtName;
    private EditText mEtRelations;
    private EditText mEtPhone;
    private Spinner mSLabour;
    private Button mBtnSave;
    private ImageView back;
    private String imagPath;
    private DialogUtils dialogUtils;
    private Bundle bundle;
    private String userid;
    private PersonList personList;
    private PersonListDao personListDao;
    private int add =0;
    private SharedPreferencesUtils sharedPreferencesUtils;

    private static final int IMAGE_PICKER = 2020;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_fdperson_add;
    }
    @Override
    protected void init(Bundle savedInstanceState) {
        initView();
    }

    private void initView() {
        personListDao = ((MyApp) BaseUtils.getContext()).getDaoSession().getPersonListDao();
        sharedPreferencesUtils = new SharedPreferencesUtils(this);
        dialogUtils = new DialogUtils();
        back = findViewById(R.id.persen_btn_back);
        mPic = findViewById(R.id.pic);
        mEtCall =findViewById(R.id.et_call);
        mEtName = findViewById(R.id.persen_name);
        mEtRelations = findViewById(R.id.et_relations);
        mBtnSave = findViewById(R.id.btn_save);
        mSLabour = findViewById(R.id.s_labour);
        mEtPhone = findViewById(R.id.et_phone);
        mPic.setOnClickListener(this);
        mBtnSave.setOnClickListener(this);
        initSpinner();
    }


    /**
     * 照片回调函数
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == ImagePicker.RESULT_CODE_ITEMS){
            if (data !=null){
                ArrayList<ImageItem> imageItems = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_RESULT_ITEMS);
                String img_ = imageItems.get(0).path;
                imagPath = img_;
                if(requestCode==IMAGE_PICKER) {
                    /**获取图片路径，上传照片用**/
                    Glide.with(FDpersenADDActivity.this).load(img_).into(mPic);
                    /**
                     * 有网络走接口上传，没网络走数据库
                     */
                    if (NetworkUtils.isConnected()){
                        UplodPicUtil.upLodPic(img_,FDpersenADDActivity.this,dialogUtils);
                    }else {
                        imagPath = img_;
                    }
                }
            } else {
                showToast("无数据");
            }
        }
    }

    /**
     * 上传照片
     */
    private void getpic() {
        final Intent intent = new Intent(FDpersenADDActivity.this, ImageGridActivity.class);
        List<String> picname = new ArrayList<>();
        picname.add("拍照");
        picname.add("相册");
        showDialog(new SelectDialog.SelectDialogListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        intent.putExtra(ImageGridActivity.EXTRAS_TAKE_PICKERS, true); // 是否是直接打开相机
                        startActivityForResult(intent, IMAGE_PICKER);
                        break;
                    case 1:
                        Intent intent1 = new Intent(FDpersenADDActivity.this, ImageGridActivity.class);
                        startActivityForResult(intent1, IMAGE_PICKER);
                    default:
                        break;
                }
            }
        },picname);
    }

    /**
     * 照片选项显示dilog
     * @param listener
     * @param names
     * @return
     */
    private SelectDialog showDialog(SelectDialog.SelectDialogListener listener, List<String> names) {
        SelectDialog dialog = new SelectDialog(FDpersenADDActivity.this, R.style
                .transparentFrameWindowStyle,
                listener, names);
        if (!FDpersenADDActivity.this.isFinishing()) {
            dialog.show();
        }
        return dialog;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() ==R.id.persen_btn_back){
            ActivityManagerUtil.getAppManager().finishActivity();
        }else if (v.getId()==R.id.btn_save){
            add+=1;
            addPersen();
        }else if (v.getId()==R.id.pic){
            getpic();
        }
    }

    /**
     * 添加成员
     */
    private void addPersen() {
        flatPersenData();
        if (NetworkUtils.isConnected()){
            RetrofitHelper.getApiService().addPersenList(MyApp.Userid,imagPath,personList.getCall(),
                    personList.getName(),personList.getPer_relations(),personList.getTel(),personList.getLabour_type()).enqueue(new MyCallBack<PersonList>() {
                @Override
                public void success(PersonList personList) {
                    personListDao.insertOrReplace(personList);
                    EventBus.getDefault().postSticky(new EventMessage(1,"addOnNet"));
                    ActivityManagerUtil.getAppManager().finishActivity();
                }
                @Override
                public void failure(String msg) {
                    showToast(msg);
                }
            });
            /**数据库添加**/
        }else {
            String userid =(String)sharedPreferencesUtils.getParam("user_id","");
            PersonList personList1 = new PersonList();
            personList1.setStatus(SyncDataStatus.ADD.ordinal());
            personList1.setImg(imagPath);
            personList1.setUser_id(userid);
            personList1.setCall(personList.getCall());
            personList1.setName(personList.getName());
            personList1.setTel(personList.getTel());
            personList1.setPer_relations(personList.getPer_relations());
            personList1.setLabour_type(personList.getLabour_type());
            personList1.setId(String.valueOf(BaseUtils.getlinkNo()));
            personListDao.insertOrReplace(personList1);
            EventBus.getDefault().postSticky(new EventMessage(2,"addpersen"));
            ActivityManagerUtil.getAppManager().finishActivity();
        }

    }

    private void flatPersenData(){
        personList = new PersonList();
        personList.setCall(mEtCall.getText().toString());
        personList.setName(mEtName.getText().toString());
        personList.setTel(mEtPhone.getText().toString());
        personList.setPer_relations(mEtRelations.getText().toString());
        personList.setLabour_type(((Item)mSLabour.getSelectedItem()).getValue());
    }

    /**
     * 初始化 Spinner
     */
    private void initSpinner(){
        ArrayList<Item> items = new ArrayList<>();
        items.add(new Item("是","1"));
        items.add(new Item("否","2"));
        mSLabour.setAdapter(new SpinnerAdapter(this,R.layout.spinner_item,items));
    }
}
