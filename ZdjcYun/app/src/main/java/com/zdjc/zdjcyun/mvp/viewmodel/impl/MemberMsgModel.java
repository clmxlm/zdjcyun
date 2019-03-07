package com.zdjc.zdjcyun.mvp.viewmodel.impl;


import android.app.Activity;
import android.support.v7.widget.GridLayoutManager;

import com.flyco.tablayout.listener.OnTabSelectListener;
import com.zdjc.zdjcyun.base.BaseModel;
import com.zdjc.zdjcyun.databinding.FragmentMemberMessageBinding;
import com.zdjc.zdjcyun.mvp.entity.MemberMsgEntity;
import com.zdjc.zdjcyun.mvp.presenter.impl.MemberMsgPresenterImpl;
import com.zdjc.zdjcyun.mvp.ui.adapter.MemberMsgRecycViewAdapter;
import com.zdjc.zdjcyun.mvp.viewmodel.IMemberMsgModel;
import com.zdjc.zdjcyun.util.PreferenceUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class MemberMsgModel extends BaseModel<FragmentMemberMessageBinding, MemberMsgPresenterImpl> implements IMemberMsgModel {


    private String id;
    private MemberMsgRecycViewAdapter memberMsgRecycViewAdapter;
    private List<MemberMsgEntity.DataBean> data;

    @Override
    public void onCreate() {
        id = PreferenceUtils.getInt(getContext(),"sectorId")+"";
        intView();
    }

    private void intView() {
        memberMsgRecycViewAdapter = new MemberMsgRecycViewAdapter((Activity)getContext());
        mBinder.rlContent.setLayoutManager(new GridLayoutManager(getContext(),1));
        mBinder.rlContent.setAdapter(memberMsgRecycViewAdapter);
        request();

        mBinder.segmentTabLayout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                memberMsgRecycViewAdapter.setList(data.get(position).getMembers());
                memberMsgRecycViewAdapter.notifyDataSetChanged();
            }

            @Override
            public void onTabReselect(int position) {

            }
        });

    }

    @Override
    public void onBeforeRequest(int tag) {
        UI.showWaitDialog();
    }

    @Override
    public void onSuccess(Object bean, int tag) {
        switch (tag) {
            case 1:
                data = (List<MemberMsgEntity.DataBean>)bean;
                String[] array=new String[data.size()];
                for(int i=0;i<data.size();i++){
                    array[i]=(String)data.get(i).getTypeName();
                }
                mBinder.segmentTabLayout.setTabData(array);
                memberMsgRecycViewAdapter.setList(data.get(0).getMembers());
                break;
                default:
                    break;
        }
    }

    @Override
    public void onError(String errorMsg,int code, int tag) {

    }

    private  void request(){
        Map<String, String> map = new HashMap<>(0);
        map.put("sectorId", id);
        mControl.getQuerySectorMember(MemberMsgModel.this, map, 1);
    }

}
