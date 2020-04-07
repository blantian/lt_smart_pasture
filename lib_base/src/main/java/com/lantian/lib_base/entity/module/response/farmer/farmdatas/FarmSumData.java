package com.lantian.lib_base.entity.module.response.farmer.farmdatas;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by Sherlock·Holmes on 2020-03-30
 */
@Entity
public class FarmSumData {

    /**
     * renkou : 1
     * zonghui : 0.00
     * mianji : null
     * butie : 0.00
     */

    @Id
    private String user_id;

    private int renkou;
    private String zonghui;
    private String mianji;
    private String butie;

    @Generated(hash = 427476657)
    public FarmSumData(String user_id, int renkou, String zonghui, String mianji,
            String butie) {
        this.user_id = user_id;
        this.renkou = renkou;
        this.zonghui = zonghui;
        this.mianji = mianji;
        this.butie = butie;
    }

    @Generated(hash = 1287150834)
    public FarmSumData() {
    }

    public int getRenkou() {
        return renkou;
    }

    public void setRenkou(int renkou) {
        this.renkou = renkou;
    }

    public String getZonghui() {
        return zonghui;
    }

    public void setZonghui(String zonghui) {
        this.zonghui = zonghui;
    }

    public String getMianji() {
        return mianji;
    }

    public void setMianji(String mianji) {
        this.mianji = mianji;
    }

    public String getButie() {
        return butie;
    }

    public void setButie(String butie) {
        this.butie = butie;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }
}
