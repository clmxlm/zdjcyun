package com.zdjc.zdjcyun.mvp.entity;

import java.util.ArrayList;

public class DeepDispalcementEntity {


    /**
     * code : 0
     * msg : 操作成功
     * data : [{"depth":0.5,"currentData":{"x":-38.6822,"y":-38.684},"currentLaserChange":{"x":0,"y":0},"speedChange":{"x":0,"y":0},"totalLaserChange":{"x":0,"y":0}},{"depth":1,"currentData":{"x":-38.6822,"y":-38.684},"currentLaserChange":{"x":0,"y":0},"speedChange":{"x":0,"y":0},"totalLaserChange":{"x":0,"y":0}},{"depth":1.5,"currentData":{"x":-38.6822,"y":-38.684},"currentLaserChange":{"x":0,"y":0},"speedChange":{"x":0,"y":0},"totalLaserChange":{"x":0,"y":0}},{"depth":2,"currentData":{"x":-38.6822,"y":-38.684},"currentLaserChange":{"x":0,"y":0},"speedChange":{"x":0,"y":0},"totalLaserChange":{"x":0,"y":0}},{"depth":2.5,"currentData":{"x":-38.6822,"y":-38.684},"currentLaserChange":{"x":0,"y":0},"speedChange":{"x":0,"y":0},"totalLaserChange":{"x":0,"y":0}},{"depth":3,"currentData":{"x":-38.6822,"y":-38.684},"currentLaserChange":{"x":0,"y":0},"speedChange":{"x":0,"y":0},"totalLaserChange":{"x":0,"y":0}},{"depth":3.5,"currentData":{"x":-38.6822,"y":-38.684},"currentLaserChange":{"x":0,"y":0},"speedChange":{"x":0,"y":0},"totalLaserChange":{"x":0,"y":0}},{"depth":4,"currentData":{"x":-38.6822,"y":-38.684},"currentLaserChange":{"x":0,"y":0},"speedChange":{"x":0,"y":0},"totalLaserChange":{"x":0,"y":0}},{"depth":4.5,"currentData":{"x":-38.6822,"y":-38.684},"currentLaserChange":{"x":0,"y":0},"speedChange":{"x":0,"y":0},"totalLaserChange":{"x":0,"y":0}},{"depth":5,"currentData":{"x":-38.6822,"y":-38.684},"currentLaserChange":{"x":0,"y":0},"speedChange":{"x":0,"y":0},"totalLaserChange":{"x":0,"y":0}}]
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
         * depth : 0.5
         * currentData : {"x":-38.6822,"y":-38.684}
         * currentLaserChange : {"x":0,"y":0}
         * speedChange : {"x":0,"y":0}
         * totalLaserChange : {"x":0,"y":0}
         */

        private double depth;
        private String currentTime;
        private CurrentDataBean currentData;
        private CurrentLaserChangeBean currentLaserChange;
        private SpeedChangeBean speedChange;
        private TotalLaserChangeBean totalLaserChange;

        public double getDepth() {
            return depth;
        }

        public void setDepth(double depth) {
            this.depth = depth;
        }

        public String getCurrentTime() {
            return currentTime;
        }

        public void setCurrentTime(String currentTime) {
            this.currentTime = currentTime;
        }

        public CurrentDataBean getCurrentData() {
            return currentData;
        }

        public void setCurrentData(CurrentDataBean currentData) {
            this.currentData = currentData;
        }

        public CurrentLaserChangeBean getCurrentLaserChange() {
            return currentLaserChange;
        }

        public void setCurrentLaserChange(CurrentLaserChangeBean currentLaserChange) {
            this.currentLaserChange = currentLaserChange;
        }

        public SpeedChangeBean getSpeedChange() {
            return speedChange;
        }

        public void setSpeedChange(SpeedChangeBean speedChange) {
            this.speedChange = speedChange;
        }

        public TotalLaserChangeBean getTotalLaserChange() {
            return totalLaserChange;
        }

        public void setTotalLaserChange(TotalLaserChangeBean totalLaserChange) {
            this.totalLaserChange = totalLaserChange;
        }

        public static class CurrentDataBean {
            /**
             * x : -38.6822
             * y : -38.684
             */

            private double x;
            private double y;

            public double getX() {
                return x;
            }

            public void setX(double x) {
                this.x = x;
            }

            public double getY() {
                return y;
            }

            public void setY(double y) {
                this.y = y;
            }
        }

        public static class CurrentLaserChangeBean {
            /**
             * x : 0
             * y : 0
             */

            private double x;
            private double y;

            public double getX() {
                return x;
            }

            public void setX(int x) {
                this.x = x;
            }

            public double getY() {
                return y;
            }

            public void setY(int y) {
                this.y = y;
            }
        }

        public static class SpeedChangeBean {
            /**
             * x : 0
             * y : 0
             */

            private double x;
            private double y;

            public double getX() {
                return x;
            }

            public void setX(int x) {
                this.x = x;
            }

            public double getY() {
                return y;
            }

            public void setY(int y) {
                this.y = y;
            }
        }

        public static class TotalLaserChangeBean {
            /**
             * x : 0
             * y : 0
             */

            private double x;
            private double y;

            public double getX() {
                return x;
            }

            public void setX(int x) {
                this.x = x;
            }

            public double getY() {
                return y;
            }

            public void setY(int y) {
                this.y = y;
            }
        }
    }
}
