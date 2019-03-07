package com.zdjc.zdjcyun.mvp.viewmodel.impl;


import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.support.v7.widget.GridLayoutManager;
import android.view.View;
import android.widget.ImageView;

import com.shuyu.gsyvideoplayer.GSYVideoManager;
import com.shuyu.gsyvideoplayer.utils.OrientationUtils;
import com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer;
import com.zdjc.zdjcyun.R;
import com.zdjc.zdjcyun.base.BaseModel;
import com.zdjc.zdjcyun.databinding.FragmentTabBinding;
import com.zdjc.zdjcyun.mvp.entity.MeasuringPointEntity;
import com.zdjc.zdjcyun.mvp.presenter.impl.TabPresenterImpl;
import com.zdjc.zdjcyun.mvp.ui.activities.MeasuringPointActivity;
import com.zdjc.zdjcyun.mvp.ui.adapter.MeasuringPointLeftRecycViewAdapter;
import com.zdjc.zdjcyun.mvp.ui.adapter.MeasuringPointMidlleRecycViewAdapter;
import com.zdjc.zdjcyun.mvp.ui.adapter.MeasuringPointRightRecycViewAdapter;
import com.zdjc.zdjcyun.mvp.viewmodel.ITabModel;
import com.zdjc.zdjcyun.util.PreferenceUtils;

import java.util.HashMap;
import java.util.List;

public class TabModel extends BaseModel<FragmentTabBinding,TabPresenterImpl> implements ITabModel {

    StandardGSYVideoPlayer videoPlayer;

    OrientationUtils orientationUtils;

    private MeasuringPointLeftRecycViewAdapter measuringPointLeftRecycViewAdapter;
    private MeasuringPointRightRecycViewAdapter measuringPointRightRecycViewAdapter;
    private MeasuringPointMidlleRecycViewAdapter measuringPointMidlleRecycViewAdapter;
    private List<MeasuringPointEntity.DataBean> pointList;
    private int leftPosition = 0;
    @Override
    public void onCreate() {
        HashMap<String, String> params = new HashMap<>(0);
        String projectId = PreferenceUtils.getInt(getContext(),"projectId")+"";
        params.put("projectId",projectId);
        mControl.getMeasuringPointMsg(this,params,1);
        measuringPointLeftRecycViewAdapter = new MeasuringPointLeftRecycViewAdapter(getContext());
        measuringPointRightRecycViewAdapter = new MeasuringPointRightRecycViewAdapter(getContext());
        measuringPointMidlleRecycViewAdapter = new MeasuringPointMidlleRecycViewAdapter(getContext());
        initView();
    }

    @Override
    public void onBeforeRequest(int tag) {
        UI.showWaitDialog();
    }

    @Override
    public void onSuccess(Object bean, int tag) {
/**
 * 成功后到这里
 */
        switch (tag) {
            case 1:
                pointList = (List<MeasuringPointEntity.DataBean>)bean;

                measuringPointLeftRecycViewAdapter.setDataList(pointList);
                mBinder.rlLeft.setAdapter(measuringPointLeftRecycViewAdapter);

                measuringPointRightRecycViewAdapter.setDataList(pointList.get(0).getDetections());
                mBinder.rlMiddle.setAdapter(measuringPointRightRecycViewAdapter);

                measuringPointMidlleRecycViewAdapter.setDataList(pointList.get(0).getDetections());
                mBinder.rlRight.setAdapter(measuringPointMidlleRecycViewAdapter);
                break;
            default:
                break;
        }
    }

    @Override
    public void onError(String errorMsg, int code,int tag) {
        if (code==2){
            ((MeasuringPointActivity)UI).finish();
        }
    }

    private void initView() {

        mBinder.rlLeft.setLayoutManager(new GridLayoutManager(getContext(),1));
        mBinder.rlMiddle.setLayoutManager(new GridLayoutManager(getContext(),1));
        mBinder.rlRight.setLayoutManager(new GridLayoutManager(getContext(),1));


        measuringPointLeftRecycViewAdapter.setOnItemClickListener((view, position) -> {
            measuringPointLeftRecycViewAdapter.getPosition(position);
            measuringPointLeftRecycViewAdapter.notifyDataSetChanged();
            measuringPointRightRecycViewAdapter.setDataList(pointList.get(position).getDetections());
            measuringPointRightRecycViewAdapter.notifyDataSetChanged();
            leftPosition = position;
        });


        videoPlayer =  mBinder.videoPlayer;
        //rtmp://112.74.35.181:10244/oflaDemo/test  rtsp:admin:hnzdjc123@10.88.89.108:554/cam/realmonitor?channel=1&subtype=0
        String source1 = "rtmp://112.74.35.181:10244/oflaDemo/test";
        videoPlayer.setUp(source1, true, "");

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
