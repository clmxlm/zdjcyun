package com.zdjc.zdjcyun.mvp.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.zdjc.zdjcyun.R;
import com.zdjc.zdjcyun.base.BaseFragment;
import com.zdjc.zdjcyun.databinding.FragmentBimBinding;
import com.zdjc.zdjcyun.databinding.FragmentDataAnalysisBinding;
import com.zdjc.zdjcyun.mvp.viewmodel.impl.BimModel;
import com.zdjc.zdjcyun.mvp.viewmodel.impl.DataAnalysisModel;

public class DataAnalysisFragment extends BaseFragment<FragmentDataAnalysisBinding,DataAnalysisModel> {

    public static final String ARG_PAGE="ARG_PAGE";
    public String mPage;

    public static DataAnalysisFragment newInstance(String page){
        DataAnalysisFragment dataAnalysisFragment=new DataAnalysisFragment();
        Bundle bundle=new Bundle();
        bundle.putString(ARG_PAGE,page);
        dataAnalysisFragment.setArguments(bundle);
        return dataAnalysisFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPage=getArguments().getString(ARG_PAGE);
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_data_analysis;
    }

    @Override
    public void initView() {

    }
}
