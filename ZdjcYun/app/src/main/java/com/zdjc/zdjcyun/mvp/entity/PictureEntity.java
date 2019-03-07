package com.zdjc.zdjcyun.mvp.entity;

import java.util.ArrayList;

/**
 * @author ClmXlm
 * @create 2019/1/15
 * @Describe
 */
public class PictureEntity {


    /**
     * code : 0
     * msg : 操作成功
     * data : [{"imageId":15,"imageListId":8,"imageName":"长丰路站现场图","imageUrl":"/monitor/images/three/siteMap/dtx.jpg","imageWidth":1001,"imageHeight":1334,"imageType":3,"imageDescription":"长丰路站现场图原图"},{"imageId":17,"imageListId":9,"imageName":"长丰路站现场图","imageUrl":"/monitor/images/three/siteMap/dtx1.jpg","imageWidth":1001,"imageHeight":1334,"imageType":3,"imageDescription":"长丰路站现场图原图"},{"imageId":19,"imageListId":10,"imageName":"长丰路站现场图","imageUrl":"/monitor/images/three/siteMap/dtx2.jpg","imageWidth":751,"imageHeight":1334,"imageType":3,"imageDescription":"长丰路站现场图原图"}]
     */

    private int code;
    private String msg;
    private ArrayList<DataBean> data;

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

    public ArrayList<DataBean> getData() {
        return data;
    }

    public void setData(ArrayList<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * imageId : 15
         * imageListId : 8
         * imageName : 长丰路站现场图
         * imageUrl : /monitor/images/three/siteMap/dtx.jpg
         * imageWidth : 1001
         * imageHeight : 1334
         * imageType : 3
         * imageDescription : 长丰路站现场图原图
         */

        private int imageId;
        private int imageListId;
        private String imageName;
        private String imageUrl;
        private int imageWidth;
        private int imageHeight;
        private int imageType;
        private String imageDescription;

        public int getImageId() {
            return imageId;
        }

        public void setImageId(int imageId) {
            this.imageId = imageId;
        }

        public int getImageListId() {
            return imageListId;
        }

        public void setImageListId(int imageListId) {
            this.imageListId = imageListId;
        }

        public String getImageName() {
            return imageName;
        }

        public void setImageName(String imageName) {
            this.imageName = imageName;
        }

        public String getImageUrl() {
            return imageUrl;
        }

        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
        }

        public int getImageWidth() {
            return imageWidth;
        }

        public void setImageWidth(int imageWidth) {
            this.imageWidth = imageWidth;
        }

        public int getImageHeight() {
            return imageHeight;
        }

        public void setImageHeight(int imageHeight) {
            this.imageHeight = imageHeight;
        }

        public int getImageType() {
            return imageType;
        }

        public void setImageType(int imageType) {
            this.imageType = imageType;
        }

        public String getImageDescription() {
            return imageDescription;
        }

        public void setImageDescription(String imageDescription) {
            this.imageDescription = imageDescription;
        }
    }
}
