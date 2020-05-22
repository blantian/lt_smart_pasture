package com.lantian.lib_image_loader.downpic;

import android.content.Context;
import android.util.Log;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.FutureTarget;
import com.lantian.lib_base.MyApp;
import com.lantian.lib_base.database.greendao.ProductResponseDao;
import com.lantian.lib_base.entity.module.response.product.ProductResponse;
import com.lantian.lib_base.file.ExternalStorageUtils;
import com.lantian.lib_base.utils.BaseUtils;
import com.lantian.lib_image_loader.loadpic.ImageLoaderManager;

import java.io.File;
import java.util.ArrayList;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

import static com.lantian.lib_base.file.ExternalStorageUtils.readStream;

/**
 * Created by Sherlock·Holmes on 2020-03-24
 */
public class DownloadPic {

    private ProductResponseDao productResponseDao;
    private static DownloadPic instance;
    private String path;
    public static DownloadPic getInstance(){
        if (instance == null){
            synchronized (ImageLoaderManager.class){
                if (instance == null){
                    instance = new DownloadPic();
                }
            }
        }
        return instance;
    }

    /**
     * 处理产品数据方法
     * @param mproductResponses
     */
    public void DataProcess(final ArrayList<ProductResponse> mproductResponses){

        for (int i = 0;i<mproductResponses.size();i++){
            final String uri = mproductResponses.get(i).getProduct_pic();
            Log.e("product_name:",mproductResponses.get(i).getProduct_name());
            mproductResponses.get(i).setUser_id(MyApp.Userid);
            SaveImage(uri,i,mproductResponses);

            }

    }

    public void SaveImage(final String uri, final int a, final ArrayList<ProductResponse> data){
        final Context context = BaseUtils.getContext();
        productResponseDao = ((MyApp) BaseUtils.getContext()).getDaoSession().getProductResponseDao();
      Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> e) throws Exception {
                FutureTarget<File> futureTarget = Glide.with(context)
                        .asFile()
                        .load(uri)
                        .submit();
                try {
                    File file = futureTarget.get();
                   boolean succ = ExternalStorageUtils.writeExternalStoragePrivate(
                           MyApp.Userid,context,
                             a + ".png", readStream(file.getPath()));
                    if (succ){
                        path = BaseUtils.getFilePath(context, MyApp.Userid + File.separator+ a + ".png");
                    }
                    e.onNext(path);
                } catch (Exception ex) {
                    e.onNext(null);
                }
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MyDownlodResult<String>() {
                    @Override
                    public void downLodSuccess(String respones) {
                        if (data.get(a).getUser_id() != null){
                            data.get(a).setProduct_pic(respones);
                            productResponseDao.insertOrReplaceInTx(data);
                           }
                        }
                });

       }



}
