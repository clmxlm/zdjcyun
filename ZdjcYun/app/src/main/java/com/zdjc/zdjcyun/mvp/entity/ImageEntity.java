package com.zdjc.zdjcyun.mvp.entity;

import java.util.List;

public class ImageEntity {


    /**
     * code : 0
     * msg : 操作成功
     * data : [{"imageId":207,"imageName":"测试图片1","imageHeight":524,"imageWidth":700,"imageUrl":"/monitor/images/onsite/one.jpg","imageDescription":"测试1图片","imageSize":"77.6KB"},{"imageId":208,"imageName":"测试图片2","imageHeight":524,"imageWidth":700,"imageUrl":"/monitor/images/onsite/two.jpg","imageDescription":"测试2图片","imageSize":"201KB"},{"imageId":209,"imageName":"测试图片3","imageHeight":524,"imageWidth":936,"imageUrl":"/monitor/images/onsite/three.jpg","imageDescription":"测试3图片","imageSize":"143KB"},{"imageId":210,"imageName":"测试图片4","imageHeight":524,"imageWidth":700,"imageUrl":"/monitor/images/onsite/four.jpg","imageDescription":"测试4图片","imageSize":"128KB"}]
     */

    private int code;
    private String msg;
    private List<DataBean> data;

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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * imageId : 207
         * imageName : 测试图片1
         * imageHeight : 524
         * imageWidth : 700
         * imageUrl : /monitor/images/onsite/one.jpg
         * imageDescription : 测试1图片
         * imageSize : 77.6KB
         */

        private int imageId;
        private String imageName;
        private int imageHeight;
        private int imageWidth;
        private String imageUrl;
        private String imageDescription;
        private String imageSize;

        public int getImageId() {
            return imageId;
        }

        public void setImageId(int imageId) {
            this.imageId = imageId;
        }

        public String getImageName() {
            return imageName;
        }

        public void setImageName(String imageName) {
            this.imageName = imageName;
        }

        public int getImageHeight() {
            return imageHeight;
        }

        public void setImageHeight(int imageHeight) {
            this.imageHeight = imageHeight;
        }

        public int getImageWidth() {
            return imageWidth;
        }

        public void setImageWidth(int imageWidth) {
            this.imageWidth = imageWidth;
        }

        public String getImageUrl() {
            return imageUrl;
        }

        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
        }

        public String getImageDescription() {
            return imageDescription;
        }

        public void setImageDescription(String imageDescription) {
            this.imageDescription = imageDescription;
        }

        public String getImageSize() {
            return imageSize;
        }

        public void setImageSize(String imageSize) {
            this.imageSize = imageSize;
        }
    }
}
