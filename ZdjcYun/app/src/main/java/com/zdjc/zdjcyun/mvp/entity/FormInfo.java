package com.zdjc.zdjcyun.mvp.entity;

/**
 * Created by huang on 2017/11/1.
 */
public class FormInfo {

    private String value,value1,value2,value3,value4,value5;
    private long time;
    private ChildData childData;
    private boolean isCheck;


    public FormInfo(String value, String value1,long time, boolean isCheck, ChildData childData) {
        this.value = value;
        this.value1 = value1;
        this.time = time;
        this.childData = childData;
        this.isCheck = isCheck;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getValue1() {
        return value1;
    }

    public void setValue1(String value1) {
        this.value1 = value1;
    }



    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public ChildData getChildData() {
        return childData;
    }

    public void setChildData(ChildData childData) {
        this.childData = childData;
    }

    public boolean isCheck() {
        return isCheck;
    }

    public void setCheck(boolean check) {
        isCheck = check;
    }
}
