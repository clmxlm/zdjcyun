package com.zdjc.zdjcyun.mvp.entity;


import java.util.ArrayList;

public class WarningEntity {


    /**
     * code : 0
     * msg : 查询成功
     * data : {"total":2,"alarmList":[{"alarmId":1039,"projectId":264,"projectName":"广西贵港至隆安高速公路边坡k41_400左侧","monitorPoint":"CJ05","smuNumber":"2017110010","sensorNumber":"05","alarmContext":"变化速率值：-4.6656，小于最小警戒值：-2.0000，正常值范围-2.0000~2.0000","createTime":"2018-05-18 20:00:29","alarmTypeName":"设备类告警","alarmStatusName":"已确认","realName":"曹增茂"},{"alarmId":1016,"projectId":261,"projectName":"广西贵港至隆安高速公路边坡k26_400右幅","monitorPoint":"CJ08","smuNumber":"2017110002","sensorNumber":"08","alarmContext":"总变化值：-13819.2760，小于最小警戒值：-30.0000，正常值范围-30.0000~30.0000","createTime":"2018-05-17 13:21:09","alarmTypeName":"设备类告警","alarmStatusName":"已确认","realName":"曹增茂"}]}
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
         * total : 2
         * alarmList : [{"alarmId":1039,"projectId":264,"projectName":"广西贵港至隆安高速公路边坡k41_400左侧","monitorPoint":"CJ05","smuNumber":"2017110010","sensorNumber":"05","alarmContext":"变化速率值：-4.6656，小于最小警戒值：-2.0000，正常值范围-2.0000~2.0000","createTime":"2018-05-18 20:00:29","alarmTypeName":"设备类告警","alarmStatusName":"已确认","realName":"曹增茂"},{"alarmId":1016,"projectId":261,"projectName":"广西贵港至隆安高速公路边坡k26_400右幅","monitorPoint":"CJ08","smuNumber":"2017110002","sensorNumber":"08","alarmContext":"总变化值：-13819.2760，小于最小警戒值：-30.0000，正常值范围-30.0000~30.0000","createTime":"2018-05-17 13:21:09","alarmTypeName":"设备类告警","alarmStatusName":"已确认","realName":"曹增茂"}]
         */

        private int total;
        private ArrayList<AlarmListBean> alarmList;

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public ArrayList<AlarmListBean> getAlarmList() {
            return alarmList;
        }

        public void setAlarmList(ArrayList<AlarmListBean> alarmList) {
            this.alarmList = alarmList;
        }


    }
}
