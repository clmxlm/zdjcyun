package com.zdjc.zdjcyun.mvp.entity;

import java.util.List;

public class BeginEntity {


    /**
     * code : 0
     * msg : 操作成功
     * data : {"image":[{"imageId":1,"description":"这是图片A","url":"http://123.207.88.210/images/a.jpg","priority":1},{"imageId":2,"description":"这是图片B","url":"http://123.207.88.210/images/b.jpg","priority":1},{"imageId":3,"description":"这是图片C","url":"http://123.207.88.210/images/c.jpg","priority":1},{"imageId":4,"description":"这是图片D","url":"http://123.207.88.210/images/d.jpg","priority":1},{"imageId":5,"description":"这是图片E","url":"http://123.207.88.210/images/e.jpg","priority":1}],"projecType":[{"scId":1,"typeName":"项目类型","typeValue":"projectType","typeCode":1,"itemName":"农田项目","itemValue":"farmland"},{"scId":2,"typeName":"项目类型","typeValue":"projectType","typeCode":1,"itemName":"桥梁项目","itemValue":"bridge"},{"scId":3,"typeName":"项目类型","typeValue":"projectType","typeCode":1,"itemName":"隧道项目","itemValue":"tunnel"},{"scId":4,"typeName":"项目类型","typeValue":"projectType","typeCode":1,"itemName":"边坡项目","itemValue":"slope"},{"scId":5,"typeName":"项目类型","typeValue":"projectType","typeCode":1,"itemName":"地铁项目","itemValue":"foundation"},{"scId":6,"typeName":"项目类型","typeValue":"projectType","typeCode":1,"itemName":"高层建筑项目","itemValue":"building"},{"scId":7,"typeName":"项目类型","typeValue":"projectType","typeCode":1,"itemName":"地铁轨道","itemValue":"subwayRail"},{"scId":30,"typeName":"项目类型","typeValue":"projectType","typeCode":1,"itemName":"气象项目","itemValue":"weather"},{"scId":39,"typeName":"项目类型","typeValue":"projectType","typeCode":1,"itemName":"道路项目","itemValue":"road"},{"scId":40,"typeName":"项目类型","typeValue":"projectType","typeCode":1,"itemName":"水利项目","itemValue":"waterConservancy"},{"scId":42,"typeName":"项目类型","typeValue":"projectType","typeCode":1,"itemName":"钢结构项目","itemValue":"steeStructure"}],"files":[{"fileId":1,"fileName":"sb.pdf","fileUrl":"/data/cbs02/mnt/monitor/file/sb.pdf"}]}
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
        private List<ImageBean> image;
        private List<ProjecTypeBean> projecType;
        private List<FilesBean> files;

        public List<ImageBean> getImage() {
            return image;
        }

        public void setImage(List<ImageBean> image) {
            this.image = image;
        }

        public List<ProjecTypeBean> getProjecType() {
            return projecType;
        }

        public void setProjecType(List<ProjecTypeBean> projecType) {
            this.projecType = projecType;
        }

        public List<FilesBean> getFiles() {
            return files;
        }

        public void setFiles(List<FilesBean> files) {
            this.files = files;
        }

        public static class ImageBean {
            /**
             * imageId : 1
             * description : 这是图片A
             * url : http://123.207.88.210/images/a.jpg
             * priority : 1
             */

            private int imageId;
            private String description;
            private String url;
            private int priority;

            public int getImageId() {
                return imageId;
            }

            public void setImageId(int imageId) {
                this.imageId = imageId;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public int getPriority() {
                return priority;
            }

            public void setPriority(int priority) {
                this.priority = priority;
            }
        }


        public static class FilesBean {
            /**
             * fileId : 1
             * fileName : sb.pdf
             * fileUrl : /data/cbs02/mnt/monitor/file/sb.pdf
             */

            private int fileId;
            private String fileName;
            private String fileUrl;

            public int getFileId() {
                return fileId;
            }

            public void setFileId(int fileId) {
                this.fileId = fileId;
            }

            public String getFileName() {
                return fileName;
            }

            public void setFileName(String fileName) {
                this.fileName = fileName;
            }

            public String getFileUrl() {
                return fileUrl;
            }

            public void setFileUrl(String fileUrl) {
                this.fileUrl = fileUrl;
            }
        }
    }
}
