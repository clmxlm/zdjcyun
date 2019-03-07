package com.zdjc.zdjcyun.mvp.entity;

import java.util.List;

/**
 * @author ClmXlm
 * @create 2019/1/8
 * @Describe
 */
public class AlarmNewsEntity {


    /**
     * code : 0
     * msg : 查询概览项目告警
     * data : [{"projectId":1,"projectName":"首个项目","sectorId":1,"sectorName":"区间1","alarmId":1,"alarmContext":"啊哈？"},{"projectId":1,"projectName":"首个项目","sectorId":1,"sectorName":"区间1","alarmId":3,"alarmContext":"呵呵"}]
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
         * projectId : 1
         * projectName : 首个项目
         * sectorId : 1
         * sectorName : 区间1
         * alarmId : 1
         * alarmContext : 啊哈？
         */

        private int projectId;
        private String projectName;
        private int sectorId;
        private String sectorName;
        private int alarmId;
        private String alarmContext;

        public int getProjectId() {
            return projectId;
        }

        public void setProjectId(int projectId) {
            this.projectId = projectId;
        }

        public String getProjectName() {
            return projectName;
        }

        public void setProjectName(String projectName) {
            this.projectName = projectName;
        }

        public int getSectorId() {
            return sectorId;
        }

        public void setSectorId(int sectorId) {
            this.sectorId = sectorId;
        }

        public String getSectorName() {
            return sectorName;
        }

        public void setSectorName(String sectorName) {
            this.sectorName = sectorName;
        }

        public int getAlarmId() {
            return alarmId;
        }

        public void setAlarmId(int alarmId) {
            this.alarmId = alarmId;
        }

        public String getAlarmContext() {
            return alarmContext;
        }

        public void setAlarmContext(String alarmContext) {
            this.alarmContext = alarmContext;
        }
    }
}
