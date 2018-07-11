package com.zdjc.zdjcyun.mvp.entity;

import java.util.ArrayList;

public class ProjectDetailEntity {


    /**
     * code : 0
     * msg : 操作成功
     * data : [{"tableName":"laser_data","monitorType":15,"monitorTypeName":"收敛","sensorList":[{"monitorPoint":"SL02","smuNumber":"2017100005","smuChannel":"00","sensorNumber":"02","totalLaserChange":-6.6,"speedChange":-3.0E-4},{"monitorPoint":"SL03","smuNumber":"2017100005","smuChannel":"00","sensorNumber":"03","totalLaserChange":-4.7,"speedChange":0},{"monitorPoint":"SL04","smuNumber":"2017100005","smuChannel":"00","sensorNumber":"04","totalLaserChange":-94.7,"speedChange":5.0E-4},{"monitorPoint":"SL05","smuNumber":"2017100005","smuChannel":"00","sensorNumber":"05","totalLaserChange":-61.9,"speedChange":2.0E-4},{"monitorPoint":"SL06","smuNumber":"2017100005","smuChannel":"00","sensorNumber":"06","totalLaserChange":2.6,"speedChange":-2.0E-4},{"monitorPoint":"SL07","smuNumber":"2017100005","smuChannel":"00","sensorNumber":"07","totalLaserChange":83,"speedChange":-3.0E-4},{"monitorPoint":"SL08","smuNumber":"2017100005","smuChannel":"00","sensorNumber":"08","totalLaserChange":-5.4,"speedChange":5.0E-4},{"monitorPoint":"SL09","smuNumber":"2017100005","smuChannel":"00","sensorNumber":"09","totalLaserChange":34.8,"speedChange":-5.0E-4},{"monitorPoint":"SL10","smuNumber":"2017100005","smuChannel":"00","sensorNumber":"10","totalLaserChange":0.7,"speedChange":3.0E-4},{"monitorPoint":"SL11","smuNumber":"2017100005","smuChannel":"00","sensorNumber":"11","totalLaserChange":7.5,"speedChange":-3.0E-4},{"monitorPoint":"SL12","smuNumber":"2017100004","smuChannel":"00","sensorNumber":"12","totalLaserChange":58.9,"speedChange":-2.0E-4},{"monitorPoint":"SL13","smuNumber":"2017100004","smuChannel":"00","sensorNumber":"13","totalLaserChange":4,"speedChange":0},{"monitorPoint":"SL14","smuNumber":"2017100004","smuChannel":"00","sensorNumber":"14","totalLaserChange":5.2,"speedChange":3.0E-4}]},{"tableName":"static_level_data","monitorType":16,"monitorTypeName":"沉降","sensorList":[{"monitorPoint":"CJ15","smuNumber":"2017100005","smuChannel":"01","sensorNumber":"15","totalLaserChange":-1153.209,"speedChange":0.0057},{"monitorPoint":"CJ16","smuNumber":"2017100005","smuChannel":"01","sensorNumber":"16","totalLaserChange":-1546.6893,"speedChange":0.0132},{"monitorPoint":"CJ17","smuNumber":"2017100005","smuChannel":"01","sensorNumber":"17","totalLaserChange":-1534.4779,"speedChange":0.009},{"monitorPoint":"CJ18","smuNumber":"2017100005","smuChannel":"01","sensorNumber":"18","totalLaserChange":-1722.6457,"speedChange":0.0089},{"monitorPoint":"CJ19","smuNumber":"2017100005","smuChannel":"01","sensorNumber":"19","totalLaserChange":-1536.1626,"speedChange":0.0093},{"monitorPoint":"CJ1a","smuNumber":"2017100005","smuChannel":"01","sensorNumber":"1a","totalLaserChange":-1526.5264,"speedChange":0.0091},{"monitorPoint":"CJ1b","smuNumber":"2017100005","smuChannel":"01","sensorNumber":"1b","totalLaserChange":-1529.7064,"speedChange":0.009},{"monitorPoint":"CJ1c","smuNumber":"2017100005","smuChannel":"01","sensorNumber":"1c","totalLaserChange":-1517.7086,"speedChange":0.0088},{"monitorPoint":"CJ1d","smuNumber":"2017100005","smuChannel":"01","sensorNumber":"1d","totalLaserChange":-1593.4019,"speedChange":0.0085},{"monitorPoint":"CJ1e","smuNumber":"2017100005","smuChannel":"01","sensorNumber":"1e","totalLaserChange":-1526.0644,"speedChange":0.0084}]}]
     */

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
         * tableName : laser_data
         * monitorType : 15
         * monitorTypeName : 收敛
         * sensorList : [{"monitorPoint":"SL02","smuNumber":"2017100005","smuChannel":"00","sensorNumber":"02","totalLaserChange":-6.6,"speedChange":-3.0E-4},{"monitorPoint":"SL03","smuNumber":"2017100005","smuChannel":"00","sensorNumber":"03","totalLaserChange":-4.7,"speedChange":0},{"monitorPoint":"SL04","smuNumber":"2017100005","smuChannel":"00","sensorNumber":"04","totalLaserChange":-94.7,"speedChange":5.0E-4},{"monitorPoint":"SL05","smuNumber":"2017100005","smuChannel":"00","sensorNumber":"05","totalLaserChange":-61.9,"speedChange":2.0E-4},{"monitorPoint":"SL06","smuNumber":"2017100005","smuChannel":"00","sensorNumber":"06","totalLaserChange":2.6,"speedChange":-2.0E-4},{"monitorPoint":"SL07","smuNumber":"2017100005","smuChannel":"00","sensorNumber":"07","totalLaserChange":83,"speedChange":-3.0E-4},{"monitorPoint":"SL08","smuNumber":"2017100005","smuChannel":"00","sensorNumber":"08","totalLaserChange":-5.4,"speedChange":5.0E-4},{"monitorPoint":"SL09","smuNumber":"2017100005","smuChannel":"00","sensorNumber":"09","totalLaserChange":34.8,"speedChange":-5.0E-4},{"monitorPoint":"SL10","smuNumber":"2017100005","smuChannel":"00","sensorNumber":"10","totalLaserChange":0.7,"speedChange":3.0E-4},{"monitorPoint":"SL11","smuNumber":"2017100005","smuChannel":"00","sensorNumber":"11","totalLaserChange":7.5,"speedChange":-3.0E-4},{"monitorPoint":"SL12","smuNumber":"2017100004","smuChannel":"00","sensorNumber":"12","totalLaserChange":58.9,"speedChange":-2.0E-4},{"monitorPoint":"SL13","smuNumber":"2017100004","smuChannel":"00","sensorNumber":"13","totalLaserChange":4,"speedChange":0},{"monitorPoint":"SL14","smuNumber":"2017100004","smuChannel":"00","sensorNumber":"14","totalLaserChange":5.2,"speedChange":3.0E-4}]
         */

        private String tableName;
        private int monitorType;
        private String monitorTypeName;
        private ArrayList<SensorListBean> sensorList;

        public String getTableName() {
            return tableName;
        }

        public void setTableName(String tableName) {
            this.tableName = tableName;
        }

        public int getMonitorType() {
            return monitorType;
        }

        public void setMonitorType(int monitorType) {
            this.monitorType = monitorType;
        }

        public String getMonitorTypeName() {
            return monitorTypeName;
        }

        public void setMonitorTypeName(String monitorTypeName) {
            this.monitorTypeName = monitorTypeName;
        }

        public ArrayList<SensorListBean> getSensorList() {
            return sensorList;
        }

        public void setSensorList(ArrayList<SensorListBean> sensorList) {
            this.sensorList = sensorList;
        }

        public static class SensorListBean {
            /**
             * monitorPoint : SL02
             * smuNumber : 2017100005
             * smuChannel : 00
             * sensorNumber : 02
             * totalLaserChange : -6.6
             * speedChange : -3.0E-4
             */

            private String monitorPoint;
            private String smuNumber;
            private String smuChannel;
            private String sensorNumber;
            private double totalLaserChange;
            private double speedChange;

            public String getMonitorPoint() {
                return monitorPoint;
            }

            public void setMonitorPoint(String monitorPoint) {
                this.monitorPoint = monitorPoint;
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

            public String getSensorNumber() {
                return sensorNumber;
            }

            public void setSensorNumber(String sensorNumber) {
                this.sensorNumber = sensorNumber;
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

            public void setSpeedChange(double speedChange) {
                this.speedChange = speedChange;
            }
        }
    }
}
