package com.zdjc.zdjcyun.mvp.entity;

import java.util.ArrayList;

/**
 * @author ClmXlm
 * @create 2019/2/18
 * @Describe
 */
public class HazardsEntity {


    /**
     * code : 0
     * msg : 查询危险告警源成功
     * data : [{"id":1,"sectorId":9,"jobActivity":"基坑开挖","dangerousSource":"支护不及时","riskResult":"塌方伤人","raowcL":3,"raowcE":6,"raowcC":15,"raowcD":270,"significantRisk":"重要","tense":"将来","status":"异常","controlMeasures":"unknown"},{"id":9,"sectorId":9,"jobActivity":"基础灌注","dangerousSource":"罐车停靠位置不妥","riskResult":"贯车塌陷","raowcL":1,"raowcE":6,"raowcC":7,"raowcD":42,"significantRisk":"不重要","tense":"将来","status":"异常","controlMeasures":"unknown"},{"id":10,"sectorId":9,"jobActivity":"基坑石方爆破","dangerousSource":"无证作业","riskResult":"人员伤亡","raowcL":1,"raowcE":1,"raowcC":40,"raowcD":40,"significantRisk":"不重要","tense":"将来","status":"异常","controlMeasures":"unknown"},{"id":11,"sectorId":9,"jobActivity":"钻孔桩施工","dangerousSource":"作业人员没有戴安全帽","riskResult":"人员伤亡","raowcL":3,"raowcE":1,"raowcC":15,"raowcD":41,"significantRisk":"不重要","tense":"将来","status":"异常","controlMeasures":"unkonwn"},{"id":12,"sectorId":9,"jobActivity":"墩台模板施工","dangerousSource":"吊装模板时吊钩不稳","riskResult":"摔坏模板砸伤额人员","raowcL":1,"raowcE":6,"raowcC":7,"raowcD":42,"significantRisk":"不重要","tense":"将来","status":"异常","controlMeasures":"unknown"},{"id":13,"sectorId":9,"jobActivity":"墩台模板施工","dangerousSource":"脚手架搭设时未系安全带","riskResult":"施工人员坠落伤亡","raowcL":1,"raowcE":6,"raowcC":15,"raowcD":90,"significantRisk":"不重要","tense":"将来","status":"异常","controlMeasures":"unkonwn"}]
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
         * id : 1
         * sectorId : 9
         * jobActivity : 基坑开挖
         * dangerousSource : 支护不及时
         * riskResult : 塌方伤人
         * raowcL : 3
         * raowcE : 6
         * raowcC : 15
         * raowcD : 270
         * significantRisk : 重要
         * tense : 将来
         * status : 异常
         * controlMeasures : unknown
         */

        private int id;
        private int sectorId;
        private String jobActivity;
        private String dangerousSource;
        private String riskResult;
        private int raowcL;
        private int raowcE;
        private int raowcC;
        private int raowcD;
        private String significantRisk;
        private String tense;
        private String status;
        private String controlMeasures;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getSectorId() {
            return sectorId;
        }

        public void setSectorId(int sectorId) {
            this.sectorId = sectorId;
        }

        public String getJobActivity() {
            return jobActivity;
        }

        public void setJobActivity(String jobActivity) {
            this.jobActivity = jobActivity;
        }

        public String getDangerousSource() {
            return dangerousSource;
        }

        public void setDangerousSource(String dangerousSource) {
            this.dangerousSource = dangerousSource;
        }

        public String getRiskResult() {
            return riskResult;
        }

        public void setRiskResult(String riskResult) {
            this.riskResult = riskResult;
        }

        public int getRaowcL() {
            return raowcL;
        }

        public void setRaowcL(int raowcL) {
            this.raowcL = raowcL;
        }

        public int getRaowcE() {
            return raowcE;
        }

        public void setRaowcE(int raowcE) {
            this.raowcE = raowcE;
        }

        public int getRaowcC() {
            return raowcC;
        }

        public void setRaowcC(int raowcC) {
            this.raowcC = raowcC;
        }

        public int getRaowcD() {
            return raowcD;
        }

        public void setRaowcD(int raowcD) {
            this.raowcD = raowcD;
        }

        public String getSignificantRisk() {
            return significantRisk;
        }

        public void setSignificantRisk(String significantRisk) {
            this.significantRisk = significantRisk;
        }

        public String getTense() {
            return tense;
        }

        public void setTense(String tense) {
            this.tense = tense;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getControlMeasures() {
            return controlMeasures;
        }

        public void setControlMeasures(String controlMeasures) {
            this.controlMeasures = controlMeasures;
        }
    }
}
