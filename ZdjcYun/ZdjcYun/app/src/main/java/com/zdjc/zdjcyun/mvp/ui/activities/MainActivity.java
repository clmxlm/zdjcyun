package com.zdjc.zdjcyun.mvp.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;

import com.blankj.utilcode.utils.ToastUtils;
import com.google.gson.Gson;
import com.zdjc.zdjcyun.R;
import com.zdjc.zdjcyun.app.BaseApplication;
import com.zdjc.zdjcyun.base.BaseActivity;
import com.zdjc.zdjcyun.databinding.ActivityMainBinding;
import com.zdjc.zdjcyun.mvp.entity.AllProjectListEntity;
import com.zdjc.zdjcyun.mvp.ui.fragment.HomeFragment;
import com.zdjc.zdjcyun.mvp.viewmodel.impl.MainModel;
import com.zdjc.zdjcyun.util.PermissionUtils;
import com.zdjc.zdjcyun.util.PreferenceUtils;

import java.util.List;

import cn.jpush.android.api.JPushInterface;

public class MainActivity extends BaseActivity<ActivityMainBinding, MainModel> implements HomeFragment.OnCallBackProjects,HomeFragment.OnCallBackMapTag{
    private long mExitTime;
    private PermissionUtils permissionUtils;
    private int alCount = 0;
    private List<AllProjectListEntity.DataBean> dataBeanList;

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
        setWindowStatusBarColor(this,R.color.theme_color);
        initPermission();
        mBinder.include.tvTtProject.setVisibility(View.VISIBLE);
        mBinder.include.tvChangeAccount.setVisibility(View.VISIBLE);
        mBinder.include.imgbtnBack.setVisibility(View.GONE);

        Intent intent = getIntent();
        if (intent.getData()!=null){
            this.mModel.moveFragment(2);
        }
    }

    private void initPermission() {
        if(permissionUtils==null){
            permissionUtils=new PermissionUtils(this);
        }

    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Bundle bundle = intent.getExtras();
        String kk = bundle.getString(JPushInterface.EXTRA_EXTRA);
        Gson gson = new Gson();
//        ListType listType = gson.fromJson(kk, ListType.class);
//        String gg = gson.
//        gson.fromJson(kk,"msg");
        this.mModel.moveFragment(2);
    }

    //对返回键进行监听
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            exit();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }


    public void allProject(View view){
        mModel.allProjectClick(dataBeanList);
    }


    public void exit() {
        if ((System.currentTimeMillis() - mExitTime) > 2000) {
            ToastUtils.showShortToast(getResources().getString(R.string.exit));
            mExitTime = System.currentTimeMillis();
        } else {
            finish();
            System.exit(0);
        }
    }


    @Override
    public void onProjects(List<AllProjectListEntity.DataBean> projects) {
        this.dataBeanList = projects;
        for (AllProjectListEntity.DataBean dataBean : dataBeanList) {
            alCount = alCount + dataBean.getAlCount();
        }
        PreferenceUtils.putInt(BaseApplication.getContext(),"projectId",dataBeanList.get(0).getProjectId());
        PreferenceUtils.putString(this,"projectName",dataBeanList.get(0).getProjectName());

        this.mModel.getAlcount(alCount);
        this.mModel.allProjectClick(dataBeanList);
    }

    @Override
    public void onMaps(int position) {
        this.mModel.mapClick(position);
    }
}
