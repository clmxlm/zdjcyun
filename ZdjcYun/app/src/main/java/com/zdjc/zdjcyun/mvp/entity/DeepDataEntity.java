package com.zdjc.zdjcyun.mvp.entity;

import java.util.ArrayList;
import java.util.List;

public class DeepDataEntity {


    /**
     * code : 0
     * msg : 操作成功
     * data : {"mapType":10,"deepDatas":[{"sensorDeep":1.01,"measuredDataX":-57.462782,"singleChangeX":80.675654,"totalChangeX":75.797796,"speedChangeX":2.161426,"measuredDataY":-54.720142,"singleChangeY":54.330566,"totalChangeY":-17.967782,"speedChangeY":-39.7365},{"sensorDeep":2.25,"measuredDataX":-8.4429,"singleChangeX":-15.275348,"totalChangeX":-0.788878,"speedChangeX":-19.122502,"measuredDataY":1.970812,"singleChangeY":32.293692,"totalChangeY":29.246456,"speedChangeY":6.952056},{"sensorDeep":3.01,"measuredDataX":-17.761894,"singleChangeX":28.382196,"totalChangeX":31.243986,"speedChangeX":-2.76111,"measuredDataY":41.386236,"singleChangeY":14.928174,"totalChangeY":-61.469118,"speedChangeY":70.627834},{"sensorDeep":4.65,"measuredDataX":-13.118684,"singleChangeX":53.252058,"totalChangeX":-53.4938,"speedChangeX":43.868914,"measuredDataY":-15.847024,"singleChangeY":-21.313954,"totalChangeY":77.421948,"speedChangeY":5.460726},{"sensorDeep":5.6,"measuredDataX":-47.293736,"singleChangeX":10.855544,"totalChangeX":30.627806,"speedChangeX":-95.362778,"measuredDataY":29.96462,"singleChangeY":-66.334768,"totalChangeY":24.004276,"speedChangeY":-25.45919},{"sensorDeep":5.8,"measuredDataX":-4.128362,"singleChangeX":-68.870414,"totalChangeX":-38.75895,"speedChangeX":97.348856,"measuredDataY":30.744914,"singleChangeY":44.642316,"totalChangeY":12.718622,"speedChangeY":-18.497474}],"sensorNumbers":["SBWYCGQ0019","SBWYCGQ0020","SBWYCGQ0021","SBWYCGQ0022","SBWYCGQ0023","SBWYCGQ0024"]}
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
         * mapType : 10
         * deepDatas : [{"sensorDeep":1.01,"measuredDataX":-57.462782,"singleChangeX":80.675654,"totalChangeX":75.797796,"speedChangeX":2.161426,"measuredDataY":-54.720142,"singleChangeY":54.330566,"totalChangeY":-17.967782,"speedChangeY":-39.7365},{"sensorDeep":2.25,"measuredDataX":-8.4429,"singleChangeX":-15.275348,"totalChangeX":-0.788878,"speedChangeX":-19.122502,"measuredDataY":1.970812,"singleChangeY":32.293692,"totalChangeY":29.246456,"speedChangeY":6.952056},{"sensorDeep":3.01,"measuredDataX":-17.761894,"singleChangeX":28.382196,"totalChangeX":31.243986,"speedChangeX":-2.76111,"measuredDataY":41.386236,"singleChangeY":14.928174,"totalChangeY":-61.469118,"speedChangeY":70.627834},{"sensorDeep":4.65,"measuredDataX":-13.118684,"singleChangeX":53.252058,"totalChangeX":-53.4938,"speedChangeX":43.868914,"measuredDataY":-15.847024,"singleChangeY":-21.313954,"totalChangeY":77.421948,"speedChangeY":5.460726},{"sensorDeep":5.6,"measuredDataX":-47.293736,"singleChangeX":10.855544,"totalChangeX":30.627806,"speedChangeX":-95.362778,"measuredDataY":29.96462,"singleChangeY":-66.334768,"totalChangeY":24.004276,"speedChangeY":-25.45919},{"sensorDeep":5.8,"measuredDataX":-4.128362,"singleChangeX":-68.870414,"totalChangeX":-38.75895,"speedChangeX":97.348856,"measuredDataY":30.744914,"singleChangeY":44.642316,"totalChangeY":12.718622,"speedChangeY":-18.497474}]
         * sensorNumbers : ["SBWYCGQ0019","SBWYCGQ0020","SBWYCGQ0021","SBWYCGQ0022","SBWYCGQ0023","SBWYCGQ0024"]
         */

        private int mapType;
        private ArrayList<DeepDatasBean> deepDatas;
        private ArrayList<String> sensorNumbers;

        public int getMapType() {
            return mapType;
        }

        public void setMapType(int mapType) {
            this.mapType = mapType;
        }

        public ArrayList<DeepDatasBean> getDeepDatas() {
            return deepDatas;
        }

        public void setDeepDatas(ArrayList<DeepDatasBean> deepDatas) {
            this.deepDatas = deepDatas;
        }

        public ArrayList<String> getSensorNumbers() {
            return sensorNumbers;
        }

        public void setSensorNumbers(ArrayList<String> sensorNumbers) {
            this.sensorNumbers = sensorNumbers;
        }

        public static class DeepDatasBean {
            /**
             * sensorDeep : 1.01
             * measuredDataX : -57.462782
             * singleChangeX : 80.675654
             * totalChangeX : 75.797796
             * speedChangeX : 2.161426
             * measuredDataY : -54.720142
             * singleChangeY : 54.330566
             * totalChangeY : -17.967782
             * speedChangeY : -39.7365
             */

            private double sensorDeep;
            private double measuredDataX;
            private double singleChangeX;
            private double totalChangeX;
            private double speedChangeX;
            private double measuredDataY;
            private double singleChangeY;
            private double totalChangeY;
            private double speedChangeY;

            public double getSensorDeep() {
                return sensorDeep;
            }

            public void setSensorDeep(double sensorDeep) {
                this.sensorDeep = sensorDeep;
            }

            public double getMeasuredDataX() {
                return measuredDataX;
            }

            public void setMeasuredDataX(double measuredDataX) {
                this.measuredDataX = measuredDataX;
            }

            public double getSingleChangeX() {
                return singleChangeX;
            }

            public void setSingleChangeX(double singleChangeX) {
                this.singleChangeX = singleChangeX;
            }

            public double getTotalChangeX() {
                return totalChangeX;
            }

            public void setTotalChangeX(double totalChangeX) {
                this.totalChangeX = totalChangeX;
            }

            public double getSpeedChangeX() {
                return speedChangeX;
            }

            public void setSpeedChangeX(double speedChangeX) {
                this.speedChangeX = speedChangeX;
            }

            public double getMeasuredDataY() {
                return measuredDataY;
            }

            public void setMeasuredDataY(double measuredDataY) {
                this.measuredDataY = measuredDataY;
            }

            public double getSingleChangeY() {
                return singleChangeY;
            }

            public void setSingleChangeY(double singleChangeY) {
                this.singleChangeY = singleChangeY;
            }

            public double getTotalChangeY() {
                return totalChangeY;
            }

            public void setTotalChangeY(double totalChangeY) {
                this.totalChangeY = totalChangeY;
            }

            public double getSpeedChangeY() {
                return speedChangeY;
            }

            public void setSpeedChangeY(double speedChangeY) {
                this.speedChangeY = speedChangeY;
            }
        }
    }
}
