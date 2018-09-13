package com.zdjc.zdjcyun.mvp.viewmodel.impl;


import android.support.v4.view.GravityCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zdjc.zdjcyun.R;
import com.zdjc.zdjcyun.app.Constant;
import com.zdjc.zdjcyun.base.BaseModel;
import com.zdjc.zdjcyun.databinding.ActivityMainBinding;
import com.zdjc.zdjcyun.mvp.entity.BeginEntity;
import com.zdjc.zdjcyun.mvp.entity.PersonMessageEntity;
import com.zdjc.zdjcyun.mvp.presenter.impl.MainPresenterImpl;
import com.zdjc.zdjcyun.mvp.ui.activities.LoginActivity;
import com.zdjc.zdjcyun.mvp.ui.activities.MainActivity;
import com.zdjc.zdjcyun.mvp.ui.activities.ProjectListActivity;
import com.zdjc.zdjcyun.mvp.ui.adapter.ProjectTypeRecycViewAdapter;
import com.zdjc.zdjcyun.mvp.viewmodel.IMainModel;
import com.zdjc.zdjcyun.util.ImageLoaderUtils;
import com.zdjc.zdjcyun.util.PreferenceUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import cn.jpush.android.api.JPushInterface;

/**
 * Created by ali on 2017/2/20.
 */

public class MainModel extends BaseModel<ActivityMainBinding,MainPresenterImpl> implements IMainModel,ViewPager.OnPageChangeListener{


    private ViewPager viewPager;
    private ArrayList<ImageView> imageViewList;
    private LinearLayout ll_point_container;
    private TextView tv_desc;
    private int previousSelectedPosition = 0;
    private boolean isRunning = false;
    private BeginEntity.DataBean homeViewData;
    private ProjectTypeRecycViewAdapter projectTypeRecycViewAdapter;
//    private PDFRecycViewAdapter pdfRecycViewAdapter;
    private DrawerLayout drawerLayout;

    @Override
    public void onCreate() {

        // 初始化布局 View视图
        initViews();

        // 开启轮询
        new Thread() {
            public void run() {
                isRunning = true;
                while (isRunning) {
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    // 往下跳一位
                    ((MainActivity) UI).runOnUiThread(() -> {
                        viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
                    });
                }
            }
        }.start();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        isRunning = false;
    }

    private void initViews() {
        drawerLayout = mBinder.drawer;
        mBinder.include.imgbtnLeft.setVisibility(View.VISIBLE);
        mBinder.include.imgbtnLeft.setOnClickListener(v -> drawerLayout.openDrawer(GravityCompat.START));

        projectTypeRecycViewAdapter = new ProjectTypeRecycViewAdapter((MainActivity) UI);
//        pdfRecycViewAdapter = new PDFRecycViewAdapter((MainActivity) UI);

        String token = PreferenceUtils.getString(getContext(),"token");
        Map<String,String> map = new HashMap<>();
        map.put("token",token);
        mControl.getHomeViewMsg(this,map,1);

        viewPager = mBinder.viewpager;
        viewPager.setOnPageChangeListener(this);// 设置页面更新监听
//		viewPager.setOffscreenPageLimit(1);// 左右各保留几个对象
        ll_point_container = mBinder.llPointContainer;
        tv_desc = mBinder.tvDesc;
        if ("".equals(token)){
            mBinder.includeLeft.tvToken.setText("请先登录");
            ImageLoaderUtils.imageDisPlayRes(R.mipmap.username,mBinder.includeLeft.ivLogout);
        }else {
            mBinder.includeLeft.tvToken.setText("退出登录");
            ImageLoaderUtils.imageDisPlayRes(R.mipmap.logout,mBinder.includeLeft.ivLogout);
        }
        mBinder.includeLeft.rlLogout.setOnClickListener(v -> {
            if (!"".equals(token)){
                Map<String,String> map1 = new HashMap<>();
                map1.put("zdjc","zdjc");
                mControl.loginOut(MainModel.this, map1,3);
            }else {
                ((MainActivity)UI).intent2Activity(LoginActivity.class);
                drawerLayout.closeDrawer(GravityCompat.START);
                ((MainActivity)UI).finish();
            }
        });

    }


    /**
     * 初始化要显示的数据
     */
    private void initData() {

        RecyclerView topRecyclerView = mBinder.topRecyclerView;
//        RecyclerView pdfRecyclerView = mBinder.pdfRecyclerView;

//        pdfRecyclerView.setLayoutManager(new GridLayoutManager(getContext(),1));
//        pdfRecycViewAdapter.setDataList(homeViewData.getFiles());
//        pdfRecyclerView.setAdapter(pdfRecycViewAdapter);

        topRecyclerView.setLayoutManager(new GridLayoutManager(getContext(),4));
        projectTypeRecycViewAdapter.setDataList(homeViewData.getProjecType());
        topRecyclerView.setAdapter(projectTypeRecycViewAdapter);

        // 初始化要展示的5个ImageView
        imageViewList = new ArrayList<>();

        ImageView imageView;
        View pointView;
        LinearLayout.LayoutParams layoutParams;
        for (int i = 0; i < homeViewData.getImage().size(); i++) {
            // 初始化要显示的图片对象
            imageView = new ImageView(getContext());
            ImageLoaderUtils.imageDisPlay(Constant.IMAGE_URL+homeViewData.getImage().get(i).getUrl(),imageView);
            imageViewList.add(imageView);

            // 加小白点, 指示器
            pointView = new View(getContext());
            pointView.setBackgroundResource(R.drawable.selector_bg_point);
            layoutParams = new LinearLayout.LayoutParams(5, 5);
            if (i != 0)
                layoutParams.leftMargin = 10;
            // 设置默认所有都不可用
            pointView.setEnabled(false);
            ll_point_container.addView(pointView, layoutParams);
        }
        PreferenceUtils.putString(getContext(),"ceshi",Constant.IMAGE_URL+homeViewData.getImage().get(0).getUrl());

        projectTypeRecycViewAdapter.setOnItemClickListener((view, position) -> {
            if ("".equals(PreferenceUtils.getString(getContext(),"token"))){
                ((MainActivity) UI).intent2Activity(LoginActivity.class);
                ((MainActivity) UI).finish();
            }else {
                ((MainActivity) UI).intent2Activity(ProjectListActivity.class);
                PreferenceUtils.putInt(getContext(),"projectType",homeViewData.getProjecType().get(position).getPtSc());
            }
        });

//        pdfRecycViewAdapter.setOnItemClickListener((view, position) -> {
//            if ("".equals(PreferenceUtils.getString(getContext(),"token"))){
//                ((MainActivity) UI).intent2Activity(LoginActivity.class);
//                ((MainActivity) UI).finish();
//            }else {
//                Intent intent = new Intent(getContext(),PDFActivity.class);
//                intent.putExtra("reportUrl",homeViewData.getFiles().get(position).getFileUrl());
//                intent.putExtra("report","free");
//                getContext().startActivity(intent);
//
//            }
//        });
    }

    private void initAdapter() {
        ll_point_container.getChildAt(0).setEnabled(true);
        tv_desc.setText(homeViewData.getImage().get(0).getDescription());
        previousSelectedPosition = 0;

        // 设置适配器
        viewPager.setAdapter(new MyAdapter());

        // 默认设置到中间的某个位置
        int pos = Integer.MAX_VALUE / 2 - (Integer.MAX_VALUE / 2 % imageViewList.size());
        // 2147483647 / 2 = 1073741823 - (1073741823 % 5)
        viewPager.setCurrentItem(5000000); // 设置到某个位置
    }

    class MyAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return Integer.MAX_VALUE;
        }

        // 3. 指定复用的判断逻辑, 固定写法
        @Override
        public boolean isViewFromObject(View view, Object object) {
//			System.out.println("isViewFromObject: "+(view == object));
            // 当划到新的条目, 又返回来, view是否可以被复用.
            // 返回判断规则
            return view == object;
        }

        // 1. 返回要显示的条目内容, 创建条目
        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            System.out.println("instantiateItem初始化: " + position);
            // container: 容器: ViewPager
            // position: 当前要显示条目的位置 0 -> 4

//			newPosition = position % 5
            int newPosition = position % imageViewList.size();

            ImageView imageView = imageViewList.get(newPosition);
            // a. 把View对象添加到container中
            container.addView(imageView);
            // b. 把View对象返回给框架, 适配器
            return imageView; // 必须重写, 否则报异常
        }

        // 2. 销毁条目
        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            // object 要销毁的对象
            System.out.println("destroyItem销毁: " + position);
            container.removeView((View) object);
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset,
                               int positionOffsetPixels) {
        // 滚动时调用
    }

    @Override
    public void onPageSelected(int position) {
        // 新的条目被选中时调用
        System.out.println("onPageSelected: " + position);
        int newPosition = position % imageViewList.size();

        //设置文本
        tv_desc.setText(homeViewData.getImage().get(newPosition).getDescription());

//		for (int i = 0; i < ll_point_container.getChildCount(); i++) {
//			View childAt = ll_point_container.getChildAt(position);
//			childAt.setEnabled(position == i);
//		}
        // 把之前的禁用, 把最新的启用, 更新指示器
        ll_point_container.getChildAt(previousSelectedPosition).setEnabled(false);
        ll_point_container.getChildAt(newPosition).setEnabled(true);

        // 记录之前的位置
        previousSelectedPosition = newPosition;

    }

    @Override
    public void onPageScrollStateChanged(int state) {
        // 滚动状态变化时调用
    }

    @Override
    public void onBeforeRequest(int tag) {
        UI.showWaitDialog();
    }

    @Override
    public void onSuccess(Object bean, int tag) {
        switch (tag) {
            case 1:
                homeViewData = (BeginEntity.DataBean) bean;
                // Model数据
                initData();
                // Controller 控制器
                initAdapter();

                if (PreferenceUtils.getString(getContext(),"uesrName")!=null){
                    Map<String,String> map = new HashMap<>();
                    map.put("userName", PreferenceUtils.getString(getContext(),"uesrName"));
                    mControl.getPersonMsg(this,map,2);
                }
                break;
            case 2:
                PersonMessageEntity.DataBean dataBean = (PersonMessageEntity.DataBean)bean;
                String userName = getContext().getResources().getString(R.string.user_name)+" "+
                        dataBean.getUserName();
                String personertName = getContext().getResources().getString(R.string.personert_name)+" "+
                        dataBean.getRealName();
                String personertPhone = getContext().getResources().getString(R.string.personert_phone)+" "+
                        dataBean.getPhone();
                String personertEmail = getContext().getResources().getString(R.string.personert_email)+" "+
                        dataBean.getEmail();
                String personertCompany = getContext().getResources().getString(R.string.personert_company)+" "+
                        dataBean.getCompany();

                mBinder.includeLeft.tvUser.setText(userName);
                mBinder.includeLeft.tvUsername.setText(personertName);
                mBinder.includeLeft.tvPhone.setText(personertPhone);
                mBinder.includeLeft.tvEmail.setText(personertEmail);
                mBinder.includeLeft.tvCompany.setText(personertCompany);
                PreferenceUtils.putString(getContext(),"userName",dataBean.getUserName());
                break;
            case 3:
                PreferenceUtils.putInt(getContext(),"projectPosition",0);
                PreferenceUtils.putInt(getContext(),"onePosition",0);
                PreferenceUtils.putInt(getContext(),"twoPosition",0);
                PreferenceUtils.putString(getContext(),"startTime",null);
                PreferenceUtils.putString(getContext(),"endTime",null);
                PreferenceUtils.putString(getContext(),"token","");
                PreferenceUtils.putString(getContext(),"uesrName",null);
                JPushInterface.stopPush(getContext());
                ((MainActivity)UI).intent2Activity(LoginActivity.class);
                ((MainActivity)UI).finish();
                break;
        }
    }

    @Override
    public void onError(String errorMsg, int tag) {

    }
}
