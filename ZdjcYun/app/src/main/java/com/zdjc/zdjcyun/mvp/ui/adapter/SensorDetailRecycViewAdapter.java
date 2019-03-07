package com.zdjc.zdjcyun.mvp.ui.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.zdjc.zdjcyun.R;
import com.zdjc.zdjcyun.base.BaseRecyclerAdapter;
import com.zdjc.zdjcyun.base.RecyclerViewHolder;
import com.zdjc.zdjcyun.mvp.entity.SensorEntity;

import butterknife.Bind;


public class SensorDetailRecycViewAdapter extends BaseRecyclerAdapter<SensorEntity.DataBean> {

    private Context context;

    public SensorDetailRecycViewAdapter(Context context) {
        super(context);
        this.context = context;
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_sensor_item;
    }

    @Override
    protected RecyclerViewHolder getHolder(View v) {
        return new ViewHolder(v);
    }

    @Override
    protected void setView(RecyclerViewHolder o, int position, SensorEntity.DataBean data) {
        ViewHolder holder= (ViewHolder) o;
        holder.mTvSensorName.setText(data.getSensorName());
        holder.mTvTerminalStatus.setText("/"+data.getSensorStatus());
        holder.mTvSensorNumber.setText(data.getSensorNumber());
        holder.mTvSensorModel.setText(data.getSensorModel());
        holder.mTvRangeNumber.setText(data.getSensorRange());
        holder.mTvPrecisionModel.setText(data.getSensorAccuracy());
    }

    class ViewHolder extends RecyclerViewHolder {

        @Bind(R.id.tv_sensor_name)
        TextView mTvSensorName;
        @Bind(R.id.tv_terminal_status)
        TextView mTvTerminalStatus;
        @Bind(R.id.tv_sensor_number)
        TextView mTvSensorNumber;
        @Bind(R.id.tv_sensor_model)
        TextView mTvSensorModel;
        @Bind(R.id.tv_range_number)
        TextView mTvRangeNumber;
        @Bind(R.id.tv_precision_model)
        TextView mTvPrecisionModel;

        public ViewHolder(View itemView) {
            super(itemView);
        }
    }

}
