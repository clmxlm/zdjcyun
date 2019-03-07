package com.zdjc.zdjcyun.mvp.viewmodel.impl;


import android.support.v7.widget.GridLayoutManager;
import android.view.View;

import com.zdjc.zdjcyun.app.BaseApplication;
import com.zdjc.zdjcyun.base.BaseModel;
import com.zdjc.zdjcyun.databinding.ActivitySensorDetailBinding;
import com.zdjc.zdjcyun.mvp.entity.SensorEntity;
import com.zdjc.zdjcyun.mvp.presenter.impl.SensorDetailPresenterImpl;
import com.zdjc.zdjcyun.mvp.ui.activities.SensorDetailActivity;
import com.zdjc.zdjcyun.mvp.ui.adapter.SensorDetailRecycViewAdapter;
import com.zdjc.zdjcyun.mvp.viewmodel.ISensorDetailModel;
import com.zdjc.zdjcyun.util.PreferenceUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class SensorDetailModel extends BaseModel<ActivitySensorDetailBinding, SensorDetailPresenterImpl> implements ISensorDetailModel {

    private String keyPre = "token";
    private SensorDetailRecycViewAdapter sensorDetailRecycViewAdapter;
    @Override
    public void onCreate() {
        if (PreferenceUtils.getString(BaseApplication.getContext(),keyPre)==null){
            PreferenceUtils.putString(BaseApplication.getContext(),"token","");
        }
        String id = PreferenceUtils.getInt(getContext(), "sectorId") + "";
        String terminalNumber = PreferenceUtils.getString(getContext(),"terminalNumber");

        Map<String,String> map = new HashMap<>(0);
        map.put("terminalNumber", terminalNumber);
        map.put("sectorId", id);
        mControl.getQuerySensorInfos(this,map,1);


        mBinder.include.tvTitle.setText("传感器详情");
        mBinder.include.imgbtnBack.setVisibility(View.VISIBLE);
        mBinder.include.imgbtnBack.setOnClickListener(v -> ((SensorDetailActivity)UI).finish());
        initData();
    }

    @Override
    public void onBeforeRequest(int tag) {

    }

    @Override
    public void onSuccess(Object bean, int tag) {
        switch (tag) {
            case 1:
                ArrayList<SensorEntity.DataBean> sensorEntity = (ArrayList<SensorEntity.DataBean>)bean;
                sensorDetailRecycViewAdapter.setList(sensorEntity);
                break;
            default:
                break;
        }
    }

    @Override
    public void onError(String errorMsg, int code,int tag) {

    }

    @Override
    public void initData() {
        sensorDetailRecycViewAdapter = new SensorDetailRecycViewAdapter((SensorDetailActivity)UI);
        mBinder.rlSensorDetail.setLayoutManager(new GridLayoutManager(getContext(),1));
        mBinder.rlSensorDetail.setAdapter(sensorDetailRecycViewAdapter);
    }

    @Override
    public void initRunable() {

    }

}
