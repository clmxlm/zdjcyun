package com.zdjc.zdjcyun.mvp.ui.adapter;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.zdjc.zdjcyun.R;
import com.zdjc.zdjcyun.app.Constant;
import com.zdjc.zdjcyun.mvp.entity.ProjecTypeEntity;
import com.zdjc.zdjcyun.mvp.ui.adapter.base.BaseRecyclerViewAdapter;
import com.zdjc.zdjcyun.util.ImageLoaderUtils;
import com.zdjc.zdjcyun.util.SuperViewHolder;


public class ProjectTypeRecycViewAdapter extends BaseRecyclerViewAdapter<ProjecTypeEntity.DataBean> {


    public ProjectTypeRecycViewAdapter(Context context) {
        super(context);
    }

    @Override
    public int getLayoutId() {
        return R.layout.project_type_item;
    }

    @Override
    public void onBindItemHolder(SuperViewHolder holder, int position) {
        fillValue(position, holder);
    }

    private void fillValue(int position, SuperViewHolder viewHolder) {
        TextView textView = viewHolder.getView(R.id.tv);
        TextView textView1 = viewHolder.getView(R.id.tv1);
        ImageView imageView = viewHolder.getView(R.id.iv);
        ImageLoaderUtils.imageDisPlayUrl(Constant.IMAGE_URL+getDataList().get(position).getItemValue(),imageView);
        textView.setText(getDataList().get(position).getItemName());
        textView1.setText("总项目数 "+getDataList().get(position).getProjectTotalCount()+"         "+"异常项目数 "+getDataList().get(position).getProjectErrorCount());
    }
}
