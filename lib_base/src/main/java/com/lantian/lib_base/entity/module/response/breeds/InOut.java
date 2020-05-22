package com.lantian.lib_base.entity.module.response.breeds;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by Sherlock·Holmes on 2020-04-01
 */
@Entity
public class InOut {

    /**
     * income : 16003001.00
     * expenditure : 1501.00
     */

    @Id
    private String userid;
    private int status;
    private String income;
    private String expenditure;

    @Generated(hash = 2050201128)
    public InOut(String userid, int status, String income, String expenditure) {
        this.userid = userid;
        this.status = status;
        this.income = income;
        this.expenditure = expenditure;
    }

    @Generated(hash = 1713764987)
    public InOut() {
    }

    public String getIncome() {
        return income;
    }

    public void setIncome(String income) {
        this.income = income;
    }

    public String getExpenditure() {
        return expenditure;
    }

    public void setExpenditure(String expenditure) {
        this.expenditure = expenditure;
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
