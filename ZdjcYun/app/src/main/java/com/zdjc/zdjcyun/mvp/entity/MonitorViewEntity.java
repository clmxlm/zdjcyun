package com.zdjc.zdjcyun.mvp.entity;

import java.util.List;

/**
 * @author ClmXlm
 * @create 2019/1/8
 * @Describe
 */
public class MonitorViewEntity {


    /**
     * code : 0
     * msg : 查询项目监测概览成功
     * data : [{"projectId":2,"projectName":"地铁二号线","projectDescription":"二号线监测项目","alarmCount":0,"list":[{"projectId":2,"monitorTypeName":"水平位移","level1":0,"level2":0,"level3":0}]}]
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
         * projectId : 2
         * projectName : 地铁二号线
         * projectDescription : 二号线监测项目
         * alarmCount : 0
         * list : [{"projectId":2,"monitorTypeName":"水平位移","level1":0,"level2":0,"level3":0}]
         */

        private int projectId;
        private String projectName;
        private String projectDescription;
        private int alarmCount;
        private List<ListBean> list;

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

        public String getProjectDescription() {
            return projectDescription;
        }

        public void setProjectDescription(String projectDescription) {
            this.projectDescription = projectDescription;
        }

        public int getAlarmCount() {
            return alarmCount;
        }

        public void setAlarmCount(int alarmCount) {
            this.alarmCount = alarmCount;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * projectId : 2
             * monitorTypeName : 水平位移
             * level1 : 0
             * level2 : 0
             * level3 : 0
             */

            private int projectId;
            private String monitorTypeName;
            private int level1;
            private int level2;
            private int level3;

            public int getProjectId() {
                return projectId;
            }

            public void setProjectId(int projectId) {
                this.projectId = projectId;
            }

            public String getMonitorTypeName() {
                return monitorTypeName;
            }

            public void setMonitorTypeName(String monitorTypeName) {
                this.monitorTypeName = monitorTypeName;
            }

            public int getLevel1() {
                return level1;
            }

            public void setLevel1(int level1) {
                this.level1 = level1;
            }

            public int getLevel2() {
                return level2;
            }

            public void setLevel2(int level2) {
                this.level2 = level2;
            }

            public int getLevel3() {
                return level3;
            }

            public void setLevel3(int level3) {
                this.level3 = level3;
            }
        }
    }
}
