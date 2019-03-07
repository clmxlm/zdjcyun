package com.zdjc.zdjcyun.mvp.entity;

import java.util.ArrayList;

/**
 * @author ClmXlm
 * @create 2019/1/18
 * @Describe
 */
public class MonitorTypeNameEntity {


    /**
     * code : 0
     * msg : ”操作成功”
     * data : [{"monitorType":19,"monitorTypeName":"沉降"},{"monitorType":20,"monitorTypeName":"收敛"},{"monitorType":21,"monitorTypeName":"水平位移"}]
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
         * monitorType : 19
         * monitorTypeName : 沉降
         */

        private int monitorType;
        private String monitorTypeName;

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
    }
}
