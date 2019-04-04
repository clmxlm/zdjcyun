package com.zdjc.zdjcyun.mvp.entity;

import java.util.ArrayList;
import java.util.List;

public class DocumentEntity {


    /**
     * code : 0
     * msg : 查询文档信息成功
     * data : [{"id":59,"sectorId":16,"name":"长沙市雨花区井湾子街道井巷社区-2-5栋-2019-03-31.xlsx","path":"/monitor/sector_documents/2019-4/长沙市雨花区井湾子街道井巷社区-2-5栋-2019-03-31/长沙市雨花区井湾子街道井巷社区-2-5栋-2019-03-31.xlsx","type":"xlsx","chargeman":"日报"}]
     */

    private int code;
    private String msg;
    private ArrayList<DataBean> data;

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

    public ArrayList<DataBean> getData() {
        return data;
    }

    public void setData(ArrayList<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 59
         * sectorId : 16
         * name : 长沙市雨花区井湾子街道井巷社区-2-5栋-2019-03-31.xlsx
         * path : /monitor/sector_documents/2019-4/长沙市雨花区井湾子街道井巷社区-2-5栋-2019-03-31/长沙市雨花区井湾子街道井巷社区-2-5栋-2019-03-31.xlsx
         * type : xlsx
         * chargeman : 日报
         */

        private int id;
        private int sectorId;
        private String name;
        private String path;
        private String type;
        private String chargeman;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getSectorId() {
            return sectorId;
        }

        public void setSectorId(int sectorId) {
            this.sectorId = sectorId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPath() {
            return path;
        }

        public void setPath(String path) {
            this.path = path;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getChargeman() {
            return chargeman;
        }

        public void setChargeman(String chargeman) {
            this.chargeman = chargeman;
        }
    }
}
