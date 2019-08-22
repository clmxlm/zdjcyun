package com.zdjc.zdjcyun.mvp.viewmodel.impl;


import android.content.Intent;
import android.graphics.Color;
import android.support.v4.view.GravityCompat;
import android.support.v7.widget.GridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.blankj.utilcode.utils.ToastUtils;
import com.github.jdsjlzx.interfaces.OnItemClickListener;
import com.zdjc.zdjcyun.R;
import com.zdjc.zdjcyun.base.BaseModel;
import com.zdjc.zdjcyun.databinding.ActivityDataMonitoringBinding;
import com.zdjc.zdjcyun.event.MyTableTextView;
import com.zdjc.zdjcyun.mvp.entity.MeasuringPointEntity;
import com.zdjc.zdjcyun.mvp.entity.MonitorTypeNameEntity;
import com.zdjc.zdjcyun.mvp.presenter.impl.DataMonitoringActivityPresenterImpl;
import com.zdjc.zdjcyun.mvp.ui.activities.DataMonitoringActivity;
import com.zdjc.zdjcyun.mvp.ui.activities.MeasuringPointActivity;
import com.zdjc.zdjcyun.mvp.ui.adapter.MeasuringPointLeftRecycViewAdapter;
import com.zdjc.zdjcyun.mvp.ui.adapter.MeasuringPointRightRecycViewAdapter;
import com.zdjc.zdjcyun.mvp.viewmodel.IDataMonitoringActivityModel;
import com.zdjc.zdjcyun.util.PreferenceUtils;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class DataMonitoringActivityModel extends BaseModel<ActivityDataMonitoringBinding,DataMonitoringActivityPresenterImpl> implements IDataMonitoringActivityModel {

    private LinearLayout mainLinerLayout;
    private RelativeLayout relativeLayout;
    private String[] name={"测点","时间","单次量","累积量","变化率"};
    private MeasuringPointLeftRecycViewAdapter measuringPointLeftRecycViewAdapter;
    private MeasuringPointRightRecycViewAdapter measuringPointRightRecycViewAdapter;
    private int leftPosition = 0;
    private ArrayList<MonitorTypeNameEntity.DataBean> data;
    private String sectorId;
    private ArrayList<String> dataMonitorName;

    @Override
    public void onCreate() {
        mainLinerLayout = mBinder.MyTable;
        sectorId = PreferenceUtils.getInt(getContext(),"sectorId")+"";
        Map<String, String> map = new HashMap<>(0);
        map.put("sectorId", sectorId);
        mControl.getQueryMonitorTypeName(DataMonitoringActivityModel.this, map, 1);
        measuringPointLeftRecycViewAdapter = new MeasuringPointLeftRecycViewAdapter((DataMonitoringActivity) UI);
        measuringPointRightRecycViewAdapter = new MeasuringPointRightRecycViewAdapter((DataMonitoringActivity) UI);
        intView();
    }

    private void intView() {
        mBinder.include.tvTitle.setText("数据监控");
        mBinder.include.imgbtnBack.setVisibility(View.VISIBLE);
        mBinder.include.imgbtnBack.setOnClickListener(v -> ((DataMonitoringActivity)UI).finish());

        mBinder.ivLeftMonitoring.setOnClickListener(v -> mBinder.drawer.openDrawer(GravityCompat.START));

        //初始化标题
        relativeLayout = (RelativeLayout) LayoutInflater.from(getContext()).inflate(R.layout.table, null);
        MyTableTextView title = relativeLayout.findViewById(R.id.list_1_1);
        title.setText(name[0]);
        title.setTextColor(Color.BLACK);
        title.setBackgroundColor(getContext().getResources().getColor(R.color.table_background));

        title =  relativeLayout.findViewById(R.id.list_1_2);
        title.setText(name[1]);
        title.setTextColor(Color.BLACK);
        title.setBackgroundColor(getContext().getResources().getColor(R.color.table_background));

        title =  relativeLayout.findViewById(R.id.list_1_3);
        title.setText(name[2]);
        title.setTextColor(Color.BLACK);
        title.setBackgroundColor(getContext().getResources().getColor(R.color.table_background));

        title =  relativeLayout.findViewById(R.id.list_1_4);
        title.setText(name[3]);
        title.setTextColor(Color.BLACK);
        title.setBackgroundColor(getContext().getResources().getColor(R.color.table_background));

        title =  relativeLayout.findViewById(R.id.list_1_5);
        title.setText(name[4]);
        title.setTextColor(Color.BLACK);
        title.setBackgroundColor(getContext().getResources().getColor(R.color.table_background));

        mainLinerLayout.addView(relativeLayout);

        //初始化内容
        for (int i=0;i<20;i++) {
            relativeLayout = (RelativeLayout) LayoutInflater.from(getContext()).inflate(R.layout.table, null);
            MyTableTextView txt =  relativeLayout.findViewById(R.id.list_1_1);
            txt.setText("ZC07");
            txt = relativeLayout.findViewById(R.id.list_1_2);
            txt.setText("1992/04/21");
            txt =  relativeLayout.findViewById(R.id.list_1_3);
            txt.setText("0.12");
            txt =  relativeLayout.findViewById(R.id.list_1_4);
            txt.setText("1.546");
            txt =  relativeLayout.findViewById(R.id.list_1_5);
            txt.setText("2.123");
            mainLinerLayout.addView(relativeLayout);
        }
        mBinder.includeMonitorActivityLeft.rlLeft.setLayoutManager(new GridLayoutManager(getContext(),1));
        mBinder.includeMonitorActivityLeft.rlRight.setLayoutManager(new GridLayoutManager(getContext(),1));

        measuringPointLeftRecycViewAdapter.setOnItemClickListener((view, position) -> {
            measuringPointLeftRecycViewAdapter.getPosition(position);
            measuringPointLeftRecycViewAdapter.notifyDataSetChanged();

            Map<String, String> map = new HashMap<>(0);
            map.put("sectorId", sectorId);
            map.put("monitorType", data.get(position).getMonitorType()+"");
            mControl.getQueryMonitorPointName(DataMonitoringActivityModel.this, map, 2);
            measuringPointRightRecycViewAdapter.notifyDataSetChanged(

            );
            leftPosition = position;
        });

        measuringPointRightRecycViewAdapter.setOnItemClickListener((view, position) -> {
            mBinder.drawer.closeDrawer(GravityCompat.START);
            ToastUtils.showShortToast(dataMonitorName.get(position));
        });
    }

    @Override
    public void onBeforeRequest(int tag) {

    }

    @Override
    public void onSuccess(Object bean, int tag) {
        switch (tag) {
            case 1:
                data = (ArrayList<MonitorTypeNameEntity.DataBean>) bean;
                ArrayList<String> array = new ArrayList<>();
                for (int i = 0; i < data.size(); i++) {
                    array.add(data.get(i).getMonitorTypeName());
                }

                measuringPointLeftRecycViewAdapter.setDataList(array);
                mBinder.includeMonitorActivityLeft.rlLeft.setAdapter(measuringPointLeftRecycViewAdapter);

                Map<String, String> map = new HashMap<>(0);
                map.put("sectorId", sectorId);
                map.put("monitorType", data.get(0).getMonitorType()+"");
                mControl.getQueryMonitorPointName(DataMonitoringActivityModel.this, map, 2);
                break;
            case 2:
                dataMonitorName = (ArrayList<String>) bean;
                measuringPointRightRecycViewAdapter.setDataList(dataMonitorName);
                mBinder.includeMonitorActivityLeft.rlRight.setAdapter(measuringPointRightRecycViewAdapter);
                break;
            default:
                break;
        }
    }

    @Override
    public void onError(String errorMsg, int code,int tag) {

    }

}
