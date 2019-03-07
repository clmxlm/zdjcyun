package com.zdjc.zdjcyun.mvp.presenter.impl;


import com.zdjc.zdjcyun.base.BaseNetControl;
import com.zdjc.zdjcyun.mvp.entity.MeasuringPointEntity;
import com.zdjc.zdjcyun.mvp.presenter.IMeasuringPointPresenter;
import com.zdjc.zdjcyun.network.RequestCallBack;
import com.zdjc.zdjcyun.network.request.HttpRequestImpl;

import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by ali on 2017/2/20.
 */

public class MeasuringPointPresenterImpl extends BaseNetControl implements IMeasuringPointPresenter {

    @Override
    public void getMeasuringPointMsg(RequestCallBack callBack, Map<String, String> params, int tag) {
        /**
         * 请求前
         */
        callBack.beforeRequest(tag);
        HttpRequestImpl.getInstance().measuringPointMsg(params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<MeasuringPointEntity>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        /**
                         * 这个就是rxjava的任务对象
                         */
                        mDisposable = d;
                    }

                    @Override
                    public void onNext(MeasuringPointEntity value) {
                        /**
                         * 这里是回掉成功的
                         */
                        if(value.getCode()==0){
                            callBack.success(value.getData(),tag);
                        }else {
                            callBack.error(value.getMsg(),value.getCode(),tag);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        /**
                         * 这里是回掉错误的
                         */
                        callBack.error(e.getMessage(),1,tag);
                    }

                    @Override
                    public void onComplete() {
                        /**
                         * 完成不用管
                         */
                    }
                });
    }

}
