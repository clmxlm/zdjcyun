package com.zdjc.zdjcyun.mvp.entity;

import java.util.List;

public class TypeProjectEntity {


    /**
     * code : 0
     * msg : 操作成功
     * data : [{"scId":3,"typeName":"项目类型","typeValue":"projectType","typeCode":1,"itemName":"隧道项目","itemValue":"tunnel"},{"scId":4,"typeName":"项目类型","typeValue":"projectType","typeCode":1,"itemName":"边坡项目","itemValue":"slope"},{"scId":5,"typeName":"项目类型","typeValue":"projectType","typeCode":1,"itemName":"地铁项目","itemValue":"foundation"},{"scId":30,"typeName":"项目类型","typeValue":"projectType","typeCode":1,"itemName":"气象项目","itemValue":"weather"},{"scId":39,"typeName":"项目类型","typeValue":"projectType","typeCode":1,"itemName":"道路项目","itemValue":"road"},{"scId":40,"typeName":"项目类型","typeValue":"projectType","typeCode":1,"itemName":"水利项目","itemValue":"waterConservancy"},{"scId":42,"typeName":"项目类型","typeValue":"projectType","typeCode":1,"itemName":"钢结构项目","itemValue":"steeStructure"}]
     */

    private int code;
    private String msg;
    private List<ProjecTypeBean> data;

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

    public List<ProjecTypeBean> getData() {
        return data;
    }

    public void setData(List<ProjecTypeBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * scId : 3
         * typeName : 项目类型
         * typeValue : projectType
         * typeCode : 1
         * itemName : 隧道项目
         * itemValue : tunnel
         */

        private int scId;
        private String typeName;
        private String typeValue;
        private int typeCode;
        private String itemName;
        private String itemValue;

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
    }
}
