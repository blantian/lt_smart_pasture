package com.lantian.lib_docs.breeddoc;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.lantian.lib_base.MyApp;
import com.lantian.lib_base.database.greendao.AddBreedDao;
import com.lantian.lib_base.file.sharedpreferences.SharedPreferencesUtils;
import com.lantian.lib_base.database.syncdata.SyncDataStatus;
import com.lantian.lib_base.entity.items.Item;
import com.lantian.lib_base.entity.module.response.breeds.AddBreed;
import com.lantian.lib_base.entity.module.response.breeds.BreedClassFind;
import com.lantian.lib_base.entity.module.response.breeds.Laiyuan;
import com.lantian.lib_base.entity.module.response.breeds.Pinzhong;
import com.lantian.lib_base.entity.module.response.picture.UplodPic;
import com.lantian.lib_base.utils.EventMessage;
import com.lantian.lib_base.utils.BaseUtils;
import com.lantian.lib_commin_ui.base.ActivityManagerUtil;
import com.lantian.lib_commin_ui.base.BaseActivity;
import com.lantian.lib_commin_ui.dialog.SelectDialog;
import com.lantian.lib_commin_ui.spinner.SpinnerAdapter;
import com.lantian.lib_docs.R;
import com.lantian.lib_docs.utils.AdressUtils;
import com.lantian.lib_network.dialog.DialogUtils;
import com.lantian.lib_network.retrofit2.MyCallBack;
import com.lantian.lib_network.retrofit2.RetrofitHelper;
import com.lantian.lib_network.utils.NetworkUtils;
import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.bean.ImageItem;
import com.lzy.imagepicker.ui.ImageGridActivity;

import org.greenrobot.eventbus.EventBus;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * Created by Sherlock·Holmes on 2020/4/26
 */
public class
AddNewBreedActivity extends BaseActivity implements View.OnClickListener {

    private ImageView mEarBtnBack;
    private Spinner mClassify;
    private ImageView mPic;
    private EditText mEtName;
    private EditText mEtNumber;
    private EditText mEtAge;
    private EditText mSelectByTime;
    private EditText mEtBypress;
    private EditText mSelectSellTime;
    private Spinner mAdressProvince;
    private Spinner mAdressCity;
    private Spinner mAdressCounty;
    private EditText mEtDetailAddress;
    private Spinner mBreed;
    private Spinner mSupplier;
    private Button mSaveMsg;
    private TextView mSupport;
    private final int IMAGE_PICKER = 21;
    private String imagPath;
    private DialogUtils dialogUtils;
    private AddBreed addBreed;
    private AddBreedDao addBreedDao;
    private SharedPreferencesUtils s;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_addnewbreeddoc;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        initView();
    }

    private void initView() {
        addBreedDao = ((MyApp) BaseUtils.getContext()).getDaoSession().getAddBreedDao();
        s = new SharedPreferencesUtils(AddNewBreedActivity.this);
        dialogUtils =new DialogUtils();
        mEarBtnBack = (ImageView) findViewById(R.id.addbreed_btn_back);
        mEarBtnBack.setOnClickListener(this);
        mClassify = (Spinner) findViewById(R.id.classify);
        mPic = (ImageView) findViewById(R.id.pic);
        mPic.setOnClickListener(this);
        mEtName = (EditText) findViewById(R.id.et_name);
        mEtNumber = (EditText) findViewById(R.id.et_number);
        mEtAge = (EditText) findViewById(R.id.et_age);
        mSelectByTime = (EditText) findViewById(R.id.select_by_time);
        mEtBypress = (EditText) findViewById(R.id.et_bypress);
        mSelectSellTime = (EditText) findViewById(R.id.select_sell_time);
        mAdressProvince = (Spinner) findViewById(R.id.adress_province);
        mAdressCity = (Spinner) findViewById(R.id.adress_city);
        mAdressCounty = (Spinner) findViewById(R.id.adress_county);
        mEtDetailAddress = (EditText) findViewById(R.id.et_detailAddress);
        mBreed = (Spinner) findViewById(R.id.breed);
        mSupplier = (Spinner) findViewById(R.id.supplier);
        mSaveMsg = (Button) findViewById(R.id.save_msg);
        mSupport = (TextView) findViewById(R.id.support);
        mSelectByTime.setOnClickListener(this);
        mSelectSellTime.setOnClickListener(this);
        mSaveMsg.setOnClickListener(this);
        initSpinner();
    }
    @Override
    public void onClick(View v) {
        if (v.getId() ==R.id.addbreed_btn_back){
            ActivityManagerUtil.getAppManager().finishActivity();
        }else if (v.getId()==R.id.pic){
            getPic();
        }else if (v.getId()==R.id.select_by_time){
            timePicker(mSelectByTime);
        }else if (v.getId() ==R.id.select_sell_time){
            timePicker(mSelectSellTime);
        }else if (v.getId()==R.id.save_msg){
            saveMsg();
        }
    }

    /**
     * 添加养殖档案
     */
    private void saveMsg() {
        /**线上**/
        if (NetworkUtils.isConnected()){
            RetrofitHelper.getApiService().addBreed(MyApp.Userid,((Item)mClassify.getSelectedItem()).getValue(),mEtNumber.getText().toString(),
                    mSelectByTime.getText().toString(),mEtName.getText().toString(),mEtAge.getText().toString(),mSelectSellTime.getText().toString(),mEtBypress.getText().toString()
                    ,imagPath,((Item)mAdressProvince.getSelectedItem()).getValue(),((Item)mAdressCity.getSelectedItem()).getValue(),((Item)mAdressCounty.getSelectedItem()).getValue(),
                    ((Item)mBreed.getSelectedItem()).getValue(),mEtDetailAddress.getText().toString(),
                    ((Item)mSupplier.getSelectedItem()).getValue()).enqueue(new MyCallBack<AddBreed>() {
                @Override
                public void success(AddBreed addBreed) {
                    if (addBreed.getUser_id().equals(MyApp.Userid)){
                        addBreed.setStatus(SyncDataStatus.HAVE.ordinal());
                        addBreed.setImg(imagPath);
                        addBreedDao.insertOrReplace(addBreed);
                        Bundle bundle = new Bundle();
                        bundle.putString("breed_id",addBreed.getBreedclass_id());
                        EventBus.getDefault().postSticky(new EventMessage(1,"add"));
                        NewBreedActivity.instance(AddNewBreedActivity.this,NewBreedActivity.class,bundle);
                        ActivityManagerUtil.getAppManager().finishActivity();
                    }
                }

                @Override
                public void failure(String msg) {
                    showToast(msg);
                }
            });
        }else {
            /**离线**/
            String userid = (String)s.getParam("user_id","");
            addBreed = new AddBreed();
            addBreed.setStatus(SyncDataStatus.ADD.ordinal());
            addBreed.setUser_id(userid);
            addBreed.setBreedclass_id(((Item)mClassify.getSelectedItem()).getValue());
            addBreed.setNumber(mEtNumber.getText().toString());
            addBreed.setAcquisition_time(mSelectByTime.getText().toString());
            addBreed.setTitle(mEtName.getText().toString());
            addBreed.setBecome_time(mSelectSellTime.getText().toString());
            addBreed.setPrice(mEtBypress.getText().toString());
            addBreed.setImg(imagPath);
            addBreed.setSheng(((Item)mAdressProvince.getSelectedItem()).getValue());
            addBreed.setShi(((Item)mAdressCity.getSelectedItem()).getValue());
            addBreed.setXian(((Item)mAdressCounty.getSelectedItem()).getValue());



            Bundle bundle = new Bundle();
            bundle.putString("breed_id",addBreed.getBreedclass_id());
            EventBus.getDefault().postSticky(new EventMessage(1,"add"));
            NewBreedActivity.instance(AddNewBreedActivity.this,NewBreedActivity.class,bundle);
            ActivityManagerUtil.getAppManager().finishActivity();

        }

    }

    /**
     * 时间选择
     */
    private void timePicker(final EditText editText) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            final DatePickerDialog dialogStart = new DatePickerDialog(this);
            dialogStart.setOnDateSetListener(new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    Calendar time = Calendar.getInstance();
                    time.set(Calendar.YEAR, year);
                    time.set(Calendar.MONTH, month);
                    time.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                    editText.setText(new SimpleDateFormat("YYYY-MM-dd").format(time.getTime()));
                }
            });
            dialogStart.show();

        } else {
            showToast("系统不支持");
        }
    }


    /**
     * 上传图片
     */
    private void getPic() {
        final Intent intent = new Intent(AddNewBreedActivity.this, ImageGridActivity.class);
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
                        Intent intent1 = new Intent(AddNewBreedActivity.this, ImageGridActivity.class);
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
        SelectDialog dialog = new SelectDialog(AddNewBreedActivity.this, R.style
                .transparentFrameWindowStyle,
                listener, names);
        if (!AddNewBreedActivity.this.isFinishing()) {
            dialog.show();
        }
        return dialog;
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
                if(requestCode==IMAGE_PICKER) {
                    /**获取图片路径，上传照片用**/
                    Glide.with(AddNewBreedActivity.this).load(img_).into(mPic);
                    /**
                     * 有网络走接口上传，没网络走数据库
                     */
                    if (NetworkUtils.isConnected()){
                        upLodPic(img_);
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
     * @param img
     */
    private void upLodPic(final String img) {
        dialogUtils.showProgress(AddNewBreedActivity.this,"正在上传图片.....");
        File file = new File(img);
        RetrofitHelper.getApiService().uploadImg(MultipartBody.Part.createFormData("photo", file.getName(),
                RequestBody.create(MediaType.parse("image/*"), file))).enqueue(new MyCallBack<UplodPic>() {
            @Override
            public void success(UplodPic uplodPic) {
                imagPath = uplodPic.getImg();
                dialogUtils.dismissProgress();
            }
            @Override
            public void failure(String msg) {
                showToast(msg);
            }
        });

    }


    /**
     * 初始化Spinner
     */
    private void initSpinner() {
        /**
         * 三级地址
         */
        AdressUtils.getAdress(AddNewBreedActivity.this,mAdressProvince,mAdressCity,mAdressCounty);
        /**
         * 种类
         */
        final ArrayList<Item> classitems = new ArrayList<>();
        RetrofitHelper.getApiService().getBreedClass().enqueue(new MyCallBack<ArrayList<BreedClassFind>>() {
            @Override
            public void success(ArrayList<BreedClassFind> breedClassFinds) {
                if (breedClassFinds!=null){
                    for (int i =0;i<breedClassFinds.size();i++){
                        classitems.add(new Item(breedClassFinds.get(i).getName(),breedClassFinds.get(i).getId()));
                    }
                    mClassify.setAdapter(new SpinnerAdapter(AddNewBreedActivity.this,R.layout.spinner_item,classitems));
                }
            }
            @Override
            public void failure(String msg) {

            }
        });

        /**
         * 品种
         */
        final ArrayList<Item> pinzhongitems = new ArrayList<>();
        RetrofitHelper.getApiService().getPinZhong().enqueue(new MyCallBack<ArrayList<Pinzhong>>() {
            @Override
            public void success(ArrayList<Pinzhong> pinzhongs) {
                if (pinzhongs !=null){
                    for (int i =0;i<pinzhongs.size();i++){
                        pinzhongitems.add(new Item(pinzhongs.get(i).getName(),pinzhongs.get(i).getId()));
                    }
                }
                mBreed.setAdapter(new SpinnerAdapter(AddNewBreedActivity.this,R.layout.spinner_item,pinzhongitems));
            }

            @Override
            public void failure(String msg) {
                showToast(msg);
            }
        });
        /**
         * 购买梁道
         */
        final ArrayList<Item> liangdaoitems = new ArrayList<>();
        RetrofitHelper.getApiService().getLaiYuan().enqueue(new MyCallBack<ArrayList<Laiyuan>>() {
            @Override
            public void success(ArrayList<Laiyuan> laiyuans) {
                if (laiyuans != null){
                    for (int i = 0; i<laiyuans.size();i++){
                        liangdaoitems.add(new Item(laiyuans.get(i).getName(),laiyuans.get(i).getId()));
                    }
                }
                mSupplier.setAdapter(new SpinnerAdapter(AddNewBreedActivity.this,R.layout.spinner_item,liangdaoitems));
            }

            @Override
            public void failure(String msg) {

            }
        });
    }

}
