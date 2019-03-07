package com.zdjc.zdjcyun.mvp.ui.adapter;

import android.content.Context;
import android.widget.TextView;

import com.zdjc.zdjcyun.R;
import com.zdjc.zdjcyun.mvp.entity.MeasuringPointEntity;
import com.zdjc.zdjcyun.mvp.ui.adapter.base.BaseRecyclerViewAdapter;
import com.zdjc.zdjcyun.util.SuperViewHolder;


public class MeasuringPointLeftRecycViewAdapter extends BaseRecyclerViewAdapter<MeasuringPointEntity.DataBean> {

    private Context context;
    private int leftPosition=0;

    public MeasuringPointLeftRecycViewAdapter(Context context) {
        super(context);
        this.context = context;
    }

    public void getPosition(int position){
        leftPosition = position;
    }

    @Override
    public int getLayoutId() {
        return R.layout.measuring_point_left_item;
    }

    @Override
    public void onBindItemHolder(SuperViewHolder holder, int position) {
        fillValue(position, holder);
    }

    private void fillValue(int position, SuperViewHolder viewHolder) {
        TextView tvMonitorTypeName = viewHolder.getView(R.id.tv_monitor_type_name);
        tvMonitorTypeName.setText(getDataList().get(position).getMonitorTypeName());
        if (position==leftPosition){
            tvMonitorTypeName.setBackgroundColor(context.getResources().getColor(R.color.fragment_background));
        }else {
            tvMonitorTypeName.setBackgroundColor(context.getResources().getColor(R.color.white));
        }
    }
}
