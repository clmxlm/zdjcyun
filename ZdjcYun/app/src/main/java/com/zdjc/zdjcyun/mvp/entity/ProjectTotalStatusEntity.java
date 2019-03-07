package com.zdjc.zdjcyun.mvp.entity;

/**
 * @author ClmXlm
 * @create 2019/1/8
 * @Describe
 */
public class ProjectTotalStatusEntity {


    /**
     * code : 0
     * msg : 查询项目资源概览成功
     * data : {"projectIngCount":0,"projectErrorCount":0,"projectEndCount":0,"projectWillEndCount":0,"projectTotalCount":0}
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
         * projectIngCount : 0
         * projectErrorCount : 0
         * projectEndCount : 0
         * projectWillEndCount : 0
         * projectTotalCount : 0
         */

        private int projectIngCount;
        private int projectErrorCount;
        private int projectEndCount;
        private int projectWillEndCount;
        private int projectTotalCount;

        public int getProjectIngCount() {
            return projectIngCount;
        }

        public void setProjectIngCount(int projectIngCount) {
            this.projectIngCount = projectIngCount;
        }

        public int getProjectErrorCount() {
            return projectErrorCount;
        }

        public void setProjectErrorCount(int projectErrorCount) {
            this.projectErrorCount = projectErrorCount;
        }

        public int getProjectEndCount() {
            return projectEndCount;
        }

        public void setProjectEndCount(int projectEndCount) {
            this.projectEndCount = projectEndCount;
        }

        public int getProjectWillEndCount() {
            return projectWillEndCount;
        }

        public void setProjectWillEndCount(int projectWillEndCount) {
            this.projectWillEndCount = projectWillEndCount;
        }

        public int getProjectTotalCount() {
            return projectTotalCount;
        }

        public void setProjectTotalCount(int projectTotalCount) {
            this.projectTotalCount = projectTotalCount;
        }
    }
}
