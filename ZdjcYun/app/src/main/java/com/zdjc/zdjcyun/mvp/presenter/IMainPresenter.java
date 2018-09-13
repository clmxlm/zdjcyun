package com.zdjc.zdjcyun.mvp.presenter;


import com.zdjc.zdjcyun.network.RequestCallBack;

import java.util.Map;

/**
 * Created by ali on 2017/2/17.
 */

public interface IMainPresenter {
    void getHomeViewMsg(RequestCallBack callBack, Map<String, String> params, int tag);

    void getProjectTypeMsg(RequestCallBack callBack, Map<String, String> params, int tag);

    void getPersonMsg(RequestCallBack callBack, Map<String, String> params, int tag);

    void loginOut(RequestCallBack callBack, Map<String, String> params, int tag);
}
