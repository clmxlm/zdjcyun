package com.zdjc.zdjcyun.mvp.ui.adapter;

import android.content.Context;
import android.widget.TextView;

import com.zdjc.zdjcyun.R;
import com.zdjc.zdjcyun.mvp.entity.MeasuringPointEntity;
import com.zdjc.zdjcyun.mvp.ui.adapter.base.BaseRecyclerViewAdapter;
import com.zdjc.zdjcyun.util.SuperViewHolder;


public class MeasuringPointRightRecycViewAdapter extends BaseRecyclerViewAdapter<MeasuringPointEntity.DataBean.DetectionsBean> {


    public MeasuringPointRightRecycViewAdapter(Context context) {
        super(context);
    }

    @Override
    public int getLayoutId() {
        return R.layout.measuring_point_right_item;
    }

    @Override
    public void onBindItemHolder(SuperViewHolder holder, int position) {
        fillValue(position, holder);
    }

    private void fillValue(int position, SuperViewHolder viewHolder) {
        TextView tvMonitorPoint = viewHolder.getView(R.id.tv_monitorPoint);
        TextView tvSmuNumber = viewHolder.getView(R.id.tv_smuNumber);
        TextView tvSmuChannel = viewHolder.getView(R.id.tv_smuChannel);
        TextView tvSensorLongitude = viewHolder.getView(R.id.tv_sensorLongitude);
        TextView tvSensorLatitude = viewHolder.getView(R.id.tv_sensorLatitude);
        TextView tPointStatus = viewHolder.getView(R.id.tv_point_status);

        String monitorPoint = "测点名称:"+getDataList().get(position).getMonitorPoint();
        String smuNumber = "传感器编号:"+getDataList().get(position).getSmuNumber();
        String smuChannel = "传感器通道:"+getDataList().get(position).getSmuChannel();
        String sensorLongitude = "经度:"+getDataList().get(position).getSensorLongitude();
        String sensorLatitude = "纬度:"+getDataList().get(position).getSensorLatitude();

        tvMonitorPoint.setText(monitorPoint);
        tvSmuNumber.setText(smuNumber);
        tvSmuChannel.setText(smuChannel);
        tvSensorLongitude.setText(sensorLongitude);
        tvSensorLatitude.setText(sensorLatitude);
        tPointStatus.setText("状态:正常");
    }
}
