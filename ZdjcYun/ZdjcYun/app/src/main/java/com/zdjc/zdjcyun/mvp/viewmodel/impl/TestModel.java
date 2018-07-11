package com.zdjc.zdjcyun.mvp.viewmodel.impl;


import com.zdjc.zdjcyun.databinding.FragmentTestBinding;
import com.zdjc.zdjcyun.mvp.presenter.impl.TestPresenterImpl;
import com.zdjc.zdjcyun.mvp.viewmodel.ITestModel;
import com.zdjc.zdjcyun.base.BaseModel;
/**
 * Created by ali on 2017/2/20.
 */

public class TestModel extends BaseModel<FragmentTestBinding,TestPresenterImpl> implements ITestModel {

    @Override
    public void onCreate() {

    }

    @Override
    public void onBeforeRequest(int tag) {

    }

    @Override
    public void onSuccess(Object bean, int tag) {

    }

    @Override
    public void onError(String errorMsg, int tag) {

    }

}
