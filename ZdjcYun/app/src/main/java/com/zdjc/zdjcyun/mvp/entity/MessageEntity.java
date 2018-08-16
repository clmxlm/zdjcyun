package com.zdjc.zdjcyun.mvp.entity;

import com.google.gson.annotations.SerializedName;

public class MessageEntity {


    /**
     * 261 : Alarm [projectId=261, projectName=广西贵港至隆安高速公路边坡k26_400右幅, monitorPoint=CJ0C, smuNumber=2017110006, sensorNumber=0C, alarmContext=总变化值：33.6159，大于最大警戒值：30.0000，正常值范围-30.0000~30.0000, createTime=Tue Aug 14 11:11:27 CST 2018, alarmTypeName=数据类告警, alarmStatusName=未确认, realName=曹增茂, operate=操作]
     * Operate : 操作
     * alarmContext : 总变化值：33.6159，大于最大警戒值：30.0000，正常值范围-30.0000~30.0000
     * alarmStatusName : 未确认
     * alarmTypeName : 数据类告警
     * createTime : Tue Aug 14 11:11:27 CST 2018
     * getAlarmId : 3423
     * monitorPoint : CJ0C
     * msg : 广西贵港至隆安高速公路边坡k26_400右幅于Tue Aug 14 11:11:27 CST 2018产生告警信息
     * projectName : 广西贵港至隆安高速公路边坡k26_400右幅
     * realName : 曹增茂
     * sensorNumber : 0C
     * smuNumber : 2017110006
     */

    @SerializedName("261")
    private String _$261;
    private String projectId;
    private String Operate;
    private String alarmContext;
    private String alarmStatusName;
    private String alarmTypeName;
    private String createTime;
    private String getAlarmId;
    private String monitorPoint;
    private String msg;
    private String projectName;
    private String realName;
    private String sensorNumber;
    private String smuNumber;

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String get_$261() {
        return _$261;
    }

    public void set_$261(String _$261) {
        this._$261 = _$261;
    }

    public String getOperate() {
        return Operate;
    }

    public void setOperate(String Operate) {
        this.Operate = Operate;
    }

    public String getAlarmContext() {
        return alarmContext;
    }

    public void setAlarmContext(String alarmContext) {
        this.alarmContext = alarmContext;
    }

    public String getAlarmStatusName() {
        return alarmStatusName;
    }

    public void setAlarmStatusName(String alarmStatusName) {
        this.alarmStatusName = alarmStatusName;
    }

    public String getAlarmTypeName() {
        return alarmTypeName;
    }

    public void setAlarmTypeName(String alarmTypeName) {
        this.alarmTypeName = alarmTypeName;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getGetAlarmId() {
        return getAlarmId;
    }

    public void setGetAlarmId(String getAlarmId) {
        this.getAlarmId = getAlarmId;
    }

    public String getMonitorPoint() {
        return monitorPoint;
    }

    public void setMonitorPoint(String monitorPoint) {
        this.monitorPoint = monitorPoint;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getSensorNumber() {
        return sensorNumber;
    }

    public void setSensorNumber(String sensorNumber) {
        this.sensorNumber = sensorNumber;
    }

    public String getSmuNumber() {
        return smuNumber;
    }

    public void setSmuNumber(String smuNumber) {
        this.smuNumber = smuNumber;
    }
}
