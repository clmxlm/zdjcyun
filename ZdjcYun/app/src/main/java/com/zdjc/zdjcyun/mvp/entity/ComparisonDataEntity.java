package com.zdjc.zdjcyun.mvp.entity;

import java.util.ArrayList;

/**
 * @author ClmXlm
 * @create 2019/1/18
 * @Describe
 */
public class ComparisonDataEntity {

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
        private int mapType;
        private ArrayList<ComparisonVOBean> comparisonVO;

        public int getMapType() {
            return mapType;
        }

        public void setMapType(int mapType) {
            this.mapType = mapType;
        }

        public ArrayList<ComparisonVOBean> getComparisonVO() {
            return comparisonVO;
        }

        public void setComparisonVO(ArrayList<ComparisonVOBean> comparisonVO) {
            this.comparisonVO = comparisonVO;
        }

        public static class ComparisonVOBean {

            private String monitorPointNumber;
            private ArrayList<ArrayList<Object>> measuredData;
            private ArrayList<ArrayList<Object>> singleChange;
            private ArrayList<ArrayList<Object>> totalChange;
            private ArrayList<ArrayList<Object>> speedChange;

            public String getMonitorPointNumber() {
                return monitorPointNumber;
            }

            public void setMonitorPointNumber(String monitorPointNumber) {
                this.monitorPointNumber = monitorPointNumber;
            }

            public ArrayList<ArrayList<Object>> getMeasuredData() {
                return measuredData;
            }

            public void setMeasuredData(ArrayList<ArrayList<Object>> measuredData) {
                this.measuredData = measuredData;
            }

            public ArrayList<ArrayList<Object>> getSingleChange() {
                return singleChange;
            }

            public void setSingleChange(ArrayList<ArrayList<Object>> singleChange) {
                this.singleChange = singleChange;
            }

            public ArrayList<ArrayList<Object>> getTotalChange() {
                return totalChange;
            }

            public void setTotalChange(ArrayList<ArrayList<Object>> totalChange) {
                this.totalChange = totalChange;
            }

            public ArrayList<ArrayList<Object>> getSpeedChange() {
                return speedChange;
            }

            public void setSpeedChange(ArrayList<ArrayList<Object>> speedChange) {
                this.speedChange = speedChange;
            }
        }
    }
}
