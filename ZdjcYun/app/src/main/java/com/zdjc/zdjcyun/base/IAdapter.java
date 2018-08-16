/**
 * snk
 * Dye-段新原
 * Copyright  2015  dye 段新原（http://blog.csdn.net/qq_20698023）. All rights reserved.
 */
package com.zdjc.zdjcyun.base;

import java.util.ArrayList;

/**
 * 适配器数据操作
 */
public interface IAdapter<T> {
    /**
     * 重新设置ArrayList
     */
    void setList(ArrayList<T> ts);

    /**
     * 添加一个ArrayList到Adapter中
     */
    void addList(ArrayList<T> ts);

    /**
     * 添加一个ArrayList到Adapter中
     */
    void addList(int index, ArrayList<T> ts);

    /**
     * 添加一个T到Adapter中
     */
    void add(T t);

    /**
     * 添加一个T到Adapter中
     */
    void add(int index, T t);

    /**
     * 替换Adapter中的某一条数据
     * @param position 条目位置
     */
    void set(int position, T t);

    /**
     * 移除某一条Item
     * @param position 条目位置
     */
    void remove(int position);

    /**
     * 移除某一条Item
     */
    void remove(T t);

    /**
     * 获取某一条数据
     * @param position 条目位置
     */
    T getListItem(int position);

    /**
     * 获取所有数据
     * @return ArrayList
     */
    ArrayList<T> getList();

    /**
     * 清除list
     */
    void clear();
}
