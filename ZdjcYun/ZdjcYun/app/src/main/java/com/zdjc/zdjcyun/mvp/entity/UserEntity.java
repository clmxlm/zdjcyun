package com.zdjc.zdjcyun.mvp.entity;

public class UserEntity {

    /**
     * code : 0
     * msg : 登录成功
     * data : eyJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE1MjQwMjE2MTYsImV4cCI6MTUyNDYyNjQxNiwidXNlck5hbWUiOiJhZG1pbiIsInVzZXJJZCI6M30.q04yp7eIBJpob_eUdmwgQ5AW56TOhcp0qxDemMxpGMk
     */

    private int code;
    private String msg;
    private String data;

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

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
