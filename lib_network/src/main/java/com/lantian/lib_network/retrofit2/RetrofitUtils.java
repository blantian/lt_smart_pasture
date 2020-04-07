package com.lantian.lib_network.retrofit2;

import com.lantian.lib_base.utils.Utils;
import com.lantian.lib_network.common.Constants;
import com.lantian.lib_network.interceptor.HttpCacheInterceptor;
import com.lantian.lib_network.interceptor.HttpHeaderInterceptor;
import com.lantian.lib_network.interceptor.LoggingInterceptor;

import java.io.File;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Sherlock·Holmes on 2020-03-06
 */
public class RetrofitUtils {
    public static OkHttpClient.Builder getOkHttpClientBuilder() {
       File cacheFile = new File(Utils.getContext().getCacheDir(), "cache");
        //100Mb
        Cache cache = new Cache(cacheFile, 1024 * 1024 * 100);
        return new OkHttpClient.Builder()
                .readTimeout(Constants.DEFAULT_TIMEOUT, TimeUnit.MILLISECONDS)
                .connectTimeout(Constants.DEFAULT_TIMEOUT, TimeUnit.MILLISECONDS)
                //.addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .addInterceptor(new LoggingInterceptor())
                .retryOnConnectionFailure(true)
                .addInterceptor(new HttpHeaderInterceptor())
                .addInterceptor(new HttpCacheInterceptor())
                // https认证 如果要使用https且为自定义证书 可以去掉这两行注释，并自行配制证书。
                // .sslSocketFactory(SslContextFactory.getSSLSocketFactoryForTwoWay())
                // .hostnameVerifier(new SafeHostnameVerifier())
                .cache(cache);
    }

    public static Retrofit.Builder getRetrofitBuilder(String baseUrl) {
        OkHttpClient okHttpClient = getOkHttpClientBuilder().build();
        return new Retrofit.Builder()
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(baseUrl);

    }
}
