package com.lantian.lib_network.common;

import com.lantian.lib_network.retrofit2.RetrofitUtils;

import retrofit2.Retrofit;

/**
 * Created by Sherlock·Holmes on 2020-03-06
 */
public class IdeaApi {
    public static <T> T getApiService(Class<T> cls, String baseUrl) {
        Retrofit retrofit = RetrofitUtils.getRetrofitBuilder(baseUrl).build();
        return retrofit.create(cls);
    }
}
