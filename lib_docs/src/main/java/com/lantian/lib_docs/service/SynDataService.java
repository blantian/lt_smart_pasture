package com.lantian.lib_docs.service;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.util.Log;

import androidx.annotation.Nullable;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.internal.LinkedTreeMap;
import com.lantian.lib_base.MyApp;
import com.lantian.lib_base.database.greendao.BreedIndexDao;
import com.lantian.lib_base.database.greendao.BreedsListDao;
import com.lantian.lib_base.database.greendao.BreedsOfUserDao;
import com.lantian.lib_base.database.greendao.EarTagDao;
import com.lantian.lib_base.database.greendao.PersonListDao;
import com.lantian.lib_base.database.greendao.editEarTagDao;
import com.lantian.lib_base.database.syncdata.SyncDataStatus;
import com.lantian.lib_base.entity.module.response.breeds.BreedIndex;
import com.lantian.lib_base.entity.module.response.breeds.BreedsList;
import com.lantian.lib_base.entity.module.response.breeds.BreedsOfUser;
import com.lantian.lib_base.entity.module.response.breeds.EarTag;
import com.lantian.lib_base.entity.module.response.breeds.addEarTag;
import com.lantian.lib_base.entity.module.response.breeds.editEarTag;
import com.lantian.lib_base.entity.module.response.farmer.farmlist.PersonList;
import com.lantian.lib_base.entity.module.response.picture.UplodPic;
import com.lantian.lib_base.thread.ThreadPoolManager;
import com.lantian.lib_base.type.NullStringEmptyTypeAdapterFactory;
import com.lantian.lib_base.utils.BaseUtils;
import com.lantian.lib_base.utils.EventMessage;
import com.lantian.lib_network.retrofit2.MyCallBack;
import com.lantian.lib_network.retrofit2.RetrofitHelper;

import org.greenrobot.eventbus.EventBus;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * Created by Sherlock·Holmes on 2020/4/18
 */
public class SynDataService extends Service {

    public static final String DOWNALL ="START_SYN";
    public static final String SYNDATA ="FINSH_SYN";
    public static final String CHECK = "CHECK";
    public static final String SYNFARMDATA ="FARM_DATA";
    private ArrayList<String> user_ids;
    private BreedsListDao breedsListDao;
    private BreedsOfUserDao breedsOfUserDao;
    private BreedIndexDao breedIndexDao;
    private EarTagDao earTagDao;
    private editEarTagDao mEditEarTagDao;

    private PersonListDao personListDao;
    private List<BreedsList> breedsLists;
    private List<editEarTag> mEditeEarTag;
    private List<PersonList> personLists;
    private String imagPath = "";
    private String NORMAL = "1";
    private int tag = 0;
    private Handler handler;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e("start","服务启动");
        /**
         * 初始化Dao
         */
        breedsListDao = ((MyApp) BaseUtils.getContext()).getDaoSession().getBreedsListDao();
        breedsOfUserDao = ((MyApp) BaseUtils.getContext()).getDaoSession().getBreedsOfUserDao();
        breedIndexDao = ((MyApp) BaseUtils.getContext()).getDaoSession().getBreedIndexDao();
        earTagDao = ((MyApp) BaseUtils.getContext()).getDaoSession().getEarTagDao();
        mEditEarTagDao = ((MyApp) BaseUtils.getContext()).getDaoSession().getEditEarTagDao();

        personListDao = ((MyApp) BaseUtils.getContext()).getDaoSession().getPersonListDao();

        breedsLists = new ArrayList<>();
        user_ids = new ArrayList<>();
        mEditeEarTag = new ArrayList<>();
        String userid = (String) intent.getExtras().get("userid");
        /**
         * 全部用户数据下载
         */
        if (intent.getAction().equals(DOWNALL)){
            List<BreedsOfUser> breedsOfUsers = breedsOfUserDao.queryBuilder().list();
            for (int i = 0; i < breedsOfUsers.size(); i++) {
                user_ids.add(breedsOfUsers.get(i).getUser_id());
            }
            if (user_ids.size()!=0){
                SynAllUserBreedsMsg();
            }
            new InitThread().start();
            new InitThreadTow().start();
            /**
             * 数据同步
             */
        }else if (intent.getAction().equals(SYNDATA)){
            upLodlocalData(userid,SYNDATA);
            /**
             * 检查是否有同步数据
             */
        }else if (intent.getAction().equals(CHECK)){
            upLodlocalData(userid,CHECK);
        }else if (intent.getAction().equals(SYNFARMDATA)){
            uplodfarmdata();
        }
        handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch (msg.what){
                    case 1:

                }
            }
        };
        return super.onStartCommand(intent, flags, startId);
     }

     /**更新牧户数据**/
    private void uplodfarmdata() {
         personLists = personListDao.queryBuilder().where(personListDao.queryBuilder()
                .and(PersonListDao.Properties.User_id.eq(MyApp.Userid),PersonListDao.Properties.Status.eq(SyncDataStatus.ADD.ordinal()))).list();
         ThreadPoolManager.getInstance().execute(new Runnable() {
             @Override
             public void run() {
                 for (int i =0;i<personLists.size();i++){
                     uplodaddpersens(i);
                 }
             }
         });

    }

    /**人口添加**/
    private void uplodaddpersens(final int i) {
        RetrofitHelper.getApiService().addPersenList(personLists.get(i).getUser_id(),personLists.get(i).getImg(),personLists.get(i).getCall(),
                personLists.get(i).getName(),personLists.get(i).getPer_relations(),personLists.get(i).getTel(),personLists.get(i).getLabour_type()).enqueue(new MyCallBack<PersonList>() {
            @Override
            public void success(PersonList personList) {
                personListDao.insertOrReplace(personList);
                personListDao.deleteByKey(personLists.get(i).getId());
            }
            @Override
            public void failure(String msg) {
            }
        });
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    class InitThread extends Thread{
        @Override
        public void run(){
            try{
                Thread.sleep(5000);
                int a = user_ids.size()/2;
                for (int i =0;i<a;i++){
                    List<BreedIndex> breedIndexList = breedIndexDao.queryBuilder().where(BreedIndexDao.Properties.User_id.eq(user_ids.get(i))).list();
                    for (int j=0;j<breedIndexList.size();j++){
                        RetrofitHelper.getApiService().getAllBreedIndex(breedIndexList.get(j).getId() ,user_ids.get(i)).enqueue(new MyCallBack<LinkedTreeMap>() {
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
                                    if (breedIndexList.getEartag() != null||!breedIndexList.getEartag_id().equals("0")){
                                        breedIndexList.setStatus(SyncDataStatus.HAVE.ordinal());
                                    }else {
                                        breedIndexList.setStatus(SyncDataStatus.NONE.ordinal());
                                    }
                                    breedsLists.add(breedIndexList);
                                   // breedsListDao.insertOrReplaceInTx(breedIndexList);
                                }
                            }

                            @Override
                            public void failure(String msg) {
                            }
                        });
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    class InitThreadTow extends Thread{
        @Override
        public void run(){
            try{
                Thread.sleep(5000);
                int a = user_ids.size()/2;
                for (int i =a;i<user_ids.size();i++){
                    List<BreedIndex> breedIndexList = breedIndexDao.queryBuilder().where(BreedIndexDao.Properties.User_id.eq(user_ids.get(i))).list();
                    for (int j=0;j<breedIndexList.size();j++){
                        RetrofitHelper.getApiService().getAllBreedIndex(breedIndexList.get(j).getId() ,user_ids.get(i)).enqueue(new MyCallBack<LinkedTreeMap>() {
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
                                    if (breedIndexList.getEartag() != null||!breedIndexList.getEartag_id().equals("0")){
                                        breedIndexList.setStatus(SyncDataStatus.HAVE.ordinal());
                                    }else {
                                        breedIndexList.setStatus(SyncDataStatus.NONE.ordinal());
                                    }
                                    breedsLists.add(breedIndexList);
                                    //
                                }
                            }
                            @Override
                            public void failure(String msg) {
                            }
                        });
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    /**
     * 获取对应用户下的所有养殖信息
     */
    private void SynAllUserBreedsMsg(){
        /**
         * 线程1
         * 获取养殖详细信息
         * */
        ThreadPoolManager.getInstance().execute(new Runnable() {
            @Override
            public void run() {
                for (int i = 0;i<user_ids.size();i++){
                    BreedsOfUser breedsOfUser = breedsOfUserDao.queryBuilder().where(BreedsOfUserDao.Properties.User_id.eq(user_ids.get(i))).unique();
                    for (int j=0;j<breedsOfUser.getData().size();j++){
                        if (breedsOfUser.getData().get(j).getCount()!=null){
                            getBreedData(breedsOfUser.getData().get(j).getId(),user_ids.get(i));
                        }
                    }
                }
            }
        });

        /**
         * 线程2
         * 获取所有用户耳标
         */
        ThreadPoolManager.getInstance().execute(new Runnable() {
            @Override
            public void run() {
                for (int i =0  ;i<user_ids.size();i++){
                    insertEarTag(user_ids.get(i));
                }
            }
        });
    }

    /**
     * 获取耳标
     * @param s
     */
    private void insertEarTag(String s) {
        RetrofitHelper.getApiService().getEarTag(s,NORMAL).enqueue(new MyCallBack<ArrayList<EarTag>>() {
            @Override
            public void success(ArrayList<EarTag> earTags) {
                earTagDao.insertOrReplaceInTx(earTags);
            }
            @Override
            public void failure(String msg) {
            }
        });

    }

    /**
     * 获取用户养殖牲畜详细信息
     * @param id
     * @param s
     */
    private void getBreedData(String id, String s) {
        RetrofitHelper.getApiService().getBreedIndex(s,id,"1").enqueue(new MyCallBack<ArrayList<BreedIndex>>() {
            @Override
            public void success(final ArrayList<BreedIndex> breedIndices) {
                breedIndexDao.insertOrReplaceInTx(breedIndices);
            }
            @Override
            public void failure(String msg) {

            }
        });
    }

    /**上传数据到服务器**/
    private void upLodlocalData(String userid, final String status) {

        mEditeEarTag = mEditEarTagDao.queryBuilder().where(mEditEarTagDao.queryBuilder().and(editEarTagDao.Properties.User_id.eq(userid)
                ,editEarTagDao.Properties.Status.eq(SyncDataStatus.ADD.ordinal()))).list();
        final File[] file = new File[mEditeEarTag.size()];
        ThreadPoolManager.getInstance().execute(new Runnable() {
            @Override
            public void run() {
                for (int i =0;i<mEditeEarTag.size();i++){
                    if (mEditeEarTag.get(i).getImg()!=null){
                        file[i] = new File(mEditeEarTag.get(i).getImg());
                    }else {
                        file[i] = new File("");
                    }
                    RetrofitHelper.getApiService().uploadImg(MultipartBody.Part.createFormData("photo", file[i].getName(),
                            RequestBody.create(MediaType.parse("image/*"), file[i]))).enqueue(new MyCallBack<UplodPic>() {
                        @Override
                        public void success(UplodPic uplodPic) {
                            imagPath = uplodPic.getImg();
                        }
                        @Override
                        public void failure(String msg) {
                        }
                    });

                }
            }
        });

        ThreadPoolManager.getInstance().execute(new Runnable() {
            @Override
            public void run() {
                try{
                    Thread.sleep(1500);
                    for (int i = 0;i<mEditeEarTag.size();i++){
                        RetrofitHelper.getApiService().addBreedEarTag(imagPath,mEditeEarTag.get(i).getEartag_id()
                                ,mEditeEarTag.get(i).getWeight(),mEditeEarTag.get(i).getAge(),mEditeEarTag.get(i).getAddtime(),
                                mEditeEarTag.get(i).getType(),mEditeEarTag.get(i).getId(),mEditeEarTag.get(i).getBreed_id(),mEditeEarTag.get(i).getUpdate_userid()).enqueue(new MyCallBack<addEarTag>() {
                            @Override
                            public void success(addEarTag addEarTag) {
                                if (addEarTag !=null){
                                    tag +=1;
                                }
                                /**在线添加成功的耳标，在数据库里删除**/
                                earTagDao.deleteByKey(addEarTag.getId());
                                mEditEarTagDao.deleteByKey(addEarTag.getId());
                                if (tag ==mEditeEarTag.size()) {
                                    EventBus.getDefault().post(new EventMessage(true));
                                    EventBus.getDefault().post(new EventMessage(3,"数据更新完成"));
                                }
                            }
                            @Override
                            public void failure(String msg) {

                            }
                        });
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
