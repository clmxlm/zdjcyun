package com.zdjc.zdjcyun.mvp.viewmodel;

import com.zdjc.zdjcyun.mvp.entity.AllProjectListEntity;

import java.util.List;

/**
 * Created by lenovo on 2017/6/9.
 */

public interface IMainModel {
    void initData();
    void initListener();
    void allProjectClick(List<AllProjectListEntity.DataBean> dataBeanList);
}
