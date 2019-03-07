package com.zdjc.zdjcyun.mvp.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.zdjc.zdjcyun.R;
import com.zdjc.zdjcyun.base.BaseFragment;
import com.zdjc.zdjcyun.databinding.FragmentHazardsBinding;
import com.zdjc.zdjcyun.mvp.viewmodel.impl.HazardsModel;

public class HazardsFragment extends BaseFragment<FragmentHazardsBinding,HazardsModel> {

    public static final String ARG_PAGE="ARG_PAGE";
    public String mPage;

    public static HazardsFragment newInstance(String page){
        HazardsFragment bimFragment=new HazardsFragment();
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
        return R.layout.fragment_hazards;
    }

    @Override
    public void initView() {

    }
}
