package com.zdjc.zdjcyun.mvp.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.zdjc.zdjcyun.R;
import com.zdjc.zdjcyun.base.BaseFragment;
import com.zdjc.zdjcyun.databinding.FragmentDataQueryBinding;
import com.zdjc.zdjcyun.mvp.viewmodel.impl.DataQueryModel;

public class DataQueryFragment extends BaseFragment<FragmentDataQueryBinding,DataQueryModel> {

    public static final String ARG_PAGE="ARG_PAGE";
    public String mPage;

    public static DataQueryFragment newInstance(String page){
        DataQueryFragment dataQueryFragment=new DataQueryFragment();
        Bundle bundle=new Bundle();
        bundle.putString(ARG_PAGE,page);
        dataQueryFragment.setArguments(bundle);
        return dataQueryFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPage=getArguments().getString(ARG_PAGE);
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_data_query;
    }

    @Override
    public void initView() {

    }
}
