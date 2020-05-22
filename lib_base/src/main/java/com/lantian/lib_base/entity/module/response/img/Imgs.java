package com.lantian.lib_base.entity.module.response.img;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by Sherlock·Holmes on 2020/5/11
 */
@Entity
public class Imgs {

    private String user_id;
    @Id
    private String path;
    private int stutas;

    @Generated(hash = 330494227)
    public Imgs(String user_id, String path, int stutas) {
        this.user_id = user_id;
        this.path = path;
        this.stutas = stutas;
    }

    @Generated(hash = 80843332)
    public Imgs() {
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public String toString() {
        return "Img{" +
                "user_id='" + user_id + '\'' +
                ", path='" + path + '\'' +
                '}';
    }

    public int getStutas() {
        return stutas;
    }

    public void setStutas(int stutas) {
        this.stutas = stutas;
    }
}
