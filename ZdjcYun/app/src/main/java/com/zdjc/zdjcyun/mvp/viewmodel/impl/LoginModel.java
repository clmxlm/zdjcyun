package com.zdjc.zdjcyun.mvp.viewmodel.impl;


import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.blankj.utilcode.utils.ToastUtils;
import com.zdjc.zdjcyun.R;
import com.zdjc.zdjcyun.app.BaseApplication;
import com.zdjc.zdjcyun.base.BaseModel;
import com.zdjc.zdjcyun.databinding.ActivityLoginBinding;
import com.zdjc.zdjcyun.mvp.presenter.impl.LoginPresenterImpl;
import com.zdjc.zdjcyun.mvp.ui.activities.BeginActivity;
import com.zdjc.zdjcyun.mvp.ui.activities.LoginActivity;
import com.zdjc.zdjcyun.mvp.viewmodel.ILoginModel;
import com.zdjc.zdjcyun.util.EditTextHolder;
import com.zdjc.zdjcyun.util.EdtCheckEntity;
import com.zdjc.zdjcyun.util.PreferenceUtils;

import java.util.HashMap;

import cn.jpush.android.api.JPushInterface;

/**
 * Created by ali on 2017/2/20.
 */

public class LoginModel extends BaseModel<ActivityLoginBinding, LoginPresenterImpl> implements ILoginModel,
        EditTextHolder.OnEditTextFocusChangeListener {

    private Animation animation, animation1;
    @Override
    public void onCreate() {
        init();
    }

    public void init(){
        Handler mHandler = new Handler();
        mHandler.postDelayed(() -> {
            animation = AnimationUtils.loadAnimation(getContext(), R.anim.translate_anim);
            animation.setRepeatMode(Animation.RESTART);
            if(mBinder.deImgBackgroud!=null){
                mBinder.deImgBackgroud.startAnimation(animation);
            }
        }, 0);
        mHandler.postDelayed(() -> {
            animation1 = AnimationUtils.loadAnimation(getContext(), R.anim.translate_anim_down);
            animation1.setRepeatMode(Animation.RESTART);
            if(mBinder.imgBackgroud!=null){
                mBinder.imgBackgroud.startAnimation(animation1);
            }
        }, 0);
    }
    @Override
    public void onBeforeRequest(int tag) {
        UI.showWaitDialog();
    }

    @Override
    public void onSuccess(Object bean, int tag) {
        /**
         * 成功后到这里
         */
        switch (tag) {
            case 1:
                String token=(String)bean;
                PreferenceUtils.putString(BaseApplication.getContext(),"token",token);
                /**
                 * UI是activity或者fragment
                 */
                JPushInterface.resumePush(getContext());
                JPushInterface.setAlias(getContext(), 0,mBinder.etUsername.getText().toString());//设置别名
                LoginActivity activity=(LoginActivity)UI;
                activity.intent2Activity(BeginActivity.class);
                activity.finish();
                JPushInterface.resumePush(getContext());
                break;
        }
    }

    @Override
    public void onError(String errorMsg, int tag) {
        ToastUtils.showShortToast(errorMsg);
    }

    @Override
    public void delectEt() {
        EdtCheckEntity.checkNum = 0;
    }

    @Override
    public void login() {

        HashMap<String, String> params = new HashMap<>();
        String pwd = mBinder.etPassword.getText().toString();
        String name = mBinder.etUsername.getText().toString();

        if ("".equals(name)){
            ToastUtils.showLongToast("请输入用户名");
        }else if ("".equals(pwd)){
            ToastUtils.showLongToast("请输入密码");
        }else {
            params.put("userName",name);
            params.put("password",pwd);
            PreferenceUtils.putString(getContext(),"uesrName",name);
            mControl.login(this,params,1);
        }
    }

    @Override
    public void onEditTextFocusChange(View v, boolean hasFocus) {

    }
}
