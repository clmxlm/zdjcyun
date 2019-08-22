package com.zdjc.zdjcyun.mvp.ui.adapter;

import android.content.Context;
import android.widget.TextView;

import com.zdjc.zdjcyun.R;
import com.zdjc.zdjcyun.mvp.entity.MeasuringPointEntity;
import com.zdjc.zdjcyun.mvp.ui.adapter.base.BaseRecyclerViewAdapter;
import com.zdjc.zdjcyun.util.SuperViewHolder;


public class MeasuringPointRightRecycViewAdapter extends BaseRecyclerViewAdapter<String> {


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
        String monitorPoint = "测点名称:"+getDataList().get(position);
        tvMonitorPoint.setText(monitorPoint);
    }
}
