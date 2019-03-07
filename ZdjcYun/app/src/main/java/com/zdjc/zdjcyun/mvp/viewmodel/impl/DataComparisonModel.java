package com.zdjc.zdjcyun.mvp.viewmodel.impl;


import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
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
import com.zdjc.zdjcyun.charting.charts.LineChart;
import com.zdjc.zdjcyun.charting.components.AxisBase;
import com.zdjc.zdjcyun.charting.components.Legend;
import com.zdjc.zdjcyun.charting.components.XAxis;
import com.zdjc.zdjcyun.charting.components.YAxis;
import com.zdjc.zdjcyun.charting.data.Entry;
import com.zdjc.zdjcyun.charting.data.LineData;
import com.zdjc.zdjcyun.charting.data.LineDataSet;
import com.zdjc.zdjcyun.charting.formatter.IAxisValueFormatter;
import com.zdjc.zdjcyun.charting.highlight.Highlight;
import com.zdjc.zdjcyun.charting.interfaces.datasets.ILineDataSet;
import com.zdjc.zdjcyun.charting.listener.OnChartValueSelectedListener;
import com.zdjc.zdjcyun.databinding.ActivityDataComparisonBinding;
import com.zdjc.zdjcyun.mvp.entity.ComparisonDataEntity;
import com.zdjc.zdjcyun.mvp.entity.MonitorTypeNameEntity;
import com.zdjc.zdjcyun.mvp.entity.MonitorUnitEntity;
import com.zdjc.zdjcyun.mvp.presenter.impl.DataComparisonPresenterImpl;
import com.zdjc.zdjcyun.mvp.ui.activities.DataComparisonActivity;
import com.zdjc.zdjcyun.mvp.viewmodel.IDataComparisonhModel;
import com.zdjc.zdjcyun.util.PreferenceUtils;
import com.zdjc.zdjcyun.util.ScreenUtil;
import com.zdjc.zdjcyun.widget.LineChartsMarkView;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static com.zdjc.zdjcyun.util.DateUtil.getDateToString;


public class DataComparisonModel extends BaseModel<ActivityDataComparisonBinding, DataComparisonPresenterImpl> implements IDataComparisonhModel {

    private String[] stringArray = {"累计变化量", "单次变化量", "变化速率"};
    private String[] stringArrayTime = {"全部", "一周", "一月"};
    private String[] xValue1 = new String[]{};
    private ArrayList<String> xTimeData= new ArrayList<>(Arrays.asList(xValue1));
    private ArrayList<String> monitorPointNumberList = new ArrayList<>();
    private ArrayList<Integer> colorList = new ArrayList<>();
    private String id;
    private ArrayList<MonitorTypeNameEntity.DataBean> data;
    private ArrayList<String> array;
    private ArrayList<String> dataMonitorName;

    private TagFlowLayout tagFlowLayout2;
    private LayoutInflater mInflater;
    private LineChart mChart;
    private int tagPositon=0;
    private ArrayList<ComparisonDataEntity.DataBean.ComparisonVOBean> comparisonVO = new ArrayList<>();
    private long startTime;
    private long endTime;
    private String pointNamesJson;
    private ArrayList<MonitorUnitEntity.DataBean> monitorUnitList = new ArrayList<>();

    @Override
    public void onCreate() {
        mInflater = LayoutInflater.from(getContext());
        id = PreferenceUtils.getInt(getContext(), "sectorId") + "";
        mBinder.include.tvTitle.setText("数据对比");
        mBinder.include.imgbtnBack.setVisibility(View.VISIBLE);
        mBinder.include.imgbtnBack.setOnClickListener(v -> {
            ((DataComparisonActivity) UI).finish();
            tagPositon=0;
        });
        startTime = System.currentTimeMillis();
        endTime = startTime-7200000*12*7;
        PreferenceUtils.putString(getContext(),"AmountOfChangeName","累计变化量");
        Map<String, String> map = new HashMap<>(0);
        map.put("sectorId", id);
        mControl.getQueryMonitorTypeName(DataComparisonModel.this, map, 1);

        /**
         * 拿到存储所有指标单位的集合（登录的时候存储的最新的数据）
         */
        String data = PreferenceUtils.getString(getContext(), "monitorUnit");
        Gson gson = new Gson();
        Type listType = new TypeToken<ArrayList<MonitorUnitEntity.DataBean>>() {
        }.getType();
        monitorUnitList = gson.fromJson(data, listType);

        initData();
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
                curvelData(xValue1,comparisonVO);
                mBinder.tvSelectedMonitor.setText(PreferenceUtils.getString(getContext(),"monitorTypeName"));
                break;
            default:
                break;
        }
    }


    @Override
    public void onError(String errorMsg, int code, int tag) {

    }

    @Override
    public void initData() {
        mBinder.segmentTabLayout.setTabData(stringArray);
        mBinder.segmentTabLayout.setCurrentTab(0);
        mBinder.segmentTabLayoutTime.setTabData(stringArrayTime);
        mBinder.segmentTabLayoutTime.setCurrentTab(1);

        mBinder.rlSelectedData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popWindow(v);
            }
        });

        mBinder.segmentTabLayout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                PreferenceUtils.putString(getContext(),"AmountOfChangeName",stringArray[position]);
                if (tagPositon==0){
                    ToastUtils.showShortToast("请先选择对比数据!");
                }else {
                    curvelData(xValue1,comparisonVO);
                }
            }

            @Override
            public void onTabReselect(int position) {

            }
        });

        mBinder.segmentTabLayoutTime.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                if (tagPositon==0){
                    ToastUtils.showShortToast("请先选择对比数据!");
                }else {
                    long oneDayTime=7200000*12;
                    if ("一周".equals(stringArrayTime[position])){
                        long weekTime=oneDayTime*7;
                        startTime = System.currentTimeMillis();
                        endTime = startTime-weekTime;
                    }else if ("一月".equals(stringArrayTime[position])){
                        long monthTime=oneDayTime*30;
                        startTime = System.currentTimeMillis();
                        endTime = startTime-monthTime;
                    }
                    queryComparisonData();
                }
            }

            @Override
            public void onTabReselect(int position) {
            }
        });
    }

    @Override
    public void initRunable() {

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
            mControl.getQueryMonitorPointName(DataComparisonModel.this, map, 2);
            PreferenceUtils.putInt(getContext(),"monitorType",data.get(position).getMonitorType());
            PreferenceUtils.putString(getContext(),"monitorTypeName",data.get(position).getMonitorTypeName());

            for (int i = 0; i < monitorUnitList.size(); i++) {
                if (data.get(position).getMonitorType() == monitorUnitList.get(i).getMonitorType()){
                    String otherUnit = monitorUnitList.get(i).getUnitA();
                    PreferenceUtils.putString(getContext(),"otherUnit",otherUnit);
                    String rateUnit = monitorUnitList.get(i).getUnitC();
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
        mControl.getQueryComparisonData(DataComparisonModel.this, map, 3);
    }


    private void backgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = ((DataComparisonActivity) UI).getWindow().getAttributes();
        lp.alpha = bgAlpha;
        ((DataComparisonActivity) UI).getWindow().setAttributes(lp);
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
        Resources resources = ((DataComparisonActivity) UI).getResources();
        int resourceId = resources.getIdentifier("navigation_bar_height", "dimen", "android");
        int height = resources.getDimensionPixelSize(resourceId);
        return height;
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
        colorList.add(R.color.theme_color);
        colorList.add(R.color.colorAccent);
        colorList.add(R.color.number_color);
        showLineChart(yData,monitorPointNumberList,colorList);

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
    /**
     * 展示多条曲线。
     */
    private void showLineChart(ArrayList<ComparisonDataEntity.DataBean.ComparisonVOBean> yAxisValues, ArrayList<String> labels, ArrayList<Integer> colours) {

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
            LineDataSet lineDataSet = new LineDataSet(yVals2, labels.get(i));
            lineDataSet.setDrawCircleHole(false);
            lineDataSet.setColor(colorList.get(i));
            lineDataSet.setDrawCircles(false);
            lineDataSet.setDrawFilled(true);
            initLineDataSet(lineDataSet, colours.get(i), false);
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

//        lineDataSet.setCircleColor(color);
//        lineDataSet.setLineWidth(1f);
//        lineDataSet.setFillColor(color);
//        lineDataSet.setCircleRadius(3f);
//        //设置曲线值的圆点是实心还是空心
//        lineDataSet.setDrawCircleHole(false);
//        lineDataSet.setValueTextSize(9f);
//        //设置折线图填充
//        lineDataSet.setDrawFilled(mode);
//        lineDataSet.setFormLineWidth(1f);
//        lineDataSet.setFormSize(15.f);

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
     * 数据变成科学计数法显示。用此方法可以使其正常显示。
     */
    private  String formatFloatNumber(double value) {
        DecimalFormat df = new DecimalFormat("0.000");
        return df.format(value);
    }
}
