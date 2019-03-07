package com.zdjc.zdjcyun.mvp.entity;

public class ProjectContactEntity {


    /**
     * code : 0
     * msg : 操作成功
     * data : {"projectBeginTime":"2017-10-30 14:53:26","projectEndTime":"2018-10-31 09:53:28","projectDescription":"恩瑞万豪酒店基坑支护工程地铁2号线区域在线监测","userName":"admin","phone":"18608479467","email":"1011592288@qq.com","company":"中大检测","realName":"admin","createTime":"2017-09-25 19:45:32","status":"正常"}
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
         * projectBeginTime : 2017-10-30 14:53:26
         * projectEndTime : 2018-10-31 09:53:28
         * projectDescription : 恩瑞万豪酒店基坑支护工程地铁2号线区域在线监测
         * userName : admin
         * phone : 18608479467
         * email : 1011592288@qq.com
         * company : 中大检测
         * realName : admin
         * createTime : 2017-09-25 19:45:32
         * status : 正常
         */

        private String projectBeginTime;
        private String projectEndTime;
        private String projectDescription;
        private String projectAddress;
        private String projectTypeName;
        private String userName;
        private String phone;
        private String email;
        private String company;
        private String realName;
        private String createTime;
        private String status;

        public String getProjectBeginTime() {
            return projectBeginTime;
        }

        public void setProjectBeginTime(String projectBeginTime) {
            this.projectBeginTime = projectBeginTime;
        }

        public String getProjectEndTime() {
            return projectEndTime;
        }

        public void setProjectEndTime(String projectEndTime) {
            this.projectEndTime = projectEndTime;
        }

        public String getProjectDescription() {
            return projectDescription;
        }

        public void setProjectDescription(String projectDescription) {
            this.projectDescription = projectDescription;
        }

        public String getProjectAddress() {
            return projectAddress;
        }

        public void setProjectAddress(String projectAddress) {
            this.projectAddress = projectAddress;
        }

        public String getProjectTypeName() {
            return projectTypeName;
        }

        public void setProjectTypeName(String projectTypeName) {
            this.projectTypeName = projectTypeName;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getCompany() {
            return company;
        }

        public void setCompany(String company) {
            this.company = company;
        }

        public String getRealName() {
            return realName;
        }

        public void setRealName(String realName) {
            this.realName = realName;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }
    }
}
