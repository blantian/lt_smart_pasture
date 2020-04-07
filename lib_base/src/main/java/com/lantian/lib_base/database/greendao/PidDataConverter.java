package com.lantian.lib_base.database.greendao;

import com.google.gson.Gson;
import com.lantian.lib_base.entity.module.response.breeds.BreedsList;

import org.greenrobot.greendao.converter.PropertyConverter;

/**
 * Created by Sherlock·Holmes on 2020/4/6
 */
public class PidDataConverter implements PropertyConverter<BreedsList.PiddataBean,String> {

    @Override
    public BreedsList.PiddataBean convertToEntityProperty(String databaseValue) {
        if (databaseValue == null){
            return null;
        }
        return new Gson().fromJson(databaseValue,BreedsList.PiddataBean.class);
    }

    @Override
    public String convertToDatabaseValue(BreedsList.PiddataBean entityProperty) {
        return new Gson().toJson(entityProperty);
    }

}
