package com.lantian.lib_base.database.greendao;

import com.google.gson.Gson;
import com.lantian.lib_base.entity.module.response.breeds.BreedsList;

import org.greenrobot.greendao.converter.PropertyConverter;

/**
 * Created by Sherlock·Holmes on 2020-04-04
 */
public class EartagBeanConverter implements PropertyConverter<BreedsList.EartagBean, String> {
    @Override
    public BreedsList.EartagBean convertToEntityProperty(String databaseValue) {
        if (databaseValue == null){
            return null;
        }
        return new Gson().fromJson(databaseValue, BreedsList.EartagBean.class);
    }

    @Override
    public String convertToDatabaseValue(BreedsList.EartagBean entityProperty) {
        if (entityProperty == null) {
            return null;
        }
        return new Gson().toJson(entityProperty);
    }
}
