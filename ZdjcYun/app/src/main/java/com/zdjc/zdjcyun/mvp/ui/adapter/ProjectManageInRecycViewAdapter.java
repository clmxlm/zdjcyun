package com.zdjc.zdjcyun.mvp.ui.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zdjc.zdjcyun.R;
import com.zdjc.zdjcyun.mvp.entity.ProjectManageEntity;
import com.zdjc.zdjcyun.mvp.ui.activities.ProjectManageDetailActivity;
import com.zdjc.zdjcyun.mvp.ui.adapter.base.BaseRecyclerViewAdapter;
import com.zdjc.zdjcyun.util.PreferenceUtils;
import com.zdjc.zdjcyun.util.SuperViewHolder;


public class ProjectManageInRecycViewAdapter extends BaseRecyclerViewAdapter<ProjectManageEntity.DataBean.ListBean> {

    private Activity context;

    public ProjectManageInRecycViewAdapter(Activity context) {
        super(context);
        this.context = context;
    }

    @Override
    public int getLayoutId() {
        return R.layout.common_layout_item;
    }

    @Override
    public void onBindItemHolder(SuperViewHolder holder, int position) {
        fillValue(position, holder);
    }

    @SuppressLint("SetTextI18n")
    private void fillValue(int position, SuperViewHolder viewHolder) {

        TextView mTvProjectK = viewHolder.getView(R.id.tv_project_k);
        TextView mTvStateNormal = viewHolder.getView(R.id.tv_state_normal);
        ImageView mIvStateNormal = viewHolder.getView(R.id.iv_state_normal);
        RelativeLayout mRlProjectManage = viewHolder.getView(R.id.rl_project_manage);
        TextView mTvAlarmOne = viewHolder.getView(R.id.tv_alarm_one);
        TextView mTvAlarmTwo = viewHolder.getView(R.id.tv_alarm_two);
        TextView mTvAlarmThree = viewHolder.getView(R.id.tv_alarm_three);
        TextView mTvCommon = viewHolder.getView(R.id.tv_common);
        TextView mTvCommonName = viewHolder.getView(R.id.tv_common_name);
        TextView mTvTimeName = viewHolder.getView(R.id.tv_time_name);
        TextView mTvTime = viewHolder.getView(R.id.tv_time);
        TextView tvCommonNameBottom = viewHolder.getView(R.id.tv_common_name_bottom);
        TextView tvCommonBottom = viewHolder.getView(R.id.tv_common_bottom);
        LinearLayout llProjectManage = viewHolder.getView(R.id.ll_project_manage);

        mTvProjectK.setText("K段: "+getDataList().get(position).getSectorName());
        if ("正常".equals(getDataList().get(position).getErrorStatus())){
            mTvStateNormal.setText("监测正常");
            mTvStateNormal.setTextColor(context.getResources().getColor(R.color.status_normal));
            mIvStateNormal.setBackgroundResource(R.mipmap.normal);
        }else if ("异常".equals(getDataList().get(position).getErrorStatus())){
            mTvStateNormal.setText("监测异常");
            mTvStateNormal.setTextColor(context.getResources().getColor(R.color.status_abnormal));
            mIvStateNormal.setBackgroundResource(R.mipmap.abnormal);
        }

        mTvAlarmOne.setText(getDataList().get(position).getLevel1()+"");
        mTvAlarmTwo.setText(getDataList().get(position).getLevel2()+"");
        mTvAlarmThree.setText(getDataList().get(position).getLevel3()+"");
        mTvCommon.setText(getDataList().get(position).getTError()+"");
        mTvCommonName.setText("设备告警(个)");
        mTvTimeName.setText("起止时间");
        mTvTime.setText(getDataList().get(position).getSectorBeginTime()+"至"+getDataList().get(position).getSectorEndTime());
        tvCommonNameBottom.setText("地址");
        tvCommonBottom.setText(getDataList().get(position).getSectorAddress());

        llProjectManage.setOnClickListener(v -> {
            Intent intent = new Intent(context, ProjectManageDetailActivity.class);
            intent.putExtra("sectorName",getDataList().get(position).getSectorName());
            context.startActivity(intent);
            PreferenceUtils.putInt(context,"sectorId",getDataList().get(position).getSectorId());
        });
    }
}
