package com.zdjc.zdjcyun.mvp.ui.activities;

import com.zdjc.zdjcyun.R;
import com.zdjc.zdjcyun.base.BaseActivity;
import com.zdjc.zdjcyun.databinding.ActivityProjectListBinding;
import com.zdjc.zdjcyun.mvp.viewmodel.impl.ProjectListModel;

public class ProjectListActivity extends BaseActivity<ActivityProjectListBinding, ProjectListModel>{

    @Override
    public int getLayoutId() {
        setWindowStatusBarColor(this, R.color.theme_color);
        return R.layout.activity_project_list;
    }

    @Override
    public void initView() {

    }

}
