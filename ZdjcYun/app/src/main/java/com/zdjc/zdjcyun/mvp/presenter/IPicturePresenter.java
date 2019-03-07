package com.zdjc.zdjcyun.mvp.presenter;


import com.zdjc.zdjcyun.network.RequestCallBack;

import java.util.Map;

/**
 * Created by ali on 2017/2/17.
 */

public interface IPicturePresenter {
    void getQueryImageType(RequestCallBack callBack, Map<String, String> params, int tag);
}
