package com.lantian.lib_base.database.greendao;

import com.google.gson.Gson;

import org.greenrobot.greendao.converter.PropertyConverter;

/**
 * Created by Sherlock·Holmes on 2020/4/30
 */
public class TypeObjectConverter implements PropertyConverter<Object, String> {
    @Override
    public Object convertToEntityProperty(String databaseValue) {
        if (databaseValue ==null){
            return "";
        }
        return new Gson().fromJson(databaseValue, Object.class);
    }

    @Override
    public String convertToDatabaseValue(Object entityProperty) {
        if (entityProperty == null){
            return "";
        }
        return new Gson().toJson(entityProperty);
    }
}
