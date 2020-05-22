package com.lantian.lib_docs.breeddoc.dabiaoyonghu;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.hdhe.uhf.reader.Tools;
import com.android.hdhe.uhf.reader.UhfReader;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.internal.LinkedTreeMap;
import com.lantian.lib_base.MyApp;
import com.lantian.lib_base.database.greendao.BreedsListDao;
import com.lantian.lib_base.database.greendao.editEarTagDao;
import com.lantian.lib_base.database.syncdata.SyncDataStatus;
import com.lantian.lib_base.entity.module.response.breeds.BreedIndex;
import com.lantian.lib_base.entity.module.response.breeds.BreedsList;
import com.lantian.lib_base.entity.module.response.breeds.editEarTag;
import com.lantian.lib_base.thread.ThreadPoolManager;
import com.lantian.lib_base.type.NullStringEmptyTypeAdapterFactory;
import com.lantian.lib_base.utils.EventMessage;
import com.lantian.lib_base.utils.BaseUtils;
import com.lantian.lib_commin_ui.base.ActivityManagerUtil;
import com.lantian.lib_commin_ui.base.BaseActivity;
import com.lantian.lib_commin_ui.dialog.AlertDialogUtil;
import com.lantian.lib_network.receiver.NetStateChangeReceiver;
import com.lantian.lib_network.receiver.interfaces.NetStateChangeObserver;
import com.lantian.lib_network.utils.NetworkType;
import com.lantian.lib_commin_ui.utils.NotificationUtils;
import com.lantian.lib_docs.R;
import com.lantian.lib_docs.breeddoc.dabiaoyonghu.adapter.EarCodeAdapter;
import com.lantian.lib_docs.service.SynDataService;
import com.lantian.lib_network.dialog.DialogUtils;
import com.lantian.lib_network.retrofit2.MyCallBack;
import com.lantian.lib_network.retrofit2.RetrofitHelper;
import com.lantian.lib_network.utils.NetworkUtils;
import com.lantian.lib_uhf.SoundUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sherlock·Holmes on 2020-04-01
 * @author lantianbao
 */
public class EarCodeActivity extends BaseActivity implements View.OnClickListener, NetStateChangeObserver {

    private ImageView mEarTagBtnBack;
    private TextView SynData;
    private TextView mDabiaoBreedName;
    private TextView mDabiaoAddBreed;
    private TextView mTagNum;
    private EditText mEarTagSearch;
    private TextView mBtnSearch;
    private Button mBttnReadEarCode;
    private Button mBttnClearEarCode;
    private TextView mBtnSex;
    private TextView mBtnBreeding;
    private TextView mBtnWight;
    private RecyclerView mEarBreedsList;
    private GridLayoutManager gridLayoutManager;
    private Bundle bundle;
    private String breedName;
    private LinearLayout mAddBreedEar;
    private EarCodeAdapter earCodeAdapter;
    private BreedsList breedIndex;
    private List<BreedsList> haveEarTagbreeds;
    private List<BreedsList> NoEarTagBreeds;
    private BreedsListDao breedsListDao;
    private editEarTagDao mEditEarTagDao;
    private List<editEarTag> mEditeEarTag;
    private String userid;
    private String id;
    private String noEarTag = "0";
    private String NoEarTagBreedId;
    private int status = 0;
    private int remainNum;
    private DialogUtils dialogUtils;
    private static Handler handler;
    private UhfReader reader;
    private List<byte[]> epcList;
    private boolean startFlag = false;
    private int outputpw = 26;
    private String msgshow ="请输入准确的耳标号吗!";


    @Override
    protected int getLayoutId() {
        return R.layout.activity_dabiao_add_ear_tag;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        NetStateChangeReceiver.registerReceiver(this);
        initView();
    }

    private void initView() {
        /**页面刷新回调**/
        EventBus.getDefault().register(this);
        /**初始化声音池**/
        SoundUtil.initSoundPool(EarCodeActivity.this);
        /**初始化高频读取**/
        reader = UhfReader.getInstance();
        if (reader !=null){
            reader.setOutputPower(outputpw);
        }
        dialogUtils = new DialogUtils();
        bundle = getIntent().getExtras();
        mEarBreedsList = findViewById(R.id.ear_breeds_list);
        mEarTagBtnBack = findViewById(R.id.ear_tag_btn_back);
        mDabiaoBreedName = findViewById(R.id.ear_breed_name);
        mTagNum = findViewById(R.id.tag_num);
        mEarTagSearch = findViewById(R.id.ear_tag_search);
        mBtnSearch = findViewById(R.id.btn_search);
        mBttnReadEarCode = findViewById(R.id.bttn_read_ear_code);
        mBttnClearEarCode = findViewById(R.id.bttn_clear_ear_code);
        mBtnSex = findViewById(R.id.btn_sex);
        mBtnBreeding = findViewById(R.id.btn_breeding);
        mBtnWight = findViewById(R.id.btn_wight);
        mAddBreedEar = findViewById(R.id.add_breed_ear);
        SynData = findViewById(R.id.syndata);
        mAddBreedEar.setOnClickListener(this);
        mBtnSearch.setOnClickListener(this);
        mBtnWight.setOnClickListener(this);
        mBttnReadEarCode.setOnClickListener(this);
        mBttnClearEarCode.setOnClickListener(this);
        mEarTagBtnBack.setOnClickListener(this);
        SynData.setOnClickListener(this);
        haveEarTagbreeds = new ArrayList<>();
        NoEarTagBreeds = new ArrayList<>();
        mEditeEarTag = new ArrayList<>();
        handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch (msg.what){
                    case 1:
                        mEarTagSearch.setText(String.valueOf(msg.obj));
                    case 2:
                         if (msg.arg1 ==1){
                             earCodeAdapter.setNewData(haveEarTagbreeds);
                         }
                    case 3:
                        showToast(String.valueOf(msg.obj));
                        }

                }


        };

        /**初始化数据库**/
        breedsListDao = ((MyApp) BaseUtils.getContext()).getDaoSession().getBreedsListDao();
        mEditEarTagDao = ((MyApp) BaseUtils.getContext()).getDaoSession().getEditEarTagDao();
        getBundle();
        Net();
    }

    /**
     * 获取上级页面传来的Bundle
     */
    private void getBundle() {
        if (bundle != null){
            Serializable serializable = bundle.getSerializable("BreedIndex");
            breedName = bundle.getString("breed_name");
            mDabiaoBreedName.setText(breedName);
            if (serializable != null) {
                BreedIndex breedIndex = (BreedIndex) serializable;
                userid = breedIndex.getUser_id();
                id = breedIndex.getId();
            }
            if(checkUploud()){
                SynData.setBackground(getResources().getDrawable(R.drawable.ic_action_syndata));
            }else {
                SynData.setBackground(getResources().getDrawable(R.drawable.ic_action_resyndata));
            }
        }
    }

    /**
     * 检查更新数据
     */
    private boolean checkUploud() {
        mEditeEarTag = mEditEarTagDao.queryBuilder().where(mEditEarTagDao.queryBuilder().and(editEarTagDao.Properties.User_id.eq(userid)
                ,editEarTagDao.Properties.Status.eq(SyncDataStatus.ADD.ordinal()))).list();
        if (mEditeEarTag.size()!= 0) {
            return true;
        }else {
            return false;
        }
    }
    /**网络状态**/
    private void Net() {
        if (NetworkUtils.isConnected()){
            getAPIdata();
        }else {
            getDataBaseData();
        }
    }


    /**网络API获取**/
    private void getAPIdata() {
        flatBreedIndex();
        if (NoEarTagBreeds.size()!=0||haveEarTagbreeds.size()!=0){
            NoEarTagBreeds.clear();
            haveEarTagbreeds.clear();
        }
        dialogUtils.showProgress(EarCodeActivity.this,"正在加载数据.....");
        RetrofitHelper.getApiService().getAllBreedIndex(breedIndex.getBreed_id(),breedIndex.getUser_id()).enqueue(new MyCallBack<LinkedTreeMap>() {
            @Override
            public void success(LinkedTreeMap linkedTreeMap) {
                Gson gson = new GsonBuilder().enableComplexMapKeySerialization()
                        .registerTypeAdapterFactory(new NullStringEmptyTypeAdapterFactory()).create();
                LinkedTreeMap<String, LinkedTreeMap> map = linkedTreeMap;
                /**移除数据冗余**/
                map.remove("typet");
                for (Object o:map.keySet()){
                    String toJson = gson.toJson(map.get(o));
                    BreedsList breedIndexList = gson.fromJson(toJson, BreedsList.class);
                    /**分离有耳标和没有耳标的牲畜**/
                    if (breedIndexList.getEartag()!=null||!breedIndexList.getEartag_id().equals("0")){
                        breedIndexList.setStatus(SyncDataStatus.HAVE.ordinal());
                        haveEarTagbreeds.add(breedIndexList);
                    }else if (breedIndexList.getEartag_id().equals(noEarTag)){
                        breedIndexList.setStatus(SyncDataStatus.NONE.ordinal());
                        NoEarTagBreeds.add(breedIndexList);
                    }
                    breedsListDao.insertOrReplaceInTx(breedIndexList);
                }
                /**
                 * remainNum 为未打标的牲畜数量
                 */
                remainNum = NoEarTagBreeds.size();

                mTagNum.setText(String.valueOf(remainNum));
                /**给Adapter数据**/
                initAdapter(haveEarTagbreeds);
                dialogUtils.dismissProgress();
            }
            @Override
            public void failure(String msg) {
            }
        });
    }

    /**
     * earAdapter输入数据源
     * @param breedIndexLists
     */
    private void initAdapter(List<BreedsList> breedIndexLists) {
        Log.e("adapter启动","");
        gridLayoutManager = new GridLayoutManager(this,1);
        mEarBreedsList.setLayoutManager(gridLayoutManager);
        earCodeAdapter = new EarCodeAdapter(R.layout.breed_list_item,breedIndexLists);
        mEarBreedsList.setAdapter(earCodeAdapter);
        //todo 注释图片放大，掌握图片压缩后重新做
//        earCodeAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
//            @Override
//            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
//                String path = earCodeAdapter.getData().get(position).getImg();
//                bundle.putString("img",path);
//                GetBigPicActivity.instance(EarCodeActivity.this,GetBigPicActivity.class,bundle);
//            }
//        });
        earCodeAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                String path = earCodeAdapter.getData().get(position).getImg();
                if (earCodeAdapter.getData().get(position).getEartag()!= null){
                    bundle.putString("Eartag_id",earCodeAdapter.getData().get(position).getEartag_id());
                    bundle.putString("earCode",earCodeAdapter.getData().get(position).getEartag().getNumber());
                }else {
                    showToast("此"+earCodeAdapter.getData().get(position).getBreed().getTitle()+"耳标已被消除");
                }
                bundle.putString("img",path);
                bundle.putString("weight",earCodeAdapter.getData().get(position).getWeight());
                bundle.putString("sex",earCodeAdapter.getData().get(position).getType());
                bundle.putString("userid",earCodeAdapter.getData().get(position).getUser_id());
                bundle.putString("id",earCodeAdapter.getData().get(position).getId());
                MyApp.BreedId = earCodeAdapter.getData().get(position).getBreed_id();
                addEarActivity.instance(EarCodeActivity.this,addEarActivity.class,bundle);
            }
        });
        List<BreedsList> breedsLists = earCodeAdapter.getData();
        if (breedsLists.size()!=0){
            earCodeAdapter.breedsearch(mEarTagSearch,breedsLists);
        }

    }

    /**本地数据库**/
    private void getDataBaseData() {
        /**
         * 获取有耳标的养殖牲畜
         */
        haveEarTagbreeds = breedsListDao.queryBuilder().where(breedsListDao.queryBuilder().and(BreedsListDao.Properties.User_id.eq(MyApp.DaBiaoUserid)
                ,BreedsListDao.Properties.Status.eq(SyncDataStatus.HAVE.ordinal()))).list();
        initAdapter(haveEarTagbreeds);
        /**
         * 获取没有耳标的养殖牲畜
         */
        NoEarTagBreeds = breedsListDao.queryBuilder().where(breedsListDao.queryBuilder().and(BreedsListDao.Properties.User_id.eq(MyApp.DaBiaoUserid)
                ,BreedsListDao.Properties.Status.eq(SyncDataStatus.NONE.ordinal()))).list();
        /**
         * 获取没有耳标的牲畜数量
         */
        remainNum = NoEarTagBreeds.size();
        mTagNum.setText(String.valueOf(remainNum));

    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onClick(View v) {
        /**
         * 获取没有耳标的养殖id
         */
        if (v.getId()==R.id.add_breed_ear){
            for (int i = 0; i< NoEarTagBreeds.size(); i++){
                if (NoEarTagBreeds.get(i).getStatus()==SyncDataStatus.NONE.ordinal()){
                    NoEarTagBreedId = NoEarTagBreeds.get(i).getId();
                    status = i;
                    MyApp.BreedId = NoEarTagBreeds.get(i).getBreed_id();
                    break;
                }
            }
            /**没有耳标的牲畜id**/
            bundle.putString("NoEarTagBreedId", NoEarTagBreedId);
            Log.e("NoEarTagBreedId",NoEarTagBreedId);
            addEarActivity.instance(BaseUtils.getContext(),addEarActivity.class,bundle);

        }if (v.getId() == R.id.btn_search){
            search();
        }if (v.getId()==R.id.btn_sex){
            descBySex();
        }if (v.getId()==R.id.btn_wight){
            descByWight();
        }if (v.getId() == R.id.bttn_read_ear_code){
            redUhf();
            search();
        }if (v.getId() == R.id.bttn_clear_ear_code){
            mEarTagSearch.setText("");
        }if (v.getId() == R.id.ear_tag_btn_back){
            ActivityManagerUtil.getAppManager().finishActivity();
            /**
             * 数据同步
             */
        }if (v.getId() == R.id.syndata){
            uploud();
        }
    }

    /**
     * 数据同步
     */
    public void uploud(){
        String[] names = new String[]{"更新数据","取消"};
        AlertDialogUtil.TwoChoiceDialog(EarCodeActivity.this, "友情提示", "离线有数据更新：" + mEditeEarTag.size() + "条数据", names, new AlertDialogUtil.TwoChoiceHandle() {
            @Override
            public void onPositiveButtonHandle() {
                if (NetworkUtils.isConnected()){
                    if (mEditeEarTag.size() != 0){
                        Intent intent = new Intent(EarCodeActivity.this, SynDataService.class);
                        intent.setAction(SynDataService.SYNDATA);
                        intent.putExtra("userid",userid);
                        startService(intent);
                    }else {
                        AlertDialogUtil.warningDialog(EarCodeActivity.this,"提示","您暂时没有更新的数据哦！");
                    }
                }else {
                    AlertDialogUtil.warningDialog(EarCodeActivity.this,"提示","您需要连接网络才能上传数据哦！");
                }
            }
            @Override
            public void onNegativeButtonHandle() {

            }
        });
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
                while(startFlag) {
                    epcList = reader.inventoryRealTime(); //实时盘存
                    if (epcList != null && !epcList.isEmpty()) {
                        //播放提示音
                        SoundUtil.play(1, 0);
                        for (byte[] epc : epcList) {
                            //将Byte转换为16进制字符串
                            String epcStr = Tools.Bytes2HexString(epc, epc.length);
                            reader.stopInventoryMulti();
                             Message message = Message.obtain();
                             message.what = 1;
                             message.obj = epcStr;
                             handler.sendMessage(message);
                            Log.e("EPC码:", epcStr);
                        }
                        startFlag = false;
                    }
                    epcList = null;
                    try {
                        Thread.sleep(40);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
              }
         });
        }else {
            setButtonClickable(mBttnReadEarCode, false);
            setButtonClickable(mBttnClearEarCode,false);
        }
    }

    private void setButtonClickable(Button button, boolean flag) {
        button.setClickable(flag);
        if (flag) {
            button.setTextColor(Color.BLACK);
        } else {
            button.setTextColor(Color.GRAY);
        }
    }

    /**
     * 按重量降序排序
     */
    private void descByWight() {
        earCodeAdapter.getData().clear();
        haveEarTagbreeds.clear();
        haveEarTagbreeds =breedsListDao.queryBuilder().where(breedsListDao.queryBuilder()
                .and(BreedsListDao.Properties.User_id.eq(MyApp.DaBiaoUserid),BreedsListDao.Properties.Status.eq(SyncDataStatus.HAVE.ordinal()))).orderAsc(BreedsListDao.Properties.Weight).list();
        earCodeAdapter.setNewData(haveEarTagbreeds);
    }
    /**
     * 性别排序
     */
    private void descBySex() {

    }
    /**
     * 搜索
     */
    private void search() {
        ThreadPoolManager.getInstance().execute(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(100);
                    if (earCodeAdapter.getData() != null && earCodeAdapter.getData().size()!=0){
                        for (int i = 0;i<earCodeAdapter.getData().size();i++){
                            String earTag = earCodeAdapter.getData().get(i).getEartag().getNumber();
                            if (mEarTagSearch.getText().toString().trim()
                                    .equals(earTag)){
                                BreedsList breedsList = earCodeAdapter.getItem(i);
                                System.out.print(breedsList);
                                haveEarTagbreeds.clear();
                                haveEarTagbreeds.add(breedsList);
                                Message message = Message.obtain();
                                message.what=2;
                                message.arg1 = 1;
                                handler.sendMessage(message);
                            }else {
                                Message message = Message.obtain();
                                message.what =3;
                                message.obj = msgshow;
                                handler.sendMessage(message);
                            }
                        }
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        });

    }

    private void flatBreedIndex(){
        breedIndex = new BreedsList();
        breedIndex.setUser_id(userid);
        breedIndex.setBreed_id(id);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        NetStateChangeReceiver.unRegisterReceiver(this);
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void refreshUiEventBus(EventMessage eventMessage){
        /**api刷新数据**/
        if (eventMessage.getMsg() == 1){
            Log.e("ceshi",String.valueOf(eventMessage.getMsg()));
            getAPIdata();
            /**本地刷新数据**/
        }else if (eventMessage.getMsg() ==2){
            NoEarTagBreeds.get(status).setStatus(SyncDataStatus.HAVE.ordinal());
            getDataBaseData();
            if(checkUploud()){
                SynData.setBackground(getResources().getDrawable(R.drawable.ic_action_syndata));
            }else {
                SynData.setBackground(getResources().getDrawable(R.drawable.ic_action_resyndata));
            }
        }else if (eventMessage.isUpdataCom()){
            if(checkUploud()){
                SynData.setBackground(getResources().getDrawable(R.drawable.ic_action_syndata));
            }else {
                SynData.setBackground(getResources().getDrawable(R.drawable.ic_action_resyndata));
            }
            getAPIdata();

        }
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
    public void onNetDisconnected() {
        Log.e("Net","没有网络！");
    }

    @Override
    public void onNetConnected(NetworkType networkType) {
        if (networkType.ordinal() == NetworkType.NETWORK_WIFI.ordinal()||networkType.ordinal() ==NetworkType.NETWORK_4G.ordinal()
        ||networkType.ordinal()==NetworkType.NETWORK_3G.ordinal()){
            Log.e("Net","WIfi/4G");
                if(checkUploud()){
                    NotificationUtils.sendNotification(EarCodeActivity.this,"您网络良好，有更新数据哦",false);
                    SynData.setBackground(getResources().getDrawable(R.drawable.ic_action_syndata));
                }else {
                    SynData.setBackground(getResources().getDrawable(R.drawable.ic_action_resyndata));
                }
        }
    }
}
