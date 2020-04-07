package com.lantian.lib_base;

import android.app.Activity;
import android.app.Application;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.isseiaoki.simplecropview.FreeCropImageView;
import com.lantian.lib_base.database.greendao.DaoMaster;
import com.lantian.lib_base.database.greendao.DaoSession;
import com.lantian.lib_base.database.greendao.GreenDaoUpgradeHelper;
import com.lantian.lib_base.utils.Utils;
import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.loader.ImageLoader;
import com.lzy.imagepicker.view.CropImageView;

import java.io.File;

/**
 * Created by Sherlock·Holmes on 2020-03-06
 */
public class MyApp extends Application {

    public static String Userid="" ;
    public static String isAdmin ="";
    private DaoSession daoSession;

    @Override
    public void onCreate() {
        super.onCreate();
        Utils.init(this);
        initDataBase();
        initImagePicker();
    }


    public DaoSession getDaoSession() {
        return daoSession;
    }
    /**
     * 数据库初始化
     */
    private void initDataBase() {
        GreenDaoUpgradeHelper helper = new GreenDaoUpgradeHelper(this, "fengtai.db");
        SQLiteDatabase db = helper.getWritableDatabase();
        DaoMaster daoMaster = new DaoMaster(db);
        daoSession = daoMaster.newSession();
    }

    /**
     * 图像选择器
     */
    private static void initImagePicker() {
        //图像选择器
        ImagePicker imagePicker = ImagePicker.getInstance();
        imagePicker.setImageLoader(new ImageLoader() {
            @Override
            public void displayImage(Activity activity, String path, ImageView imageView, int width, int height) {
                Glide.with(activity)                             //配置上下文
                        .load(new File(path))      //设置图片路径(fix #8,文件名包含%符号 无法识别和显示)
                        .into(imageView);

            }

            @Override
            public void displayImagePreview(Activity activity, String path, ImageView imageView, int width, int height) {
                Glide.with(activity)                             //配置上下文
                        .load(Uri.fromFile(new File(path)))      //设置图片路径(fix #8,文件名包含%符号 无法识别和显示)
                        .into(imageView);
            }

            @Override
            public void clearMemoryCache() {

            }
        });
        imagePicker.setMultiMode(false);
        //显示拍照按钮
        imagePicker.setShowCamera(true);
        //允许裁剪（单选才有效）
        imagePicker.setCrop(true);
        //新版添加,自由裁剪，优先于setCrop
        imagePicker.setFreeCrop(true, FreeCropImageView.CropMode.FREE);
        //是否按矩形区域保存
        imagePicker.setSaveRectangle(true);
        //裁剪框的形状
        imagePicker.setStyle(CropImageView.Style.RECTANGLE);
        //裁剪框的宽度。单位像素（圆形自动取宽高最小值）
        imagePicker.setFocusWidth(800);
        //裁剪框的高度。单位像素（圆形自动取宽高最小值）
        imagePicker.setFocusHeight(800);
        //保存文件的宽度。单位像素
        imagePicker.setOutPutX(1000);
        //保存文件的高度。单位像素
        imagePicker.setOutPutY(1000);
    }





}
