package com.zdjc.zdjcyun.mvp.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.zdjc.zdjcyun.R;
import com.zdjc.zdjcyun.base.BaseFragment;
import com.zdjc.zdjcyun.databinding.FragmentProjectImagesBinding;
import com.zdjc.zdjcyun.mvp.viewmodel.impl.ProjectImagesModel;

public class ProjectImagesFragment extends BaseFragment<FragmentProjectImagesBinding,ProjectImagesModel> {

    public static final String ARG_PAGE="ARG_PAGE";
    public String mPage;

    public static ProjectImagesFragment newInstance(String page){
        ProjectImagesFragment projectImagesFragment=new ProjectImagesFragment();
        Bundle bundle=new Bundle();
        bundle.putString(ARG_PAGE,page);
        projectImagesFragment.setArguments(bundle);
        return projectImagesFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPage=getArguments().getString(ARG_PAGE);
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_project_images;
    }

    @Override
    public void initView() {

    }
}
