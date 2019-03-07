package com.zdjc.zdjcyun.mvp.viewmodel.impl;


import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zdjc.zdjcyun.app.Constant;
import com.zdjc.zdjcyun.base.BaseModel;
import com.zdjc.zdjcyun.databinding.ActivityVideoPictureBinding;
import com.zdjc.zdjcyun.mvp.entity.PictureEntity;
import com.zdjc.zdjcyun.mvp.presenter.impl.PicturePresenterImpl;
import com.zdjc.zdjcyun.mvp.ui.activities.PictureActivity;
import com.zdjc.zdjcyun.mvp.viewmodel.IPictureModel;
import com.zdjc.zdjcyun.photoview.PhotoView;
import com.zdjc.zdjcyun.util.ImageLoaderUtils;
import com.zdjc.zdjcyun.util.PreferenceUtils;
import com.zdjc.zdjcyun.util.ScreenUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class PictureModel extends BaseModel<ActivityVideoPictureBinding,PicturePresenterImpl> implements IPictureModel,ViewPager.OnPageChangeListener{

    private ArrayList<String> imageUrls = new ArrayList<>();
    private TextView imageNumber;
    private String id,imageTypeName;
    private ArrayList<PictureEntity.DataBean> dataBeans;

    @Override
    public void onCreate() {
        inData();
    }

    public void inData(){

        id = PreferenceUtils.getInt(getContext(), "sectorId") + "";
        imageTypeName = PreferenceUtils.getInt(getContext(), "imageTypeName") + "";

        Map<String,String> map = new HashMap<>(0);
        map.put("sectorId", id);
        map.put("imageType",3+"");
        map.put("imageTypeName",imageTypeName+"");
        mControl.getQueryImageType(this,map,1);


        mBinder.include.imgbtnBack.setVisibility(View.VISIBLE);
        mBinder.include.imgbtnBack.setOnClickListener(v -> ((PictureActivity)UI).finish());


    }

    @Override
    public void onBeforeRequest(int tag) {

    }

    @Override
    public void onSuccess(Object bean, int tag) {
        switch (tag) {
            case 1:
                dataBeans = (ArrayList<PictureEntity.DataBean>)bean;
                for (PictureEntity.DataBean dataBean : dataBeans) {
                    imageUrls.add(Constant.IMAGE_URL+dataBean.getImageUrl());
                }
                imageNumber = mBinder.imageNumber;
                SamplePagerAdapter samplePagerAdapter = new SamplePagerAdapter();
                mBinder.hackyViewPager.setAdapter(samplePagerAdapter);
                mBinder.hackyViewPager.addOnPageChangeListener(this);
                imageNumber.setText(String.format("%d/%d", 1, imageUrls.size()));
                mBinder.hackyViewPager.setCurrentItem(0);
                mBinder.include.tvTitle.setText(dataBeans.get(0).getImageDescription());
                break;
            default:
                break;
        }
    }

    @Override
    public void onError(String errorMsg, int code,int tag) {

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
        imageNumber.setText(String.format("%d/%d", position + 1, imageUrls.size()));
        mBinder.include.tvTitle.setText(dataBeans.get(position).getImageDescription());
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

}
