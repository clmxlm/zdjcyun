package com.zdjc.zdjcyun.mvp.viewmodel.impl;


import android.view.View;

import com.blankj.utilcode.utils.ToastUtils;
import com.zdjc.zdjcyun.base.BaseModel;
import com.zdjc.zdjcyun.databinding.ActivityChangePasswordBinding;
import com.zdjc.zdjcyun.mvp.entity.UpdatePasswordEntity;
import com.zdjc.zdjcyun.mvp.presenter.impl.ChangePasswordPresenterImpl;
import com.zdjc.zdjcyun.mvp.ui.activities.ChangePasswordActivity;
import com.zdjc.zdjcyun.mvp.ui.activities.LoginActivity;
import com.zdjc.zdjcyun.mvp.viewmodel.IChangePasswordModel;

import java.util.HashMap;
import java.util.Map;


public class ChangePasswordModel extends BaseModel<ActivityChangePasswordBinding, ChangePasswordPresenterImpl> implements IChangePasswordModel {

    @Override
    public void onCreate() {
        mBinder.include.tvTitle.setText("修改密码");

        mBinder.include.imgbtnBack.setVisibility(View.VISIBLE);
        mBinder.include.imgbtnBack.setOnClickListener(v -> ((ChangePasswordActivity)UI).finish());

        mBinder.btnChangePassword.setOnClickListener(v -> {
            if (!mBinder.etNewPassword.getText().toString().equals(mBinder.etConfirmPassword.getText().toString())){
                ToastUtils.showShortToast("密码前后不一致");
            }else {
                initData();
            }
        });

    }

    @Override
    public void onBeforeRequest(int tag) {

    }

    @Override
    public void onSuccess(Object bean, int tag) {
        switch (tag) {
            case 1:
                UpdatePasswordEntity updatePasswordEntity = (UpdatePasswordEntity)bean;
                if (updatePasswordEntity.getCode()==0){
                    ((ChangePasswordActivity)UI).intent2Activity(LoginActivity.class);
                }else {
                    ToastUtils.showShortToast(updatePasswordEntity.getMsg());
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void onError(String errorMsg, int code,int tag) {

    }

    @Override
    public void initData() {
        Map<String,String> map = new HashMap<>(0);
        map.put("oldPassword",mBinder.etOldPassword.getText().toString());
        map.put("newPassword",mBinder.etNewPassword.getText().toString());
        mControl.changePassword(this,map,1);

    }

    @Override
    public void initRunable() {

    }

}
