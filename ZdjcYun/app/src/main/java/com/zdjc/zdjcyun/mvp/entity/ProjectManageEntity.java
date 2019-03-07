package com.zdjc.zdjcyun.mvp.entity;

import java.util.List;

/**
 * @author ClmXlm
 * @create 2019/1/9
 * @Describe
 */
public class ProjectManageEntity {


    /**
     * code : 0
     * msg : 查询项目管理信息成功
     * data : [{"projectId":1,"projectName":"首个项目","list":[{"projectId":1,"projectName":"首个项目","sectorName":"区间1","sectorId":1,"itemName":"地铁","sectorStatus":"进行中","errorStatus":"异常","sectorAddress":"地址1","alarmTotal":5,"level1":4,"level2":1,"level3":0,"tError":5,"sectorBeginTime":"2018-12-01 01:43:09.0","sectorEndTime":"2018-12-28 01:43:09.0"},{"projectId":1,"projectName":"首个项目","sectorName":"区间2","sectorId":2,"itemName":"地铁","sectorStatus":"进行中","errorStatus":"异常","sectorAddress":"地址2","alarmTotal":1,"level1":1,"level2":0,"level3":0,"tError":0,"sectorBeginTime":"2018-11-23 01:43:37.0","sectorEndTime":"2018-12-21 01:43:37.0"}]},{"projectId":2,"projectName":"地铁二号线","list":[{"projectId":2,"projectName":"地铁二号线","sectorName":"地铁区间","sectorId":4,"itemName":"地铁","sectorStatus":"未开始","errorStatus":"正常","sectorAddress":"地铁地址","alarmTotal":0,"level1":0,"level2":0,"level3":0,"tError":0,"sectorBeginTime":"2018-12-01 01:43:09.0","sectorEndTime":"2018-12-28 01:43:09.0"}]}]
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
         * list : [{"projectId":1,"projectName":"首个项目","sectorName":"区间1","sectorId":1,"itemName":"地铁","sectorStatus":"进行中","errorStatus":"异常","sectorAddress":"地址1","alarmTotal":5,"level1":4,"level2":1,"level3":0,"tError":5,"sectorBeginTime":"2018-12-01 01:43:09.0","sectorEndTime":"2018-12-28 01:43:09.0"},{"projectId":1,"projectName":"首个项目","sectorName":"区间2","sectorId":2,"itemName":"地铁","sectorStatus":"进行中","errorStatus":"异常","sectorAddress":"地址2","alarmTotal":1,"level1":1,"level2":0,"level3":0,"tError":0,"sectorBeginTime":"2018-11-23 01:43:37.0","sectorEndTime":"2018-12-21 01:43:37.0"}]
         */

        private int projectId;
        private String projectName;
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

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * projectId : 1
             * projectName : 首个项目
             * sectorName : 区间1
             * sectorId : 1
             * itemName : 地铁
             * sectorStatus : 进行中
             * errorStatus : 异常
             * sectorAddress : 地址1
             * alarmTotal : 5
             * level1 : 4
             * level2 : 1
             * level3 : 0
             * tError : 5
             * sectorBeginTime : 2018-12-01 01:43:09.0
             * sectorEndTime : 2018-12-28 01:43:09.0
             */

            private int projectId;
            private String projectName;
            private String sectorName;
            private int sectorId;
            private String itemName;
            private String sectorStatus;
            private String errorStatus;
            private String sectorAddress;
            private int alarmTotal;
            private int level1;
            private int level2;
            private int level3;
            private int tError;
            private String sectorBeginTime;
            private String sectorEndTime;

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

            public String getSectorName() {
                return sectorName;
            }

            public void setSectorName(String sectorName) {
                this.sectorName = sectorName;
            }

            public int getSectorId() {
                return sectorId;
            }

            public void setSectorId(int sectorId) {
                this.sectorId = sectorId;
            }

            public String getItemName() {
                return itemName;
            }

            public void setItemName(String itemName) {
                this.itemName = itemName;
            }

            public String getSectorStatus() {
                return sectorStatus;
            }

            public void setSectorStatus(String sectorStatus) {
                this.sectorStatus = sectorStatus;
            }

            public String getErrorStatus() {
                return errorStatus;
            }

            public void setErrorStatus(String errorStatus) {
                this.errorStatus = errorStatus;
            }

            public String getSectorAddress() {
                return sectorAddress;
            }

            public void setSectorAddress(String sectorAddress) {
                this.sectorAddress = sectorAddress;
            }

            public int getAlarmTotal() {
                return alarmTotal;
            }

            public void setAlarmTotal(int alarmTotal) {
                this.alarmTotal = alarmTotal;
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

            public int getTError() {
                return tError;
            }

            public void setTError(int tError) {
                this.tError = tError;
            }

            public String getSectorBeginTime() {
                return sectorBeginTime;
            }

            public void setSectorBeginTime(String sectorBeginTime) {
                this.sectorBeginTime = sectorBeginTime;
            }

            public String getSectorEndTime() {
                return sectorEndTime;
            }

            public void setSectorEndTime(String sectorEndTime) {
                this.sectorEndTime = sectorEndTime;
            }
        }
    }
}
