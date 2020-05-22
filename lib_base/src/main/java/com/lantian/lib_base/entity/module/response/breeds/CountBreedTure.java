package com.lantian.lib_base.entity.module.response.breeds;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Index;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by Sherlock·Holmes on 2020-03-30
 */
@Entity(indexes = {@Index(value = "userid DESC,status DESC",unique = true)})
public class CountBreedTure {

    /**
     * breedcont : 120
     * expenditure : 163200.00
     */

    @Id
    private String userid;
    private int status;
    private String breedcont;
    private String expenditure;

    @Generated(hash = 844379058)
    public CountBreedTure(String userid, int status, String breedcont,
            String expenditure) {
        this.userid = userid;
        this.status = status;
        this.breedcont = breedcont;
        this.expenditure = expenditure;
    }

    @Generated(hash = 750946010)
    public CountBreedTure() {
    }

    public String getBreedcont() {
        return breedcont;
    }

    public void setBreedcont(String breedcont) {
        this.breedcont = breedcont;
    }

    public String getExpenditure() {
        return expenditure;
    }

    public void setExpenditure(String expenditure) {
        this.expenditure = expenditure;
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
