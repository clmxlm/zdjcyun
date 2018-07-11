package com.zdjc.zdjcyun.mvp.viewmodel.impl;


import android.annotation.SuppressLint;
import android.graphics.Color;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.bin.david.form.data.column.Column;
import com.bin.david.form.data.format.bg.BaseBackgroundFormat;
import com.bin.david.form.data.style.FontStyle;
import com.bin.david.form.data.table.TableData;
import com.bin.david.form.utils.DensityUtils;
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
import com.zdjc.zdjcyun.databinding.FragmentProjectDetailBinding;
import com.zdjc.zdjcyun.event.BottomPopWindow;
import com.zdjc.zdjcyun.event.DetailPopWindow;
import com.zdjc.zdjcyun.mvp.entity.CurveDetailEntity;
import com.zdjc.zdjcyun.mvp.entity.ProjectDetailEntity;
import com.zdjc.zdjcyun.mvp.entity.UserInfo;
import com.zdjc.zdjcyun.mvp.presenter.impl.ProjectDetailPresenterImpl;
import com.zdjc.zdjcyun.mvp.ui.adapter.BootomRecycViewAdapter;
import com.zdjc.zdjcyun.mvp.ui.adapter.ProjectDetailRecycViewAdapter;
import com.zdjc.zdjcyun.mvp.ui.adapter.TopRecycViewAdapter;
import com.zdjc.zdjcyun.mvp.ui.fragment.ProjectDetailFragment;
import com.zdjc.zdjcyun.mvp.viewmodel.IProjectDetailModel;
import com.zdjc.zdjcyun.pickerview.TimePickerDialog;
import com.zdjc.zdjcyun.pickerview.data.Type;
import com.zdjc.zdjcyun.pickerview.listener.OnDateSetListener;
import com.zdjc.zdjcyun.util.DateUtil;
import com.zdjc.zdjcyun.util.PreferenceUtils;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ali on 2017/2/20.
 */

public class ProjectDetailModel extends BaseModel<FragmentProjectDetailBinding,ProjectDetailPresenterImpl> implements IProjectDetailModel,
        OnItemClickListener, View.OnClickListener, OnDateSetListener {


    private TimePickerDialog mDialogAll;
    @SuppressLint("SimpleDateFormat")
    private
    SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    private ProjectDetailFragment projectDetailFragment;
    private LineChart mChart;
    private boolean endOrStart;
    private DetailPopWindow detailPopWindow;
    private BottomPopWindow bottomPopWindow;
    private ProjectDetailRecycViewAdapter projectDetailRecycViewAdapter;
    private TopRecycViewAdapter topRecycViewAdapter;
    private BootomRecycViewAdapter bootomRecycViewAdapter;
    private String[] xValue1 = new String[]{};

    private ArrayList<String> mVals1 = new ArrayList<>();

    private  ArrayList<ProjectDetailEntity.DataBean> data = new ArrayList<>();
    private  ArrayList<CurveDetailEntity.DataBean> curvelData = new ArrayList<>();
    private int topPosition = 0;
    private String startText = "";
    private String endText = "";
    private int leftPosition = 0;
    private String monitorPoint;
    private int defaultPosition = 0;
    private int requestSelectedPosition = 0;


    @Override
    public void onCreate() {
        startText = mBinder.tvChooseStartTime.getText().toString();
        endText = mBinder.tvChooseEndTime.getText().toString();
        requestData(false);
        initListener();
        PreferenceUtils.putInt(getContext(),"topPosition",0);
        PreferenceUtils.putInt(getContext(),"leftPosition",0);
        mVals1.add("测点描述");
        mVals1.add("数据信息");
    }

    private void inData(ArrayList<String> list) {
        mBinder.tvProject.setText(PreferenceUtils.getString(getContext(),"projectName"));
        projectDetailFragment=(ProjectDetailFragment)UI;
        if (projectDetailRecycViewAdapter == null){
            projectDetailRecycViewAdapter = new ProjectDetailRecycViewAdapter(projectDetailFragment.getActivity());
        }
        if (topRecycViewAdapter == null){
            topRecycViewAdapter = new TopRecycViewAdapter(projectDetailFragment.getActivity());
        }
        if (bootomRecycViewAdapter == null){
            bootomRecycViewAdapter = new BootomRecycViewAdapter(projectDetailFragment.getActivity());
        }
        long tenYears = 20L * 365 * 1000 * 60 * 60 * 24L;
        mDialogAll = new TimePickerDialog.Builder()
                .setCallBack(this)
                .setCancelStringId(getContext().getResources().getString(R.string.picker_cancel))
                .setSureStringId(getContext().getResources().getString(R.string.picker_sure))
                .setTitleStringId(getContext().getResources().getString(R.string.picker_title))
                .setYearText(getContext().getResources().getString(R.string.picker_year))
                .setMonthText(getContext().getResources().getString(R.string.picker_month))
                .setDayText(getContext().getResources().getString(R.string.picker_day))
                .setHourText(getContext().getResources().getString(R.string.picker_hour))
                .setMinuteText(getContext().getResources().getString(R.string.picker_minute))
                .setCyclic(true)
                .setMinMillseconds(System.currentTimeMillis()-tenYears)
                .setMaxMillseconds(System.currentTimeMillis() + tenYears)
                .setCurrentMillseconds(System.currentTimeMillis())
                .setThemeColor(getContext().getResources().getColor(R.color.timepicker_dialog_bg))
                .setType(Type.ALL)
                .setWheelItemTextNormalColor(getContext().getResources().getColor(R.color.timetimepicker_default_text_color))
                .setWheelItemTextSelectorColor(getContext().getResources().getColor(R.color.timepicker_toolbar_bg))
                .setWheelItemTextSize(12)
                .build();

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
            detailPopWindow = new DetailPopWindow(projectDetailFragment.getActivity());
            detailPopWindow.popRecyclerView.setAdapter(projectDetailRecycViewAdapter);
            //刷新指标选中颜色
            topRecycViewAdapter.getPosition(position);
            topRecycViewAdapter.notifyDataSetChanged();

            //展示数据popupwindow
            detailPopWindow.showAtLocation(mBinder.topRecyclerView, Gravity.START,0,0);

            PreferenceUtils.putInt(getContext(),"topPosition",position);
            requestSelectedPosition = position;
            PreferenceUtils.putString(getContext(),"tableName",data.get(position).getTableName());
        });


        RecyclerView bootomRecyclerView = mBinder.bootomRecyclerView;
        // 如果可以确定每个item的高度是固定的，设置这个选项可以提高性能
        bootomRecyclerView.setHasFixedSize(true);

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
        bootomRecyclerView.setLayoutManager(layoutManager1);

        bootomRecycViewAdapter.setDataList(mVals1);
        bootomRecyclerView.setAdapter(bootomRecycViewAdapter);
        //为点击标签设置点击事件.
        bootomRecycViewAdapter.setOnItemClickListener((view, position) -> {
            if (position==0){
                //展示数据popupwindow
                bottomPopWindow = new BottomPopWindow(projectDetailFragment.getActivity());
                bottomPopWindow.showAtLocation(mBinder.bootomRecyclerView, Gravity.BOTTOM,0,0);
                topPosition = PreferenceUtils.getInt(getContext(),"topPosition");
                leftPosition = PreferenceUtils.getInt(getContext(),"leftPosition");
                bottomPopWindow.ll_description.setVisibility(View.VISIBLE);
                bottomPopWindow.sl_tablelayout.setVisibility(View.GONE);
                String tvProjectName = getContext().getResources().getString(R.string.tv_project_name)+PreferenceUtils.getString(getContext(),"projectName");
                String tvDetectionIndicator = getContext().getResources().getString(R.string.tv_detection_indicator)+data.get(topPosition).getMonitorTypeName();
                String tvSurveysPointName = getContext().getResources().getString(R.string.tv_surveys_point_name)+data.get(topPosition).getSensorList()
                        .get(leftPosition).getMonitorPoint();
                String tvTerminalNumber = getContext().getResources().getString(R.string.tv_terminal_number)+data.get(topPosition).getSensorList()
                        .get(leftPosition).getSmuNumber();
                String tvSensorNumber = getContext().getResources().getString(R.string.tv_sensor_number)+data.get(topPosition).getSensorList()
                        .get(leftPosition).getSensorNumber();
                String tvSate = "";
                if (curvelData.size()>0){
                    int status = curvelData.get(leftPosition).getSensorStatus();

                    if (status==1){
                        tvSate = getContext().getResources().getString(R.string.tv_sate)+"正常";
                    }else if (status == 0){
                        tvSate = getContext().getResources().getString(R.string.tv_sate)+"不正常";
                    }
                }else {
                    tvSate = getContext().getResources().getString(R.string.tv_sate)+"未知";
                }
                bottomPopWindow.tv_sate.setText(tvSate);
                bottomPopWindow.tv_project_name.setText(tvProjectName);
                bottomPopWindow.tv_detection_indicator.setText(tvDetectionIndicator);
                bottomPopWindow.tv_surveys_point_name.setText(tvSurveysPointName);
                bottomPopWindow.tv_terminal_number.setText(tvTerminalNumber);
                bottomPopWindow.tv_sensor_number.setText(tvSensorNumber);

            }else if (position==1){
                if (curvelData.size()>0){
                    //展示数据popupwindow
                    bottomPopWindow = new BottomPopWindow(projectDetailFragment.getActivity());
                    bottomPopWindow.showAtLocation(mBinder.bootomRecyclerView, Gravity.BOTTOM,0,0);
                    bottomPopWindow.ll_description.setVisibility(View.GONE);
                    bottomPopWindow.sl_tablelayout.setVisibility(View.VISIBLE);

                    FontStyle.setDefaultTextSize(DensityUtils.sp2px(getContext(),15)); //设置全局字体大小

                    WindowManager wm = projectDetailFragment.getActivity().getWindowManager();
                    int screenWith = wm.getDefaultDisplay().getWidth();
                    bottomPopWindow.table.getConfig().setMinTableWidth(screenWith); //设置最小宽度 屏幕宽度
                    bottomPopWindow.table.getConfig().setShowTableTitle(false);
                    //生成数据
                    final List<UserInfo> testData = new ArrayList<>();
                    for(int i = curvelData.size()-1;i >=0; i--) {
                        UserInfo userData = new UserInfo(
                                monitorPoint,
                                curvelData.get(i).getPreviousTime(),
                                formatFloatNumber(curvelData.get(i).getTotalLaserChange()),
                                formatFloatNumber(curvelData.get(i).getCurrentLaserChange()),
                                formatFloatNumber(curvelData.get(i).getSpeedChange()),
                                curvelData.get(i).getSmuNumber(),
                                curvelData.get(i).getSmuChannel());
                        testData.add(userData);
                    }

                    final Column<String> nameColumn = new Column<>("测点名称", "name");
                    nameColumn.setAutoCount(true);
                    final Column<Integer> ageColumn = new Column<>("本次时间", "time");
                    ageColumn.setAutoCount(true);
                    ageColumn.setAutoCount(true);
                    final Column<String> column2 = new Column<>("累计变化量", "totalChange");
                    column2.setAutoCount(true);
                    final Column<Integer> column3 = new Column<>("单次变化量", "singleChange");
                    column3.setAutoCount(true);
                    final Column<Integer> column4 = new Column<>("变化速率", "speedChange");
                    column4.setAutoCount(true);
                    final Column<String> column5 = new Column<>("终端编号", "sunNum");
                    column5.setAutoCount(true);
                    final Column<Integer> column6 = new Column<>("采集器通道", "sunChancel");
                    column6.setAutoCount(true);

                    final TableData<UserInfo> tableData = new TableData<>
                            ("",testData,nameColumn,ageColumn,column2,column3,column4,column5,column6);
                    tableData.setShowCount(false);

                    bottomPopWindow.table.getConfig().setColumnTitleBackground(new BaseBackgroundFormat(getContext().getResources().getColor(R.color.windows_bg)));
                    bottomPopWindow.table.getConfig().setCountBackground(new BaseBackgroundFormat(getContext().getResources().getColor(R.color.windows_bg)));


                    FontStyle fontStyle = new FontStyle();
                    fontStyle.setTextColor(getContext().getResources().getColor(android.R.color.white));
                    bottomPopWindow.table.getConfig().setContentStyle(fontStyle);
                    bottomPopWindow.table.getConfig().setColumnTitleStyle(fontStyle);

                    bottomPopWindow.table.setTableData(tableData);
                    bottomPopWindow.table.getConfig().setContentBackground(
                            new BaseBackgroundFormat(getContext().getResources().getColor(R.color.theme_color)));
                    bottomPopWindow.table.getConfig().setColumnTitleBackground(
                            new BaseBackgroundFormat(getContext().getResources().getColor(R.color.theme_color))
                    );
                    bottomPopWindow.table.getConfig().setShowXSequence(false);
                    bottomPopWindow.table.getConfig().setShowYSequence(false);
                }else {
                    ToastUtils.showLongToast("所选项目指标下的测点没有数据!");
                }
            }
        });
        //左边弹出框list的点击事件
        projectDetailRecycViewAdapter.setOnItemClickListener((view, position) -> {
            topPosition = PreferenceUtils.getInt(getContext(),"topPosition");
            Map<String,String> map = new HashMap<>();
            map.put("tableName",data.get(topPosition).getTableName());
            map.put("sensorNumber",data.get(topPosition).getSensorList().get(position).getSensorNumber());
            map.put("smuNumber",data.get(topPosition).getSensorList().get(position).getSmuNumber());
            map.put("smuChannel",data.get(topPosition).getSensorList().get(position).getSmuChannel());
            map.put("beginTime",startText);
            map.put("endTime",endText);

            if ("".equals(startText)||"".equals(endText)){
                ToastUtils.showLongToast("请选择时间");
            }else {
                mControl.getProjectCurveDetail(ProjectDetailModel.this,map,2);
                PreferenceUtils.putString(getContext(),"sensorNumber",data.get(topPosition).getSensorList().get(position).getSensorNumber());
                PreferenceUtils.putString(getContext(),"smuNumber",data.get(topPosition).getSensorList().get(position).getSmuNumber());
                PreferenceUtils.putString(getContext(),"smuChannel",data.get(topPosition).getSensorList().get(position).getSmuChannel());
            }
            monitorPoint = data.get(topPosition).getSensorList().get(position).getMonitorPoint();
            PreferenceUtils.putInt(getContext(),"leftPosition",position);

            detailPopWindow.dismiss();
        });
    }

    //曲线信息
    private void curvelData(String[] xValue,ArrayList<CurveDetailEntity.DataBean> data1){
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
//        mChart.setBackgroundColor(Color.parseColor("#eeeeee"));
        mChart.setBackgroundColor(Color.parseColor("#192531"));
        // add data
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
//        l.setYOffset(11f);

        XAxis xAxis = mChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTH_SIDED);
        xAxis.setAxisMinimum(0f);
        xAxis.setGranularity(1f);
//        xAxis.setEnabled(false);
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
//                ToastView toastView = new ToastView(getContext(),curvelData.get((int)e.getX()).getPreviousTime(),
//                        "累计变化量(mm): "+formatFloatNumber(curvelData.get((int)e.getX()).getTotalLaserChange()),
//                        "单次变化量(mm): "+ formatFloatNumber(curvelData.get((int)e.getX()).getCurrentLaserChange()),
//                        "变化速率(mm/min): "+ formatFloatNumber(curvelData.get((int)e.getX()).getSpeedChange()));
                ToastUtils.showLongToast(
                        curvelData.get((int)e.getX()).getPreviousTime()+"\n"+
                                "累计变化量(mm): "+formatFloatNumber(curvelData.get((int)e.getX()).getTotalLaserChange())+"\n"
                                +"单次变化量(mm): "+ formatFloatNumber(curvelData.get((int)e.getX()).getCurrentLaserChange())+"\n"
                                +"变化速率(mm/min): "+ formatFloatNumber(curvelData.get((int)e.getX()).getSpeedChange()));
//                toastView.show(1000);
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
                    ArrayList<String> list= new ArrayList<>();
                    list.clear();
                    for (ProjectDetailEntity.DataBean datum : data) {
                        list.add(datum.getMonitorTypeName());
                    }
                    inData(list);
                    //默认时间是当前时间往前推两个小时
                    long time = System.currentTimeMillis();
                    long time1 = time-7200000;

                    String tableName;
                    String sensorNumber;
                    String smuNumber;
                    String smuChannel;
                    String start = PreferenceUtils.getString(getContext(),"startTime");
                    String end = PreferenceUtils.getString(getContext(),"endTime");
                    if (!"".equals(startText)&&!"".equals(endText)
                            &&start!=null&&end!=null){
                        startText = start;
                        endText = end;
                    }else {
                        startText = DateUtil.getDateToString(time1);
                        endText = DateUtil.getDateToString(time);
                    }

                    mBinder.tvChooseStartTime.setText(startText);
                    mBinder.tvChooseEndTime.setText(endText);
                    if (PreferenceUtils.getString(getContext(),"tableName")==null){
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

                    Map<String,String> map = new HashMap<>();
                    map.put("tableName",tableName);
                    map.put("sensorNumber",sensorNumber);
                    map.put("smuNumber",smuNumber);
                    map.put("smuChannel",smuChannel);
                    map.put("beginTime",startText);
                    map.put("endTime",endText);
                    mControl.getProjectCurveDetail(ProjectDetailModel.this,map,2);

                    monitorPoint = data.get(0).getSensorList().get(0).getMonitorPoint();
                }
                break;
            case 2:
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
                    curvelData(xValue1,curvelData);
                    //记录用户选择的时间段
                    PreferenceUtils.putString(getContext(),"startTime",startText);
                    PreferenceUtils.putString(getContext(),"endTime",endText);
                    topRecycViewAdapter.getProjectName(monitorPoint);
                    PreferenceUtils.putInt(getContext(),"requestSelectedPosition",requestSelectedPosition);
                }else {
                    mBinder.llNoCurvel.setVisibility(View.VISIBLE);
                    mBinder.chart1.setVisibility(View.GONE);
                }

                break;
        }

    }

    @Override
    public void onError(String errorMsg, int tag) {

    }

    @Override
    public void onItemClick(View view, int position) {

    }

    @Override
    public void initListener() {
        mBinder.tvChooseStartTime.setOnClickListener(this);
        mBinder.tvChooseEndTime.setOnClickListener(this);
   }

    @Override
    public void ChooseTime() {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_choose_start_time:
                endOrStart = true;
                mDialogAll.show(projectDetailFragment.getActivity().getSupportFragmentManager(), "all");
                break;
            case R.id.tv_choose_end_time:
                endOrStart = false;
                mDialogAll.show(projectDetailFragment.getActivity().getSupportFragmentManager(), "all");
                break;
            default:
                break;
        }
    }

    @Override
    public void onDateSet(TimePickerDialog timePickerDialog, long millseconds) {
        if (endOrStart){
            startText = getDateToString(millseconds);
            mBinder.tvChooseStartTime.setText(startText);
        }else {
            endText = getDateToString(millseconds);
            mBinder.tvChooseEndTime.setText(endText);
        }

    }

    public String getDateToString(long time) {
        Date d = new Date(time);
        return sf.format(d);
    }

    @Override
    public void onResume() {
        requestData(false);
    }

    public  void getPosition(int i){
        this.defaultPosition = i;
    }

    public  void requestData(boolean isReduction){
        mBinder.tvProject.setText(PreferenceUtils.getString(getContext(),"projectName"));
        if (isReduction){
            if (topRecycViewAdapter!=null){
                //刷新指标选中颜色
                topRecycViewAdapter.getPosition(0);
                topRecycViewAdapter.notifyDataSetChanged();
            }
        }
        Map<String,String> map = new HashMap<>();
        map.put("projectId",String.valueOf(PreferenceUtils.getInt(BaseApplication.getContext(),"projectId",0)));
        mControl.getProjectDetail(this,map,1);
    }
    //曲线图设值
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

        if (mChart.getData() != null &&
                mChart.getData().getDataSetCount() > 0) {
            set1 = (LineDataSet) mChart.getData().getDataSetByIndex(0);
            set2 = (LineDataSet) mChart.getData().getDataSetByIndex(1);
            set3 = (LineDataSet) mChart.getData().getDataSetByIndex(2);
            set1.setValues(yVals1);
            set2.setValues(yVals2);
            set3.setValues(yVals3);
            mChart.getData().notifyDataChanged();


            mChart.notifyDataSetChanged();
        } else {
            // create a dataset and give it a type
            set1 = new LineDataSet(yVals1, "累计变化量(mm)");

            set1.setAxisDependency(YAxis.AxisDependency.LEFT);
            set1.setColor(Color.parseColor("#00FFFF"));
//            set1.setCircleColor(Color.WHITE);
            set1.setLineWidth(2f);
//            set1.setCircleRadius(3f);
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
//            set2.setCircleColor(Color.WHITE);
            set2.setValueTextColor(Color.WHITE);
            set2.setLineWidth(2f);
//            set2.setCircleRadius(3f);
            set2.setFillAlpha(65);
            set2.setFillColor(Color.RED);
            set2.setDrawCircleHole(false);
            set2.setDrawCircles(false);
            set2.setHighLightColor(Color.rgb(90,153,255));
            //set2.setFillFormatter(new MyFillFormatter(900f));
            //设置小数点后面位数
            set2.setValueFormatter((value, entry, dataSetIndex, viewPortHandler) -> "   "+value);

            set3 = new LineDataSet(yVals3, "变化速率(mm/min)");
            set3.setAxisDependency(YAxis.AxisDependency.LEFT);
            set3.setColor(Color.parseColor("#2096F2"));
//            set3.setCircleColor(Color.WHITE);
            set3.setValueTextColor(Color.WHITE);
            set3.setLineWidth(2f);
            set3.setDrawCircles(false);
//            set3.setCircleRadius(3f);
            set3.setFillAlpha(65);
            set3.setFillColor(ColorTemplate.colorWithAlpha(Color.YELLOW, 200));
            set3.setDrawCircleHole(false);
            set3.setHighLightColor(Color.rgb(90,153,255));
            //设置小数点后面位数
            set3.setValueFormatter((value, entry, dataSetIndex, viewPortHandler) -> "    "+value);
            // create a data object with the datasets
            LineData data = new LineData(set1, set2, set3);
            //不显示圆点上的数值
            data.setDrawValues(false);

//            data.setValueTextColor(Color.WHITE);
//            data.setValueTextSize(9f);
            //去掉科学计数法保留四位小数
            data.setValueFormatter(new DefaultValueFormatter(3));
            // set data
            mChart.setData(data);

        }
    }
    /**
     * 数据变成科学计数法显示。用此方法可以使其正常显示。
     * @param value
     * @return Sting
     */
    private   String formatFloatNumber(double value) {
        DecimalFormat df = new DecimalFormat("0.000");
        return df.format(value);
    }

}
