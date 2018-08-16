package com.zdjc.zdjcyun.mvp.entity;


import com.bin.david.form.annotation.SmartColumn;

/**
 * Created by huang on 2017/11/1.
 */
public class WarningInfo {

    @SmartColumn(id =1,name = "告警ID",autoCount = true)
    private int warningId;
    @SmartColumn(id=2,name="用户名",autoCount = true)
    private String userName;
    @SmartColumn(id =3,name="所属项目",autoCount = true)
    private String projectName;
    @SmartColumn(id =4,name="所在测点",autoCount = true)
    private String monitorPoint;
    @SmartColumn(id =5,name="采集终端编号",autoCount = true)
    private String smuNumber;
    @SmartColumn(id =6,name="传感器编号",autoCount = true)
    private String sensorNumber;
    @SmartColumn(id =7,name="告警类型",autoCount = true)
    private String alarmTypeName;
    @SmartColumn(id =8,name="告警内容",autoCount = true)
    private String alarmContext;
    @SmartColumn(id =9,name="产生时间",autoCount = true)
    private String createTime;
    @SmartColumn(id =10,name="告警状态",autoCount = true)
    private String alarmStatusName;
    @SmartColumn(id =11,name="操作",autoCount = true)
    private String sure;


//    public WarningInfo(int warningId, String userName,
//                       String projectName, String monitorPoint,
//                       String smuNumber, String sensorNumber,
//                       String alarmTypeName,String alarmContext,
//                       String createTime,String alarmStatusName,String sure) {
//        this.warningId = warningId;
//        this.userName = userName;
//        this.projectName = projectName;
//        this.monitorPoint = monitorPoint;
//        this.smuNumber = smuNumber;
//        this.sensorNumber = sensorNumber;
//        this.alarmTypeName = alarmTypeName;
//        this.alarmContext = alarmContext;
//        this.createTime = createTime;
//        this.alarmStatusName = alarmStatusName;
//        this.sure = sure;
//    }

    public int getWarningId() {
        return warningId;
    }

    public void setWarningId(int warningId) {
        this.warningId = warningId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

    public String getAlarmTypeName() {
        return alarmTypeName;
    }

    public void setAlarmTypeName(String alarmTypeName) {
        this.alarmTypeName = alarmTypeName;
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

    public String getAlarmStatusName() {
        return alarmStatusName;
    }

    public void setAlarmStatusName(String alarmStatusName) {
        this.alarmStatusName = alarmStatusName;
    }

    public String getSure() {
        return sure;
    }

    public void setSure(String sure) {
        this.sure = sure;
    }
}
