package com.zdjc.zdjcyun.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.nfc.Tag;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.zdjc.zdjcyun.R;
import com.zdjc.zdjcyun.charting.charts.Chart;
import com.zdjc.zdjcyun.charting.charts.LineChart;
import com.zdjc.zdjcyun.charting.components.MarkerView;
import com.zdjc.zdjcyun.charting.data.Entry;
import com.zdjc.zdjcyun.charting.data.LineData;
import com.zdjc.zdjcyun.charting.formatter.IAxisValueFormatter;
import com.zdjc.zdjcyun.charting.highlight.Highlight;
import com.zdjc.zdjcyun.charting.interfaces.datasets.ILineDataSet;
import com.zdjc.zdjcyun.charting.utils.MPPointF;
import com.zdjc.zdjcyun.mvp.ui.adapter.MarkViewRecycViewAdapter;

import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * 自定义显示触摸值
 */
public class LineChartsMarkView extends MarkerView {

    private final MarkViewRecycViewAdapter markViewRecycViewAdapter;
    private TextView tvDate;
    private Context context;
    private int titleTag=-1;
    private IAxisValueFormatter xAxisValueFormatter;
    DecimalFormat df = new DecimalFormat("0.000");

    public LineChartsMarkView(Context context, IAxisValueFormatter xAxisValueFormatter) {
        super(context, R.layout.layout_markviews);
        this.context = context;
        this.xAxisValueFormatter = xAxisValueFormatter;
        tvDate = (TextView) findViewById(R.id.tv_date);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycleView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        markViewRecycViewAdapter = new MarkViewRecycViewAdapter(context);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(markViewRecycViewAdapter);
    }

    public void getTitleName(int tag){
        this.titleTag = tag;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void refreshContent(Entry e, Highlight highlight) {
        Chart chart = getChartView();
        if (chart instanceof LineChart) {
            LineData lineData = ((LineChart) chart).getLineData();
            //获取到图表中的所有曲线
            ArrayList<ILineDataSet> dataSetList = (ArrayList<ILineDataSet>) lineData.getDataSets();
            markViewRecycViewAdapter.setList(dataSetList);
            markViewRecycViewAdapter.setEntry(e);
            if (titleTag==10){
                tvDate.setText("深度m: "+xAxisValueFormatter.getFormattedValue(e.getX(), null));
            }else {
                tvDate.setText("时间: "+xAxisValueFormatter.getFormattedValue(e.getX(), null));
            }
        }
        super.refreshContent(e, highlight);
    }

    @Override
    public MPPointF getOffset() {
        return new MPPointF(-(getWidth() / 2), -getHeight());
    }

}
