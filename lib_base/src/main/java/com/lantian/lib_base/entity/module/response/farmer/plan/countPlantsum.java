package com.lantian.lib_base.entity.module.response.farmer.plan;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by Sherlock·Holmes on 2020-04-02
 */
@Entity
public class countPlantsum {

    /**
     * plant : 20016
     * plantclass : 5
     */

    @Id
    private String userid;
    private int status;
    private String plant;
    private String plantclass;

    @Generated(hash = 1696409636)
    public countPlantsum(String userid, int status, String plant,
            String plantclass) {
        this.userid = userid;
        this.status = status;
        this.plant = plant;
        this.plantclass = plantclass;
    }

    @Generated(hash = 1731483776)
    public countPlantsum() {
    }

    public String getPlant() {
        return plant;
    }

    public void setPlant(String plant) {
        this.plant = plant;
    }

    public String getPlantclass() {
        return plantclass;
    }

    public void setPlantclass(String plantclass) {
        this.plantclass = plantclass;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }
}
