package com.zdjc.zdjcyun.widget.img_dots;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.AnimationDrawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.blankj.utilcode.utils.ScreenUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.Target;
import com.zdjc.zdjcyun.R;
import com.zdjc.zdjcyun.photoview.PhotoView;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class ImageLayout extends FrameLayout{

    ArrayList<PointSimple> points;

    FrameLayout layouPoints;

    PhotoView imgBg;
    OnClickListener mOnClickListener;
    Context mContext;

    public void setmOnClickListener(OnClickListener mOnClickListener) {
        this.mOnClickListener = mOnClickListener;
    }

    public ImageLayout(Context context) {
        this(context, null);
    }

    public ImageLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ImageLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context, attrs);
    }


    private void initView(Context context, AttributeSet attrs) {

        mContext = context;

        View imgPointLayout = inflate(context, R.layout.layout_imgview_point, this);

        imgBg = (PhotoView) imgPointLayout.findViewById(R.id.imgBg);

        layouPoints = (FrameLayout) imgPointLayout.findViewById(R.id.layouPoints);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
    }

    public void setImgBg(int width, int height, String imgUrl) {
        /**
         * 这就是图片的长和宽了
         */
        ViewGroup.LayoutParams lp = imgBg.getLayoutParams();
        lp.width = width;
        lp.height = height;

        imgBg.setLayoutParams(lp);

        ViewGroup.LayoutParams lp1 = layouPoints.getLayoutParams();
        lp1.width = width;
        lp1.height = height;

        layouPoints.setLayoutParams(lp1);
        Glide.with(mContext).load(imgUrl).asBitmap().into(new SimpleTarget<Bitmap>(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL) {
            @Override
            public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {

                int imageWidth = resource.getWidth();
                int imageHeight = resource.getHeight();
                int height = ScreenUtils.getScreenWidth() * imageHeight / imageWidth;
                ViewGroup.LayoutParams para = imgBg.getLayoutParams();
                para.height = height;
                para.width = ScreenUtils.getScreenWidth();
                imgBg.setImageBitmap(resource);

            }
        });

        addPoints(width, height);

    }
    /**
     * 下载图片
     * @param iconUrl
     */
    private void downloadIMG(String iconUrl) {
        int myWidth = Target.SIZE_ORIGINAL;
        int myHeight = Target.SIZE_ORIGINAL;
        Glide.with(mContext)
                .load(iconUrl)
                .asBitmap()
                .into(new SimpleTarget<Bitmap>(myWidth, myHeight) {
                    @Override
                    public void onResourceReady(Bitmap bitmap, GlideAnimation anim) {
                        if(null!=bitmap) {
                            ByteArrayOutputStream baos = new ByteArrayOutputStream();
                            bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
                            byte[] bytes=baos.toByteArray();
                            Glide.with(mContext).load(bytes).into(imgBg);
                        }
                    }
                });
    }

    public void setPoints(ArrayList<PointSimple> points) {
        this.points = points;
    }

    private void addPoints(int width, int height) {

        layouPoints.removeAllViews();
        double widthScale;
        double heightScale;
        for (int i = 0; i < points.size(); i++) {
            PointSimple pointSimple = points.get(i);
            widthScale = pointSimple.width_scale;
            heightScale = pointSimple.height_scale;
            LinearLayout view = (LinearLayout) LayoutInflater.from(mContext).inflate(R.layout.layout_img_point, this, false);
            ImageView imageView =  view.findViewById(R.id.imgPoint);
            if (points.get(i).status==0){
                //绿色 正常
                imageView.setImageDrawable(mContext.getResources().getDrawable(R.drawable.dot_point_img));
            }else if (points.get(i).status==3){
                //红色 三级告警
                imageView.setImageDrawable(mContext.getResources().getDrawable(R.drawable.dot_point_img_red));
            }else if (points.get(i).status==1){
                //黄色 一级告警
                imageView.setImageDrawable(mContext.getResources().getDrawable(R.drawable.dot_point_img_yellow));
            }else if (points.get(i).status==2){
                //橙色 二级告警
                imageView.setImageDrawable(mContext.getResources().getDrawable(R.drawable.dot_point_img_orange));
            }else if (points.get(i).status==-1){
                //灰色 监测完毕
                imageView.setImageDrawable(mContext.getResources().getDrawable(R.drawable.dot_point_img_gray));
            }else {
                //其它情况都是绿色
                imageView.setImageDrawable(mContext.getResources().getDrawable(R.drawable.dot_point_img));
            }
            imageView.setTag(i);
            AnimationDrawable animationDrawable = (AnimationDrawable) imageView.getDrawable();
            animationDrawable.start();
            LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();

            layoutParams.leftMargin = (int) (width * widthScale)-20;
            layoutParams.topMargin = (int) (height * heightScale)-20;

            imageView.setOnClickListener(mOnClickListener);

            layouPoints.addView(view, layoutParams);
        }
    }


}