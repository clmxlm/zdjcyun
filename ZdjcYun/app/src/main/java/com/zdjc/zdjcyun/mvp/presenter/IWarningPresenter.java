package com.zdjc.zdjcyun.mvp.presenter;


import com.zdjc.zdjcyun.network.RequestCallBack;

import java.util.Map;

/**
 * Created by ali on 2017/2/17.
 */

public interface IWarningPresenter {

    /**
     * 这里就是一个登录的抽象方法，具体实现在LoginPresenter里面实现
     * @param callBack
     * @param params
     * @param tag
     */
    void getWarningList(RequestCallBack callBack, Map<String, String> params, int tag);

    void getQueryAlarmCount(RequestCallBack callBack, Map<String, String> params, int tag);

    void changeWarningMsg(RequestCallBack callBack, Map<String, String> params, int tag);
}
