package com.lantian.lib_docs.breeddoc.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.lantian.lib_base.MyApp;
import com.lantian.lib_base.database.greendao.BreedFindDao;
import com.lantian.lib_base.file.sharedpreferences.SharedPreferencesUtils;
import com.lantian.lib_base.entity.module.response.breeds.BreedFind;
import com.lantian.lib_base.entity.module.response.breeds.BreedIndex;
import com.lantian.lib_base.utils.BaseUtils;
import com.lantian.lib_commin_ui.base.LazyloadFragment;
import com.lantian.lib_commin_ui.dialog.AlertDialogUtil;
import com.lantian.lib_docs.R;
import com.lantian.lib_network.retrofit2.MyCallBack;
import com.lantian.lib_network.retrofit2.RetrofitHelper;
import com.lantian.lib_network.utils.NetworkUtils;

import java.io.Serializable;

/**
 * Created by Sherlock·Holmes on 2020/4/28
 * @author lantianbao
 */
public class BreedBaseMsgFragment extends LazyloadFragment {

    private TextView BreedbaseName ;
    private TextView BasebreedNum  ;
    private TextView BaseMaleNum ;
    private TextView BaseFameleNum ;
    private TextView BaseBreedAge ;
    private TextView BaseBreedTime ;
    private TextView BaseBreedAdress;
    private TextView BaseBreedClass ;
    private TextView GongYingShang ;
    private TextView ZongZhiChu ;
    private ImageView avatar;
    private String breedid;
    private BreedFindDao breedFindDao;
    private SharedPreferencesUtils sharedPreferencesUtils;

    @Override
    protected int setContentView() {
        return R.layout.basemsg_fragment;
    }

    /**
     * 让布局中的view与fragment中的变量建立起映射
     */
    @Override
    protected void initView() {
        breedFindDao = ((MyApp) BaseUtils.getContext()).getDaoSession().getBreedFindDao();
        sharedPreferencesUtils = new SharedPreferencesUtils(getContext());
         BreedbaseName = rootView.findViewById(R.id.breedbase_name);
         BasebreedNum = rootView.findViewById(R.id.basebreed_num);
         BaseMaleNum = rootView.findViewById(R.id.basemale_num);
         BaseFameleNum = rootView.findViewById(R.id.basefamele_num);
         BaseBreedAge = rootView.findViewById(R.id.basebreed_age);
         BaseBreedTime = rootView.findViewById(R.id.basebreed_time);
         BaseBreedAdress = rootView.findViewById(R.id.basebreed_adress);
         BaseBreedClass = rootView.findViewById(R.id.basebreed_class);
         GongYingShang = rootView.findViewById(R.id.gongyingshang);
         ZongZhiChu = rootView.findViewById(R.id.zongzhichu);
         avatar = rootView.findViewById(R.id.avatar);
    }
    /**
     * 加载要显示的数据
     */
    @Override
    protected void lazyLoad() {
        initData();
    }


    private void initData() {
        Bundle bundle = getArguments();
        if ( bundle !=null){
            Serializable serializable = bundle.getSerializable("BreedIndex");
            BreedIndex breedIndex = (BreedIndex) serializable;
            breedid = breedIndex.getId();
        }

        getApiData();

    }

    private void getApiData() {
        if (NetworkUtils.isConnected()){
            RetrofitHelper.getApiService().getBreedFind(breedid, MyApp.Userid).enqueue(new MyCallBack<BreedFind>() {
                @Override
                public void success(BreedFind breedFind) {
                    breedFindDao.insertOrReplace(breedFind);
                    Glide.with(getContext()).load(breedFind.getImg()).into(avatar);
                    BreedbaseName.setText(breedFind.getTitle());
                    BasebreedNum.setText(breedFind.getNumber());
                    BaseMaleNum.setText(breedFind.getGong());
                    BaseFameleNum.setText(breedFind.getMu());
                    BaseBreedAge.setText(breedFind.getAge());
                    BaseBreedTime.setText(breedFind.getBecome_time());
                    BaseBreedClass.setText(breedFind.getVariety());
                    GongYingShang.setText(breedFind.getSupplier());
                    ZongZhiChu.setText(breedFind.getBecome_price());
                    BaseBreedAdress.setText(breedFind.getDizhi());
                }

                @Override
                public void failure(String msg) {
                }
            });
        }else {
            String userid = (String) sharedPreferencesUtils.getParam("user_id","");
            BreedFind breedFind = breedFindDao.queryBuilder().where(breedFindDao.queryBuilder().and(BreedFindDao.Properties.User_id.eq(userid),BreedFindDao.Properties.Id.eq(breedid))).unique();
            if (breedFind!=null){
                Glide.with(getContext()).load(breedFind.getImg()).into(avatar);
                BreedbaseName.setText(breedFind.getTitle());
                BasebreedNum.setText(breedFind.getNumber());
                BaseMaleNum.setText(breedFind.getGong());
                BaseFameleNum.setText(breedFind.getMu());
                BaseBreedAge.setText(breedFind.getAge());
                BaseBreedTime.setText(breedFind.getBecome_time());
                BaseBreedClass.setText(breedFind.getVariety());
                GongYingShang.setText(breedFind.getSupplier());
                ZongZhiChu.setText(breedFind.getBecome_price());
                BaseBreedAdress.setText(breedFind.getDizhi());
            }else {
                AlertDialogUtil.warningDialog(getActivity(),"温馨提示！","您先加载数据哦！");
            }
        }

    }

    public static BreedBaseMsgFragment getInstance(){
        BreedBaseMsgFragment fragment = new BreedBaseMsgFragment();
        return fragment;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
