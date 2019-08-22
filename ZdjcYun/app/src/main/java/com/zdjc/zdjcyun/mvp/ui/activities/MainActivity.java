package com.zdjc.zdjcyun.mvp.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;

import com.blankj.utilcode.utils.ToastUtils;
import com.google.gson.Gson;
import com.zdjc.zdjcyun.R;
import com.zdjc.zdjcyun.base.BaseActivity;
import com.zdjc.zdjcyun.databinding.ActivityMainBinding;
import com.zdjc.zdjcyun.mvp.entity.MessageEntity;
import com.zdjc.zdjcyun.mvp.entity.PushEntity;
import com.zdjc.zdjcyun.mvp.viewmodel.impl.MainModel;
import com.zdjc.zdjcyun.util.PreferenceUtils;

import cn.jpush.android.api.JPushInterface;

public class MainActivity extends BaseActivity<ActivityMainBinding, MainModel>{



    private long mExitTime;
    @Override
    public int getLayoutId() {
        setWindowStatusBarColor(this, R.color.theme_color);
        return R.layout.activity_main;
    }

    @Override
    public void initView() {

    }

    /**
     * 对返回键进行监听
     * @param keyCode
     * @param event
     * @return
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            exit();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    public void exit() {
        if ((System.currentTimeMillis() - mExitTime) > 2000) {
            ToastUtils.showShortToast(getResources().getString(R.string.exit));
            mExitTime = System.currentTimeMillis();
        } else {
            finish();
        }
    }


}
