package com.zdjc.zdjcyun.mvp.ui.activities;

import android.view.KeyEvent;

import com.blankj.utilcode.utils.ToastUtils;
import com.zdjc.zdjcyun.R;
import com.zdjc.zdjcyun.base.BaseActivity;
import com.zdjc.zdjcyun.databinding.ActivityBeginBinding;
import com.zdjc.zdjcyun.mvp.viewmodel.impl.BeginModel;

public class VideoSurveillanceActivity extends BaseActivity<ActivityBeginBinding, BeginModel>{



    private long mExitTime;
    @Override
    public int getLayoutId() {
        setWindowStatusBarColor(this, R.color.theme_color);
        return R.layout.activity_begin;
    }

    @Override
    public void initView() {

    }

    //对返回键进行监听
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
            System.exit(0);
        }
    }


}
