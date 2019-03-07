package com.zdjc.zdjcyun.mvp.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.zdjc.zdjcyun.R;
import com.zdjc.zdjcyun.base.BaseFragment;
import com.zdjc.zdjcyun.databinding.FragmentDataMonitoringBinding;
import com.zdjc.zdjcyun.mvp.viewmodel.impl.DataMonitoringModel;

public class DataMonitoringFragment extends BaseFragment<FragmentDataMonitoringBinding,DataMonitoringModel> {

    public static final String ARG_PAGE="ARG_PAGE";
    public String mPage;

    public static DataMonitoringFragment newInstance(String page){
        DataMonitoringFragment dataMonitoringFragment=new DataMonitoringFragment();
        Bundle bundle=new Bundle();
        bundle.putString(ARG_PAGE,page);
        dataMonitoringFragment.setArguments(bundle);
        return dataMonitoringFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPage=getArguments().getString(ARG_PAGE);
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_data_monitoring;
    }

    @Override
    public void initView() {

    }
}
