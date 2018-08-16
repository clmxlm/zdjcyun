package com.zdjc.zdjcyun.mvp.entity;

public class PersonMessageEntity {


    /**
     * code : 0
     * msg : 获取数据成功
     * data : {"userId":3,"userName":"admin","password":"038bdaf98f2037b31f1e75b5b4c9b26e","phone":"18608479467","email":"1011592288@qq.com","company":"中大检测","realName":"admin","createTime":"2017-09-25 19:45:32","status":"正常"}
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
         * userId : 3
         * userName : admin
         * password : 038bdaf98f2037b31f1e75b5b4c9b26e
         * phone : 18608479467
         * email : 1011592288@qq.com
         * company : 中大检测
         * realName : admin
         * createTime : 2017-09-25 19:45:32
         * status : 正常
         */

        private int userId;
        private String userName;
        private String password;
        private String phone;
        private String email;
        private String company;
        private String realName;
        private String createTime;
        private String status;

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
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
