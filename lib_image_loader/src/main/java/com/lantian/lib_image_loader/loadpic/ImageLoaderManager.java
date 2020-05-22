package com.lantian.lib_image_loader.loadpic;

import android.annotation.SuppressLint;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.BitmapTransitionOptions;
import com.bumptech.glide.request.RequestOptions;
import com.lantian.lib_image_loader.R;

/**
 * Created by Sherlock·Holmes on 2020-03-05
 */
public class ImageLoaderManager {

    public static ImageLoaderManager instance;
    public static ImageLoaderManager getInstance() {
        if (instance == null){
            synchronized (ImageLoaderManager.class){
                if (instance == null){
                    instance = new ImageLoaderManager();
                }
            }
        }
        return instance;
    }
    /**
     * 为ImageView加载图片
     * @param imageView
     * @param url
     */
   public void displayImageForView(ImageView imageView,String url){
       Glide.with(imageView.getContext())
               .asBitmap()
               .load(url)
               .apply(initCommonRequestOption())
               .transition(BitmapTransitionOptions.withCrossFade())
               .into(imageView);
   }

   @SuppressLint("CheckResult")
   private RequestOptions initCommonRequestOption(){
       RequestOptions options = new RequestOptions();
       options
               .error(R.drawable.ic_action_erro)
               .fallback(R.drawable.ic_action_erro)
               .diskCacheStrategy(DiskCacheStrategy.DATA)
               .skipMemoryCache(false)
               .override(60,60)
               .centerCrop()
               .priority(Priority.NORMAL);

       return options;
   }


}
