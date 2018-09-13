package com.zdjc.zdjcyun.mvp.viewmodel.impl;


import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zdjc.zdjcyun.base.BaseModel;
import com.zdjc.zdjcyun.databinding.ActivityVideoPictureBinding;
import com.zdjc.zdjcyun.mvp.presenter.impl.VideoOrPicturePresenterImpl;
import com.zdjc.zdjcyun.mvp.ui.activities.PictureActivity;
import com.zdjc.zdjcyun.mvp.viewmodel.IVideoOrPictureModel;
import com.zdjc.zdjcyun.photoview.PhotoView;
import com.zdjc.zdjcyun.util.ImageLoaderUtils;
import com.zdjc.zdjcyun.util.PreferenceUtils;
import com.zdjc.zdjcyun.util.ScreenUtil;

import java.util.ArrayList;

/**
 * Created by ali on 2017/2/20.
 */

public class VideoOrPictureModel extends BaseModel<ActivityVideoPictureBinding,VideoOrPicturePresenterImpl> implements IVideoOrPictureModel,ViewPager.OnPageChangeListener{

    private ArrayList<String> imageUrls = new ArrayList<>();
//    private ArrayList<Integer> imgheight_list = new ArrayList<>();
//    private ArrayList<Integer> imgwidth_list = new ArrayList<>();
    private TextView image_number;

    @Override
    public void onCreate() {
        inData();
    }

    public void inData(){
        for (int i = 0; i < 5; i++) {
            imageUrls.add(PreferenceUtils.getString(getContext(),"ceshi"));
        }
        mBinder.include.tvTitle.setText("抓拍或者视频");
        mBinder.include.imgbtnBack.setVisibility(View.VISIBLE);
        mBinder.include.imgbtnBack.setOnClickListener(v -> ((PictureActivity)UI).finish());

        image_number = mBinder.imageNumber;
        SamplePagerAdapter samplePagerAdapter = new SamplePagerAdapter();
        mBinder.hackyViewPager.setAdapter(samplePagerAdapter);
        mBinder.hackyViewPager.addOnPageChangeListener(this);
        image_number.setText(String.format("%d/%d", 1, imageUrls.size()));
        mBinder.hackyViewPager.setCurrentItem(0);
    }

    @Override
    public void onBeforeRequest(int tag) {

    }

    @Override
    public void onSuccess(Object bean, int tag) {

    }

    @Override
    public void onError(String errorMsg, int tag) {

    }

    class SamplePagerAdapter extends PagerAdapter {
        @Override
        public int getCount() {
            return (imageUrls == null) ? 0 : imageUrls.size();
        }

        @Override
        public View instantiateItem(ViewGroup container, int position) {
            PhotoView photoView = new PhotoView(container.getContext());
            //双击放大后单击退出预览界面（本activity）
            photoView.setOnPhotoTapListener((view, x, y) -> ((PictureActivity)UI).finish());
//            int imageheight = imgheight_list.get(position);
//            int imagewidth = imgwidth_list.get(position);
//            double The_proportion = (imageheight > imagewidth) ? (double) imagewidth / (double) imageheight : (double) imageheight / (double) imagewidth;
//            int imageHeight = (int) (The_proportion * ScreenUtil.getScreenWidth(getContext()));

            container.addView(photoView, ScreenUtil.getScreenWidth(getContext()), 300);
            ImageLoaderUtils.imageDisPlayByAppNotAnimate(imageUrls.get(position), photoView);
            return photoView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        image_number.setText(String.format("%d/%d", position + 1, imageUrls.size()));
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

}
