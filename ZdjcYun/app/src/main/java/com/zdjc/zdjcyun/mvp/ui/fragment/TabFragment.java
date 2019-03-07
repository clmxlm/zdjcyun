package com.zdjc.zdjcyun.mvp.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.zdjc.zdjcyun.R;
import com.zdjc.zdjcyun.base.BaseFragment;
import com.zdjc.zdjcyun.databinding.FragmentTabBinding;
import com.zdjc.zdjcyun.mvp.viewmodel.impl.TabModel;


public class TabFragment extends BaseFragment<FragmentTabBinding,TabModel> {

    public static final String ARG_PAGE="ARG_PAGE";
    public String mPage;

    public static TabFragment newInstance(String page){
        TabFragment tabFragment=new TabFragment();
        Bundle bundle=new Bundle();
        bundle.putString(ARG_PAGE,page);
        tabFragment.setArguments(bundle);
        return tabFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPage=getArguments().getString(ARG_PAGE);
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_tab;
    }

    @Override
    public void initView() {

    }

}
