package com.zdjc.zdjcyun.mvp.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.zdjc.zdjcyun.R;
import com.zdjc.zdjcyun.base.BaseFragment;
import com.zdjc.zdjcyun.databinding.FragmentBimBinding;
import com.zdjc.zdjcyun.mvp.viewmodel.impl.BimModel;

public class BIMFragment extends BaseFragment<FragmentBimBinding,BimModel> {

    public static final String ARG_PAGE="ARG_PAGE";
    public String mPage;

    public static BIMFragment newInstance(String page){
        BIMFragment bimFragment=new BIMFragment();
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
        return R.layout.fragment_bim;
    }

    @Override
    public void initView() {

    }
}
