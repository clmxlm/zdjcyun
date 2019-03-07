package com.zdjc.zdjcyun.mvp.viewmodel.impl;


import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.view.View;

import com.flyco.tablayout.listener.OnTabSelectListener;
import com.zdjc.zdjcyun.base.BaseModel;
import com.zdjc.zdjcyun.base.OnItemClickListener;
import com.zdjc.zdjcyun.databinding.FragmentProjectImagesBinding;
import com.zdjc.zdjcyun.mvp.entity.ImageListEntity;
import com.zdjc.zdjcyun.mvp.presenter.impl.ProjectImagesPresenterImpl;
import com.zdjc.zdjcyun.mvp.ui.activities.PictureActivity;
import com.zdjc.zdjcyun.mvp.ui.adapter.ProjectImagesRecycViewAdapter;
import com.zdjc.zdjcyun.mvp.viewmodel.IProjectImagesModel;
import com.zdjc.zdjcyun.util.PreferenceUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class ProjectImagesModel extends BaseModel<FragmentProjectImagesBinding,ProjectImagesPresenterImpl> implements IProjectImagesModel {


    private String id;
    private ProjectImagesRecycViewAdapter projectImagesRecycViewAdapter;
    private ArrayList<ImageListEntity.DataBean> data;
    @Override
    public void onCreate() {
        id = PreferenceUtils.getInt(getContext(), "sectorId") + "";
        inData();
    }

    private void inData() {

        projectImagesRecycViewAdapter = new ProjectImagesRecycViewAdapter(getContext());
        mBinder.rlContent.setLayoutManager(new GridLayoutManager(getContext(),2));
        mBinder.rlContent.setAdapter(projectImagesRecycViewAdapter);

        mBinder.segmentTabLayout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                PreferenceUtils.putInt(getContext(),"imageTypeName",data.get(position).getImageTypeName());
                projectImagesRecycViewAdapter.setList(data.get(position).getImages());
                projectImagesRecycViewAdapter.notifyDataSetChanged();
            }

            @Override
            public void onTabReselect(int position) {

            }
        });

        Map<String,String> map = new HashMap<>(0);
        map.put("sectorId", id);
        map.put("imageType",1+"");
        mControl.getQueryImageNames(this,map,1);

        projectImagesRecycViewAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(getContext(), PictureActivity.class);
                getContext().startActivity(intent);
            }
        });

    }

    @Override
    public void onBeforeRequest(int tag) {
    }

    @Override
    public void onSuccess(Object bean, int tag) {
        switch (tag) {
            case 1:
                data = (ArrayList<ImageListEntity.DataBean>)bean;
                String[] array=new String[data.size()];
                for(int i=0;i<data.size();i++){
                    array[i]=data.get(i).getImageType();
                }
                PreferenceUtils.putInt(getContext(),"imageTypeName",data.get(0).getImageTypeName());
                mBinder.segmentTabLayout.setTabData(array);
                projectImagesRecycViewAdapter.setList(data.get(0).getImages());
                break;
            default:
                break;
        }
    }

    @Override
    public void onError(String errorMsg, int code,int tag) {

    }



}
