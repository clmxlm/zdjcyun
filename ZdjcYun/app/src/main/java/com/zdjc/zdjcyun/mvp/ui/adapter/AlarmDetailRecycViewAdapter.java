package com.zdjc.zdjcyun.mvp.ui.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.widget.Button;
import android.widget.TextView;

import com.blankj.utilcode.utils.LogUtils;
import com.zdjc.zdjcyun.R;
import com.zdjc.zdjcyun.mvp.entity.AlarmDetailEntity;
import com.zdjc.zdjcyun.mvp.ui.adapter.base.BaseRecyclerViewAdapter;
import com.zdjc.zdjcyun.util.SuperViewHolder;

import java.util.List;


public class AlarmDetailRecycViewAdapter extends BaseRecyclerViewAdapter<AlarmDetailEntity.DataBean.AlarmInfoBean> {

    private Context context;

    public AlarmDetailRecycViewAdapter(Context context) {
        super(context);
        this.context = context;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_alarm_detail_item;
    }
    /**
     * 局部刷新关键：带payload的这个onBindViewHolder方法必须实现
     */
    @Override
    public void onBindViewHolder(SuperViewHolder holder, int position, List<Object> payloads) {
        if (payloads.isEmpty()) {
            onBindViewHolder(holder, position);
        } else {
            //要刷新的位置和内容
            TextView mTvStatus = holder.getView(R.id.tv_status);
            mTvStatus.setText("已确认");
            mTvStatus.setTextColor(context.getResources().getColor(R.color.status_normal));

            Button mBtnConfirmAlarm = holder.getView(R.id.btn_confirm_alarm);
            mBtnConfirmAlarm.setBackground(context.getResources().getDrawable(R.drawable.background_unalarm_btn));
            mBtnConfirmAlarm.setText("已确认");
        }
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindItemHolder(SuperViewHolder holder, int position) {
        TextView mTvAlarmName = holder.getView(R.id.tv_alarm_name);
        TextView mTvAlarmSensorNumber = holder.getView(R.id.tv_alarm_sensor_number);
        TextView mTvAlarmNumber = holder.getView(R.id.tv_alarm_number);
        TextView mTvAlarmTime = holder.getView(R.id.tv_alarm_time);
        TextView mTvAlarmType = holder.getView(R.id.tv_alarm_type);
        TextView mTvAlarmContent = holder.getView(R.id.tv_alarm_content);
        TextView mTvStatus = holder.getView(R.id.tv_status);
        Button mBtnConfirmAlarm = holder.getView(R.id.btn_confirm_alarm);

        mTvAlarmName.setText( mDataList.get(position).getMonitorPointNumber() + "/" + "告警等级 " +  mDataList.get(position).getAlarmLevel());
        mTvAlarmSensorNumber.setText(mDataList.get(position).getSensorNumber());
        mTvAlarmNumber.setText(mDataList.get(position).getTerminalNumber());
        mTvAlarmTime.setText(mDataList.get(position).getCreateTime());
        mTvAlarmType.setText(mDataList.get(position).getAlarmType());
        mTvAlarmContent.setText(mDataList.get(position).getAlarmContext());
        LogUtils.i("zj","num="+mDataList.get(position).getTerminalNumber());
        if ("未确认".equals(mDataList.get(position).getAlarmStatus())){
            mTvStatus.setText(mDataList.get(position).getAlarmStatus());
            mTvStatus.setTextColor(context.getResources().getColor(R.color.status_abnormal));
            mBtnConfirmAlarm.setBackground(context.getResources().getDrawable(R.drawable.background_alarm_btn));
            mBtnConfirmAlarm.setText("未确认");
        }else if ("已确认".equals(mDataList.get(position).getAlarmStatus())){
            mTvStatus.setText(mDataList.get(position).getAlarmStatus());
            mTvStatus.setTextColor(context.getResources().getColor(R.color.status_normal));
            mBtnConfirmAlarm.setBackground(context.getResources().getDrawable(R.drawable.background_unalarm_btn));
            mBtnConfirmAlarm.setText("已确认");
        }

        //确认点击事件
        mBtnConfirmAlarm.setOnClickListener(v -> {
            if ("未确认".equals(mDataList.get(position).getAlarmStatus())){
                mOnItemClickListener.onItemClick(v,position);
            }
        });
    }

}
