package com.zdjc.zdjcyun.mvp.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.zdjc.zdjcyun.R;
import com.zdjc.zdjcyun.base.BaseFragment;
import com.zdjc.zdjcyun.databinding.FragmentAlarmDetailBinding;
import com.zdjc.zdjcyun.mvp.viewmodel.impl.AlarmDetailModel;

public class AlarmDetailFragment extends BaseFragment<FragmentAlarmDetailBinding,AlarmDetailModel> {

    public static final String ARG_PAGE="ARG_PAGE";
    public String mPage;

    public static AlarmDetailFragment newInstance(String page){
        AlarmDetailFragment alarmDetailFragment=new AlarmDetailFragment();
        Bundle bundle=new Bundle();
        bundle.putString(ARG_PAGE,page);
        alarmDetailFragment.setArguments(bundle);
        return alarmDetailFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPage=getArguments().getString(ARG_PAGE);
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_alarm_detail;
    }

    @Override
    public void initView() {

    }

}
