package com.lantian.lt_smart_pasture.view.mine;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.lantian.lib_base.MyApp;
import com.lantian.lib_base.database.greendao.ImgsDao;
import com.lantian.lib_base.entity.module.response.img.Imgs;
import com.lantian.lib_base.file.DelFile;
import com.lantian.lib_base.file.ExternalStorageUtils;
import com.lantian.lib_base.file.sharedpreferences.SharedPreferencesHelper;
import com.lantian.lib_base.file.sharedpreferences.SharedPreferencesUtils;
import com.lantian.lib_base.thread.ThreadPoolManager;
import com.lantian.lib_base.utils.BaseUtils;
import com.lantian.lib_commin_ui.base.ActivityManagerUtil;
import com.lantian.lib_commin_ui.dialog.AlertDialogUtil;
import com.lantian.lib_image_loader.loadpic.GlideCacheUtil;
import com.lantian.lt_smart_pasture.R;

import java.io.File;
import java.util.List;

/**
 * Created by Sherlock·Holmes on 2020-02-24
 */
public class MineFragment extends Fragment implements View.OnClickListener {

    private ImageView mEarBtnBack;
    private ImageView mUserhead;
    private ImageView mIcHead;
    private TextView mIcHeadName;
    private TextView mWelcomeTo;
    private TextView mPerusername;
    private TextView mBttnFind;
    private TextView mBttnMyMsg;
    private TextView mBttnAboutUs;
    private Button mBttnQuit;
    private ImgsDao imgsDao;
    private String[] buttnStr = {"确定","取消"};
    private SharedPreferencesUtils sharedPreferencesUtils;

    public static Fragment newInstance() {
        MineFragment fragment = new MineFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_mine, null);
        initView(rootView);
        return rootView;
    }

    private void initView(View rootView) {
        sharedPreferencesUtils = new SharedPreferencesUtils(getContext());
        imgsDao = ((MyApp) BaseUtils.getContext()).getDaoSession().getImgsDao();
        mBttnFind = rootView.findViewById(R.id.bttn_find);
        mBttnMyMsg = rootView.findViewById(R.id.bttn_my_msg);
        mBttnAboutUs = rootView.findViewById(R.id.bttn_about_us);
        mBttnQuit = rootView.findViewById(R.id.bttn_quit);

        mBttnQuit.setOnClickListener(this);
        mBttnAboutUs.setOnClickListener(this);
        mBttnMyMsg.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.bttn_quit){
            AlertDialogUtil.TwoChoiceDialog(getActivity(), "退出程序", "您是否退出程序？",
                    buttnStr, new AlertDialogUtil.TwoChoiceHandle() {
                        @Override
                        public void onPositiveButtonHandle() {
                            /**清除图片缓存**/
                            GlideCacheUtil.getInstance().cleanCatchDisk(getContext());
                            SharedPreferencesHelper.clear(getContext());
                            delimg();
                            ActivityManagerUtil.getAppManager().AppExit(getContext());
                        }
                        @Override
                        public void onNegativeButtonHandle() {
                        }
                    });

        }else if (v.getId() == R.id.bttn_find){
            findActivity.instance(getContext(),findActivity.class,null);
        }else if (v.getId() ==R.id.bttn_about_us){
            AboutAsActiviity.instance(getContext(),AboutAsActiviity.class,null);
        }else if (v.getId() == R.id.bttn_my_msg){
            personalActivity.instance(getContext(),personalActivity.class,null);
        }
    }


    private void delimg() {
        String userid = (String) sharedPreferencesUtils.getParam("db_userid","");
        List<Imgs> imgs = imgsDao.queryBuilder().where(imgsDao.queryBuilder().and(ImgsDao.Properties.User_id.eq(userid),
                ImgsDao.Properties.Stutas.eq(DelFile.CAMERO_FILE.ordinal()))).list();
        ThreadPoolManager.getInstance().execute(new Runnable() {
            @Override
            public void run() {
               // for (int i = 0;i<imgs.size();i++){
                    File file = new File("/storage/emulated/0/Android/data/com.lantian.lt_smart_pasture/files/Pictures/cropTemp/");
                    ExternalStorageUtils.deleteAllFile(file);
               // }
            }
        });
    }
}
