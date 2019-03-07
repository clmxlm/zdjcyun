package com.zdjc.zdjcyun.mvp.ui.activities;

import android.content.Intent;
import android.view.KeyEvent;
import android.view.View;

import com.zdjc.zdjcyun.R;
import com.zdjc.zdjcyun.base.BaseActivity;
import com.zdjc.zdjcyun.databinding.ActivityProjectDetailBinding;
import com.zdjc.zdjcyun.mvp.viewmodel.impl.ProjectDetailModel;
import com.zdjc.zdjcyun.util.PreferenceUtils;

public class ProjectDetailActivity extends BaseActivity<ActivityProjectDetailBinding, ProjectDetailModel>{

    @Override
    public int getLayoutId() {
        setWindowStatusBarColor(this,R.color.theme_color);
        return R.layout.activity_project_detail;
    }

    @Override
    public void initView() {
        mBinder.include.tvTitle.setText("曲线信息");
        mBinder.include.imgbtnBack.setVisibility(View.VISIBLE);
        if (!"MpActivity".equals(PreferenceUtils.getString(this,"activityTag"))){
            exit();
        }
        mBinder.include.imgbtnBack.setOnClickListener(v -> {
            finish();
            exit();
        });
    }


    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
    }

    /**
     * 对返回键进行监听
     * @param keyCode
     * @param event
     * @return
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            exit();
            finish();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    public void exit() {
            PreferenceUtils.putString(ProjectDetailActivity.this,"monitorTypeName","");
            PreferenceUtils.putString(ProjectDetailActivity.this,"singleTime","");
            PreferenceUtils.putString(ProjectDetailActivity.this,"doubleTime","");
            PreferenceUtils.putString(ProjectDetailActivity.this,"firstStartTime","");
            PreferenceUtils.putString(ProjectDetailActivity.this,"firstEndTime","");
            PreferenceUtils.putString(ProjectDetailActivity.this,"tableName","");
            PreferenceUtils.putString(ProjectDetailActivity.this,"sensorNumber",null);
            PreferenceUtils.putString(ProjectDetailActivity.this,"smuNumber",null);
            PreferenceUtils.putString(ProjectDetailActivity.this,"smuChannel",null);
    }

}
