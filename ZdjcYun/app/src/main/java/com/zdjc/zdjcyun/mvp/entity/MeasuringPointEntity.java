package com.zdjc.zdjcyun.mvp.entity;

import java.util.List;

public class MeasuringPointEntity {


    /**
     * code : 0
     * msg : 操作成功
     * data : [{"monitorTypeName":"沉降","detections":[{"monitorPoint":"CJ15","smuNumber":"2017100005","smuChannel":"02","sensorLongitude":"119.5481900000","sensorLatitude":"26.6657100000"},{"monitorPoint":"CJ16","smuNumber":"2017100005","smuChannel":"02","sensorLongitude":"119.5481900000","sensorLatitude":"26.6657100000"},{"monitorPoint":"CJ17","smuNumber":"2017100005","smuChannel":"02","sensorLongitude":"11","sensorLatitude":"11"},{"monitorPoint":"CJ18","smuNumber":"2017100005","smuChannel":"02","sensorLongitude":"11","sensorLatitude":"11"},{"monitorPoint":"CJ19","smuNumber":"2017100005","smuChannel":"02","sensorLongitude":"11","sensorLatitude":"11"},{"monitorPoint":"CJ1a","smuNumber":"2017100005","smuChannel":"02","sensorLongitude":"11","sensorLatitude":"11"},{"monitorPoint":"CJ1b","smuNumber":"2017100005","smuChannel":"02","sensorLongitude":"11","sensorLatitude":"11"},{"monitorPoint":"CJ1c","smuNumber":"2017100005","smuChannel":"02","sensorLongitude":"11","sensorLatitude":"11"},{"monitorPoint":"CJ1d","smuNumber":"2017100005","smuChannel":"02","sensorLongitude":"11","sensorLatitude":"11"},{"monitorPoint":"CJ1e","smuNumber":"2017100005","smuChannel":"02","sensorLongitude":"11","sensorLatitude":"11"}]},{"monitorTypeName":"收敛","detections":[{"monitorPoint":"SL02","smuNumber":"2017100005","smuChannel":"00","sensorLongitude":"113.0854218","sensorLatitude":"28.1481068"},{"monitorPoint":"SL03","smuNumber":"2017100005","smuChannel":"00","sensorLongitude":"113.0854218","sensorLatitude":"28.1481068"},{"monitorPoint":"SL04","smuNumber":"2017100005","smuChannel":"00","sensorLongitude":"113.0854218","sensorLatitude":"28.1481068"},{"monitorPoint":"SL05","smuNumber":"2017100005","smuChannel":"00","sensorLongitude":"113.0854218","sensorLatitude":"28.1481068"},{"monitorPoint":"SL06","smuNumber":"2017100005","smuChannel":"00","sensorLongitude":"113.0854218","sensorLatitude":"28.1481068"},{"monitorPoint":"SL07","smuNumber":"2017100005","smuChannel":"00","sensorLongitude":"113.0854218","sensorLatitude":"28.1481068"},{"monitorPoint":"SL08","smuNumber":"2017100005","smuChannel":"00","sensorLongitude":"113.0854218","sensorLatitude":"28.1481068"},{"monitorPoint":"SL09","smuNumber":"2017100005","smuChannel":"00","sensorLongitude":"113.0854218","sensorLatitude":"28.1481068"},{"monitorPoint":"SL10","smuNumber":"2017100005","smuChannel":"00","sensorLongitude":"113.0854218","sensorLatitude":"28.1481068"},{"monitorPoint":"SL11","smuNumber":"2017100005","smuChannel":"00","sensorLongitude":"113.0854218","sensorLatitude":"28.1481068"},{"monitorPoint":"SL12","smuNumber":"2017100004","smuChannel":"00","sensorLongitude":"113.0854218","sensorLatitude":"28.1481068"},{"monitorPoint":"SL13","smuNumber":"2017100004","smuChannel":"00","sensorLongitude":"113.0854218","sensorLatitude":"28.1481068"},{"monitorPoint":"SL14","smuNumber":"2017100004","smuChannel":"00","sensorLongitude":"113.0854218","sensorLatitude":"28.1481068"}]}]
     */

    private int code;
    private String msg;
    private List<DataBean> data;

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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * monitorTypeName : 沉降
         * detections : [{"monitorPoint":"CJ15","smuNumber":"2017100005","smuChannel":"02","sensorLongitude":"119.5481900000","sensorLatitude":"26.6657100000"},{"monitorPoint":"CJ16","smuNumber":"2017100005","smuChannel":"02","sensorLongitude":"119.5481900000","sensorLatitude":"26.6657100000"},{"monitorPoint":"CJ17","smuNumber":"2017100005","smuChannel":"02","sensorLongitude":"11","sensorLatitude":"11"},{"monitorPoint":"CJ18","smuNumber":"2017100005","smuChannel":"02","sensorLongitude":"11","sensorLatitude":"11"},{"monitorPoint":"CJ19","smuNumber":"2017100005","smuChannel":"02","sensorLongitude":"11","sensorLatitude":"11"},{"monitorPoint":"CJ1a","smuNumber":"2017100005","smuChannel":"02","sensorLongitude":"11","sensorLatitude":"11"},{"monitorPoint":"CJ1b","smuNumber":"2017100005","smuChannel":"02","sensorLongitude":"11","sensorLatitude":"11"},{"monitorPoint":"CJ1c","smuNumber":"2017100005","smuChannel":"02","sensorLongitude":"11","sensorLatitude":"11"},{"monitorPoint":"CJ1d","smuNumber":"2017100005","smuChannel":"02","sensorLongitude":"11","sensorLatitude":"11"},{"monitorPoint":"CJ1e","smuNumber":"2017100005","smuChannel":"02","sensorLongitude":"11","sensorLatitude":"11"}]
         */

        private String monitorTypeName;
        private String tableName;
        private List<DetectionsBean> detections;

        public String getMonitorTypeName() {
            return monitorTypeName;
        }

        public void setMonitorTypeName(String monitorTypeName) {
            this.monitorTypeName = monitorTypeName;
        }

        public String getTableName() {
            return tableName;
        }

        public void setTableName(String tableName) {
            this.tableName = tableName;
        }

        public List<DetectionsBean> getDetections() {
            return detections;
        }

        public void setDetections(List<DetectionsBean> detections) {
            this.detections = detections;
        }

        public static class DetectionsBean {
            /**
             * monitorPoint : CJ15
             * smuNumber : 2017100005
             * smuChannel : 02
             * sensorLongitude : 119.5481900000
             * sensorLatitude : 26.6657100000
             */

            private String monitorPoint;
            private String smuNumber;
            private String smuChannel;
            private String sensorLatitude;
            private String sensorLongitude;
            private String sensorNumber;

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

            public String getSensorLongitude() {
                return sensorLongitude;
            }

            public void setSensorLongitude(String sensorLongitude) {
                this.sensorLongitude = sensorLongitude;
            }

            public String getSensorLatitude() {
                return sensorLatitude;
            }

            public void setSensorLatitude(String sensorLatitude) {
                this.sensorLatitude = sensorLatitude;
            }

            public String getSensorNumber() {
                return sensorNumber;
            }

            public void setSensorNumber(String sensorNumber) {
                this.sensorNumber = sensorNumber;
            }
        }
    }
}
