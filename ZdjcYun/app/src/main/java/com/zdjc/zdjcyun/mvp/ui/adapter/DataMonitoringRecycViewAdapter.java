package com.zdjc.zdjcyun.mvp.ui.adapter;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.zdjc.zdjcyun.R;
import com.zdjc.zdjcyun.app.Constant;
import com.zdjc.zdjcyun.charting.charts.LineChart;
import com.zdjc.zdjcyun.mvp.entity.ProjecTypeEntity;
import com.zdjc.zdjcyun.mvp.ui.adapter.base.BaseRecyclerViewAdapter;
import com.zdjc.zdjcyun.util.ImageLoaderUtils;
import com.zdjc.zdjcyun.util.SuperViewHolder;


public class DataMonitoringRecycViewAdapter extends BaseRecyclerViewAdapter<Integer> {

    public DataMonitoringRecycViewAdapter(Context context) {
        super(context);
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_data_monitoring_item;
    }

    @Override
    public void onBindItemHolder(SuperViewHolder holder, int position) {
        fillValue(position, holder);
    }

    private void fillValue(int position, SuperViewHolder viewHolder) {

        TextView tvCurrentValue = viewHolder.getView(R.id.tv_current_value);
        TextView tvTotalValue = viewHolder.getView(R.id.tv_total_value);
        TextView tvSpeedValue = viewHolder.getView(R.id.tv_speed_value);
        TextView tvSingleValue = viewHolder.getView(R.id.tv_single_value);
        TextView tvMaxValue = viewHolder.getView(R.id.tv_max_value);
        TextView tvMinValue = viewHolder.getView(R.id.tv_min_value);
        TextView tvFristLevelValue = viewHolder.getView(R.id.tv_first_level_value);
        TextView tvTwoLevelValue = viewHolder.getView(R.id.tv_two_level_value);
        TextView tvThreeLevelValue = viewHolder.getView(R.id.tv_three_level_value);
        ImageView ivZoom = viewHolder.getView(R.id.iv_zoom);
        LineChart lineChart = viewHolder.getView(R.id.line_chart_adapter);

        holder.getView(R.id.iv_zoom).setOnClickListener(v -> mOnItemClickListener.onItemClick(v,position));
    }
}
