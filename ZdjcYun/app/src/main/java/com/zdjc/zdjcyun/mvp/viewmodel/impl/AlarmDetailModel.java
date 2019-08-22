package com.zdjc.zdjcyun.mvp.viewmodel.impl;


import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

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
    private static final String[] ALARM_LEVEL={"全部","等级一","等级二","等级三"};
    private static final String[] ALARM_TYPE={"全部","设备类告警","数据类告警"};
    private static final String[] ALARM_STATUS={"全部","未确认","已确认"};
    private Spinner spinner,spinner1,spinner2;
    private String spinnerAlarmLevel="",spinnerAlarmType="",spinnerAlarmStatus="";


    @Override
    public void onCreate() {
        spinner = mBinder.spinner;
        spinner1 = mBinder.spinner1;
        spinner2 = mBinder.spinner2;
        id = PreferenceUtils.getInt(getContext(), "sectorId") + "";
        userId = PreferenceUtils.getInt(getContext(), "userId") + "";
        getSearchAlarmList(mBinder.et1.getText().toString(), spinnerAlarmStatus, spinnerAlarmType, spinnerAlarmLevel);
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
            getSearchAlarmList(mBinder.et1.getText().toString(), spinnerAlarmStatus, spinnerAlarmType, spinnerAlarmLevel);
        });

        //将可选内容与ArrayAdapter连接起来，simple_spinner_item是android系统自带样式
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, ALARM_LEVEL);
        //设置下拉列表的风格,simple_spinner_dropdown_item是android系统自带的样式，等会自定义修改
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //将adapter 添加到spinner中
        spinner.setAdapter(adapter);
        //添加事件Spinner事件监听
        spinner.setOnItemSelectedListener(new SpinnerSelectedListener());

        //将可选内容与ArrayAdapter连接起来，simple_spinner_item是android系统自带样式
        ArrayAdapter<String> adapter1 = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, ALARM_TYPE);
        //设置下拉列表的风格,simple_spinner_dropdown_item是android系统自带的样式，等会自定义修改
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //将adapter 添加到spinner中
        spinner1.setAdapter(adapter1);
        //添加事件Spinner事件监听
        spinner1.setOnItemSelectedListener(new SpinnerSelectedListener1());

        //将可选内容与ArrayAdapter连接起来，simple_spinner_item是android系统自带样式
        ArrayAdapter<String> adapter2 = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, ALARM_STATUS);
        //设置下拉列表的风格,simple_spinner_dropdown_item是android系统自带的样式，等会自定义修改
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //将adapter 添加到spinner中
        spinner2.setAdapter(adapter2);
        //添加事件Spinner事件监听
        spinner2.setOnItemSelectedListener(new SpinnerSelectedListener2());

    }

    //使用数组形式操作
    class SpinnerSelectedListener implements AdapterView.OnItemSelectedListener {

        @Override
        public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
                                   long arg3) {
            spinnerAlarmLevel = ALARM_LEVEL[arg2];
        }

        @Override
        public void onNothingSelected(AdapterView<?> arg0) {
        }
    }

    //使用数组形式操作
    class SpinnerSelectedListener1 implements AdapterView.OnItemSelectedListener {

        @Override
        public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
                                   long arg3) {
            spinnerAlarmType = ALARM_TYPE[arg2];
        }

        @Override
        public void onNothingSelected(AdapterView<?> arg0) {
        }
    }

    //使用数组形式操作
    class SpinnerSelectedListener2 implements AdapterView.OnItemSelectedListener {

        @Override
        public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
                                   long arg3) {
            spinnerAlarmStatus = ALARM_STATUS[arg2];
        }

        @Override
        public void onNothingSelected(AdapterView<?> arg0) {
        }
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
                    mBinder.recycleView.setVisibility(View.VISIBLE);
                    mBinder.tvNoAlarm.setVisibility(View.GONE);
                    if (page > 1) {
                        alarmDetailRecycViewAdapter.addAll(data.getAlarmInfo());
                    } else {
                        alarmDetailRecycViewAdapter.setDataList(data.getAlarmInfo());
                    }
                }else {
                    mBinder.recycleView.setVisibility(View.GONE);
                    mBinder.tvNoAlarm.setVisibility(View.VISIBLE);
                }
                if (mBinder.recycleView != null) {
                    mBinder.recycleView.setPullLoadMoreCompleted();
                }
                break;
            case 2:
                data = (AlarmDetailEntity.DataBean) bean;
                if (data.getAlarmInfo().size() > 0) {
                    mBinder.recycleView.setVisibility(View.VISIBLE);
                    mBinder.tvNoAlarm.setVisibility(View.GONE);
                    if (page > 1) {
                        alarmDetailRecycViewAdapter.addAll(data.getAlarmInfo());
                    } else {
                        alarmDetailRecycViewAdapter.setDataList(data.getAlarmInfo());
                    }
                }else {
                    mBinder.recycleView.setVisibility(View.GONE);
                    mBinder.tvNoAlarm.setVisibility(View.VISIBLE);
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
        if ("全部".equals(alarmStatus)){
            alarmStatus = "";
        }
        if ("全部".equals(alarmType)){
            alarmType = "";
        }
        map.put("alarmStatus", alarmStatus);
        map.put("alarmType", alarmType);
        if("等级三".equals(alarmLevel)){
            alarmLevel = "三";
        }else if ("等级二".equals(alarmLevel)){
            alarmLevel = "二";
        }else if ("等级一".equals(alarmLevel)){
            alarmLevel = "一";
        }else {
            alarmLevel = "";
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
