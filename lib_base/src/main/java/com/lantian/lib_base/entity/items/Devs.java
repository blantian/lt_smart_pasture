package com.lantian.lib_base.entity.items;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by Sherlock·Holmes on 2020/5/11
 */
@Entity
public class Devs {

    private int icon;
    private String devname;
    private String name;
    @Id
    private String ip;
    private String port;
    private String pass;
    private int status;
    private String userid;
    private int devkind;


    @Generated(hash = 941088625)
    public Devs(int icon, String devname, String name, String ip, String port,
            String pass, int status, String userid, int devkind) {
        this.icon = icon;
        this.devname = devname;
        this.name = name;
        this.ip = ip;
        this.port = port;
        this.pass = pass;
        this.status = status;
        this.userid = userid;
        this.devkind = devkind;
    }

    @Generated(hash = 1479661575)
    public Devs() {
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }


    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getDevname() {
        return devname;
    }

    public void setDevname(String devname) {
        this.devname = devname;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public int getDevkind() {
        return devkind;
    }

    public void setDevkind(int devkind) {
        this.devkind = devkind;
    }
}
