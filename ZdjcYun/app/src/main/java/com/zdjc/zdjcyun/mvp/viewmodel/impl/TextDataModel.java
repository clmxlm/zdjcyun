package com.zdjc.zdjcyun.mvp.viewmodel.impl;


import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.DatePicker;

import com.zdjc.zdjcyun.R;
import com.zdjc.zdjcyun.app.BaseApplication;
import com.zdjc.zdjcyun.base.BaseModel;
import com.zdjc.zdjcyun.databinding.ActivityTextDataBinding;
import com.zdjc.zdjcyun.mvp.entity.CurveDetailEntity;
import com.zdjc.zdjcyun.mvp.entity.ProjectDetailEntity;
import com.zdjc.zdjcyun.mvp.presenter.impl.ProjectDetailPresenterImpl;
import com.zdjc.zdjcyun.mvp.ui.activities.TextDataActivity;
import com.zdjc.zdjcyun.mvp.viewmodel.ITextDataModel;
import com.zdjc.zdjcyun.util.PreferenceUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static com.zdjc.zdjcyun.util.DateUtil.getDateToString;



public class TextDataModel extends BaseModel<ActivityTextDataBinding,ProjectDetailPresenterImpl> implements ITextDataModel {

    private ArrayList<ProjectDetailEntity.DataBean> data;
    private long time;
    private long time1;
    private String monitorPoint;
    private String[] xValue1,xValue2;
    private ArrayList<CurveDetailEntity.DataBean> curvelData;

    @Override
    public void onCreate() {

        //默认时间是当前时间往前推两个小时
        time = System.currentTimeMillis();
        time1 = time-7200000*12;
        mBinder.include.tvTime.setVisibility(View.VISIBLE);
        mBinder.include.tvTime.setOnClickListener(v -> showDialogTwo());

        Map<String,String> map = new HashMap<>();
        map.put("projectId",String.valueOf(PreferenceUtils.getInt(BaseApplication.getContext(),"projectId",0)));
        mControl.getProjectDetail(this,map,1);

        mBinder.tv1.setOnClickListener(v -> showDialogTwo());
    }

    private void inData(ArrayList<String> list) {


    }

    @Override
    public void onBeforeRequest(int tag) {

    }

    @Override
    public void onSuccess(Object bean, int tag) {
        switch (tag) {
            case 1:
                data = (ArrayList< ProjectDetailEntity.DataBean >)bean;
                if (data.size()>0){
                    //尾矿库数据手动添加
                    String string = PreferenceUtils.getString(getContext(),"projectName");
                    if ("黄金洞尾矿库".equals(string)){
                        ArrayList<ProjectDetailEntity.DataBean.SensorListBean> sensorList = new ArrayList<>();
                        ProjectDetailEntity.DataBean.SensorListBean sensorListBean = new ProjectDetailEntity.DataBean.SensorListBean();
                        sensorListBean.setMonitorPoint("GT01");
                        sensorListBean.setSensorNumber("02");
                        sensorListBean.setSmuChannel("00");
                        sensorListBean.setSmuNumber("2017100005");
                        sensorListBean.setSpeedChange(0.000);
                        sensorListBean.setTotalLaserChange(0.000);
                        sensorList.add(sensorListBean);

                        ProjectDetailEntity.DataBean dataBean = new ProjectDetailEntity.DataBean();
                        dataBean.setMonitorType(15);
                        dataBean.setMonitorTypeName("干滩高程");
                        dataBean.setTableName("laser_data");
                        dataBean.setSensorList(sensorList);
                        data.add(dataBean);

                    }
                    ArrayList<String> list= new ArrayList<>();
                    list.clear();
                    for (ProjectDetailEntity.DataBean datum : data) {
                        list.add(datum.getMonitorTypeName());
                    }
                    inData(list);
                    if (PreferenceUtils.getString(getContext(),"monitorTypeName")==null
                            ||"".equals(PreferenceUtils.getString(getContext(),"monitorTypeName"))){
                        PreferenceUtils.putString(getContext(),"monitorTypeName",list.get(0));
                    }
                    if (list.size()==1){
                        if ("深部位移".equals(list.get(0))){
                            //默认时间是当前时间往前推两个小时
                            long time = System.currentTimeMillis();
                            long time1 = time-7200000*12;

                            Map<String,String> map = new HashMap<>();
                            map.put("projectId",PreferenceUtils.getInt(getContext(),"projectId",0)+"");
                            map.put("monitorPoint",data.get(0).getSensorList().get(0).getMonitorPoint());
                            map.put("date", getDateToString(time1));
                            mControl.getProjectDeepDispalcementDetailDetail(TextDataModel.this,map,3);
                        }else {
                            requestCurvelData();
                        }
                    }else {
                        requestCurvelData();
                    }
                }
                break;
            case 2:

                break;
            case 3:

                break;
                default:
                    break;
        }
    }



    @Override
    public void onError(String errorMsg, int code,int tag) {

    }

    private void requestCurvelData(){

        String tableName;
        String sensorNumber;
        String smuNumber;
        String smuChannel;

        if ("".equals(PreferenceUtils.getString(getContext(),"tableName"))
                ||PreferenceUtils.getString(getContext(),"tableName")==null){
            tableName = data.get(0).getTableName();
        }else {
            tableName = PreferenceUtils.getString(getContext(),"tableName");
        }

        if (PreferenceUtils.getString(getContext(),"sensorNumber")==null){
            sensorNumber = data.get(0).getSensorList().get(0).getSensorNumber();
        }else {
            sensorNumber = PreferenceUtils.getString(getContext(),"sensorNumber");
        }

        if (PreferenceUtils.getString(getContext(),"smuNumber")==null){
            smuNumber = data.get(0).getSensorList().get(0).getSmuNumber();
        }else {
            smuNumber = PreferenceUtils.getString(getContext(),"smuNumber");
        }

        if (PreferenceUtils.getString(getContext(),"smuChannel")==null){
            smuChannel = data.get(0).getSensorList().get(0).getSmuChannel();
        }else {
            smuChannel = PreferenceUtils.getString(getContext(),"smuChannel");
        }

        Map<String,String> map = new HashMap<>();
        map.put("tableName",tableName);
        map.put("sensorNumber",sensorNumber);
        map.put("smuNumber",smuNumber);
        map.put("smuChannel",smuChannel);
        map.put("beginTime",getDateToString(time1));
        map.put("endTime",getDateToString(time));
        mControl.getProjectCurveDetail(TextDataModel.this,map,2);

        monitorPoint = data.get(0).getSensorList().get(0).getMonitorPoint();
    }


    private void showDialogTwo() {
        View view = LayoutInflater.from(((TextDataActivity)UI)).inflate(R.layout.dialog_date, null);
        final DatePicker startTime = view.findViewById(R.id.st);
        final DatePicker endTime = view.findViewById(R.id.et);
        startTime.updateDate(startTime.getYear(), startTime.getMonth(), 01);
        AlertDialog.Builder builder = new AlertDialog.Builder(((TextDataActivity)UI));
        builder.setView(view);
        builder.setPositiveButton("确定", (dialog, which) -> {
            int month = startTime.getMonth() + 1;
            String st = "" + startTime.getYear() + month + startTime.getDayOfMonth();
            int month1 = endTime.getMonth() + 1;
            String et = "" + endTime.getYear() + month1 + endTime.getDayOfMonth();

            mBinder.tv1.setText(st+"  "+et);
        });
        builder.setNegativeButton("取消", null);
        AlertDialog dialog = builder.create();
        dialog.show();
        //自动弹出键盘问题解决
        dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
    }

}
