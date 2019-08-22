package com.zdjc.zdjcyun.mvp.viewmodel.impl;


import android.content.Intent;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

import com.zdjc.zdjcyun.base.BaseModel;
import com.zdjc.zdjcyun.databinding.ActivityWarningMessageBinding;
import com.zdjc.zdjcyun.event.PullLoadMoreRecyclerView;
import com.zdjc.zdjcyun.mvp.entity.AlarmCountEntity;
import com.zdjc.zdjcyun.mvp.presenter.impl.WarningPresenterImpl;
import com.zdjc.zdjcyun.mvp.ui.activities.ProjectManageDetailActivity;
import com.zdjc.zdjcyun.mvp.ui.adapter.WarningRecycViewAdapter;
import com.zdjc.zdjcyun.mvp.viewmodel.IWarningMessageModel;
import com.zdjc.zdjcyun.util.PreferenceUtils;

import java.util.HashMap;
import java.util.Map;




public class WarningModel extends BaseModel<ActivityWarningMessageBinding,WarningPresenterImpl> implements IWarningMessageModel{

    private WarningRecycViewAdapter adapter;
    private PullLoadMoreRecyclerView recyclerView;
    private TextView tvNoGoods;
    private int page = 1;
    private AlarmCountEntity.DataBean dataBean;

    @Override
    public void onCreate() {
        inData();
    }

    private void inData() {
        tvNoGoods = mBinder.tvNoGoods;
        recyclerView = mBinder.recycleView;
        adapter = new WarningRecycViewAdapter(getContext());
        recyclerView.setGridLayout(1);
        recyclerView.setAdapter(adapter);
        getProjectList(page);
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

        adapter.setOnItemClickListener((view, position) -> {
            PreferenceUtils.putInt(getContext(),"sectorId",dataBean.getAlarmCounts().get(position).getSectorId());
            PreferenceUtils.putString(getContext(),"alarmTag","warning");
            Intent intent = new Intent(getContext(), ProjectManageDetailActivity.class);
            intent.putExtra("sectorName",dataBean.getAlarmCounts().get(position).getSectorName());
            getContext().startActivity(intent);
        });
    }

    private void getProjectList(int page){
        Map<String,String> map = new HashMap<>(0);
        map.put("current", page + "");
        map.put("pageSize", 10 + "");
        mControl.getQueryAlarmCount(this,map,1);
    }

    @Override
    public void onBeforeRequest(int tag) {
        UI.showWaitDialog();
    }

    @Override
    public void onSuccess(Object bean, int tag) {
        switch (tag) {
            case 1:
                dataBean = (AlarmCountEntity.DataBean) bean;
                if (dataBean.getAlarmCounts().size()>0){
                    if (page == 1) {
                        adapter.setList(dataBean.getAlarmCounts());
                    } else {
                        adapter.addList(dataBean.getAlarmCounts());
                    }
                }else if (dataBean.getAlarmCounts().size()==0){
                    if (page>1){
                        tvNoGoods.setVisibility(View.VISIBLE);
                        recyclerView.setVisibility(View.GONE);
                    }else {
                        recyclerView.setVisibility(View.VISIBLE);
                        tvNoGoods.setVisibility(View.GONE);
                    }
                }
                if (recyclerView != null) {
                    recyclerView.setPullLoadMoreCompleted();
                }
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

    private void getData() {
        new Handler().postDelayed(() -> {
            page++;
            getProjectList(page);
        }, 1000);
    }

    private void setRefresh() {
        recyclerView.setHasMore(true);
        page = 1;
        getProjectList(page);
    }


}
