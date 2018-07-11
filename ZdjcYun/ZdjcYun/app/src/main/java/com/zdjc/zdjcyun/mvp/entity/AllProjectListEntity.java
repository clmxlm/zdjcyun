package com.zdjc.zdjcyun.mvp.entity;


import java.util.List;

public class AllProjectListEntity{


    /**
     * code : 0
     * msg : 操作成功
     * data : [{"projectId":176,"projectName":"恩瑞万豪酒店基坑支护工程地铁2号线区域在线监测","projectAddress":"湖南长沙","weatherAddress":"长沙","projectBeginTime":"2017-10-30 14:53:26","projectEndTime":"2018-10-31 09:53:28","projectDescription":"恩瑞万豪酒店基坑支护工程地铁2号线区域在线监测","projectPrincipal":"胡泽超","projectSensorNumber":2,"projectSmuNumber":23,"alCount":0,"projectTypeName":"基坑项目","projectStatusName":"未启动"},{"projectId":249,"projectName":"福州市轨道交通2号线下穿既有铁路工程","projectAddress":"福建福州","weatherAddress":"福州","projectBeginTime":"2017-11-24 00:00:00","projectEndTime":"2018-01-26 00:00:00","projectDescription":"描述福州市轨道交通2号线下穿既有铁路工程。","projectPrincipal":"张金龙","projectSensorNumber":1,"projectSmuNumber":9,"alCount":0,"projectTypeName":"基坑项目","projectStatusName":"已暂停"},{"projectId":260,"projectName":"衢宁铁路5标填方段路基施工临近既有在线监测","projectAddress":"福建宁德","weatherAddress":"宁德","projectBeginTime":"2017-12-25 00:00:00","projectEndTime":"2018-12-26 00:00:00","projectDescription":"衢宁铁路5标填方段路基施工期间对临近既有线沉降进行变形监测。","projectPrincipal":"张金龙","projectSensorNumber":1,"projectSmuNumber":8,"alCount":0,"projectTypeName":"基坑项目","projectStatusName":"未启动"},{"projectId":261,"projectName":"广西贵港至隆安高速公路边坡k26_400右幅","projectAddress":"广西贵港市港北区","weatherAddress":"贵港","projectBeginTime":"2018-01-22 00:00:00","projectEndTime":"2019-01-01 00:00:00","projectDescription":"贵隆高速边坡K26+400路段(右幅)在线实时监测","projectPrincipal":"曹增茂","projectSensorNumber":1,"projectSmuNumber":6,"alCount":0,"projectTypeName":"边坡项目","projectStatusName":"未启动"},{"projectId":262,"projectName":"广西贵港至隆安高速公路边坡k26_400左幅","projectAddress":"广西贵港市港北区","weatherAddress":"贵港","projectBeginTime":"2018-01-22 00:00:00","projectEndTime":"2019-01-01 00:00:00","projectDescription":"贵隆高速边坡K26+400路段（左幅）在线实时监测","projectPrincipal":"曹增茂","projectSensorNumber":2,"projectSmuNumber":5,"alCount":0,"projectTypeName":"边坡项目","projectStatusName":"未启动"},{"projectId":263,"projectName":"广西贵港至隆安高速公路边坡k39_290右侧","projectAddress":"广西贵港市港北区","weatherAddress":"贵港","projectBeginTime":"2018-01-22 00:00:00","projectEndTime":"2019-01-01 00:00:00","projectDescription":"贵隆高速边坡k39+290(右侧)在线实时监测","projectPrincipal":"曹增茂","projectSensorNumber":2,"projectSmuNumber":7,"alCount":0,"projectTypeName":"边坡项目","projectStatusName":"未启动"},{"projectId":264,"projectName":"广西贵港至隆安高速公路边坡k41_400左侧","projectAddress":"广西贵港市港北区","weatherAddress":"贵港","projectBeginTime":"2018-01-22 00:00:00","projectEndTime":"2019-01-01 00:00:00","projectDescription":"贵隆高速边坡k41+400(左侧)在线实时监测","projectPrincipal":"曹增茂","projectSensorNumber":2,"projectSmuNumber":7,"alCount":0,"projectTypeName":"边坡项目","projectStatusName":"未启动"},{"projectId":249,"projectName":"福州市轨道交通2号线下穿既有铁路工程","projectAddress":"福建福州","weatherAddress":"福州","projectBeginTime":"2017-11-24 00:00:00","projectEndTime":"2018-01-26 00:00:00","projectDescription":"描述福州市轨道交通2号线下穿既有铁路工程。","projectPrincipal":"胡泽超","projectSensorNumber":1,"projectSmuNumber":9,"alCount":0,"projectTypeName":"基坑项目","projectStatusName":"已暂停"},{"projectId":176,"projectName":"恩瑞万豪酒店基坑支护工程地铁2号线区域在线监测","projectAddress":"湖南长沙","weatherAddress":"长沙","projectBeginTime":"2017-10-30 14:53:26","projectEndTime":"2018-10-31 09:53:28","projectDescription":"恩瑞万豪酒店基坑支护工程地铁2号线区域在线监测","projectPrincipal":"孔成","projectSensorNumber":2,"projectSmuNumber":23,"alCount":0,"projectTypeName":"基坑项目","projectStatusName":"未启动"}]
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
         * projectId : 176
         * projectName : 恩瑞万豪酒店基坑支护工程地铁2号线区域在线监测
         * projectAddress : 湖南长沙
         * weatherAddress : 长沙
         * private String projectLongitude;112.9369115584
         * private String projectLatitude;28.2115599233
         * projectBeginTime : 2017-10-30 14:53:26
         * projectEndTime : 2018-10-31 09:53:28
         * projectDescription : 恩瑞万豪酒店基坑支护工程地铁2号线区域在线监测
         * projectPrincipal : 胡泽超
         * projectSensorNumber : 2
         * projectSmuNumber : 23
         * alCount : 0
         * projectTypeName : 基坑项目
         * projectStatusName : 未启动
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
    }
}
