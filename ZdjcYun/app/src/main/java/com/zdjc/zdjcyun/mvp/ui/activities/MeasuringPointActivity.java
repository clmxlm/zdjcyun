package com.zdjc.zdjcyun.mvp.ui.activities;


import android.view.View;

import com.zdjc.zdjcyun.R;
import com.zdjc.zdjcyun.base.BaseActivity;
import com.zdjc.zdjcyun.databinding.ActivityMeasuringPointBinding;
import com.zdjc.zdjcyun.mvp.viewmodel.impl.MeasuringPointModel;


public class MeasuringPointActivity extends BaseActivity<ActivityMeasuringPointBinding,MeasuringPointModel> {

    @Override
    public int getLayoutId() {
        setWindowStatusBarColor(this, R.color.theme_color);
        return R.layout.activity_measuring_point;
    }

    @Override
    public void initView() {
        mBinder.include.tvTitle.setText("测点信息");
        mBinder.include.imgbtnBack.setVisibility(View.VISIBLE);
        mBinder.include.imgbtnBack.setOnClickListener(v -> finish());
    }

}
