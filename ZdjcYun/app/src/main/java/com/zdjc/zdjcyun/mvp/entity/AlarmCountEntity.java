package com.zdjc.zdjcyun.mvp.entity;

import java.util.ArrayList;

/**
 * @author ClmXlm
 * @create 2019/1/14
 * @Describe
 */
public class AlarmCountEntity {


    /**
     * code : 0
     * msg : 查询粗略统计成功
     * data : {"total":3,"AlarmCounts":[{"projectId":1,"projectName":"首个项目","sectorName":"区间1","sectorId":1,"scId":11,"itemName":"区间","alarmTotal":7,"level1":4,"level2":1,"level3":2,"tError":1,"sError":0},{"projectId":1,"projectName":"首个项目","sectorName":"区间2","sectorId":2,"scId":11,"itemName":"区间","alarmTotal":1,"level1":1,"level2":0,"level3":0,"tError":1,"sError":0},{"projectId":3,"projectName":"地铁六号线","sectorName":"长丰路站","sectorId":9,"scId":11,"itemName":"站点","alarmTotal":443,"level1":135,"level2":82,"level3":226,"tError":0,"sError":0}]}
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
         * AlarmCounts : [{"projectId":1,"projectName":"首个项目","sectorName":"区间1","sectorId":1,"scId":11,"itemName":"区间","alarmTotal":7,"level1":4,"level2":1,"level3":2,"tError":1,"sError":0},{"projectId":1,"projectName":"首个项目","sectorName":"区间2","sectorId":2,"scId":11,"itemName":"区间","alarmTotal":1,"level1":1,"level2":0,"level3":0,"tError":1,"sError":0},{"projectId":3,"projectName":"地铁六号线","sectorName":"长丰路站","sectorId":9,"scId":11,"itemName":"站点","alarmTotal":443,"level1":135,"level2":82,"level3":226,"tError":0,"sError":0}]
         */

        private int total;
        private ArrayList<AlarmCountsBean> AlarmCounts;

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public ArrayList<AlarmCountsBean> getAlarmCounts() {
            return AlarmCounts;
        }

        public void setAlarmCounts(ArrayList<AlarmCountsBean> AlarmCounts) {
            this.AlarmCounts = AlarmCounts;
        }

        public static class AlarmCountsBean {
            /**
             * projectId : 1
             * projectName : 首个项目
             * sectorName : 区间1
             * sectorId : 1
             * scId : 11
             * itemName : 区间
             * alarmTotal : 7
             * level1 : 4
             * level2 : 1
             * level3 : 2
             * tError : 1
             * sError : 0
             */

            private int projectId;
            private String projectName;
            private String sectorName;
            private int sectorId;
            private int scId;
            private String itemName;
            private int alarmTotal;
            private int level1;
            private int level2;
            private int level3;
            private int tError;
            private int sError;

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

            public int getScId() {
                return scId;
            }

            public void setScId(int scId) {
                this.scId = scId;
            }

            public String getItemName() {
                return itemName;
            }

            public void setItemName(String itemName) {
                this.itemName = itemName;
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

            public int getSError() {
                return sError;
            }

            public void setSError(int sError) {
                this.sError = sError;
            }
        }
    }
}
