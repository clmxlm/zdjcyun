package com.zdjc.zdjcyun.mvp.ui.activities;


import com.zdjc.zdjcyun.R;
import com.zdjc.zdjcyun.base.BaseActivity;
import com.zdjc.zdjcyun.databinding.ActivityReportBinding;
import com.zdjc.zdjcyun.mvp.viewmodel.impl.ReportModel;

public class ReportActivity extends BaseActivity<ActivityReportBinding, ReportModel> {

    @Override
    public int getLayoutId() {
        setWindowStatusBarColor(this, R.color.theme_color);
        return R.layout.activity_report;
    }

    @Override
    public void initView() {

    }

}
