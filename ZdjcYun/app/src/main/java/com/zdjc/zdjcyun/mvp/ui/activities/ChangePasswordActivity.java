package com.zdjc.zdjcyun.mvp.ui.activities;


import com.zdjc.zdjcyun.R;
import com.zdjc.zdjcyun.base.BaseActivity;
import com.zdjc.zdjcyun.databinding.ActivityChangePasswordBinding;
import com.zdjc.zdjcyun.mvp.viewmodel.impl.ChangePasswordModel;

public class ChangePasswordActivity extends BaseActivity<ActivityChangePasswordBinding, ChangePasswordModel> {

    @Override
    public int getLayoutId() {
        setWindowStatusBarColor(this, R.color.theme_color);
        return R.layout.activity_change_password;
    }

    @Override
    public void initView() {

    }

}
