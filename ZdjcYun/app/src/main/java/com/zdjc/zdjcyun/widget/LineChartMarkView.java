package com.zdjc.zdjcyun.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.widget.TextView;

import com.zdjc.zdjcyun.R;
import com.zdjc.zdjcyun.charting.components.MarkerView;
import com.zdjc.zdjcyun.charting.data.Entry;
import com.zdjc.zdjcyun.charting.formatter.IAxisValueFormatter;
import com.zdjc.zdjcyun.charting.highlight.Highlight;
import com.zdjc.zdjcyun.charting.utils.MPPointF;
import com.zdjc.zdjcyun.util.PreferenceUtils;

import java.text.DecimalFormat;

/**
 * 自定义显示触摸值
 */
public class LineChartMarkView extends MarkerView {
 
    private TextView tvDate;
    private TextView tvValue;
    private IAxisValueFormatter xAxisValueFormatter;
    DecimalFormat df = new DecimalFormat("0.000");
    private String yString;
 
    public LineChartMarkView(Context context, IAxisValueFormatter xAxisValueFormatter,String string) {
        super(context, R.layout.layout_markview);
        this.xAxisValueFormatter = xAxisValueFormatter;
        this.yString = string;
        tvDate = findViewById(R.id.tv_date);
        tvValue = findViewById(R.id.tv_value);
    }
 
    @SuppressLint("SetTextI18n")
    @Override
    public void refreshContent(Entry e, Highlight highlight) {
        //展示自定义X轴值 后的X轴内容
        String rateUnit = PreferenceUtils.getString(getContext(),"rateUnit");
        String otherUnit = PreferenceUtils.getString(getContext(),"otherUnit");
        tvDate.setText("时间: "+xAxisValueFormatter.getFormattedValue(e.getX(), null));
        if ("变化速率".equals(yString)){
            tvValue.setText(yString+": " + df.format(e.getY())+ "("+rateUnit+")");
        }else {
            tvValue.setText(yString+": " + df.format(e.getY())+"("+otherUnit+")");
        }

        super.refreshContent(e, highlight);
    }
 
    @Override
    public MPPointF getOffset() {
        return new MPPointF(-(getWidth() / 2), -getHeight());
    }
}
