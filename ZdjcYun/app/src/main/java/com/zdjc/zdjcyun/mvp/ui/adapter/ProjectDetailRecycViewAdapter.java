package com.zdjc.zdjcyun.mvp.ui.adapter;

import android.content.Context;
import android.widget.TextView;

import com.zdjc.zdjcyun.R;
import com.zdjc.zdjcyun.mvp.entity.ProjectDetailEntity;
import com.zdjc.zdjcyun.mvp.ui.adapter.base.BaseRecyclerViewAdapter;
import com.zdjc.zdjcyun.util.SuperViewHolder;

import java.text.DecimalFormat;


public class ProjectDetailRecycViewAdapter extends BaseRecyclerViewAdapter<ProjectDetailEntity.DataBean.SensorListBean> {

    private Context context;
    private int  selectedPosition;

    public ProjectDetailRecycViewAdapter(Context context) {
        super(context);
        this.context = context;
    }

    @Override
    public int getLayoutId() {
        return R.layout.recycview_detail_item;
    }

    @Override
    public void onBindItemHolder(SuperViewHolder holder, int position) {
        fillValue(position, holder);
    }

    public  void getPosition(int selectedPosition){
        this.selectedPosition = selectedPosition;
    }
    private void fillValue(int position, SuperViewHolder viewHolder) {
        TextView tvMeasuringPoint = viewHolder.getView(R.id.tv_measuring_point);
        TextView tvCurrentValue = viewHolder.getView(R.id.tv_current_value);
        TextView tvChangeValue = viewHolder.getView(R.id.tv_change_value);
        tvMeasuringPoint.setText(mDataList.get(position).getMonitorPoint());
        tvCurrentValue.setText(formatFloatNumber(mDataList.get(position).getTotalLaserChange()));
        tvChangeValue.setText(formatFloatNumber(mDataList.get(position).getSpeedChange()));
        if (selectedPosition==position){
            tvMeasuringPoint.setTextColor(context.getResources().getColor(R.color.timepicker_toolbar_bg));
            tvCurrentValue.setTextColor(context.getResources().getColor(R.color.timepicker_toolbar_bg));
            tvChangeValue.setTextColor(context.getResources().getColor(R.color.timepicker_toolbar_bg));
        }else {
            tvMeasuringPoint.setTextColor(context.getResources().getColor(R.color.white));
            tvCurrentValue.setTextColor(context.getResources().getColor(R.color.white));
            tvChangeValue.setTextColor(context.getResources().getColor(R.color.white));
        }

    }
    private   String formatFloatNumber(double value) {
        DecimalFormat df = new DecimalFormat("0.000");
        return df.format(value);
    }
}
