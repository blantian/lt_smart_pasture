package com.lantian.lib_base.type;

import android.text.TextUtils;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;

/**
 * Created by Sherlock·Holmes on 2020/4/6
 */
public class DataNullAdapter extends TypeAdapter<String> {
    @Override
    public void write(JsonWriter out, String value) throws IOException {
        try {
            if(TextUtils.isEmpty(value)){
                value = "Empty";
            }
            out.value(value);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public String read(JsonReader in) throws IOException {
        try {
            if (in.peek() == JsonToken.BEGIN_OBJECT){
                in.nextString();
                return "Empty";
            }
            if(JsonToken.NULL == in.peek()){
                in.nextNull();
                return "Empty";
            }else {
                return in.nextString();
            }
        }catch (Exception e){

        }
        return "Empty";
    }

}

