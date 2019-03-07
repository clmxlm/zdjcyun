package com.zdjc.zdjcyun.mvp.entity;

import java.util.ArrayList;

/**
 * @author ClmXlm
 * @create 2019/1/11
 * @Describe
 */
public class SensorEntity {


    /**
     * code : 0
     * msg : 操作成功
     * data : [{"sensorId":20,"terminalChannel":"3","sensorNumber":"300849","manufacturer":"长沙鲲鹏检测技术有限公司","sensorModel":"KPJC-ZXX","sensorName":"弦式轴力计","sensorRange":"0-Z300A","sensorAccuracy":"0.1Hz","productDate":"2018-12-20 00:00:00","endDate":"2018-12-20 00:00:00","sensorStatus":"使用中"},{"sensorId":21,"terminalChannel":"1","sensorNumber":"300720","manufacturer":"长沙鲲鹏检测技术有限公司","sensorModel":"KPJC-ZXX","sensorName":"弦式轴力计","sensorRange":"0-Z300A","sensorAccuracy":"0.1Hz","productDate":"2018-12-20 00:00:00","endDate":"2018-12-20 00:00:00","sensorStatus":"使用中"},{"sensorId":23,"terminalChannel":"2","sensorNumber":"300863","manufacturer":"长沙鲲鹏检测技术有限公司","sensorModel":"KPJC-ZXX","sensorName":"弦式轴力计","sensorRange":"0-Z300A","sensorAccuracy":"0.1Hz","productDate":"2018-12-20 00:00:00","endDate":"2018-12-20 00:00:00","sensorStatus":"使用中"}]
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
         * sensorId : 20
         * terminalChannel : 3
         * sensorNumber : 300849
         * manufacturer : 长沙鲲鹏检测技术有限公司
         * sensorModel : KPJC-ZXX
         * sensorName : 弦式轴力计
         * sensorRange : 0-Z300A
         * sensorAccuracy : 0.1Hz
         * productDate : 2018-12-20 00:00:00
         * endDate : 2018-12-20 00:00:00
         * sensorStatus : 使用中
         */

        private int sensorId;
        private String terminalChannel;
        private String sensorNumber;
        private String manufacturer;
        private String sensorModel;
        private String sensorName;
        private String sensorRange;
        private String sensorAccuracy;
        private String productDate;
        private String endDate;
        private String sensorStatus;

        public int getSensorId() {
            return sensorId;
        }

        public void setSensorId(int sensorId) {
            this.sensorId = sensorId;
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

        public String getManufacturer() {
            return manufacturer;
        }

        public void setManufacturer(String manufacturer) {
            this.manufacturer = manufacturer;
        }

        public String getSensorModel() {
            return sensorModel;
        }

        public void setSensorModel(String sensorModel) {
            this.sensorModel = sensorModel;
        }

        public String getSensorName() {
            return sensorName;
        }

        public void setSensorName(String sensorName) {
            this.sensorName = sensorName;
        }

        public String getSensorRange() {
            return sensorRange;
        }

        public void setSensorRange(String sensorRange) {
            this.sensorRange = sensorRange;
        }

        public String getSensorAccuracy() {
            return sensorAccuracy;
        }

        public void setSensorAccuracy(String sensorAccuracy) {
            this.sensorAccuracy = sensorAccuracy;
        }

        public String getProductDate() {
            return productDate;
        }

        public void setProductDate(String productDate) {
            this.productDate = productDate;
        }

        public String getEndDate() {
            return endDate;
        }

        public void setEndDate(String endDate) {
            this.endDate = endDate;
        }

        public String getSensorStatus() {
            return sensorStatus;
        }

        public void setSensorStatus(String sensorStatus) {
            this.sensorStatus = sensorStatus;
        }
    }
}
