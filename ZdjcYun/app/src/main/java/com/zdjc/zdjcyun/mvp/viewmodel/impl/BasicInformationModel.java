package com.zdjc.zdjcyun.mvp.viewmodel.impl;


import com.zdjc.zdjcyun.base.BaseModel;
import com.zdjc.zdjcyun.databinding.FragmentBasicInformationBinding;
import com.zdjc.zdjcyun.mvp.entity.BasicInformationEntity;
import com.zdjc.zdjcyun.mvp.presenter.impl.BasicInformationPresenterImpl;
import com.zdjc.zdjcyun.mvp.ui.fragment.BasicInformationFragment;
import com.zdjc.zdjcyun.mvp.viewmodel.IBasicInformationModel;
import com.zdjc.zdjcyun.util.PreferenceUtils;

import java.util.HashMap;
import java.util.Map;


public class BasicInformationModel extends BaseModel<FragmentBasicInformationBinding, BasicInformationPresenterImpl> implements IBasicInformationModel {


    private BasicInformationFragment basicInformationFragment;
    private String id;
    @Override
    public void onCreate() {
        basicInformationFragment = (BasicInformationFragment) UI;
        id = PreferenceUtils.getInt(getContext(),"sectorId")+"";
        intView();
    }

    private void intView() {
        request();
    }

    @Override
    public void onBeforeRequest(int tag) {
        UI.showWaitDialog();
    }

    @Override
    public void onSuccess(Object bean, int tag) {
        switch (tag) {
            case 1:
                BasicInformationEntity.DataBean data = (BasicInformationEntity.DataBean)bean;
                String string="";
                for (String s : data.getMonitorTypeName()) {
                    string = string+s+",";
                }
                mBinder.tvProjectName.setText(data.getSectorName());
                mBinder.tvProjectDescription.setText(data.getSectorDescription());
                mBinder.tvProjectMonitoringPurpose.setText(string);
                mBinder.tvProjectAddress.setText(data.getSectorAddress());
                mBinder.tvProjectTime.setText(data.getSectorTime());
                mBinder.tvProjectType.setText(data.getSectorTypeName());
                break;
                default:
                    break;
        }
    }

    @Override
    public void onError(String errorMsg,int code, int tag) {

    }

    private  void request(){
        Map<String, String> map = new HashMap<>(0);
        map.put("sectorId", id);
        mControl.getBasicInformation(BasicInformationModel.this, map, 1);
    }

}
