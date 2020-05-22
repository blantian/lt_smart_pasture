package com.lantian.lib_base.database.greendao;

import com.alibaba.fastjson.JSON;
import com.lantian.lib_base.entity.module.response.breeds.BreedsOfUser;

import org.greenrobot.greendao.converter.PropertyConverter;

import java.util.List;

/**
 * Created by Sherlock·Holmes on 2020/4/13
 */
public class ListConverter implements PropertyConverter<List<BreedsOfUser.DataBean>, String> {

    @Override
    public List<BreedsOfUser.DataBean> convertToEntityProperty(String databaseValue) {
        return JSON.parseArray(databaseValue , BreedsOfUser.DataBean.class);
    }
    @Override
    public String convertToDatabaseValue(List<BreedsOfUser.DataBean> entityProperty) {
        return JSON.toJSONString(entityProperty);
    }
}
