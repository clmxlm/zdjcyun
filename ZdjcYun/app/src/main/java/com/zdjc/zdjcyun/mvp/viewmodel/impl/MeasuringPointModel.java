package com.zdjc.zdjcyun.mvp.viewmodel.impl;


import android.support.v7.widget.GridLayoutManager;

import com.zdjc.zdjcyun.base.BaseModel;
import com.zdjc.zdjcyun.databinding.ActivityMeasuringPointBinding;
import com.zdjc.zdjcyun.mvp.entity.MeasuringPointEntity;
import com.zdjc.zdjcyun.mvp.presenter.impl.MeasuringPointPresenterImpl;
import com.zdjc.zdjcyun.mvp.ui.activities.MeasuringPointActivity;
import com.zdjc.zdjcyun.mvp.ui.adapter.MeasuringPointLeftRecycViewAdapter;
import com.zdjc.zdjcyun.mvp.ui.adapter.MeasuringPointRightRecycViewAdapter;
import com.zdjc.zdjcyun.mvp.viewmodel.IMeasuringPointModel;
import com.zdjc.zdjcyun.util.PreferenceUtils;

import java.util.HashMap;
import java.util.List;



public class MeasuringPointModel extends BaseModel<ActivityMeasuringPointBinding,MeasuringPointPresenterImpl> implements IMeasuringPointModel {


    private MeasuringPointLeftRecycViewAdapter measuringPointLeftRecycViewAdapter;
    private MeasuringPointRightRecycViewAdapter measuringPointRightRecycViewAdapter;
    private List<MeasuringPointEntity.DataBean> pointList;
    private int leftPosition = 0;

    @Override
    public void onCreate() {
        HashMap<String, String> params = new HashMap<>(0);
        String projectId = PreferenceUtils.getInt(getContext(),"projectId")+"";
        params.put("projectId",projectId);
        mControl.getMeasuringPointMsg(this,params,1);
        measuringPointLeftRecycViewAdapter = new MeasuringPointLeftRecycViewAdapter((MeasuringPointActivity) UI);
        measuringPointRightRecycViewAdapter = new MeasuringPointRightRecycViewAdapter((MeasuringPointActivity) UI);
        inData();
    }

    public void inData(){
        mBinder.rlLeft.setLayoutManager(new GridLayoutManager(getContext(),1));
        mBinder.rlRight.setLayoutManager(new GridLayoutManager(getContext(),1));


        measuringPointLeftRecycViewAdapter.setOnItemClickListener((view, position) -> {
            measuringPointLeftRecycViewAdapter.getPosition(position);
            measuringPointLeftRecycViewAdapter.notifyDataSetChanged();
            measuringPointRightRecycViewAdapter.setDataList(pointList.get(position).getDetections());
            measuringPointRightRecycViewAdapter.notifyDataSetChanged();
            leftPosition = position;
        });

        measuringPointRightRecycViewAdapter.setOnItemClickListener((view, position) -> {
            PreferenceUtils.putString(getContext(),"activityTag","MpActivity");
            PreferenceUtils.putString(getContext(),"monitorTypeName",pointList.get(leftPosition).getMonitorTypeName());
            PreferenceUtils.putString(getContext(),"monitorPoint",pointList.get(leftPosition).getDetections().get(position).getMonitorPoint());
            PreferenceUtils.putString(getContext(),"tableName",pointList.get(leftPosition).getTableName());
            PreferenceUtils.putString(getContext(),"sensorNumber",pointList.get(leftPosition).getDetections().get(position).getSensorNumber());
            PreferenceUtils.putString(getContext(),"smuNumber",pointList.get(leftPosition).getDetections().get(position).getSmuNumber());
            PreferenceUtils.putString(getContext(),"smuChannel",pointList.get(leftPosition).getDetections().get(position).getSmuChannel());
        });

    }

    @Override
    public void onBeforeRequest(int tag) {
        UI.showWaitDialog();
    }

    @Override
    public void onSuccess(Object bean, int tag) {
/**
 * 成功后到这里
 */
        switch (tag) {
            case 1:
                pointList = (List<MeasuringPointEntity.DataBean>)bean;

                measuringPointLeftRecycViewAdapter.setDataList(pointList);
                mBinder.rlLeft.setAdapter(measuringPointLeftRecycViewAdapter);

                measuringPointRightRecycViewAdapter.setDataList(pointList.get(0).getDetections());
                mBinder.rlRight.setAdapter(measuringPointRightRecycViewAdapter);
                break;
                default:
                    break;
        }
    }

    @Override
    public void onError(String errorMsg, int code,int tag) {
        if (code==2){
            ((MeasuringPointActivity)UI).finish();
        }
    }

}
