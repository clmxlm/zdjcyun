package com.zdjc.zdjcyun.mvp.entity;

import java.util.ArrayList;

/**
 * @author ClmXlm
 * @create 2019/1/17
 * @Describe
 */
public class DataMonitoringImagesEntity {


    /**
     * code : 0
     * msg : 操作成功
     * data : [{"imageId":13,"imageName":"长丰路站布点图","imageUrl":"/monitor/images/three/pointMap/cfl.png","imageWidth":4648,"imageHeight":624,"imageDescription":"长丰路站布点图原图","monitorPoints":[{"monitorPointNumber":"ZCL07","picX":"2447px","picY":"285px","monitorType":28,"terminalNumber":"240305004049739","monitorTypeName":"砼支撑轴力"},{"monitorPointNumber":"ZCL08","picX":"2772px","picY":"285px","monitorType":28,"terminalNumber":"240305004049739","monitorTypeName":"砼支撑轴力"},{"monitorPointNumber":"ZCL3-8直","picX":"2078px","picY":"285px","monitorType":29,"terminalNumber":"240305004039864","monitorTypeName":"钢支撑轴力"},{"monitorPointNumber":"ZCL2-9直","picX":"2377px","picY":"285px","monitorType":29,"terminalNumber":"240305004039864","monitorTypeName":"钢支撑轴力"},{"monitorPointNumber":"ZCL09","picX":"3096px","picY":"285px","monitorType":28,"terminalNumber":"240305004049739","monitorTypeName":"砼支撑轴力"}]}]
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
         * imageId : 13
         * imageName : 长丰路站布点图
         * imageUrl : /monitor/images/three/pointMap/cfl.png
         * imageWidth : 4648
         * imageHeight : 624
         * imageDescription : 长丰路站布点图原图
         * monitorPoints : [{"monitorPointNumber":"ZCL07","picX":"2447px","picY":"285px","monitorType":28,"terminalNumber":"240305004049739","monitorTypeName":"砼支撑轴力"},{"monitorPointNumber":"ZCL08","picX":"2772px","picY":"285px","monitorType":28,"terminalNumber":"240305004049739","monitorTypeName":"砼支撑轴力"},{"monitorPointNumber":"ZCL3-8直","picX":"2078px","picY":"285px","monitorType":29,"terminalNumber":"240305004039864","monitorTypeName":"钢支撑轴力"},{"monitorPointNumber":"ZCL2-9直","picX":"2377px","picY":"285px","monitorType":29,"terminalNumber":"240305004039864","monitorTypeName":"钢支撑轴力"},{"monitorPointNumber":"ZCL09","picX":"3096px","picY":"285px","monitorType":28,"terminalNumber":"240305004049739","monitorTypeName":"砼支撑轴力"}]
         */
        /**
         * 这里是给定了图片的宽度和高度
         */
        /**
         * 这里是用宽*比列=高度
         * 比列=宽/高度
         */
        private int imageId;
        private String imageName;
        private String imageUrl;
        private int imageWidth;
        private int imageHeight;
        private String imageDescription;
        private ArrayList<MonitorPointsBean> monitorPoints;

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

        public String getImageDescription() {
            return imageDescription;
        }

        public void setImageDescription(String imageDescription) {
            this.imageDescription = imageDescription;
        }

        public ArrayList<MonitorPointsBean> getMonitorPoints() {
            return monitorPoints;
        }

        public void setMonitorPoints(ArrayList<MonitorPointsBean> monitorPoints) {
            this.monitorPoints = monitorPoints;
        }

        public static class MonitorPointsBean {
            /**
             * monitorPointNumber : ZCL07
             * picX : 2447px
             * picY : 285px
             * monitorType : 28
             * terminalNumber : 240305004049739
             * monitorTypeName : 砼支撑轴力
             */

            private String monitorPointNumber;
            private String picXaxis;
            private String picYaxis;
            private int monitorType;
            private int status;
            private String terminalNumber;
            private String monitorTypeName;

            public String getMonitorPointNumber() {
                return monitorPointNumber;
            }

            public void setMonitorPointNumber(String monitorPointNumber) {
                this.monitorPointNumber = monitorPointNumber;
            }

            public String getPicXaxis() {
                return picXaxis;
            }

            public void setPicXaxis(String picXaxis) {
                this.picXaxis = picXaxis;
            }

            public String getPicYaxis() {
                return picYaxis;
            }

            public void setPicYaxis(String picYaxis) {
                this.picYaxis = picYaxis;
            }

            public int getMonitorType() {
                return monitorType;
            }

            public void setMonitorType(int monitorType) {
                this.monitorType = monitorType;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public String getTerminalNumber() {
                return terminalNumber;
            }

            public void setTerminalNumber(String terminalNumber) {
                this.terminalNumber = terminalNumber;
            }

            public String getMonitorTypeName() {
                return monitorTypeName;
            }

            public void setMonitorTypeName(String monitorTypeName) {
                this.monitorTypeName = monitorTypeName;
            }
        }
    }
}
