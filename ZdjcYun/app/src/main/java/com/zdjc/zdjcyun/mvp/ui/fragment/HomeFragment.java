package com.zdjc.zdjcyun.mvp.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.blankj.utilcode.utils.LogUtils;
import com.zdjc.zdjcyun.R;
import com.zdjc.zdjcyun.base.BaseFragment;
import com.zdjc.zdjcyun.databinding.FragmentHomeBinding;
import com.zdjc.zdjcyun.mvp.entity.AllProjectListEntity;
import com.zdjc.zdjcyun.mvp.viewmodel.impl.HomeModel;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends BaseFragment<FragmentHomeBinding,HomeModel> {

    private OnCallBackProjects onCallBackProjects;
    private OnCallBackMapTag onCallBackMapTag;

    public HomeFragment() {
        // Required empty public constructor
    }

    public static HomeFragment newInstance(String s) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void onAttachToContext(Context context) {
        try {
            onCallBackProjects=(OnCallBackProjects)context;
            onCallBackMapTag = (OnCallBackMapTag)context;
        }catch (Exception e){
            LogUtils.i(e.getMessage());
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }


    @Override
    public int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    public void initView() {

    }


    @Override
    public void onDetach() {
        super.onDetach();
        onCallBackProjects = null;
        onCallBackMapTag = null;
    }

    public OnCallBackProjects getOnCallBackProjects() {
        return onCallBackProjects;
    }

    public void setOnCallBackProjects(OnCallBackProjects onCallBackProjects) {
        this.onCallBackProjects = onCallBackProjects;
    }

    public interface OnCallBackProjects {
        void onProjects(List<AllProjectListEntity.DataBean> projects);
    }

    public interface OnCallBackMapTag {
        void onMaps(int position);
    }

    public OnCallBackMapTag getOnCallBackMapTag() {
        return onCallBackMapTag;
    }

    public void setOnCallBackMapTag(OnCallBackMapTag onCallBackMapTag) {
        this.onCallBackMapTag = onCallBackMapTag;
    }
}
