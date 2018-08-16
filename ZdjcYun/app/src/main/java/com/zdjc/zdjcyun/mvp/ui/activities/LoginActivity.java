package com.zdjc.zdjcyun.mvp.ui.activities;


import android.view.View;

import com.zdjc.zdjcyun.R;
import com.zdjc.zdjcyun.base.BaseActivity;
import com.zdjc.zdjcyun.databinding.ActivityLoginBinding;
import com.zdjc.zdjcyun.mvp.viewmodel.impl.LoginModel;


public class LoginActivity extends BaseActivity<ActivityLoginBinding,LoginModel> {

    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public void initView() {

    }

    /**
     * 点击事件从这里走，请求登陆
     * @param view
     */
    public void btnLogin(View view){
        mModel.login();
    }

//    public void imageBack(View view){
//        finish();
//    }
}
