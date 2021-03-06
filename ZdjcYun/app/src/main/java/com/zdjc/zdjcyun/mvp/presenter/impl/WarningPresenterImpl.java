package com.zdjc.zdjcyun.mvp.presenter.impl;


import com.zdjc.zdjcyun.base.BaseNetControl;
import com.zdjc.zdjcyun.mvp.entity.AlarmCountEntity;
import com.zdjc.zdjcyun.mvp.entity.UserBean;
import com.zdjc.zdjcyun.mvp.entity.WarningEntity;
import com.zdjc.zdjcyun.mvp.presenter.IWarningPresenter;
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

public class WarningPresenterImpl extends BaseNetControl implements IWarningPresenter {

    @Override
    public void getWarningList(RequestCallBack callBack, Map<String, String> params, int tag) {
        /**
         * 请求前
         */
        callBack.beforeRequest(tag);
        HttpRequestImpl.getInstance().warningDetail(params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<WarningEntity>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        /**
                         * 这个就是rxjava的任务对象
                         */
                        mDisposable = d;
                    }

                    @Override
                    public void onNext(WarningEntity value) {
                        /**
                         * 这里是回掉成功的
                         */
                        if(value.getCode()==0){
                            /**
                             * 这里是把自己想要的数据传过去，现在getData成功的话就是token啦，那边拿到的也是token
                             */
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


    @Override
    public void getQueryAlarmCount(RequestCallBack callBack, Map<String, String> params, int tag) {
        /**
         * 请求前
         */
        callBack.beforeRequest(tag);
        HttpRequestImpl.getInstance().queryAlarmCount(params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<AlarmCountEntity>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        /**
                         * 这个就是rxjava的任务对象
                         */
                        mDisposable = d;
                    }

                    @Override
                    public void onNext(AlarmCountEntity value) {
                        /**
                         * 这里是回掉成功的
                         */
                        if(value.getCode()==0){
                            /**
                             * 这里是把自己想要的数据传过去，现在getData成功的话就是token啦，那边拿到的也是token
                             */
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

    @Override
    public void changeWarningMsg(RequestCallBack callBack, Map<String, String> params, int tag) {
        /**
         * 请求前
         */
        callBack.beforeRequest(tag);
        HttpRequestImpl.getInstance().warningChange(params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<UserBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        /**
                         * 这个就是rxjava的任务对象
                         */
                        mDisposable = d;
                    }

                    @Override
                    public void onNext(UserBean value) {
                        /**
                         * 这里是回掉成功的
                         */
                        if(value.getCode()==0){
                            /**
                             * 这里是把自己想要的数据传过去，现在getData成功的话就是token啦，那边拿到的也是token
                             */
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
