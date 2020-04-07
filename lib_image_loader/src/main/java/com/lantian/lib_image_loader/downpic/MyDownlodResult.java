package com.lantian.lib_image_loader.downpic;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by Sherlock·Holmes on 2020-03-25
 */
public abstract class MyDownlodResult<T> implements Observer<T> {
    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onNext(T t) {
        downLodSuccess(t);
    }

    @Override
    public void onError(Throwable e) {

    }

    @Override
    public void onComplete() {

    }

    /**
     * 图片下载成功后回调
     * @param respones
     */
    abstract public void downLodSuccess(T respones);
}
