package com.zdjc.zdjcyun.mvp.ui.activities;

import com.zdjc.zdjcyun.R;
import com.zdjc.zdjcyun.base.BaseActivity;
import com.zdjc.zdjcyun.databinding.ActivityProjectTestDetailBinding;
import com.zdjc.zdjcyun.mvp.viewmodel.impl.TestProjectDetailModel;

public class ProjectDetailTestActivity extends BaseActivity<ActivityProjectTestDetailBinding, TestProjectDetailModel>{


    @Override
    public int getLayoutId() {
        return R.layout.activity_project_test_detail;
    }

    @Override
    public void initView() {
        setWindowStatusBarColor(this,R.color.theme_color);

    }


}
