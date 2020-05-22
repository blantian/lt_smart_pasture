package com.lantian.lib_docs.breeddoc.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
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
import com.lantian.lib_base.database.syncdata.SyncDataStatus;
import com.lantian.lib_base.entity.module.response.breeds.BreedIndex;
import com.lantian.lib_base.entity.module.response.breeds.BreedsList;
import com.lantian.lib_base.thread.ThreadPoolManager;
import com.lantian.lib_base.type.NullStringEmptyTypeAdapterFactory;
import com.lantian.lib_base.utils.BaseUtils;
import com.lantian.lib_commin_ui.base.ActivityManagerUtil;
import com.lantian.lib_commin_ui.base.LazyloadFragment;
import com.lantian.lib_docs.R;
import com.lantian.lib_docs.breeddoc.GetBigPicActivity;
import com.lantian.lib_docs.breeddoc.dabiaoyonghu.adapter.EarCodeAdapter;
import com.lantian.lib_network.dialog.DialogUtils;
import com.lantian.lib_network.retrofit2.MyCallBack;
import com.lantian.lib_network.retrofit2.RetrofitHelper;
import com.lantian.lib_uhf.SoundUtil;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sherlock·Holmes on 2020/4/28
 */
public class BreedDacMsgFragment extends LazyloadFragment implements View.OnClickListener {



    private ImageView mEarTagBtnBack;
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
    private BreedsList breedIndex;
    private List<BreedsList> haveEarTagbreeds;
    private List<BreedsList> NoEarTagBreeds;
    private BreedsListDao breedsListDao;
    private DialogUtils dialogUtils;
    private static Handler handler;
    private UhfReader reader;
    private List<byte[]> epcList;
    private boolean startFlag = false;
    private static final String noEarTag = "0";
    private static final int outputpw = 26;
    private String breedName;
    private String id;
    private EarCodeAdapter earCodeAdapter;


    @Override
    protected int setContentView() {
        return R.layout.dac_msg_fragment;
    }

    @Override
    protected void initView() {
        bundle = getArguments();
        if (bundle!=null){
            Serializable serializable = bundle.getSerializable("BreedIndex");
            BreedIndex breedIndex = (BreedIndex) serializable;
            id = breedIndex.getId();
        }
        /**页面刷新回调**/
        //EventBus.getDefault().register(this);
        /**初始化声音池**/
        SoundUtil.initSoundPool(getContext());
        /**初始化高频读取**/
        reader = UhfReader.getInstance();
        if (reader !=null){
            reader.setOutputPower(outputpw);
        }
        dialogUtils = new DialogUtils();
        mEarBreedsList = rootView.findViewById(R.id.ear_breeds_list);
        mEarTagBtnBack = rootView.findViewById(R.id.ear_tag_btn_back);
        mEarTagSearch = rootView.findViewById(R.id.ear_tag_search);
        mBtnSearch = rootView.findViewById(R.id.btn_search);
        mBttnReadEarCode = rootView.findViewById(R.id.bttn_read_ear_code);
        mBttnClearEarCode = rootView.findViewById(R.id.bttn_clear_ear_code);
        mBtnSex = rootView.findViewById(R.id.btn_sex);
        mBtnBreeding = rootView.findViewById(R.id.btn_breeding);
        mBtnWight = rootView.findViewById(R.id.btn_wight);

        handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch (msg.what){
                    case 1:
                        mEarTagSearch.setText(String.valueOf(msg.obj));
                }
            }


        };
    }

    @Override
    protected void lazyLoad() {
        initData();
    }


    private void initData() {
       flatBreedIndex();
       haveEarTagbreeds = new ArrayList<>();
       NoEarTagBreeds = new ArrayList<>();
       dialogUtils.showProgress(getContext(),"正在加载...");
       breedsListDao = ((MyApp) BaseUtils.getContext()).getDaoSession().getBreedsListDao();
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

                /**给Adapter数据**/
                initAdapter(haveEarTagbreeds);
                dialogUtils.dismissProgress();
            }
            @Override
            public void failure(String msg) {
            }
        });
    }

    private void flatBreedIndex(){
        breedIndex = new BreedsList();
        breedIndex.setUser_id(MyApp.Userid);
        breedIndex.setBreed_id(id);
    }

    /**
     * earAdapter输入数据源
     * @param breedIndexLists
     */
    private void initAdapter(List<BreedsList> breedIndexLists) {
        Log.e("adapter启动","");
        gridLayoutManager = new GridLayoutManager(getContext(),1);
        mEarBreedsList.setLayoutManager(gridLayoutManager);
        earCodeAdapter = new EarCodeAdapter(R.layout.breed_list_item,breedIndexLists);
        mEarBreedsList.setAdapter(earCodeAdapter);
        //todo 注释图片放大，掌握图片压缩后重新做
        earCodeAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
               String path = earCodeAdapter.getData().get(position).getImg();
               bundle.putString("img",path);
                GetBigPicActivity.instance(getContext(),GetBigPicActivity.class,bundle);
            }});
        earCodeAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                String path = earCodeAdapter.getData().get(position).getImg();
                bundle.putString("img",path);
                bundle.putString("earCode",earCodeAdapter.getData().get(position).getEartag().getNumber());
                bundle.putString("Eartag_id",earCodeAdapter.getData().get(position).getEartag_id());
                bundle.putString("weight",earCodeAdapter.getData().get(position).getWeight());
                bundle.putString("sex",earCodeAdapter.getData().get(position).getType());
                bundle.putString("userid",earCodeAdapter.getData().get(position).getUser_id());
                bundle.putString("id",earCodeAdapter.getData().get(position).getId());
                MyApp.BreedId = earCodeAdapter.getData().get(position).getBreed_id();
                //addEarActivity.instance(getContext(),addEarActivity.class,bundle);
            }
        });
    }

    public static BreedDacMsgFragment getInstance(){
        BreedDacMsgFragment breedDacMsgFragment = new BreedDacMsgFragment();
        return breedDacMsgFragment;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() ==R.id.bttn_read_ear_code){
            redUhf();
        }else if (v.getId() ==R.id.ear_tag_btn_back){
            ActivityManagerUtil.getAppManager().finishActivity();
        }else if (v.getId() ==R.id.bttn_clear_ear_code){
            mBttnClearEarCode.setText("");
        }
    }

    /**
     * 读EPC线程
     * @author Administrator
     */
    private void redUhf() {
        if (reader !=null){
            if (reader ==null){
                Log.e("reader","空");
                setButtonClickable(mBttnReadEarCode, false);
                return;
            }
            if (!startFlag) {
                startFlag = true;
            }
            ThreadPoolManager.getInstance().execute(new Runnable() {
                @Override
                public void run() {
                    if (startFlag) {
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
}
