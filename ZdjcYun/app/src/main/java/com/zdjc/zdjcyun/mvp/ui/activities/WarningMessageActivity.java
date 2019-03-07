package com.zdjc.zdjcyun.mvp.ui.activities;


import android.view.View;

import com.zdjc.zdjcyun.R;
import com.zdjc.zdjcyun.base.BaseActivity;
import com.zdjc.zdjcyun.databinding.ActivityWarningMessageBinding;
import com.zdjc.zdjcyun.mvp.viewmodel.impl.WarningModel;


public class WarningMessageActivity extends BaseActivity<ActivityWarningMessageBinding,WarningModel> {

    @Override
    public int getLayoutId() {
        setWindowStatusBarColor(this, R.color.theme_color);
        return R.layout.activity_warning_message;
    }

    @Override
    public void initView() {
        mBinder.include.tvTitle.setText("告警信息");
        mBinder.include.imgbtnBack.setVisibility(View.VISIBLE);
        mBinder.include.imgbtnBack.setOnClickListener(v -> finish());
    }

}
