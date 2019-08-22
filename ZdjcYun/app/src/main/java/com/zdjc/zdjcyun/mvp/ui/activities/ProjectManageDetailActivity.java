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
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.google.gson.Gson;
import com.zdjc.zdjcyun.R;
import com.zdjc.zdjcyun.mvp.entity.PushEntity;
import com.zdjc.zdjcyun.mvp.ui.fragment.AlarmDetailFragment;
import com.zdjc.zdjcyun.mvp.ui.fragment.BIMFragment;
import com.zdjc.zdjcyun.mvp.ui.fragment.BasicInformationFragment;
import com.zdjc.zdjcyun.mvp.ui.fragment.DataAnalysisFragment;
import com.zdjc.zdjcyun.mvp.ui.fragment.DataMonitoringFragment;
import com.zdjc.zdjcyun.mvp.ui.fragment.DataQueryFragment;
import com.zdjc.zdjcyun.mvp.ui.fragment.DeviceFragment;
import com.zdjc.zdjcyun.mvp.ui.fragment.DocumentsDetailFragment;
import com.zdjc.zdjcyun.mvp.ui.fragment.HazardsFragment;
import com.zdjc.zdjcyun.mvp.ui.fragment.MemberMsgFragment;
import com.zdjc.zdjcyun.mvp.ui.fragment.OperationLogFragment;
import com.zdjc.zdjcyun.mvp.ui.fragment.ProjectImagesFragment;
import com.zdjc.zdjcyun.mvp.ui.fragment.TabFragment;
import com.zdjc.zdjcyun.mvp.ui.fragment.VideoFragment;
import com.zdjc.zdjcyun.mvp.viewmodel.impl.DataComparisonModel;
import com.zdjc.zdjcyun.util.PreferenceUtils;
import com.zdjc.zdjcyun.util.ScreenUtil;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.jpush.android.api.JPushInterface;

import static com.zdjc.zdjcyun.base.BaseActivity.setWindowStatusBarColor;

/**
 * @author ClmXlm
 * @create 2019/1/9
 * @Describe
 */
public class ProjectManageDetailActivity extends AppCompatActivity {

    private TabLayout tabLayout = null;
    private ViewPager vp_pager;
    private String[] arrTltles = new String[] {"基本信息","人员信息","设备信息","BIM","数据查询","数据监控","数据分析","视频监控","危险源","项目告警","项目文库","操作日志","图纸","中大"};
    private String[] arrTltles1 = new String[] {"基本信息","人员信息","设备信息","BIM","数据查询","数据监控","数据分析","视频监控","危险源","项目告警","项目文库","操作日志","图纸"};
    private Fragment[] mFragmentArrays = new Fragment[14];
    private LayoutInflater mInflater;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setWindowStatusBarColor(this, R.color.theme_color);
        setContentView(R.layout.activity_project_manage_detail);
        tabLayout = (TabLayout) findViewById(R.id.tablayout);
        vp_pager = (ViewPager) findViewById(R.id.tab_viewpager);
        initView();

        mInflater = LayoutInflater.from(this);
    }

    private void initView() {
        Intent intent = getIntent();
        TextView textView = findViewById(R.id.tv_title);
        textView.setText(intent.getStringExtra("sectorName"));
        ImageView imageView = findViewById(R.id.imgbtn_back);
        LinearLayout llAll = findViewById(R.id.ll_all);
        imageView.setVisibility(View.VISIBLE);
        imageView.setOnClickListener(v -> finish());


        mFragmentArrays[0] = BasicInformationFragment.newInstance("");
        mFragmentArrays[1] = MemberMsgFragment.newInstance("");
        mFragmentArrays[2] = DeviceFragment.newInstance("");
        mFragmentArrays[3] = BIMFragment.newInstance("");
        mFragmentArrays[4] = DataQueryFragment.newInstance("");
        mFragmentArrays[5] = DataMonitoringFragment.newInstance("");
        mFragmentArrays[6] = DataAnalysisFragment.newInstance("");
        mFragmentArrays[7] = TabFragment.newInstance("tag");
        mFragmentArrays[8] = HazardsFragment.newInstance("");
        mFragmentArrays[9] = AlarmDetailFragment.newInstance("");
        mFragmentArrays[10] = DocumentsDetailFragment.newInstance("");
        mFragmentArrays[11] = OperationLogFragment.newInstance("");
        mFragmentArrays[12] = ProjectImagesFragment.newInstance("");
        mFragmentArrays[13] = BIMFragment.newInstance("");
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        PagerAdapter pagerAdapter = new MyViewPagerAdapter(getSupportFragmentManager());
        //将ViewPager和TabLayout绑定
        vp_pager.setAdapter(pagerAdapter);
        tabLayout.setupWithViewPager(vp_pager);
        if ( "warning".equals(PreferenceUtils.getString(this,"alarmTag"))){
            vp_pager.setCurrentItem(9);
            PreferenceUtils.putString(this,"alarmTag","");
        }else {
            vp_pager.setCurrentItem(4);
        }

        llAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popWindow(v);
            }
        });

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

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Bundle bundle = intent.getExtras();
        if (bundle!=null){
            String json = bundle.getString(JPushInterface.EXTRA_ALERT);
            Gson gson = new Gson();
            PushEntity listType = gson.fromJson(json, PushEntity.class);
            String projectId = listType.getSensorNumber();
            String dd = listType.getSensorNumber();
//            PreferenceUtils.putBoolean(this,"push",true);
//            PreferenceUtils.putInt(this,"projectId",Integer.valueOf(projectId));
//            intent2Activity(ProjectDetailActivity.class);
            vp_pager.setCurrentItem(5);
        }
    }


    private void popWindow(View v) {
        View view = LayoutInflater.from(this).inflate(R.layout.popwindow_query_layout, null);
        //设置屏幕的高度和宽度
        final PopupWindow pop = new PopupWindow(view, ScreenUtil.getScreenWidth(this), ScreenUtil.getScreenHeight(this) * 3 / 10);
        //如果不设置背景颜色的话，无法是pop dimiss掉。
        pop.setBackgroundDrawable(this.getResources().getDrawable(R.drawable.popupwindow_background));
        pop.setOutsideTouchable(true);
//        pop.setAnimationStyle(R.style.MyPopupWindow_anim_style);

        pop.setWidth(WindowManager.LayoutParams.MATCH_PARENT);
        int height = ScreenUtil.getScreenHeight(this)/2;
        pop.setHeight(WindowManager.LayoutParams.WRAP_CONTENT);


        TagFlowLayout tagFlowLayout = view.findViewById(R.id.flowlayout1);
        ImageView ivCancel = view.findViewById(R.id.iv_cancel);

        ivCancel.setOnClickListener(v12 -> pop.dismiss());

        List list = Arrays.asList(arrTltles1);
        tagFlowLayout.setAdapter(new TagAdapter<String>(list) {
            @Override
            public View getView(FlowLayout parent, int position, String s) {
                TextView tv = (TextView) mInflater.inflate(R.layout.tag_text,
                        tagFlowLayout, false);
                tv.setText(s);
                return tv;
            }
        });

        tagFlowLayout.setOnTagClickListener((view1, position, parent) -> {
            vp_pager.setCurrentItem(position);
            pop.dismiss();
            return false;
        });
        pop.showAsDropDown(v);
        //设置 背景的颜色为 0.5f 的透明度
        backgroundAlpha(0.5f);
        pop.setOnDismissListener(() -> backgroundAlpha(1.0f));

    }

    private void backgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = this.getWindow().getAttributes();
        lp.alpha = bgAlpha;
        this.getWindow().setAttributes(lp);
    }
}
