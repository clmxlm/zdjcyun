package com.zdjc.zdjcyun.mvp.ui.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.blankj.utilcode.utils.LogUtils;
import com.zdjc.zdjcyun.R;
import com.zdjc.zdjcyun.mvp.entity.AlarmDetailEntity;
import com.zdjc.zdjcyun.mvp.entity.DocumentEntity;
import com.zdjc.zdjcyun.mvp.ui.adapter.base.BaseRecyclerViewAdapter;
import com.zdjc.zdjcyun.util.SuperViewHolder;

import java.util.List;


public class DocumentsDetailRecycViewAdapter extends BaseRecyclerViewAdapter<DocumentEntity.DataBean> {

    private Context context;

    public DocumentsDetailRecycViewAdapter(Context context) {
        super(context);
        this.context = context;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_document_item;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindItemHolder(SuperViewHolder holder, int position) {
        TextView mTvDocumentName = holder.getView(R.id.tv_document_name);
        TextView mTvDocumentType = holder.getView(R.id.tv_document_type);
        TextView mTvManager = holder.getView(R.id.tv_manager);
        mTvDocumentName.setText(getDataList().get(position).getName());
        mTvDocumentType.setText(getDataList().get(position).getType());
        mTvManager.setText(getDataList().get(position).getChargeman());

        holder.getView(R.id.btn_download).setOnClickListener(v -> mOnItemClickListener.onItemClick(v,position));
    }

}
