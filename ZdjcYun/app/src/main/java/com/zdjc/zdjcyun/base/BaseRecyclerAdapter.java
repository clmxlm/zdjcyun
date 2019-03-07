/**
 * @Title: BaseDyeAdapter.java
 * @Copyright: XXX Co., Ltd. Copyright YYYY-YYYY,  All rights reserved
 * Author:: Dye-Duanxinyuan
 * 2015年1月21日 下午2:49:29   *  V3.1.0
 */
package com.zdjc.zdjcyun.base;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;


/**
 * 适配器基类，加入了ButterKnife
 */
public abstract class BaseRecyclerAdapter<T> extends RecyclerView.Adapter<RecyclerViewHolder> implements IAdapter<T> {

    private ArrayList<T> list = new ArrayList<>();
    protected Context context;
    protected LayoutInflater mInflater;

    protected OnItemClickListener onItemClickListener;
    protected OnItemLongClickListener onItemLongClickListener;

    public BaseRecyclerAdapter(Context context) {
        this.context = context;
        mInflater = LayoutInflater.from(context);
    }

    public BaseRecyclerAdapter(Context context, ArrayList<T> list) {
        this.context = context;
        this.list = list;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = mInflater.inflate(getContentViewId(), parent, false);
        return getHolder(view);
    }

    @Override
    public void onBindViewHolder(final RecyclerViewHolder holder, final int position) {
        if (null != onItemClickListener) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onItemClick(holder.itemView, position);
                }
            });
        }
        if (null != onItemLongClickListener) {
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    onItemLongClickListener.onItemLongClick(view, position);
                    return true;
                }
            });
        }
        setView(holder, position, getListItem(position));
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        if (null != list && list.size() > 0) {
            return list.size();
        } else {
            return 0;
        }
    }

    /**
     * 返回适配器条目Layout的Id
     */
    protected abstract int getContentViewId();

    protected abstract RecyclerViewHolder getHolder(View v);

    /**
     * 设置适配器内容
     */
    protected abstract void setView(RecyclerViewHolder o, int position, T data);

    @Override
    public void setList(ArrayList<T> ts) {
        list.clear();
        addList(ts);
    }

    @Override
    public void addList(ArrayList<T> ts) {
        list.addAll(ts);
        notifyDataSetChanged();
    }

    @Override
    public void addList(int index, ArrayList<T> ts) {
        list.addAll(index, ts);
        notifyDataSetChanged();
    }

    @Override
    public void add(T t) {
        add(0,t);
    }

    @Override
    public void add(int index, T t) {
        list.add(index, t);
        notifyDataSetChanged();
    }

    @Override
    public void set(int position, T t) {
        list.set(position, t);
        notifyDataSetChanged();
    }

    @Override
    public void remove(int position) {
        list.remove(position);
        notifyDataSetChanged();
    }

    @Override
    public void remove(T t) {
        list.remove(t);
        notifyDataSetChanged();
    }

    @Override
    public T getListItem(int position) {
        return list.get(position);
    }

    @Override
    public ArrayList<T> getList() {
        return list;
    }

    @Override
    public void clear() {
        list.clear();
        notifyDataSetChanged();
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public void setOnItemLongClickListener(OnItemLongClickListener onItemLongClickListener) {
        this.onItemLongClickListener = onItemLongClickListener;
    }
}
