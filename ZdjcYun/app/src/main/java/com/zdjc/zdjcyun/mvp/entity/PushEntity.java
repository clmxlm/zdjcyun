package com.zdjc.zdjcyun.mvp.entity;

import java.util.List;

public class PushEntity {


    /**
     * terminalNumber : ZD2019040009
     * sensorNumber : JG2019030017
     * createDate : 2019-05-23 15:42:00
     * data : [52512,-3,-6,-0.6]
     */

    private String terminalNumber;
    private String sensorNumber;
    private String createDate;
    private List<Double> data;

    public String getTerminalNumber() {
        return terminalNumber;
    }

    public void setTerminalNumber(String terminalNumber) {
        this.terminalNumber = terminalNumber;
    }

    public String getSensorNumber() {
        return sensorNumber;
    }

    public void setSensorNumber(String sensorNumber) {
        this.sensorNumber = sensorNumber;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public List<Double> getData() {
        return data;
    }

    public void setData(List<Double> data) {
        this.data = data;
    }
}
