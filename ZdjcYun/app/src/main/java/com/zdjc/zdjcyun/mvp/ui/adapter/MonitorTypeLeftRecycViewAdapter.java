package com.zdjc.zdjcyun.mvp.ui.adapter;

import android.content.Context;
import android.widget.TextView;

import com.zdjc.zdjcyun.R;
import com.zdjc.zdjcyun.mvp.entity.MeasuringPointEntity;
import com.zdjc.zdjcyun.mvp.entity.MonitorTypePointNameEntity;
import com.zdjc.zdjcyun.mvp.ui.adapter.base.BaseRecyclerViewAdapter;
import com.zdjc.zdjcyun.util.SuperViewHolder;


public class MonitorTypeLeftRecycViewAdapter extends BaseRecyclerViewAdapter<MonitorTypePointNameEntity.DataBean> {

    private int onclickPosition = 0;
    private Context context;
    public MonitorTypeLeftRecycViewAdapter(Context context) {
        super(context);
        this.context = context;
    }

    @Override
    public int getLayoutId() {
        return R.layout.adpater_item_tv;
    }

    public void getOnclickPosition(int onclickPosition){
        this.onclickPosition = onclickPosition;
    }

    @Override
    public void onBindItemHolder(SuperViewHolder holder, int position) {
        fillValue(position, holder);
    }

    private void fillValue(int position, SuperViewHolder viewHolder) {
        TextView tvMonitorType = viewHolder.getView(R.id.tv);
        if (onclickPosition == position){
            tvMonitorType.setBackgroundColor(context.getResources().getColor(R.color.white));
        }else {
            tvMonitorType.setBackgroundColor(context.getResources().getColor(R.color.unonclick_color));
        }
        tvMonitorType.setText(getDataList().get(position).getMonitorTypeName());
    }
}
