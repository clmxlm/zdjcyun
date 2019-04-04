package com.zdjc.zdjcyun.mvp.viewmodel.impl;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import com.blankj.utilcode.utils.ToastUtils;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.zdjc.zdjcyun.R;
import com.zdjc.zdjcyun.app.Constant;
import com.zdjc.zdjcyun.base.BaseModel;
import com.zdjc.zdjcyun.charting.charts.LineChart;
import com.zdjc.zdjcyun.charting.components.AxisBase;
import com.zdjc.zdjcyun.charting.components.Legend;
import com.zdjc.zdjcyun.charting.components.XAxis;
import com.zdjc.zdjcyun.charting.components.YAxis;
import com.zdjc.zdjcyun.charting.data.Entry;
import com.zdjc.zdjcyun.charting.data.LineData;
import com.zdjc.zdjcyun.charting.data.LineDataSet;
import com.zdjc.zdjcyun.charting.formatter.DefaultValueFormatter;
import com.zdjc.zdjcyun.charting.formatter.IAxisValueFormatter;
import com.zdjc.zdjcyun.charting.highlight.Highlight;
import com.zdjc.zdjcyun.charting.interfaces.datasets.ILineDataSet;
import com.zdjc.zdjcyun.charting.listener.OnChartValueSelectedListener;
import com.zdjc.zdjcyun.databinding.FragmentDataMonitoringBinding;
import com.zdjc.zdjcyun.mvp.entity.ComparisonDataEntity;
import com.zdjc.zdjcyun.mvp.entity.DataMonitoringImagesEntity;
import com.zdjc.zdjcyun.mvp.entity.DeepDataEntity;
import com.zdjc.zdjcyun.mvp.entity.GPSNormEntity;
import com.zdjc.zdjcyun.mvp.entity.MapTypeEntity;
import com.zdjc.zdjcyun.mvp.entity.MonitorUnitEntity;
import com.zdjc.zdjcyun.mvp.entity.NormDeepEntity;
import com.zdjc.zdjcyun.mvp.entity.SensorDataEntity;
import com.zdjc.zdjcyun.mvp.entity.TerminalAndSensorEntity;
import com.zdjc.zdjcyun.mvp.presenter.impl.DataMonitoringPresenterImpl;
import com.zdjc.zdjcyun.mvp.ui.activities.DataComparisonActivity;
import com.zdjc.zdjcyun.mvp.ui.adapter.ImgDotsAdapter;
import com.zdjc.zdjcyun.mvp.viewmodel.IDataMonitoringModel;
import com.zdjc.zdjcyun.util.ArithUtils;
import com.zdjc.zdjcyun.util.PreferenceUtils;
import com.zdjc.zdjcyun.widget.LineChartMarkView;
import com.zdjc.zdjcyun.widget.LineChartsMarkView;
import com.zdjc.zdjcyun.widget.img_dots.ImgSimple;
import com.zdjc.zdjcyun.widget.img_dots.PointSimple;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import static com.zdjc.zdjcyun.util.DateUtil.getDateToString;


public class DataMonitoringModel extends BaseModel<FragmentDataMonitoringBinding, DataMonitoringPresenterImpl> implements IDataMonitoringModel, ViewPager.OnPageChangeListener {


    private String id;
    private ArrayList<DataMonitoringImagesEntity.DataBean> dataBeans;
    private String[] stringArrayTime = {"全部", "一周", "一月", "一季"};
    private String[] stringArrayMonitor = {"累计变化量", "单次变化量", "变化速率"};
    private String[] stringArraySLYLMonitor = {"单次变化量", "变化速率"};
    private String[] stringArrayGTMonitor = {"高度", "宽度"};
    private String[] stringArrayKSMonitor = {"当前值", "单次变化量"};
    private String[] stringArraySLMonitor = {"累计变化量", "单次变化量", "变化速率"};
    private String[] stringArrayYLMonitor = {"单次变化量", "变化速率"};
    private long startTimeL;
    private long endTimeL;
    private String startTimeS;
    private String endTimeS;
    private String[] xValue1 = new String[]{};
    private ArrayList<String> xTimeData= new ArrayList<>(Arrays.asList(xValue1));
    private ArrayList<SensorDataEntity.DataBean.CommonDataVOsBean> commonDataVOsBean = new ArrayList<>();
    private ArrayList<GPSNormEntity.DataBean.CommonDataVOsBean> gpsCommonDataVOsBean = new ArrayList<>();
    private LineChart mChart;
    private int monitorType;
    private String monitorPointNumber;
    private String monitorTypeName;
    private ArrayList<MonitorUnitEntity.DataBean> monitorUnitList = new ArrayList<>();
    private int mapType=-1;
    private ArrayList<String> totalGPSList = new ArrayList<>();
    private ArrayList<String> measuredDeepList = new ArrayList<>();
    private ArrayList<String> measuredDeepTimeList = new ArrayList<>();
    private boolean isOnclick = false;

    @Override
    public void onCreate() {
        totalGPSList.clear();
        measuredDeepList.clear();
        id = PreferenceUtils.getInt(getContext(), "sectorId") + "";
        startTimeL = System.currentTimeMillis();
        endTimeL = startTimeL-7200000*12*7;
        mBinder.btnStartTime.setText(getDateToString(endTimeL));
        mBinder.btnEndTime.setText(getDateToString(startTimeL));
        PreferenceUtils.putString(getContext(),"AmountOfChangeName","累计变化量");
        intView();
        totalGPSList.add("累计变化量X");
        totalGPSList.add("累计变化量Y");
        totalGPSList.add("累计变化量Z");
        measuredDeepList.add("X通道");
        measuredDeepList.add("Y通道");
        measuredDeepTimeList.add("深度X通道");
        measuredDeepTimeList.add("深度Y通道");
        /**
         * 拿到存储所有指标单位的集合（登录的时候存储的最新的数据）
         */
        String data = PreferenceUtils.getString(getContext(), "monitorUnit");
        Gson gson = new Gson();
        Type listType = new TypeToken<ArrayList<MonitorUnitEntity.DataBean>>() {
        }.getType();
        monitorUnitList = gson.fromJson(data, listType);
    }

    private void intView() {
        mBinder.segmentTabLayoutTime.setTabData(stringArrayTime);
        mBinder.segmentTabLayoutTime.setCurrentTab(1);
        mBinder.segmentTabLayoutMonitor.setTabData(stringArrayMonitor);
        request();
        mBinder.btnData.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), DataComparisonActivity.class);
            getContext().startActivity(intent);
        });

        mBinder.segmentTabLayoutMonitor.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                PreferenceUtils.putString(getContext(),"AmountOfChangeName",stringArrayMonitor[position]);
                if (xValue1.length==0){
                    ToastUtils.showShortToast("请先选择对比数据!");
                }else {
                    curvelData(xValue1,commonDataVOsBean);
                }
            }
            @Override
            public void onTabReselect(int position) {

            }
        });

        mBinder.segmentTabLayoutTime.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                if (xValue1.length==0){
                    ToastUtils.showShortToast("请先选择对比数据!");
                }else {
                    long oneDayTime=7200000*12;
                    if ("一周".equals(stringArrayTime[position])){
                        long weekTime=oneDayTime*7;
                        startTimeL = System.currentTimeMillis();
                        endTimeL = startTimeL-weekTime;
                    }else if ("一月".equals(stringArrayTime[position])){
                        long monthTime=oneDayTime*30;
                        startTimeL = System.currentTimeMillis();
                        endTimeL = startTimeL-monthTime;
                    }else if ("一季".equals(stringArrayTime[position])){
                        long reasonTime=oneDayTime*30*3;
                        startTimeL = System.currentTimeMillis();
                        endTimeL = startTimeL-reasonTime;
                    }
                    mBinder.btnStartTime.setText(getDateToString(endTimeL));
                    mBinder.btnEndTime.setText(getDateToString(startTimeL));
                    startTimeS = getDateToString(endTimeL);
                    endTimeS = getDateToString(startTimeL);
                    querySensorData();
                }
            }

            @Override
            public void onTabReselect(int position) {
            }
        });

        mBinder.btnStartTime.setOnClickListener(v -> showDialogTwo());

        mBinder.btnEndTime.setOnClickListener(v -> showDialogTwo());
    }

    private void querySensorData() {
        monitorType = PreferenceUtils.getInt(getContext(),"monitorType");
        monitorTypeName = PreferenceUtils.getString(getContext(),"monitorTypeName");
        monitorPointNumber = PreferenceUtils.getString(getContext(),"monitorPointNumber");


        Map<String, String> map = new HashMap<>(0);
        map.put("sectorId", id);
        map.put("monitorType", monitorType+"");
        map.put("monitorPointNumber", monitorPointNumber);
        map.put("beginTime", startTimeS);
        map.put("endTime", endTimeS);
        map.put("dateType", 1+"");
        if (mapType==SENSOR_TYPE){
            mControl.getQuerySensorData(DataMonitoringModel.this, map, 2);
            mBinder.segmentTabLayoutMonitor.setVisibility(View.VISIBLE);
            mBinder.segmentTabLayoutTime.setVisibility(View.VISIBLE);
            mBinder.chart2.setVisibility(View.GONE);
        }else if (mapType==SENSOR_TYPE_TWO){
            mControl.getQueryNormTwoSensorData(DataMonitoringModel.this, map, 2);
            mBinder.segmentTabLayoutMonitor.setVisibility(View.GONE);
            mBinder.segmentTabLayoutTime.setVisibility(View.GONE);
            mBinder.chart2.setVisibility(View.GONE);
        }else if (mapType==SENSOR_TYPE_THREE){
            mControl.getQueryNormThreeSensorData(DataMonitoringModel.this, map, 2);
            mBinder.segmentTabLayoutMonitor.setVisibility(View.GONE);
            mBinder.segmentTabLayoutTime.setVisibility(View.GONE);
            mBinder.chart2.setVisibility(View.VISIBLE);
        }
    }


    @Override
    public void onBeforeRequest(int tag) {
        UI.showWaitDialog();
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onSuccess(Object bean, int tag) {
        mBinder.chart1.setVisibility(View.VISIBLE);
        mBinder.tvNoData.setVisibility(View.GONE);
        switch (tag) {
            case 1:
                dataBeans = (ArrayList<DataMonitoringImagesEntity.DataBean>) bean;
                ArrayList<ImgSimple> imgSimples = new ArrayList<>();
                for (DataMonitoringImagesEntity.DataBean dataBean : dataBeans) {
                    ImgSimple imgSimple = new ImgSimple();
                    imgSimple.pointSimples=new ArrayList<>();
                    imgSimple.scale = (float) ArithUtils.div(dataBean.getImageWidth(), dataBean.getImageHeight(), 3);
                    imgSimple.url = Constant.IMAGE_URL + dataBean.getImageUrl();
                    for (DataMonitoringImagesEntity.DataBean.MonitorPointsBean monitorPointsBean : dataBean.getMonitorPoints()) {
                        PointSimple pointSimple = new PointSimple();
                        int widthPx=Integer.valueOf(monitorPointsBean.getPicX().replace("px", ""));
                        int heightPx=Integer.valueOf(monitorPointsBean.getPicY().replace("px", ""));
                        pointSimple.width_scale=ArithUtils.div(dataBean.getImageWidth(),widthPx,3);
                        pointSimple.height_scale=ArithUtils.div(dataBean.getImageHeight(),heightPx,3);
                        pointSimple.status = monitorPointsBean.getStatus();
                        imgSimple.pointSimples.add(pointSimple);
                    }
                    imgSimples.add(imgSimple);
                }
                TextView imageNumber = mBinder.imageNumber;



                ImgDotsAdapter samplePagerAdapter = new ImgDotsAdapter(v -> {
                    PreferenceUtils.putInt(getContext(),"monitorType",dataBeans.get(0).getMonitorPoints().get((Integer) v.getTag()).getMonitorType());
                    PreferenceUtils.putString(getContext(),"monitorTypeName",dataBeans.get(0).getMonitorPoints().get((Integer) v.getTag()).getMonitorTypeName());
                    PreferenceUtils.putString(getContext(),"monitorPointNumber",dataBeans.get(0).getMonitorPoints().get((Integer) v.getTag()).getMonitorPointNumber());
                    /**
                     * 根据请求回来单位信息点击来匹配该测点的数据单位
                     */
                    for (int i = 0; i < monitorUnitList.size(); i++) {
                        if (dataBeans.get(0).getMonitorPoints().get((Integer) v.getTag()).getMonitorType() == monitorUnitList.get(i).getMonitorType()){
                            String otherUnit = monitorUnitList.get(i).getUnitA();
                            PreferenceUtils.putString(getContext(),"otherUnit",otherUnit);
                            String rateUnit = monitorUnitList.get(i).getUnitC();
                            PreferenceUtils.putString(getContext(),"rateUnit",rateUnit);
                        }
                    }
                    mBinder.segmentTabLayoutMonitor.setCurrentTab(0);
                    PreferenceUtils.putString(getContext(),"AmountOfChangeName","累计变化量");
                    isOnclick = true;
                    /**
                     * 请求不同指标下的不同数据对象tag
                     */
                    Map<String, String> mapType = new HashMap<>(0);
                    mapType.put("monitorType", dataBeans.get(0).getMonitorPoints().get((Integer) v.getTag()).getMonitorType()+"");
                    mControl.getQueryMapType(DataMonitoringModel.this, mapType, 4);

                }, (Activity) UI.getConText(), imgSimples);

                /**
                 * 布点图布局设值
                 */
                mBinder.hackyViewPager.setAdapter(samplePagerAdapter);
                mBinder.hackyViewPager.addOnPageChangeListener(this);
                mBinder.hackyViewPager.setCurrentItem(0);
                mBinder.imageNumber.setText(dataBeans.get(0).getImageDescription());

                break;
            case 2:
                if (mapType==SENSOR_TYPE){
                    SensorDataEntity.DataBean sensorDataEntity = (SensorDataEntity.DataBean) bean;
                    xTimeData.clear();
                    commonDataVOsBean = sensorDataEntity.getCommonDataVOs();
                    for (SensorDataEntity.DataBean.CommonDataVOsBean commonDataVOsBean : sensorDataEntity.getCommonDataVOs()) {
                        xTimeData.add(commonDataVOsBean.getCreateDate());
                    }
                    xValue1 = xTimeData.toArray(new String[xTimeData.size()]);
                    curvelData(xValue1, sensorDataEntity.getCommonDataVOs());
                }else if (mapType==SENSOR_TYPE_TWO){
                    GPSNormEntity.DataBean gpsNormEntity = (GPSNormEntity.DataBean) bean;
                    xTimeData.clear();
                    gpsCommonDataVOsBean = gpsNormEntity.getCommonDataVOs();
                    for (GPSNormEntity.DataBean.CommonDataVOsBean commonDataVOsBean : gpsNormEntity.getCommonDataVOs()) {
                        xTimeData.add(commonDataVOsBean.getCreateDate());
                    }
                    xValue1 = xTimeData.toArray(new String[xTimeData.size()]);
                    curvelGPSData(xValue1, gpsCommonDataVOsBean);
                }else if (mapType==SENSOR_TYPE_THREE){
                    DeepDataEntity.DataBean deepDataEntity = (DeepDataEntity.DataBean) bean;
                    xTimeData.clear();
                    ArrayList<DeepDataEntity.DataBean.DeepDatasBean> deepDatas  = deepDataEntity.getDeepDatas();
                    for (DeepDataEntity.DataBean.DeepDatasBean deepDatasBean: deepDatas) {
                        xTimeData.add(String.valueOf(deepDatasBean.getSensorDeep()));
                    }
                    xValue1 = xTimeData.toArray(new String[xTimeData.size()]);
                    curvelDeepData(xValue1, deepDataEntity);

                    Map<String, String> map = new HashMap<>(0);
                    map.put("sectorId", id);
                    map.put("monitorType", monitorType+"");
                    map.put("monitorPointNumber", monitorPointNumber);
                    map.put("sensorNumber", deepDataEntity.getSensorNumbers().get(0));
                    map.put("beginTime", startTimeS);
                    map.put("endTime", endTimeS);
                    mControl.getQueryDeepData(DataMonitoringModel.this, map, 5);
                }
                break;
            case 3:
                TerminalAndSensorEntity.DataBean data = (TerminalAndSensorEntity.DataBean) bean;

                mBinder.tvMonitorName.setText(data.getMonitorPointNumber());
                mBinder.tvSensorNumber.setText(data.getSensorNumber());
                mBinder.tvTerminalChanel.setText(data.getTerminalChannel());
                mBinder.tvTerminalNumber.setText(data.getTerminalNumber());
                mBinder.tvMonitorTypeName.setText(data.getMonitorTypeName());

                mBinder.tvSelected.setText("已选择测点");
                mBinder.tvSelected.setTextColor(getContext().getResources().getColor(R.color.black));
                mBinder.tvSelectedMonitor.setText(monitorTypeName+"-"+monitorPointNumber);
                break;

            case 4:
                mapType = (int) bean;
                PreferenceUtils.putInt(getContext(),"mapType",mapType);
                /**
                 * 请求曲线图数据
                 */
                startTimeS = getDateToString(endTimeL);
                endTimeS = getDateToString(startTimeL);
                querySensorData();

                /**
                 * 请求曲线图下面表格数据数据
                 */
                Map<String, String> map = new HashMap<>(0);
                map.put("sectorId", id);
                map.put("monitorType", monitorType+"");
                map.put("monitorPointNumber", monitorPointNumber);
                mControl.getQueryTerminalAndSensor(DataMonitoringModel.this, map, 3);
                break;
            case 5:
                NormDeepEntity.DataBean normDeepEntity = (NormDeepEntity.DataBean) bean;
                xTimeData.clear();
                ArrayList<NormDeepEntity.DataBean.MeasuredDataXBean> measuredDataX  = normDeepEntity.getMeasuredDataX();
                for (NormDeepEntity.DataBean.MeasuredDataXBean normDatasBean: measuredDataX) {
                    xTimeData.add(normDatasBean.getCreateDate());
                }
                xValue1 = xTimeData.toArray(new String[xTimeData.size()]);
                curvelDeepimeTData(xValue1, normDeepEntity);
                break;
            default:
                break;
        }
    }

    @Override
    public void onError(String errorMsg, int code, int tag) {
        ToastUtils.showShortToast(errorMsg);
        mBinder.chart1.setVisibility(View.GONE);
        mBinder.chart2.setVisibility(View.GONE);
        mBinder.tvNoData.setVisibility(View.VISIBLE);
    }

    private void request() {
        Map<String, String> map = new HashMap<>(0);
        map.put("sectorId", id);
        map.put("imageType", 3 + "");
        mControl.getQueryImagesMonitorPoint(DataMonitoringModel.this, map, 1);
    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
//        imageNumber.setText(String.format("%d/%d", position + 1, imageUrls.size()));
        mBinder.imageNumber.setText(dataBeans.get(position).getImageDescription());
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    /**
     * 初始化LineChart
     */
    private void curvelData(String[] xValue,ArrayList<SensorDataEntity.DataBean.CommonDataVOsBean> yData){

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
        mChart.setBackgroundColor(Color.parseColor("#ffffff"));
        // add data
        setData(yData);
        mChart.animateX(2500);
        // get the legend (only possible after setting data)
        Legend l = mChart.getLegend();
        // modify the legend ...
        l.setForm(Legend.LegendForm.LINE);
        l.setTypeface(mTfLight);
        l.setTextSize(11f);
        l.setTextColor(Color.BLACK);
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT);
        l.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        l.setDrawInside(false);

        XAxis xAxis = mChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTH_SIDED);
        xAxis.setAxisMinimum(0f);
        xAxis.setGranularity(1f);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setLabelCount(3);
        xAxis.setTextColor(Color.BLACK);
        xAxis.setDrawGridLines(false);
        if (xValue.length>0){
            xAxis.setValueFormatter((value, axis) -> xValue[(int) value % xValue.length]);
        }
        YAxis yAxis = mChart.getAxisLeft();
        yAxis.setDrawGridLines(false);
        yAxis.setTextColor(Color.BLACK);

        yAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                if ("变化速率".equals(PreferenceUtils.getString(getContext(),"AmountOfChangeName"))){
                    return value+"("+PreferenceUtils.getString(getContext(),"rateUnit")+")";
                }else {
                    return value+"("+PreferenceUtils.getString(getContext(),"otherUnit")+")";
                }
            }
        });

        YAxis yAxis1 = mChart.getAxisRight();
        yAxis1.setDrawGridLines(false);
        yAxis1.setTextColor(Color.BLACK);
        yAxis1.setEnabled(false);
        yAxis1.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                if ("变化速率".equals(PreferenceUtils.getString(getContext(),"AmountOfChangeName"))){
                    return value+"("+PreferenceUtils.getString(getContext(),"rateUnit")+")";
                }else {
                    return value+"("+PreferenceUtils.getString(getContext(),"otherUnit")+")";
                }
            }
        });

        mChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, Highlight h) {
                if ("累计变化量".equals(PreferenceUtils.getString(getContext(),"AmountOfChangeName"))){
                    LineChartMarkView mv = new LineChartMarkView(getContext(), xAxis.getValueFormatter(),"累计变化量");
                    mv.setChartView(mChart);
                    mChart.setMarker(mv);
                    mChart.invalidate();
                }else if (("单次变化量".equals(PreferenceUtils.getString(getContext(),"AmountOfChangeName")))){
                    LineChartMarkView mv = new LineChartMarkView(getContext(), xAxis.getValueFormatter(),"单次变化量");
                    mv.setChartView(mChart);
                    mChart.setMarker(mv);
                    mChart.invalidate();
                }else if (("变化速率".equals(PreferenceUtils.getString(getContext(),"AmountOfChangeName")))){
                    LineChartMarkView mv = new LineChartMarkView(getContext(), xAxis.getValueFormatter(),"变化速率");
                    mv.setChartView(mChart);
                    mChart.setMarker(mv);
                    mChart.invalidate();
                }
            }
            @Override
            public void onNothingSelected() {

            }
        });
    }

    /**
     * 初始化GPS位移LineChart
     */
    private void curvelGPSData(String[] xValue,ArrayList<GPSNormEntity.DataBean.CommonDataVOsBean> yData){

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
        mChart.setBackgroundColor(Color.parseColor("#ffffff"));
        // add data
        showLineChart(yData,totalGPSList);
        mChart.animateX(2500);
        // get the legend (only possible after setting data)
        Legend l = mChart.getLegend();
        // modify the legend ...
        l.setForm(Legend.LegendForm.LINE);
        l.setTypeface(mTfLight);
        l.setTextSize(11f);
        l.setTextColor(Color.BLACK);
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT);
        l.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        l.setDrawInside(false);

        XAxis xAxis = mChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTH_SIDED);
        xAxis.setAxisMinimum(0f);
        xAxis.setGranularity(1f);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setLabelCount(3);
        xAxis.setTextColor(Color.BLACK);
        xAxis.setDrawGridLines(false);
        if (xValue.length>0){
            xAxis.setValueFormatter((value, axis) -> xValue[(int) value % xValue.length]);
        }
        YAxis yAxis = mChart.getAxisLeft();
        yAxis.setDrawGridLines(false);
        yAxis.setTextColor(Color.BLACK);

        yAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                if ("变化速率".equals(PreferenceUtils.getString(getContext(),"AmountOfChangeName"))){
                    return value+"("+PreferenceUtils.getString(getContext(),"rateUnit")+")";
                }else {
                    return value+"("+PreferenceUtils.getString(getContext(),"otherUnit")+")";
                }
            }
        });

        YAxis yAxis1 = mChart.getAxisRight();
        yAxis1.setDrawGridLines(false);
        yAxis1.setTextColor(Color.BLACK);
        yAxis1.setEnabled(false);
        yAxis1.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                if ("变化速率".equals(PreferenceUtils.getString(getContext(),"AmountOfChangeName"))){
                    return value+"("+PreferenceUtils.getString(getContext(),"rateUnit")+")";
                }else {
                    return value+"("+PreferenceUtils.getString(getContext(),"otherUnit")+")";
                }
            }
        });

        mChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, Highlight h) {
                LineChartsMarkView mv = new LineChartsMarkView(getContext(), xAxis.getValueFormatter());
                mv.setChartView(mChart);
                mChart.setMarker(mv);
                mChart.invalidate();
            }
            @Override
            public void onNothingSelected() {

            }
        });
    }

    /**
     * 初始化深部位移深度LineChart
     */
    private void curvelDeepData(String[] xValue,DeepDataEntity.DataBean deepDataEntity){

        // no description text
        mBinder.chart2.getDescription().setEnabled(false);
        // enable touch gestures
        mBinder.chart2.setTouchEnabled(true);
        mBinder.chart2.setDragDecelerationFrictionCoef(0.9f);
        // enable scaling and dragging
        mBinder.chart2.setDragEnabled(true);
        mBinder.chart2.setScaleEnabled(true);
        mBinder.chart2.setDrawGridBackground(false);
        mBinder.chart2.setHighlightPerDragEnabled(true);
        mBinder.chart2.setDrawBorders(false);
        // if disabled, scaling can be done on x- and y-axis separately
        mBinder.chart2.setPinchZoom(true);
        // set an alternative background color
        mBinder.chart2.setBackgroundColor(Color.parseColor("#ffffff"));
        // add data
        showLineDeepChart(deepDataEntity,measuredDeepList);
        mBinder.chart2.animateX(2500);
        // get the legend (only possible after setting data)
        Legend l = mBinder.chart2.getLegend();
        // modify the legend ...
        l.setForm(Legend.LegendForm.LINE);
        l.setTypeface(mTfLight);
        l.setTextSize(11f);
        l.setTextColor(Color.BLACK);
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT);
        l.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        l.setDrawInside(false);

        XAxis xAxis = mBinder.chart2.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTH_SIDED);
        xAxis.setAxisMinimum(0f);
        xAxis.setGranularity(1f);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setLabelCount(3);
        xAxis.setTextColor(Color.BLACK);
        xAxis.setDrawGridLines(false);
        if (xValue.length>0){
            xAxis.setValueFormatter((value, axis) -> xValue[(int) value % xValue.length]);
        }
        YAxis yAxis = mBinder.chart2.getAxisLeft();
        yAxis.setDrawGridLines(false);
        yAxis.setTextColor(Color.BLACK);

        yAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                if ("变化速率".equals(PreferenceUtils.getString(getContext(),"AmountOfChangeName"))){
                    return value+"("+PreferenceUtils.getString(getContext(),"rateUnit")+")";
                }else {
                    return value+"("+PreferenceUtils.getString(getContext(),"otherUnit")+")";
                }
            }
        });

        YAxis yAxis1 = mBinder.chart2.getAxisRight();
        yAxis1.setDrawGridLines(false);
        yAxis1.setTextColor(Color.BLACK);
        yAxis1.setEnabled(false);
        yAxis1.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                if ("变化速率".equals(PreferenceUtils.getString(getContext(),"AmountOfChangeName"))){
                    return value+"("+PreferenceUtils.getString(getContext(),"rateUnit")+")";
                }else {
                    return value+"("+PreferenceUtils.getString(getContext(),"otherUnit")+")";
                }
            }
        });

        mBinder.chart2.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, Highlight h) {
                LineChartsMarkView mv = new LineChartsMarkView(getContext(), xAxis.getValueFormatter());
                mv.setChartView(mBinder.chart2);
                mv.getTitleName(10);
                mBinder.chart2.setMarker(mv);
                mBinder.chart2.invalidate();

            }
            @Override
            public void onNothingSelected() {

            }
        });
    }

    /**
     * 初始化深部位移时间LineChart
     */
    private void curvelDeepimeTData(String[] xValue,NormDeepEntity.DataBean normDeepEntity){

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
        mChart.setBackgroundColor(Color.parseColor("#ffffff"));
        // add data
        showLineDeepTChart(normDeepEntity,measuredDeepTimeList);
        mChart.animateX(2500);
        // get the legend (only possible after setting data)
        Legend l = mChart.getLegend();
        // modify the legend ...
        l.setForm(Legend.LegendForm.LINE);
        l.setTypeface(mTfLight);
        l.setTextSize(11f);
        l.setTextColor(Color.BLACK);
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT);
        l.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        l.setDrawInside(false);

        XAxis xAxis = mChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTH_SIDED);
        xAxis.setAxisMinimum(0f);
        xAxis.setGranularity(1f);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setLabelCount(3);
        xAxis.setTextColor(Color.BLACK);
        xAxis.setDrawGridLines(false);
        if (xValue.length>0){
            xAxis.setValueFormatter((value, axis) -> xValue[(int) value % xValue.length]);
        }
        YAxis yAxis = mChart.getAxisLeft();
        yAxis.setDrawGridLines(false);
        yAxis.setTextColor(Color.BLACK);

        yAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                if ("变化速率".equals(PreferenceUtils.getString(getContext(),"AmountOfChangeName"))){
                    return value+"("+PreferenceUtils.getString(getContext(),"rateUnit")+")";
                }else {
                    return value+"("+PreferenceUtils.getString(getContext(),"otherUnit")+")";
                }
            }
        });

        YAxis yAxis1 = mChart.getAxisRight();
        yAxis1.setDrawGridLines(false);
        yAxis1.setTextColor(Color.BLACK);
        yAxis1.setEnabled(false);
        yAxis1.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                if ("变化速率".equals(PreferenceUtils.getString(getContext(),"AmountOfChangeName"))){
                    return value+"("+PreferenceUtils.getString(getContext(),"rateUnit")+")";
                }else {
                    return value+"("+PreferenceUtils.getString(getContext(),"otherUnit")+")";
                }
            }
        });

        mChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, Highlight h) {
                LineChartsMarkView mv = new LineChartsMarkView(getContext(), xAxis.getValueFormatter());
                mv.setChartView(mChart);
                mChart.setMarker(mv);
                mChart.invalidate();
            }
            @Override
            public void onNothingSelected() {

            }
        });
    }

    /**
     * 统一曲线图设值
     */
    private void setData(ArrayList<SensorDataEntity.DataBean.CommonDataVOsBean> data1) {

        ArrayList<Entry> yVals1 = new ArrayList<>();
        LineDataSet set1 = null;
        if ("累计变化量".equals(PreferenceUtils.getString(getContext(),"AmountOfChangeName"))){
            for (int i = 0; i < data1.size(); i++) {
                yVals1.add(new Entry(i, (float) data1.get(i).getTotalChange()));
            }
            set1 = new LineDataSet(yVals1, "累计变化量(mm)");
        }else if (("单次变化量".equals(PreferenceUtils.getString(getContext(),"AmountOfChangeName")))){
            for (int i = 0; i < data1.size(); i++) {
                yVals1.add(new Entry(i, (float) data1.get(i).getSingleChange()));
            }
            set1 = new LineDataSet(yVals1, "单次变化量(mm)");
        }else if (("变化速率".equals(PreferenceUtils.getString(getContext(),"AmountOfChangeName")))){
            for (int i = 0; i < data1.size(); i++) {
                yVals1.add(new Entry(i, (float) data1.get(i).getSpeedChange()));
            }
            set1 = new LineDataSet(yVals1, "变化速率(mm)");
        }
        //随机颜色
        Random myRandom = new Random();
        int ranColor = 0xff000000 | myRandom.nextInt(0x00ffffff);

        set1.setAxisDependency(YAxis.AxisDependency.LEFT);
        set1.setColor(ranColor);
        set1.setLineWidth(1f);
        set1.setFillAlpha(65);
        set1.setDrawCircleHole(false);
        set1.setDrawCircles(false);
        set1.setValueTextColor(Color.BLACK);
        set1.setDrawValues(true);
        //设置小数点后面位数
        set1.setValueFormatter((value, entry, dataSetIndex, viewPortHandler) -> "   "+value);
        LineData data = new LineData(set1);
        //不显示圆点上的数值
        data.setDrawValues(false);
        data.setValueFormatter(new DefaultValueFormatter(3));
        // set data
        mChart.setData(data);

//        }
    }

    /**
     * 展示多条曲线。
     */
    private void showLineChart(ArrayList<GPSNormEntity.DataBean.CommonDataVOsBean> yAxisValues, ArrayList<String> labels) {

        ArrayList<ILineDataSet> dataSets = new ArrayList<>();
        for (int i = 0; i < totalGPSList.size(); i++) {
            ArrayList<Entry> yVals2 = new ArrayList<>();
            if ("累计变化量X".equals(totalGPSList.get(i))){
                for (int j = 1; j < yAxisValues.size(); j++) {
                    yVals2.add(new Entry(j, (float) yAxisValues.get(j).getTotalChangeX()));
                }
            }else if (("累计变化量Y".equals(totalGPSList.get(i)))){
                for (int j = 1; j < yAxisValues.size(); j++) {
                    yVals2.add(new Entry(j, (float) yAxisValues.get(j).getTotalChangeY()));
                }
            }else if (("累计变化量Z".equals(totalGPSList.get(i)))){
                for (int j = 1; j < yAxisValues.size(); j++) {
                    yVals2.add(new Entry(j, (float) yAxisValues.get(j).getTotalChangeZ()));
                }
            }
            Random myRandom = new Random();
            int ranColor = 0xff000000 | myRandom.nextInt(0x00ffffff);
            LineDataSet lineDataSet = new LineDataSet(yVals2, totalGPSList.get(i));
            lineDataSet.setDrawCircleHole(false);
            lineDataSet.setColor(ranColor);
            lineDataSet.setDrawCircles(false);
            lineDataSet.setDrawFilled(true);
            initLineDataSet(lineDataSet, ranColor, false);
            dataSets.add(lineDataSet);
        }

        LineData data = new LineData(dataSets);
        mChart.setData(data);
    }

    /**
     * 深部位移深度展示多条曲线。
     */
    private void showLineDeepChart(DeepDataEntity.DataBean yAxisValues, ArrayList<String> labels) {

        ArrayList<ILineDataSet> dataSets = new ArrayList<>();
        for (int i = 0; i < measuredDeepList.size(); i++) {
            ArrayList<Entry> yVals2 = new ArrayList<>();
            if ("X通道".equals(measuredDeepList.get(i))){
                for (int j = 1; j < yAxisValues.getDeepDatas().size(); j++) {
                    yVals2.add(new Entry(j, (float) yAxisValues.getDeepDatas().get(j).getMeasuredDataX()));
                }
            }else if (("Y通道".equals(measuredDeepList.get(i)))){
                for (int j = 1; j < yAxisValues.getDeepDatas().size(); j++) {
                    yVals2.add(new Entry(j, (float) yAxisValues.getDeepDatas().get(j).getMeasuredDataY()));
                }
            }
            Random myRandom = new Random();
            int ranColor = 0xff000000 | myRandom.nextInt(0x00ffffff);
            LineDataSet lineDataSet = new LineDataSet(yVals2, measuredDeepList.get(i));
            lineDataSet.setDrawCircleHole(false);
            lineDataSet.setColor(ranColor);
            lineDataSet.setDrawCircles(false);
            lineDataSet.setDrawFilled(true);
            initLineDataSet(lineDataSet, ranColor, false);
            dataSets.add(lineDataSet);
        }

        LineData data = new LineData(dataSets);
        mBinder.chart2.setData(data);
    }

    /**
     * 深部位移时间展示多条曲线。
     */
    private void showLineDeepTChart(NormDeepEntity.DataBean yAxisValues, ArrayList<String> labels) {

        ArrayList<ILineDataSet> dataSets = new ArrayList<>();
        for (int i = 0; i < measuredDeepTimeList.size(); i++) {
            ArrayList<Entry> yVals2 = new ArrayList<>();
            if ("深度X通道".equals(measuredDeepTimeList.get(i))){
                for (int j = 1; j < yAxisValues.getMeasuredDataX().size(); j++) {
                    yVals2.add(new Entry(j, (float) yAxisValues.getMeasuredDataX().get(j).getDate()));
                }
            }else if (("深度Y通道".equals(measuredDeepTimeList.get(i)))){
                for (int j = 1; j < yAxisValues.getMeasuredDataY().size(); j++) {
                    yVals2.add(new Entry(j, (float) yAxisValues.getMeasuredDataY().get(j).getDate()));
                }
            }
            Random myRandom = new Random();
            int ranColor = 0xff000000 | myRandom.nextInt(0x00ffffff);
            LineDataSet lineDataSet = new LineDataSet(yVals2, measuredDeepTimeList.get(i));
            lineDataSet.setDrawCircleHole(false);
            lineDataSet.setColor(ranColor);
            lineDataSet.setDrawCircles(false);
            lineDataSet.setDrawFilled(true);
            initLineDataSet(lineDataSet, ranColor, false);
            dataSets.add(lineDataSet);
        }

        LineData data = new LineData(dataSets);
        mChart.setData(data);
    }

    /**
     * 初始化曲线 每一个LineDataSet代表一条线
     *
     * @param lineDataSet
     * @param color
     * @param mode        折线图是否填充
     */
    private void initLineDataSet(LineDataSet lineDataSet, int color, boolean mode) {

        lineDataSet.setAxisDependency(YAxis.AxisDependency.LEFT);
        lineDataSet.setLineWidth(1f);
        lineDataSet.setFillAlpha(65);
        lineDataSet.setDrawCircleHole(false);
        lineDataSet.setDrawCircles(false);
        lineDataSet.setValueTextColor(Color.BLACK);
        lineDataSet.setDrawValues(true);
        //设置小数点后面位数
        lineDataSet.setValueFormatter((value, entry, dataSetIndex, viewPortHandler) -> "   "+value);
        //线模式为圆滑曲线（默认折线）
//        lineDataSet.setMode(LineDataSet.Mode.);
    }
    /**
     * 时间选择框
     */
    private void showDialogTwo() {
        View view = LayoutInflater.from((getContext())).inflate(R.layout.dialog_date, null);
        final DatePicker startTimeD = view.findViewById(R.id.st);
        final DatePicker endTimeD = view.findViewById(R.id.et);
        final TextView tvSt = view.findViewById(R.id.tv_st);
        final TextView tvEt = view.findViewById(R.id.tv_et);
        final TimePicker timePicker = view.findViewById(R.id.myTimePicker);
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setView(view);
        tvSt.setText("开始时间");
        tvEt.setText("结束时间");
        timePicker.setVisibility(View.GONE);
        startTimeD.setVisibility(View.VISIBLE);
        endTimeD.updateDate(startTimeD.getYear(), startTimeD.getMonth(), 01);
        builder.setPositiveButton("确定", (dialog, which) -> {
            int month = startTimeD.getMonth() + 1;
            String stringStartMonth;
            String strinStartDayOfMonth;
            if (month<10){
                stringStartMonth = "0" + String.valueOf(month);
            }else {
                stringStartMonth = String.valueOf(month);
            }
            if (startTimeD.getDayOfMonth()<10){
                strinStartDayOfMonth = "0" + String.valueOf(startTimeD.getDayOfMonth());
            }else {
                strinStartDayOfMonth = String.valueOf(startTimeD.getDayOfMonth());
            }
            String st = "" + startTimeD.getYear() + "-" + stringStartMonth + "-" + strinStartDayOfMonth;

            int month1 = endTimeD.getMonth() + 1;
            String stringEndMonth;
            String strinEndDayOfMonth;
            if (month1<10){
                stringEndMonth = "0" + String.valueOf(month1);
            }else {
                stringEndMonth = String.valueOf(month1);
            }
            if (endTimeD.getDayOfMonth()<10){
                strinEndDayOfMonth = "0" + String.valueOf(endTimeD.getDayOfMonth());
            }else {
                strinEndDayOfMonth = String.valueOf(endTimeD.getDayOfMonth());
            }
            String et = "" + endTimeD.getYear() + "-" + stringEndMonth + "-" + strinEndDayOfMonth;
            startTimeS = st+" 00:00:00";
            endTimeS = et+" 00:00:00";
            mBinder.btnStartTime.setText(startTimeS);
            mBinder.btnEndTime.setText(endTimeS);
            querySensorData();
        });
        builder.setNegativeButton("取消", null);
        AlertDialog dialog = builder.create();
        dialog.show();
        //自动弹出键盘问题解决
        dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
    }

}
