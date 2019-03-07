package com.zdjc.zdjcyun.mvp.viewmodel.impl;


import android.support.v7.widget.GridLayoutManager;
import android.view.View;

import com.zdjc.zdjcyun.base.BaseModel;
import com.zdjc.zdjcyun.databinding.ActivityProjectManageBinding;
import com.zdjc.zdjcyun.mvp.entity.ProjectManageEntity;
import com.zdjc.zdjcyun.mvp.presenter.impl.ProjectManagePresenterImpl;
import com.zdjc.zdjcyun.mvp.ui.activities.ProjectManageActivity;
import com.zdjc.zdjcyun.mvp.ui.adapter.ProjectManageRecycViewAdapter;
import com.zdjc.zdjcyun.mvp.viewmodel.IProjectManageModel;
import com.zdjc.zdjcyun.util.PreferenceUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class ProjectManageModel extends BaseModel<ActivityProjectManageBinding, ProjectManagePresenterImpl> implements IProjectManageModel {

    private ProjectManageRecycViewAdapter projectManageRecycViewAdapter;

    @Override
    public void onCreate() {
        int scId = PreferenceUtils.getInt(getContext(), "projectType");
        int projectId = PreferenceUtils.getInt(getContext(), "projectId");
        int tag = PreferenceUtils.getInt(getContext(), "tag");

        mBinder.include.tvTitle.setText("项目管理");

        mBinder.include.imgbtnBack.setVisibility(View.VISIBLE);
        mBinder.include.imgbtnBack.setOnClickListener(v -> ((ProjectManageActivity)UI).finish());

        projectManageRecycViewAdapter = new ProjectManageRecycViewAdapter((ProjectManageActivity)UI);
        mBinder.rlProjectManage.setLayoutManager(new GridLayoutManager(getContext(),1));
        mBinder.rlProjectManage.setAdapter(projectManageRecycViewAdapter);

        Map<String,String> map = new HashMap<>(0);
        if (tag==0){
            map.put("scId", scId +"");
        }else if (tag==1){
            map.put("scId", scId +"");
            map.put("projectTag", projectId +"");
        }
        mControl.getQueryProjects(this,map,1);



        initData();
    }

    @Override
    public void onBeforeRequest(int tag) {

    }

    @Override
    public void onSuccess(Object bean, int tag) {
        switch (tag) {
            case 1:
                ArrayList<ProjectManageEntity.DataBean> projectManageEntity = (ArrayList<ProjectManageEntity.DataBean>)bean;
                projectManageRecycViewAdapter.setList(projectManageEntity);

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

    }

    @Override
    public void initRunable() {

    }

}
