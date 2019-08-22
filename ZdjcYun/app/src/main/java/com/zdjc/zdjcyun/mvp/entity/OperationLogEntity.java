package com.zdjc.zdjcyun.mvp.entity;

import java.util.ArrayList;
import java.util.List;

public class OperationLogEntity {


    /**
     * code : 0
     * msg : 查询文档信息成功
     * data : {"totalPage":1712,"operated":[{"userName":"admin","operation":"用户名为admin的用户登录","method":"com.zhongda.monitor.account.service.impl.UserServiceImpl.login()","description":"操作成功,返回值是true","createDate":"2019-08-16 16:16:06"},{"userName":"admin","operation":"分页搜索查询用户下当前区间告警","method":"com.zhongda.monitor.business.alarm.controller.AlarmController.querySearchAlarmInfo()","description":"操作成功:查询告警信息成功","createDate":"2019-08-16 16:10:54"},{"userName":"admin","operation":"查询概览项目告警区间ID为11","method":"com.zhongda.monitor.business.alarm.controller.AlarmController.queryAlarmProject()","description":"操作成功:查询概览项目告警成功","createDate":"2019-08-16 16:10:15"},{"userName":"admin","operation":"用户名为admin的用户登录","method":"com.zhongda.monitor.account.service.impl.UserServiceImpl.login()","description":"操作成功,返回值是true","createDate":"2019-08-16 16:09:51"},{"userName":"admin","operation":"查询概览项目告警区间ID为46","method":"com.zhongda.monitor.business.alarm.controller.AlarmController.queryAlarmProject()","description":"操作成功:查询概览项目告警成功","createDate":"2019-08-16 09:53:23"}]}
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
         * totalPage : 1712
         * operated : [{"userName":"admin","operation":"用户名为admin的用户登录","method":"com.zhongda.monitor.account.service.impl.UserServiceImpl.login()","description":"操作成功,返回值是true","createDate":"2019-08-16 16:16:06"},{"userName":"admin","operation":"分页搜索查询用户下当前区间告警","method":"com.zhongda.monitor.business.alarm.controller.AlarmController.querySearchAlarmInfo()","description":"操作成功:查询告警信息成功","createDate":"2019-08-16 16:10:54"},{"userName":"admin","operation":"查询概览项目告警区间ID为11","method":"com.zhongda.monitor.business.alarm.controller.AlarmController.queryAlarmProject()","description":"操作成功:查询概览项目告警成功","createDate":"2019-08-16 16:10:15"},{"userName":"admin","operation":"用户名为admin的用户登录","method":"com.zhongda.monitor.account.service.impl.UserServiceImpl.login()","description":"操作成功,返回值是true","createDate":"2019-08-16 16:09:51"},{"userName":"admin","operation":"查询概览项目告警区间ID为46","method":"com.zhongda.monitor.business.alarm.controller.AlarmController.queryAlarmProject()","description":"操作成功:查询概览项目告警成功","createDate":"2019-08-16 09:53:23"}]
         */

        private int totalPage;
        private ArrayList<OperatedBean> operated;

        public int getTotalPage() {
            return totalPage;
        }

        public void setTotalPage(int totalPage) {
            this.totalPage = totalPage;
        }

        public ArrayList<OperatedBean> getOperated() {
            return operated;
        }

        public void setOperated(ArrayList<OperatedBean> operated) {
            this.operated = operated;
        }

        public static class OperatedBean {
            /**
             * userName : admin
             * operation : 用户名为admin的用户登录
             * method : com.zhongda.monitor.account.service.impl.UserServiceImpl.login()
             * description : 操作成功,返回值是true
             * createDate : 2019-08-16 16:16:06
             */

            private String userName;
            private String operation;
            private String method;
            private String description;
            private String createDate;

            public String getUserName() {
                return userName;
            }

            public void setUserName(String userName) {
                this.userName = userName;
            }

            public String getOperation() {
                return operation;
            }

            public void setOperation(String operation) {
                this.operation = operation;
            }

            public String getMethod() {
                return method;
            }

            public void setMethod(String method) {
                this.method = method;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            public String getCreateDate() {
                return createDate;
            }

            public void setCreateDate(String createDate) {
                this.createDate = createDate;
            }
        }
    }
}
