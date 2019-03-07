package com.zdjc.zdjcyun.mvp.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.zdjc.zdjcyun.R;
import com.zdjc.zdjcyun.base.BaseRecyclerAdapter;
import com.zdjc.zdjcyun.base.RecyclerViewHolder;
import com.zdjc.zdjcyun.mvp.entity.DeviceEntity;
import com.zdjc.zdjcyun.mvp.ui.activities.SensorDetailActivity;
import com.zdjc.zdjcyun.util.PreferenceUtils;

import butterknife.Bind;


public class DeviceRecycViewAdapter extends BaseRecyclerAdapter<DeviceEntity.DataBean.TerminalsBean> {

    private Context context;

    public DeviceRecycViewAdapter(Context context) {
        super(context);
        this.context = context;
    }

    @Override
    protected int getContentViewId() {
        return R.layout.fragment_device_item;
    }

    @Override
    protected RecyclerViewHolder getHolder(View v) {
        return new ViewHolder(v);
    }

    @Override
    protected void setView(RecyclerViewHolder o, int position, DeviceEntity.DataBean.TerminalsBean data) {
        ViewHolder holder= (ViewHolder) o;
        holder.mTvDeviceName.setText(data.getTerminalName());
        holder.mTvTerminalStatus.setText("/"+data.getTerminalStatus());
        holder.mTvTerminalNumber.setText(data.getTerminalNumber());
        holder.mTvTerminalModel.setText(data.getTerminalModel());
        holder.mBtnSensorDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PreferenceUtils.putString(context,"terminalNumber",data.getTerminalNumber());
                Intent intent = new Intent(context, SensorDetailActivity.class);
                context.startActivity(intent);
            }
        });
    }

    class ViewHolder extends RecyclerViewHolder {

        @Bind(R.id.tv_device_name)
        TextView mTvDeviceName;
        @Bind(R.id.tv_terminal_status)
        TextView mTvTerminalStatus;
        @Bind(R.id.btn_sensor_detail)
        Button mBtnSensorDetail;
        @Bind(R.id.tv_terminal_number)
        TextView mTvTerminalNumber;
        @Bind(R.id.tv_terminal_model)
        TextView mTvTerminalModel;

        public ViewHolder(View itemView) {
            super(itemView);
        }
    }

}
