package com.zdjc.zdjcyun.mvp.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.zdjc.zdjcyun.R;
import com.zdjc.zdjcyun.base.BaseFragment;
import com.zdjc.zdjcyun.databinding.FragmentDocumentsDetailBinding;
import com.zdjc.zdjcyun.databinding.FragmentOperationLogBinding;
import com.zdjc.zdjcyun.mvp.viewmodel.impl.DocumentsDetailModel;
import com.zdjc.zdjcyun.mvp.viewmodel.impl.OperationLogModel;

public class OperationLogFragment extends BaseFragment<FragmentOperationLogBinding,OperationLogModel> {

    public static final String ARG_PAGE="ARG_PAGE";
    public String mPage;

    public static OperationLogFragment newInstance(String page){
        OperationLogFragment operationLogFragment=new OperationLogFragment();
        Bundle bundle=new Bundle();
        bundle.putString(ARG_PAGE,page);
        operationLogFragment.setArguments(bundle);
        return operationLogFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPage=getArguments().getString(ARG_PAGE);
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_operation_log;
    }

    @Override
    public void initView() {

    }
}
