package com.zdjc.zdjcyun.mvp.entity;


import com.bin.david.form.annotation.SmartColumn;

/**
 * Created by huang on 2017/11/1.
 */
public class UserInfo {

    @SmartColumn(id =1,name = "name",autoCount = true)
    private String name;
    @SmartColumn(id=2,name="time",autoCount = true)
    private String time;
    @SmartColumn(id =3,name="totalChange",autoCount = true)
    private String totalChange;
    @SmartColumn(id =4,name="singleChange",autoCount = true)
    private String singleChange;
    @SmartColumn(id =5,name="speedChange",autoCount = true)
    private String speedChange;
    @SmartColumn(id =6,name="sunNum",autoCount = true)
    private String sunNum;
    @SmartColumn(id =7,name="sunChancel",autoCount = true)
    private String sunChancel;


    public UserInfo(String name, String time,String totalChange,String singleChange, String speedChange, String sunNum, String sunChancel) {
        this.name = name;
        this.time = time;
        this.totalChange = totalChange;
        this.singleChange = singleChange;
        this.speedChange = speedChange;
        this.sunNum = sunNum;
        this.sunChancel = sunChancel;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTotalChange() {
        return totalChange;
    }

    public void setTotalChange(String totalChange) {
        this.totalChange = totalChange;
    }

    public String getSingleChange() {
        return singleChange;
    }

    public void setSingleChange(String singleChange) {
        this.singleChange = singleChange;
    }

    public String getSpeedChange() {
        return speedChange;
    }

    public void setSpeedChange(String speedChange) {
        this.speedChange = speedChange;
    }

    public String getSunNum() {
        return sunNum;
    }

    public void setSunNum(String sunNum) {
        this.sunNum = sunNum;
    }

    public String getSunChancel() {
        return sunChancel;
    }

    public void setSunChancel(String sunChancel) {
        this.sunChancel = sunChancel;
    }
}
