package com.zdjc.zdjcyun.mvp.ui.activities;


import com.zdjc.zdjcyun.R;
import com.zdjc.zdjcyun.base.BaseActivity;
import com.zdjc.zdjcyun.databinding.ActivityDataComparisonBinding;
import com.zdjc.zdjcyun.mvp.viewmodel.impl.DataComparisonModel;

public class DataComparisonActivity extends BaseActivity<ActivityDataComparisonBinding, DataComparisonModel> {

    @Override
    public int getLayoutId() {
        setWindowStatusBarColor(this, R.color.theme_color);
        return R.layout.activity_data_comparison;
    }

    @Override
    public void initView() {

    }

}
