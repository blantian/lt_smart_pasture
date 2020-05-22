package com.lantian.lib_base.entity.module.response.breeds;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Index;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by Sherlock·Holmes on 2020-04-01
 */

@Entity(indexes = {@Index(value = "userid DESC,status DESC",unique = true)})
public class CountBreeddatas {

    /**
     * breeddata1 : 0 出栏数量
     * breeddata2 : 0 未出栏数量
     * Breeddata3 : 0 屠宰数量
     */

    private int status;
    @Id
    private String userid;
    private String breeddata1;
    private String breeddata2;
    private String Breeddata3;

    @Generated(hash = 1758562121)
    public CountBreeddatas(int status, String userid, String breeddata1,
            String breeddata2, String Breeddata3) {
        this.status = status;
        this.userid = userid;
        this.breeddata1 = breeddata1;
        this.breeddata2 = breeddata2;
        this.Breeddata3 = Breeddata3;
    }

    @Generated(hash = 615449563)
    public CountBreeddatas() {
    }

    public String getBreeddata1() {
        return breeddata1;
    }

    public void setBreeddata1(String breeddata1) {
        this.breeddata1 = breeddata1;
    }

    public String getBreeddata2() {
        return breeddata2;
    }

    public void setBreeddata2(String breeddata2) {
        this.breeddata2 = breeddata2;
    }

    public String getBreeddata3() {
        return Breeddata3;
    }

    public void setBreeddata3(String Breeddata3) {
        this.Breeddata3 = Breeddata3;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
