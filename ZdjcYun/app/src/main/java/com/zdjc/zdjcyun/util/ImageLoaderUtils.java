package com.zdjc.zdjcyun.util;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.zdjc.zdjcyun.R;
import com.zdjc.zdjcyun.app.BaseApplication;

import java.io.File;


/**
 * Created by 95802 on 2016/3/22.
 * ImageLoader辅助类
 */
public class ImageLoaderUtils {


    public static void imageDisPlayUrl(String url, ImageView myImageView) {
        Glide.with(BaseApplication.getContext())
                .load(url)
                .crossFade()
                .dontAnimate()
//                .placeholder(R.drawable.loading_phone)
                .into(myImageView);
    }

//    public static void imageDisPlayPhoneUrl(String url, ImageView myImageView, final LoadingDialogUtils loadingDialogUtils) {
////        CustomImageSizeModel customImageRequest = new CustomImageSizeModelFutureStudio(url);
//        Glide.with(LocationApplication.getInstance())
//                .load(url)
//                .crossFade()
//                .into(new GlideDrawableImageViewTarget(myImageView) {
//                    @Override
//                    public void onResourceReady(GlideDrawable drawable, GlideAnimation anim) {
//                        super.onResourceReady(drawable, anim);
//                        //在这里添加一些图片加载完成的操作
//                        if (loadingDialogUtils.isShow()){
//                            loadingDialogUtils.dismiss();
//                        }
//                    }
//                });
//    }
//    public static void imageDisPlayTeesDiyUrl(String url, ImageView myImageView, final LoadingDialogUtils loadingDialogUtils) {
////        CustomImageSizeModel customImageRequest = new CustomImageSizeModelFutureStudio(url);
//        Glide.with(LocationApplication.getInstance())
//                .load(url)
//                .crossFade()
//                .into(new GlideDrawableImageViewTarget(myImageView) {
//                    @Override
//                    public void onResourceReady(GlideDrawable drawable, GlideAnimation anim) {
//                        super.onResourceReady(drawable, anim);
//                        //在这里添加一些图片加载完成的操作
//                        if (loadingDialogUtils.isShow()){
//                            loadingDialogUtils.dismiss();
//                        }
//                    }
//                });
//    }

//    public static void imageDisPlayFile(File url, ImageView myImageView) {
//        Glide.with(myImageView.getContext())
//                .load(url)
//                .fitCenter()
//                .diskCacheStrategy(DiskCacheStrategy.ALL)
//                .placeholder(R.drawable.loading)
//                .crossFade()
//                .dontAnimate()
//                .error(R.drawable.loading)
//                .into(myImageView);
//    }

    public static void imageDisPlayFiles(File url, ImageView myImageView) {
        Glide.with(myImageView.getContext())
                .load(url)
                .fitCenter()
                .dontAnimate()
                .into(myImageView);
    }

    public static void imageCircleDisPlayFile(File url, ImageView myImageView) {
        Glide.with(myImageView.getContext())
                .load(url)
                .fitCenter()
                .dontAnimate()
                .transform(new GlideCircleTransform(BaseApplication.getContext()))
                .crossFade()
                .into(myImageView);
    }

    public static void imageDisPlayByApp(String url, ImageView myImageView) {
        Glide.with(BaseApplication.getContext())
                .load(url)
                .crossFade()
                .dontAnimate()
                .into(myImageView);
    }
    public static void imageDisPlayTessDiyByApp(String url, ImageView myImageView) {
        Glide.with(BaseApplication.getContext())
                .load(url)
                .crossFade()
                .dontAnimate()
                .into(myImageView);
    }
    /**
     * 需求要求不缓存
     *直接跳过缓存
     * @param url
     * @param myImageView
     */
    public static void imageDisPlayByAppZj(String url, ImageView myImageView) {
        Glide.with(BaseApplication.getContext())
                .load(url)
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.NONE) //跳过硬盘缓存
                .skipMemoryCache(true) //跳过内存缓存
//                .placeholder(R.drawable.loading)
                .dontAnimate()
                .into(myImageView);
    }

    public static void imageDisPlayByAppNotAnimate(String url, ImageView myImageView) {
        Glide.with(BaseApplication.getContext())
                .load(url)
                .crossFade()
                .dontAnimate()
                .placeholder(R.mipmap.error)
                .into(myImageView);
    }

    public static void imageDisPlay(String url, ImageView myImageView) {
        Glide.with(BaseApplication.getContext())
                .load(url)
                .crossFade()
                .placeholder(R.drawable.loading)
                .dontAnimate()
                .into(myImageView);
    }

    public static void imageDisPlayNotLoadingByApp(String url, ImageView myImageView) {
        Glide.with(BaseApplication.getContext())
                .load(url)
                .crossFade()
                .into(myImageView);
    }

    public static void imageCircleDisPlayByApp(String url, ImageView myImageView) {
        Glide.with(BaseApplication.getContext())
                .load(url)
                .crossFade()
                .dontAnimate()
//                .placeholder(R.drawable.defaultheard)
                .transform(new GlideCircleTransform(BaseApplication.getContext()))
                .into(myImageView);
    }

    public static void imageDisPlayResByApp(int id, ImageView myImageView) {
        Glide.with(BaseApplication.getContext())
                .load(id)
                .crossFade()
                .placeholder(R.mipmap.error)
                .dontAnimate()
                .into(myImageView);
    }
    public static void imageDisPlayResByAppTeesDiy(int id, ImageView myImageView) {
        Glide.with(BaseApplication.getContext())
                .load(id)
                .crossFade()
                .dontAnimate()
                .transform(new GlideCircleTransform(BaseApplication.getContext()))
                .into(myImageView);
    }

    public static void imageDisPlayRes(int id, ImageView myImageView) {
        Glide.with(BaseApplication.getContext())
                .load(id)
                .crossFade()
                .dontAnimate()
//                .placeholder(R.drawable.defaultheard)
                .into(myImageView);
    }

}
