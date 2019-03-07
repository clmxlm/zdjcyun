package com.zdjc.zdjcyun.mvp.ui.activities;

import com.zdjc.zdjcyun.R;
import com.zdjc.zdjcyun.base.BaseActivity;
import com.zdjc.zdjcyun.databinding.ActivityProjectManageBinding;
import com.zdjc.zdjcyun.mvp.viewmodel.impl.ProjectManageModel;
/**
 * @author ClmXlm
 * @create 2019/1/9
 * @Describe
 */
public class ProjectManageActivity extends BaseActivity<ActivityProjectManageBinding, ProjectManageModel> {

    @Override
    public int getLayoutId() {
        setWindowStatusBarColor(this, R.color.theme_color);
        return R.layout.activity_project_manage;
    }

    @Override
    public void initView() {

    }

}
