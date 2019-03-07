package com.zdjc.zdjcyun.mvp.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.zdjc.zdjcyun.R;
import com.zdjc.zdjcyun.base.BaseFragment;
import com.zdjc.zdjcyun.databinding.FragmentMemberMessageBinding;
import com.zdjc.zdjcyun.mvp.viewmodel.impl.MemberMsgModel;

public class MemberMsgFragment extends BaseFragment<FragmentMemberMessageBinding,MemberMsgModel> {

    public static final String ARG_PAGE="ARG_PAGE";
    public String mPage;

    public static MemberMsgFragment newInstance(String page){
        MemberMsgFragment memberMsgFragment=new MemberMsgFragment();
        Bundle bundle=new Bundle();
        bundle.putString(ARG_PAGE,page);
        memberMsgFragment.setArguments(bundle);
        return memberMsgFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPage=getArguments().getString(ARG_PAGE);
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_member_message;
    }

    @Override
    public void initView() {

    }
}
