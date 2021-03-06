package com.zdjc.zdjcyun.mvp.entity;

import java.util.ArrayList;

/**
 * @author ClmXlm
 * @create 2019/2/22
 * @Describe
 */
public class MonitorUnitEntity {


    /**
     * code : 0
     * msg : 操作成功
     * data : [{"monitorType":19,"monitorTypeName":"沉降","unitA":"mm","unitB":"mm","unitC":"mm/min","unitD":"mm"},{"monitorType":20,"monitorTypeName":"收敛","unitA":"mm","unitB":"mm","unitC":"mm/min","unitD":"mm"},{"monitorType":21,"monitorTypeName":"水平位移","unitA":"mm","unitB":"mm","unitC":"mm/min","unitD":"mm"},{"monitorType":25,"monitorTypeName":"周边地下水位","unitA":"mm","unitB":"mm","unitC":"mm/min","unitD":"mm"},{"monitorType":26,"monitorTypeName":"深部位移","unitA":"mm","unitB":"mm","unitC":"mm/min","unitD":"mm"},{"monitorType":28,"monitorTypeName":"砼支撑轴力","unitA":"KN","unitB":"KN","unitC":"KN/min","unitD":"KN"},{"monitorType":29,"monitorTypeName":"钢支撑轴力","unitA":"KN","unitB":"KN","unitC":"KN/min","unitD":"KN"},{"monitorType":52,"monitorTypeName":"GPS位移","unitA":"mm","unitB":"mm","unitC":"mm/min","unitD":"mm"}]
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
         * monitorType : 19
         * monitorTypeName : 沉降
         * unitA : mm
         * unitB : mm
         * unitC : mm/min
         * unitD : mm
         */

        private int monitorType;
        private String monitorTypeName;
        private String unitAtype;
        private String unitBtype;
        private String unitCtype;
        private String unitDtype;

        public int getMonitorType() {
            return monitorType;
        }

        public void setMonitorType(int monitorType) {
            this.monitorType = monitorType;
        }

        public String getMonitorTypeName() {
            return monitorTypeName;
        }

        public void setMonitorTypeName(String monitorTypeName) {
            this.monitorTypeName = monitorTypeName;
        }

        public String getUnitAtype() {
            return unitAtype;
        }

        public void setUnitAtype(String unitAtype) {
            this.unitAtype = unitAtype;
        }

        public String getUnitBtype() {
            return unitBtype;
        }

        public void setUnitBtype(String unitBtype) {
            this.unitBtype = unitBtype;
        }

        public String getUnitCtype() {
            return unitCtype;
        }

        public void setUnitCtype(String unitCtype) {
            this.unitCtype = unitCtype;
        }

        public String getUnitDtype() {
            return unitDtype;
        }

        public void setUnitDtype(String unitDtype) {
            this.unitDtype = unitDtype;
        }
    }
}
