package com.zdjc.zdjcyun.mvp.ui.activities;


import android.view.View;

import com.zdjc.zdjcyun.R;
import com.zdjc.zdjcyun.base.BaseActivity;
import com.zdjc.zdjcyun.databinding.ActivityMapBinding;
import com.zdjc.zdjcyun.mvp.viewmodel.impl.MapModel;

public class MapActivity extends BaseActivity<ActivityMapBinding, MapModel> {

    @Override
    public int getLayoutId() {
        setWindowStatusBarColor(this, R.color.theme_color);
        return R.layout.activity_map;
    }

    @Override
    public void initView() {
        mBinder.include.tvTitle.setText("位置信息");
        mBinder.include.imgbtnBack.setVisibility(View.VISIBLE);
        mBinder.include.imgbtnBack.setOnClickListener(v -> finish());
    }

}
