package com.lantian.lib_network.converter;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.lantian.lib_network.BuildConfig;
import com.lantian.lib_network.common.BasicResponse;
import com.lantian.lib_network.common.ErroCode;
import com.lantian.lib_network.exception.ApiException;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Converter;

/**
 * Created by Sherlock·Holmes on 2020/4/6
 */
public class MyGsonResponseBodyConverter<T> implements Converter<ResponseBody, T> {
    private final Gson gson;
    private final TypeAdapter<T> adapter;

    MyGsonResponseBodyConverter(Gson gson, TypeAdapter<T> adapter) {

        this.gson = gson;
        this.adapter = adapter;
    }

    @Override public T convert(ResponseBody value) throws IOException {
      String response = value.string();
      if (BuildConfig.DEBUG){
          Log.e(BuildConfig.APPLICATION_ID, response);
      }
        BasicResponse basicResponse = gson.fromJson(response,BasicResponse.class);
      if (basicResponse.getCode() != ErroCode.SUCCESS || basicResponse.getStatus() != ErroCode.SUCCESS){
          value.close();
          int code;
          if (basicResponse.getStatus()!= 0){
              code = basicResponse.getCode();
          }else if (basicResponse.getCode() != 0){
              code = basicResponse.getStatus();
          }

          throw new ApiException(basicResponse.getStatus(),basicResponse.getMessage());
      }
        try {
            return adapter.fromJson(response);
        } finally {
            value.close();
        }
    }

}
