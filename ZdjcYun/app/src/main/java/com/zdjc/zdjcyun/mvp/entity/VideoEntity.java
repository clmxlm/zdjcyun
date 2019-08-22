package com.zdjc.zdjcyun.mvp.entity;

import java.util.ArrayList;
import java.util.List;

public class VideoEntity {


    /**
     * code : 0
     * msg : 查询直播信息成功
     * data : {"liveUrl":"rtmp://192.168.10.18:1935/stream/","videoVos":[{"videoId":1,"videoName":"zdjc","liveVideoUrl":"rtmp://10.88.89.170:8899/live/","videoDescription":"测试的摄像头","cameraStatus":0,"monitorPointNumber":"test1","cameraNumber":"testCamera","sectorId":1},{"videoId":2,"videoName":"zdjc","liveVideoUrl":"rtmp://10.88.89.170:8899/live/","videoDescription":"测试的摄像头","cameraStatus":0,"monitorPointNumber":"test2","cameraNumber":"testCamera","sectorId":1},{"videoId":3,"videoName":"zdjc","liveVideoUrl":"rtmp://10.88.89.170:8899/live/","videoDescription":"测试的摄像头","cameraStatus":0,"monitorPointNumber":"test3","cameraNumber":"testCamera","sectorId":1}]}
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
         * liveUrl : rtmp://192.168.10.18:1935/stream/
         * videoVos : [{"videoId":1,"videoName":"zdjc","liveVideoUrl":"rtmp://10.88.89.170:8899/live/","videoDescription":"测试的摄像头","cameraStatus":0,"monitorPointNumber":"test1","cameraNumber":"testCamera","sectorId":1},{"videoId":2,"videoName":"zdjc","liveVideoUrl":"rtmp://10.88.89.170:8899/live/","videoDescription":"测试的摄像头","cameraStatus":0,"monitorPointNumber":"test2","cameraNumber":"testCamera","sectorId":1},{"videoId":3,"videoName":"zdjc","liveVideoUrl":"rtmp://10.88.89.170:8899/live/","videoDescription":"测试的摄像头","cameraStatus":0,"monitorPointNumber":"test3","cameraNumber":"testCamera","sectorId":1}]
         */

        private String liveUrl;
        private ArrayList<VideoVosBean> videoVos;

        public String getLiveUrl() {
            return liveUrl;
        }

        public void setLiveUrl(String liveUrl) {
            this.liveUrl = liveUrl;
        }

        public ArrayList<VideoVosBean> getVideoVos() {
            return videoVos;
        }

        public void setVideoVos(ArrayList<VideoVosBean> videoVos) {
            this.videoVos = videoVos;
        }

        public static class VideoVosBean {
            /**
             * videoId : 1
             * videoName : zdjc
             * liveVideoUrl : rtmp://10.88.89.170:8899/live/
             * videoDescription : 测试的摄像头
             * cameraStatus : 0
             * monitorPointNumber : test1
             * cameraNumber : testCamera
             * sectorId : 1
             */

            private int videoId;
            private String videoName;
            private String liveVideoUrl;
            private String videoDescription;
            private int cameraStatus;
            private String monitorPointNumber;
            private String cameraNumber;
            private int sectorId;

            public int getVideoId() {
                return videoId;
            }

            public void setVideoId(int videoId) {
                this.videoId = videoId;
            }

            public String getVideoName() {
                return videoName;
            }

            public void setVideoName(String videoName) {
                this.videoName = videoName;
            }

            public String getLiveVideoUrl() {
                return liveVideoUrl;
            }

            public void setLiveVideoUrl(String liveVideoUrl) {
                this.liveVideoUrl = liveVideoUrl;
            }

            public String getVideoDescription() {
                return videoDescription;
            }

            public void setVideoDescription(String videoDescription) {
                this.videoDescription = videoDescription;
            }

            public int getCameraStatus() {
                return cameraStatus;
            }

            public void setCameraStatus(int cameraStatus) {
                this.cameraStatus = cameraStatus;
            }

            public String getMonitorPointNumber() {
                return monitorPointNumber;
            }

            public void setMonitorPointNumber(String monitorPointNumber) {
                this.monitorPointNumber = monitorPointNumber;
            }

            public String getCameraNumber() {
                return cameraNumber;
            }

            public void setCameraNumber(String cameraNumber) {
                this.cameraNumber = cameraNumber;
            }

            public int getSectorId() {
                return sectorId;
            }

            public void setSectorId(int sectorId) {
                this.sectorId = sectorId;
            }
        }
    }
}
