package com.zdjc.zdjcyun.mvp.viewmodel.impl;


import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;

import com.blankj.utilcode.utils.ToastUtils;
import com.zdjc.zdjcyun.R;
import com.zdjc.zdjcyun.base.BaseModel;
import com.zdjc.zdjcyun.databinding.ActivityPdfBinding;
import com.zdjc.zdjcyun.mvp.presenter.impl.PDFPresenterImpl;
import com.zdjc.zdjcyun.mvp.ui.activities.PDFActivity;
import com.zdjc.zdjcyun.mvp.viewmodel.IPDFModel;
import com.zdjc.zdjcyun.util.PreferenceUtils;

import es.voghdev.pdfviewpager.library.RemotePDFViewPager;
import es.voghdev.pdfviewpager.library.adapter.PDFPagerAdapter;
import es.voghdev.pdfviewpager.library.remote.DownloadFile;
import es.voghdev.pdfviewpager.library.util.FileUtil;


public class PDFModel extends BaseModel<ActivityPdfBinding,PDFPresenterImpl> implements IPDFModel ,DownloadFile.Listener{

    private RemotePDFViewPager remotePDFViewPager;
    private String mUrl ="";

    @Override
    public void onCreate() {
        intView();
    }

    private void intView() {
        Intent intent = ((PDFActivity)UI).getIntent();
        String tag = intent.getStringExtra("report");
        int reportID = intent.getIntExtra("reportId",-1);
        String reportUrl = intent.getStringExtra("reportUrl");
        String token = PreferenceUtils.getString(getContext(),"token");
        if ("free".equals(tag)){
            mUrl = "http://123.207.88.210/"+reportUrl;
        }else if ("noFree".equals(tag)){
            mUrl = "http://123.207.88.210:8081/download/downloadReportAuthorityFor?Authorization=Bearer "+token+"&id="+reportID;
        }
        mBinder.include.tvTitle.setText("报告详情");
        mBinder.include.imgbtnBack.setVisibility(View.VISIBLE);
        mBinder.include.imgbtnBack.setOnClickListener(v -> ((PDFActivity)UI).finish());
        mBinder.pbBar.setVisibility(View.VISIBLE);
        mBinder.pdfViewPager.setVisibility(View.VISIBLE);
        setDownloadListener();
    }

    /*设置监听*/
    protected void setDownloadListener() {
        final DownloadFile.Listener listener = this;
        remotePDFViewPager = new RemotePDFViewPager(getContext(), mUrl, listener);
        remotePDFViewPager.setId(R.id.pdfViewPager);
    }

    @Override
    public void onSuccess(String url, String destinationPath) {
        mBinder.pbBar.setVisibility(View.GONE);
        PDFPagerAdapter pdfPagerAdapter = new PDFPagerAdapter(getContext(), FileUtil.extractFileNameFromURL(url));
        remotePDFViewPager.setAdapter(pdfPagerAdapter);
        updateLayout();
    }

    /*更新视图*/
    private void updateLayout() {
        mBinder.llReport.removeAllViewsInLayout();
        mBinder.llReport.addView(remotePDFViewPager, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
    }

    @Override
    public void onFailure(Exception e) {
        mBinder.pbBar.setVisibility(View.GONE); ToastUtils.showLongToast("数据加载错误");
    }

    @Override
    public void onProgressUpdate(int progress, int total) {

    }


    @Override
    public void onBeforeRequest(int tag) {

    }

    @Override
    public void onSuccess(Object bean, int tag) {

    }

    @Override
    public void onError(String errorMsg, int code,int tag) {

    }

}
