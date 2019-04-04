package com.zdjc.zdjcyun.mvp.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.zdjc.zdjcyun.R;
import com.zdjc.zdjcyun.base.BaseFragment;
import com.zdjc.zdjcyun.databinding.FragmentAlarmDetailBinding;
import com.zdjc.zdjcyun.databinding.FragmentDocumentsDetailBinding;
import com.zdjc.zdjcyun.mvp.viewmodel.impl.AlarmDetailModel;
import com.zdjc.zdjcyun.mvp.viewmodel.impl.DocumentsDetailModel;

public class DocumentsDetailFragment extends BaseFragment<FragmentDocumentsDetailBinding,DocumentsDetailModel> {

    public static final String ARG_PAGE="ARG_PAGE";
    public String mPage;

    public static DocumentsDetailFragment newInstance(String page){
        DocumentsDetailFragment documentsDetailFragment=new DocumentsDetailFragment();
        Bundle bundle=new Bundle();
        bundle.putString(ARG_PAGE,page);
        documentsDetailFragment.setArguments(bundle);
        return documentsDetailFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPage=getArguments().getString(ARG_PAGE);
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_documents_detail;
    }

    @Override
    public void initView() {

    }
}
