package com.zdjc.zdjcyun.mvp.entity;

import java.util.ArrayList;

/**
 * @author ClmXlm
 * @create 2019/1/11
 * @Describe
 */
public class DeviceEntity {


    /**
     * code : 0
     * msg : 操作成功
     * data : {"totalPage":2,"terminals":[{"terminalId":16,"terminalNumber":"240305004039864","terminalModel":"KPJC-Micro16","terminalName":"自动测量单元","terminalStatus":"上线"},{"terminalId":15,"terminalNumber":"240305004049739","terminalModel":"KPJC-Micro16","terminalName":"自动测量单元","terminalStatus":"上线"}]}
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
         * totalPage : 2
         * terminals : [{"terminalId":16,"terminalNumber":"240305004039864","terminalModel":"KPJC-Micro16","terminalName":"自动测量单元","terminalStatus":"上线"},{"terminalId":15,"terminalNumber":"240305004049739","terminalModel":"KPJC-Micro16","terminalName":"自动测量单元","terminalStatus":"上线"}]
         */

        private int totalPage;
        private ArrayList<TerminalsBean> terminals;

        public int getTotalPage() {
            return totalPage;
        }

        public void setTotalPage(int totalPage) {
            this.totalPage = totalPage;
        }

        public ArrayList<TerminalsBean> getTerminals() {
            return terminals;
        }

        public void setTerminals(ArrayList<TerminalsBean> terminals) {
            this.terminals = terminals;
        }

        public static class TerminalsBean {
            /**
             * terminalId : 16
             * terminalNumber : 240305004039864
             * terminalModel : KPJC-Micro16
             * terminalName : 自动测量单元
             * terminalStatus : 上线
             */

            private int terminalId;
            private String terminalNumber;
            private String terminalModel;
            private String terminalName;
            private String terminalStatus;

            public int getTerminalId() {
                return terminalId;
            }

            public void setTerminalId(int terminalId) {
                this.terminalId = terminalId;
            }

            public String getTerminalNumber() {
                return terminalNumber;
            }

            public void setTerminalNumber(String terminalNumber) {
                this.terminalNumber = terminalNumber;
            }

            public String getTerminalModel() {
                return terminalModel;
            }

            public void setTerminalModel(String terminalModel) {
                this.terminalModel = terminalModel;
            }

            public String getTerminalName() {
                return terminalName;
            }

            public void setTerminalName(String terminalName) {
                this.terminalName = terminalName;
            }

            public String getTerminalStatus() {
                return terminalStatus;
            }

            public void setTerminalStatus(String terminalStatus) {
                this.terminalStatus = terminalStatus;
            }
        }
    }
}
