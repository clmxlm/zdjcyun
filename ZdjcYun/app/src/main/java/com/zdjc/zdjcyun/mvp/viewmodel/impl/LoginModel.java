package com.zdjc.zdjcyun.mvp.viewmodel.impl;


import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.util.Log;
import android.view.View;

import com.blankj.utilcode.utils.ToastUtils;
import com.google.gson.Gson;
import com.zdjc.zdjcyun.app.BaseApplication;
import com.zdjc.zdjcyun.base.BaseModel;
import com.zdjc.zdjcyun.databinding.ActivityLoginBinding;
import com.zdjc.zdjcyun.mvp.entity.MonitorUnitEntity;
import com.zdjc.zdjcyun.mvp.entity.VersionEntity;
import com.zdjc.zdjcyun.mvp.presenter.impl.LoginPresenterImpl;
import com.zdjc.zdjcyun.mvp.ui.activities.LoginActivity;
import com.zdjc.zdjcyun.mvp.ui.activities.MainActivity;
import com.zdjc.zdjcyun.mvp.viewmodel.ILoginModel;
import com.zdjc.zdjcyun.util.AppActivityManager;
import com.zdjc.zdjcyun.util.EditTextHolder;
import com.zdjc.zdjcyun.util.EdtCheckEntity;
import com.zdjc.zdjcyun.util.PreferenceUtils;

import java.util.ArrayList;
import java.util.HashMap;

import cn.jpush.android.api.JPushInterface;



public class LoginModel extends BaseModel<ActivityLoginBinding, LoginPresenterImpl> implements ILoginModel,
        EditTextHolder.OnEditTextFocusChangeListener {

    @Override
    public void onCreate() {
    }

    @Override
    public void onBeforeRequest(int tag) {
        UI.showWaitDialog();
    }

    @Override
    public void onSuccess(Object bean, int tag) {
        /**
         * 成功后到这里
         */
        switch (tag) {
            case 1:
                String token=(String)bean;
                PreferenceUtils.putString(BaseApplication.getContext(),"token",token);
                /**
                 * UI是activity或者fragment
                 */
                JPushInterface.resumePush(getContext());
                //设置别名
                JPushInterface.setAlias(getContext(), 0,mBinder.etUsername.getText().toString());

                JPushInterface.resumePush(getContext());

                HashMap<String, String> params = new HashMap<>(0);
                params.put("zdjc","zdjc");
                mControl.queryMonitorUnit(this,params,2);

//                HashMap<String, String> paramsVersion = new HashMap<>(0);
//                paramsVersion.put("appType",61+"");
//                paramsVersion.put("version",getVersionName());
//                mControl.queryVersion(this,paramsVersion,3);
                break;
            case 2:
                /**
                 * 存储所有指标的集合
                 */
                ArrayList<MonitorUnitEntity.DataBean> data = (ArrayList<MonitorUnitEntity.DataBean>)bean;
                Gson gson = new Gson();
                String monitorUnitData = gson.toJson(data);
                PreferenceUtils.putString(getContext(),"monitorUnit",monitorUnitData);

                LoginActivity activity=(LoginActivity)UI;
                activity.intent2Activity(MainActivity.class);
                activity.finish();
                break;

//            case 3:
//                VersionEntity.DataBean dataVersion = (VersionEntity.DataBean)bean;
//                PreferenceUtils.putBoolean(getContext(),"newVersion",dataVersion.isNewVersion());
//                PreferenceUtils.putString(getContext(),"version",dataVersion.getVersion());
//                PreferenceUtils.putString(getContext(),"apkUrl",dataVersion.getUrl());
//
//
//                break;
             default:
                break;
        }
    }

    @Override
    public void onError(String errorMsg, int code,int tag) {

    }

    @Override
    public void delectEt() {
        EdtCheckEntity.checkNum = 0;
    }

    @Override
    public void login() {

        HashMap<String, String> params = new HashMap<>(0);
        String pwd = mBinder.etPassword.getText().toString();
        String name = mBinder.etUsername.getText().toString();

        if ("".equals(name)){
            ToastUtils.showLongToast("请输入用户名");
        }else if ("".equals(pwd)){
            ToastUtils.showLongToast("请输入密码");
        }else {
            params.put("userName",name);
            params.put("password",pwd);
            PreferenceUtils.putString(getContext(),"uesrName",name);
            mControl.login(this,params,1);
        }
    }

    @Override
    public void onEditTextFocusChange(View v, boolean hasFocus) {

    }

    /**
     * 获取当前程序的版本名
     */
    private String getVersionName(){
        //获取packagemanager的实例
        PackageManager packageManager = getContext().getPackageManager();
        //getPackageName()是你当前类的包名，0代表是获取版本信息
        PackageInfo packInfo = null;
        try {
            packInfo = packageManager.getPackageInfo(getContext().getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        Log.e("TAG","版本号"+packInfo.versionCode);
        Log.e("TAG","版本名"+packInfo.versionName);
        return packInfo.versionName;
    }
}
