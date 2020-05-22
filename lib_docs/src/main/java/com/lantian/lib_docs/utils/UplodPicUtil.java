package com.lantian.lib_docs.utils;

import android.content.Context;

import com.lantian.lib_base.entity.module.response.picture.UplodPic;
import com.lantian.lib_network.dialog.DialogUtils;
import com.lantian.lib_network.retrofit2.MyCallBack;
import com.lantian.lib_network.retrofit2.RetrofitHelper;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * Created by Sherlock·Holmes on 2020/4/22
 */
public class UplodPicUtil {

    /**
     * 上传照片
     * @param img
     */
    private static String imagPath;

    public static String upLodPic(final String img, Context context, final DialogUtils dialogUtils) {
        dialogUtils.showProgress(context,"正在上传图片.....");
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
                imagPath = msg;
            }
        });
        return imagPath;
    }
}
