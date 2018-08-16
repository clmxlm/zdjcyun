package com.zdjc.zdjcyun.mvp.entity;

import com.bin.david.form.annotation.SmartColumn;
import com.bin.david.form.annotation.SmartTable;

@SmartTable(name = "warning")
public class AlarmListBean {
    /**
     * alarmId : 1039
     * projectId : 264
     * projectName : 广西贵港至隆安高速公路边坡k41_400左侧
     * monitorPoint : CJ05
     * smuNumber : 2017110010
     * sensorNumber : 05
     * alarmContext : 变化速率值：-4.6656，小于最小警戒值：-2.0000，正常值范围-2.0000~2.0000
     * createTime : 2018-05-18 20:00:29
     * alarmTypeName : 设备类告警
     * alarmStatusName : 已确认
     * realName : 曹增茂
     */
    private int projectId;
    private int alarmId;
    @SmartColumn(id =1,name="所属项目")
    private String projectName;
//    @SmartColumn(id =2,name="所在测点")
    private String monitorPoint;
    @SmartColumn(id =2,name="采集终端编号")
    private String smuNumber;
    @SmartColumn(id =3,name="传感器编号")
    private String sensorNumber;
    @SmartColumn(id =4,name="告警内容")
    private String alarmContext;
    @SmartColumn(id =5,name="产生时间")
    private String createTime;
    @SmartColumn(id =6,name="告警类型")
    private String alarmTypeName;
    @SmartColumn(id =7,name="告警状态")
    private String alarmStatusName;
    private String realName;
    @SmartColumn(id =8,name="操作")
    private String operate;

    public int getAlarmId() {
        return alarmId;
    }

    public void setAlarmId(int alarmId) {
        this.alarmId = alarmId;
    }

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

    public String getMonitorPoint() {
        return monitorPoint;
    }

    public void setMonitorPoint(String monitorPoint) {
        this.monitorPoint = monitorPoint;
    }

    public String getSmuNumber() {
        return smuNumber;
    }

    public void setSmuNumber(String smuNumber) {
        this.smuNumber = smuNumber;
    }

    public String getSensorNumber() {
        return sensorNumber;
    }

    public void setSensorNumber(String sensorNumber) {
        this.sensorNumber = sensorNumber;
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

    public String getAlarmTypeName() {
        return alarmTypeName;
    }

    public void setAlarmTypeName(String alarmTypeName) {
        this.alarmTypeName = alarmTypeName;
    }

    public String getAlarmStatusName() {
        return alarmStatusName;
    }

    public void setAlarmStatusName(String alarmStatusName) {
        this.alarmStatusName = alarmStatusName;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getOperate() {
        return operate;
    }

    public void setOperate(String operate) {
        this.operate = operate;
    }
}


