package com.zdjc.zdjcyun.mvp.ui.adapter;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.zdjc.zdjcyun.R;
import com.zdjc.zdjcyun.app.Constant;
import com.zdjc.zdjcyun.mvp.entity.ProjecTypeBean;
import com.zdjc.zdjcyun.mvp.ui.adapter.base.BaseRecyclerViewAdapter;
import com.zdjc.zdjcyun.util.ImageLoaderUtils;
import com.zdjc.zdjcyun.util.SuperViewHolder;


public class ProjectTypeRecycViewAdapter extends BaseRecyclerViewAdapter<ProjecTypeBean> {

    private Context context;

    public ProjectTypeRecycViewAdapter(Context context) {
        super(context);
        this.context = context;
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
        TextView textView = viewHolder.getView(R.id.tv);
        ImageView imageView = viewHolder.getView(R.id.iv);
        ImageLoaderUtils.imageDisPlayUrl(Constant.IMAGE_URL+getDataList().get(position).getImageUrl(),imageView);
        textView.setText(getDataList().get(position).getProjectTypeName());
    }
}
