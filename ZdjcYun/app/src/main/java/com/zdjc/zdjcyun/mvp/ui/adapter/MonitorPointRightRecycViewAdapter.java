package com.zdjc.zdjcyun.mvp.ui.adapter;

import android.content.Context;
import android.widget.TextView;

import com.zdjc.zdjcyun.R;
import com.zdjc.zdjcyun.mvp.entity.MonitorTypePointNameEntity;
import com.zdjc.zdjcyun.mvp.ui.adapter.base.BaseRecyclerViewAdapter;
import com.zdjc.zdjcyun.util.SuperViewHolder;


public class MonitorPointRightRecycViewAdapter extends BaseRecyclerViewAdapter<String> {

    private Context context;
    public MonitorPointRightRecycViewAdapter(Context context) {
        super(context);
        this.context = context;
    }

    @Override
    public int getLayoutId() {
        return R.layout.adpater_item_tv;
    }

    @Override
    public void onBindItemHolder(SuperViewHolder holder, int position) {
        fillValue(position, holder);
    }

    private void fillValue(int position, SuperViewHolder viewHolder) {
        TextView tvMonitorType = viewHolder.getView(R.id.tv);
        tvMonitorType.setText(getDataList().get(position));
        tvMonitorType.setBackground(context.getResources().getDrawable(R.drawable.select_tv_lable));
    }
}
