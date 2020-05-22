package com.lantian.lib_base.entity.module.response.adress;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class Adress {

    /**
     * 地区
     * id : 925
     * title : 北京市
     * longitude : 39.9047253699
     * latitude : 116.4072154982
     * fid : 0
     */
    @Id
    private String id;

    private String title;
    private String longitude;
    private String latitude;
    private String fid;

    @Generated(hash = 1920729352)
    public Adress(String id, String title, String longitude, String latitude,
            String fid) {
        this.id = id;
        this.title = title;
        this.longitude = longitude;
        this.latitude = latitude;
        this.fid = fid;
    }

    @Generated(hash = 1282510280)
    public Adress() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getFid() {
        return fid;
    }

    public void setFid(String fid) {
        this.fid = fid;
    }
}
