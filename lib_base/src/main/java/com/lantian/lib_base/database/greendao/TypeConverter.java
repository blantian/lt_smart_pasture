package com.lantian.lib_base.database.greendao;

import com.google.gson.Gson;
import com.lantian.lib_base.entity.module.response.login.LoginResponse;

import org.greenrobot.greendao.converter.PropertyConverter;

/**
 * Created by Sherlock·Holmes on 2020-03-25
 */
public class TypeConverter implements PropertyConverter<LoginResponse.UserdataBean, String> {

    @Override
    public LoginResponse.UserdataBean convertToEntityProperty(String databaseValue) {
        if (databaseValue == null){
            return null;
        }
         return new Gson().fromJson(databaseValue, LoginResponse.UserdataBean.class);
    }

    @Override
    public String convertToDatabaseValue(LoginResponse.UserdataBean entityProperty) {
        if (entityProperty == null) {
            return null;
        }
        return new Gson().toJson(entityProperty);
    }
}
