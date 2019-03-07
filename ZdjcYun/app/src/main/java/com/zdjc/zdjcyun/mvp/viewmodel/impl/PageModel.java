package com.zdjc.zdjcyun.mvp.viewmodel.impl;


import android.content.Intent;
import android.os.Handler;
import android.view.View;
import android.widget.Toast;

import com.zdjc.zdjcyun.app.BaseApplication;
import com.zdjc.zdjcyun.base.BaseModel;
import com.zdjc.zdjcyun.databinding.FragmentPageBinding;
import com.zdjc.zdjcyun.event.PullLoadMoreRecyclerView;
import com.zdjc.zdjcyun.mvp.entity.PageReportEntity;
import com.zdjc.zdjcyun.mvp.presenter.impl.PagePresenterImpl;
import com.zdjc.zdjcyun.mvp.ui.activities.PDFActivity;
import com.zdjc.zdjcyun.mvp.ui.adapter.ReportRecycleViewAdapter;
import com.zdjc.zdjcyun.mvp.ui.fragment.PageFragment;
import com.zdjc.zdjcyun.mvp.viewmodel.IPageModel;
import com.zdjc.zdjcyun.util.PreferenceUtils;

import java.util.HashMap;
import java.util.Map;



public class PageModel extends BaseModel<FragmentPageBinding, PagePresenterImpl> implements IPageModel {

    private ReportRecycleViewAdapter adapter;
    private PageReportEntity.DataBean dataBean;
    private PullLoadMoreRecyclerView mPullLoadMoreRecyclerView;
    private int page = 1;
    private PageFragment pageFragment;
    private String id;
    @Override
    public void onCreate() {
        pageFragment = (PageFragment) UI;
        id = PreferenceUtils.getInt(BaseApplication.getContext(),"projectId")+"";
        intView();
    }

    private void intView() {
        mPullLoadMoreRecyclerView = mBinder.pullLoadMoreRecyclerView;
        adapter = new ReportRecycleViewAdapter(getContext());
        mPullLoadMoreRecyclerView.setGridLayout(1);
        mPullLoadMoreRecyclerView.setAdapter(adapter);
        request(page);

        if (mPullLoadMoreRecyclerView!=null){
            mPullLoadMoreRecyclerView.setOnPullLoadMoreListener(new PullLoadMoreRecyclerView.PullLoadMoreListener() {
                @Override
                public void onRefresh() {
                    setRefresh();
                }

                @Override
                public void onLoadMore() {
                    getData();
                }
            });
        }

        if (adapter!=null){
            adapter.setOnItemClickListener((view, position) -> {
                Intent intent = new Intent(getContext(),PDFActivity.class);
                intent.putExtra("reportId",dataBean.getDataList().get(position).getId());
                intent.putExtra("report","noFree");
                getContext().startActivity(intent);
            });
        }

    }

    @Override
    public void onBeforeRequest(int tag) {
        UI.showWaitDialog();
    }

    @Override
    public void onSuccess(Object bean, int tag) {
        switch (tag) {
            case 1:
                dataBean = (PageReportEntity.DataBean) bean;
                if (dataBean.getDataList().size()>0){
                    if (page == 1) {
                        adapter.setList(dataBean.getDataList());
                    } else {
                        adapter.addList(dataBean.getDataList());
                    }
                }else if (dataBean.getDataList().size()==0){
                    if (page>1){
                        mBinder.tvNoReport.setVisibility(View.GONE);
                        mPullLoadMoreRecyclerView.setVisibility(View.VISIBLE);
                        Toast.makeText(getContext(),"已经是最后一页了!",Toast.LENGTH_SHORT).show();
                    }else {
                        mPullLoadMoreRecyclerView.setVisibility(View.GONE);
                        mBinder.tvNoReport.setVisibility(View.VISIBLE);
                    }
                }
                if (mPullLoadMoreRecyclerView != null) {
                    mPullLoadMoreRecyclerView.setPullLoadMoreCompleted();
                }
                break;
                default:
                    break;
        }
    }

    @Override
    public void onError(String errorMsg,int code, int tag) {
        if (mPullLoadMoreRecyclerView != null) {
            mPullLoadMoreRecyclerView.setPullLoadMoreCompleted();
        }
    }
    private void getData() {
        new Handler().postDelayed(() -> {
            page++;
            request(page);
        }, 1000);
    }

    private void setRefresh() {
        mPullLoadMoreRecyclerView.setHasMore(true);
        page = 1;
        request(page);
    }

    private  void request(int page){
        Map<String, String> map = new HashMap<>(0);
        map.put("projectId", id);
        map.put("pageIndex", page+"");
        map.put("pageSize", "10");
        map.put("reportType",pageFragment.mPage);
        mControl.getReportMessage(PageModel.this, map, 1);
    }

}
