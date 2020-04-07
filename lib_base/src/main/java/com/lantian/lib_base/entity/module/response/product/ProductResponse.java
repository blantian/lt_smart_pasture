package com.lantian.lib_base.entity.module.response.product;

import com.google.gson.Gson;

import org.greenrobot.greendao.annotation.Convert;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Index;
import org.greenrobot.greendao.converter.PropertyConverter;

/**
 * Created by Sherlock·Holmes on 2020-03-26
 */

@Entity(indexes = {@Index(value = "product_id,user_id",unique = true)})
public class ProductResponse {


    /**
     * product_id : 6
     * iot_product_id : 8227
     * product_name : 智能饮水系统
     * product_type : FT5010
     * product_pic : http://admin.fengtaiiot.com/Public/Admin/uploadFile/images/智能饮水@2x-8.png
     * content : 牲畜感应、自动补水、排水等功能，通过物联网远程控制、采集多种环境数据和视频画面。
     * agreement : http
     * default_time : 60
     * product_min : 0
     * product_max : 500
     * product_scope : 500
     * type : 1
     * created_at : 2019-02-21 10:39:17
     * updated_at : 2019-10-30 10:28:59
     * deleted_at : null
     */

    private String product_id;
    private String user_id;

    private String iot_product_id;
    private String product_name;
    private String product_type;
    private String product_pic;
    private String content;
    private String agreement;
    private String default_time;
    private String product_min;
    private String product_max;
    private String product_scope;
    private String type;
    private String created_at;
    private String updated_at;
    private int product_icon;

    @Convert(converter = ProductTypeConverter.class,columnType = String.class)
    private static Object deleted_at;
    public ProductResponse() {
    }

    @Generated(hash = 1307908898)
    public ProductResponse(String product_id, String user_id, String iot_product_id,
            String product_name, String product_type, String product_pic, String content,
            String agreement, String default_time, String product_min, String product_max,
            String product_scope, String type, String created_at, String updated_at,
            int product_icon) {
        this.product_id = product_id;
        this.user_id = user_id;
        this.iot_product_id = iot_product_id;
        this.product_name = product_name;
        this.product_type = product_type;
        this.product_pic = product_pic;
        this.content = content;
        this.agreement = agreement;
        this.default_time = default_time;
        this.product_min = product_min;
        this.product_max = product_max;
        this.product_scope = product_scope;
        this.type = type;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.product_icon = product_icon;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public static class ProductTypeConverter implements PropertyConverter<Object, String>{

        @Override
        public Object convertToEntityProperty(String databaseValue) {
            if (databaseValue == null){
                return null;
            }
            return new Gson().fromJson(databaseValue, Object.class);
        }

        @Override
        public String convertToDatabaseValue(Object entityProperty) {
            if (entityProperty == null){
                return null;
            }
            return new Gson().toJson(entityProperty);
        }
    }

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public String getIot_product_id() {
        return iot_product_id;
    }

    public void setIot_product_id(String iot_product_id) {
        this.iot_product_id = iot_product_id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getProduct_type() {
        return product_type;
    }

    public void setProduct_type(String product_type) {
        this.product_type = product_type;
    }

    public String getProduct_pic() {
        return product_pic;
    }

    public void setProduct_pic(String product_pic) {
        this.product_pic = product_pic;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAgreement() {
        return agreement;
    }

    public void setAgreement(String agreement) {
        this.agreement = agreement;
    }

    public String getDefault_time() {
        return default_time;
    }

    public void setDefault_time(String default_time) {
        this.default_time = default_time;
    }

    public String getProduct_min() {
        return product_min;
    }

    public void setProduct_min(String product_min) {
        this.product_min = product_min;
    }

    public String getProduct_max() {
        return product_max;
    }

    public void setProduct_max(String product_max) {
        this.product_max = product_max;
    }

    public String getProduct_scope() {
        return product_scope;
    }

    public void setProduct_scope(String product_scope) {
        this.product_scope = product_scope;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public Object getDeleted_at() {
        return deleted_at;
    }

    public void setDeleted_at(Object deleted_at) {
        ProductResponse.deleted_at = deleted_at;
    }

    public int getProduct_icon() {
        return product_icon;
    }

    public void setProduct_icon(int product_icon) {
        this.product_icon = product_icon;
    }
}
