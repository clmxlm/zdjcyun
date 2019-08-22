package com.zdjc.zdjcyun.mvp.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.zdjc.zdjcyun.R;
import com.zdjc.zdjcyun.base.BaseFragment;
import com.zdjc.zdjcyun.databinding.FragmentBimBinding;
import com.zdjc.zdjcyun.databinding.FragmentVideoBinding;
import com.zdjc.zdjcyun.mvp.viewmodel.impl.BimModel;
import com.zdjc.zdjcyun.mvp.viewmodel.impl.VideoModel;

public class VideoFragment extends BaseFragment<FragmentVideoBinding,VideoModel> {

    public static final String ARG_PAGE="ARG_PAGE";
    public String mPage;

    public static VideoFragment newInstance(String page){
        VideoFragment bimFragment=new VideoFragment();
        Bundle bundle=new Bundle();
        bundle.putString(ARG_PAGE,page);
        bimFragment.setArguments(bundle);
        return bimFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPage=getArguments().getString(ARG_PAGE);
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_video;
    }

    @Override
    public void initView() {

    }
}
