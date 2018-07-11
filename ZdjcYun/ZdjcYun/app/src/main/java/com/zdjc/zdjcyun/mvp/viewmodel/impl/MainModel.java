package com.zdjc.zdjcyun.mvp.viewmodel.impl;


import android.app.Dialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;

import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.flyco.tablayout.utils.UnreadMsgUtils;
import com.flyco.tablayout.widget.MsgView;
import com.github.jdsjlzx.interfaces.OnItemClickListener;
import com.zdjc.zdjcyun.R;
import com.zdjc.zdjcyun.app.BaseApplication;
import com.zdjc.zdjcyun.base.BaseModel;
import com.zdjc.zdjcyun.databinding.ActivityMainBinding;
import com.zdjc.zdjcyun.jpush.NewsActivity;
import com.zdjc.zdjcyun.mvp.entity.AllProjectListEntity;
import com.zdjc.zdjcyun.mvp.entity.TabEntity;
import com.zdjc.zdjcyun.mvp.presenter.impl.MainPresenterImpl;
import com.zdjc.zdjcyun.mvp.ui.activities.MainActivity;
import com.zdjc.zdjcyun.mvp.ui.adapter.ProjectListRecycViewAdapter;
import com.zdjc.zdjcyun.mvp.ui.fragment.HomeFragment;
import com.zdjc.zdjcyun.mvp.ui.fragment.PersonnerFragment;
import com.zdjc.zdjcyun.mvp.ui.fragment.ProjectDetailFragment;
import com.zdjc.zdjcyun.mvp.ui.fragment.WarningMessageFragment;
import com.zdjc.zdjcyun.mvp.viewmodel.IMainModel;
import com.zdjc.zdjcyun.util.ImageLoaderUtils;
import com.zdjc.zdjcyun.util.PreferenceUtils;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by ali on 2017/2/20.
 */

public class MainModel extends BaseModel<ActivityMainBinding, MainPresenterImpl> implements
        IMainModel, OnItemClickListener{



    private List<AllProjectListEntity.DataBean> dataBeanList;
    private String[] mTitles = {"首页", "实时", "告警", "我的"};
    private ProjectListRecycViewAdapter projectListRecycViewAdapter;

    private int[] mIconUnselectIds = {
            R.mipmap.tab_home_unselect, R.mipmap.tab_more_unselect,
            R.mipmap.tab_speech_unselect, R.mipmap.tab_contact_unselect};
    private int[] mIconSelectIds = {
            R.mipmap.tab_home_select, R.mipmap.tab_more_select,
            R.mipmap.tab_speech_select, R.mipmap.tab_contact_select};
    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();
    private int count = 0;
    private Dialog dialog;
    private boolean mapOnclick;
    private int mapOnclickPosition;


    @Override
    public void onCreate() {
        PreferenceUtils.putInt(getContext(),"projectPosition",0);
        initData();
    }

    @Override
    public void onBeforeRequest(int tag) {

    }


    @Override
    public void onSuccess(Object bean, int tag) {
        /**
         * 成功后到这里
         */
        switch (tag) {
            case 1:
                break;
        }
    }
    @Override
    public void onError(String errorMsg, int tag) {

    }

    public void getAlcount(int alcount){
        this.count = alcount;
        PreferenceUtils.putInt(getContext(),"count",count);
        mBinder.tl2.showMsg(2, count);
        mBinder.tl2.setMsgMargin(2, -5, 5);
    }
    @Override
    public void initData() {
        NewsActivity newsActivity = new NewsActivity(getContext());
        newsActivity.registerMessageReceiver();
        projectListRecycViewAdapter = new ProjectListRecycViewAdapter((MainActivity)UI);
        for (String title : mTitles) {
            if ("首页".equals(title)){
                mFragments.add(HomeFragment.newInstance(title));
            }else if ("实时".equals(title)){
                mFragments.add(ProjectDetailFragment.newInstance(title));
            }else if ("告警".equals(title)){
                mFragments.add(WarningMessageFragment.newInstance(title));
            }else if ("我的".equals(title)){
                mFragments.add(PersonnerFragment.newInstance(title));
            }
        }

        for (int i = 0; i < mTitles.length; i++) {
            mTabEntities.add(new TabEntity(mTitles[i], mIconSelectIds[i], mIconUnselectIds[i]));
        }

        /** with Fragment */
        mBinder.tl2.setTabData(mTabEntities, (MainActivity)UI, R.id.fl_change1, mFragments);
        mBinder.tl2.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                if (position==1){
                    ProjectDetailFragment projectDetailFragment = (ProjectDetailFragment) mFragments.get(1);
                    projectDetailFragment.mModel.requestData(false);
                }
            }

            @Override
            public void onTabReselect(int position) {

            }
        });

        mBinder.tl2.setCurrentTab(0);
        mBinder.tl2.showMsg(2, count);
        mBinder.tl2.setMsgMargin(2, -5, 5);

        //设置未读消息红点
//        mBinder.tl2.showDot(3);
        MsgView rtv_2_2 =  mBinder.tl2.getMsgView(3);
        if (rtv_2_2 != null) {
            UnreadMsgUtils.setSize(rtv_2_2, dp2px(7.5f));
        }

        projectListRecycViewAdapter.setOnItemClickListener((view, position) -> {
            dialog.dismiss();
            if (mapOnclick){
                mBinder.tl2.setCurrentTab(1);
                ProjectDetailFragment projectDetailFragment = (ProjectDetailFragment) mFragments.get(1);
                PreferenceUtils.putInt(getContext(),"projectPosition",mapOnclickPosition);
                projectDetailFragment.mModel.requestData(false);
            }else {
                PreferenceUtils.putInt(BaseApplication.getContext(),"projectId",dataBeanList.get(position).getProjectId());
                PreferenceUtils.putString(getContext(),"projectName",dataBeanList.get(position).getProjectName());
                PreferenceUtils.putInt(getContext(),"projectPosition",position);
                //重新选择项目之前存下来的所有字段值全部清空
                PreferenceUtils.putInt(getContext(),"topPosition",0);
                PreferenceUtils.putInt(getContext(),"leftPosition",0);
                PreferenceUtils.putString(getContext(),"tableName",null);
                PreferenceUtils.putString(getContext(),"startTime",null);
                PreferenceUtils.putString(getContext(),"endTime",null);
                PreferenceUtils.putString(getContext(),"sensorNumber",null);
                PreferenceUtils.putString(getContext(),"smuNumber",null);
                PreferenceUtils.putString(getContext(),"smuChannel",null);
                moveFragment(1);
            }

        });
    }

    public void moveFragment(int tag){
        dialog.dismiss();
        ImageLoaderUtils.imageDisPlayResByApp(R.mipmap.arrow_down,mBinder.include.tvChangeAccount);
        mBinder.tl2.setCurrentTab(tag);
        ProjectDetailFragment projectDetailFragment = (ProjectDetailFragment) mFragments.get(1);
        WarningMessageFragment warningMessageFragment = (WarningMessageFragment) mFragments.get(2);
        if (tag==1){
            projectDetailFragment.mModel.requestData(true);
        }else if (tag == 2){
            PreferenceUtils.putInt(BaseApplication.getContext(),"projectId",dataBeanList.get(0).getProjectId());
            warningMessageFragment.mModel.getData(true);
            warningMessageFragment.mModel.getView(mBinder.tl2);
            mBinder.tl2.showMsg(2, count);
            mBinder.tl2.setMsgMargin(2, -5, 5);
        }

    }

    @Override
    public void initListener() {

    }



    public void mapClick(int i) {
        mapOnclick = true;
        List<AllProjectListEntity.DataBean> mapDataList = new ArrayList<>();
        mapDataList.add(dataBeanList.get(i));
        myProjectListDialog(mapDataList);
        mapOnclickPosition = i;
        ImageLoaderUtils.imageDisPlayResByApp(R.mipmap.arrow_up,mBinder.include.tvChangeAccount);
    }


    @Override
    public void allProjectClick(List<AllProjectListEntity.DataBean> dataBeanList) {
        mapOnclick = false;
        this.dataBeanList = dataBeanList;
        myProjectListDialog(dataBeanList);
        ImageLoaderUtils.imageDisPlayResByApp(R.mipmap.arrow_up,mBinder.include.tvChangeAccount);

    }


    protected int dp2px(float dp) {
        final float scale = getContext().getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }
    @Override
    public void onItemClick(View view, int position) {

    }


    /**
     * 自定义项目列表弹窗
     */
    private void myProjectListDialog(List<AllProjectListEntity.DataBean> dataBeanList) {

        MainActivity mainActivity = (MainActivity)UI;
        dialog = new Dialog(mainActivity, R.style.dialog);
        // 通过LayoutInflater来加载一个xml的布局文件作为一个View对象
        View view = LayoutInflater.from(mainActivity).inflate(R.layout.pop_project_list_drop_down, null);
        // 设置我们自己定义的布局文件作为弹出框的Content
        dialog.setContentView(view);
        dialog.setCanceledOnTouchOutside(true);
        dialog.show();
        dialog.setOnDismissListener(dialogInterface -> ImageLoaderUtils.imageDisPlayResByApp(R.mipmap.arrow_down,mBinder.include.tvChangeAccount));
        // 适配屏幕 screenAdaptive
        WindowManager wm = mainActivity.getWindowManager();
        Display display = wm.getDefaultDisplay();
        WindowManager.LayoutParams lp = dialog.getWindow().getAttributes();
        lp.width = 99 * display.getWidth() / 100;
        lp.height = FrameLayout.LayoutParams.WRAP_CONTENT;
        dialog.getWindow().setAttributes(lp);

        RecyclerView popRecyclerView = view.findViewById(R.id.popRecyclerView);

        // 如果可以确定每个item的高度是固定的，设置这个选项可以提高性能
        popRecyclerView.setHasFixedSize(true);

        // 确定每个item的排列方式
        LinearLayoutManager layoutManager = new LinearLayoutManager(mainActivity) {
            @Override
            public RecyclerView.LayoutParams generateDefaultLayoutParams() {
                // 这里要复写一下，因为默认宽高都是wrap_content
                // 这个不复写，你点击的背景色就只充满你的内容
                return new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT);
            }
        };
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        popRecyclerView.setLayoutManager(layoutManager);

        int projectPosition = PreferenceUtils.getInt(getContext(), "projectPosition");
        projectListRecycViewAdapter.setDataList(dataBeanList);
        projectListRecycViewAdapter.getPosition(projectPosition);
        popRecyclerView.setAdapter(projectListRecycViewAdapter);
    }

}
