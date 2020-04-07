package com.lantian.lib_network.utils;

import android.app.Activity;

import androidx.annotation.NonNull;

import com.lantian.lib_network.dialog.DialogUtils;
import com.trello.rxlifecycle2.components.support.RxFragmentActivity;

import java.lang.ref.WeakReference;

import io.reactivex.ObservableTransformer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * Created by Sherlock·Holmes on 2020-03-07
 */
public class ProgressUtils {

    public static <T> ObservableTransformer<T, T> applyProgressBar(
            @NonNull final RxFragmentActivity activity, String msg) {
        final WeakReference<Activity> activityWeakReference = new WeakReference<>(activity);
        final DialogUtils dialogUtils = new DialogUtils();
        dialogUtils.showProgress(activityWeakReference.get());
        return upstream -> upstream.doOnSubscribe(disposable -> {

        }).doOnTerminate(() -> {
            Activity context;
            if ((context = activityWeakReference.get()) != null
                    && !context.isFinishing()) {
                dialogUtils.dismissProgress();
            }
        }).doOnSubscribe((Consumer<Disposable>) disposable -> {
        });
    }

    public static <T> ObservableTransformer<T, T> applyProgressBar(
            @NonNull final RxFragmentActivity activity) {
        return applyProgressBar(activity, "");
    }
}
