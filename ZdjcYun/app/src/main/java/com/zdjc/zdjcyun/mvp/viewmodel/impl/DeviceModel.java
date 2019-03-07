package com.zdjc.zdjcyun.mvp.viewmodel.impl;


import android.content.Intent;
import android.os.Handler;
import android.view.View;

import com.zdjc.zdjcyun.base.BaseModel;
import com.zdjc.zdjcyun.base.OnItemClickListener;
import com.zdjc.zdjcyun.databinding.FragmentDeviceBinding;
import com.zdjc.zdjcyun.event.PullLoadMoreRecyclerView;
import com.zdjc.zdjcyun.mvp.entity.DeviceEntity;
import com.zdjc.zdjcyun.mvp.entity.SensorEntity;
import com.zdjc.zdjcyun.mvp.presenter.impl.DevicePresenterImpl;
import com.zdjc.zdjcyun.mvp.ui.activities.SensorDetailActivity;
import com.zdjc.zdjcyun.mvp.ui.adapter.DeviceRecycViewAdapter;
import com.zdjc.zdjcyun.mvp.ui.fragment.DeviceFragment;
import com.zdjc.zdjcyun.mvp.viewmodel.IDeviceModel;
import com.zdjc.zdjcyun.util.PreferenceUtils;

import java.util.HashMap;
import java.util.Map;


public class DeviceModel extends BaseModel<FragmentDeviceBinding, DevicePresenterImpl> implements IDeviceModel {


    private DeviceFragment deviceFragment;
    private String id;
    private DeviceRecycViewAdapter deviceRecycViewAdapter;
    private DeviceEntity.DataBean data;
    private int page = 1;

    @Override
    public void onCreate() {
        deviceFragment = (DeviceFragment) UI;
        id = PreferenceUtils.getInt(getContext(),"sectorId")+"";
        getDevice(page);
        intView();
    }

    private void intView() {
        deviceRecycViewAdapter = new DeviceRecycViewAdapter(getContext());
        mBinder.recycleView.setPullRefreshEnable(false);
        mBinder.recycleView.setGridLayout(1);
        mBinder.recycleView.setAdapter(deviceRecycViewAdapter);
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

        deviceRecycViewAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                PreferenceUtils.putString(getContext(),"terminalNumber",data.getTerminals().get(position).getTerminalNumber());
                Intent intent = new Intent(getContext(), SensorDetailActivity.class);
                getContext().startActivity(intent);
            }
        });

        mBinder.btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSearchDeviceList(mBinder.et1.getText().toString(),mBinder.et2.getText().toString());
            }
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
                data = (DeviceEntity.DataBean)bean;
                deviceRecycViewAdapter.setList(data.getTerminals());
                break;

            case 2:
                data = (DeviceEntity.DataBean)bean;
                deviceRecycViewAdapter.setList(data.getTerminals());
                break;

            case 3:
                SensorEntity sensorEntity = (SensorEntity)bean;

                break;
                default:
                    break;
        }
    }

    @Override
    public void onError(String errorMsg,int code, int tag) {

    }

    private void getDevice(int page){
        Map<String, String> map = new HashMap<>(0);
        map.put("pageNum", page+"");
        map.put("pageSize", 10+"");
        map.put("sectorId", id);
        mControl.getQueryDeviceInfo(DeviceModel.this, map, 1);
    }

    public void getSearchDeviceList(String terminalNumber,String terminalModel){
        Map<String,String> map = new HashMap<>(0);
        map.put("terminalNumber", terminalNumber);
        map.put("terminalModel", terminalModel);
        map.put("pageNum", 1+"");
        map.put("pageSize", 10+"");
        map.put("sectorId", id);
        mControl.getQueryDeviceInfo(this,map,2);
    }

    public void getSensorInfor(String terminalNumber){
        Map<String,String> map = new HashMap<>(0);
        map.put("terminalNumber", terminalNumber);
        map.put("sectorId", id);
        mControl.getQuerySensorInfos(this,map,3);
    }

    private void getData() {
        new Handler().postDelayed(() -> {
            page++;
            getDevice(page);
        }, 1000);
    }

    private void setRefresh() {
        mBinder.recycleView.setHasMore(true);
        page = 1;
        getDevice(page);
    }

}
