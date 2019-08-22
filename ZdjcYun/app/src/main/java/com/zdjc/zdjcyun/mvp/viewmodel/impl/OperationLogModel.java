package com.zdjc.zdjcyun.mvp.viewmodel.impl;


import android.os.Handler;
import android.view.View;
import android.widget.TextView;

import com.zdjc.zdjcyun.base.BaseModel;
import com.zdjc.zdjcyun.databinding.FragmentOperationLogBinding;
import com.zdjc.zdjcyun.event.PullLoadMoreRecyclerView;
import com.zdjc.zdjcyun.mvp.entity.OperationLogEntity;
import com.zdjc.zdjcyun.mvp.presenter.impl.OperationLogPresenterImpl;
import com.zdjc.zdjcyun.mvp.ui.adapter.OperationLogRecycViewAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class OperationLogModel extends BaseModel<FragmentOperationLogBinding,OperationLogPresenterImpl>{

    private OperationLogRecycViewAdapter adapter;
    private PullLoadMoreRecyclerView recyclerView;
    private TextView tvNoDocuments;
    private int page = 1;

    @Override
    public void onCreate() {
        inData();
    }

    private void inData() {
        tvNoDocuments = mBinder.tvNoDocuments;
        recyclerView = mBinder.recycleView;
        adapter = new OperationLogRecycViewAdapter(getContext());
        recyclerView.setGridLayout(1);
        recyclerView.setAdapter(adapter);
        getOperationList(page);
        recyclerView.setOnPullLoadMoreListener(new PullLoadMoreRecyclerView.PullLoadMoreListener() {
            @Override
            public void onRefresh() {
                setRefresh();
            }

            @Override
            public void onLoadMore() {
                getData();
            }
        });

    }

    private void getOperationList(int page){
        Map<String,String> map = new HashMap<>(0);
        map.put("pageNum", page + "");
        map.put("pageSize", 10 + "");
        mControl.getOperationLog(this,map,1);
    }

    @Override
    public void onBeforeRequest(int tag) {
        UI.showWaitDialog();
    }

    @Override
    public void onSuccess(Object bean, int tag) {
        switch (tag) {
            case 1:
                OperationLogEntity.DataBean dataBean = (OperationLogEntity.DataBean) bean;
                if (dataBean.getOperated().size()>0){
                    if (page == 1) {
                        adapter.setList(dataBean.getOperated());
                    } else {
                        adapter.addList(dataBean.getOperated());
                    }
                }else if (dataBean.getOperated().size()==0){
                    if (page>1){
                        tvNoDocuments.setVisibility(View.GONE);
                        recyclerView.setVisibility(View.VISIBLE);
                    }else {
                        recyclerView.setVisibility(View.GONE);
                        tvNoDocuments.setVisibility(View.VISIBLE);
                    }
                }
                if (recyclerView != null) {
                    recyclerView.setPullLoadMoreCompleted();
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void onError(String errorMsg, int code,int tag) {

    }

    private void getData() {
        new Handler().postDelayed(() -> {
            page++;
            getOperationList(page);
        }, 1000);
    }

    private void setRefresh() {
        recyclerView.setHasMore(true);
        page = 1;
        getOperationList(page);
    }


}
