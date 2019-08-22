package com.zdjc.zdjcyun.mvp.viewmodel.impl;


import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.blankj.utilcode.utils.ToastUtils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.zdjc.zdjcyun.R;
import com.zdjc.zdjcyun.app.Constant;
import com.zdjc.zdjcyun.base.BaseModel;
import com.zdjc.zdjcyun.databinding.FragmentDataMonitoringBinding;
import com.zdjc.zdjcyun.mvp.presenter.impl.DataMonitoringPresenterImpl;
import com.zdjc.zdjcyun.mvp.ui.activities.DataMonitoringActivity;
import com.zdjc.zdjcyun.mvp.ui.adapter.DataMonitoringRecycViewAdapter;
import com.zdjc.zdjcyun.mvp.viewmodel.IDataMonitoringModel;

import java.util.ArrayList;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import ua.naiksoftware.stomp.Stomp;
import ua.naiksoftware.stomp.StompClient;
import ua.naiksoftware.stomp.dto.StompMessage;


public class DataMonitoringModel extends BaseModel<FragmentDataMonitoringBinding,DataMonitoringPresenterImpl> implements IDataMonitoringModel {


    @Override
    public void onCreate() {
        inData();
    }

    private void inData() {
        RecyclerView recyclerView = mBinder.recycleView;

        ArrayList<Integer> dd = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            dd.add(i);
        }
        DataMonitoringRecycViewAdapter dataMonitoringRecycViewAdapter = new DataMonitoringRecycViewAdapter(getContext());
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),1));
        dataMonitoringRecycViewAdapter.setDataList(dd);
        recyclerView.setAdapter(dataMonitoringRecycViewAdapter);


        dataMonitoringRecycViewAdapter.setOnItemClickListener((view, position) -> {
            switch (view.getId()) {
                case R.id.iv_zoom:
                    Intent intent = new Intent(((Activity)getContext()),DataMonitoringActivity.class);
                    getContext().startActivity(intent);
                    break;
                default:
                    break;
            }
        });
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
