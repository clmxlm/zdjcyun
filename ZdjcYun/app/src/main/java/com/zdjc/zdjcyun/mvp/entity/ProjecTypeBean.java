package com.zdjc.zdjcyun.mvp.entity;

public class ProjecTypeBean {

    /**
     * projectTypeId : 1
     * projectTypeName : 农田项目
     * ptSc : 1
     * imageUrl : /monitor/images/projectType/rural.png
     */

    private int projectTypeId;
    private String projectTypeName;
    private int ptSc;
    private String imageUrl;

    public int getProjectTypeId() {
        return projectTypeId;
    }

    public void setProjectTypeId(int projectTypeId) {
        this.projectTypeId = projectTypeId;
    }

    public String getProjectTypeName() {
        return projectTypeName;
    }

    public void setProjectTypeName(String projectTypeName) {
        this.projectTypeName = projectTypeName;
    }

    public int getPtSc() {
        return ptSc;
    }

    public void setPtSc(int ptSc) {
        this.ptSc = ptSc;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
