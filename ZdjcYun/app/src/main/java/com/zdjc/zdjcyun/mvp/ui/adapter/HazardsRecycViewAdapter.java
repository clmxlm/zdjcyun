package com.zdjc.zdjcyun.mvp.ui.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.widget.TextView;

import com.zdjc.zdjcyun.R;
import com.zdjc.zdjcyun.mvp.entity.HazardsEntity;
import com.zdjc.zdjcyun.mvp.ui.adapter.base.BaseRecyclerViewAdapter;
import com.zdjc.zdjcyun.util.SuperViewHolder;


public class HazardsRecycViewAdapter extends BaseRecyclerViewAdapter<HazardsEntity.DataBean> {

    private Context context;

    public HazardsRecycViewAdapter(Context context) {
        super(context);
        this.context = context;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_hazards_item;
    }

    @Override
    public void onBindItemHolder(SuperViewHolder holder, int position) {
        fillValue(position, holder);
    }


    @SuppressLint("SetTextI18n")
    private void fillValue(int position, SuperViewHolder viewHolder) {
        TextView tvActivityName = viewHolder.getView(R.id.tv_activity_name);
        TextView tvRiskName = viewHolder.getView(R.id.tv_risk_name);
        TextView tvRiskReason = viewHolder.getView(R.id.tv_risk_reason);
        TextView tvRiskAssessment = viewHolder.getView(R.id.tv_risk_assessment);
        tvActivityName.setText(mDataList.get(position).getJobActivity());
        tvRiskName.setText(mDataList.get(position).getDangerousSource());
        tvRiskReason.setText(mDataList.get(position).getRiskResult());
        tvRiskAssessment.setText(mDataList.get(position).getRaowcL()+"");
    }

}
