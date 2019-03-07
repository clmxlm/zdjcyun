package com.zdjc.zdjcyun.mvp.entity;

import java.util.ArrayList;

/**
 * @author ClmXlm
 * @create 2019/1/14
 * @Describe
 */
public class ImageListEntity {


    /**
     * code : 0
     * msg : 操作成功
     * data : [{"imageType":"布点图","images":[{"imageListId":7,"imageName":"长丰路站布点图","imageUrl":"/monitor/images/three/pointMap/cfls.png","imageWidth":240,"imageHeight":32}]},{"imageType":"现场图","images":[{"imageListId":8,"imageName":"长丰路站现场图","imageUrl":"/monitor/images/three/siteMap/dtxs.jpg","imageWidth":100,"imageHeight":133},{"imageListId":9,"imageName":"长丰路站现场图","imageUrl":"/monitor/images/three/siteMap/dtx1s.jpg","imageWidth":100,"imageHeight":133},{"imageListId":10,"imageName":"长丰路站现场图","imageUrl":"/monitor/images/three/siteMap/dtx2s.jpg","imageWidth":100,"imageHeight":177}]}]
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
         * imageType : 布点图
         * images : [{"imageListId":7,"imageName":"长丰路站布点图","imageUrl":"/monitor/images/three/pointMap/cfls.png","imageWidth":240,"imageHeight":32}]
         */

        private String imageType;
        private int imageTypeName;
        private ArrayList<ImagesBean> images;

        public String getImageType() {
            return imageType;
        }

        public void setImageType(String imageType) {
            this.imageType = imageType;
        }

        public int getImageTypeName() {
            return imageTypeName;
        }

        public void setImageTypeName(int imageTypeName) {
            this.imageTypeName = imageTypeName;
        }

        public ArrayList<ImagesBean> getImages() {
            return images;
        }

        public void setImages(ArrayList<ImagesBean> images) {
            this.images = images;
        }

        public static class ImagesBean {
            /**
             * imageListId : 7
             * imageName : 长丰路站布点图
             * imageUrl : /monitor/images/three/pointMap/cfls.png
             * imageWidth : 240
             * imageHeight : 32
             */

            private int imageListId;
            private String imageName;
            private String imageUrl;
            private int imageWidth;
            private int imageHeight;

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
        }
    }
}
