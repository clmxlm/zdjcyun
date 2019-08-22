package com.zdjc.zdjcyun.mvp.viewmodel.impl;


import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.blankj.utilcode.utils.ToastUtils;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.zdjc.zdjcyun.R;
import com.zdjc.zdjcyun.base.BaseModel;
import com.zdjc.zdjcyun.charting.charts.BubbleChart;
import com.zdjc.zdjcyun.charting.charts.CandleStickChart;
import com.zdjc.zdjcyun.charting.charts.LineChart;
import com.zdjc.zdjcyun.charting.charts.ScatterChart;
import com.zdjc.zdjcyun.charting.components.AxisBase;
import com.zdjc.zdjcyun.charting.components.Legend;
import com.zdjc.zdjcyun.charting.components.LegendEntry;
import com.zdjc.zdjcyun.charting.components.XAxis;
import com.zdjc.zdjcyun.charting.components.YAxis;
import com.zdjc.zdjcyun.charting.data.BubbleData;
import com.zdjc.zdjcyun.charting.data.BubbleDataSet;
import com.zdjc.zdjcyun.charting.data.BubbleEntry;
import com.zdjc.zdjcyun.charting.data.CandleData;
import com.zdjc.zdjcyun.charting.data.CandleDataSet;
import com.zdjc.zdjcyun.charting.data.CandleEntry;
import com.zdjc.zdjcyun.charting.data.Entry;
import com.zdjc.zdjcyun.charting.data.LineData;
import com.zdjc.zdjcyun.charting.data.LineDataSet;
import com.zdjc.zdjcyun.charting.data.ScatterData;
import com.zdjc.zdjcyun.charting.data.ScatterDataSet;
import com.zdjc.zdjcyun.charting.formatter.DefaultValueFormatter;
import com.zdjc.zdjcyun.charting.formatter.IAxisValueFormatter;
import com.zdjc.zdjcyun.charting.highlight.Highlight;
import com.zdjc.zdjcyun.charting.interfaces.datasets.IBubbleDataSet;
import com.zdjc.zdjcyun.charting.interfaces.datasets.ILineDataSet;
import com.zdjc.zdjcyun.charting.listener.OnChartValueSelectedListener;
import com.zdjc.zdjcyun.charting.utils.ColorTemplate;
import com.zdjc.zdjcyun.databinding.FragmentBimBinding;
import com.zdjc.zdjcyun.databinding.FragmentDataAnalysisBinding;
import com.zdjc.zdjcyun.mvp.entity.ComparisonDataEntity;
import com.zdjc.zdjcyun.mvp.entity.ComparisonGPSDataEntity;
import com.zdjc.zdjcyun.mvp.entity.MonitorTypeNameEntity;
import com.zdjc.zdjcyun.mvp.entity.MonitorUnitEntity;
import com.zdjc.zdjcyun.mvp.presenter.impl.BimPresenterImpl;
import com.zdjc.zdjcyun.mvp.presenter.impl.DataAnalysisPresenterImpl;
import com.zdjc.zdjcyun.mvp.ui.activities.DataComparisonActivity;
import com.zdjc.zdjcyun.mvp.ui.activities.MainActivity;
import com.zdjc.zdjcyun.mvp.viewmodel.IBimMessageModel;
import com.zdjc.zdjcyun.mvp.viewmodel.IDataAnalysisModel;
import com.zdjc.zdjcyun.util.PreferenceUtils;
import com.zdjc.zdjcyun.util.ScreenUtil;
import com.zdjc.zdjcyun.widget.LineChartMarkView;
import com.zdjc.zdjcyun.widget.LineChartsMarkView;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import static com.zdjc.zdjcyun.util.DateUtil.getDateToString;


public class DataAnalysisModel extends BaseModel<FragmentDataAnalysisBinding,DataAnalysisPresenterImpl> implements IDataAnalysisModel {


    private CandleStickChart csc;
    private Random random;
    private ScatterChart scatterChart;
    private BubbleChart bubbleChart;
    private BubbleData bubbleData;
    private ArrayList<String> xDatas = new ArrayList<>();
    private ArrayList<BubbleEntry> yDatas1 = new ArrayList<>();
    private ArrayList<BubbleEntry> yDatas2 = new ArrayList<>();
    private ArrayList<BubbleEntry> yDatas3 = new ArrayList<>();
    private ArrayList<BubbleEntry> yDatas4 = new ArrayList<>();
    private String[] stringArrayAnalysis = {"原数据", "离散分析图", "箱型分析图", "趋势分析图"};

    private LineChart lineChart;
    private LineData lineData;
    private ArrayList<String> xLineDatas = new ArrayList<>();

    private String[] xValue1 = new String[]{};
    private ArrayList<String> xTimeData= new ArrayList<>(Arrays.asList(xValue1));
    private ArrayList<String> monitorPointNumberList = new ArrayList<>();
    private ArrayList<Entry> yDatas = new ArrayList<>();
    private TagFlowLayout tagFlowLayout2;
    private LayoutInflater mInflater;
    private String id;
    private ArrayList<MonitorTypeNameEntity.DataBean> data;
    private ArrayList<String> array;
    private ArrayList<String> dataMonitorName;
    private ArrayList<ComparisonDataEntity.DataBean.ComparisonVOBean> comparisonVO = new ArrayList<>();
    private ArrayList<ComparisonGPSDataEntity.DataBean.ComparisonVOBean> comparisonGPSVO = new ArrayList<>();
    private long startTime;
    private long endTime;
    private String pointNamesJson;
    private ArrayList<MonitorUnitEntity.DataBean> monitorUnitList = new ArrayList<>();
    private int mapType=-1;
    private int tagPositon=0;
    private LineChart mChart;
    private int tagGraphics = 0;

    @Override
    public void onCreate() {

        id = PreferenceUtils.getInt(getContext(), "sectorId") + "";
        mapType = PreferenceUtils.getInt(getContext(),"mapType",mapType);
        mInflater = LayoutInflater.from(getContext());
        Map<String, String> map = new HashMap<>(0);
        map.put("sectorId", id);
        mControl.getQueryMonitorTypeName(DataAnalysisModel.this, map, 1);

        startTime = System.currentTimeMillis();
        endTime = startTime-7200000*12*7;
        /**
         * 拿到存储所有指标单位的集合（登录的时候存储的最新的数据）
         */
        String data = PreferenceUtils.getString(getContext(), "monitorUnit");
        Gson gson = new Gson();
        Type listType = new TypeToken<ArrayList<MonitorUnitEntity.DataBean>>() {
        }.getType();
        monitorUnitList = gson.fromJson(data, listType);
        inData();
    }

    private void inData() {
        random = new Random();
        initSetting();
        List<CandleEntry> candleEntry = new ArrayList<>();
        /**
         * shadowH 当天的最高价
         * shadowL 当天的最低价
         * open 开盘价
         * close 收盘价
         */
        candleEntry.add(new CandleEntry(2,8,1,8f,2f));
        candleEntry.add(new CandleEntry(3,5,1,4f,3f));
        candleEntry.add(new CandleEntry(4,8,2,4f,6f));
        setCandleStickData(candleEntry);


        /**
         * 气泡图初始化
         */
        for(int i = 0; i < 12; i++){
            xDatas.add((i+1) + "月");
            yDatas1.add(new BubbleEntry(i, (float) Math.random()*100, (float) Math.random()*30));
            yDatas2.add(new BubbleEntry(i, (float) Math.random()*100, (float) Math.random()*30));
            yDatas3.add(new BubbleEntry(i, (float) Math.random()*100, (float) Math.random()*30));
            yDatas4.add(new BubbleEntry(i, (float) Math.random()*100, (float) Math.random()*30));
        }

        bubbleChart = mBinder.bubbleChart;
        bubbleData = getBubbleData();
        showBubbleChart(bubbleChart, bubbleData);



        mBinder.segmentTabLayoutAnalysisMonitor.setTabData(stringArrayAnalysis);
        mBinder.segmentTabLayoutAnalysisMonitor.setCurrentTab(0);

        mBinder.segmentTabLayoutAnalysisMonitor.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                PreferenceUtils.putString(getContext(),"graphName",stringArrayAnalysis[position]);
                if ("原数据".equals(stringArrayAnalysis[position])){
                    mBinder.chart1.setVisibility(View.VISIBLE);
                    mBinder.bubbleChart.setVisibility(View.GONE);
                    mBinder.scatterChart.setVisibility(View.GONE);
                    mBinder.candleStickChart.setVisibility(View.GONE);
                }else if ("离散分析图".equals(stringArrayAnalysis[position])){
                    mBinder.chart1.setVisibility(View.GONE);
                    mBinder.bubbleChart.setVisibility(View.GONE);
                    mBinder.scatterChart.setVisibility(View.VISIBLE);
                    mBinder.candleStickChart.setVisibility(View.GONE);
                    /**
                     * 散列图初始化
                     */
                    initScatterChart();
                }else if ("箱型分析图".equals(stringArrayAnalysis[position])){
                    mBinder.chart1.setVisibility(View.GONE);
                    mBinder.bubbleChart.setVisibility(View.GONE);
                    mBinder.scatterChart.setVisibility(View.GONE);
                    mBinder.candleStickChart.setVisibility(View.VISIBLE);
                }else if ("趋势分析图".equals(stringArrayAnalysis[position])){
                    mBinder.chart1.setVisibility(View.GONE);
                    mBinder.bubbleChart.setVisibility(View.VISIBLE);
                    mBinder.scatterChart.setVisibility(View.GONE);
                    mBinder.candleStickChart.setVisibility(View.GONE);
                }
            }
            @Override
            public void onTabReselect(int position) {

            }
        });

        mBinder.rlSelectedData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popWindow(v);
            }
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
                array = new ArrayList<>();
                for (int i = 0; i < data.size(); i++) {
                    array.add(data.get(i).getMonitorTypeName());
                }
                break;
            case 2:
                dataMonitorName = (ArrayList<String>) bean;
                tagFlowLayout2.setAdapter(new TagAdapter<String>(dataMonitorName) {
                    @Override
                    public View getView(FlowLayout parent, int position, String s) {
                        TextView tv = (TextView) mInflater.inflate(R.layout.tag_text,
                                tagFlowLayout2, false);
                        tv.setText(s);
                        return tv;
                    }
                });

                break;
            case 3:
                tagPositon++;
                ComparisonDataEntity.DataBean comparisonDataEntity = (ComparisonDataEntity.DataBean) bean;
                comparisonVO = comparisonDataEntity.getComparisonVO();
                xTimeData.clear();
                monitorPointNumberList.clear();
                for (ComparisonDataEntity.DataBean.ComparisonVOBean aa : comparisonVO) {
                    monitorPointNumberList.add(aa.getMonitorPointNumber());
                    for (ArrayList<Object> string : aa.getTotalChange()) {
                        xTimeData.add(((String) string.get(0)));
                    }
                }
                xValue1 = xTimeData.toArray(new String[xTimeData.size()]);
                if (tagGraphics==1){
                    scatterChartData(xValue1,comparisonVO);
                }else if (tagGraphics==0){
                    curvelData(xValue1,comparisonVO);
                }
                mBinder.tvSelectedMonitor.setText(PreferenceUtils.getString(getContext(),"monitorTypeName"));
                break;
            case 4:
                tagPositon++;
                ComparisonGPSDataEntity.DataBean comparisonGPSDataEntity = (ComparisonGPSDataEntity.DataBean) bean;
                comparisonGPSVO = comparisonGPSDataEntity.getComparisonVO();
                xTimeData.clear();
                monitorPointNumberList.clear();
                for (ComparisonGPSDataEntity.DataBean.ComparisonVOBean aa : comparisonGPSVO) {
                    monitorPointNumberList.add(aa.getMonitorPointNumber());
                    for (ArrayList<Object> string : aa.getTotalChangeX()) {
                        xTimeData.add(((String) string.get(0)));
                    }
                }
                xValue1 = xTimeData.toArray(new String[xTimeData.size()]);
                curvelGPSData(xValue1,comparisonGPSVO);
                mBinder.tvSelectedMonitor.setText(PreferenceUtils.getString(getContext(),"monitorTypeName"));
                break;
            case 5:
                mapType = (int) bean;
                PreferenceUtils.putInt(getContext(),"mapType",mapType);
                if (mapType==SENSOR_TYPE){
                    PreferenceUtils.putString(getContext(),"AmountOfChangeName","累计变化量");
                }else if (mapType==SENSOR_TYPE_TWO){
                    PreferenceUtils.putString(getContext(),"AmountOfChangeName","累计变化量X");
                }
//                if (mapType==SENSOR_TYPE){
//                    mBinder.segmentTabLayout.setTabData(stringArray);
//                }else if (mapType==SENSOR_TYPE_TWO){
//                    mBinder.segmentTabLayout.setTabData(stringTotalArray);
//                }
//                mBinder.segmentTabLayout.setCurrentTab(0);
//                mBinder.segmentTabLayoutTime.setTabData(stringArrayTime);
//                mBinder.segmentTabLayoutTime.setCurrentTab(1);
                break;
            default:
                break;
        }
    }
    /**
     * 散列图常用设置
     */
    private void initScatterChart() {
        tagGraphics = 1;
        queryComparisonData();

    }

    public void scatterChartData(String[] xValue,ArrayList<ComparisonDataEntity.DataBean.ComparisonVOBean> yData){
        scatterChart = mBinder.scatterChart;
        ArrayList<Entry> yVals = new ArrayList<>();
        //初始化横纵坐标内容
        ArrayList<String> xVals = new ArrayList<>();
        for (String aXValue : xValue) {
            xVals.add(aXValue);
        }
        for (ComparisonDataEntity.DataBean.ComparisonVOBean aa : yData) {
            for (int i = 0; i < aa.getSingleChange().size(); i++) {
                yVals.add(new Entry(Float.valueOf(String.valueOf(aa.getSingleChange().get(i).get(1))), i));
            }
        }
        scatterChart.getDescription().setEnabled(false);
        scatterChart.setDrawGridBackground(false);
        ScatterDataSet scatterDataSet = new ScatterDataSet(yVals, "");
        //设置丰富多彩的颜色
        scatterDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        //生成Scatterdata对象
        ScatterData scatterData = new ScatterData(scatterDataSet);
        //生成Scatterdata对象
//        ScatterData scatterData = new ScatterData(scatterDataSet);
        //设置对应数据
        scatterChart.setData(scatterData);
//        scatterChart.getLegend().setPosition(Legend.LegendPosition.ABOVE_CHART_LEFT);
//        scatterChart.getLegend().setForm(Legend.LegendForm.CIRCLE);
        scatterChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, Highlight h) {
                ToastUtils.showShortToast("Something selected value = " + e.getX());
            }

            @Override
            public void onNothingSelected() {

            }
        });
        //设置X轴位置
        scatterChart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
        //右侧Y轴关闭
        scatterChart.getAxisRight().setEnabled(false);
        scatterChart.setDrawGridBackground(false);
        XAxis xAxis = scatterChart.getXAxis();
        xAxis.setDrawAxisLine(false);
//        xAxis.setValueFormatter(new MyCustomFormatter)
//        if (xValue.length>0){
//            xAxis.setValueFormatter((value, axis) -> xValue[(int) value % xValue.length]);
//        }
        //设置最小Y值
        scatterChart.getAxisLeft().setAxisMinValue(0.0f);
//        //设置纵向网格线条颜色
//        scatterChart.getXAxis().setGridColor(Color.RED);
//        //设置横向网格颜色
//        scatterChart.getAxisLeft().setGridColor(Color.GREEN);
        //设置描述内容
//        scatterChart.setDescription("No Deal");
        //设置描述文字的字体颜色
//        scatterChart.setDescriptionTextSize(20.f);
        //动画效果
        scatterChart.animateXY(1000, 1000);
    }
    /**
     * K线图（蜡烛图）常用设置
     */
    private void initSetting() {
        csc = mBinder.candleStickChart;
        csc.getDescription().setText("");
        csc.getDescription().setTextColor(Color.RED);
        //设置描述的文字 ,颜色 大小
        csc.getDescription().setTextSize(16);
        //没数据的时候显示
        csc.setNoDataText("无数据噢");
        //是否显示边框
        csc.setDrawBorders(false);
        //x轴动画
        csc.animateX(500);
        // 设置是否可以触摸
        csc.setTouchEnabled(true);
        // 是否可以拖拽
        csc.setDragEnabled(true);
        // 是否可以缩放 x和y轴, 默认是true
        csc.setScaleEnabled(false);
        //是否可以缩放 仅x轴
        csc.setScaleXEnabled(true);
        //是否可以缩放 仅y轴
        csc.setScaleYEnabled(true);
        //设置x轴和y轴能否同时缩放。默认是否
        csc.setPinchZoom(true);
        //设置是否可以通过双击屏幕放大图表。默认是true
        csc.setDoubleTapToZoomEnabled(true);
        //能否拖拽高亮线(数据点与坐标的提示线)，默认是true
        csc.setHighlightPerDragEnabled(true);
        //拖拽滚动时，手放开是否会持续滚动，默认是true（false是拖到哪是哪，true拖拽之后还会有缓冲）
        csc.setDragDecelerationEnabled(true);
        //与上面那个属性配合，持续滚动时的速度快慢，[0,1) 0代表立即停止
        csc.setDragDecelerationFrictionCoef(0.99f);

        //x轴的设置
        //获取x轴
        XAxis xAxis = csc.getXAxis();
        xAxis.setAxisMinimum(0);
        //设置x轴位置
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        //设置x轴字体颜色
        xAxis.setTextColor(Color.GRAY);
        //设置x轴文字字体大小
        xAxis.setTextSize(14);
        //设置竖向线  网格线
        xAxis.setDrawGridLines(false);

        //y轴的设置
        YAxis yAxisLeft = csc.getAxisLeft();
        //设置左侧y轴
        yAxisLeft.setAxisMinimum(0);
        //左侧y轴文字字体大小
        yAxisLeft.setTextSize(14);
        //设置右侧y轴
        YAxis yAxisRight = csc.getAxisRight();
        //设置右侧y轴是否可用
        yAxisRight.setEnabled(false);

        /**
         * 设置图例
         */
        Legend legend = csc.getLegend();
        legend.setEnabled(true);
        //设置比例图样式 方
        legend.setForm(Legend.LegendForm.SQUARE);
        //设置横向
        legend.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        //设置位置
        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT);
        String[] lables = new String[]{"涨","跌"};
        int[] colors = new int[]{Color.RED,Color.BLACK};
        LegendEntry incre = new LegendEntry("涨", Legend.LegendForm.SQUARE, 10f, 1f, null, colors[0]);
        LegendEntry decre = new LegendEntry("跌", Legend.LegendForm.SQUARE, 10f, 1f, null, colors[1]);
        legend.setCustom(new LegendEntry[]{incre,decre});
    }

    /**
     * 设置K线图（蜡烛图）数据
     * @param yVals
     */
    public void setCandleStickData(List<CandleEntry> yVals){
        CandleDataSet candleDataSet = new CandleDataSet(yVals,"");
        candleDataSet.setValueTextColor(Color.BLACK);
        candleDataSet.setValueTextSize(14);
        //设置影线的颜色
        candleDataSet.setShadowColor(Color.DKGRAY);
        //设置影线的宽度
        candleDataSet.setShadowWidth(0.5f);
        //设置影线和蜡烛图的颜色一样
        candleDataSet.setShadowColorSameAsCandle(true);
        //设置减少色
        candleDataSet.setDecreasingColor(Color.BLACK);
        //绿跌，空心描边
        candleDataSet.setDecreasingPaintStyle(Paint.Style.STROKE);
        //设置增长色
        candleDataSet.setIncreasingColor(Color.RED);
        //设置增长红 实心
        candleDataSet.setIncreasingPaintStyle(Paint.Style.FILL);
        //当天价格不涨不跌（一字线）颜色
        candleDataSet.setNeutralColor(Color.RED);
        //设置定位线是否可用
        candleDataSet.setHighlightEnabled(true);
        //设置定位线的颜色
        candleDataSet.setHighLightColor(Color.BLACK);
        //设置定位线的线宽
        candleDataSet.setHighlightLineWidth(0.5f);
        //0 至1 之间,越小蜡烛图的宽度越宽
        candleDataSet.setBarSpace(0.9f);
        //设置是否显示蜡烛图上的文字
        candleDataSet.setDrawValues(false);
        CandleData data = new CandleData(candleDataSet);
        csc.setData(data);
    }

    /**
     * 展示气泡图
     * @param bubbleChart
     * @param bubbleData
     */
    private void showBubbleChart(BubbleChart bubbleChart, BubbleData bubbleData) {
        bubbleChart.setDrawBorders(false);
        bubbleChart.setDrawGridBackground(false);
        bubbleChart.setPinchZoom(false);
        bubbleChart.animateXY(1000, 1000);
        bubbleChart.getAxisLeft().setAxisMinValue(0.0f);

        XAxis xAxis = bubbleChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);

        YAxis yAxisRight = bubbleChart.getAxisRight();
        yAxisRight.setEnabled(false);

        Legend legend = bubbleChart.getLegend();
        legend.setForm(Legend.LegendForm.CIRCLE);
        legend.setPosition(Legend.LegendPosition.LEFT_OF_CHART_INSIDE);

        bubbleChart.setData(bubbleData);
        bubbleChart.invalidate();
    }

    /**
     * 得到气泡图数据
     * @return
     */
    public BubbleData getBubbleData() {
        BubbleDataSet bubbleDataSet1 = new BubbleDataSet(yDatas1, "第一组气泡");
        bubbleDataSet1.setColor(Color.rgb(255, 51, 153));
        bubbleDataSet1.setValueTextSize(8);
        BubbleDataSet bubbleDataSet2 = new BubbleDataSet(yDatas2, "第二组气泡");
        bubbleDataSet2.setColor(Color.rgb(255, 255, 51));
        bubbleDataSet2.setValueTextSize(8);
        BubbleDataSet bubbleDataSet3 = new BubbleDataSet(yDatas3, "第三组气泡");
        bubbleDataSet3.setColor(Color.rgb(102, 0, 255));
        bubbleDataSet3.setValueTextSize(8);
        BubbleDataSet bubbleDataSet4 = new BubbleDataSet(yDatas4, "第四组气泡");
        bubbleDataSet4.setColor(Color.rgb(0, 255, 102));
        bubbleDataSet4.setValueTextSize(8);

        ArrayList<IBubbleDataSet> bubbleDataSets = new ArrayList<>();
        bubbleDataSets.add(bubbleDataSet1);
        bubbleDataSets.add(bubbleDataSet2);
        bubbleDataSets.add(bubbleDataSet3);
        bubbleDataSets.add(bubbleDataSet4);

        BubbleData bubbleData = new BubbleData(bubbleDataSets);
        return bubbleData;
    }



    /**
     * 初始化LineChart
     */
    private void curvelData(String[] xValue,ArrayList<ComparisonDataEntity.DataBean.ComparisonVOBean> yData){
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
        showLineChart(yData,monitorPointNumberList);

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
        xAxis.setTextColor(Color.BLACK);
        xAxis.setDrawGridLines(false);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setLabelCount(3);
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
    private void curvelGPSData(String[] xValue,ArrayList<ComparisonGPSDataEntity.DataBean.ComparisonVOBean> yData){
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
        setData(yData,monitorPointNumberList);

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
        xAxis.setTextColor(Color.BLACK);
        xAxis.setDrawGridLines(false);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setLabelCount(3);
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
                if ("累计变化量X".equals(PreferenceUtils.getString(getContext(),"AmountOfChangeName"))){
                    LineChartMarkView mv = new LineChartMarkView(getContext(), xAxis.getValueFormatter(),"累计变化量X");
                    mv.setChartView(mChart);
                    mChart.setMarker(mv);
                    mChart.invalidate();
                }else if (("累计变化量Y".equals(PreferenceUtils.getString(getContext(),"AmountOfChangeName")))){
                    LineChartMarkView mv = new LineChartMarkView(getContext(), xAxis.getValueFormatter(),"累计变化量Y");
                    mv.setChartView(mChart);
                    mChart.setMarker(mv);
                    mChart.invalidate();
                }else if (("累计变化量Z".equals(PreferenceUtils.getString(getContext(),"AmountOfChangeName")))){
                    LineChartMarkView mv = new LineChartMarkView(getContext(), xAxis.getValueFormatter(),"累计变化量Z");
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
     * 统一曲线图设值
     */
    private void setData(ArrayList<ComparisonGPSDataEntity.DataBean.ComparisonVOBean> data1,ArrayList<String> monitorPointNumberList) {

        ArrayList<Entry> yVals1 = new ArrayList<>();
        LineDataSet set1 = null;
        for (int i = 0; i < data1.size(); i++) {
            if ("累计变化量X".equals(PreferenceUtils.getString(getContext(),"AmountOfChangeName"))){
                for (int j = 0; j < data1.get(i).getTotalChangeX().size(); j++) {
                    yVals1.add(new Entry(j, Float.valueOf(String.valueOf(data1.get(i).getTotalChangeX().get(j).get(1)))));
                }
                set1 = new LineDataSet(yVals1, monitorPointNumberList.get(i));
            }else if (("累计变化量Y".equals(PreferenceUtils.getString(getContext(),"AmountOfChangeName")))){
                for (int j = 0; j < data1.get(i).getTotalChangeY().size(); j++) {
                    yVals1.add(new Entry(j, Float.valueOf(String.valueOf(data1.get(i).getTotalChangeY().get(j).get(1)))));
                }
                set1 = new LineDataSet(yVals1, monitorPointNumberList.get(i));
            }else if (("累计变化量Z".equals(PreferenceUtils.getString(getContext(),"AmountOfChangeName")))){
                for (int j = 0; j < data1.get(i).getTotalChangeZ().size(); j++) {
                    yVals1.add(new Entry(j, Float.valueOf(String.valueOf(data1.get(i).getTotalChangeZ().get(j).get(1)))));
                }
                set1 = new LineDataSet(yVals1, monitorPointNumberList.get(i));
            }

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
    private void showLineChart(ArrayList<ComparisonDataEntity.DataBean.ComparisonVOBean> yAxisValues, ArrayList<String> labels) {

        ArrayList<ILineDataSet> dataSets = new ArrayList<>();
        for (int i = 0; i < yAxisValues.size(); i++) {
            ArrayList<Entry> yVals2 = new ArrayList<>();
            if ("累计变化量".equals(PreferenceUtils.getString(getContext(),"AmountOfChangeName"))){
                for (int j = 1; j < yAxisValues.get(i).getTotalChange().size(); j++) {
                    yVals2.add(new Entry(j, Float.valueOf(String.valueOf(yAxisValues.get(i).getTotalChange().get(j).get(1)))));
                }
            }else if (("单次变化量".equals(PreferenceUtils.getString(getContext(),"AmountOfChangeName")))){
                for (int j = 1; j < yAxisValues.get(i).getSingleChange().size(); j++) {
                    yVals2.add(new Entry(j, Float.valueOf(String.valueOf(yAxisValues.get(i).getSingleChange().get(j).get(1)))));
                }
            }else if (("变化速率".equals(PreferenceUtils.getString(getContext(),"AmountOfChangeName")))){
                for (int j = 1; j < yAxisValues.get(i).getSpeedChange().size(); j++) {
                    yVals2.add(new Entry(j, Float.valueOf(String.valueOf(yAxisValues.get(i).getSpeedChange().get(j).get(1)))));
                }
            }
            Random myRandom = new Random();
            int ranColor = 0xff000000 | myRandom.nextInt(0x00ffffff);
            LineDataSet lineDataSet = new LineDataSet(yVals2, labels.get(i));
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

    @Override
    public void onError(String errorMsg, int code,int tag) {

    }

    private void popWindow(View v) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.popwindow_layout, null);
        //设置屏幕的高度和宽度
        final PopupWindow pop = new PopupWindow(view, ScreenUtil.getScreenWidth(getContext()), ScreenUtil.getScreenHeight(getContext()) * 3 / 10);
        //如果不设置背景颜色的话，无法是pop dimiss掉。
        pop.setBackgroundDrawable(getContext().getResources().getDrawable(R.drawable.popupwindow_background));
        pop.setOutsideTouchable(true);
        pop.setAnimationStyle(R.style.MyPopupWindow_anim_style);

        pop.setWidth(WindowManager.LayoutParams.MATCH_PARENT);
        int height = ScreenUtil.getScreenHeight(getContext())/2;
        pop.setHeight(height);


        TagFlowLayout tagFlowLayout = view.findViewById(R.id.flowlayout1);
        Button btnComparedData = view.findViewById(R.id.btn_compared_data);
        ImageView ivCancel = view.findViewById(R.id.iv_cancel);
        tagFlowLayout2 = view.findViewById(R.id.flowlayout2);

        ivCancel.setOnClickListener(v12 -> pop.dismiss());

        tagFlowLayout.setAdapter(new TagAdapter<String>(array) {
            @Override
            public View getView(FlowLayout parent, int position, String s) {
                TextView tv = (TextView) mInflater.inflate(R.layout.tag_text,
                        tagFlowLayout, false);
                tv.setText(s);
                return tv;
            }
        });

        tagFlowLayout.setOnTagClickListener((view1, position, parent) -> {
            Map<String, String> map = new HashMap<>(0);
            map.put("sectorId", id);
            map.put("monitorType", data.get(position).getMonitorType()+"");
            mControl.getQueryMonitorPointName(DataAnalysisModel.this, map, 2);

            /**
             * 请求不同指标下的不同数据对象tag
             */
            Map<String, String> mapType = new HashMap<>(0);
            mapType.put("monitorType", data.get(position).getMonitorType()+"");
            mControl.getQueryMapType(DataAnalysisModel.this, mapType, 5);

            PreferenceUtils.putInt(getContext(),"monitorType",data.get(position).getMonitorType());
            PreferenceUtils.putString(getContext(),"monitorTypeName",data.get(position).getMonitorTypeName());

            for (int i = 0; i < monitorUnitList.size(); i++) {
                if (data.get(position).getMonitorType() == monitorUnitList.get(i).getMonitorType()){
                    String otherUnit = monitorUnitList.get(i).getUnitAtype();
                    PreferenceUtils.putString(getContext(),"otherUnit",otherUnit);
                    String rateUnit = monitorUnitList.get(i).getUnitCtype();
                    PreferenceUtils.putString(getContext(),"rateUnit",rateUnit);
                }
            }
            return true;
        });

        tagFlowLayout2.setOnSelectListener(selectPosSet -> {

            ArrayList<String> pointNames = new ArrayList<>();
            for (Integer integer : selectPosSet) {
                pointNames.add(dataMonitorName.get(integer));
            }
            Gson gson = new Gson();
            pointNamesJson = gson.toJson(pointNames);
        });


        btnComparedData.setOnClickListener(v1 -> {
            queryComparisonData();
            pop.dismiss();
        });

        /** * 设置popwindow的弹出的位置. *
         1：首先要判断是否有navigation bar。如果有的的话，要把他们的高度给加起来。 * *
         2：showAtLocation（）；是pop相对于屏幕而言的。 * *
         3：如果是 pop.showAsDropDown();则是相对于你要点击的view的位置。设置的坐标。
         */
        if (checkDeviceHasNavigationBar2(getContext())) {
            int heigth_tobottom = getNavigationBarHeight();
            pop.showAtLocation(v, Gravity.BOTTOM, 0, heigth_tobottom);
        } else {
            pop.showAtLocation(v, Gravity.BOTTOM, 0, 0);
        }
        //设置 背景的颜色为 0.5f 的透明度
        backgroundAlpha(0.5f);
        pop.setOnDismissListener(() -> backgroundAlpha(1.0f));

    }

    private void backgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = ((Activity) getContext()).getWindow().getAttributes();
        lp.alpha = bgAlpha;
        ((Activity) getContext()).getWindow().setAttributes(lp);
    }

    /**
     * /获取是否存在虚拟按键 NavigationBar：如果是有就返回true,如果是没有就是返回的false。第二种方法
     */
    private boolean checkDeviceHasNavigationBar2(Context context) {
        boolean hasNavigationBar = false;
        Resources rs = context.getResources();
        int id = rs.getIdentifier("config_showNavigationBar", "bool", "android");
        if (id > 0) {
            hasNavigationBar = rs.getBoolean(id);
        }
        try {
            Class systemPropertiesClass = Class.forName("android.os.SystemProperties");
            Method m = systemPropertiesClass.getMethod("get", String.class);
            String navBarOverride = (String) m.invoke(systemPropertiesClass, "qemu.hw.mainkeys");
            if ("1".equals(navBarOverride)) {
                hasNavigationBar = false;
            } else if ("0".equals(navBarOverride)) {
                hasNavigationBar = true;
            }
        } catch (Exception e) {
        }
        return hasNavigationBar;
    }

    /**
     * 获取navigationbar的高度。
     */
    private int getNavigationBarHeight() {
        Resources resources = ((Activity) getContext()).getResources();
        int resourceId = resources.getIdentifier("navigation_bar_height", "dimen", "android");
        int height = resources.getDimensionPixelSize(resourceId);
        return height;
    }

    private void queryComparisonData(){
        int monitorType = PreferenceUtils.getInt(getContext(),"monitorType");
        String startTimeString = getDateToString(startTime);
        String endTimeString = getDateToString(endTime);
        Map<String, String> map = new HashMap<>(0);
        map.put("sectorId", id);
        map.put("monitorType", monitorType+"");
        map.put("pointNames", pointNamesJson);
        map.put("beginTime", endTimeString);
        map.put("endTime", startTimeString);
        map.put("dateType", 1+"");
        if (mapType==SENSOR_TYPE){
            mControl.getQueryComparisonData(DataAnalysisModel.this, map, 3);
        }else if (mapType==SENSOR_TYPE_TWO){
            mControl.getQueryComparisonGPSData(DataAnalysisModel.this, map, 4);
        }

    }

}
