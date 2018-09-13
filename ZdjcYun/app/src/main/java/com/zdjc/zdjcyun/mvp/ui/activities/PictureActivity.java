package com.zdjc.zdjcyun.mvp.ui.activities;


import com.zdjc.zdjcyun.R;
import com.zdjc.zdjcyun.base.BaseActivity;
import com.zdjc.zdjcyun.databinding.ActivityVideoPictureBinding;
import com.zdjc.zdjcyun.mvp.viewmodel.impl.VideoOrPictureModel;


public class PictureActivity extends BaseActivity<ActivityVideoPictureBinding,VideoOrPictureModel> {

    @Override
    public int getLayoutId() {
        setWindowStatusBarColor(this, R.color.theme_color);
        return R.layout.activity_video_picture;
    }

    @Override
    public void initView() {

    }

}
