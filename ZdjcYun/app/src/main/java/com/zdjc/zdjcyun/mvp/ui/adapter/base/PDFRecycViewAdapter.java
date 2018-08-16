package com.zdjc.zdjcyun.mvp.ui.adapter.base;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.widget.TextView;

import com.zdjc.zdjcyun.R;
import com.zdjc.zdjcyun.mvp.entity.BeginEntity;
import com.zdjc.zdjcyun.util.SuperViewHolder;


public class PDFRecycViewAdapter extends BaseRecyclerViewAdapter<BeginEntity.DataBean.FilesBean> {

    private Context context;

    public PDFRecycViewAdapter(Context context) {
        super(context);
        this.context = context;
    }

    @Override
    public int getLayoutId() {
        return R.layout.pdf;
    }

    @Override
    public void onBindItemHolder(SuperViewHolder holder, int position) {
        fillValue(position, holder);
    }

    private void fillValue(int position, SuperViewHolder viewHolder) {
        TextView btnLogin = viewHolder.getView(R.id.text);
        btnLogin.getPaint().setColor(Color.BLUE);
        btnLogin.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG); //下划线
        btnLogin.getPaint().setAntiAlias(true);//抗锯齿
        btnLogin.setText(getDataList().get(position).getFileName());
    }
}
