package com.zdjc.zdjcyun.mvp.viewmodel.impl;

import android.os.Handler;
import android.view.View;
import android.widget.TextView;

import com.zdjc.zdjcyun.base.BaseModel;
import com.zdjc.zdjcyun.databinding.ActivityProjectListBinding;
import com.zdjc.zdjcyun.event.PullLoadMoreRecyclerView;
import com.zdjc.zdjcyun.mvp.entity.ProjectListEntity;
import com.zdjc.zdjcyun.mvp.presenter.impl.ProjectListPresenterImpl;
import com.zdjc.zdjcyun.mvp.ui.activities.ProjectDetailActivity;
import com.zdjc.zdjcyun.mvp.ui.activities.ProjectListActivity;
import com.zdjc.zdjcyun.mvp.ui.adapter.ProjectRecycViewAdapter;
import com.zdjc.zdjcyun.mvp.viewmodel.IProjectListModel;
import com.zdjc.zdjcyun.util.PreferenceUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ali on 2017/2/20.
 */

public class ProjectListModel extends BaseModel<ActivityProjectListBinding,ProjectListPresenterImpl> implements IProjectListModel {

    private ProjectListEntity.DataBean typeProjectList;
    private ProjectRecycViewAdapter adapter;
    private PullLoadMoreRecyclerView recyclerView;
    private TextView tv_no_goods;
    private int page = 1;

    @Override
    public void onCreate() {
        intView();
    }

    private void intView() {
        mBinder.searchView.setOnClickSearch(string -> getSearchProjectList(string)
        );

        // 5. 设置点击返回按键后的操作（通过回调接口）
        mBinder.searchView.setOnClickBack(() -> ((ProjectListActivity)UI).finish());
        tv_no_goods = mBinder.tvNoGoods;
        recyclerView = mBinder.recycleView;
        adapter = new ProjectRecycViewAdapter(getContext());
        recyclerView.setGridLayout(2);
        recyclerView.setAdapter(adapter);
        getProjectList(page);

        recyclerView.setOnPullLoadMoreListener(new PullLoadMoreRecyclerView.PullLoadMoreListener() {
            @Override
            public void onRefresh() {
                setRefresh();
            }

            @Override
            public void onLoadMore() {
                getData();
            }
        });

        adapter.setOnItemClickListener((view, position) -> {
            UI.showWaitDialog();
            ((ProjectListActivity)UI).intent2Activity(ProjectDetailActivity.class);
            PreferenceUtils.putInt(getContext(),"projectId",typeProjectList.getRows().get(position).getProjectId());
            PreferenceUtils.putString(getContext(),"projectName",typeProjectList.getRows().get(position).getProjectName());
        });
    }

    @Override
    public void onBeforeRequest(int tag) {
        UI.showWaitDialog();
    }

    @Override
    public void onSuccess(Object bean, int tag) {
        switch (tag) {
            case 1:
                typeProjectList = (ProjectListEntity.DataBean)bean;
                if (typeProjectList.getRows().size()>0){
                    if (page == 1) {
                        adapter.setList(typeProjectList.getRows());
                    } else {
                        adapter.addList(typeProjectList.getRows());
                    }
                }else if (typeProjectList.getRows().size()==0){
                    if (page>1){
                        tv_no_goods.setVisibility(View.VISIBLE);
                        recyclerView.setVisibility(View.GONE);
                    }else {
                        recyclerView.setVisibility(View.VISIBLE);
                        tv_no_goods.setVisibility(View.GONE);
                    }
                }
                if (recyclerView != null) {
                    recyclerView.setPullLoadMoreCompleted();
                }
                break;
        }
    }

    @Override
    public void onError(String errorMsg, int tag) {
        if (recyclerView != null) {
            recyclerView.setPullLoadMoreCompleted();
        }
    }

    public void getProjectList(int page){
        Map<String,String> map = new HashMap<>();
        map.put("projectType", PreferenceUtils.getInt(getContext(),"projectType")+"");
        map.put("pageNum", page+"");
        map.put("pageSize", 10+"");
        mControl.getTypeProjectList(this,map,1);
    }

    public void getSearchProjectList(String searchString){
        Map<String,String> map = new HashMap<>();
        map.put("projectType", PreferenceUtils.getInt(getContext(),"projectType")+"");
        map.put("condition", searchString);
        map.put("pageNum", 1+"");
        map.put("pageSize", 10+"");
        mControl.getSearchProjectList(this,map,1);
    }

    private void getData() {
        new Handler().postDelayed(() -> {
            page++;
            getProjectList(page);
        }, 1000);
    }

    private void setRefresh() {
        recyclerView.setHasMore(true);
        page = 1;
        getProjectList(page);
    }

    @Override
    public void onResume() {
        super.onResume();
        UI.hideWaitDialog();
    }
}
