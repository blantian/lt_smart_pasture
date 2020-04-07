package com.lantian.lib_base.database.greendao;

import com.google.gson.Gson;
import com.lantian.lib_base.entity.module.response.breeds.BreedsList;

import org.greenrobot.greendao.converter.PropertyConverter;

/**
 * Created by Sherlock·Holmes on 2020-04-04
 */
public class BreedBeanConverter implements PropertyConverter<BreedsList.BreedBean,String> {
    @Override
    public BreedsList.BreedBean convertToEntityProperty(String databaseValue) {
        if (databaseValue == null){
            return null;
        }
        return new Gson().fromJson(databaseValue, BreedsList.BreedBean.class);
    }

    @Override
    public String convertToDatabaseValue(BreedsList.BreedBean entityProperty) {
        return new Gson().toJson(entityProperty);
    }
}
