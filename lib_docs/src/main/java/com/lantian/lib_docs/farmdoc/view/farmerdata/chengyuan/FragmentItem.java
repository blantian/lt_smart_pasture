package com.lantian.lib_docs.farmdoc.view.farmerdata.chengyuan;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lantian.lib_base.MyApp;
import com.lantian.lib_base.database.greendao.PersonListDao;
import com.lantian.lib_base.file.sharedpreferences.SharedPreferencesUtils;
import com.lantian.lib_base.entity.module.response.farmer.butie.ButieList;
import com.lantian.lib_base.entity.module.response.farmer.farmlist.PersonList;
import com.lantian.lib_base.entity.module.response.farmer.plan.CaoyuanList;
import com.lantian.lib_base.utils.BaseUtils;
import com.lantian.lib_base.utils.EventMessage;
import com.lantian.lib_commin_ui.base.BaseFragmen;
import com.lantian.lib_docs.R;
import com.lantian.lib_docs.farmdoc.view.farmerdata.area.FDAreaAddActivity;
import com.lantian.lib_docs.farmdoc.view.farmerdata.area.FDAreaEditActivity;
import com.lantian.lib_docs.farmdoc.view.farmerdata.butie.FDButieAddActivity;
import com.lantian.lib_docs.farmdoc.view.farmerdata.butie.FDButieEditActivity;
import com.lantian.lib_docs.farmdoc.view.farmerdata.chengyuan.adapter.FDButieAdapter;
import com.lantian.lib_docs.farmdoc.view.farmerdata.chengyuan.adapter.FDCaoyuanAdapter;
import com.lantian.lib_docs.farmdoc.view.farmerdata.chengyuan.adapter.FDPersenAdapter;
import com.lantian.lib_docs.farmdoc.view.farmerdata.person.FDpersenADDActivity;
import com.lantian.lib_docs.farmdoc.view.farmerdata.person.FDpersenEditActivity;
import com.lantian.lib_network.retrofit2.MyCallBack;
import com.lantian.lib_network.retrofit2.RetrofitHelper;
import com.lantian.lib_network.utils.NetworkUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sherlock·Holmes on 2020-04-03
 */
public class FragmentItem extends BaseFragmen implements View.OnClickListener {

    private TextView mTitle;
    private TextView mBtnAdd;
    private RecyclerView mFdmemberList;
    private FDPersenAdapter fdPersenAdapter;
    private FDCaoyuanAdapter fdCaoyuanAdapter;
    private FDButieAdapter fdButieAdapter;
    private List<PersonList> mPersonLists;
    private List<ButieList> mButieLists;
    private List<CaoyuanList> mCaoyuanLists;
    private PersonListDao personListDao;
    private SharedPreferencesUtils sharedPreferencesUtils;


    private static final String CEHNGYUAN = "成员信息";
    private static final String CAOYUAN = "草原面积";
    private static final String BUTIE ="牧户补贴";

    private static final String USER_ID = "userid";
    private static final String LAYOUT_TYPE = "layoutType";
    private String userid;
    private int layoutType;

    public static FragmentItem newInstance(int layoutType, String userid) {
        Bundle args = new Bundle();
        args.putString(USER_ID, userid);
        args.putInt(LAYOUT_TYPE, layoutType);
        FragmentItem fragment = new FragmentItem();
        fragment.setArguments(args);
        return fragment;
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fd_item, null);
        initView(view);
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
    }

    private void initView(View view) {
        sharedPreferencesUtils = new SharedPreferencesUtils(getContext());
        personListDao = ((MyApp) BaseUtils.getContext()).getDaoSession().getPersonListDao();
        mPersonLists = new ArrayList<>();
        mCaoyuanLists = new ArrayList<>();
        mButieLists = new ArrayList<>();
        mTitle = view.findViewById(R.id.item_title);
        mBtnAdd = view.findViewById(R.id.btn_add);
        mFdmemberList = view.findViewById(R.id.fdmember_list);
        mBtnAdd.setOnClickListener(this);
        mBtnAdd.setText("添加");
        mFdmemberList.setLayoutManager(new GridLayoutManager(getContext(),1));
        getAPIData();
    }

    /**
     * 获取网络数据
     */
    private void getAPIData(){
        Bundle args = getArguments();
            if (args != null){
                userid = args.getString(USER_ID);
                layoutType = args.getInt(LAYOUT_TYPE);
                initData();

        }
    }

    private void initData(){
        switch (layoutType){
            case 0:
                if (NetworkUtils.isConnected()){
                    getPersenList();
                }else {
                    intDaBase();
                }

                break;
            case 1:
                mTitle.setText("收入支出");
                break;
            case 2:
                getCaoyuanList();
                break;
            case 3:
                getButieList();
                break;
            default:
        }
    }


    /**
     * 页面刷新回调
     * @param eventMessage
     */
    @Subscribe(sticky = true,threadMode = ThreadMode.MAIN)
    public void refreshUiEventBus(EventMessage eventMessage){
        if (eventMessage.getMsg()==1&&eventMessage.getMessage().equals("addOnNet")){
           initData();
        }else if (eventMessage.getMsg()==2&&eventMessage.getMessage().equals("addpersen")){
            intDaBase();
        }
    }


    /**
     * 初始化成员 adapter
     * @param personLists
     */
    private void initPersenAdapter(final List<PersonList> personLists) {
        fdPersenAdapter = new FDPersenAdapter(R.layout.item_fdmember,personLists);
        mFdmemberList.setAdapter(fdPersenAdapter);
        fdPersenAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Bundle bundle =new Bundle();
                bundle.putSerializable("persen",personLists.get(position));
                FDpersenEditActivity.instance(getContext(),FDpersenEditActivity.class,bundle);
            }
        });
    }

    /**
     * 初始化草原  adapter
     * @param caoyuanLists
     */
    private void initCaoyuanAdapter(final ArrayList<CaoyuanList> caoyuanLists){
        fdCaoyuanAdapter = new FDCaoyuanAdapter(R.layout.item_fdmember,caoyuanLists);
        mFdmemberList.setAdapter(fdCaoyuanAdapter);
        fdCaoyuanAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("caoyuan",caoyuanLists.get(position));
                FDAreaEditActivity.instance(getContext(),FDAreaEditActivity.class,bundle);

            }
        });
    }

    /**
     * 初始化补贴 adapter
     * @param butieLists
     */
    private void initButieAdapter(final ArrayList<ButieList> butieLists){
        fdButieAdapter = new FDButieAdapter(R.layout.item_fdmember,butieLists);
        mFdmemberList.setAdapter(fdButieAdapter);
        fdButieAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("butie",butieLists.get(position));
                FDButieEditActivity.instance(getContext(), FDButieEditActivity.class,bundle);
            }
        });
    }

    /**
     * 获取成员列表
     */
    private void getPersenList() {
        if (NetworkUtils.isConnected()){
            mTitle.setText(CEHNGYUAN);
            RetrofitHelper.getApiService().getPersenList(userid).enqueue(new MyCallBack<ArrayList<PersonList>>() {
                @Override
                public void success(ArrayList<PersonList> personLists) {
                    personListDao.insertOrReplaceInTx(personLists);
                    initPersenAdapter(personLists);
                }
                @Override
                public void failure(String msg) {

                }
            });
        }else {
           intDaBase();
        }

    }

    private void intDaBase() {
        userid =(String)sharedPreferencesUtils.getParam("user_id","");
        mTitle.setText(CEHNGYUAN);
        List<PersonList> personLists = personListDao.queryBuilder().where(PersonListDao.Properties.User_id.eq(userid)).list();
        initPersenAdapter(personLists);
    }


    /**
     * 获取草原列表
     */
    private void getCaoyuanList() {
        mTitle.setText(CAOYUAN);
        RetrofitHelper.getApiService().getCaoyuanList(userid).enqueue(new MyCallBack<ArrayList<CaoyuanList>>() {
            @Override
            public void success(ArrayList<CaoyuanList> caoyuanLists) {
                mCaoyuanLists.addAll(caoyuanLists);
                initCaoyuanAdapter(caoyuanLists);
            }
            @Override
            public void failure(String msg) {

            }
        });

    }

    /**
     * 获取补贴列表
     */
    private void getButieList() {
        mTitle.setText(BUTIE);
        RetrofitHelper.getApiService().getButieList(userid).enqueue(new MyCallBack<ArrayList<ButieList>>() {
            @Override
            public void success(ArrayList<ButieList> butieLists) {
                mButieLists.addAll(butieLists);
                initButieAdapter(butieLists);
            }
            @Override
            public void failure(String msg) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btn_add){
            if (mTitle.getText().equals(CEHNGYUAN)){
                FDpersenADDActivity.instance(getContext(),FDpersenADDActivity.class,null);
            }else if (mTitle.getText().equals(CAOYUAN)){
                FDAreaAddActivity.instance(getContext(),FDAreaAddActivity.class,null);
            }else if (mTitle.getText().equals(BUTIE)){
                FDButieAddActivity.instance(getContext(), FDButieAddActivity.class,null);
            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

}
