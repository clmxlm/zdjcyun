package com.zdjc.zdjcyun.mvp.presenter;


import com.zdjc.zdjcyun.network.RequestCallBack;

import java.util.Map;

/**
 * Created by ali on 2017/2/17.
 */

public interface IDataQueryPresenter {
    void getQueryImagesMonitorPoint(RequestCallBack callBack, Map<String, String> params, int tag);

    void getQuerySensorData(RequestCallBack callBack, Map<String, String> params, int tag);

    void getQueryNormTwoSensorData(RequestCallBack callBack, Map<String, String> params, int tag);

    void getQueryNormThreeSensorData(RequestCallBack callBack, Map<String, String> params, int tag);

    void getQueryNormFourSensorData(RequestCallBack callBack, Map<String, String> params, int tag);

    void getQueryMapType(RequestCallBack callBack, Map<String, String> params, int tag);

    void getQueryTerminalAndSensor(RequestCallBack callBack, Map<String, String> params, int tag);

    void getQueryDeepData(RequestCallBack callBack, Map<String, String> params, int tag);

    void getQueryMonitorTypePointName(RequestCallBack callBack, Map<String, String> params, int tag);
}
