package com.zdjc.zdjcyun.mvp.viewmodel.impl;


import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.zdjc.zdjcyun.base.BaseModel;
import com.zdjc.zdjcyun.databinding.FragmentHazardsBinding;
import com.zdjc.zdjcyun.mvp.entity.HazardsEntity;
import com.zdjc.zdjcyun.mvp.presenter.impl.HazardsPresenterImpl;
import com.zdjc.zdjcyun.mvp.ui.adapter.HazardsRecycViewAdapter;
import com.zdjc.zdjcyun.mvp.viewmodel.IHazardsMessageModel;
import com.zdjc.zdjcyun.util.KeyboardUtils;
import com.zdjc.zdjcyun.util.PreferenceUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class HazardsModel extends BaseModel<FragmentHazardsBinding,HazardsPresenterImpl> implements IHazardsMessageModel {


    private String id;
    private HazardsRecycViewAdapter hazardsRecycViewAdapter;
    private RecyclerView recyclerView;

    @Override
    public void onCreate() {
        id = PreferenceUtils.getInt(getContext(), "sectorId") + "";
        inData();
    }

    private void inData() {
        recyclerView = mBinder.rlRisk;
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        hazardsRecycViewAdapter = new HazardsRecycViewAdapter(getContext());
        recyclerView.setAdapter(hazardsRecycViewAdapter);
        getRisk("","");


        mBinder.btnSearch.setOnClickListener(v -> {
            getRisk(mBinder.et1.getText().toString(), mBinder.et2.getText().toString());
            KeyboardUtils.hideKeyboard(v);
        });
    }

    @Override
    public void onBeforeRequest(int tag) {

    }

    @Override
    public void onSuccess(Object bean, int tag) {
        switch (tag) {
            case 1:
                ArrayList<HazardsEntity.DataBean> data = (ArrayList<HazardsEntity.DataBean>) bean;
                hazardsRecycViewAdapter.setDataList(data);
                break;
            case 2:

                break;
            default:
                break;
        }
    }

    @Override
    public void onError(String errorMsg, int code,int tag) {

    }

    private void getRisk(String jobActivity,String riskType) {
        Map<String, String> map = new HashMap<>(0);
        map.put("sectorId", id);
        map.put("jobActivity", jobActivity);
        mControl.getHazardsInfo(HazardsModel.this,map,1);
    }

}
