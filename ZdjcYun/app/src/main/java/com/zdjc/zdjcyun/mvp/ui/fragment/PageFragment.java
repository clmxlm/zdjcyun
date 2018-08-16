package com.zdjc.zdjcyun.mvp.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.zdjc.zdjcyun.R;
import com.zdjc.zdjcyun.base.BaseFragment;
import com.zdjc.zdjcyun.databinding.FragmentPageBinding;
import com.zdjc.zdjcyun.mvp.viewmodel.impl.PageModel;

public class PageFragment extends BaseFragment<FragmentPageBinding,PageModel> {

    public static final String ARG_PAGE="ARG_PAGE";
    public String mPage;

    public static PageFragment newInstance(String page){
        PageFragment pageFragment=new PageFragment();
        Bundle bundle=new Bundle();
        bundle.putString(ARG_PAGE,page);
        pageFragment.setArguments(bundle);
        return pageFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPage=getArguments().getString(ARG_PAGE);
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_page;
    }

    @Override
    public void initView() {

    }
}
