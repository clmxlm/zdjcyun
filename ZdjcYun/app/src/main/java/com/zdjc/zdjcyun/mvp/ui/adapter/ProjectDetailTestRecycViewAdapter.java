package com.zdjc.zdjcyun.mvp.ui.adapter;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.zdjc.zdjcyun.R;
import com.zdjc.zdjcyun.mvp.ui.adapter.base.BaseRecyclerViewAdapter;
import com.zdjc.zdjcyun.util.ImageLoaderUtils;
import com.zdjc.zdjcyun.util.SuperViewHolder;


public class ProjectDetailTestRecycViewAdapter extends BaseRecyclerViewAdapter<String> {

    private int[] imagesId = {R.mipmap.map,R.mipmap.project,R.mipmap.data,R.mipmap.measuring_point,R.mipmap.warnning,
            R.mipmap.video,R.mipmap.document_management};

    public ProjectDetailTestRecycViewAdapter(Context context) {
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
        TextView textView = viewHolder.getView(R.id.tv);
        ImageView imageView = viewHolder.getView(R.id.iv);
        ImageLoaderUtils.imageDisPlayRes(imagesId[position],imageView);
        textView.setText(getDataList().get(position));
    }
}
