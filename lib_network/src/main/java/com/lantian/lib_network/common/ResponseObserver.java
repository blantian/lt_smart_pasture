package com.lantian.lib_network.common;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.JsonParseException;
import com.lantian.lib_base.utils.ToastUtils;
import com.lantian.lib_network.R;
import com.lantian.lib_network.exception.NoDataExceptionException;
import com.lantian.lib_network.exception.ServerResponseException;

import org.json.JSONException;

import java.io.InterruptedIOException;
import java.net.ConnectException;
import java.text.ParseException;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import retrofit2.HttpException;

/**
 * Created by Sherlock·Holmes on 2020-03-12
 */
public abstract class ResponseObserver<T> implements Observer<T> {
    @Override
    public void onSubscribe(Disposable d) {

    }
    @Override
    public void onNext(T response) {
        onSuccess(response);
        onFinish();
    }
    @Override
    public void onError(Throwable e) {
        /**HTTP错误**/
        if (e instanceof HttpException){
            onException(ExceptionReason.BAD_NETWORK);
        }else
            /**连接错误**/
            if (e instanceof ConnectException){
            onException(ExceptionReason.CONNECT_ERROR);
        }else
            /**连接超时**/
            if (e instanceof InterruptedIOException){
                onException(ExceptionReason.CONNECT_TIMEOUT);
            }else
                /**解析错误**/
                if (e instanceof JsonParseException
                        || e instanceof JSONException
                        || e instanceof ParseException) {
                    Log.e("erro",e +"");
                    onException(ExceptionReason.PARSE_ERROR);
                }else
                    /**服务器返回异常**/
                    if (e instanceof ServerResponseException){
                        onFail(e.getMessage());
                    }
                    else
                        /**服务器没返回数据**/
                        if (e instanceof NoDataExceptionException){
                            onSuccess(null);
                        }
         onFinish();
    }

    @Override
    public void onComplete() {

    }

    /**
     * 服务器返回数据，但响应码不为200
     */
    public void onFail(String message) {
        ToastUtils.show(message);
    }

    /**
     * 请求成功
     * @param response 服务器返回的数据
     */
    abstract public void onSuccess(T response);

    public void onFinish() {

    }

    /**
     * 请求异常
     * @param reason
     */
    public void onException(ExceptionReason reason) {
        switch (reason) {
            case CONNECT_ERROR:
                ToastUtils.show(R.string.connect_error, Toast.LENGTH_SHORT);
                break;

            case CONNECT_TIMEOUT:
                ToastUtils.show(R.string.connect_timeout, Toast.LENGTH_SHORT);
                break;

            case BAD_NETWORK:
                ToastUtils.show(R.string.bad_network, Toast.LENGTH_SHORT);
                break;

            case PARSE_ERROR:
                ToastUtils.show(R.string.parse_error, Toast.LENGTH_SHORT);
                break;

            case UNKNOWN_ERROR:
            default:
                ToastUtils.show(R.string.unknown_error, Toast.LENGTH_SHORT);
                break;
        }
    }

    /**
     * 请求网络失败原因
     */
    public enum ExceptionReason {
        /**
         * 解析数据失败
         */
        PARSE_ERROR,
        /**
         * 网络问题
         */
        BAD_NETWORK,
        /**
         * 连接错误
         */
        CONNECT_ERROR,
        /**
         * 连接超时
         */
        CONNECT_TIMEOUT,
        /**
         * 未知错误
         */
        UNKNOWN_ERROR,
    }

}
