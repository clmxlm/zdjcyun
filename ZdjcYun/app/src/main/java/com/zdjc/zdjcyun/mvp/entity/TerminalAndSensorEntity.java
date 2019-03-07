package com.zdjc.zdjcyun.mvp.entity;

/**
 * @author ClmXlm
 * @create 2019/1/22
 * @Describe
 */
public class TerminalAndSensorEntity {


    /**
     * code : 2
     * msg : 没有找到第一次数据；没有阈值信息；
     * data : {"mapType":6,"monitorPointNumber":"ZCL08","terminalNumber":"240305004049739","terminalChannel":"1","sensorNumber":"ZCL8","monitorTypeName":"砼支撑轴力","firstTime":null,"firstData":null,"oneMinValue":null,"twoMinValue":null,"threeMinValue":null}
     */

    private int code;
    private String msg;
    private DataBean data;

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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * mapType : 6
         * monitorPointNumber : ZCL08
         * terminalNumber : 240305004049739
         * terminalChannel : 1
         * sensorNumber : ZCL8
         * monitorTypeName : 砼支撑轴力
         * firstTime : null
         * firstData : null
         * oneMinValue : null
         * twoMinValue : null
         * threeMinValue : null
         */

        private int mapType;
        private String monitorPointNumber;
        private String terminalNumber;
        private String terminalChannel;
        private String sensorNumber;
        private String monitorTypeName;
        private Object firstTime;
        private Object firstData;
        private Object oneMinValue;
        private Object twoMinValue;
        private Object threeMinValue;

        public int getMapType() {
            return mapType;
        }

        public void setMapType(int mapType) {
            this.mapType = mapType;
        }

        public String getMonitorPointNumber() {
            return monitorPointNumber;
        }

        public void setMonitorPointNumber(String monitorPointNumber) {
            this.monitorPointNumber = monitorPointNumber;
        }

        public String getTerminalNumber() {
            return terminalNumber;
        }

        public void setTerminalNumber(String terminalNumber) {
            this.terminalNumber = terminalNumber;
        }

        public String getTerminalChannel() {
            return terminalChannel;
        }

        public void setTerminalChannel(String terminalChannel) {
            this.terminalChannel = terminalChannel;
        }

        public String getSensorNumber() {
            return sensorNumber;
        }

        public void setSensorNumber(String sensorNumber) {
            this.sensorNumber = sensorNumber;
        }

        public String getMonitorTypeName() {
            return monitorTypeName;
        }

        public void setMonitorTypeName(String monitorTypeName) {
            this.monitorTypeName = monitorTypeName;
        }

        public Object getFirstTime() {
            return firstTime;
        }

        public void setFirstTime(Object firstTime) {
            this.firstTime = firstTime;
        }

        public Object getFirstData() {
            return firstData;
        }

        public void setFirstData(Object firstData) {
            this.firstData = firstData;
        }

        public Object getOneMinValue() {
            return oneMinValue;
        }

        public void setOneMinValue(Object oneMinValue) {
            this.oneMinValue = oneMinValue;
        }

        public Object getTwoMinValue() {
            return twoMinValue;
        }

        public void setTwoMinValue(Object twoMinValue) {
            this.twoMinValue = twoMinValue;
        }

        public Object getThreeMinValue() {
            return threeMinValue;
        }

        public void setThreeMinValue(Object threeMinValue) {
            this.threeMinValue = threeMinValue;
        }
    }
}
