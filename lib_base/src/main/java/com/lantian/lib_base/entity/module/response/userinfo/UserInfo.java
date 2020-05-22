package com.lantian.lib_base.entity.module.response.userinfo;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by Sherlock·Holmes on 2020-03-20
 */
@Entity
public class UserInfo {

    /**
     * user_id : 29579
     * username : lantian
     * password :
     * phone : 18204893804
     * email : 1249361759@qq.com
     * nickname : null
     * sex : 1
     * address : 呼和浩特
     * id_card : 150423199410011439
     * age : 10
     * avatar : http://admin.fengtaiiot.com/uploadImage/5de215e5753ab.png
     * open_id : null
     * user_api_key : null
     * company_api_key : null
     * fial_time : null
     * is_admin : 3
     * register_code : null
     * flag_code : null
     * last_code : null
     * smx_username : null
     * smx_password : null
     * iot_username : null
     * iot_password :
     * appkey :
     * appsecret :
     * login_ip : null
     * created_at : 2019-11-14 11:20:57
     * updated_at : null
     * deleted_at : null
     * xian : 204
     * shi : 76
     * sheng : 66
     * address_id : null
     * smx_id :
     * smx_type : 1
     * smx_ticket : null
     * usertype_id : 0
     * lng : null
     * lat : null
     * updated_time : 0000-00-00 00:00:00
     * staff_id : null
     * name : lantian
     * addtime : 2019-11-14 11:20:57
     * status : 2
     * syte_id : 29
     * shengdata : 内蒙古自治区
     * shidata : 赤峰市
     * xiandata : 巴林右旗
     */

    @Id
    private String user_id;
    private String state;
    private String username;
    private String password;
    private String phone;
    private String email;
    private String nickname;
    private String sex;
    private String address;
    private String id_card;
    private String age;
    private String avatar;
    private String open_id;
    private String user_api_key;
    private String company_api_key;
    private String fial_time;
    private String is_admin;
    private String register_code;
    private String flag_code;
    private String last_code;
    private String smx_username;
    private String smx_password;
    private String iot_username;
    private String iot_password;
    private String appkey;
    private String appsecret;
    private String login_ip;
    private String created_at;
    private String updated_at;
    private String deleted_at;
    private String xian;
    private String shi;
    private String sheng;
    private String address_id;
    private String smx_id;
    private String smx_type;
    private String smx_ticket;
    private String usertype_id;
    private String lng;
    private String lat;
    private String updated_time;
    private String staff_id;
    private String name;
    private String addtime;
    private String status;
    private String syte_id;
    private String shengdata;
    private String shidata;
    private String xiandata;

    @Generated(hash = 1932014899)
    public UserInfo(String user_id, String state, String username, String password,
            String phone, String email, String nickname, String sex, String address,
            String id_card, String age, String avatar, String open_id,
            String user_api_key, String company_api_key, String fial_time,
            String is_admin, String register_code, String flag_code,
            String last_code, String smx_username, String smx_password,
            String iot_username, String iot_password, String appkey,
            String appsecret, String login_ip, String created_at, String updated_at,
            String deleted_at, String xian, String shi, String sheng,
            String address_id, String smx_id, String smx_type, String smx_ticket,
            String usertype_id, String lng, String lat, String updated_time,
            String staff_id, String name, String addtime, String status,
            String syte_id, String shengdata, String shidata, String xiandata) {
        this.user_id = user_id;
        this.state = state;
        this.username = username;
        this.password = password;
        this.phone = phone;
        this.email = email;
        this.nickname = nickname;
        this.sex = sex;
        this.address = address;
        this.id_card = id_card;
        this.age = age;
        this.avatar = avatar;
        this.open_id = open_id;
        this.user_api_key = user_api_key;
        this.company_api_key = company_api_key;
        this.fial_time = fial_time;
        this.is_admin = is_admin;
        this.register_code = register_code;
        this.flag_code = flag_code;
        this.last_code = last_code;
        this.smx_username = smx_username;
        this.smx_password = smx_password;
        this.iot_username = iot_username;
        this.iot_password = iot_password;
        this.appkey = appkey;
        this.appsecret = appsecret;
        this.login_ip = login_ip;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.deleted_at = deleted_at;
        this.xian = xian;
        this.shi = shi;
        this.sheng = sheng;
        this.address_id = address_id;
        this.smx_id = smx_id;
        this.smx_type = smx_type;
        this.smx_ticket = smx_ticket;
        this.usertype_id = usertype_id;
        this.lng = lng;
        this.lat = lat;
        this.updated_time = updated_time;
        this.staff_id = staff_id;
        this.name = name;
        this.addtime = addtime;
        this.status = status;
        this.syte_id = syte_id;
        this.shengdata = shengdata;
        this.shidata = shidata;
        this.xiandata = xiandata;
    }

    @Generated(hash = 1279772520)
    public UserInfo() {
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getId_card() {
        return id_card;
    }

    public void setId_card(String id_card) {
        this.id_card = id_card;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getOpen_id() {
        return open_id;
    }

    public void setOpen_id(String open_id) {
        this.open_id = open_id;
    }

    public String getUser_api_key() {
        return user_api_key;
    }

    public void setUser_api_key(String user_api_key) {
        this.user_api_key = user_api_key;
    }

    public String getCompany_api_key() {
        return company_api_key;
    }

    public void setCompany_api_key(String company_api_key) {
        this.company_api_key = company_api_key;
    }

    public String getFial_time() {
        return fial_time;
    }

    public void setFial_time(String fial_time) {
        this.fial_time = fial_time;
    }

    public String getIs_admin() {
        return is_admin;
    }

    public void setIs_admin(String is_admin) {
        this.is_admin = is_admin;
    }

    public String getRegister_code() {
        return register_code;
    }

    public void setRegister_code(String register_code) {
        this.register_code = register_code;
    }

    public String getFlag_code() {
        return flag_code;
    }

    public void setFlag_code(String flag_code) {
        this.flag_code = flag_code;
    }

    public String getLast_code() {
        return last_code;
    }

    public void setLast_code(String last_code) {
        this.last_code = last_code;
    }

    public String getSmx_username() {
        return smx_username;
    }

    public void setSmx_username(String smx_username) {
        this.smx_username = smx_username;
    }

    public String getSmx_password() {
        return smx_password;
    }

    public void setSmx_password(String smx_password) {
        this.smx_password = smx_password;
    }

    public String getIot_username() {
        return iot_username;
    }

    public void setIot_username(String iot_username) {
        this.iot_username = iot_username;
    }

    public String getIot_password() {
        return iot_password;
    }

    public void setIot_password(String iot_password) {
        this.iot_password = iot_password;
    }

    public String getAppkey() {
        return appkey;
    }

    public void setAppkey(String appkey) {
        this.appkey = appkey;
    }

    public String getAppsecret() {
        return appsecret;
    }

    public void setAppsecret(String appsecret) {
        this.appsecret = appsecret;
    }

    public String getLogin_ip() {
        return login_ip;
    }

    public void setLogin_ip(String login_ip) {
        this.login_ip = login_ip;
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

    public String getDeleted_at() {
        return deleted_at;
    }

    public void setDeleted_at(String deleted_at) {
        this.deleted_at = deleted_at;
    }

    public String getXian() {
        return xian;
    }

    public void setXian(String xian) {
        this.xian = xian;
    }

    public String getShi() {
        return shi;
    }

    public void setShi(String shi) {
        this.shi = shi;
    }

    public String getSheng() {
        return sheng;
    }

    public void setSheng(String sheng) {
        this.sheng = sheng;
    }

    public String getAddress_id() {
        return address_id;
    }

    public void setAddress_id(String address_id) {
        this.address_id = address_id;
    }

    public String getSmx_id() {
        return smx_id;
    }

    public void setSmx_id(String smx_id) {
        this.smx_id = smx_id;
    }

    public String getSmx_type() {
        return smx_type;
    }

    public void setSmx_type(String smx_type) {
        this.smx_type = smx_type;
    }

    public String getSmx_ticket() {
        return smx_ticket;
    }

    public void setSmx_ticket(String smx_ticket) {
        this.smx_ticket = smx_ticket;
    }

    public String getUsertype_id() {
        return usertype_id;
    }

    public void setUsertype_id(String usertype_id) {
        this.usertype_id = usertype_id;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getUpdated_time() {
        return updated_time;
    }

    public void setUpdated_time(String updated_time) {
        this.updated_time = updated_time;
    }

    public String getStaff_id() {
        return staff_id;
    }

    public void setStaff_id(String staff_id) {
        this.staff_id = staff_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddtime() {
        return addtime;
    }

    public void setAddtime(String addtime) {
        this.addtime = addtime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSyte_id() {
        return syte_id;
    }

    public void setSyte_id(String syte_id) {
        this.syte_id = syte_id;
    }

    public String getShengdata() {
        return shengdata;
    }

    public void setShengdata(String shengdata) {
        this.shengdata = shengdata;
    }

    public String getShidata() {
        return shidata;
    }

    public void setShidata(String shidata) {
        this.shidata = shidata;
    }

    public String getXiandata() {
        return xiandata;
    }

    public void setXiandata(String xiandata) {
        this.xiandata = xiandata;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
