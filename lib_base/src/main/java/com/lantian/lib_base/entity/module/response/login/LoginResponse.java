package com.lantian.lib_base.entity.module.response.login;

import com.lantian.lib_base.database.greendao.TypeConverter;

import org.greenrobot.greendao.annotation.Convert;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Index;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by Sherlock·Holmes on 2020-03-26
 */
@Entity(indexes = {@Index(value = "user_id DESC,status DESC",unique = true)})
public class LoginResponse {


    /**
     * token : eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE1ODUxOTI1NzcsImV4cCI6MTU4NTI3ODk3NywidXNlcl9pZCI6IjI5NjEzIiwidXNlcm5hbWUiOiJ0ZXN0NiJ9.46q8tqgXGoLhg_hH_RsXZZcvKMJ_FZLn-BoUodquuyU
     * user_id : 29613
     * username : test6
     * userdata : {"user_id":"29613","username":"test6","password":"fcea920f7412b5da7be0cf42b8c93759","phone":"13404880950","email":"","nickname":null,"sex":"0","address":"","id_card":"","age":"0","avatar":null,"open_id":null,"user_api_key":null,"company_api_key":null,"fial_time":null,"is_admin":"3","register_code":null,"flag_code":null,"last_code":null,"smx_username":null,"smx_password":null,"iot_username":null,"iot_password":"","appkey":"","appsecret":"","login_ip":null,"created_at":"2019-12-22 11:27:42","updated_at":null,"deleted_at":null,"xian":"0","shi":"0","sheng":"0","address_id":null,"smx_id":"","smx_type":"1","smx_ticket":null,"usertype_id":"0","lng":null,"lat":null,"updated_time":"0000-00-00 00:00:00","staff_id":null,"name":"test6","addtime":"2019-12-22 11:27:42","status":"2","syte_id":"29"}
     */


    private int status;
    private String token;
    private String user_id;
    private String username;
    @Convert(converter = TypeConverter.class,columnType = String.class)
    private UserdataBean userdata;
    public LoginResponse() {
    }

    @Generated(hash = 173898931)
    public LoginResponse(int status, String token, String user_id, String username, UserdataBean userdata) {
        this.status = status;
        this.token = token;
        this.user_id = user_id;
        this.username = username;
        this.userdata = userdata;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
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

    public UserdataBean getUserdata() {
        return userdata;
    }

    public void setUserdata(UserdataBean userdata) {
        this.userdata = userdata;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public static class UserdataBean {
        /**
         * user_id : 29613
         * username : test6
         * password : fcea920f7412b5da7be0cf42b8c93759
         * phone : 13404880950
         * email :
         * nickname : null
         * sex : 0
         * address :
         * id_card :
         * age : 0
         * avatar : null
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
         * created_at : 2019-12-22 11:27:42
         * updated_at : null
         * deleted_at : null
         * xian : 0
         * shi : 0
         * sheng : 0
         * address_id : null
         * smx_id :
         * smx_type : 1
         * smx_ticket : null
         * usertype_id : 0
         * lng : null
         * lat : null
         * updated_time : 0000-00-00 00:00:00
         * staff_id : null
         * name : test6
         * addtime : 2019-12-22 11:27:42
         * status : 2
         * syte_id : 29
         */

        private String appact_type;
        private String user_id;
        private String username;
        private String password;
        private String phone;
        private String email;
        private Object nickname;
        private String sex;
        private String address;
        private String id_card;
        private String age;
        private Object avatar;
        private Object open_id;
        private Object user_api_key;
        private Object company_api_key;
        private Object fial_time;
        private String is_admin;
        private Object register_code;
        private Object flag_code;
        private Object last_code;
        private Object smx_username;
        private Object smx_password;
        private Object iot_username;
        private String iot_password;
        private String appkey;
        private String appsecret;
        private Object login_ip;
        private String created_at;
        private Object updated_at;
        private Object deleted_at;
        private String xian;
        private String shi;
        private String sheng;
        private Object address_id;
        private String smx_id;
        private String smx_type;
        private Object smx_ticket;
        private String usertype_id;
        private Object lng;
        private Object lat;
        private String updated_time;
        private Object staff_id;
        private String name;
        private String addtime;
        private String status;
        private String syte_id;

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

        public Object getNickname() {
            return nickname;
        }

        public void setNickname(Object nickname) {
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

        public Object getAvatar() {
            return avatar;
        }

        public void setAvatar(Object avatar) {
            this.avatar = avatar;
        }

        public Object getOpen_id() {
            return open_id;
        }

        public void setOpen_id(Object open_id) {
            this.open_id = open_id;
        }

        public Object getUser_api_key() {
            return user_api_key;
        }

        public void setUser_api_key(Object user_api_key) {
            this.user_api_key = user_api_key;
        }

        public Object getCompany_api_key() {
            return company_api_key;
        }

        public void setCompany_api_key(Object company_api_key) {
            this.company_api_key = company_api_key;
        }

        public Object getFial_time() {
            return fial_time;
        }

        public void setFial_time(Object fial_time) {
            this.fial_time = fial_time;
        }

        public String getIs_admin() {
            return is_admin;
        }

        public void setIs_admin(String is_admin) {
            this.is_admin = is_admin;
        }

        public Object getRegister_code() {
            return register_code;
        }

        public void setRegister_code(Object register_code) {
            this.register_code = register_code;
        }

        public Object getFlag_code() {
            return flag_code;
        }

        public void setFlag_code(Object flag_code) {
            this.flag_code = flag_code;
        }

        public Object getLast_code() {
            return last_code;
        }

        public void setLast_code(Object last_code) {
            this.last_code = last_code;
        }

        public Object getSmx_username() {
            return smx_username;
        }

        public void setSmx_username(Object smx_username) {
            this.smx_username = smx_username;
        }

        public Object getSmx_password() {
            return smx_password;
        }

        public void setSmx_password(Object smx_password) {
            this.smx_password = smx_password;
        }

        public Object getIot_username() {
            return iot_username;
        }

        public void setIot_username(Object iot_username) {
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

        public Object getLogin_ip() {
            return login_ip;
        }

        public void setLogin_ip(Object login_ip) {
            this.login_ip = login_ip;
        }

        public String getCreated_at() {
            return created_at;
        }

        public void setCreated_at(String created_at) {
            this.created_at = created_at;
        }

        public Object getUpdated_at() {
            return updated_at;
        }

        public void setUpdated_at(Object updated_at) {
            this.updated_at = updated_at;
        }

        public Object getDeleted_at() {
            return deleted_at;
        }

        public void setDeleted_at(Object deleted_at) {
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

        public Object getAddress_id() {
            return address_id;
        }

        public void setAddress_id(Object address_id) {
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

        public Object getSmx_ticket() {
            return smx_ticket;
        }

        public void setSmx_ticket(Object smx_ticket) {
            this.smx_ticket = smx_ticket;
        }

        public String getUsertype_id() {
            return usertype_id;
        }

        public void setUsertype_id(String usertype_id) {
            this.usertype_id = usertype_id;
        }

        public Object getLng() {
            return lng;
        }

        public void setLng(Object lng) {
            this.lng = lng;
        }

        public Object getLat() {
            return lat;
        }

        public void setLat(Object lat) {
            this.lat = lat;
        }

        public String getUpdated_time() {
            return updated_time;
        }

        public void setUpdated_time(String updated_time) {
            this.updated_time = updated_time;
        }

        public Object getStaff_id() {
            return staff_id;
        }

        public void setStaff_id(Object staff_id) {
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

        public String getAppact_type() {
            return appact_type;
        }

        public void setAppact_type(String appact_type) {
            this.appact_type = appact_type;
        }
    }
}
