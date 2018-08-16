package com.zdjc.zdjcyun.mvp.entity;

import java.util.ArrayList;

public class PageReportEntity {


    /**
     * code : 0
     * msg : 查询成功
     * data : {"count":"4","dataList":[{"id":19,"projectId":"176","reportTyp":"Day","reportPath":"/mnt/pdf/176Day/","reportName":"a.pdf","commit_user":null,"commit_time":"2018-07-20 10:13:13.0","timeof_Report":"2017-08-01 00:00:00.0","personIn_charge":"??"},{"id":20,"projectId":"176","reportTyp":"Day","reportPath":"/mnt/pdf/176Day/","reportName":"a1.pdf","commit_user":null,"commit_time":"2018-07-20 10:13:50.0","timeof_Report":"2017-08-01 00:00:00.0","personIn_charge":"??"}]}
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
         * count : 4
         * dataList : [{"id":19,"projectId":"176","reportTyp":"Day","reportPath":"/mnt/pdf/176Day/","reportName":"a.pdf","commit_user":null,"commit_time":"2018-07-20 10:13:13.0","timeof_Report":"2017-08-01 00:00:00.0","personIn_charge":"??"},{"id":20,"projectId":"176","reportTyp":"Day","reportPath":"/mnt/pdf/176Day/","reportName":"a1.pdf","commit_user":null,"commit_time":"2018-07-20 10:13:50.0","timeof_Report":"2017-08-01 00:00:00.0","personIn_charge":"??"}]
         */

        private String count;
        private ArrayList<DataListBean> dataList;

        public String getCount() {
            return count;
        }

        public void setCount(String count) {
            this.count = count;
        }

        public ArrayList<DataListBean> getDataList() {
            return dataList;
        }

        public void setDataList(ArrayList<DataListBean> dataList) {
            this.dataList = dataList;
        }

        public static class DataListBean {
            /**
             * id : 19
             * projectId : 176
             * reportTyp : Day
             * reportPath : /mnt/pdf/176Day/
             * reportName : a.pdf
             * commit_user : null
             * commit_time : 2018-07-20 10:13:13.0
             * timeof_Report : 2017-08-01 00:00:00.0
             * personIn_charge : ??
             */

            private int id;
            private String projectId;
            private String reportTyp;
            private String reportPath;
            private String reportName;
            private String commit_user;
            private String commit_time;
            private String timeof_Report;
            private String personIn_charge;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getProjectId() {
                return projectId;
            }

            public void setProjectId(String projectId) {
                this.projectId = projectId;
            }

            public String getReportTyp() {
                return reportTyp;
            }

            public void setReportTyp(String reportTyp) {
                this.reportTyp = reportTyp;
            }

            public String getReportPath() {
                return reportPath;
            }

            public void setReportPath(String reportPath) {
                this.reportPath = reportPath;
            }

            public String getReportName() {
                return reportName;
            }

            public void setReportName(String reportName) {
                this.reportName = reportName;
            }

            public String getCommit_user() {
                return commit_user;
            }

            public void setCommit_user(String commit_user) {
                this.commit_user = commit_user;
            }

            public String getCommit_time() {
                return commit_time;
            }

            public void setCommit_time(String commit_time) {
                this.commit_time = commit_time;
            }

            public String getTimeof_Report() {
                return timeof_Report;
            }

            public void setTimeof_Report(String timeof_Report) {
                this.timeof_Report = timeof_Report;
            }

            public String getPersonIn_charge() {
                return personIn_charge;
            }

            public void setPersonIn_charge(String personIn_charge) {
                this.personIn_charge = personIn_charge;
            }
        }
    }
}
