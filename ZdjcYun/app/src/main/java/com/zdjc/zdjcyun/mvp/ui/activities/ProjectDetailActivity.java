package com.zdjc.zdjcyun.mvp.ui.activities;

import android.content.Intent;
import android.view.KeyEvent;
import android.view.View;

import com.zdjc.zdjcyun.R;
import com.zdjc.zdjcyun.app.BaseApplication;
import com.zdjc.zdjcyun.base.BaseActivity;
import com.zdjc.zdjcyun.databinding.ActivityProjectDetailBinding;
import com.zdjc.zdjcyun.mvp.entity.AllProjectListEntity;
import com.zdjc.zdjcyun.mvp.ui.fragment.HomeFragment;
import com.zdjc.zdjcyun.mvp.viewmodel.impl.ProjectMarkModel;
import com.zdjc.zdjcyun.util.PermissionUtils;
import com.zdjc.zdjcyun.util.PreferenceUtils;

import java.util.List;

public class ProjectDetailActivity extends BaseActivity<ActivityProjectDetailBinding, ProjectMarkModel> implements HomeFragment.OnCallBackProjects,HomeFragment.OnCallBackMapTag{
    private PermissionUtils permissionUtils;
    private int alCount = 0;
    private List<AllProjectListEntity.DataBean> dataBeanList;

    @Override
    public int getLayoutId() {
        return R.layout.activity_project_detail;
    }

    @Override
    public void initView() {
        setWindowStatusBarColor(this,R.color.theme_color);
        initPermission();
    }

    private void initPermission() {
        if(permissionUtils==null){
            permissionUtils=new PermissionUtils(this);
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
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
        finish();
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
