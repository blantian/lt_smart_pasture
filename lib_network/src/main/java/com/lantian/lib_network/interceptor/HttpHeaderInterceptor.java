package com.lantian.lib_network.interceptor;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Sherlock·Holmes on 2020-03-20
 */
public class HttpHeaderInterceptor implements Interceptor {
    @NotNull
    @Override
    public Response intercept(@NotNull Chain chain) throws IOException {
        Request request = chain.request().newBuilder()
                .header("Content-Type", "application/x-www-form-urlencoded")
                .header("Accept-Encoding","gzip, deflate")
                .header("Content-Disposition", "form-data")
                .addHeader("Connection","keep-alive")
                .addHeader("Accept","application/json, text/plain, */*")
                .build();
        return chain.proceed(request);
    }
}
