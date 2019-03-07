package com.zdjc.zdjcyun.mvp.ui.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.ColorRes;
import android.view.View;
import android.widget.TextView;

import com.zdjc.zdjcyun.R;
import com.zdjc.zdjcyun.base.BaseRecyclerAdapter;
import com.zdjc.zdjcyun.base.RecyclerViewHolder;
import com.zdjc.zdjcyun.charting.animation.Easing;
import com.zdjc.zdjcyun.charting.charts.BarChart;
import com.zdjc.zdjcyun.charting.components.Description;
import com.zdjc.zdjcyun.charting.components.Legend;
import com.zdjc.zdjcyun.charting.components.XAxis;
import com.zdjc.zdjcyun.charting.components.YAxis;
import com.zdjc.zdjcyun.charting.data.BarData;
import com.zdjc.zdjcyun.charting.data.BarDataSet;
import com.zdjc.zdjcyun.charting.data.BarEntry;
import com.zdjc.zdjcyun.charting.interfaces.datasets.IBarDataSet;
import com.zdjc.zdjcyun.mvp.entity.MonitorViewEntity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;

import butterknife.Bind;


public class ProjectRecycViewAdapter extends BaseRecyclerAdapter<MonitorViewEntity.DataBean> {

    private Context context;
    private boolean or = false;
    private XAxis xAxis;
    private YAxis leftAxis;
    private YAxis rightAxis;
    private Legend legend;

    public ProjectRecycViewAdapter(Context context) {
        super(context);
        this.context = context;
    }

    @Override
    protected int getContentViewId() {
        return R.layout.news_item;
    }

    @Override
    protected RecyclerViewHolder getHolder(View v) {
        return new ViewHolder(v);
    }

    @Override
    protected void setView(RecyclerViewHolder o, int position, MonitorViewEntity.DataBean data) {
        ViewHolder holder= (ViewHolder) o;
        initBarChart(holder.barChart);
        holder.tvAlarmAll.setText("总预警（次）"+data.getAlarmCount()+"");
        holder.tvProjectName.setText(data.getProjectName());
        holder.tvProjectDescription.setText(data.getProjectName()+": "+data.getProjectDescription());
        //处理数据是 记得判断每条柱状图对应的数据集合 长度是否一致
        LinkedHashMap<String, List<Float>> chartDataMap = new LinkedHashMap<>();
        List<String> xValues = new ArrayList<>();
        List<Float> yValue1 = new ArrayList<>();
        List<Float> yValue2 = new ArrayList<>();
        List<Float> yValue3 = new ArrayList<>();
        List<Integer> colors = Arrays.asList(
                context.getResources().getColor(R.color.number_color), context.getResources().getColor(R.color.theme_color),context.getResources().getColor(R.color.colorAccent));
        List<MonitorViewEntity.DataBean.ListBean>  valueList = data.getList();
        Collections.reverse(valueList);



        for (MonitorViewEntity.DataBean.ListBean valueBean : valueList) {
            xValues.add(valueBean.getMonitorTypeName());
            yValue1.add((float) valueBean.getLevel1());
            yValue2.add((float) valueBean.getLevel2());
            yValue3.add((float) valueBean.getLevel3());
        }

        chartDataMap.put("一级告警", yValue1);
        chartDataMap.put("二级告警", yValue2);
        chartDataMap.put("三级告警", yValue3);

        showBarChart(xValues, chartDataMap, colors,holder.barChart);
    }

    class ViewHolder extends RecyclerViewHolder {
        @Bind(R.id.barChart)
        BarChart barChart;
        @Bind(R.id.tv_alarm_all)
        TextView tvAlarmAll;
        @Bind(R.id.tv_project_name)
        TextView tvProjectName;
        @Bind(R.id.tv_project_description)
        TextView tvProjectDescription;


        public ViewHolder(View itemView) {
            super(itemView);
        }
    }

    /**
     * 初始化BarChart图表
     */
    private void initBarChart(BarChart barChart) {
        /***图表设置***/
        //背景颜色
        barChart.setBackgroundColor(Color.WHITE);
        //不显示图表网格
        barChart.setDrawGridBackground(false);
        //背景阴影
        barChart.setDrawBarShadow(false);
        barChart.setHighlightFullBarEnabled(false);

        barChart.setDoubleTapToZoomEnabled(false);
        //禁止拖拽
        barChart.setDragEnabled(false);
        //X轴或Y轴禁止缩放
        barChart.setScaleXEnabled(false);
        barChart.setScaleYEnabled(false);
        barChart.setScaleEnabled(false);
        //禁止所有事件
//        barChart.setTouchEnabled(false);


        //不显示边框
        barChart.setDrawBorders(false);

        //不显示右下角描述内容
        Description description = new Description();
        description.setEnabled(false);
        barChart.setDescription(description);

        //设置动画效果
        barChart.animateY(1000, Easing.Linear);
        barChart.animateX(1000, Easing.Linear);

        /***XY轴的设置***/
        //X轴设置显示位置在底部
        xAxis = barChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setGranularity(1f);

        leftAxis = barChart.getAxisLeft();
        rightAxis = barChart.getAxisRight();


//        xAxis.setAxisMinimum(0f);
        //保证Y轴从0开始，不然会上移一点
//        leftAxis.setAxisMinimum(0f);
//        rightAxis.setAxisMinimum(0f);

        //不绘制X Y轴线条
        xAxis.setDrawAxisLine(false);
        leftAxis.setDrawAxisLine(true);
        rightAxis.setDrawAxisLine(false);
        //不显示左侧Y轴
        rightAxis.setEnabled(false);

        //不显示X轴网格线
        xAxis.setDrawGridLines(false);
        //左侧Y轴网格线设置为虚线
        leftAxis.enableGridDashedLine(10f, 10f, 0f);
        /***折线图例 标签 设置***/
        legend = barChart.getLegend();
        legend.setForm(Legend.LegendForm.LINE);
        legend.setTextSize(11f);
        //显示位置
        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT);
        legend.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        //是否绘制在图表里面
        legend.setDrawInside(false);
    }

    /**
     * 柱状图始化设置 一个BarDataSet 代表一列柱状图
     *
     * @param barDataSet 柱状图
     * @param color      柱状图颜色
     */
    private void initBarDataSet(BarDataSet barDataSet, int color) {
        barDataSet.setColor(color);
        barDataSet.setFormLineWidth(1f);
        barDataSet.setFormSize(15.f);
        //不显示柱状图顶部值
        barDataSet.setDrawValues(false);
//        barDataSet.setValueTextSize(10f);
//        barDataSet.setValueTextColor(color);
    }

    /**
     * @param xValues   X轴的值
     * @param dataLists LinkedHashMap<String, List<Float>>
     * key对应柱状图名字  List<Float> 对应每类柱状图的Y值
     * @param colors
     */
    public void showBarChart(final List<String> xValues, LinkedHashMap<String, List<Float>> dataLists,
                             @ColorRes List<Integer> colors,BarChart barChart) {

        List<IBarDataSet> dataSets = new ArrayList<>();
        //用于柱状图颜色集合的index
        int currentPosition = 0;

        for (LinkedHashMap.Entry<String, List<Float>> entry : dataLists.entrySet()) {
            String name = entry.getKey();
            List<Float> yValueList = entry.getValue();

            List<BarEntry> entries = new ArrayList<>();

            for (int i = 0; i < yValueList.size(); i++) {
                /**
                 *  如果需要添加TAG标志 可使用以下构造方法
                 *  BarEntry(float x, float y, Object data)
                 *  e.getData()
                 */
                entries.add(new BarEntry(i, yValueList.get(i)));
            }
            // 每一个BarDataSet代表一类柱状图
            BarDataSet barDataSet = new BarDataSet(entries, name);
            initBarDataSet(barDataSet, colors.get(currentPosition));
            dataSets.add(barDataSet);

            currentPosition++;
        }

        //X轴自定义值
        xAxis.setValueFormatter((value, axis) -> xValues.get((int) Math.abs(value) % xValues.size()));
        //左侧Y轴自定义值
        leftAxis.setValueFormatter((value, axis) -> (int) (value)+"");
        leftAxis.setAxisMinimum(1f);
        leftAxis.setAxisMaximum(15f);
        BarData data = new BarData(dataSets);

        /**
         * float groupSpace = 0.3f;   //柱状图组之间的间距
         * float barSpace =  0.05f;  //每条柱状图之间的间距  一组两个柱状图
         * float barWidth = 0.3f;    //每条柱状图的宽度     一组两个柱状图
         * (barWidth + barSpace) * 2 + groupSpace = (0.3 + 0.05) * 2 + 0.3 = 1.00
         * 3个数值 加起来 必须等于 1 即100% 按照百分比来计算 组间距 柱状图间距 柱状图宽度
         */
        //需要显示柱状图的类别 数量
        int barAmount = dataLists.size();
        //设置组间距占比30% 每条柱状图宽度占比 70% /barAmount  柱状图间距占比 0%
        //柱状图组之间的间距
        float groupSpace = 0.3f;
        float barWidth = (1f - groupSpace) / barAmount;
        float barSpace = 0f;
        //设置柱状图宽度
        data.setBarWidth(barWidth);
        //(起始点、柱状图组间距、柱状图之间间距)
        data.groupBars(0f, groupSpace, barSpace);
        barChart.setData(data);

        xAxis.setAxisMinimum(0f);
        xAxis.setAxisMaximum(xValues.size());
        //将X轴的值显示在中央
        xAxis.setCenterAxisLabels(true);
    }

}
