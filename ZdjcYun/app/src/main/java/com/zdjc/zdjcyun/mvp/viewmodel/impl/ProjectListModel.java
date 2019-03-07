package com.zdjc.zdjcyun.mvp.viewmodel.impl;

import android.annotation.SuppressLint;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.blankj.utilcode.utils.ToastUtils;
import com.zdjc.zdjcyun.base.BaseModel;
import com.zdjc.zdjcyun.databinding.ActivityProjectListBinding;
import com.zdjc.zdjcyun.mvp.entity.AlarmNewsEntity;
import com.zdjc.zdjcyun.mvp.entity.MonitorViewEntity;
import com.zdjc.zdjcyun.mvp.entity.ProjectTotalStatusEntity;
import com.zdjc.zdjcyun.mvp.entity.WillProjectedEntity;
import com.zdjc.zdjcyun.mvp.presenter.impl.ProjectListPresenterImpl;
import com.zdjc.zdjcyun.mvp.ui.activities.ProjectListActivity;
import com.zdjc.zdjcyun.mvp.ui.activities.ProjectManageActivity;
import com.zdjc.zdjcyun.mvp.ui.adapter.ProjectRecycViewAdapter;
import com.zdjc.zdjcyun.mvp.viewmodel.IProjectListModel;
import com.zdjc.zdjcyun.util.PreferenceUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ProjectListModel extends BaseModel<ActivityProjectListBinding,ProjectListPresenterImpl> implements IProjectListModel {

    private ArrayList<MonitorViewEntity.DataBean> monitorViewList;
    private ProjectRecycViewAdapter adapter;
    private RecyclerView recyclerView;
    private int scId;

    @Override
    public void onCreate() {
        intView();
    }

    private void intView() {

        String token = PreferenceUtils.getString(getContext(), "token");
        scId = PreferenceUtils.getInt(getContext(),"projectType");


        Map<String,String> map = new HashMap<>(0);
        map.put("token", token);
        map.put("scId",scId+"");
        mControl.getQueryProjectStatusCount(this,map,2);

        mBinder.include.tvTitle.setText("地铁自动化监测");

        mBinder.include.imgbtnBack.setVisibility(View.VISIBLE);
        mBinder.include.imgbtnBack.setOnClickListener(v -> ((ProjectListActivity)UI).finish());

        mBinder.ppb.setCostPercent(10, 0, 90, 0, 2);

        recyclerView = mBinder.recycleView;
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter = new ProjectRecycViewAdapter(getContext());
        recyclerView.setAdapter(adapter);
        int page = 1;
        getProjectList(page);

        adapter.setOnItemClickListener((view, position) -> {
            UI.showWaitDialog();
            PreferenceUtils.putInt(getContext(),"projectId",monitorViewList.get(position).getProjectId());
            PreferenceUtils.putString(getContext(),"projectName",monitorViewList.get(position).getProjectName());
            PreferenceUtils.putInt(getContext(),"tag",1);
            ((ProjectListActivity)UI).intent2Activity(ProjectManageActivity.class);
        });
        //视频监测模块
        mBinder.llVideoSurveillance.setOnClickListener(v -> ToastUtils.showShortToast("开发中..."));
        //所有项目点击事件
        mBinder.llProject.setOnClickListener(v -> {
            ((ProjectListActivity) UI).intent2Activity(ProjectManageActivity.class);
            PreferenceUtils.putInt(getContext(),"tag",0);
        });
        mBinder.btnDetail.setOnClickListener(v -> {
            ((ProjectListActivity) UI).intent2Activity(ProjectManageActivity.class);
            PreferenceUtils.putInt(getContext(),"tag",0);
        });
    }

    @Override
    public void onBeforeRequest(int tag) {
        UI.showWaitDialog();
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onSuccess(Object bean, int tag) {
        switch (tag) {
            case 1:
                monitorViewList = (ArrayList<MonitorViewEntity.DataBean>)bean;
                if (monitorViewList.size()>0){
                    adapter.setList(monitorViewList);
                }
                break;
            case 2:
                ProjectTotalStatusEntity.DataBean dataBean = (ProjectTotalStatusEntity.DataBean)bean;
                mBinder.tvAllProject.setText(dataBean.getProjectTotalCount()+"");
                mBinder.tvProjecting.setText(dataBean.getProjectIngCount()+"");
                mBinder.tvProjectError.setText(dataBean.getProjectErrorCount()+"");
                mBinder.tvProjected.setText(dataBean.getProjectEndCount()+"");
                mBinder.tvWillProjected.setText(dataBean.getProjectWillEndCount()+"");

                Map<String,String> map = new HashMap<>(0);
                map.put("scId",scId+"");
                mControl.getQueryAlarmProject(this,map,3);
                break;

            case 3:
                ArrayList<AlarmNewsEntity.DataBean>  alarmNewsEntityList = (ArrayList<AlarmNewsEntity.DataBean>)bean;
                if (alarmNewsEntityList.size()>0){
                    mBinder.tvAlarmNews.setText(alarmNewsEntityList.get(0).getProjectName());
                }else {
                    mBinder.tvAlarmNews.setText("暂无项目有告警信息!");
                }
                Map<String,String> map1 = new HashMap<>(0);
                map1.put("scId",scId+"");
                mControl.getQueryWillProject(this,map1,4);
                break;
            case 4:
                ArrayList<WillProjectedEntity.DataBean> willProjectedEntityList = (ArrayList<WillProjectedEntity.DataBean>)bean;
                if (willProjectedEntityList.size()>0){
                    mBinder.tvWillEnd.setText(willProjectedEntityList.get(0).getProjectName());
                }else {
                    mBinder.tvWillEnd.setText("暂无即将完成的项目!");
                }

                break;
                default:
                    break;
        }
    }

    @Override
    public void onError(String errorMsg, int code,int tag) {

    }

    private void getProjectList(int page){
        Map<String,String> map = new HashMap<>(0);
        map.put("scId",scId+"");
        mControl.getQueryMonitorView(this,map,1);
    }

    @Override
    public void onResume() {
        super.onResume();
        UI.hideWaitDialog();
    }
}
