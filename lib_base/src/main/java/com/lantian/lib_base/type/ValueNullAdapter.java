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
public class ValueNullAdapter extends TypeAdapter<String> {
    @Override
    public void write(JsonWriter out, String value) throws IOException {
        try{
            if (TextUtils.isEmpty(value)){
                value = "Empty";
            }
            out.value(value);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String read(JsonReader in) throws IOException {
        try{
            String value = "Empty";
            if (in.peek() == JsonToken.BEGIN_OBJECT){
                in.nextString();
                return value;
            }
            if (in.peek() == JsonToken.NULL){
                in.nextString();
                return value;
            }else {
                return in.nextString();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Empty";
    }
}
