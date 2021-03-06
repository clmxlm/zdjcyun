package com.zdjc.zdjcyun.mvp.ui.activities;


import android.view.View;

import com.zdjc.zdjcyun.R;
import com.zdjc.zdjcyun.base.BaseActivity;
import com.zdjc.zdjcyun.databinding.ActivityTextViewBinding;
import com.zdjc.zdjcyun.mvp.viewmodel.impl.TextViewModel;


public class TextViewActivity extends BaseActivity<ActivityTextViewBinding,TextViewModel> {

    @Override
    public int getLayoutId() {
        setWindowStatusBarColor(this, R.color.theme_color);
        return R.layout.activity_text_view;
    }

    @Override
    public void initView() {
        mBinder.include.tvTitle.setText("基本信息");
        mBinder.include.imgbtnBack.setVisibility(View.VISIBLE);
        mBinder.include.imgbtnBack.setOnClickListener(v -> finish());
    }

}
