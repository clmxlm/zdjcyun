package com.zdjc.zdjcyun.mvp.presenter;


import com.zdjc.zdjcyun.network.RequestCallBack;

import java.util.Map;

/**
 * Created by ali on 2017/2/17.
 */

public interface IProjectListPresenter {
    void getTypeProjectList(RequestCallBack callBack, Map<String, String> params, int tag);

    void getSearchProjectList(RequestCallBack callBack, Map<String, String> params, int tag);

    void getQueryProjectStatusCount(RequestCallBack callBack, Map<String, String> params, int tag);

    void getQueryAlarmProject(RequestCallBack callBack, Map<String, String> params, int tag);

    void getQueryWillProject(RequestCallBack callBack, Map<String, String> params, int tag);

    void getQueryMonitorView(RequestCallBack callBack, Map<String, String> params, int tag);
}
