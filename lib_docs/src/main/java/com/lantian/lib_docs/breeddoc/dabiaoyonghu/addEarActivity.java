package com.lantian.lib_docs.breeddoc.dabiaoyonghu;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.fastjson.JSON;
import com.android.hdhe.uhf.reader.Tools;
import com.android.hdhe.uhf.reader.UhfReader;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.lantian.lib_base.MyApp;
import com.lantian.lib_base.database.greendao.BreedFindDao;
import com.lantian.lib_base.database.greendao.BreedIndexDao;
import com.lantian.lib_base.database.greendao.BreedsListDao;
import com.lantian.lib_base.database.greendao.EarTagDao;
import com.lantian.lib_base.database.greendao.ImgsDao;
import com.lantian.lib_base.database.greendao.editEarTagDao;
import com.lantian.lib_base.database.syncdata.SyncDataStatus;
import com.lantian.lib_base.entity.items.Item;
import com.lantian.lib_base.entity.module.response.breeds.BreedFind;
import com.lantian.lib_base.entity.module.response.breeds.BreedIndex;
import com.lantian.lib_base.entity.module.response.breeds.BreedsList;
import com.lantian.lib_base.entity.module.response.breeds.EarTag;
import com.lantian.lib_base.entity.module.response.breeds.addEarTag;
import com.lantian.lib_base.entity.module.response.breeds.editEarTag;
import com.lantian.lib_base.entity.module.response.img.Imgs;
import com.lantian.lib_base.entity.module.response.picture.UplodPic;
import com.lantian.lib_base.file.DelFile;
import com.lantian.lib_base.thread.ThreadPoolManager;
import com.lantian.lib_base.utils.BaseUtils;
import com.lantian.lib_base.utils.EventMessage;
import com.lantian.lib_commin_ui.base.ActivityManagerUtil;
import com.lantian.lib_commin_ui.base.BaseActivity;
import com.lantian.lib_commin_ui.dialog.AlertDialogUtil;
import com.lantian.lib_commin_ui.dialog.SelectDialog;
import com.lantian.lib_commin_ui.spinner.SpinnerAdapter;
import com.lantian.lib_docs.R;
import com.lantian.lib_docs.breeddoc.GetBigPicActivity;
import com.lantian.lib_docs.breeddoc.dabiaoyonghu.adapter.EarTagAdapter;
import com.lantian.lib_image_loader.downpic.PicByLuban;
import com.lantian.lib_image_loader.loadpic.ImageLoaderManager;
import com.lantian.lib_network.dialog.DialogUtils;
import com.lantian.lib_network.receiver.NetStateChangeReceiver;
import com.lantian.lib_network.receiver.interfaces.NetStateChangeObserver;
import com.lantian.lib_network.retrofit2.MyCallBack;
import com.lantian.lib_network.retrofit2.RetrofitHelper;
import com.lantian.lib_network.utils.NetworkType;
import com.lantian.lib_network.utils.NetworkUtils;
import com.lantian.lib_uhf.SoundUtil;
import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.bean.ImageItem;
import com.lzy.imagepicker.ui.ImageGridActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import top.zibin.luban.CompressionPredicate;
import top.zibin.luban.Luban;
import top.zibin.luban.OnCompressListener;
import top.zibin.luban.OnRenameListener;

/**
 * Created by Sherlock·Holmes on 2020-04-01
 */
public class addEarActivity extends BaseActivity implements View.OnClickListener, NetStateChangeObserver, View.OnFocusChangeListener {

    private DrawerLayout drawerLayout;
    private ImageView mEarBtnBack;
    private EditText mEarTagSearch;
    private TextView mBtnEarAdd;
    private ImageView mBreedHeadIcon;
    private EditText mBreedWight;
    private Spinner mBreedSex;
    private Button mSave;
    private Bundle bundle;

    private EditText mDrawerEarTagSearch;
    private TextView mBtnDrawerSearch;
    private RecyclerView mEarDrawerList;
    private Button mBttnAddEar;
    private Button mBttnEarCancel;
    private EarTagAdapter earTagAdapter;
    private GridLayoutManager gridLayoutManager;
    private FloatingActionButton floatingActionButton;
    private final int IMAGE_PICKER = 2100;

    private Handler handler;
    private UhfReader reader;
    private List<byte[]> epcList;
    private boolean startFlag = false;
    private int outputpw = 26;

    private static final String ALL = "0";
    private static final String NORMAL = "1";
    private static  final String CANCELLATION ="2";
    private String userid;
    private String eraCode;
    private String BreedId;
    private String NoEarTagBreedId;
    private String earTagId;
    private String editEarTagId;
    private String editEarTagBreedId;
    private String newEditeEarTagBreedId;
    private String imagPath;
    private String age;
    private String addTime;
    private String editEarCode ="";
    private String imgPath;
    private List<EarTag> earTags;
    private EarTagDao earTagDao;
    private editEarTagDao mEditEarTagDao;
    private BreedFindDao breedFindDao;
    private BreedsListDao breedsListDao;
    private BreedIndexDao breedIndexDao;
    private ImgsDao imgsDao;
    private List<editEarTag> editEarTags;
    private DialogUtils dialogUtils;
    private String[] names = new String[]{"确定","更新数据","取消"
    };
    private String[] bttnnames = new String[]{"确定","取消"};

    @Override
    protected int getLayoutId() {
        return R.layout.activity_add_ear_drawer;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        NetStateChangeReceiver.registerReceiver(this);
        initView();
    }

    private void initView() {
        EventBus.getDefault().register(this);
        bundle = getIntent().getExtras();
        SoundUtil.initSoundPool(addEarActivity.this);
        reader = UhfReader.getInstance();
        if (reader !=null){
            reader.setOutputPower(outputpw);
        }

        earTagDao = ((MyApp) BaseUtils.getContext()).getDaoSession().getEarTagDao();
        earTags = new ArrayList<>();

        mEditEarTagDao = ((MyApp) BaseUtils.getContext()).getDaoSession().getEditEarTagDao();
        editEarTags = new ArrayList<>();

        breedFindDao = ((MyApp) BaseUtils.getContext()).getDaoSession().getBreedFindDao();
        breedsListDao = ((MyApp) BaseUtils.getContext()).getDaoSession().getBreedsListDao();

        breedIndexDao = ((MyApp) BaseUtils.getContext()).getDaoSession().getBreedIndexDao();

        imgsDao = ((MyApp)BaseUtils.getContext()).getDaoSession().getImgsDao();

        dialogUtils = new DialogUtils();
        mDrawerEarTagSearch = findViewById(R.id.drawer_ear_tag_search);
        mBtnDrawerSearch = findViewById(R.id.btn_drawer_search);
        mEarDrawerList = findViewById(R.id.ear_drawer_list);
        mBttnAddEar = findViewById(R.id.bttn_add_ear);
        mBttnEarCancel = findViewById(R.id.bttn_rselect_ear);
        drawerLayout = findViewById(R.id.addDrawerLayout);
        mEarBtnBack = findViewById(R.id.ear_btn_back);
        mEarTagSearch = findViewById(R.id.add_ear_tag_search);
        mSave = findViewById(R.id.save);
        mBreedHeadIcon = findViewById(R.id.breed_head_icon);
        mBreedWight = findViewById(R.id.breed_wight);
        mBreedSex = findViewById(R.id.breed_sex);
        mBtnEarAdd = findViewById(R.id.btn_ear_add);
        floatingActionButton = findViewById(R.id.read_ear_tag);
        gridLayoutManager = new GridLayoutManager(this,1);
        mBtnEarAdd.setOnClickListener(this);
        mEarBtnBack.setOnClickListener(this);
        mBreedHeadIcon.setOnClickListener(this);
        mBttnEarCancel.setOnClickListener(this);
        mBttnAddEar.setOnClickListener(this);
        mSave.setOnClickListener(this);
        mBtnDrawerSearch.setOnClickListener(this);
        floatingActionButton.setOnClickListener(this);
        mEarTagSearch.setFocusable(false);

        handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                mDrawerEarTagSearch.setText(String.valueOf(msg.obj));
                searchTag(String.valueOf(msg.obj));

            }
        };
        initData();
        initEarData();
        initSpinner();
    }





    /**
     * 数据初始化
     */
    private void initData() {
        if (bundle != null) {
            mEarTagSearch.setText(bundle.getString("earCode"));
            mBreedWight.setText(bundle.getString("weight"));
            /**要修改的耳标id**/
            editEarCode = bundle.getString("earCode");
            editEarTagId =bundle.getString("Eartag_id");
            userid = MyApp.DaBiaoUserid;
            /**添加耳标获取的 BreedId**/
            NoEarTagBreedId = bundle.getString("NoEarTagBreedId");
            imagPath = bundle.getString("img");
            editEarTagBreedId = bundle.getString("id");
            BreedId = MyApp.BreedId;
            ImageLoaderManager.getInstance().displayImageForView(mBreedHeadIcon,imagPath);
        }
        getBreedFind(BreedId,userid);

    }

    @Override 
    public void onClick(View v) {
        if (v.getId() == R.id.btn_ear_add) {
            drawerLayout.openDrawer(Gravity.LEFT);
        }if (v.getId() == R.id.ear_btn_back){
            ActivityManagerUtil.getAppManager().finishActivity();
        }if (v.getId() ==R.id.breed_head_icon){
            getpic();
        }if (v.getId() == R.id.bttn_rselect_ear){
           /**重新获取耳标**/
            mDrawerEarTagSearch.setText("");
               getEarTag();
        }if (v.getId() ==R.id.bttn_add_ear){
            if (mDrawerEarTagSearch.getText().toString().equals("")){
                showToast("请选择耳标！");
            }else {
                mEarTagSearch.setText(eraCode);
                drawerLayout.closeDrawers();
            }
            mEarTagSearch.setText(eraCode);
        }if (v.getId() == R.id.save){
            if (CheckEarTagId()){
                UpdataEarTag();
            }else {
                Save();
            }
            /**耳标搜索**/
        }if (v.getId() == R.id.btn_drawer_search){
            searchTag(mDrawerEarTagSearch.getText().toString());
            /**读耳标**/
        }if (v.getId() ==R.id.read_ear_tag ){
            redUhf();
        }
    }



    /**检查耳标**/
    private boolean CheckEarTagId(){
        if (editEarCode !=null){
            if (editEarCode.equals(mEarTagSearch.getText().toString())){
                return true;
            }else {
                return false;
            }
        }else {
            return false;
        }
    }
    /**
     * 获取耳标
     */
    private void getEarTag() {
        /**有网络**/
        if (NetworkUtils.isConnected()){
            RetrofitHelper.getApiService().getEarTag(userid,NORMAL).enqueue(new MyCallBack<ArrayList<EarTag>>() {
                @Override
                public void success(ArrayList<EarTag> earTags) {
                    if (earTags !=null){
                        earTagDao.insertOrReplaceInTx(earTags);
                        initAdapter(earTags);
                    }else {
                        showToast("没有耳标可添加");
                    }
                }
                @Override
                public void failure(String msg) {

                }
            });
            /**无网络**/
        }else {
            earTags = earTagDao.queryBuilder().where(EarTagDao.Properties.User_id.eq(userid)).list();
            if (earTags.size()!=0){
                initAdapter(earTags);
            }else {
                showToast("没有耳标可添加,请连接网络重新获取新数据！");
            }
        }

    }

    /**
     * 搜索耳标
     */
    private void searchTag(String earNum) {
        for (int i = 0; i<earTagAdapter.getData().size();i++){
            if (earTagAdapter.getData().get(i).getNumber().equals(earNum)){
                eraCode = earNum;
                earTagId = earTagAdapter.getData().get(i).getId();
                Log.e("eratTagId",earTagId);
                EarTag earTag = earTagAdapter.getData().get(i);
                List<EarTag> earTags = new ArrayList<>();
                earTags.add(earTag);
                earTagAdapter.setNewData(earTags);
            }else {
                if (reader !=null){
                    showToast("耳标不存在");
                }
            }
        }
    }

    /**
     * 普通手机屏蔽高频读取按钮
     * @param button
     * @param flag
     */
    private void setButtonClickable(Button button, boolean flag) {
        button.setClickable(flag);
        if (flag) {
            button.setTextColor(Color.BLACK);
        } else {
            button.setTextColor(Color.GRAY);
        }
    }
    /**
     * 读EPC线程
     * @author Administrator
     */
    private void redUhf() {
        if (reader !=null){
            if (!startFlag) {
                startFlag = true;
            }
            ThreadPoolManager.getInstance().execute(new Runnable() {
                @Override
                public void run() {
                    while (startFlag) {
                        epcList = reader.inventoryRealTime(); //实时盘存
                        if (epcList != null && !epcList.isEmpty()) {
                            //播放提示音
                            SoundUtil.play(1, 0);
                            for (byte[] epc : epcList) {
                                //将Byte转换为16进制字符串
                                String epcStr = Tools.Bytes2HexString(epc, epc.length);
                                reader.stopInventoryMulti();
                                Message message = Message.obtain();
                                message.obj = epcStr;
                                handler.sendMessage(message);
                                Log.e("EPC码:", epcStr);
                            }
                           startFlag = false;
                        }
                        epcList = null;
                        try {
                            Thread.sleep(0);
                        } catch (InterruptedException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    }
                }
            });
        }else {
            showToast("您的手机不支持此功能！");
        }
    }

    /**
     * 添加新耳标
     */
    private void Save(){
        if (NetworkUtils.isConnected()){
            addNewEarTag();
        }else {
            insertInDataBase();
        }
    }
    /**
     * 获取牧户养殖的牲畜的单条详细信息
     */
    private void getBreedFind(String breedid,String userid){
        RetrofitHelper.getApiService().geBreedFind(breedid,userid).enqueue(new MyCallBack<BreedFind>() {
            @Override
            public void success(BreedFind breedFind) {
                breedFindDao.insertOrReplace(breedFind);
                age = breedFind.getAge();
                addTime = breedFind.getAddtime();
            }
            @Override
            public void failure(String msg) {

            }
        });

    }
    /**
     * 更改耳标下的牲畜信息
     */
    private void UpdataEarTag(){
        /***
         * 有网添加
         */
        if (NetworkUtils.isConnected()) {
            RetrofitHelper.getApiService().addBreedEarTag(imagPath,editEarTagId
                    , mBreedWight.getText().toString(), age, addTime,
                    ((Item) mBreedSex.getSelectedItem()).getValue(), editEarTagBreedId,BreedId, MyApp.Userid).enqueue(new MyCallBack<addEarTag>() {
                @Override
                public void success(addEarTag addEarTag) {
                    editEarTagId = addEarTag.getEartag_id();
                    newEditeEarTagBreedId = addEarTag.getId();
                }
                /**
                 * 提示
                 *
                 * @param msg
                 */
                @Override
                public void failure(String msg) {
                    AlertDialogUtil.ThreeChoiceDialog(addEarActivity.this, "提示", msg, names, new AlertDialogUtil.ThreeChoiceHandle() {
                        /**新增数据**/
                        @Override
                        public void onPositiveButtonHandle() {
                            RetrofitHelper.getApiService().addBreedEarTag(imagPath,editEarTagId
                                    ,mBreedWight.getText().toString(),age,addTime,
                                    ((Item)mBreedSex.getSelectedItem()).getValue(), newEditeEarTagBreedId,BreedId,MyApp.Userid).enqueue(new MyCallBack<addEarTag>() {
                                @Override
                                public void success(addEarTag addEarTag) {
                                    /**在线添加成功的耳标，在数据库力删除**/
                                    earTagDao.deleteByKey(addEarTag.getId());
                                    EventBus.getDefault().post(new EventMessage(1, userid));
                                    ActivityManagerUtil.getAppManager().finishActivity();
                                }
                                @Override
                                public void failure(String msg) {
                                    showToast(msg);
                                }
                            });
                            /**延迟操作**/
                            ThreadPoolManager.getInstance().execute(new Runnable() {
                                @Override
                                public void run() {
                                    try {
                                        Thread.sleep(500);
                                        EventBus.getDefault().post(new EventMessage(1, userid));
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }

                                }
                            });
                            ActivityManagerUtil.getAppManager().finishActivity();

                        }

                        /**更新数据**/
                        @Override
                        public void onNegativeButtonHandle() {
                            editEarTag();
                            /**延迟操作**/
                            ThreadPoolManager.getInstance().execute(new Runnable() {
                                @Override
                                public void run() {
                                    try {
                                        Thread.sleep(500);
                                        EventBus.getDefault().post(new EventMessage(1, userid));
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }

                                }
                            });
                            ActivityManagerUtil.getAppManager().finishActivity();
                        }

                        @Override
                        public void onNeutralButtonButtonHandle() {

                        }
                    });
                }
            });
            /**离线添加**/
        }else{
            insertInDataBase();
        }
    }

    /**
     * 获取耳标id
     */
    public void getEarTagId(){
        for (int i = 0;i<earTags.size();i++){
            earTagId = earTags.get(i).getId();
            break;
        }
    }

    /**
     * 离线添加耳标
     */
    private void insertInDataBase() {
            BreedIndex breedIndex = breedIndexDao.queryBuilder().where(BreedIndexDao.Properties.User_id.eq(userid)).unique();
            EarTag earTag = earTagDao.queryBuilder().where(EarTagDao.Properties.Id.eq(earTagId)).unique();
            age =breedIndex.getAge();
            addTime = breedIndex.getAddtime();
            /**转换为Json**/
            String s = JSON.toJSONString(earTag);
            String f = JSON.toJSONString(breedIndex);
            /**格式转换**/
            BreedsList.EartagBean eartagBean = JSON.parseObject(s, BreedsList.EartagBean.class);
            BreedsList.BreedBean lcobreedFind = JSON.parseObject(f,BreedsList.BreedBean.class);
            getEarTagId();
            editEarTag EditEarTag = new editEarTag();
            /**标记添加的耳标**/
            EditEarTag.setStatus(SyncDataStatus.ADD.ordinal());
            EditEarTag.setImg(imagPath);
            Log.e("测试",earTagId);
            EditEarTag.setEartag_id(earTagId);
            EditEarTag.setWeight(mBreedWight.getText().toString());
            EditEarTag.setAge(age);
            EditEarTag.setAddtime(addTime);
            EditEarTag.setType(((Item)mBreedSex.getSelectedItem()).getValue());
            EditEarTag.setId(NoEarTagBreedId);
            EditEarTag.setBreed_id(BreedId);
            EditEarTag.setUser_id(userid);
            EditEarTag.setUpdate_userid(MyApp.Userid);
            mEditEarTagDao.insert(EditEarTag);

            /**
             * 修改breedList表的养殖信息
             * 标记为已经添加耳标
             * */
            BreedsList breedsList = new BreedsList();
            breedsList.setStatus(SyncDataStatus.HAVE.ordinal());
            Log.e("number",mEarTagSearch.getText().toString());
            breedsList.setEartag(eartagBean);
            breedsList.setBreed(lcobreedFind);
            breedsList.setEartag_id(earTagId);
            breedsList.setWeight(mBreedWight.getText().toString());
            breedsList.setAge(age);
            breedsList.setAddtime(addTime);
            breedsList.setType(((Item)mBreedSex.getSelectedItem()).getValue());
            breedsList.setId(NoEarTagBreedId);
            breedsList.setBreed_id(BreedId);
            breedsList.setUser_id(userid);
            breedsList.setUpdate_userid(MyApp.Userid);
            breedsListDao.update(breedsList);

            /**删除已添加的耳标id**/
            earTagDao.deleteByKey(earTagId);
            /**重新从数据库获取耳标**/
            initEarData();
            /**刷新adapter**/
            earTagAdapter.notifyDataSetChanged();
        EventBus.getDefault().post(new EventMessage(2, userid));
        ActivityManagerUtil.getAppManager().finishActivity();
    }

    /**离线修改耳标**/
    private void nolineeditdata() {
            AlertDialogUtil.TwoChoiceDialog(addEarActivity.this, "离线修改信息", "您要修改耳标信息吗？", bttnnames, new AlertDialogUtil.TwoChoiceHandle() {
                @Override
                public void onPositiveButtonHandle() {
                    editEarTag EditEarTag = new editEarTag();
                    /**标记添加的耳标**/
                    EditEarTag.setStatus(SyncDataStatus.UPDATE.ordinal());
                    EditEarTag.setImg(imagPath);
                    Log.e("测试",earTagId);
                    EditEarTag.setEartag_id(earTagId);
                    EditEarTag.setWeight(mBreedWight.getText().toString());
                    EditEarTag.setAge(age);
                    EditEarTag.setAddtime(addTime);
                    EditEarTag.setType(((Item)mBreedSex.getSelectedItem()).getValue());
                    EditEarTag.setId(NoEarTagBreedId);
                    EditEarTag.setBreed_id(BreedId);
                    EditEarTag.setUser_id(userid);
                    EditEarTag.setUpdate_userid(MyApp.Userid);
                    mEditEarTagDao.update(EditEarTag);
                }
                @Override
                public void onNegativeButtonHandle() {

                }
            });

    }

    /**
     * 修改信息
     */
    private void editEarTag(){
        RetrofitHelper.getApiService().editBreedEarTag(imagPath,editEarTagId
                ,mBreedWight.getText().toString(),age,addTime,
                ((Item)mBreedSex.getSelectedItem()).getValue(), editEarTagBreedId,BreedId,MyApp.Userid).enqueue(new MyCallBack<editEarTag>() {
            @Override
            public void success(editEarTag meditEarTag) {
                editEarTag MeditEarTag = meditEarTag;
                MeditEarTag.setStatus(SyncDataStatus.UPDATE.ordinal());
                mEditEarTagDao.insertOrReplace(MeditEarTag);
            }
            @Override
            public void failure(String msg) {

            }
        });
    }

    /**
     * 接口添加耳标
     */
    private void addNewEarTag(){
        if (earTagId != null){
            RetrofitHelper.getApiService().addBreedEarTag(imagPath,earTagId
                    ,mBreedWight.getText().toString(),age,addTime,
                    ((Item)mBreedSex.getSelectedItem()).getValue(),NoEarTagBreedId,BreedId,MyApp.Userid).enqueue(new MyCallBack<addEarTag>() {
                @Override
                public void success(addEarTag addEarTag) {
                    /**在线添加成功的耳标，在数据库力删除**/
                    earTagDao.deleteByKey(addEarTag.getEartag_id());
                    EventBus.getDefault().post(new EventMessage(1, userid));
                    ActivityManagerUtil.getAppManager().finishActivity();
                }
                @Override
                public void failure(String msg) {
                    showToast(msg);
                }
            });
        }else {
            showToast("请选择耳标！");
        }

    }


    /**
     * 上传照片
     */
    private void getpic() {
        final Intent intent = new Intent(addEarActivity.this, ImageGridActivity.class);
        List<String> picname = new ArrayList<>();
        picname.add("拍照");
        picname.add("相册");
        picname.add("浏览照片");
        showDialog(new SelectDialog.SelectDialogListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        intent.putExtra(ImageGridActivity.EXTRAS_TAKE_PICKERS, true); // 是否是直接打开相机
                        startActivityForResult(intent, IMAGE_PICKER);
                        break;
                    case 1:
                        Intent intent1 = new Intent(addEarActivity.this, ImageGridActivity.class);
                        startActivityForResult(intent1, IMAGE_PICKER);
                    case 2:
                       bundle.putString("img",imagPath);
                       GetBigPicActivity.instance(addEarActivity.this,GetBigPicActivity.class,bundle);
                       break;
                    default:
                        break;
                }
            }
        },picname);
    }

    /**获取压缩图片路径**/
    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true)
    public void onEvent(EventMessage eventMessage){
        if (eventMessage.getMsg()==PicByLuban.GET_PATH){
            imgPath = eventMessage.getMessage();
            Log.e("imgPath",imgPath);
        }

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
                Imgs imgs = new Imgs();
                imgs.setPath(img_);
                imgs.setUser_id(userid);
                imgs.setStutas(DelFile.CAMERO_FILE.ordinal());
                imgsDao.insertOrReplace(imgs);
                Log.e("img_",img_);
                /**
                 * 鲁班压缩图片
                 */
                lubanMethod(img_);
            } else {
                showToast("无数据");
            }
        }
    }
    /**
     * 图片压缩
     * @param file
     */
    private void lubanMethod(String file){
        Luban.with(this)//上下文对象
                .load(file)//原图路径
                .ignoreBy(100)//不压缩的阈值 单位为K 即图片小于此阈值时不压缩
                .setFocusAlpha(false)//设置是否保留透明通道
                .setTargetDir(setLuBanPath())//设置缓存压缩图片路径
                .filter(new CompressionPredicate() {
                    @Override
                    public boolean apply(String path) {
                        return !(TextUtils.isEmpty(path));//图片路径不为空 返回true压缩
                    }
                })//设置开启压缩条件
                .setCompressListener(new OnCompressListener() {
                    @Override
                    public void onStart() {
                        Log.e("start","开始压缩！");
                    }

                    @Override
                    public void onSuccess(File file) {
                        Log.e("path","压缩成功后的路径:"+file.getAbsolutePath());
                        imgPath = file.getAbsolutePath();
                        Imgs imgs = new Imgs();
                        imgs.setPath(imgPath);
                        imgs.setUser_id(userid);
                        imgs.setStutas(DelFile.COMPRESS_FILE.ordinal());
                        imgsDao.insertOrReplace(imgs);
                        Glide.with(addEarActivity.this).load(imgPath).into(mBreedHeadIcon);
                        if (NetworkUtils.isConnected()){
                            upLodPic(imgPath);
                        }else {
                            imagPath = imgPath;
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                })//压缩回调接口
                .setRenameListener(new OnRenameListener() {
                    @Override
                    public String rename(String filePath) {
                        String result = BaseUtils.getlinkNo() + ".jpg";
                        return result;
                    }
                })//压缩前重命名接口
                .launch();//压缩
    }

    /**
     * 压缩图片保存地址
     * @return
     */
    private String setLuBanPath() {
        String path = Environment.getExternalStorageDirectory() + "/Fengtai/image/breeds";
        File file = new File(path);
        if (file.mkdirs()) {
            return path;
        }
        return path;
    }

    /**
     * 上传照片
     * @param img
     */
    private void upLodPic(final String img) {
        dialogUtils.showProgress(addEarActivity.this,"正在上传图片.....");
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
     * 获取耳标数据源
     */
    private void initEarData() {
        /**
         * 有网络
         */
        if (NetworkUtils.isConnected()) {
            RetrofitHelper.getApiService().getEarTag(userid, NORMAL).enqueue(new MyCallBack<ArrayList<EarTag>>() {
                @Override
                public void success(ArrayList<EarTag> earTags) {
                    initAdapter(earTags);
                }

                @Override
                public void failure(String msg) {
                    showToast(msg);
                }
            });
            /**
             * 无网络
             */
        }else {
            earTags = earTagDao.queryBuilder().where(EarTagDao.Properties.User_id.eq(userid)).list();
            initAdapter(earTags);
        }
    }

    /**
     * 给adapter数据源
     * @param earTag
     */
    private void initAdapter(final List<EarTag> earTag) {
        mEarDrawerList.setLayoutManager(gridLayoutManager);
        earTagAdapter = new EarTagAdapter(R.layout.item_ear_tag,earTag);
        mEarDrawerList.setAdapter(earTagAdapter);
        earTagAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, final int position) {
                EarTag selectedTags = earTagAdapter.getData().get(position);
                eraCode = selectedTags.getNumber();
                mDrawerEarTagSearch.setText(selectedTags.getNumber());
                /**获取被选的耳标id**/
                earTagId = selectedTags.getId();
                Log.e("eratTagId",earTagId);
                earTag.clear();
                earTag.add(selectedTags);
                earTagAdapter.setNewData(earTag);
            }
        });
        List<EarTag> earTags = earTagAdapter.getData();
        if (earTags.size()!= 0){
            Log.e("insert",mDrawerEarTagSearch.getText().toString().trim());
            earTagAdapter.eartagserch(mDrawerEarTagSearch,earTags);
        }

    }

    /**
     * 照片选项显示dilog
     * @param listener
     * @param names
     * @return
     */
    private SelectDialog showDialog(SelectDialog.SelectDialogListener listener, List<String> names) {
        SelectDialog dialog = new SelectDialog(addEarActivity.this, R.style
                .transparentFrameWindowStyle,
                listener, names);
        if (!addEarActivity.this.isFinishing()) {
            dialog.show();
        }
        return dialog;
    }

    /**
     * 初始化下拉Spinner
     */
    public void initSpinner(){
        ArrayList<Item> relations = new ArrayList<>();
        relations.add(new Item("母", "2"));
        relations.add(new Item("公", "1"));
        mBreedSex.setAdapter(new SpinnerAdapter(addEarActivity.this,android.R.layout.simple_list_item_1, relations));
    }


    @Override
    protected void onPause() {
        super.onPause();
        NetStateChangeReceiver.unRegisterObserver(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        NetStateChangeReceiver.registerObserver(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        NetStateChangeReceiver.unRegisterReceiver(this);
    }

    @Override
    public void onNetDisconnected() {
        Log.e("NONET","No_NET");
    }
    @Override
    public void onNetConnected(NetworkType networkType) {
        if (networkType.ordinal() == NetworkType.NETWORK_WIFI.ordinal()||networkType.ordinal() ==NetworkType.NETWORK_4G.ordinal()
                ||networkType.ordinal()==NetworkType.NETWORK_3G.ordinal()){
            getEarTag();
        }else if (networkType.ordinal()==NetworkType.NETWORK_NO.ordinal()){
            if (earTags.size()!=0){
                earTags.clear();
            }
            earTags = earTagDao.queryBuilder().where(EarTagDao.Properties.User_id.eq(userid)).list();
            if (earTags.size()!=0){
                initAdapter(earTags);
            }else {
                showToast("没有耳标可添加,请连接网络重新获取新数据！");
            }
        }
    }

    /**监听搜索输入框**/
    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        if (v.getId() ==R.id.drawer_ear_tag_search){

        }
    }
}
