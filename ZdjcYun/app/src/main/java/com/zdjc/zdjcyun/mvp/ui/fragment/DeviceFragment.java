package com.zdjc.zdjcyun.mvp.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.zdjc.zdjcyun.R;
import com.zdjc.zdjcyun.base.BaseFragment;
import com.zdjc.zdjcyun.databinding.FragmentDeviceBinding;
import com.zdjc.zdjcyun.mvp.viewmodel.impl.DeviceModel;

public class DeviceFragment extends BaseFragment<FragmentDeviceBinding,DeviceModel> {

    public static final String ARG_PAGE="ARG_PAGE";
    public String mPage;

    public static DeviceFragment newInstance(String page){
        DeviceFragment deviceFragment=new DeviceFragment();
        Bundle bundle=new Bundle();
        bundle.putString(ARG_PAGE,page);
        deviceFragment.setArguments(bundle);
        return deviceFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPage=getArguments().getString(ARG_PAGE);
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_device;
    }

    @Override
    public void initView() {

    }
}
