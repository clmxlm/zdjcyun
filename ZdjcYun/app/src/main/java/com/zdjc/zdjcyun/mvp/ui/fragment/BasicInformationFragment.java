package com.zdjc.zdjcyun.mvp.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.zdjc.zdjcyun.R;
import com.zdjc.zdjcyun.base.BaseFragment;
import com.zdjc.zdjcyun.databinding.FragmentBasicInformationBinding;
import com.zdjc.zdjcyun.mvp.viewmodel.impl.BasicInformationModel;

public class BasicInformationFragment extends BaseFragment<FragmentBasicInformationBinding,BasicInformationModel> {

    public static final String ARG_PAGE="ARG_PAGE";
    public String mPage;

    public static BasicInformationFragment newInstance(String page){
        BasicInformationFragment basicInformationFragment=new BasicInformationFragment();
        Bundle bundle=new Bundle();
        bundle.putString(ARG_PAGE,page);
        basicInformationFragment.setArguments(bundle);
        return basicInformationFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPage=getArguments().getString(ARG_PAGE);
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_basic_information;
    }

    @Override
    public void initView() {

    }
}
