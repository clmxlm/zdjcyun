package com.zdjc.zdjcyun.mvp.entity;

/**
 * @author ClmXlm
 * @create 2019/3/1
 * @Describe
 */
public class VersionEntity {


    /**
     * code : 0
     * msg : 操作成功
     * data : {"newVersion":false,"version":"3.0.1","url":"/monitor/file/android/3.0.1/zhongdajiance.apk"}
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
         * newVersion : false
         * version : 3.0.1
         * url : /monitor/file/android/3.0.1/zhongdajiance.apk
         */

        private boolean newVersion;
        private String version;
        private String url;

        public boolean isNewVersion() {
            return newVersion;
        }

        public void setNewVersion(boolean newVersion) {
            this.newVersion = newVersion;
        }

        public String getVersion() {
            return version;
        }

        public void setVersion(String version) {
            this.version = version;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
