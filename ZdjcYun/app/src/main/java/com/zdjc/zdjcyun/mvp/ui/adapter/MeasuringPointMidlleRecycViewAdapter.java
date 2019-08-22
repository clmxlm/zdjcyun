package com.zdjc.zdjcyun.mvp.ui.adapter;

import android.content.Context;
import android.widget.TextView;

import com.zdjc.zdjcyun.R;
import com.zdjc.zdjcyun.mvp.entity.MeasuringPointEntity;
import com.zdjc.zdjcyun.mvp.entity.VideoEntity;
import com.zdjc.zdjcyun.mvp.ui.adapter.base.BaseRecyclerViewAdapter;
import com.zdjc.zdjcyun.util.SuperViewHolder;


public class MeasuringPointMidlleRecycViewAdapter extends BaseRecyclerViewAdapter<VideoEntity.DataBean.VideoVosBean> {


    public MeasuringPointMidlleRecycViewAdapter(Context context) {
        super(context);
    }

    @Override
    public int getLayoutId() {
        return R.layout.tv;
    }

    @Override
    public void onBindItemHolder(SuperViewHolder holder, int position) {
        fillValue(position, holder);
    }

    private void fillValue(int position, SuperViewHolder viewHolder) {
        TextView tvMonitorPoint = viewHolder.getView(R.id.tv);
        tvMonitorPoint.setText(getDataList().get(position).getMonitorPointNumber());

    }
}
