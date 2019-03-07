package com.zdjc.zdjcyun.mvp.viewmodel.impl;


import com.zdjc.zdjcyun.base.BaseModel;
import com.zdjc.zdjcyun.databinding.FragmentBimBinding;
import com.zdjc.zdjcyun.mvp.presenter.impl.BimPresenterImpl;
import com.zdjc.zdjcyun.mvp.viewmodel.IBimMessageModel;


public class BimModel extends BaseModel<FragmentBimBinding,BimPresenterImpl> implements IBimMessageModel {


    @Override
    public void onCreate() {
        inData();
    }

    private void inData() {

    }

    @Override
    public void onBeforeRequest(int tag) {
    }

    @Override
    public void onSuccess(Object bean, int tag) {

    }

    @Override
    public void onError(String errorMsg, int code,int tag) {

    }



}
