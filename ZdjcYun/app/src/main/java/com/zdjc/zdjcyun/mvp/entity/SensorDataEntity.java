package com.zdjc.zdjcyun.mvp.entity;

import java.util.ArrayList;

/**
 * @author ClmXlm
 * @create 2019/1/22
 * @Describe
 */
public class SensorDataEntity {


    /**
     * code : 0
     * msg : ”操作成功”
     * data : {"mapType":6,"commonDataVOs":[{"createDate":"2018-12-14 11:05:54","measuredData":1.1,"singleChange":23.234,"totalChange":34.1,"speedChange":23.1},{"createDate":"2018-12-14 10:54:54","measuredData":1.1,"singleChange":23.234,"totalChange":34.1,"speedChange":23.1},{"createDate":"2018-12-14 10:10:54","measuredData":1.1,"singleChange":23.234,"totalChange":34.1,"speedChange":23.1},{"createDate":"2018-12-14 10:20:54","measuredData":1.1,"singleChange":23.234,"totalChange":34.1,"speedChange":23.1},{"createDate":"2018-12-14 10:40:54","measuredData":1.1,"singleChange":23.234,"totalChange":34.1,"speedChange":23.1}]}
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
         * commonDataVOs : [{"createDate":"2018-12-14 11:05:54","measuredData":1.1,"singleChange":23.234,"totalChange":34.1,"speedChange":23.1},{"createDate":"2018-12-14 10:54:54","measuredData":1.1,"singleChange":23.234,"totalChange":34.1,"speedChange":23.1},{"createDate":"2018-12-14 10:10:54","measuredData":1.1,"singleChange":23.234,"totalChange":34.1,"speedChange":23.1},{"createDate":"2018-12-14 10:20:54","measuredData":1.1,"singleChange":23.234,"totalChange":34.1,"speedChange":23.1},{"createDate":"2018-12-14 10:40:54","measuredData":1.1,"singleChange":23.234,"totalChange":34.1,"speedChange":23.1}]
         */

        private int mapType;
        private ArrayList<CommonDataVOsBean> commonDataVOs;

        public int getMapType() {
            return mapType;
        }

        public void setMapType(int mapType) {
            this.mapType = mapType;
        }

        public ArrayList<CommonDataVOsBean> getCommonDataVOs() {
            return commonDataVOs;
        }

        public void setCommonDataVOs(ArrayList<CommonDataVOsBean> commonDataVOs) {
            this.commonDataVOs = commonDataVOs;
        }

        public static class CommonDataVOsBean {
            /**
             * createDate : 2018-12-14 11:05:54
             * measuredData : 1.1
             * singleChange : 23.234
             * totalChange : 34.1
             * speedChange : 23.1
             */

            private String createDate;
            private double measuredData;
            private double singleChange;
            private double totalChange;
            private double speedChange;

            public String getCreateDate() {
                return createDate;
            }

            public void setCreateDate(String createDate) {
                this.createDate = createDate;
            }

            public double getMeasuredData() {
                return measuredData;
            }

            public void setMeasuredData(double measuredData) {
                this.measuredData = measuredData;
            }

            public double getSingleChange() {
                return singleChange;
            }

            public void setSingleChange(double singleChange) {
                this.singleChange = singleChange;
            }

            public double getTotalChange() {
                return totalChange;
            }

            public void setTotalChange(double totalChange) {
                this.totalChange = totalChange;
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
