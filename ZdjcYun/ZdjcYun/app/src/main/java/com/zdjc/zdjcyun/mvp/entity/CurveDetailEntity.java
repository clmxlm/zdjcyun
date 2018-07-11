package com.zdjc.zdjcyun.mvp.entity;

import java.util.ArrayList;

public class CurveDetailEntity {

    private int code;
    private String msg;
    private ArrayList<DataBean> data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public ArrayList<DataBean> getData() {
        return data;
    }

    public void setData(ArrayList<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 591855
         * sensorNumber : 0A
         * firstTime : 2018-04-25 17:30:38
         * firstData : 284.3334
         * previousTime : 2018-05-07 23:40:38
         * previousData : 281.0972
         * currentTimes : 2018-05-07 23:50:35
         * currentData : 281.551
         * currentTemperature : 23.81
         * currentLaserChange : 0.4538
         * totalLaserChange : -2.7824
         * speedChange : 8.0E-4
         * sensorStatus : 1
         * createType : manual
         * smuNumber : 2017110003
         * smuChannel : 08
         * smuStatus : 1
         */

        private int id;
        private String sensorNumber;
        private String firstTime;
        private double firstData;
        private String previousTime;
        private double previousData;
        private String currentTimes;
        private double currentData;
        private double currentTemperature;
        private double currentLaserChange;
        private double totalLaserChange;
        private float speedChange;
        private int sensorStatus;
        private String createType;
        private String smuNumber;
        private String smuChannel;
        private int smuStatus;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getSensorNumber() {
            return sensorNumber;
        }

        public void setSensorNumber(String sensorNumber) {
            this.sensorNumber = sensorNumber;
        }

        public String getFirstTime() {
            return firstTime;
        }

        public void setFirstTime(String firstTime) {
            this.firstTime = firstTime;
        }

        public double getFirstData() {
            return firstData;
        }

        public void setFirstData(double firstData) {
            this.firstData = firstData;
        }

        public String getPreviousTime() {
            return previousTime;
        }

        public void setPreviousTime(String previousTime) {
            this.previousTime = previousTime;
        }

        public double getPreviousData() {
            return previousData;
        }

        public void setPreviousData(double previousData) {
            this.previousData = previousData;
        }

        public String getCurrentTimes() {
            return currentTimes;
        }

        public void setCurrentTimes(String currentTimes) {
            this.currentTimes = currentTimes;
        }

        public double getCurrentData() {
            return currentData;
        }

        public void setCurrentData(double currentData) {
            this.currentData = currentData;
        }

        public double getCurrentTemperature() {
            return currentTemperature;
        }

        public void setCurrentTemperature(double currentTemperature) {
            this.currentTemperature = currentTemperature;
        }

        public double getCurrentLaserChange() {
            return currentLaserChange;
        }

        public void setCurrentLaserChange(double currentLaserChange) {
            this.currentLaserChange = currentLaserChange;
        }

        public double getTotalLaserChange() {
            return totalLaserChange;
        }

        public void setTotalLaserChange(double totalLaserChange) {
            this.totalLaserChange = totalLaserChange;
        }

        public double getSpeedChange() {
            return speedChange;
        }

        public void setSpeedChange(float speedChange) {
            this.speedChange = speedChange;
        }

        public int getSensorStatus() {
            return sensorStatus;
        }

        public void setSensorStatus(int sensorStatus) {
            this.sensorStatus = sensorStatus;
        }

        public String getCreateType() {
            return createType;
        }

        public void setCreateType(String createType) {
            this.createType = createType;
        }

        public String getSmuNumber() {
            return smuNumber;
        }

        public void setSmuNumber(String smuNumber) {
            this.smuNumber = smuNumber;
        }

        public String getSmuChannel() {
            return smuChannel;
        }

        public void setSmuChannel(String smuChannel) {
            this.smuChannel = smuChannel;
        }

        public int getSmuStatus() {
            return smuStatus;
        }

        public void setSmuStatus(int smuStatus) {
            this.smuStatus = smuStatus;
        }
    }
}
