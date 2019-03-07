package com.zdjc.zdjcyun.mvp.presenter;


import com.zdjc.zdjcyun.network.RequestCallBack;

import java.util.Map;

/**
 * Created by ali on 2017/2/17.
 */

public interface IDataMonitoringPresenter {
    void getQueryImagesMonitorPoint(RequestCallBack callBack, Map<String, String> params, int tag);

    void getQuerySensorData(RequestCallBack callBack, Map<String, String> params, int tag);

    void getQueryTerminalAndSensor(RequestCallBack callBack, Map<String, String> params, int tag);
}
