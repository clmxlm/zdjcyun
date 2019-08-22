package com.zdjc.zdjcyun.mvp.entity;

import java.util.ArrayList;
import java.util.List;

public class MonitorTypePointNameEntity {


    /**
     * code : 0
     * msg : 操作成功
     * data : [{"monitorType":28,"monitorTypeName":"砼支撑轴力","monitorPointNumber":["ZCL07","ZCL08","ZCL09","ZCL10","ZCL11","ZCL12"]},{"monitorType":29,"monitorTypeName":"钢支撑轴力","monitorPointNumber":["ZCL2-7直","ZCL2-11直","ZCL2-12直","ZCL2-13斜","ZCL2-14斜","ZCL1","ZCL2","ZCL3","ZCL4","ZCL6"]}]
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
         * monitorType : 28
         * monitorTypeName : 砼支撑轴力
         * monitorPointNumber : ["ZCL07","ZCL08","ZCL09","ZCL10","ZCL11","ZCL12"]
         */

        private int monitorType;
        private String monitorTypeName;
        private ArrayList<String> monitorPointNumber;

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

        public ArrayList<String> getMonitorPointNumber() {
            return monitorPointNumber;
        }

        public void setMonitorPointNumber(ArrayList<String> monitorPointNumber) {
            this.monitorPointNumber = monitorPointNumber;
        }
    }
}
