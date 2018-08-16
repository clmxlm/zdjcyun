package com.zdjc.zdjcyun.mvp.entity;

import java.util.ArrayList;

public class ProjectListEntity {


    /**
     * code : 0
     * msg : 操作成功
     * data : {"total":3,"rows":[{"projectId":176,"projectName":"恩瑞万豪酒店基坑支护工程地铁2号线区域在线监测","projectType":5,"projectAddress":"湖南长沙","weatherAddress":"长沙","projectLongitude":"112.9369115584","projectLatitude":"28.2115599233","projectBeginTime":"2017-10-30 14:53:26","projectEndTime":"2018-10-31 09:53:28","projectStatus":23,"projectImageUrl":"images/a.jpg","projectDescription":"恩瑞万豪酒店基坑支护工程地铁2号线区域在线监测","countSmunumber":0,"countSensorNumber":0,"countAlarmConfirm":0,"countAlarmUnConfirm":0,"projectSchedule":0},{"projectId":249,"projectName":"福州市轨道交通2号线下穿既有铁路工程","projectType":5,"projectAddress":"福建福州","weatherAddress":"福州","projectLongitude":"119.376479","projectLatitude":"26.069292","projectBeginTime":"2017-11-24 00:00:00","projectEndTime":"2018-10-26 00:00:00","projectStatus":23,"projectImageUrl":"images/b.jpg","projectDescription":"描述福州市轨道交通2号线下穿既有铁路工程。","countSmunumber":0,"countSensorNumber":0,"countAlarmConfirm":0,"countAlarmUnConfirm":0,"projectSchedule":0}]}
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
         * total : 3
         * rows : [{"projectId":176,"projectName":"恩瑞万豪酒店基坑支护工程地铁2号线区域在线监测","projectType":5,"projectAddress":"湖南长沙","weatherAddress":"长沙","projectLongitude":"112.9369115584","projectLatitude":"28.2115599233","projectBeginTime":"2017-10-30 14:53:26","projectEndTime":"2018-10-31 09:53:28","projectStatus":23,"projectImageUrl":"images/a.jpg","projectDescription":"恩瑞万豪酒店基坑支护工程地铁2号线区域在线监测","countSmunumber":0,"countSensorNumber":0,"countAlarmConfirm":0,"countAlarmUnConfirm":0,"projectSchedule":0},{"projectId":249,"projectName":"福州市轨道交通2号线下穿既有铁路工程","projectType":5,"projectAddress":"福建福州","weatherAddress":"福州","projectLongitude":"119.376479","projectLatitude":"26.069292","projectBeginTime":"2017-11-24 00:00:00","projectEndTime":"2018-10-26 00:00:00","projectStatus":23,"projectImageUrl":"images/b.jpg","projectDescription":"描述福州市轨道交通2号线下穿既有铁路工程。","countSmunumber":0,"countSensorNumber":0,"countAlarmConfirm":0,"countAlarmUnConfirm":0,"projectSchedule":0}]
         */

        private int total;
        private ArrayList<RowsBean> rows;

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public ArrayList<RowsBean> getRows() {
            return rows;
        }

        public void setRows(ArrayList<RowsBean> rows) {
            this.rows = rows;
        }

        public static class RowsBean {
            /**
             * projectId : 176
             * projectName : 恩瑞万豪酒店基坑支护工程地铁2号线区域在线监测
             * projectType : 5
             * projectAddress : 湖南长沙
             * weatherAddress : 长沙
             * projectLongitude : 112.9369115584
             * projectLatitude : 28.2115599233
             * projectBeginTime : 2017-10-30 14:53:26
             * projectEndTime : 2018-10-31 09:53:28
             * projectStatus : 23
             * projectImageUrl : images/a.jpg
             * projectDescription : 恩瑞万豪酒店基坑支护工程地铁2号线区域在线监测
             * countSmunumber : 0
             * countSensorNumber : 0
             * countAlarmConfirm : 0
             * countAlarmUnConfirm : 0
             * projectSchedule : 0
             */

            private int projectId;
            private String projectName;
            private int projectType;
            private String projectAddress;
            private String weatherAddress;
            private String projectLongitude;
            private String projectLatitude;
            private String projectBeginTime;
            private String projectEndTime;
            private int projectStatus;
            private String projectImageUrl;
            private String projectDescription;
            private int countSmunumber;
            private int countSensorNumber;
            private int countAlarmConfirm;
            private int countAlarmUnConfirm;
            private int projectSchedule;

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

            public int getProjectType() {
                return projectType;
            }

            public void setProjectType(int projectType) {
                this.projectType = projectType;
            }

            public String getProjectAddress() {
                return projectAddress;
            }

            public void setProjectAddress(String projectAddress) {
                this.projectAddress = projectAddress;
            }

            public String getWeatherAddress() {
                return weatherAddress;
            }

            public void setWeatherAddress(String weatherAddress) {
                this.weatherAddress = weatherAddress;
            }

            public String getProjectLongitude() {
                return projectLongitude;
            }

            public void setProjectLongitude(String projectLongitude) {
                this.projectLongitude = projectLongitude;
            }

            public String getProjectLatitude() {
                return projectLatitude;
            }

            public void setProjectLatitude(String projectLatitude) {
                this.projectLatitude = projectLatitude;
            }

            public String getProjectBeginTime() {
                return projectBeginTime;
            }

            public void setProjectBeginTime(String projectBeginTime) {
                this.projectBeginTime = projectBeginTime;
            }

            public String getProjectEndTime() {
                return projectEndTime;
            }

            public void setProjectEndTime(String projectEndTime) {
                this.projectEndTime = projectEndTime;
            }

            public int getProjectStatus() {
                return projectStatus;
            }

            public void setProjectStatus(int projectStatus) {
                this.projectStatus = projectStatus;
            }

            public String getProjectImageUrl() {
                return projectImageUrl;
            }

            public void setProjectImageUrl(String projectImageUrl) {
                this.projectImageUrl = projectImageUrl;
            }

            public String getProjectDescription() {
                return projectDescription;
            }

            public void setProjectDescription(String projectDescription) {
                this.projectDescription = projectDescription;
            }

            public int getCountSmunumber() {
                return countSmunumber;
            }

            public void setCountSmunumber(int countSmunumber) {
                this.countSmunumber = countSmunumber;
            }

            public int getCountSensorNumber() {
                return countSensorNumber;
            }

            public void setCountSensorNumber(int countSensorNumber) {
                this.countSensorNumber = countSensorNumber;
            }

            public int getCountAlarmConfirm() {
                return countAlarmConfirm;
            }

            public void setCountAlarmConfirm(int countAlarmConfirm) {
                this.countAlarmConfirm = countAlarmConfirm;
            }

            public int getCountAlarmUnConfirm() {
                return countAlarmUnConfirm;
            }

            public void setCountAlarmUnConfirm(int countAlarmUnConfirm) {
                this.countAlarmUnConfirm = countAlarmUnConfirm;
            }

            public int getProjectSchedule() {
                return projectSchedule;
            }

            public void setProjectSchedule(int projectSchedule) {
                this.projectSchedule = projectSchedule;
            }
        }
    }
}
