package com.zdjc.zdjcyun.mvp.viewmodel.impl;


import com.zdjc.zdjcyun.app.BaseApplication;
import com.zdjc.zdjcyun.base.BaseModel;
import com.zdjc.zdjcyun.databinding.ActivitySplashBinding;
import com.zdjc.zdjcyun.mvp.presenter.impl.SplashPresenterImpl;
import com.zdjc.zdjcyun.mvp.ui.activities.BeginActivity;
import com.zdjc.zdjcyun.mvp.ui.activities.SplashActivity;
import com.zdjc.zdjcyun.mvp.viewmodel.ISplashModel;
import com.zdjc.zdjcyun.util.PreferenceUtils;

import cn.jpush.android.api.JPushInterface;

/**
 * Created by ali on 2017/2/20.
 */

public class SplashModel extends BaseModel<ActivitySplashBinding, SplashPresenterImpl> implements ISplashModel {

    @Override
    public void onCreate() {
        if (PreferenceUtils.getString(BaseApplication.getContext(),"token")==null){
            PreferenceUtils.putString(BaseApplication.getContext(),"token","");
        }
        initData();
    }

    @Override
    public void onBeforeRequest(int tag) {

    }

    @Override
    public void onSuccess(Object bean, int tag) {

    }

    @Override
    public void onError(String errorMsg, int tag) {

    }

    @Override
    public void initData() {
        if ("".equals(PreferenceUtils.getString(getContext(),"token"))){
            //没登录之前关掉极光推送
            JPushInterface.stopPush(getContext());
            ((SplashActivity)UI).intent2Activity(BeginActivity.class);
        }else {
            ((SplashActivity)UI).intent2Activity(BeginActivity.class);
        }
        ((SplashActivity)UI).finish();
    }

    @Override
    public void initRunable() {

    }

}
