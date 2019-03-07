package com.zdjc.zdjcyun.mvp.entity;

/**
 * @author ClmXlm
 * @create 2019/1/7
 * @Describe
 */
public class AlarmEntity {


    /**
     * code : 0
     * msg : 查询首页告警数量成功
     * data : {"sensorErrorCount":1,"terminalErrorCount":0,"projectAlarmCount":6,"levelOneCount":5,"levelTwoCount":1,"levelThreeCount":0}
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
         * sensorErrorCount : 1
         * terminalErrorCount : 0
         * projectAlarmCount : 6
         * levelOneCount : 5
         * levelTwoCount : 1
         * levelThreeCount : 0
         */

        private int sensorErrorCount;
        private int terminalErrorCount;
        private int projectAlarmCount;
        private int levelOneCount;
        private int levelTwoCount;
        private int levelThreeCount;

        public int getSensorErrorCount() {
            return sensorErrorCount;
        }

        public void setSensorErrorCount(int sensorErrorCount) {
            this.sensorErrorCount = sensorErrorCount;
        }

        public int getTerminalErrorCount() {
            return terminalErrorCount;
        }

        public void setTerminalErrorCount(int terminalErrorCount) {
            this.terminalErrorCount = terminalErrorCount;
        }

        public int getProjectAlarmCount() {
            return projectAlarmCount;
        }

        public void setProjectAlarmCount(int projectAlarmCount) {
            this.projectAlarmCount = projectAlarmCount;
        }

        public int getLevelOneCount() {
            return levelOneCount;
        }

        public void setLevelOneCount(int levelOneCount) {
            this.levelOneCount = levelOneCount;
        }

        public int getLevelTwoCount() {
            return levelTwoCount;
        }

        public void setLevelTwoCount(int levelTwoCount) {
            this.levelTwoCount = levelTwoCount;
        }

        public int getLevelThreeCount() {
            return levelThreeCount;
        }

        public void setLevelThreeCount(int levelThreeCount) {
            this.levelThreeCount = levelThreeCount;
        }
    }
}
