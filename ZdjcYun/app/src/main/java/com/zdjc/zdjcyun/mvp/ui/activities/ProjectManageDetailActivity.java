package com.zdjc.zdjcyun.mvp.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.zdjc.zdjcyun.R;
import com.zdjc.zdjcyun.mvp.ui.fragment.AlarmDetailFragment;
import com.zdjc.zdjcyun.mvp.ui.fragment.BIMFragment;
import com.zdjc.zdjcyun.mvp.ui.fragment.BasicInformationFragment;
import com.zdjc.zdjcyun.mvp.ui.fragment.DataMonitoringFragment;
import com.zdjc.zdjcyun.mvp.ui.fragment.DeviceFragment;
import com.zdjc.zdjcyun.mvp.ui.fragment.HazardsFragment;
import com.zdjc.zdjcyun.mvp.ui.fragment.MemberMsgFragment;
import com.zdjc.zdjcyun.mvp.ui.fragment.ProjectImagesFragment;
import com.zdjc.zdjcyun.util.PreferenceUtils;

import static com.zdjc.zdjcyun.base.BaseActivity.setWindowStatusBarColor;

/**
 * @author ClmXlm
 * @create 2019/1/9
 * @Describe
 */
public class ProjectManageDetailActivity extends AppCompatActivity {

    private TabLayout tabLayout = null;
    private ViewPager vp_pager;
    private String[] arrTltles = new String[] {"基本信息","人员信息","设备信息","BIM","数据监控","视频监控","危险源","项目告警","项目文库","操作日志","图纸"};
    private Fragment[] mFragmentArrays = new Fragment[11];

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setWindowStatusBarColor(this, R.color.theme_color);
        setContentView(R.layout.activity_project_manage_detail);
        tabLayout = (TabLayout) findViewById(R.id.tablayout);
        vp_pager = (ViewPager) findViewById(R.id.tab_viewpager);
        initView();

    }

    private void initView() {
        Intent intent = getIntent();
        TextView textView = findViewById(R.id.tv_title);
        textView.setText(intent.getStringExtra("sectorName"));
        ImageView imageView = findViewById(R.id.imgbtn_back);
        imageView.setVisibility(View.VISIBLE);
        imageView.setOnClickListener(v -> finish());


        mFragmentArrays[0] = BasicInformationFragment.newInstance("");
        mFragmentArrays[1] = MemberMsgFragment.newInstance("");
        mFragmentArrays[2] = DeviceFragment.newInstance("");
        mFragmentArrays[3] = BIMFragment.newInstance("");
        mFragmentArrays[4] = DataMonitoringFragment.newInstance("");
        mFragmentArrays[5] = BIMFragment.newInstance("");
        mFragmentArrays[6] = HazardsFragment.newInstance("");
        mFragmentArrays[7] = AlarmDetailFragment.newInstance("");
        mFragmentArrays[8] = BIMFragment.newInstance("");
        mFragmentArrays[9] = BIMFragment.newInstance("");
        mFragmentArrays[10] = ProjectImagesFragment.newInstance("");

        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        PagerAdapter pagerAdapter = new MyViewPagerAdapter(getSupportFragmentManager());
        //将ViewPager和TabLayout绑定
        vp_pager.setAdapter(pagerAdapter);
        tabLayout.setupWithViewPager(vp_pager);
        if ( "warning".equals(PreferenceUtils.getString(this,"alarmTag"))){
            vp_pager.setCurrentItem(7);
            PreferenceUtils.putString(this,"alarmTag","");
        }else {
            vp_pager.setCurrentItem(4);
        }
    }
    final class MyViewPagerAdapter extends FragmentPagerAdapter {
        public MyViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentArrays[position];
        }


        @Override
        public int getCount() {
            return mFragmentArrays.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return arrTltles[position];

        }
    }

}
