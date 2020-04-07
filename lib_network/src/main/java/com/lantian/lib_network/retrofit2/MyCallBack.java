package com.lantian.lib_network.retrofit2;

import com.lantian.lib_network.common.BasicResponse;
import com.lantian.lib_network.common.ErroCode;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Sherlock·Holmes on 2020-03-20
 */
public abstract class MyCallBack<T> implements Callback<BasicResponse<T>> {

    public abstract void success(T t);
    public abstract void failure(String msg);
    @Override
    public void onResponse(Call<BasicResponse<T>> call, Response<BasicResponse<T>> response) {
        if (response.body().getStatus()== ErroCode.SUCCESS||response.body().getCode()==ErroCode.SUCCESS){
            success(response.body().getData());
        }else {
            failure(response.body().getMessage());
        }
    }
    @Override
    public void onFailure(Call<BasicResponse<T>> call, Throwable t) {
        t.printStackTrace();
    }
}
