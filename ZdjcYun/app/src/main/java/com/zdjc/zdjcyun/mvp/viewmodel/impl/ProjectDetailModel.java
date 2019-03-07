package com.zdjc.zdjcyun.mvp.viewmodel.impl;


import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import com.blankj.utilcode.utils.ToastUtils;
import com.github.jdsjlzx.interfaces.OnItemClickListener;
import com.zdjc.zdjcyun.R;
import com.zdjc.zdjcyun.app.BaseApplication;
import com.zdjc.zdjcyun.base.BaseModel;
import com.zdjc.zdjcyun.charting.charts.LineChart;
import com.zdjc.zdjcyun.charting.components.Legend;
import com.zdjc.zdjcyun.charting.components.XAxis;
import com.zdjc.zdjcyun.charting.components.YAxis;
import com.zdjc.zdjcyun.charting.data.Entry;
import com.zdjc.zdjcyun.charting.data.LineData;
import com.zdjc.zdjcyun.charting.data.LineDataSet;
import com.zdjc.zdjcyun.charting.formatter.DefaultValueFormatter;
import com.zdjc.zdjcyun.charting.highlight.Highlight;
import com.zdjc.zdjcyun.charting.listener.OnChartValueSelectedListener;
import com.zdjc.zdjcyun.charting.utils.ColorTemplate;
import com.zdjc.zdjcyun.databinding.ActivityProjectDetailBinding;
import com.zdjc.zdjcyun.event.DetailPopWindow;
import com.zdjc.zdjcyun.mvp.entity.CurveDetailEntity;
import com.zdjc.zdjcyun.mvp.entity.DeepDispalcementEntity;
import com.zdjc.zdjcyun.mvp.entity.ProjectDetailEntity;
import com.zdjc.zdjcyun.mvp.presenter.impl.ProjectDetailPresenterImpl;
import com.zdjc.zdjcyun.mvp.ui.activities.MeasuringPointActivity;
import com.zdjc.zdjcyun.mvp.ui.activities.ProjectDetailActivity;
import com.zdjc.zdjcyun.mvp.ui.activities.WarningMessageActivity;
import com.zdjc.zdjcyun.mvp.ui.adapter.BootomRecycViewAdapter;
import com.zdjc.zdjcyun.mvp.ui.adapter.ProjectDetailRecycViewAdapter;
import com.zdjc.zdjcyun.mvp.ui.adapter.TopRecycViewAdapter;
import com.zdjc.zdjcyun.mvp.viewmodel.IProjectDetailModel;
import com.zdjc.zdjcyun.util.DateUtil;
import com.zdjc.zdjcyun.util.PreferenceUtils;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static com.zdjc.zdjcyun.util.DateUtil.getDateToString;



public class ProjectDetailModel extends BaseModel<ActivityProjectDetailBinding,ProjectDetailPresenterImpl> implements IProjectDetailModel,
        OnItemClickListener{


    private LineChart mChart;
    private DetailPopWindow detailPopWindow;
    private ProjectDetailRecycViewAdapter projectDetailRecycViewAdapter;
    private TopRecycViewAdapter topRecycViewAdapter;
    private BootomRecycViewAdapter bootomRecycViewAdapter;
    private String[] xValue1 = new String[]{};
    private String[] xValue2 = new String[]{};
    private String[] xValue3 = new String[]{};

    private ArrayList<String> mVals2 = new ArrayList<>();

    private  ArrayList<ProjectDetailEntity.DataBean> data = new ArrayList<>();
    private  ArrayList<CurveDetailEntity.DataBean> curvelData = new ArrayList<>();
    private int topPosition = 0;
    private String startText = "";
    private String endText = "";
    private int leftPosition = 0;
    private String monitorPoint="";
    private int defaultPosition = 0;
    private int requestSelectedPosition = 0;
    private boolean detailTag;
    private ArrayList<DeepDispalcementEntity.DataBean> deepDispalcementEntity;
    private int radioTag = 1;
    private String st;
    private String et;
    private long time;
    private long time1;


    @Override
    public void onCreate() {
        //默认时间是当前时间往前推一天
        time = System.currentTimeMillis();
        time1 = time-7200000*12;
        mBinder.include.tvTime.setVisibility(View.VISIBLE);
        mBinder.include.tvTime.setOnClickListener(v -> showDialogTwo());

        requestData();
        initListener();
        PreferenceUtils.putInt(getContext(),"topPosition",0);
        PreferenceUtils.putInt(getContext(),"leftPosition",0);

        mVals2.add("对比分析");
        mVals2.add("返回图表");
    }

    /**
     * 时间选择框
     */
    private void showDialogTwo() {
        View view = LayoutInflater.from(((ProjectDetailActivity)UI)).inflate(R.layout.dialog_date, null);
        final DatePicker startTime = view.findViewById(R.id.st);
        final DatePicker endTime = view.findViewById(R.id.et);
        final TextView tvSt = view.findViewById(R.id.tv_st);
        final TextView tvEt = view.findViewById(R.id.tv_et);
        final TimePicker timePicker = view.findViewById(R.id.myTimePicker);
        final String[] stringMonth = {""};
        final String[] strinDayOfMonth = {""};
        AlertDialog.Builder builder = new AlertDialog.Builder(((ProjectDetailActivity)UI));
        builder.setView(view);
        if ("深部位移".equals(PreferenceUtils.getString(getContext(),"monitorTypeName"))){
            tvEt.setText("选择时分秒");
            tvSt.setText("选择年月日");
            timePicker.setVisibility(View.VISIBLE);
            endTime.setVisibility(View.GONE);
            startTime.updateDate(startTime.getYear(), startTime.getMonth(), 01);
            builder.setPositiveButton("确定", (dialog, which) -> {
                int month = startTime.getMonth() + 1;
                if (month<10){
                    stringMonth[0] = "0" + String.valueOf(month);
                }else {
                    stringMonth[0] = String.valueOf(month);
                }
                if (startTime.getDayOfMonth()<10){
                    strinDayOfMonth[0] = "0" + String.valueOf(startTime.getDayOfMonth());
                }else {
                    strinDayOfMonth[0] = String.valueOf(startTime.getDayOfMonth());
                }
                st = "" + startTime.getYear() +"-"+ stringMonth[0] +"-"+ strinDayOfMonth[0];
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    et = "" + timePicker.getHour() +":"+ timePicker.getMinute() +":"+ "00";
                }
                startText = st;
                endText = et;
                String chooseTime = st+"  "+et;
                mBinder.include.tvTime.setText(chooseTime);
                PreferenceUtils.putString(getContext(),"singleTime",chooseTime);
                timeResetData();
            });
        }else {
            tvSt.setText("开始时间");
            tvEt.setText("结束时间");
            timePicker.setVisibility(View.GONE);
            endTime.setVisibility(View.VISIBLE);
            startTime.updateDate(startTime.getYear(), startTime.getMonth(), 01);
            builder.setPositiveButton("确定", (dialog, which) -> {
                int month = startTime.getMonth() + 1;
                String stringStartMonth;
                String strinStartDayOfMonth;
                if (month<10){
                    stringStartMonth = "0" + String.valueOf(month);
                }else {
                    stringStartMonth = String.valueOf(month);
                }
                if (startTime.getDayOfMonth()<10){
                    strinStartDayOfMonth = "0" + String.valueOf(startTime.getDayOfMonth());
                }else {
                    strinStartDayOfMonth = String.valueOf(startTime.getDayOfMonth());
                }
                st = "" + startTime.getYear() +"-"+ stringStartMonth +"-"+ strinStartDayOfMonth;

                int month1 = endTime.getMonth() + 1;
                String stringEndMonth;
                String strinEndDayOfMonth;
                if (month1<10){
                    stringEndMonth = "0" + String.valueOf(month1);
                }else {
                    stringEndMonth = String.valueOf(month1);
                }
                if (endTime.getDayOfMonth()<10){
                    strinEndDayOfMonth = "0" + String.valueOf(endTime.getDayOfMonth());
                }else {
                    strinEndDayOfMonth = String.valueOf(endTime.getDayOfMonth());
                }
                et = "" + endTime.getYear() +"-"+ stringEndMonth +"-"+ strinEndDayOfMonth;
                startText = st;
                endText = et;
                String chooseTime = st+"  "+et;
                mBinder.include.tvTime.setText(chooseTime);
                PreferenceUtils.putString(getContext(),"doubleTime",chooseTime);
                timeResetData();
            });
        }
        builder.setNegativeButton("取消", null);
        AlertDialog dialog = builder.create();
        dialog.show();
        //自动弹出键盘问题解决
        dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
    }

    private void inData(ArrayList<String> list) {
        mBinder.tvProject.setText(PreferenceUtils.getString(getContext(),"projectName"));
        if (projectDetailRecycViewAdapter == null){
            projectDetailRecycViewAdapter = new ProjectDetailRecycViewAdapter(getContext());
        }
        if (topRecycViewAdapter == null){
            topRecycViewAdapter = new TopRecycViewAdapter(getContext());
        }
        if (bootomRecycViewAdapter == null){
            bootomRecycViewAdapter = new BootomRecycViewAdapter(getContext());
        }

        RecyclerView topRecyclerView = mBinder.topRecyclerView;

        // 如果可以确定每个item的高度是固定的，设置这个选项可以提高性能
        topRecyclerView.setHasFixedSize(true);

        // 确定每个item的排列方式
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext()) {
            @Override
            public RecyclerView.LayoutParams generateDefaultLayoutParams() {
                // 这里要复写一下，因为默认宽高都是wrap_content
                // 这个不复写，你点击的背景色就只充满你的内容
                return new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT);
            }
        };

        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        topRecyclerView.setLayoutManager(layoutManager);

        topRecycViewAdapter.setDataList(list);
        topRecyclerView.setAdapter(topRecycViewAdapter);
        //项目下检测指标点击事件
        topRecycViewAdapter.setOnItemClickListener((view, position) -> {

            leftPosition = PreferenceUtils.getInt(getContext(),"leftPosition");
            topPosition = PreferenceUtils.getInt(getContext(),"topPosition");

            //切换不同指标的不同传感器的集合
            projectDetailRecycViewAdapter.setDataList(data.get(position).getSensorList());

            if (topPosition == position){
                if (PreferenceUtils.getInt(getContext(),"requestSelectedPosition") == position){
                    projectDetailRecycViewAdapter.getPosition(leftPosition);
                }else {
                    projectDetailRecycViewAdapter.getPosition(-1);
                }
            }else {
                projectDetailRecycViewAdapter.getPosition(-1);
                if (PreferenceUtils.getInt(getContext(),"requestSelectedPosition") == position){
                    topRecycViewAdapter.getProjectName(monitorPoint);
                    projectDetailRecycViewAdapter.getPosition(leftPosition);
                }else {
                    topRecycViewAdapter.getProjectName("");
                }
            }

            detailPopWindow = new DetailPopWindow((ProjectDetailActivity)UI);
            detailPopWindow.popRecyclerView.setAdapter(projectDetailRecycViewAdapter);
            //刷新指标选中颜色
            topRecycViewAdapter.getPosition(position);
            topRecycViewAdapter.notifyDataSetChanged();

            if ("深部位移".equals(data.get(position).getMonitorTypeName())){
                startText = st;
                detailTag = true;
                mBinder.bootomRecyclerView.setVisibility(View.GONE);
                if ("".equals(PreferenceUtils.getString(getContext(),"singleTime"))){
                    mBinder.include.tvTime.setText("时间筛选");
                }else {
                    mBinder.include.tvTime.setText(PreferenceUtils.getString(getContext(),"singleTime"));
                }
            }else {
                detailTag = false;
                mBinder.bootomRecyclerView.setVisibility(View.VISIBLE);
                if ("".equals(PreferenceUtils.getString(getContext(),"doubleTime"))){
                    if ("".equals(PreferenceUtils.getString(getContext(),"firstStartTime"))){
                        mBinder.include.tvTime.setText("时间筛选");
                    }else {
                        String time = PreferenceUtils.getString(getContext(),"firstStartTime")+" "+
                                PreferenceUtils.getString(getContext(),"firstEndTime");
                        mBinder.include.tvTime.setText(time);
                    }
                }else {
                    mBinder.include.tvTime.setText(PreferenceUtils.getString(getContext(),"doubleTime"));
                }
            }
            //展示数据popupwindow
            detailPopWindow.showAtLocation(mBinder.topRecyclerView, Gravity.START,0,0);

            PreferenceUtils.putInt(getContext(),"topPosition",position);
            requestSelectedPosition = position;
            PreferenceUtils.putString(getContext(),"tableName",data.get(position).getTableName());
            PreferenceUtils.putString(getContext(),"monitorTypeName",data.get(position).getMonitorTypeName());
        });

        // 如果可以确定每个item的高度是固定的，设置这个选项可以提高性能
        mBinder.bootomRecyclerView.setHasFixedSize(true);

        // 确定每个item的排列方式
        LinearLayoutManager layoutManager1 = new LinearLayoutManager(getContext()) {
            @Override
            public RecyclerView.LayoutParams generateDefaultLayoutParams() {
                // 这里要复写一下，因为默认宽高都是wrap_content
                // 这个不复写，你点击的背景色就只充满你的内容
                return new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT);
            }
        };
        layoutManager1.setOrientation(LinearLayoutManager.HORIZONTAL);
        mBinder.bootomRecyclerView.setLayoutManager(layoutManager1);


        //为点击标签设置点击事件.
        bootomRecycViewAdapter.setOnItemClickListener((view, position) -> {
            if (position==0){
                ArrayList<String> list2= new ArrayList<>(Arrays.asList(xValue3));
                list2.clear();
                for (int i = 0; i < data.size(); i++) {
                    if ("渗流压力".equals(data.get(i).getMonitorTypeName())){
                        for (ProjectDetailEntity.DataBean.SensorListBean sensorListBean : data.get(i).getSensorList()) {
                            list2.add(sensorListBean.getMonitorPoint());
                        }
                    }
                }
                xValue3 = list2.toArray(new String[list2.size()]);
                curvelData4(xValue3);
            }else if (position==1){
                curvelData3(xValue1,curvelData);
            }
        });
        //左边弹出框list的点击事件
        projectDetailRecycViewAdapter.setOnItemClickListener((view, position) -> {
            topPosition = PreferenceUtils.getInt(getContext(),"topPosition");
            if (detailTag){
                //深部位移
                if ("时间筛选".equals(mBinder.include.tvTime.getText().toString())){
                    ToastUtils.showShortToast("请先选择时间!");
                }else {
                    Map<String,String> map = new HashMap<>(0);
                    map.put("projectId",PreferenceUtils.getInt(getContext(),"projectId",0)+"");
                    map.put("monitorPoint",data.get(topPosition).getSensorList().get(position).getMonitorPoint());
                    map.put("date",mBinder.include.tvTime.getText().toString());
                    mControl.getProjectDeepDispalcementDetailDetail(ProjectDetailModel.this,map,3);
                }
            }else {
                String doubleTime = PreferenceUtils.getString(getContext(),"doubleTime");
                if (!"".equals(doubleTime)){
                    st = doubleTime.substring(0,doubleTime.indexOf(" "));
                    et = doubleTime.substring(doubleTime.indexOf(" "),doubleTime.length());
                }else {
                    st = PreferenceUtils.getString(getContext(),"firstStartTime");
                    et = PreferenceUtils.getString(getContext(),"firstEndTime");
                }
                Map<String,String> map = new HashMap<>(0);
                map.put("tableName",data.get(topPosition).getTableName());
                map.put("sensorNumber",data.get(topPosition).getSensorList().get(position).getSensorNumber());
                map.put("smuNumber",data.get(topPosition).getSensorList().get(position).getSmuNumber());
                map.put("smuChannel",data.get(topPosition).getSensorList().get(position).getSmuChannel());
                map.put("beginTime",st);
                map.put("endTime",et);
                mControl.getProjectCurveDetail(ProjectDetailModel.this,map,2);
                mBinder.include.tvTime.setText(st+"|"+et);
                PreferenceUtils.putString(getContext(),"sensorNumber",data.get(topPosition).getSensorList().get(position).getSensorNumber());
                PreferenceUtils.putString(getContext(),"smuNumber",data.get(topPosition).getSensorList().get(position).getSmuNumber());
                PreferenceUtils.putString(getContext(),"smuChannel",data.get(topPosition).getSensorList().get(position).getSmuChannel());
            }
            monitorPoint = data.get(topPosition).getSensorList().get(position).getMonitorPoint();
            PreferenceUtils.putInt(getContext(),"leftPosition",position);
            PreferenceUtils.putString(getContext(),"monitorPoint",data.get(topPosition).getSensorList().get(position).getMonitorPoint());
            detailPopWindow.dismiss();
        });

        //radioButton的点击事件
        mBinder.deepDispalcementRg.setOnCheckedChangeListener((group, checkedId) -> {
            if(checkedId == mBinder.rbCurrentData.getId()){
               radioTag = 1;
                curvelData1(xValue2,deepDispalcementEntity);
            }else if(checkedId == mBinder.rbCurrentLaserChange.getId()){
                radioTag = 2;
                curvelData1(xValue2,deepDispalcementEntity);
            }else if (checkedId == mBinder.rbSpeedChange.getId()){
                radioTag = 3;
                curvelData1(xValue2,deepDispalcementEntity);
            }else {
                radioTag = 4;
            }
        });

        mBinder.llSensor.setOnClickListener(v -> ((ProjectDetailActivity)UI).intent2Activity(MeasuringPointActivity.class));

        mBinder.llAlarm.setOnClickListener(v -> ((ProjectDetailActivity)UI).intent2Activity(WarningMessageActivity.class));

        mBinder.llTerminal.setOnClickListener(v -> ((ProjectDetailActivity)UI).intent2Activity(MeasuringPointActivity.class));
    }

    /**
     * 曲线信息
     * @param data1
     */
    private void curvelData(String[] xValue,ArrayList<CurveDetailEntity.DataBean> data1){
        mBinder.chart1.setVisibility(View.VISIBLE);
        mBinder.chart2.setVisibility(View.GONE);
        mChart = mBinder.chart1;
        // no description text
        mChart.getDescription().setEnabled(false);
        // enable touch gestures
        mChart.setTouchEnabled(true);
        mChart.setDragDecelerationFrictionCoef(0.9f);
        // enable scaling and dragging
        mChart.setDragEnabled(true);
        mChart.setScaleEnabled(true);
        mChart.setDrawGridBackground(false);
        mChart.setHighlightPerDragEnabled(true);
        mChart.setDrawBorders(false);
        mChart.setPinchZoom(true);

        mChart.setBackgroundColor(Color.parseColor("#192531"));

        setData(data1);
        mChart.animateX(2500);
        // get the legend (only possible after setting data)
        Legend l = mChart.getLegend();
        // modify the legend ...
        l.setForm(Legend.LegendForm.LINE);
        l.setTypeface(mTfLight);
        l.setTextSize(11f);
        l.setTextColor(Color.WHITE);
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT);
        l.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        l.setDrawInside(false);

        XAxis xAxis = mChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTH_SIDED);
        xAxis.setAxisMinimum(0f);
        xAxis.setGranularity(1f);
        xAxis.setTextColor(Color.WHITE);
        xAxis.setDrawGridLines(false);
        if (xValue.length>0){
            xAxis.setValueFormatter((value, axis) -> xValue[(int) value % xValue.length]);
        }

        YAxis yAxis = mChart.getAxisLeft();
        yAxis.setDrawGridLines(false);
        yAxis.setTextColor(Color.WHITE);

        YAxis yAxis1 = mChart.getAxisRight();
        yAxis1.setDrawGridLines(false);
        yAxis1.setTextColor(Color.WHITE);

        mChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, Highlight h) {
                String monitorTypeName = PreferenceUtils.getString(getContext(),"monitorTypeName");
                if ("雨量".equals(monitorTypeName)){
                    ToastUtils.showShortToast(
                            curvelData.get((int)e.getX()).getPreviousTime()+"\n"
                                    +"单次变化量(mm): "+ formatFloatNumber(curvelData.get((int)e.getX()).getCurrentLaserChange())+"\n"
                                    +"变化速率(mm/min): "+ formatFloatNumber(curvelData.get((int)e.getX()).getSpeedChange()));
                }else if ("渗流压力".equals(monitorTypeName)){
                    ToastUtils.showShortToast(
                            curvelData.get((int)e.getX()).getPreviousTime()+"\n"+
                                    "当前值(mm): "+formatFloatNumber(curvelData.get((int)e.getX()).getCurrentData())+"\n"
                                    +"单次变化量(mm): "+ formatFloatNumber(curvelData.get((int)e.getX()).getCurrentLaserChange()));
                }else if ("干滩高程".equals(monitorTypeName)){
                    ToastUtils.showShortToast(
                            curvelData.get((int)e.getX()).getPreviousTime()+"\n"+
                                    "高度(m): "+formatFloatNumber(curvelData.get((int)e.getX()).getTotalLaserChange())+"\n"
                                    +"宽度(m): "+ formatFloatNumber(curvelData.get((int)e.getX()).getCurrentLaserChange()));
                }else if ("库水位".equals(monitorTypeName)){
                    ToastUtils.showShortToast(
                            curvelData.get((int)e.getX()).getPreviousTime()+"\n"+
                                    "当前值(mm): "+formatFloatNumber(curvelData.get((int)e.getX()).getCurrentData())+"\n"
                                    +"单次变化量(mm): "+ formatFloatNumber(curvelData.get((int)e.getX()).getCurrentLaserChange()));
                }else {
                    ToastUtils.showShortToast(
                            curvelData.get((int)e.getX()).getPreviousTime()+"\n"+
                                    "累计变化量(mm): "+formatFloatNumber(curvelData.get((int)e.getX()).getTotalLaserChange())+"\n"
                                    +"单次变化量(mm): "+ formatFloatNumber(curvelData.get((int)e.getX()).getCurrentLaserChange())+"\n"
                                    +"变化速率(mm/min): "+ formatFloatNumber(curvelData.get((int)e.getX()).getSpeedChange()));
                }

            }
            @Override
            public void onNothingSelected() {

            }
        });
    }
    private void curvelData1(String[] xDeepValue,ArrayList<DeepDispalcementEntity.DataBean> data2){
        mBinder.chart1.setVisibility(View.GONE);
        mBinder.chart2.setVisibility(View.VISIBLE);
        mChart = mBinder.chart2;
        // no description text
        mChart.getDescription().setEnabled(false);
        // enable touch gestures
        mChart.setTouchEnabled(true);
        mChart.setDragDecelerationFrictionCoef(0.9f);
        // enable scaling and dragging
        mChart.setDragEnabled(true);
        mChart.setScaleEnabled(true);
        mChart.setDrawGridBackground(false);
        mChart.setHighlightPerDragEnabled(true);
        mChart.setDrawBorders(false);
        mChart.setRotation(90);
        // if disabled, scaling can be done on x- and y-axis separately
        mChart.setPinchZoom(true);

        mChart.setBackgroundColor(Color.parseColor("#192531"));
        // add data
        setData1(data2);
        mChart.animateX(2500);
        // get the legend (only possible after setting data)
        Legend l = mChart.getLegend();
        // modify the legend ...
        l.setForm(Legend.LegendForm.LINE);
        l.setTypeface(mTfLight);
        l.setTextSize(11f);
        l.setTextColor(Color.WHITE);
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT);
        l.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        l.setDrawInside(false);
        XAxis xAxis = mChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTH_SIDED);
        xAxis.setAxisMinimum(0f);
        xAxis.setGranularity(1f);
        xAxis.setTextColor(Color.WHITE);
        xAxis.setLabelRotationAngle(-90);
        xAxis.setDrawGridLines(false);
        if (xDeepValue.length>0){
            xAxis.setValueFormatter((value, axis) -> xDeepValue[(int) value % xDeepValue.length]);
        }

        YAxis yAxis = mChart.getAxisLeft();
        yAxis.setDrawGridLines(false);
        yAxis.setTextColor(Color.WHITE);

        YAxis yAxis1 = mChart.getAxisRight();
        yAxis1.setDrawGridLines(false);
        yAxis1.setTextColor(Color.WHITE);

        mChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, Highlight h) {
                ToastUtils.showShortToast(
                        "深度(M): "+formatFloatNumber(deepDispalcementEntity.get((int)e.getX()).getDepth())+"\n"+
                                "X通道(mm): "+formatFloatNumber(deepDispalcementEntity.get((int)e.getX()).getCurrentData().getX())+"\n"
                                +"Y通道(mm): "+ formatFloatNumber(deepDispalcementEntity.get((int)e.getX()).getCurrentData().getY()));

            }
            @Override
            public void onNothingSelected() {

            }
        });
    }
    private void curvelData2(String[] xValue,ArrayList<CurveDetailEntity.DataBean> data1){
        mBinder.chart1.setVisibility(View.VISIBLE);
        mBinder.chart2.setVisibility(View.GONE);
        mChart = mBinder.chart1;
        // no description text
        mChart.getDescription().setEnabled(false);
        // enable touch gestures
        mChart.setTouchEnabled(true);
        mChart.setDragDecelerationFrictionCoef(0.9f);
        // enable scaling and dragging
        mChart.setDragEnabled(true);
        mChart.setScaleEnabled(true);
        mChart.setDrawGridBackground(false);
        mChart.setHighlightPerDragEnabled(true);
        mChart.setDrawBorders(false);
        // if disabled, scaling can be done on x- and y-axis separately
        mChart.setPinchZoom(true);

        // set an alternative background color
        mChart.setBackgroundColor(Color.parseColor("#192531"));
        // add data
        setData2(data1);
        mChart.animateX(2500);
        // get the legend (only possible after setting data)
        Legend l = mChart.getLegend();
        // modify the legend ...
        l.setForm(Legend.LegendForm.LINE);
        l.setTypeface(mTfLight);
        l.setTextSize(11f);
        l.setTextColor(Color.WHITE);
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT);
        l.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        l.setDrawInside(false);

        XAxis xAxis = mChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTH_SIDED);
        xAxis.setAxisMinimum(0f);
        xAxis.setGranularity(1f);
        xAxis.setTextColor(Color.WHITE);
        xAxis.setDrawGridLines(false);
        if (xValue.length>0){
            xAxis.setValueFormatter((value, axis) -> xValue[(int) value % xValue.length]);
        }
        YAxis yAxis = mChart.getAxisLeft();
        yAxis.setDrawGridLines(false);
        yAxis.setTextColor(Color.WHITE);

        YAxis yAxis1 = mChart.getAxisRight();
        yAxis1.setDrawGridLines(false);
        yAxis1.setTextColor(Color.WHITE);

        mChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, Highlight h) {
                String monitorTypeName = PreferenceUtils.getString(getContext(),"monitorTypeName");
                if ("雨量".equals(monitorTypeName)){
                    ToastUtils.showShortToast(
                            curvelData.get((int)e.getX()).getPreviousTime()+"\n"
                                    +"单次变化量(mm): "+ formatFloatNumber(curvelData.get((int)e.getX()).getCurrentLaserChange())+"\n"
                                    +"变化速率(mm/min): "+ formatFloatNumber(curvelData.get((int)e.getX()).getSpeedChange()));
                }else if ("渗流压力".equals(monitorTypeName)){
                    ToastUtils.showShortToast(
                            curvelData.get((int)e.getX()).getPreviousTime()+"\n"+
                                    "当前值(mm): "+formatFloatNumber(curvelData.get((int)e.getX()).getCurrentData())+"\n"
                                    +"单次变化量(mm): "+ formatFloatNumber(curvelData.get((int)e.getX()).getCurrentLaserChange()));
                }else if ("干滩高程".equals(monitorTypeName)){
                    ToastUtils.showShortToast(
                            curvelData.get((int)e.getX()).getPreviousTime()+"\n"+
                                    "高度(m): "+formatFloatNumber(curvelData.get((int)e.getX()).getTotalLaserChange())+"\n"
                                    +"宽度(m): "+ formatFloatNumber(curvelData.get((int)e.getX()).getCurrentLaserChange()));
                }else if ("库水位".equals(monitorTypeName)){
                    ToastUtils.showShortToast(
                            curvelData.get((int)e.getX()).getPreviousTime()+"\n"+
                                    "当前值(mm): "+formatFloatNumber(curvelData.get((int)e.getX()).getCurrentData())+"\n"
                                    +"单次变化量(mm): "+ formatFloatNumber(curvelData.get((int)e.getX()).getCurrentLaserChange()));
                }else {
                    ToastUtils.showShortToast(
                            curvelData.get((int)e.getX()).getPreviousTime()+"\n"+
                                    "累计变化量(mm): "+formatFloatNumber(curvelData.get((int)e.getX()).getTotalLaserChange())+"\n"
                                    +"单次变化量(mm): "+ formatFloatNumber(curvelData.get((int)e.getX()).getCurrentLaserChange())+"\n"
                                    +"变化速率(mm/min): "+ formatFloatNumber(curvelData.get((int)e.getX()).getSpeedChange()));
                }

            }
            @Override
            public void onNothingSelected() {

            }
        });
    }
    private void curvelData3(String[] xValue,ArrayList<CurveDetailEntity.DataBean> data1){
        mBinder.chart1.setVisibility(View.VISIBLE);
        mBinder.chart2.setVisibility(View.GONE);
        mChart = mBinder.chart1;
        // no description text
        mChart.getDescription().setEnabled(false);
        // enable touch gestures
        mChart.setTouchEnabled(true);
        mChart.setDragDecelerationFrictionCoef(0.9f);
        // enable scaling and dragging
        mChart.setDragEnabled(true);
        mChart.setScaleEnabled(true);
        mChart.setDrawGridBackground(false);
        mChart.setHighlightPerDragEnabled(true);
        mChart.setDrawBorders(false);
        // if disabled, scaling can be done on x- and y-axis separately
        mChart.setPinchZoom(true);

        // set an alternative background color
        mChart.setBackgroundColor(Color.parseColor("#192531"));

        setData3(data1);

        mChart.animateX(2500);
        // get the legend (only possible after setting data)
        Legend l = mChart.getLegend();
        // modify the legend ...
        l.setForm(Legend.LegendForm.LINE);
        l.setTypeface(mTfLight);
        l.setTextSize(11f);
        l.setTextColor(Color.WHITE);
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT);
        l.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        l.setDrawInside(false);

        XAxis xAxis = mChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTH_SIDED);
        xAxis.setAxisMinimum(0f);
        xAxis.setGranularity(1f);
        xAxis.setTextColor(Color.WHITE);
        xAxis.setDrawGridLines(false);
        if (xValue.length>0){
            xAxis.setValueFormatter((value, axis) -> xValue[(int) value % xValue.length]);
        }
        YAxis yAxis = mChart.getAxisLeft();
        yAxis.setDrawGridLines(false);
        yAxis.setTextColor(Color.WHITE);

        YAxis yAxis1 = mChart.getAxisRight();
        yAxis1.setDrawGridLines(false);
        yAxis1.setTextColor(Color.WHITE);

        mChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, Highlight h) {
                String monitorTypeName = PreferenceUtils.getString(getContext(),"monitorTypeName");
                if ("雨量".equals(monitorTypeName)){
                    ToastUtils.showShortToast(
                            curvelData.get((int)e.getX()).getPreviousTime()+"\n"
                                    +"单次变化量(mm): "+ formatFloatNumber(curvelData.get((int)e.getX()).getCurrentLaserChange())+"\n"
                                    +"变化速率(mm/min): "+ formatFloatNumber(curvelData.get((int)e.getX()).getSpeedChange()));
                }else if ("渗流压力".equals(monitorTypeName)){
                    ToastUtils.showShortToast(
                            curvelData.get((int)e.getX()).getPreviousTime()+"\n"+
                                    "当前值(mm): "+formatFloatNumber(curvelData.get((int)e.getX()).getCurrentData())+"\n"
                                    +"单次变化量(mm): "+ formatFloatNumber(curvelData.get((int)e.getX()).getCurrentLaserChange()));
                }else if ("干滩高程".equals(monitorTypeName)){
                    ToastUtils.showShortToast(
                            curvelData.get((int)e.getX()).getPreviousTime()+"\n"+
                                    "高度(m): "+formatFloatNumber(curvelData.get((int)e.getX()).getTotalLaserChange())+"\n"
                                    +"宽度(m): "+ formatFloatNumber(curvelData.get((int)e.getX()).getCurrentLaserChange()));
                }else if ("库水位".equals(monitorTypeName)){
                    ToastUtils.showShortToast(
                            curvelData.get((int)e.getX()).getPreviousTime()+"\n"+
                                    "当前值(mm): "+formatFloatNumber(curvelData.get((int)e.getX()).getCurrentData())+"\n"
                                    +"单次变化量(mm): "+ formatFloatNumber(curvelData.get((int)e.getX()).getCurrentLaserChange()));
                }else {
                    ToastUtils.showShortToast(
                            curvelData.get((int)e.getX()).getPreviousTime()+"\n"+
                                    "累计变化量(mm): "+formatFloatNumber(curvelData.get((int)e.getX()).getTotalLaserChange())+"\n"
                                    +"单次变化量(mm): "+ formatFloatNumber(curvelData.get((int)e.getX()).getCurrentLaserChange())+"\n"
                                    +"变化速率(mm/min): "+ formatFloatNumber(curvelData.get((int)e.getX()).getSpeedChange()));
                }

            }
            @Override
            public void onNothingSelected() {

            }
        });
    }
    private void curvelData4(String[] xValueA){
        mBinder.chart1.setVisibility(View.VISIBLE);
        mBinder.chart2.setVisibility(View.GONE);
        mChart = mBinder.chart1;
        // no description text
        mChart.getDescription().setEnabled(false);
        // enable touch gestures
        mChart.setTouchEnabled(true);
        mChart.setDragDecelerationFrictionCoef(0.9f);
        // enable scaling and dragging
        mChart.setDragEnabled(true);
        mChart.setScaleEnabled(true);
        mChart.setDrawGridBackground(false);
        mChart.setHighlightPerDragEnabled(true);
        mChart.setDrawBorders(false);
        // if disabled, scaling can be done on x- and y-axis separately
        mChart.setPinchZoom(true);

        // set an alternative background color
        mChart.setBackgroundColor(Color.parseColor("#192531"));
        // add data
        setData4();
        mChart.animateX(2500);
        // get the legend (only possible after setting data)
        Legend l = mChart.getLegend();
        // modify the legend ...
        l.setForm(Legend.LegendForm.LINE);
        l.setTypeface(mTfLight);
        l.setTextSize(11f);
        l.setTextColor(Color.WHITE);
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT);
        l.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        l.setDrawInside(false);

        XAxis xAxis = mChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTH_SIDED);
        xAxis.setAxisMinimum(0f);
        xAxis.setGranularity(1f);
        xAxis.setTextColor(Color.WHITE);
        xAxis.setDrawGridLines(false);
        if (xValueA.length>0){
            xAxis.setValueFormatter((value, axis) -> xValueA[(int) value % xValueA.length]);
        }
        YAxis yAxis = mChart.getAxisLeft();
        yAxis.setDrawGridLines(false);
        yAxis.setTextColor(Color.WHITE);

        YAxis yAxis1 = mChart.getAxisRight();
        yAxis1.setDrawGridLines(false);
        yAxis1.setTextColor(Color.WHITE);
        ArrayList<String> data = new ArrayList<>();
        data.add("3.312");
        data.add("7.824");
        data.add("10.757");
        data.add("12.835");
        mChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, Highlight h) {
                String monitorTypeName = PreferenceUtils.getString(getContext(),"monitorTypeName");
                if ("雨量".equals(monitorTypeName)){
                    ToastUtils.showShortToast(
                            curvelData.get((int)e.getX()).getPreviousTime()+"\n"
                                    +"单次变化量(mm): "+ formatFloatNumber(curvelData.get((int)e.getX()).getCurrentLaserChange())+"\n"
                                    +"变化速率(mm/min): "+ formatFloatNumber(curvelData.get((int)e.getX()).getSpeedChange()));
                }else if ("渗流压力".equals(monitorTypeName)){
                    ToastUtils.showShortToast(
                            ""+"\n"+
                                    "当前值(mm): "+formatFloatNumber(Double.parseDouble(data.get((int)e.getX()))));
                }else if ("干滩高程".equals(monitorTypeName)){
                    ToastUtils.showShortToast(
                            curvelData.get((int)e.getX()).getPreviousTime()+"\n"+
                                    "高度(m): "+formatFloatNumber(curvelData.get((int)e.getX()).getTotalLaserChange())+"\n"
                                    +"宽度(m): "+ formatFloatNumber(curvelData.get((int)e.getX()).getCurrentLaserChange()));
                }else if ("库水位".equals(monitorTypeName)){
                    ToastUtils.showShortToast(
                            curvelData.get((int)e.getX()).getPreviousTime()+"\n"+
                                    "当前值(mm): "+formatFloatNumber(curvelData.get((int)e.getX()).getCurrentData())+"\n"
                                    +"单次变化量(mm): "+ formatFloatNumber(curvelData.get((int)e.getX()).getCurrentLaserChange()));
                }else {
                    ToastUtils.showShortToast(
                            curvelData.get((int)e.getX()).getPreviousTime()+"\n"+
                                    "累计变化量(mm): "+formatFloatNumber(curvelData.get((int)e.getX()).getTotalLaserChange())+"\n"
                                    +"单次变化量(mm): "+ formatFloatNumber(curvelData.get((int)e.getX()).getCurrentLaserChange())+"\n"
                                    +"变化速率(mm/min): "+ formatFloatNumber(curvelData.get((int)e.getX()).getSpeedChange()));
                }

            }
            @Override
            public void onNothingSelected() {

            }
        });
    }
    private void curvelData5(String[] xValue,ArrayList<CurveDetailEntity.DataBean> data1){
        mBinder.chart1.setVisibility(View.VISIBLE);
        mBinder.chart2.setVisibility(View.GONE);
        mChart = mBinder.chart1;
        // no description text
        mChart.getDescription().setEnabled(false);
        // enable touch gestures
        mChart.setTouchEnabled(true);
        mChart.setDragDecelerationFrictionCoef(0.9f);
        // enable scaling and dragging
        mChart.setDragEnabled(true);
        mChart.setScaleEnabled(true);
        mChart.setDrawGridBackground(false);
        mChart.setHighlightPerDragEnabled(true);
        mChart.setDrawBorders(false);
        // if disabled, scaling can be done on x- and y-axis separately
        mChart.setPinchZoom(true);

        // set an alternative background color
        mChart.setBackgroundColor(Color.parseColor("#192531"));

        setData5(data1);
        mChart.animateX(2500);
        // get the legend (only possible after setting data)
        Legend l = mChart.getLegend();
        // modify the legend ...
        l.setForm(Legend.LegendForm.LINE);
        l.setTypeface(mTfLight);
        l.setTextSize(11f);
        l.setTextColor(Color.WHITE);
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT);
        l.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        l.setDrawInside(false);

        XAxis xAxis = mChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTH_SIDED);
        xAxis.setAxisMinimum(0f);
        xAxis.setGranularity(1f);
        xAxis.setTextColor(Color.WHITE);
        xAxis.setDrawGridLines(false);
        if (xValue.length>0){
            xAxis.setValueFormatter((value, axis) -> xValue[(int) value % xValue.length]);
        }
        YAxis yAxis = mChart.getAxisLeft();
        yAxis.setDrawGridLines(false);
        yAxis.setTextColor(Color.WHITE);

        YAxis yAxis1 = mChart.getAxisRight();
        yAxis1.setDrawGridLines(false);
        yAxis1.setTextColor(Color.WHITE);

        mChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, Highlight h) {
                String monitorTypeName = PreferenceUtils.getString(getContext(),"monitorTypeName");
                if ("雨量".equals(monitorTypeName)){
                    ToastUtils.showShortToast(
                            curvelData.get((int)e.getX()).getPreviousTime()+"\n"
                                    +"单次变化量(mm): "+ formatFloatNumber(curvelData.get((int)e.getX()).getCurrentLaserChange())+"\n"
                                    +"变化速率(mm/min): "+ formatFloatNumber(curvelData.get((int)e.getX()).getSpeedChange()));
                }else if ("渗流压力".equals(monitorTypeName)){
                    ToastUtils.showShortToast(
                            curvelData.get((int)e.getX()).getPreviousTime()+"\n"+
                                    "当前值(mm): "+formatFloatNumber(curvelData.get((int)e.getX()).getCurrentData())+"\n"
                                    +"单次变化量(mm): "+ formatFloatNumber(curvelData.get((int)e.getX()).getCurrentLaserChange()));
                }else if ("干滩高程".equals(monitorTypeName)){
                    ToastUtils.showShortToast(
                            curvelData.get((int)e.getX()).getPreviousTime()+"\n"+
                                    "高度(m): "+formatFloatNumber(curvelData.get((int)e.getX()).getTotalLaserChange())+"\n"
                                    +"宽度(m): "+ formatFloatNumber(curvelData.get((int)e.getX()).getCurrentLaserChange()));
                }else if ("库水位".equals(monitorTypeName)){
                    ToastUtils.showShortToast(
                            curvelData.get((int)e.getX()).getPreviousTime()+"\n"+
                                    "当前值(mm): "+formatFloatNumber(curvelData.get((int)e.getX()).getCurrentData())+"\n"
                                    +"单次变化量(mm): "+ formatFloatNumber(curvelData.get((int)e.getX()).getCurrentLaserChange()));
                }else {
                    ToastUtils.showShortToast(
                            curvelData.get((int)e.getX()).getPreviousTime()+"\n"+
                                    "累计变化量(mm): "+formatFloatNumber(curvelData.get((int)e.getX()).getTotalLaserChange())+"\n"
                                    +"单次变化量(mm): "+ formatFloatNumber(curvelData.get((int)e.getX()).getCurrentLaserChange())+"\n"
                                    +"变化速率(mm/min): "+ formatFloatNumber(curvelData.get((int)e.getX()).getSpeedChange()));
                }

            }
            @Override
            public void onNothingSelected() {

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
                data = (ArrayList< ProjectDetailEntity.DataBean >)bean;
                if (data.size()>0){
                    //尾矿库数据手动添加
                    String string = PreferenceUtils.getString(getContext(),"projectName");
                    if ("黄金洞尾矿库".equals(string)){
                        ArrayList<ProjectDetailEntity.DataBean.SensorListBean> sensorList = new ArrayList<>();
                        ProjectDetailEntity.DataBean.SensorListBean sensorListBean = new ProjectDetailEntity.DataBean.SensorListBean();
                        sensorListBean.setMonitorPoint("GT01");
                        sensorListBean.setSensorNumber("02");
                        sensorListBean.setSmuChannel("00");
                        sensorListBean.setSmuNumber("2017100005");
                        sensorListBean.setSpeedChange(0.000);
                        sensorListBean.setTotalLaserChange(0.000);
                        sensorList.add(sensorListBean);

                        ProjectDetailEntity.DataBean dataBean = new ProjectDetailEntity.DataBean();
                        dataBean.setMonitorType(15);
                        dataBean.setMonitorTypeName("干滩高程");
                        dataBean.setTableName("laser_data");
                        dataBean.setSensorList(sensorList);
                        data.add(dataBean);
                    }
                    ArrayList<String> list= new ArrayList<>();
                    list.clear();
                    for (ProjectDetailEntity.DataBean datum : data) {
                        list.add(datum.getMonitorTypeName());
                    }
                    inData(list);
                    if (PreferenceUtils.getString(getContext(),"monitorTypeName")==null
                            ||"".equals(PreferenceUtils.getString(getContext(),"monitorTypeName"))){
                        PreferenceUtils.putString(getContext(),"monitorTypeName",list.get(0));
                    }
                    if (list.size()==1){
                        if ("深部位移".equals(list.get(0))){
                            //默认时间是当前时间往前推两个小时
                            long time = System.currentTimeMillis();
                            long time1 = time-7200000;
                            String start = PreferenceUtils.getString(getContext(),"startTime1");
                            if (start!=null&&!"".equals(startText)){
                                startText = start;
                            }else {
                                startText = DateUtil.getDateToStringss(time1);
                            }
                            String monitorPoint;
                            if (PreferenceUtils.getString(getContext(),"monitorPoint")==null||
                                    "".equals(PreferenceUtils.getString(getContext(),"monitorPoint"))){
                                monitorPoint = data.get(0).getSensorList().get(0).getMonitorPoint();
                            }else {
                                monitorPoint = PreferenceUtils.getString(getContext(),"monitorPoint");
                            }
                            Map<String,String> map = new HashMap<>(0);
                            map.put("projectId",PreferenceUtils.getInt(getContext(),"projectId",0)+"");
                            map.put("monitorPoint",monitorPoint);
                            map.put("date",startText);
                            mControl.getProjectDeepDispalcementDetailDetail(ProjectDetailModel.this,map,3);
                        }else {
                            requestCurvelData();
                        }
                    }else {
                        requestCurvelData();
                    }
                }
                break;
            case 2:
                mBinder.bootomRecyclerView.setVisibility(View.VISIBLE);
                mBinder.llRadio.setVisibility(View.GONE);
                curvelData = (ArrayList< CurveDetailEntity.DataBean >)bean;
                if (curvelData.size()>1){
                    mBinder.llNoCurvel.setVisibility(View.GONE);
                    mBinder.chart1.setVisibility(View.VISIBLE);
                    ArrayList<String> list1= new ArrayList<>(Arrays.asList(xValue1));
                    list1.clear();
                    for (CurveDetailEntity.DataBean dataBean : curvelData) {
                        String result = dataBean.getPreviousTime().substring(dataBean.getPreviousTime().indexOf(" "),dataBean.getPreviousTime().length());
                        list1.add(result);
                    }
                    xValue1 = list1.toArray(new String[list1.size()]);
                    String monitorTypeName = PreferenceUtils.getString(getContext(),"monitorTypeName");
                    if ("雨量".equals(monitorTypeName)){
                        curvelData2(xValue1,curvelData);
                    }else if ("渗流压力".equals(monitorTypeName)){
                        curvelData3(xValue1,curvelData);
                        bootomRecycViewAdapter.setDataList(mVals2);
                        mBinder.bootomRecyclerView.setAdapter(bootomRecycViewAdapter);
                    }else if ("干滩高程".equals(monitorTypeName)){
                        curvelData5(xValue1,curvelData);
                    }else if ("库水位".equals(monitorTypeName)){
                        curvelData3(xValue1,curvelData);
                    }else {
                        curvelData(xValue1,curvelData);
                    }
                    //记录用户选择的时间段
                    PreferenceUtils.putString(getContext(),"startTime",startText);
                    PreferenceUtils.putString(getContext(),"endTime",endText);
                    topRecycViewAdapter.getProjectName(monitorPoint);
                    PreferenceUtils.putInt(getContext(),"requestSelectedPosition",requestSelectedPosition);
                }else {
                    mBinder.llNoCurvel.setVisibility(View.VISIBLE);
                    mBinder.chart1.setVisibility(View.GONE);
                    mBinder.chart2.setVisibility(View.GONE);
                    mBinder.bootomRecyclerView.setVisibility(View.VISIBLE);
                    mBinder.llRadio.setVisibility(View.GONE);
                }
                break;
            case 3:
                mBinder.bootomRecyclerView.setVisibility(View.GONE);
                mBinder.llRadio.setVisibility(View.VISIBLE);
                deepDispalcementEntity = (ArrayList<DeepDispalcementEntity.DataBean>)bean;
                if (deepDispalcementEntity.size()>1){
                    mBinder.llNoCurvel.setVisibility(View.GONE);
                    mBinder.chart1.setVisibility(View.VISIBLE);
                    ArrayList<String> list2= new ArrayList<>(Arrays.asList(xValue2));
                    list2.clear();
                    for (DeepDispalcementEntity.DataBean dataBean : deepDispalcementEntity) {
                        String string = formatFloatNumber(dataBean.getDepth());
                        list2.add(string);
                    }
                    xValue2 = list2.toArray(new String[list2.size()]);
                    curvelData1(xValue2,deepDispalcementEntity);
                    PreferenceUtils.putString(getContext(),"startTime1",startText);
                }else {
                    mBinder.llNoCurvel.setVisibility(View.VISIBLE);
                    mBinder.chart1.setVisibility(View.GONE);
                }
                break;
                default:
                    break;
        }

    }

    private void requestCurvelData(){

        String tableName;
        String sensorNumber;
        String smuNumber;
        String smuChannel;

        if ("".equals(PreferenceUtils.getString(getContext(),"tableName"))
                ||PreferenceUtils.getString(getContext(),"tableName")==null){
            tableName = data.get(0).getTableName();
        }else {
            tableName = PreferenceUtils.getString(getContext(),"tableName");
        }

        if (PreferenceUtils.getString(getContext(),"sensorNumber")==null){
            sensorNumber = data.get(0).getSensorList().get(0).getSensorNumber();
        }else {
            sensorNumber = PreferenceUtils.getString(getContext(),"sensorNumber");
        }

        if (PreferenceUtils.getString(getContext(),"smuNumber")==null){
            smuNumber = data.get(0).getSensorList().get(0).getSmuNumber();
        }else {
            smuNumber = PreferenceUtils.getString(getContext(),"smuNumber");
        }

        if (PreferenceUtils.getString(getContext(),"smuChannel")==null){
            smuChannel = data.get(0).getSensorList().get(0).getSmuChannel();
        }else {
            smuChannel = PreferenceUtils.getString(getContext(),"smuChannel");
        }

        Map<String,String> map = new HashMap<>(0);
        map.put("tableName",tableName);
        map.put("sensorNumber",sensorNumber);
        map.put("smuNumber",smuNumber);
        map.put("smuChannel",smuChannel);
        map.put("beginTime",getDateToString(time1));
        map.put("endTime",getDateToString(time));
        mControl.getProjectCurveDetail(ProjectDetailModel.this,map,2);
        mBinder.include.tvTime.setText(getDateToString(time1)+" "+getDateToString(time));
        PreferenceUtils.putString(getContext(),"firstStartTime",getDateToString(time1));
        PreferenceUtils.putString(getContext(),"firstEndTime",getDateToString(time));
        monitorPoint = data.get(PreferenceUtils.getInt(getContext(),"topPosition")).getSensorList().get(PreferenceUtils.getInt(getContext(),"leftPosition")).getMonitorPoint();
    }

    @Override
    public void onError(String errorMsg, int code,int tag) {

    }

    @Override
    public void onItemClick(View view, int position) {

    }

    @Override
    public void initListener() {
   }

    @Override
    public void chooseTime() {

    }

    @Override
    public void onResume() {
        requestData();
    }

    public  void getPosition(int i){
        this.defaultPosition = i;
    }

    private void requestData(){
        mBinder.tvProject.setText(PreferenceUtils.getString(getContext(),"projectName"));

        if (topRecycViewAdapter!=null){
            //刷新指标选中颜色
            topRecycViewAdapter.getPosition(topPosition);
            String d = PreferenceUtils.getString(getContext(),"monitorPoint");
            topRecycViewAdapter.getProjectName(d);
            topRecycViewAdapter.notifyDataSetChanged();
        }
        Map<String,String> map = new HashMap<>(0);
        map.put("projectId",String.valueOf(PreferenceUtils.getInt(BaseApplication.getContext(),"projectId",0)));
        mControl.getProjectDetail(this,map,1);
    }

    private void timeResetData(){
        int leftPosition = PreferenceUtils.getInt(getContext(),"leftPosition");
        int topPosition = PreferenceUtils.getInt(getContext(),"topPosition");
        if (detailTag){
            Map<String,String> map = new HashMap<>(0);
            map.put("projectId",PreferenceUtils.getInt(getContext(),"projectId",0)+"");
            map.put("monitorPoint",data.get(topPosition).getSensorList().get(leftPosition).getMonitorPoint());
            map.put("date",PreferenceUtils.getString(getContext(),"singleTime"));
            mControl.getProjectDeepDispalcementDetailDetail(ProjectDetailModel.this,map,3);
        }else {
            Map<String,String> map = new HashMap<>(0);
            map.put("tableName",data.get(topPosition).getTableName());
            map.put("sensorNumber",data.get(topPosition).getSensorList().get(leftPosition).getSensorNumber());
            map.put("smuNumber",data.get(topPosition).getSensorList().get(leftPosition).getSmuNumber());
            map.put("smuChannel",data.get(topPosition).getSensorList().get(leftPosition).getSmuChannel());
            map.put("beginTime",st);
            map.put("endTime",et);

            if ("".equals(st)||"".equals(et)){
                ToastUtils.showLongToast("请选择时间");
            }else {
                mControl.getProjectCurveDetail(ProjectDetailModel.this,map,2);
                PreferenceUtils.putString(getContext(),"sensorNumber",data.get(topPosition).getSensorList().get(leftPosition).getSensorNumber());
                PreferenceUtils.putString(getContext(),"smuNumber",data.get(topPosition).getSensorList().get(leftPosition).getSmuNumber());
                PreferenceUtils.putString(getContext(),"smuChannel",data.get(topPosition).getSensorList().get(leftPosition).getSmuChannel());
            }
        }
    }
    /**
     * 统一曲线图设值
     */
    private void setData(ArrayList<CurveDetailEntity.DataBean> data1) {

        ArrayList<Entry> yVals1 = new ArrayList<>();
        for (int i = 0; i < data1.size(); i++) {
            yVals1.add(new Entry(i, (float) data1.get(i).getTotalLaserChange()));
        }
        ArrayList<Entry> yVals2 = new ArrayList<>();
        for (int i = 0; i < data1.size(); i++) {
            yVals2.add(new Entry(i, (float) data1.get(i).getCurrentLaserChange()));
        }
        ArrayList<Entry> yVals3 = new ArrayList<>();
        for (int i = 0; i < data1.size(); i++) {
            yVals3.add(new Entry(i, (float) data1.get(i).getSpeedChange()));
        }

        LineDataSet set1, set2, set3;

            set1 = new LineDataSet(yVals1, "累计变化量(mm)");
            set1.setAxisDependency(YAxis.AxisDependency.LEFT);
            set1.setColor(Color.parseColor("#00FFFF"));
            set1.setLineWidth(2f);
            set1.setFillAlpha(65);
            set1.setFillColor(ColorTemplate.getHoloBlue());
            set1.setDrawCircleHole(false);
            set1.setDrawCircles(false);
            set1.setValueTextColor(Color.WHITE);
            set1.setDrawValues(true);
            set1.setHighLightColor(Color.rgb(90,153,255));
            //设置小数点后面位数
            set1.setValueFormatter((value, entry, dataSetIndex, viewPortHandler) -> "   "+value);

            // create a dataset and give it a type
            set2 = new LineDataSet(yVals2, "单次变化量(mm)");
            set2.setAxisDependency(YAxis.AxisDependency.LEFT);
            set2.setColor(Color.parseColor("#89289c"));
            set2.setValueTextColor(Color.WHITE);
            set2.setLineWidth(2f);
            set2.setFillAlpha(65);
            set2.setFillColor(Color.RED);
            set2.setDrawCircleHole(false);
            set2.setDrawCircles(false);
            set2.setHighLightColor(Color.rgb(90,153,255));
            set2.setValueFormatter((value, entry, dataSetIndex, viewPortHandler) -> "   "+value);

            set3 = new LineDataSet(yVals3, "变化速率(mm/min)");
            set3.setAxisDependency(YAxis.AxisDependency.LEFT);
            set3.setColor(Color.parseColor("#2096F2"));
            set3.setValueTextColor(Color.WHITE);
            set3.setLineWidth(2f);
            set3.setDrawCircles(false);
            set3.setFillAlpha(65);
            set3.setFillColor(ColorTemplate.colorWithAlpha(Color.YELLOW, 200));
            set3.setDrawCircleHole(false);
            set3.setHighLightColor(Color.rgb(90,153,255));
            //设置小数点后面位数
            set3.setValueFormatter((value, entry, dataSetIndex, viewPortHandler) -> "    "+value);

            // create a data object with the datasets
            LineData data = new LineData(set1,set2,set3);
            //不显示圆点上的数值
            data.setDrawValues(false);
//            data.setValueTextColor(Color.WHITE);
            data.setValueFormatter(new DefaultValueFormatter(3));
            // set data
            mChart.setData(data);

//        }
    }

    /**
     * 雨量曲线图设值
     */
    private void setData2(ArrayList<CurveDetailEntity.DataBean> data1) {

        ArrayList<Entry> yVals2 = new ArrayList<>();
        for (int i = 0; i < data1.size(); i++) {
            yVals2.add(new Entry(i, (float) data1.get(i).getCurrentLaserChange()));
        }
        ArrayList<Entry> yVals3 = new ArrayList<>();
        for (int i = 0; i < data1.size(); i++) {
            yVals3.add(new Entry(i, (float) data1.get(i).getSpeedChange()));
        }

        LineDataSet set2, set3;

            set2 = new LineDataSet(yVals2, "单次变化量(mm)");
            set2.setAxisDependency(YAxis.AxisDependency.LEFT);
            set2.setColor(Color.parseColor("#89289c"));
            set2.setValueTextColor(Color.WHITE);
            set2.setLineWidth(2f);
            set2.setFillAlpha(65);
            set2.setFillColor(Color.RED);
            set2.setDrawCircleHole(false);
            set2.setDrawCircles(false);
            set2.setHighLightColor(Color.rgb(90,153,255));
            set2.setValueFormatter((value, entry, dataSetIndex, viewPortHandler) -> "   "+value);

            set3 = new LineDataSet(yVals3, "变化速率(mm/min)");
            set3.setAxisDependency(YAxis.AxisDependency.LEFT);
            set3.setColor(Color.parseColor("#2096F2"));
            set3.setValueTextColor(Color.WHITE);
            set3.setLineWidth(2f);
            set3.setDrawCircles(false);
            set3.setFillAlpha(65);
            set3.setFillColor(ColorTemplate.colorWithAlpha(Color.YELLOW, 200));
            set3.setDrawCircleHole(false);
            set3.setHighLightColor(Color.rgb(90,153,255));
            //设置小数点后面位数
            set3.setValueFormatter((value, entry, dataSetIndex, viewPortHandler) -> "    "+value);

            LineData data = new LineData(set2,set3);
            //不显示圆点上的数值
            data.setDrawValues(false);
            data.setValueFormatter(new DefaultValueFormatter(3));
            // set data
            mChart.setData(data);

//        }
    }

    /**
     * 渗流压力和库水位曲线图设值
     */
    private void setData3(ArrayList<CurveDetailEntity.DataBean> data1) {

        ArrayList<Entry> yVals2 = new ArrayList<>();
        for (int i = 0; i < data1.size(); i++) {
            yVals2.add(new Entry(i, (float) data1.get(i).getCurrentData()));
        }
        ArrayList<Entry> yVals3 = new ArrayList<>();
        for (int i = 0; i < data1.size(); i++) {
            yVals3.add(new Entry(i, (float) data1.get(i).getCurrentLaserChange()));
        }

        LineDataSet set2, set3;

            set2 = new LineDataSet(yVals2, "当前值(mm)");
            set2.setAxisDependency(YAxis.AxisDependency.LEFT);
            set2.setColor(Color.parseColor("#89289c"));
            set2.setValueTextColor(Color.WHITE);
            set2.setLineWidth(2f);
            set2.setFillAlpha(65);
            set2.setFillColor(Color.RED);
            set2.setDrawCircleHole(false);
            set2.setDrawCircles(false);
            set2.setHighLightColor(Color.rgb(90,153,255));
            set2.setValueFormatter((value, entry, dataSetIndex, viewPortHandler) -> "   "+value);

            set3 = new LineDataSet(yVals3, "单次变化量(mm/min)");
            set3.setAxisDependency(YAxis.AxisDependency.LEFT);
            set3.setColor(Color.parseColor("#2096F2"));
            set3.setValueTextColor(Color.WHITE);
            set3.setLineWidth(2f);
            set3.setDrawCircles(false);
            set3.setFillAlpha(65);
            set3.setFillColor(ColorTemplate.colorWithAlpha(Color.YELLOW, 200));
            set3.setDrawCircleHole(false);
            set3.setHighLightColor(Color.rgb(90,153,255));
            //设置小数点后面位数
            set3.setValueFormatter((value, entry, dataSetIndex, viewPortHandler) -> "    "+value);

            // create a data object with the datasets
            LineData data = new LineData(set2,set3);
            //不显示圆点上的数值
            data.setDrawValues(false);
            data.setValueFormatter(new DefaultValueFormatter(3));
            // set data
            mChart.setData(data);

//        }
    }

    /**
     * 渗流压力对比分析曲线图设值
     */
    private void setData4() {

        ArrayList<Entry> yVals2 = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            if (i==0){
                yVals2.add(new Entry(i, (float) 3.312));
            }else if (i==1){
                yVals2.add(new Entry(i, (float) 7.824));
            }else if (i==2){
                yVals2.add(new Entry(i, (float) 10.757));
            }else {
                yVals2.add(new Entry(i, (float) 12.835));
            }

        }
        LineDataSet set2;

        set2 = new LineDataSet(yVals2, "当前值(mm)");
        set2.setAxisDependency(YAxis.AxisDependency.LEFT);
        set2.setColor(Color.parseColor("#89289c"));
        set2.setValueTextColor(Color.WHITE);
        set2.setLineWidth(2f);
        set2.setFillAlpha(65);
        set2.setFillColor(Color.RED);
        set2.setDrawCircleHole(false);
        set2.setDrawCircles(false);
        set2.setHighLightColor(Color.rgb(90,153,255));
        set2.setValueFormatter((value, entry, dataSetIndex, viewPortHandler) -> "   "+value);

        // create a data object with the datasets
        LineData data = new LineData(set2);
        //不显示圆点上的数值
        data.setDrawValues(false);
        data.setValueFormatter(new DefaultValueFormatter(3));
        // set data
        mChart.setData(data);

//        }
    }

    /**
     * 深部位移曲线图设值
     */
    private void setData1(ArrayList<DeepDispalcementEntity.DataBean> data2) {

        ArrayList<Entry> yVals1 = new ArrayList<>();
        if (radioTag==1){
            for (int i = 0; i < data2.size(); i++) {
                yVals1.add(new Entry(i,(float) data2.get(i).getCurrentData().getX()));
            }
        }else if (radioTag==2){
            for (int i = 0; i < data2.size(); i++) {
                yVals1.add(new Entry(i,(float) data2.get(i).getCurrentLaserChange().getX()));
            }
        }else if (radioTag==3){
            for (int i = 0; i < data2.size(); i++) {
                yVals1.add(new Entry(i,(float) data2.get(i).getSpeedChange().getX()));
            }
        }

        ArrayList<Entry> yVals2 = new ArrayList<>();
        if (radioTag==1){
            for (int i = 0; i < data2.size(); i++) {
                yVals2.add(new Entry(i, (float) data2.get(i).getCurrentData().getY()));
            }
        }else if (radioTag==2){
            for (int i = 0; i < data2.size(); i++) {
                yVals2.add(new Entry(i, (float) data2.get(i).getCurrentLaserChange().getY()));
            }
        }else if (radioTag==3){
            for (int i = 0; i < data2.size(); i++) {
                yVals2.add(new Entry(i, (float) data2.get(i).getSpeedChange().getY()));
            }
        }

        LineDataSet set1, set2;

        if (mChart.getData() != null &&
                mChart.getData().getDataSetCount() > 0) {
            set1 = (LineDataSet) mChart.getData().getDataSetByIndex(0);
            set2 = (LineDataSet) mChart.getData().getDataSetByIndex(1);
            set1.setValues(yVals1);
            set2.setValues(yVals2);
            mChart.getData().notifyDataChanged();
            mChart.notifyDataSetChanged();
        } else {
            // create a dataset and give it a type
            set1 = new LineDataSet(yVals1, "X通道");

            set1.setAxisDependency(YAxis.AxisDependency.LEFT);
            set1.setColor(Color.parseColor("#89289c"));
            set1.setLineWidth(2f);
            set1.setFillAlpha(65);
            set1.setFillColor(ColorTemplate.getHoloBlue());
            set1.setDrawCircleHole(false);
            set1.setDrawCircles(false);
            set1.setValueTextColor(Color.WHITE);

            set1.setDrawValues(true);
            set1.setHighLightColor(Color.rgb(90,153,255));
            //设置小数点后面位数
            set1.setValueFormatter((value, entry, dataSetIndex, viewPortHandler) -> "   "+value);

            // create a dataset and give it a type
            set2 = new LineDataSet(yVals2, "Y通道");
            set2.setAxisDependency(YAxis.AxisDependency.LEFT);
            set2.setColor(Color.parseColor("#2096F2"));
            set2.setValueTextColor(Color.WHITE);
            set2.setLineWidth(2f);
            set2.setFillAlpha(65);
            set2.setFillColor(Color.RED);
            set2.setDrawCircleHole(false);
            set2.setDrawCircles(false);
            set2.setHighLightColor(Color.rgb(90,153,255));
            set2.setValueFormatter((value, entry, dataSetIndex, viewPortHandler) -> "   "+value);

            // create a data object with the datasets
            LineData data = new LineData(set1, set2);
            //不显示圆点上的数值
            data.setDrawValues(false);

//            data.setValueTextColor(Color.WHITE);
            data.setValueFormatter(new DefaultValueFormatter(3));
            // set data
            mChart.setData(data);

        }
    }
    /**
     * 干滩高程曲线图设值
     */
    private void setData5(ArrayList<CurveDetailEntity.DataBean> data1) {

        ArrayList<Entry> yVals1 = new ArrayList<>();
        for (int i = 0; i < data1.size(); i++) {
            yVals1.add(new Entry(i, (float) data1.get(i).getTotalLaserChange()));
        }
        ArrayList<Entry> yVals2 = new ArrayList<>();
        for (int i = 0; i < data1.size(); i++) {
            yVals2.add(new Entry(i, (float) data1.get(i).getCurrentLaserChange()));
        }
        LineDataSet set1, set2;

        set1 = new LineDataSet(yVals1, "高度(m)");
        set1.setAxisDependency(YAxis.AxisDependency.LEFT);
        set1.setColor(Color.parseColor("#00FFFF"));
        set1.setLineWidth(2f);
        set1.setFillAlpha(65);
        set1.setFillColor(ColorTemplate.getHoloBlue());
        set1.setDrawCircleHole(false);
        set1.setDrawCircles(false);
        set1.setValueTextColor(Color.WHITE);
        set1.setDrawValues(true);
        set1.setHighLightColor(Color.rgb(90,153,255));
        //设置小数点后面位数
        set1.setValueFormatter((value, entry, dataSetIndex, viewPortHandler) -> "   "+value);

        // create a dataset and give it a type
        set2 = new LineDataSet(yVals2, "宽度(m)");
        set2.setAxisDependency(YAxis.AxisDependency.LEFT);
        set2.setColor(Color.parseColor("#89289c"));
        set2.setValueTextColor(Color.WHITE);
        set2.setLineWidth(2f);
        set2.setFillAlpha(65);
        set2.setFillColor(Color.RED);
        set2.setDrawCircleHole(false);
        set2.setDrawCircles(false);
        set2.setHighLightColor(Color.rgb(90,153,255));
        set2.setValueFormatter((value, entry, dataSetIndex, viewPortHandler) -> "   "+value);

        // create a data object with the datasets
        LineData data = new LineData(set1,set2);
        //不显示圆点上的数值
        data.setDrawValues(false);
        data.setValueFormatter(new DefaultValueFormatter(3));
        // set data
        mChart.setData(data);

//        }
    }

    /**
     * 数据变成科学计数法显示。用此方法可以使其正常显示。
     */
    private  String formatFloatNumber(double value) {
        DecimalFormat df = new DecimalFormat("0.000");
        return df.format(value);
    }

}
