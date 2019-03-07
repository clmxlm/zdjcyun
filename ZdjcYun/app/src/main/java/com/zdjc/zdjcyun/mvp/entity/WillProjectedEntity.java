package com.zdjc.zdjcyun.mvp.entity;

import java.util.List;

/**
 * @author ClmXlm
 * @create 2019/1/8
 * @Describe
 */
public class WillProjectedEntity {
    /**
     * code : 0
     * msg : 查询概览即将完毕项目成功
     * data : [{"projectId":1,"projectName":"首个项目","sectorId":1,"sectorName":"区间1"},{"projectId":1,"projectName":"首个项目","sectorId":2,"sectorName":"区间2"},{"projectId":2,"projectName":"地铁二号线","sectorId":4,"sectorName":"地铁区间"}]
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
         * projectId : 1
         * projectName : 首个项目
         * sectorId : 1
         * sectorName : 区间1
         */

        private int projectId;
        private String projectName;
        private int sectorId;
        private String sectorName;

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
    }



}
