package com.zdjc.zdjcyun.mvp.entity;

import java.util.List;

/**
 * @author ClmXlm
 * @create 2019/1/9
 * @Describe
 */
public class UpdatePasswordEntity {


    /**
     * code : 0
     * msg : ”修改成功”
     * data : []
     */

    private int code;
    private String msg;
    private List<?> data;

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

    public List<?> getData() {
        return data;
    }

    public void setData(List<?> data) {
        this.data = data;
    }
}
