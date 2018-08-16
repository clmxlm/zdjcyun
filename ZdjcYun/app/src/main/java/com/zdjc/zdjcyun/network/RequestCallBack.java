package com.zdjc.zdjcyun.network;

/**
 * Created by ali on 2017/2/16.
 */

public interface RequestCallBack<T> {

    void beforeRequest(int tag);

    void success(Object data, int tag);

    void error(String errorMsg,int tag);
}
