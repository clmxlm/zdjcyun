package com.zdjc.zdjcyun.mvp.entity;

import java.util.ArrayList;

/**
 * @author ClmXlm
 * @create 2019/1/15
 * @Describe
 */
public class AlarmDetailEntity {


    /**
     * code : 0
     * msg : 查询告警信息成功
     * data : {"total":443,"AlarmInfo":[{"alarmId":322,"projectName":"地铁六号线","sectorName":"地铁六号线-长丰路站","monitorPointNumber":"ZCL07","terminalNumber":"240305004049739","sensorNumber":"ZCL7","alarmType":"数据类告警","alarmContext":"总变化值：3807.0751247716976，大于最高一级警戒值：3220.0","createTime":"2019-01-09 15:30:55","alarmStatus":"未确认","alarmLevel":"等级一"},{"alarmId":323,"projectName":"地铁六号线","sectorName":"地铁六号线-长丰路站","monitorPointNumber":"ZCL07","terminalNumber":"240305004049739","sensorNumber":"ZCL7","alarmType":"数据类告警","alarmContext":"总变化值：5698.903654089807，大于最高三级警戒值：4600.0","createTime":"2019-01-09 17:08:05","alarmStatus":"未确认","alarmLevel":"等级三"},{"alarmId":324,"projectName":"地铁六号线","sectorName":"地铁六号线-长丰路站","monitorPointNumber":"ZCL07","terminalNumber":"240305004049739","sensorNumber":"ZCL7","alarmType":"数据类告警","alarmContext":"总变化值：8493.83296510138，大于最高三级警戒值：4600.0","createTime":"2019-01-09 18:35:55","alarmStatus":"未确认","alarmLevel":"等级三"},{"alarmId":325,"projectName":"地铁六号线","sectorName":"地铁六号线-长丰路站","monitorPointNumber":"ZCL07","terminalNumber":"240305004049739","sensorNumber":"ZCL7","alarmType":"数据类告警","alarmContext":"总变化值：4089.8151711088776，大于最高一级警戒值：3220.0","createTime":"2019-01-09 18:54:48","alarmStatus":"未确认","alarmLevel":"等级一"},{"alarmId":326,"projectName":"地铁六号线","sectorName":"地铁六号线-长丰路站","monitorPointNumber":"ZCL07","terminalNumber":"240305004049739","sensorNumber":"ZCL7","alarmType":"数据类告警","alarmContext":"总变化值：4959.503145909522，大于最高三级警戒值：4600.0","createTime":"2019-01-09 18:59:25","alarmStatus":"已确认","alarmLevel":"等级三"},{"alarmId":327,"projectName":"地铁六号线","sectorName":"地铁六号线-长丰路站","monitorPointNumber":"ZCL07","terminalNumber":"240305004049739","sensorNumber":"ZCL7","alarmType":"数据类告警","alarmContext":"总变化值：3918.397483653336，大于最高一级警戒值：3220.0","createTime":"2019-01-09 19:04:03","alarmStatus":"未确认","alarmLevel":"等级一"},{"alarmId":328,"projectName":"地铁六号线","sectorName":"地铁六号线-长丰路站","monitorPointNumber":"ZCL07","terminalNumber":"240305004049739","sensorNumber":"ZCL7","alarmType":"数据类告警","alarmContext":"总变化值：5360.838364614198，大于最高三级警戒值：4600.0","createTime":"2019-01-09 19:08:41","alarmStatus":"未确认","alarmLevel":"等级三"},{"alarmId":329,"projectName":"地铁六号线","sectorName":"地铁六号线-长丰路站","monitorPointNumber":"ZCL07","terminalNumber":"240305004049739","sensorNumber":"ZCL7","alarmType":"数据类告警","alarmContext":"总变化值：4729.197260471879，大于最高三级警戒值：4600.0","createTime":"2019-01-09 19:13:17","alarmStatus":"未确认","alarmLevel":"等级三"},{"alarmId":330,"projectName":"地铁六号线","sectorName":"地铁六号线-长丰路站","monitorPointNumber":"ZCL07","terminalNumber":"240305004049739","sensorNumber":"ZCL7","alarmType":"数据类告警","alarmContext":"总变化值：6283.944223274251，大于最高三级警戒值：4600.0","createTime":"2019-01-09 19:17:55","alarmStatus":"未确认","alarmLevel":"等级三"},{"alarmId":331,"projectName":"地铁六号线","sectorName":"地铁六号线-长丰路站","monitorPointNumber":"ZCL07","terminalNumber":"240305004049739","sensorNumber":"ZCL7","alarmType":"数据类告警","alarmContext":"总变化值：5140.32620107035，大于最高三级警戒值：4600.0","createTime":"2019-01-09 19:22:33","alarmStatus":"未确认","alarmLevel":"等级三"}]}
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
         * total : 443
         * AlarmInfo : [{"alarmId":322,"projectName":"地铁六号线","sectorName":"地铁六号线-长丰路站","monitorPointNumber":"ZCL07","terminalNumber":"240305004049739","sensorNumber":"ZCL7","alarmType":"数据类告警","alarmContext":"总变化值：3807.0751247716976，大于最高一级警戒值：3220.0","createTime":"2019-01-09 15:30:55","alarmStatus":"未确认","alarmLevel":"等级一"},{"alarmId":323,"projectName":"地铁六号线","sectorName":"地铁六号线-长丰路站","monitorPointNumber":"ZCL07","terminalNumber":"240305004049739","sensorNumber":"ZCL7","alarmType":"数据类告警","alarmContext":"总变化值：5698.903654089807，大于最高三级警戒值：4600.0","createTime":"2019-01-09 17:08:05","alarmStatus":"未确认","alarmLevel":"等级三"},{"alarmId":324,"projectName":"地铁六号线","sectorName":"地铁六号线-长丰路站","monitorPointNumber":"ZCL07","terminalNumber":"240305004049739","sensorNumber":"ZCL7","alarmType":"数据类告警","alarmContext":"总变化值：8493.83296510138，大于最高三级警戒值：4600.0","createTime":"2019-01-09 18:35:55","alarmStatus":"未确认","alarmLevel":"等级三"},{"alarmId":325,"projectName":"地铁六号线","sectorName":"地铁六号线-长丰路站","monitorPointNumber":"ZCL07","terminalNumber":"240305004049739","sensorNumber":"ZCL7","alarmType":"数据类告警","alarmContext":"总变化值：4089.8151711088776，大于最高一级警戒值：3220.0","createTime":"2019-01-09 18:54:48","alarmStatus":"未确认","alarmLevel":"等级一"},{"alarmId":326,"projectName":"地铁六号线","sectorName":"地铁六号线-长丰路站","monitorPointNumber":"ZCL07","terminalNumber":"240305004049739","sensorNumber":"ZCL7","alarmType":"数据类告警","alarmContext":"总变化值：4959.503145909522，大于最高三级警戒值：4600.0","createTime":"2019-01-09 18:59:25","alarmStatus":"已确认","alarmLevel":"等级三"},{"alarmId":327,"projectName":"地铁六号线","sectorName":"地铁六号线-长丰路站","monitorPointNumber":"ZCL07","terminalNumber":"240305004049739","sensorNumber":"ZCL7","alarmType":"数据类告警","alarmContext":"总变化值：3918.397483653336，大于最高一级警戒值：3220.0","createTime":"2019-01-09 19:04:03","alarmStatus":"未确认","alarmLevel":"等级一"},{"alarmId":328,"projectName":"地铁六号线","sectorName":"地铁六号线-长丰路站","monitorPointNumber":"ZCL07","terminalNumber":"240305004049739","sensorNumber":"ZCL7","alarmType":"数据类告警","alarmContext":"总变化值：5360.838364614198，大于最高三级警戒值：4600.0","createTime":"2019-01-09 19:08:41","alarmStatus":"未确认","alarmLevel":"等级三"},{"alarmId":329,"projectName":"地铁六号线","sectorName":"地铁六号线-长丰路站","monitorPointNumber":"ZCL07","terminalNumber":"240305004049739","sensorNumber":"ZCL7","alarmType":"数据类告警","alarmContext":"总变化值：4729.197260471879，大于最高三级警戒值：4600.0","createTime":"2019-01-09 19:13:17","alarmStatus":"未确认","alarmLevel":"等级三"},{"alarmId":330,"projectName":"地铁六号线","sectorName":"地铁六号线-长丰路站","monitorPointNumber":"ZCL07","terminalNumber":"240305004049739","sensorNumber":"ZCL7","alarmType":"数据类告警","alarmContext":"总变化值：6283.944223274251，大于最高三级警戒值：4600.0","createTime":"2019-01-09 19:17:55","alarmStatus":"未确认","alarmLevel":"等级三"},{"alarmId":331,"projectName":"地铁六号线","sectorName":"地铁六号线-长丰路站","monitorPointNumber":"ZCL07","terminalNumber":"240305004049739","sensorNumber":"ZCL7","alarmType":"数据类告警","alarmContext":"总变化值：5140.32620107035，大于最高三级警戒值：4600.0","createTime":"2019-01-09 19:22:33","alarmStatus":"未确认","alarmLevel":"等级三"}]
         */

        private int total;
        private ArrayList<AlarmInfoBean> AlarmInfo;

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public ArrayList<AlarmInfoBean> getAlarmInfo() {
            return AlarmInfo;
        }

        public void setAlarmInfo(ArrayList<AlarmInfoBean> AlarmInfo) {
            this.AlarmInfo = AlarmInfo;
        }

        public static class AlarmInfoBean {
            /**
             * alarmId : 322
             * projectName : 地铁六号线
             * sectorName : 地铁六号线-长丰路站
             * monitorPointNumber : ZCL07
             * terminalNumber : 240305004049739
             * sensorNumber : ZCL7
             * alarmType : 数据类告警
             * alarmContext : 总变化值：3807.0751247716976，大于最高一级警戒值：3220.0
             * createTime : 2019-01-09 15:30:55
             * alarmStatus : 未确认
             * alarmLevel : 等级一
             */

            private int alarmId;
            private String projectName;
            private String sectorName;
            private String monitorPointNumber;
            private String terminalNumber;
            private String sensorNumber;
            private String alarmType;
            private String alarmContext;
            private String createTime;
            private String alarmStatus;
            private String alarmLevel;

            public int getAlarmId() {
                return alarmId;
            }

            public void setAlarmId(int alarmId) {
                this.alarmId = alarmId;
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

            public String getMonitorPointNumber() {
                return monitorPointNumber;
            }

            public void setMonitorPointNumber(String monitorPointNumber) {
                this.monitorPointNumber = monitorPointNumber;
            }

            public String getTerminalNumber() {
                return terminalNumber;
            }

            public void setTerminalNumber(String terminalNumber) {
                this.terminalNumber = terminalNumber;
            }

            public String getSensorNumber() {
                return sensorNumber;
            }

            public void setSensorNumber(String sensorNumber) {
                this.sensorNumber = sensorNumber;
            }

            public String getAlarmType() {
                return alarmType;
            }

            public void setAlarmType(String alarmType) {
                this.alarmType = alarmType;
            }

            public String getAlarmContext() {
                return alarmContext;
            }

            public void setAlarmContext(String alarmContext) {
                this.alarmContext = alarmContext;
            }

            public String getCreateTime() {
                return createTime;
            }

            public void setCreateTime(String createTime) {
                this.createTime = createTime;
            }

            public String getAlarmStatus() {
                return alarmStatus;
            }

            public void setAlarmStatus(String alarmStatus) {
                this.alarmStatus = alarmStatus;
            }

            public String getAlarmLevel() {
                return alarmLevel;
            }

            public void setAlarmLevel(String alarmLevel) {
                this.alarmLevel = alarmLevel;
            }
        }
    }
}
