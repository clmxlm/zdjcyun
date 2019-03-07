package com.zdjc.zdjcyun.mvp.viewmodel.impl;


import android.support.v7.widget.GridLayoutManager;
import android.view.View;

import com.zdjc.zdjcyun.app.Constant;
import com.zdjc.zdjcyun.banner.BannerConfig;
import com.zdjc.zdjcyun.banner.GlideImageLoader;
import com.zdjc.zdjcyun.base.BaseModel;
import com.zdjc.zdjcyun.databinding.ActivityProjectTestDetailBinding;
import com.zdjc.zdjcyun.mvp.entity.ImageEntity;
import com.zdjc.zdjcyun.mvp.presenter.impl.TestProjectDetailPresenterImpl;
import com.zdjc.zdjcyun.mvp.ui.activities.MapActivity;
import com.zdjc.zdjcyun.mvp.ui.activities.MeasuringPointActivity;
import com.zdjc.zdjcyun.mvp.ui.activities.PictureActivity;
import com.zdjc.zdjcyun.mvp.ui.activities.ProjectDetailActivity;
import com.zdjc.zdjcyun.mvp.ui.activities.ProjectDetailTestActivity;
import com.zdjc.zdjcyun.mvp.ui.activities.ReportActivity;
import com.zdjc.zdjcyun.mvp.ui.activities.TextViewActivity;
import com.zdjc.zdjcyun.mvp.ui.activities.VideoActivity;
import com.zdjc.zdjcyun.mvp.ui.activities.WarningMessageActivity;
import com.zdjc.zdjcyun.mvp.ui.adapter.ProjectDetailTestRecycViewAdapter;
import com.zdjc.zdjcyun.mvp.viewmodel.ITestProjectDetailModel;
import com.zdjc.zdjcyun.util.PreferenceUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class TestProjectDetailModel extends BaseModel<ActivityProjectTestDetailBinding,TestProjectDetailPresenterImpl> implements ITestProjectDetailModel {

    private boolean or = false;

    @Override
    public void onCreate() {
        mBinder.include.tvTitle.setText("项目详情");
        mBinder.include.imgbtnBack.setVisibility(View.VISIBLE);
        mBinder.include.imgbtnBack.setOnClickListener(v -> ((ProjectDetailTestActivity)UI).finish());

        HashMap<String, String> params = new HashMap<>(0);
        int projectId = PreferenceUtils.getInt(getContext(), "projectId");
        params.put("projectId", projectId +"");
        mControl.getProjectOnSiteImage(this,params,1);

        inData();
    }

    private void inData(){

        ArrayList<String> string = new ArrayList<>();
        string.add("地图位置");
        string.add("项目概况");
        string.add("曲线信息");
        string.add("测点信息");
        string.add("告警信息");
        string.add("视频监控");
        string.add("文档管理");

        ProjectDetailTestRecycViewAdapter projectDetailTestRecycViewAdapter = new ProjectDetailTestRecycViewAdapter(getContext());
        mBinder.rlProjectDec.setLayoutManager(new GridLayoutManager(getContext(),3));
        projectDetailTestRecycViewAdapter.setDataList(string);
        mBinder.rlProjectDec.setAdapter(projectDetailTestRecycViewAdapter);

        projectDetailTestRecycViewAdapter.setOnItemClickListener((view, position) -> {
            switch (position){
                case 0:
                    ((ProjectDetailTestActivity)UI).intent2Activity(MapActivity.class);
                    break;
                case 1:
                    ((ProjectDetailTestActivity)UI).intent2Activity(TextViewActivity.class);
                    break;
                case 2:
                    ((ProjectDetailTestActivity)UI).intent2Activity(ProjectDetailActivity.class);
                    break;
                case 3:
                    ((ProjectDetailTestActivity)UI).intent2Activity(MeasuringPointActivity.class);
                    break;
                case 4:
                    ((ProjectDetailTestActivity)UI).intent2Activity(WarningMessageActivity.class);
                    break;
                case 5:
                    if (or){
                        ((ProjectDetailTestActivity)UI).intent2Activity(VideoActivity.class);
                        or = false;
                    }else {
                        ((ProjectDetailTestActivity)UI).intent2Activity(PictureActivity.class);
                        or = true;
                    }
                    break;
                case 6:
                    ((ProjectDetailTestActivity)UI).intent2Activity(ReportActivity.class);
                    break;
                default:
                    break;
            }
        });
    }

    @Override
    public void onBeforeRequest(int tag) {

    }

    @Override
    public void onSuccess(Object bean, int tag) {
        switch (tag) {
            case 1:
                List<ImageEntity.DataBean> imageData = (List<ImageEntity.DataBean>)bean;
                List<String> imageUrls=new ArrayList<>();
                List<String> titles=new ArrayList<>();
                if (imageData.size()>0){
                    for (ImageEntity.DataBean imageDatum : imageData) {
                        imageUrls.add(Constant.IMAGE_URL+imageDatum.getImageUrl());
                        titles.add(imageDatum.getImageDescription());
                    }
                }else {
                        imageUrls.add(Constant.IMAGE_URL);
                        titles.add("暂无现场图");
                }
                mBinder.banner.setImages(imageUrls)
                        .setBannerTitles(titles)
                        .setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE)
                        .setImageLoader(new GlideImageLoader())
                        .start();
                break;
                default:
                    break;
        }
    }

    @Override
    public void onError(String errorMsg, int code,int tag) {

    }

}
