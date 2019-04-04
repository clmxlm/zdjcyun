package com.zdjc.zdjcyun.mvp.presenter;


import com.zdjc.zdjcyun.network.RequestCallBack;

import java.util.Map;

/**
 * Created by ali on 2017/2/17.
 */

public interface IDataComparisonPresenter {
    void getQueryMonitorTypeName(RequestCallBack callBack, Map<String, String> params, int tag);
    void getQueryMonitorPointName(RequestCallBack callBack, Map<String, String> params, int tag);
    void getQueryComparisonData(RequestCallBack callBack, Map<String, String> params, int tag);
    void getQueryComparisonGPSData(RequestCallBack callBack, Map<String, String> params, int tag);
    void getQueryMapType(RequestCallBack callBack, Map<String, String> params, int tag);
}
