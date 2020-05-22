package com.lantian.lib_image_loader.downpic;

import android.content.Context;
import android.util.Log;

import com.lantian.lib_base.utils.BaseUtils;
import com.lantian.lib_base.utils.EventMessage;

import org.greenrobot.eventbus.EventBus;

import java.io.File;

import top.zibin.luban.Luban;
import top.zibin.luban.OnCompressListener;
import top.zibin.luban.OnRenameListener;

/**
 * Created by Sherlock·Holmes on 2020/4/25
 */
public class PicByLuban {

    private static Context context;
    public static final int GET_PATH = 10;


    /**
     * 鲁班压缩图片
     * @param context
     * @param path
     */
    public static void lubanMethod(final Context context, final String path){
        Luban.with(context)
                .load(path)
                .ignoreBy(100)
                .setFocusAlpha(false)
                .setCompressListener(new OnCompressListener() {
            @Override
            public void onStart() {
                /**压缩开始之前**/
                Log.e("start","开始压缩");
            }

            @Override
            public void onSuccess(File file) {
                /**压缩成功过后**/
                Log.e("path", file.getAbsolutePath());
                EventBus.getDefault().postSticky(new EventMessage(GET_PATH,file.getAbsolutePath()));
            }

            @Override
            public void onError(Throwable e) {
                /**压缩失败**/
                Log.e("erro",e + "");
            }
        }).setRenameListener(new OnRenameListener() {
            @Override
            public String rename(String filePath) {
                String result = BaseUtils.getlinkNo() +"jpg";
                return result;
            }
        }).launch();
    }

}
