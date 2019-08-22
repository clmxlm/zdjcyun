package com.zdjc.zdjcyun.mvp.viewmodel.impl;


import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.support.v7.widget.GridLayoutManager;
import android.view.View;
import android.widget.ImageView;

import com.github.jdsjlzx.interfaces.OnItemClickListener;
import com.shuyu.gsyvideoplayer.GSYVideoManager;
import com.shuyu.gsyvideoplayer.utils.OrientationUtils;
import com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer;
import com.zdjc.zdjcyun.R;
import com.zdjc.zdjcyun.base.BaseModel;
import com.zdjc.zdjcyun.databinding.FragmentTabBinding;
import com.zdjc.zdjcyun.mvp.entity.VideoEntity;
import com.zdjc.zdjcyun.mvp.presenter.impl.TabPresenterImpl;
import com.zdjc.zdjcyun.mvp.ui.adapter.MeasuringPointLeftRecycViewAdapter;
import com.zdjc.zdjcyun.mvp.ui.adapter.MeasuringPointMidlleRecycViewAdapter;
import com.zdjc.zdjcyun.mvp.ui.adapter.MeasuringPointRightRecycViewAdapter;
import com.zdjc.zdjcyun.mvp.viewmodel.ITabModel;
import com.zdjc.zdjcyun.util.PreferenceUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class TabModel extends BaseModel<FragmentTabBinding,TabPresenterImpl> implements ITabModel {

    private StandardGSYVideoPlayer videoPlayer;
    private OrientationUtils orientationUtils;

    private MeasuringPointMidlleRecycViewAdapter measuringPointMidlleRecycViewAdapter;
    private String urlHead;
    private ArrayList<VideoEntity.DataBean.VideoVosBean> videoVos;

    @Override
    public void onCreate() {
        String tag = PreferenceUtils.getString(getContext(),"video");
        int id = PreferenceUtils.getInt(getContext(),"sectorId");
        if ("".equals(tag)){
            mBinder.llVideoTitle.setVisibility(View.GONE);
        }else if ("tag".equals(tag)){
            mBinder.llVideoTitle.setVisibility(View.VISIBLE);
        }

        initView();

        Map<String, String> mapType = new HashMap<>(0);
        mapType.put("sectorId", 1+"");
        mControl.getQueryVideoBySectorId(TabModel.this, mapType, 1);

    }

    @Override
    public void onBeforeRequest(int tag) {
        UI.showWaitDialog();
    }

    @Override
    public void onSuccess(Object bean, int tag) {
        switch (tag) {
            case 1:
                VideoEntity.DataBean data = (VideoEntity.DataBean)bean;
                urlHead = data.getLiveUrl();
                videoVos = data.getVideoVos();
                mBinder.tvVideoName.setText("监测点-"+videoVos.get(0).getMonitorPointNumber());
                measuringPointMidlleRecycViewAdapter.setDataList(data.getVideoVos());
                break;
            default:
                break;
        }
    }

    @Override
    public void onError(String errorMsg, int code,int tag) {

    }

    private void initView() {
        mBinder.rlLeft.setLayoutManager(new GridLayoutManager(getContext(),4));
        measuringPointMidlleRecycViewAdapter = new MeasuringPointMidlleRecycViewAdapter(getContext());
        mBinder.rlLeft.setAdapter(measuringPointMidlleRecycViewAdapter);

        videoPlayer =  mBinder.videoPlayer;
        //rtmp://112.74.35.181:10244/oflaDemo/test  rtsp:admin:hnzdjc123@10.88.89.108:554/cam/realmonitor?channel=1&subtype=0


        //增加封面
        ImageView imageView = new ImageView(getContext());
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setImageResource(R.mipmap.error);
        videoPlayer.setThumbImageView(imageView);
        //增加title
        videoPlayer.getTitleTextView().setVisibility(View.VISIBLE);
        //设置返回键
        videoPlayer.getBackButton().setVisibility(View.VISIBLE);
        //设置旋转
        orientationUtils = new OrientationUtils((Activity) getContext(), videoPlayer);
        //设置全屏按键功能,这是使用的是选择屏幕，而不是全屏
        videoPlayer.getFullscreenButton().setOnClickListener(v -> orientationUtils.resolveByClick());
        //是否可以滑动调整
        videoPlayer.setIsTouchWiget(true);
        //设置返回按键功能
        videoPlayer.getBackButton().setVisibility(View.GONE);
        videoPlayer.getBackButton().setOnClickListener(v -> {
            if (orientationUtils.getScreenType() == ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE) {
                videoPlayer.getFullscreenButton().performClick();
                return;
            }
            //释放所有
            videoPlayer.setVideoAllCallBack(null);
        });
        videoPlayer.startPlayLogic();


        measuringPointMidlleRecycViewAdapter.setOnItemClickListener((view, position) -> {
            String source1 = urlHead+videoVos.get(position).getLiveVideoUrl();
            mBinder.tvVideoName.setText("监测点-"+videoVos.get(position).getMonitorPointNumber());
            videoPlayer.setUp(source1, true, "");
            videoPlayer.startPlayLogic();
        });
    }



    @Override
    public void onPause() {
        super.onPause();
        videoPlayer.onVideoPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        videoPlayer.onVideoResume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        GSYVideoManager.releaseAllVideos();
        if (orientationUtils != null){
            orientationUtils.releaseListener();
        }
    }

}
