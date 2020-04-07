package com.lantian.lib_network.utils;

import android.util.Log;

import com.lantian.lib_base.entity.module.response.regist.CheckPhoneResponse;
import com.lantian.lib_base.entity.module.response.regist.CheckUsernameResponse;
import com.lantian.lib_network.retrofit2.RetrofitHelper;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Sherlock·Holmes on 2020-03-20
 */
public class CheckMsg {

    private static CheckMsg checkMsg;
    public static CheckMsg getCheckMsg(){
        if (checkMsg == null){
            checkMsg = new CheckMsg();
        }
        return checkMsg;
    }
    String mssege = "";

    /**
     *
     * @param phone
     */
    public String CheckPhone(String phone) {
        RetrofitHelper.getApiService().getPhone(phone).enqueue(new Callback<CheckPhoneResponse>() {
            @Override
            public void onResponse(Call<CheckPhoneResponse> call, Response<CheckPhoneResponse> response) {
                if (response.body().getCode()== 400){
                    mssege = response.body().getMessage();
                }else {
                    mssege = response.body().getMessage();
                }
            }
            @Override
            public void onFailure(Call<CheckPhoneResponse> call, Throwable t) {
                t.printStackTrace();
            }
        });
        return mssege;
    }

    /**
     *
     * @param username
     */
    public String CheckUser(String username) {
        RetrofitHelper.getApiService().getUsername(username).enqueue(new Callback<CheckUsernameResponse>() {
            @Override
            public void onResponse(Call<CheckUsernameResponse> call, Response<CheckUsernameResponse> response) {
              mssege = response.body().getMessage();
                Log.e("mssege",mssege);
            }
            @Override
            public void onFailure(Call<CheckUsernameResponse> call, Throwable t) {
                t.printStackTrace();
            }
        });
        return mssege;
    }
}
