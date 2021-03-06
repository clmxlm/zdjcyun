package com.zdjc.zdjcyun.mvp.ui.adapter.base;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.jdsjlzx.interfaces.OnItemClickListener;
import com.zdjc.zdjcyun.util.SuperViewHolder;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 封装adapter（注意：仅供参考，根据需要选择使用demo中提供的封装adapter）
 * @param <T>
 */
public abstract class BaseRecyclerViewAdapter<T> extends RecyclerView.Adapter<SuperViewHolder> {
    protected Context mContext;
    protected OnItemClickListener mOnItemClickListener;
    protected ArrayList<T> mDataList = new ArrayList<>();
    protected SuperViewHolder holder;

    public BaseRecyclerViewAdapter(Context context) {
        mContext = context;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    @Override
    public SuperViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(mContext).inflate(getLayoutId(), parent, false);
        holder=new SuperViewHolder(itemView);
        setItemOnClickEvent(holder);
        return holder;
    }


    private void setItemOnClickEvent(final RecyclerView.ViewHolder holder) {
        if (mOnItemClickListener != null) {
            holder.itemView.setOnClickListener(v -> mOnItemClickListener.onItemClick(v, holder.getLayoutPosition()));
        }
    }

    @Override
    public void onBindViewHolder(SuperViewHolder holder, int position) {
        onBindItemHolder(holder, position);
    }

    //局部刷新关键：带payload的这个onBindViewHolder方法必须实现
    @Override
    public void onBindViewHolder(SuperViewHolder holder, int position, List<Object> payloads) {
        if (payloads.isEmpty()) {
            onBindViewHolder(holder, position);
        } else {
            onBindItemHolder(holder, position, payloads);
        }

    }

    public abstract int getLayoutId();

    public abstract void onBindItemHolder(SuperViewHolder holder, int position);

    public void onBindItemHolder(SuperViewHolder holder, int position, List<Object> payloads){

    }

    @Override
    public int getItemCount() {
        return mDataList==null?0:mDataList.size();
    }

    public ArrayList<T> getDataList() {
        return mDataList;
    }

    public void setDataList(Collection<T> list) {
        this.mDataList.clear();
        this.mDataList.addAll(list);
        notifyDataSetChanged();
    }

    public void addAll(Collection<T> list) {
        int lastIndex = this.mDataList.size();
        if (this.mDataList.addAll(list)) {
            notifyItemRangeInserted(lastIndex, list.size());
        }
    }

    public void add(int position, T item) {
        mDataList.add(position, item);
        notifyItemInserted(position);
    }

    public void remove(int position) {
        this.mDataList.remove(position);
        notifyItemRemoved(position);
        // 如果移除的是最后一个，忽略
        if(position != (getDataList().size())){
            notifyItemRangeChanged(position,this.mDataList.size()-position);
        }
    }

    public void clear() {
        mDataList.clear();
        notifyDataSetChanged();
    }
}
