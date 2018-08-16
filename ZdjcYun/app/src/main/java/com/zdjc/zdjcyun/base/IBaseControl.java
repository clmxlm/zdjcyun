package com.zdjc.zdjcyun.base;

/**
 * Created by ali on 2017/2/23.
 */

public interface IBaseControl {
    /**
     * 这个主要用于在activity销毁时取消请求
     */
    void disposableCancel();
}
