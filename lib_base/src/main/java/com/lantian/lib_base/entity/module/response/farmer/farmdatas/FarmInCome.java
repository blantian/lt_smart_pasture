package com.lantian.lib_base.entity.module.response.farmer.farmdatas;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by Sherlock·Holmes on 2020-03-30
 */
@Entity
public class FarmInCome {

    /**
     * income : 0.00
     * expenditure : 0.00
     */
    @Id
    private String userid;

    private int status;
    private String income;
    private String expenditure;

    @Generated(hash = 465309935)
    public FarmInCome(String userid, int status, String income,
            String expenditure) {
        this.userid = userid;
        this.status = status;
        this.income = income;
        this.expenditure = expenditure;
    }

    @Generated(hash = 2102063540)
    public FarmInCome() {
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
