package com.zdjc.zdjcyun.mvp.ui.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.zdjc.zdjcyun.R;
import com.zdjc.zdjcyun.base.BaseRecyclerAdapter;
import com.zdjc.zdjcyun.base.RecyclerViewHolder;
import com.zdjc.zdjcyun.mvp.entity.AlarmCountEntity;

import butterknife.Bind;


public class WarningRecycViewAdapter extends BaseRecyclerAdapter<AlarmCountEntity.DataBean.AlarmCountsBean> {

    private Context context;
    private boolean or = false;

    public WarningRecycViewAdapter(Context context) {
        super(context);
        this.context = context;
    }

    @Override
    protected int getContentViewId() {
        return R.layout.warning_item;
    }

    @Override
    protected RecyclerViewHolder getHolder(View v) {
        return new ViewHolder(v);
    }

    @Override
    protected void setView(RecyclerViewHolder o, int position, AlarmCountEntity.DataBean.AlarmCountsBean data) {
        ViewHolder holder= (ViewHolder) o;

        holder.mTvProjectName.setText(data.getProjectName()+"-"+data.getSectorName());
        holder.mTvAlarmOne.setText(data.getLevel1()+"");
        holder.mTvAlarmTwo.setText(data.getLevel2()+"");
        holder.mTvAlarmThree.setText(data.getLevel3()+"");
        holder.mTvAlarmAll.setText(data.getAlarmTotal()+"");
        holder.mTvTerminalError.setText(data.getTError()+"");
        holder.mTvSensorError.setText(data.getSError()+"");
    }

    class ViewHolder extends RecyclerViewHolder {
        @Bind(R.id.tv_project_name)
        TextView mTvProjectName;
        @Bind(R.id.tv_alarm_one)
        TextView mTvAlarmOne;
        @Bind(R.id.tv_alarm_two)
        TextView mTvAlarmTwo;
        @Bind(R.id.tv_alarm_three)
        TextView mTvAlarmThree;
        @Bind(R.id.tv_alarm_all)
        TextView mTvAlarmAll;
        @Bind(R.id.tv_terminal_error)
        TextView mTvTerminalError;
        @Bind(R.id.tv_sensor_error)
        TextView mTvSensorError;

        public ViewHolder(View itemView) {
            super(itemView);
        }
    }


}
