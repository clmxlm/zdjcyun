package com.zdjc.zdjcyun.mvp.entity;

import java.util.List;
import java.util.Map;

/**
 * @author ClmXlm
 * @create 2019/1/10
 * @Describe
 */
public class BasicInformationEntity {


    /**
     * code : 0
     * msg : 操作成功
     * data : {"sectorId":9,"sectorName":"长丰路站","sectorDescription":"长丰路站为6号线工程第7个站。车站位于桐梓坡路与麓松路交叉口，沿桐梓坡路东西向布置。车站总长230m，标准段宽度21.1m。车站有效站台中心里程DK19+668.700，车站设计起点里程DK19+520.900，车站设计终点里程DK19+750.900。","sectorTypeName":"地铁","sectorLongitude":"112.8750790819","sectorLatitude":"28.2265291197","sectorAddress":"长沙地铁六号线长丰路站","sectorTime":"2018-10-05至2018-12-31","companyInfo":{"监测单位":"中大检测","施工单位":"中建五局"},"monitorBasis":["《建筑基坑支护技术规程》 JGJ120-2012","《建筑基坑工程检测技术规范》 GB50497-2009","《城市轨道交通工程检测技术规范》 GB50911-2013","《建筑变形测量规范》 JGJ8-2016","《建筑地基基础工程施工质量验收规范》 GB50202-2002","《国家一、二等水准测量规范》 GB/T12897-2006","《工程测量规范》 GB50026-2007","《岩土锚杆与喷射混凝土支护工程技术规范》 GB50086-2015","《建筑地基基础设计规范》 GB50007-2011"],"monitorTypeName":["砼支撑轴力","钢支撑轴力"]}
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
         * sectorId : 9
         * sectorName : 长丰路站
         * sectorDescription : 长丰路站为6号线工程第7个站。车站位于桐梓坡路与麓松路交叉口，沿桐梓坡路东西向布置。车站总长230m，标准段宽度21.1m。车站有效站台中心里程DK19+668.700，车站设计起点里程DK19+520.900，车站设计终点里程DK19+750.900。
         * sectorTypeName : 地铁
         * sectorLongitude : 112.8750790819
         * sectorLatitude : 28.2265291197
         * sectorAddress : 长沙地铁六号线长丰路站
         * sectorTime : 2018-10-05至2018-12-31
         * companyInfo : {"监测单位":"中大检测","施工单位":"中建五局"}
         * monitorBasis : ["《建筑基坑支护技术规程》 JGJ120-2012","《建筑基坑工程检测技术规范》 GB50497-2009","《城市轨道交通工程检测技术规范》 GB50911-2013","《建筑变形测量规范》 JGJ8-2016","《建筑地基基础工程施工质量验收规范》 GB50202-2002","《国家一、二等水准测量规范》 GB/T12897-2006","《工程测量规范》 GB50026-2007","《岩土锚杆与喷射混凝土支护工程技术规范》 GB50086-2015","《建筑地基基础设计规范》 GB50007-2011"]
         * monitorTypeName : ["砼支撑轴力","钢支撑轴力"]
         */

        private int sectorId;
        private String sectorName;
        private String sectorDescription;
        private String sectorTypeName;
        private String sectorLongitude;
        private String sectorLatitude;
        private String sectorAddress;
        private String sectorTime;
        private Map<String,String> companyInfo;
        private List<String> monitorBasis;
        private List<String> monitorTypeName;

        public int getSectorId() {
            return sectorId;
        }

        public void setSectorId(int sectorId) {
            this.sectorId = sectorId;
        }

        public String getSectorName() {
            return sectorName;
        }

        public void setSectorName(String sectorName) {
            this.sectorName = sectorName;
        }

        public String getSectorDescription() {
            return sectorDescription;
        }

        public void setSectorDescription(String sectorDescription) {
            this.sectorDescription = sectorDescription;
        }

        public String getSectorTypeName() {
            return sectorTypeName;
        }

        public void setSectorTypeName(String sectorTypeName) {
            this.sectorTypeName = sectorTypeName;
        }

        public String getSectorLongitude() {
            return sectorLongitude;
        }

        public void setSectorLongitude(String sectorLongitude) {
            this.sectorLongitude = sectorLongitude;
        }

        public String getSectorLatitude() {
            return sectorLatitude;
        }

        public void setSectorLatitude(String sectorLatitude) {
            this.sectorLatitude = sectorLatitude;
        }

        public String getSectorAddress() {
            return sectorAddress;
        }

        public void setSectorAddress(String sectorAddress) {
            this.sectorAddress = sectorAddress;
        }

        public String getSectorTime() {
            return sectorTime;
        }

        public void setSectorTime(String sectorTime) {
            this.sectorTime = sectorTime;
        }

        public Map<String, String> getCompanyInfo() {
            return companyInfo;
        }

        public void setCompanyInfo(Map<String, String> companyInfo) {
            this.companyInfo = companyInfo;
        }

        public List<String> getMonitorBasis() {
            return monitorBasis;
        }

        public void setMonitorBasis(List<String> monitorBasis) {
            this.monitorBasis = monitorBasis;
        }

        public List<String> getMonitorTypeName() {
            return monitorTypeName;
        }

        public void setMonitorTypeName(List<String> monitorTypeName) {
            this.monitorTypeName = monitorTypeName;
        }
    }
}
