/**
 * snk
 * Dye-段新原
 * Copyright  2015  dye 段新原（http://blog.csdn.net/qq_20698023）. All rights reserved.
 */
package com.zdjc.zdjcyun.base;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import butterknife.ButterKnife;

/**
 * ViewHolder
 */
public class RecyclerViewHolder extends RecyclerView.ViewHolder {

    public RecyclerViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
