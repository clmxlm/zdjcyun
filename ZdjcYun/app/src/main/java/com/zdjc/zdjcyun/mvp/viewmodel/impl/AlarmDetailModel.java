package com.zdjc.zdjcyun.mvp.viewmodel.impl;


import android.os.Handler;

import com.zdjc.zdjcyun.R;
import com.zdjc.zdjcyun.base.BaseModel;
import com.zdjc.zdjcyun.databinding.FragmentAlarmDetailBinding;
import com.zdjc.zdjcyun.event.PullLoadMoreRecyclerView;
import com.zdjc.zdjcyun.mvp.entity.AlarmDetailEntity;
import com.zdjc.zdjcyun.mvp.presenter.impl.AlarmDetailPresenterImpl;
import com.zdjc.zdjcyun.mvp.ui.adapter.AlarmDetailRecycViewAdapter;
import com.zdjc.zdjcyun.mvp.viewmodel.IAlarmDetailModel;
import com.zdjc.zdjcyun.util.PreferenceUtils;

import java.util.HashMap;
import java.util.Map;


public class AlarmDetailModel extends BaseModel<FragmentAlarmDetailBinding, AlarmDetailPresenterImpl> implements IAlarmDetailModel {


    private String id, userId;
    private AlarmDetailRecycViewAdapter alarmDetailRecycViewAdapter;
    private int page = 1;
    private int tagPosition;

    @Override
    public void onCreate() {
        id = PreferenceUtils.getInt(getContext(), "sectorId") + "";
        userId = PreferenceUtils.getInt(getContext(), "userId") + "";
        getAlarm(page);
        intView();
    }

    private void intView() {

        alarmDetailRecycViewAdapter = new AlarmDetailRecycViewAdapter(getContext());
        mBinder.recycleView.setAdapter(alarmDetailRecycViewAdapter);
        mBinder.recycleView.setPullRefreshEnable(false);
        mBinder.recycleView.setGridLayout(1);
        mBinder.recycleView.setOnPullLoadMoreListener(new PullLoadMoreRecyclerView.PullLoadMoreListener() {
            @Override
            public void onRefresh() {
                setRefresh();
            }

            @Override
            public void onLoadMore() {
                getData();
            }
        });


        alarmDetailRecycViewAdapter.setOnItemClickListener((view, position) -> {
            switch (view.getId()) {
                case R.id.btn_confirm_alarm:
                    Map<String, String> map = new HashMap<>(0);
                    map.put("alarmId", alarmDetailRecycViewAdapter.getDataList().get(position).getAlarmId() + "");
                    mControl.getConfirmAlarmInfo(AlarmDetailModel.this, map, 3);
                    tagPosition = position;
                    break;
                default:
                    break;
            }
        });
        mBinder.btnSearch.setOnClickListener(v -> {
            page = 1;
            getSearchAlarmList(mBinder.et1.getText().toString(), mBinder.et2.getText().toString(), mBinder.et3.getText().toString(), mBinder.et4.getText().toString());
        });

    }

    @Override
    public void onBeforeRequest(int tag) {
        UI.showWaitDialog();
    }

    @Override
    public void onSuccess(Object bean, int tag) {
        switch (tag) {
            case 1:
                AlarmDetailEntity.DataBean data = (AlarmDetailEntity.DataBean) bean;
                if (data.getAlarmInfo().size() > 0) {
                    if (page > 1) {
                        alarmDetailRecycViewAdapter.addAll(data.getAlarmInfo());
                    } else {
                        alarmDetailRecycViewAdapter.setDataList(data.getAlarmInfo());
                    }
                }
                if (mBinder.recycleView != null) {
                    mBinder.recycleView.setPullLoadMoreCompleted();
                }
                break;
            case 2:
                data = (AlarmDetailEntity.DataBean) bean;
                if (data.getAlarmInfo().size() > 0) {
                    if (page > 1) {
                        alarmDetailRecycViewAdapter.addAll(data.getAlarmInfo());
                    } else {
                        alarmDetailRecycViewAdapter.setDataList(data.getAlarmInfo());
                    }
                }
                if (mBinder.recycleView != null) {
                    mBinder.recycleView.setPullLoadMoreCompleted();
                }
                break;
            case 3:
                //确认成功之后的处理然后adapter那里刷新对应的位置
                alarmDetailRecycViewAdapter.notifyItemChanged(tagPosition, "zdjc");
                break;
            default:
                break;
        }
    }

    @Override
    public void onError(String errorMsg, int code, int tag) {

    }

    private void getAlarm(int page) {
        Map<String, String> map = new HashMap<>(0);
        map.put("current", page + "");
        map.put("pageSize", 10 + "");
        map.put("sector_id", id);
        mControl.getQueryAlarmInfo(AlarmDetailModel.this, map, 1);
    }

    public void getSearchAlarmList(String monitorPointNumber, String alarmStatus, String alarmType, String alarmLevel) {
        Map<String, String> map = new HashMap<>(0);
        map.put("monitorPointNumber", monitorPointNumber);
        map.put("alarmStatus", alarmStatus);
        map.put("alarmType", alarmType);
        if("等级三".equals(alarmLevel)){
            alarmLevel = "三";
        }else if ("等级二".equals(alarmLevel)){
            alarmLevel = "二";
        }else if ("等级一".equals(alarmLevel)){
            alarmLevel = "一";
        }
        map.put("alarmLevel", alarmLevel);
        map.put("current", 1 + "");
        map.put("pageSize", 10 + "");
        map.put("sectorId", id);
        map.put("userId", userId);
        mControl.getQuerySearchAlarmInfo(this, map, 2);
    }

    private void getData() {
        new Handler().postDelayed(() -> {
            page++;
            getAlarm(page);
        }, 1000);
    }

    private void setRefresh() {
        page = 1;
        getAlarm(page);
    }

}
