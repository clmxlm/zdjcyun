package com.zdjc.zdjcyun.mvp.viewmodel.impl;


import android.content.Intent;
import android.provider.MediaStore;
import android.view.View;

import com.zdjc.zdjcyun.base.BaseModel;
import com.zdjc.zdjcyun.databinding.FragmentBimBinding;
import com.zdjc.zdjcyun.databinding.FragmentVideoBinding;
import com.zdjc.zdjcyun.mvp.presenter.impl.BimPresenterImpl;
import com.zdjc.zdjcyun.mvp.ui.activities.VideoActivity;
import com.zdjc.zdjcyun.mvp.viewmodel.IBimMessageModel;


public class VideoModel extends BaseModel<FragmentVideoBinding,BimPresenterImpl> implements IBimMessageModel {


    @Override
    public void onCreate() {
        inData();
    }

    private void inData() {
        videoCheck();

        mBinder.btnVideo.setOnClickListener(v -> videoCheck());
    }

    public void videoCheck(){
        Intent intent = new Intent(getContext(), VideoActivity.class);
        getContext().startActivity(intent);
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
