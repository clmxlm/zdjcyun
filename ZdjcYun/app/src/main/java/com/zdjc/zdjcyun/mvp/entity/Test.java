package com.zdjc.zdjcyun.mvp.entity;

import java.util.List;

public class Test {


    /**
     * code : 0
     * msg : 操作成功
     * data : [{"projectId":261,"projectName":"广西贵港至隆安高速公路边坡k26_400右幅","projectAddress":"广西贵港市港北区","weatherAddress":"贵港","projectLongitude":"109.7149008433","projectLatitude":"23.2314736585","projectBeginTime":"2018-01-22 00:00:00","projectEndTime":"2019-01-01 00:00:00","projectDescription":"贵隆高速边坡K26+400路段(右幅)在线实时监测","projectPrincipal":"曹增茂","projectSensorNumber":1,"projectSmuNumber":7,"alCount":0,"projectTypeName":"边坡项目","projectStatusName":"已启动","countSmunumber":0,"countSensorNumber":0,"countAlarmConfirm":0,"countAlarmUnConfirm":0,"projectSchedule":0,"projectParameterList":[{"parameterName":"水平位移","monitorPointList":[{"monitorPoint":"SPWY04"},{"monitorPoint":"SPWY12"},{"monitorPoint":"SPWY14"}]},{"parameterName":"沉降","monitorPointList":[{"monitorPoint":"CJ09"},{"monitorPoint":"CJ0C"},{"monitorPoint":"CJ01"},{"monitorPoint":"CJ08"}]}]},{"projectId":262,"projectName":"广西贵港至隆安高速公路边坡k26_400左幅","projectAddress":"广西贵港市港北区","weatherAddress":"贵港","projectLongitude":"109.7141283671","projectLatitude":"23.2322525466","projectBeginTime":"2018-01-22 00:00:00","projectEndTime":"2019-01-01 00:00:00","projectDescription":"贵隆高速边坡K26+400路段（左幅）在线实时监测","projectPrincipal":"曹增茂","projectSensorNumber":1,"projectSmuNumber":5,"alCount":0,"projectTypeName":"边坡项目","projectStatusName":"已启动","countSmunumber":0,"countSensorNumber":0,"countAlarmConfirm":0,"countAlarmUnConfirm":0,"projectSchedule":0,"projectParameterList":[{"parameterName":"沉降","monitorPointList":[{"monitorPoint":"CJ0f"},{"monitorPoint":"CJ03"},{"monitorPoint":"CJ0E"}]},{"parameterName":"水平位移","monitorPointList":[{"monitorPoint":"SPWY18"},{"monitorPoint":"SPWY20"}]}]},{"projectId":263,"projectName":"广西贵港至隆安高速公路边坡k39_290右侧","projectAddress":"广西贵港市港北区","weatherAddress":"贵港","projectLongitude":"109.6108970117","projectLatitude":"23.1665230024","projectBeginTime":"2018-01-22 00:00:00","projectEndTime":"2019-01-01 00:00:00","projectDescription":"贵隆高速边坡k39+290(右侧)在线实时监测","projectPrincipal":"曹增茂","projectSensorNumber":1,"projectSmuNumber":5,"alCount":0,"projectTypeName":"边坡项目","projectStatusName":"已启动","countSmunumber":0,"countSensorNumber":0,"countAlarmConfirm":0,"countAlarmUnConfirm":0,"projectSchedule":0,"projectParameterList":[{"parameterName":"沉降","monitorPointList":[{"monitorPoint":"CJ02"},{"monitorPoint":"CJ07"},{"monitorPoint":"CJ0D"}]},{"parameterName":"水平位移","monitorPointList":[{"monitorPoint":"SPWY06"},{"monitorPoint":"SPWY16"}]}]},{"projectId":264,"projectName":"广西贵港至隆安高速公路边坡k41_400左侧","projectAddress":"广西贵港市港北区","weatherAddress":"贵港","projectLongitude":"109.5903504015","projectLatitude":"23.1632653567","projectBeginTime":"2018-01-22 00:00:00","projectEndTime":"2019-01-01 00:00:00","projectDescription":"贵隆高速边坡k41+400(左侧)在线实时监测","projectPrincipal":"曹增茂","projectSensorNumber":1,"projectSmuNumber":5,"alCount":0,"projectTypeName":"边坡项目","projectStatusName":"已启动","countSmunumber":0,"countSensorNumber":0,"countAlarmConfirm":0,"countAlarmUnConfirm":0,"projectSchedule":0,"projectParameterList":[{"parameterName":"沉降","monitorPointList":[{"monitorPoint":"CJ04"},{"monitorPoint":"CJ05"},{"monitorPoint":"CJ06"}]},{"parameterName":"水平位移","monitorPointList":[{"monitorPoint":"SPWY15"},{"monitorPoint":"SPWY03"}]}]}]
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
         * projectId : 261
         * projectName : 广西贵港至隆安高速公路边坡k26_400右幅
         * projectAddress : 广西贵港市港北区
         * weatherAddress : 贵港
         * projectLongitude : 109.7149008433
         * projectLatitude : 23.2314736585
         * projectBeginTime : 2018-01-22 00:00:00
         * projectEndTime : 2019-01-01 00:00:00
         * projectDescription : 贵隆高速边坡K26+400路段(右幅)在线实时监测
         * projectPrincipal : 曹增茂
         * projectSensorNumber : 1
         * projectSmuNumber : 7
         * alCount : 0
         * projectTypeName : 边坡项目
         * projectStatusName : 已启动
         * countSmunumber : 0
         * countSensorNumber : 0
         * countAlarmConfirm : 0
         * countAlarmUnConfirm : 0
         * projectSchedule : 0
         * projectParameterList : [{"parameterName":"水平位移","monitorPointList":[{"monitorPoint":"SPWY04"},{"monitorPoint":"SPWY12"},{"monitorPoint":"SPWY14"}]},{"parameterName":"沉降","monitorPointList":[{"monitorPoint":"CJ09"},{"monitorPoint":"CJ0C"},{"monitorPoint":"CJ01"},{"monitorPoint":"CJ08"}]}]
         */

        private int projectId;
        private String projectName;
        private String projectAddress;
        private String weatherAddress;
        private String projectLongitude;
        private String projectLatitude;
        private String projectBeginTime;
        private String projectEndTime;
        private String projectDescription;
        private String projectPrincipal;
        private int projectSensorNumber;
        private int projectSmuNumber;
        private int alCount;
        private String projectTypeName;
        private String projectStatusName;
        private int countSmunumber;
        private int countSensorNumber;
        private int countAlarmConfirm;
        private int countAlarmUnConfirm;
        private int projectSchedule;
        private List<ProjectParameterListBean> projectParameterList;

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

        public String getProjectDescription() {
            return projectDescription;
        }

        public void setProjectDescription(String projectDescription) {
            this.projectDescription = projectDescription;
        }

        public String getProjectPrincipal() {
            return projectPrincipal;
        }

        public void setProjectPrincipal(String projectPrincipal) {
            this.projectPrincipal = projectPrincipal;
        }

        public int getProjectSensorNumber() {
            return projectSensorNumber;
        }

        public void setProjectSensorNumber(int projectSensorNumber) {
            this.projectSensorNumber = projectSensorNumber;
        }

        public int getProjectSmuNumber() {
            return projectSmuNumber;
        }

        public void setProjectSmuNumber(int projectSmuNumber) {
            this.projectSmuNumber = projectSmuNumber;
        }

        public int getAlCount() {
            return alCount;
        }

        public void setAlCount(int alCount) {
            this.alCount = alCount;
        }

        public String getProjectTypeName() {
            return projectTypeName;
        }

        public void setProjectTypeName(String projectTypeName) {
            this.projectTypeName = projectTypeName;
        }

        public String getProjectStatusName() {
            return projectStatusName;
        }

        public void setProjectStatusName(String projectStatusName) {
            this.projectStatusName = projectStatusName;
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

        public List<ProjectParameterListBean> getProjectParameterList() {
            return projectParameterList;
        }

        public void setProjectParameterList(List<ProjectParameterListBean> projectParameterList) {
            this.projectParameterList = projectParameterList;
        }

        public static class ProjectParameterListBean {
            /**
             * parameterName : 水平位移
             * monitorPointList : [{"monitorPoint":"SPWY04"},{"monitorPoint":"SPWY12"},{"monitorPoint":"SPWY14"}]
             */

            private String parameterName;
            private List<MonitorPointListBean> monitorPointList;

            public String getParameterName() {
                return parameterName;
            }

            public void setParameterName(String parameterName) {
                this.parameterName = parameterName;
            }

            public List<MonitorPointListBean> getMonitorPointList() {
                return monitorPointList;
            }

            public void setMonitorPointList(List<MonitorPointListBean> monitorPointList) {
                this.monitorPointList = monitorPointList;
            }

            public static class MonitorPointListBean {
                /**
                 * monitorPoint : SPWY04
                 */

                private String monitorPoint;

                public String getMonitorPoint() {
                    return monitorPoint;
                }

                public void setMonitorPoint(String monitorPoint) {
                    this.monitorPoint = monitorPoint;
                }
            }
        }
    }
}
