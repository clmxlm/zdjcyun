package com.zdjc.zdjcyun.mvp.entity;

import java.util.List;

public class ProjecTypeEntity {


    /**
     * code : 0
     * msg : 查询个人已有项目类型成功
     * data : [{"scId":11,"typeName":"区间类型","typeValue":"sectorType","typeCode":3,"itemName":"地铁","itemValue":"monitor/images/three/icon/subway.png","projectTotalCount":4,"projectErrorCount":2},{"scId":12,"typeName":"区间类型","typeValue":"sectorType","typeCode":3,"itemName":"道路","itemValue":"monitor/images/three/icon/road.png","projectTotalCount":1,"projectErrorCount":0}]
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
         * scId : 11
         * typeName : 区间类型
         * typeValue : sectorType
         * typeCode : 3
         * itemName : 地铁
         * itemValue : monitor/images/three/icon/subway.png
         * projectTotalCount : 4
         * projectErrorCount : 2
         */

        private int scId;
        private String typeName;
        private String typeValue;
        private int typeCode;
        private String itemName;
        private String itemValue;
        private int projectTotalCount;
        private int projectErrorCount;

        public int getScId() {
            return scId;
        }

        public void setScId(int scId) {
            this.scId = scId;
        }

        public String getTypeName() {
            return typeName;
        }

        public void setTypeName(String typeName) {
            this.typeName = typeName;
        }

        public String getTypeValue() {
            return typeValue;
        }

        public void setTypeValue(String typeValue) {
            this.typeValue = typeValue;
        }

        public int getTypeCode() {
            return typeCode;
        }

        public void setTypeCode(int typeCode) {
            this.typeCode = typeCode;
        }

        public String getItemName() {
            return itemName;
        }

        public void setItemName(String itemName) {
            this.itemName = itemName;
        }

        public String getItemValue() {
            return itemValue;
        }

        public void setItemValue(String itemValue) {
            this.itemValue = itemValue;
        }

        public int getProjectTotalCount() {
            return projectTotalCount;
        }

        public void setProjectTotalCount(int projectTotalCount) {
            this.projectTotalCount = projectTotalCount;
        }

        public int getProjectErrorCount() {
            return projectErrorCount;
        }

        public void setProjectErrorCount(int projectErrorCount) {
            this.projectErrorCount = projectErrorCount;
        }
    }
}
