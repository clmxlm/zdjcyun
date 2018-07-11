package com.zdjc.zdjcyun.mvp.viewmodel.impl;


import com.zdjc.zdjcyun.base.BaseModel;
import com.zdjc.zdjcyun.databinding.ActivitySplashBinding;
import com.zdjc.zdjcyun.mvp.presenter.impl.SplashPresenterImpl;
import com.zdjc.zdjcyun.mvp.ui.activities.LoginActivity;
import com.zdjc.zdjcyun.mvp.ui.activities.MainActivity;
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
        if ("logout".equals(PreferenceUtils.getString(getContext(),"token"))
                ||PreferenceUtils.getString(getContext(),"token")==null){
            //没登录之前关掉极光推送
            JPushInterface.stopPush(getContext());
            ((SplashActivity)UI).intent2Activity(LoginActivity.class);
        }else {
            ((SplashActivity)UI).intent2Activity(MainActivity.class);
        }
        ((SplashActivity)UI).finish();
    }

    @Override
    public void initRunable() {

    }

}
