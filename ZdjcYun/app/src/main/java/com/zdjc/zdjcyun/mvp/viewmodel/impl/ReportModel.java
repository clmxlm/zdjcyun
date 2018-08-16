package com.zdjc.zdjcyun.mvp.viewmodel.impl;


import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;

import com.zdjc.zdjcyun.R;
import com.zdjc.zdjcyun.base.BaseModel;
import com.zdjc.zdjcyun.databinding.ActivityReportBinding;
import com.zdjc.zdjcyun.event.PagerSlidingTabStrip;
import com.zdjc.zdjcyun.mvp.presenter.impl.ReportPresenterImpl;
import com.zdjc.zdjcyun.mvp.ui.activities.ReportActivity;
import com.zdjc.zdjcyun.mvp.ui.fragment.PageFragment;
import com.zdjc.zdjcyun.mvp.viewmodel.IReportModel;

import java.util.ArrayList;

/**
 * Created by ali on 2017/2/20.
 */

public class ReportModel extends BaseModel<ActivityReportBinding,ReportPresenterImpl> implements IReportModel {

    private String tabTags[]=new String[]{"Day","Week","Month","Quarter","Year"};
    private ArrayList<PageFragment> reportListingFragments = new ArrayList<>();//根据分类数量生成相对应的fragment
    private PagerSlidingTabStrip mPagerSlidingTabStrip;
    private DisplayMetrics dm; // 获取当前屏幕密度

    @Override
    public void onCreate() {
        initViews();
    }

    private void initViews() {
        mBinder.include.tvTitle.setText("报告类型");
        mBinder.include.imgbtnBack.setVisibility(View.VISIBLE);
        mBinder.include.imgbtnBack.setOnClickListener(v -> ((ReportActivity)UI).finish());
        ViewPager vp = mBinder.vp;
        vp.setOffscreenPageLimit(1);
        mPagerSlidingTabStrip = mBinder.tabs;
        dm = getContext().getResources().getDisplayMetrics();
        for (String tabTag : tabTags) {
            reportListingFragments.add(PageFragment.newInstance(tabTag));
        }

        MyPagerAdapter myPagerAdapter = new MyPagerAdapter(((ReportActivity)UI).getSupportFragmentManager(),reportListingFragments);
        vp.setAdapter(myPagerAdapter);
        mPagerSlidingTabStrip.setViewPager(vp);
        setTabsValue();
    }

    /**
     * 图片内容适配器
     */
    public class MyPagerAdapter extends FragmentStatePagerAdapter{
        private final String[] tabTitles = {"日报","周报","月报","季报","年报"};
        private ArrayList<PageFragment> reportListingFragments;
        private MyPagerAdapter(FragmentManager fm,ArrayList<PageFragment> reportListingFragments) {
            super(fm);
            this.reportListingFragments = reportListingFragments;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return tabTitles[position];
        }

        @Override
        public int getCount() {
            return reportListingFragments.size();
        }

        @Override
        public Fragment getItem(int position) {
            return reportListingFragments.get(position);
        }

    }

    @Override
    public void onBeforeRequest(int tag) {

    }

    @Override
    public void onSuccess(Object bean, int tag) {

    }

    @Override
    public void onError(String errorMsg, int tag) {

    }


    /**
     * 对PagerSlidingTabStrip的各项属性进行赋值。
     */
    private void setTabsValue() {
        // 设置Tab是自动填充满屏幕的
        mPagerSlidingTabStrip.setShouldExpand(true);
        // 设置Tab的分割线是透明的
        mPagerSlidingTabStrip.setDividerColor(Color.TRANSPARENT);
        // 设置Tab底部线的高度
        mPagerSlidingTabStrip.setUnderlineHeight((int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, 1, dm));
        // 设置Tab Indicator的高度
        mPagerSlidingTabStrip.setIndicatorHeight((int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, 2, dm));
        // 设置Tab标题文字的大小
        mPagerSlidingTabStrip.setTextSize((int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_SP, 15, dm));
        // 设置Tab标题默认的颜色
        mPagerSlidingTabStrip.setTextColor(getContext().getResources().getColor(
                R.color.get_record_text_unselected_color));
        // 设置选中Tab标题的颜色
        mPagerSlidingTabStrip.setSelectedTextColor(getContext().getResources().getColor(
                R.color.get_record_text_selected_color));
        // 设置Tab底部线的颜色
        mPagerSlidingTabStrip.setUnderlineColor(getContext().getResources().getColor(
                R.color.get_record_line_unselected_color));
        // 设置Tab Indicator的颜色
        mPagerSlidingTabStrip.setIndicatorColor(getContext().getResources().getColor(
                R.color.get_record_line_selected_color));
        // 取消点击Tab时的背景色
        // get_record_tab.setTabBackground(getResources().getColor(R.color.tab_pressed_hover));
    }

}
