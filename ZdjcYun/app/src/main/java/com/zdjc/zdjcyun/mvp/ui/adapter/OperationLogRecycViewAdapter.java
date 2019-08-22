package com.zdjc.zdjcyun.mvp.ui.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.zdjc.zdjcyun.R;
import com.zdjc.zdjcyun.base.BaseRecyclerAdapter;
import com.zdjc.zdjcyun.base.RecyclerViewHolder;
import com.zdjc.zdjcyun.mvp.entity.AlarmCountEntity;
import com.zdjc.zdjcyun.mvp.entity.OperationLogEntity;

import butterknife.Bind;


public class OperationLogRecycViewAdapter extends BaseRecyclerAdapter<OperationLogEntity.DataBean.OperatedBean> {

    private Context context;

    public OperationLogRecycViewAdapter(Context context) {
        super(context);
        this.context = context;
    }

    @Override
    protected int getContentViewId() {
        return R.layout.operation_log_item;
    }

    @Override
    protected RecyclerViewHolder getHolder(View v) {
        return new ViewHolder(v);
    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void setView(RecyclerViewHolder o, int position, OperationLogEntity.DataBean.OperatedBean data) {
        ViewHolder holder= (ViewHolder) o;

        holder.mTvSerialNum.setText("序号   "+position+"");
        holder.mTvUser.setText("用户名   "+data.getUserName());
        holder.mTvTime.setText("时间   "+data.getCreateDate());
        holder.mTvOperation.setText("操作   "+data.getOperation());
        holder.mTvDescribe.setText("描述   "+data.getDescription());
    }

    class ViewHolder extends RecyclerViewHolder {
        @Bind(R.id.tv_serial_num)
        TextView mTvSerialNum;
        @Bind(R.id.tv_user)
        TextView mTvUser;
        @Bind(R.id.tv_time)
        TextView mTvTime;
        @Bind(R.id.tv_operation)
        TextView mTvOperation;
        @Bind(R.id.tv_describe)
        TextView mTvDescribe;


        public ViewHolder(View itemView) {
            super(itemView);
        }
    }


}
