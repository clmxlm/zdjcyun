package com.zdjc.zdjcyun.mvp.viewmodel.impl;


import android.util.Log;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.zdjc.zdjcyun.base.BaseModel;
import com.zdjc.zdjcyun.databinding.ActivityTextViewBinding;
import com.zdjc.zdjcyun.mvp.entity.ProjectContactEntity;
import com.zdjc.zdjcyun.mvp.presenter.impl.TextViewPresenterImpl;
import com.zdjc.zdjcyun.mvp.ui.activities.TextViewActivity;
import com.zdjc.zdjcyun.mvp.viewmodel.ITextViewModel;
import com.zdjc.zdjcyun.util.ArithUtils;
import com.zdjc.zdjcyun.util.DateUtil;
import com.zdjc.zdjcyun.util.PreferenceUtils;

import java.math.BigDecimal;
import java.util.HashMap;


public class TextViewModel extends BaseModel<ActivityTextViewBinding,TextViewPresenterImpl> implements ITextViewModel {

    private int projectId;
    private ProgressBar progesss;
    private TextView progesssValue;
    private LinearLayout full;


    @Override
    public void onCreate() {
        HashMap<String, String> params = new HashMap<>(0);
        projectId = PreferenceUtils.getInt(getContext(),"projectId");
        String userId = PreferenceUtils.getInt(getContext(),"userId")+"";
        params.put("projectId",projectId+"");
        params.put("userId",userId);
        mControl.getTextMessage(this,params,1);

        progesss = mBinder.progesss1;
        progesssValue = mBinder.progesssValue1;
        full = mBinder.full;

        intView();

    }


    private void intView() {

    }

    @Override
    public void onBeforeRequest(int tag) {
        UI.showWaitDialog();
    }

    @Override
    public void onSuccess(Object bean, int tag) {
        /**
         * 成功后到这里
         */
        switch (tag) {
            case 1:
                ProjectContactEntity.DataBean dataBean = (ProjectContactEntity.DataBean)bean;

                String projectName = "项目描述:"+dataBean.getProjectDescription();
                String projectAddress = "项目地址:"+dataBean.getProjectAddress();
                String projectType = "项目类型:"+dataBean.getProjectTypeName();
                String projectStTime = "项目结束时间:"+dataBean.getProjectEndTime();
                String projectEdTime = "项目开始时间:"+dataBean.getProjectBeginTime();
                String projectStatus = "状态:"+dataBean.getStatus();
                mBinder.tv1.setText(projectName);
                mBinder.tvAddress.setText(projectAddress);
                mBinder.tvType.setText(projectType);
                mBinder.tv3.setText(projectStTime);
                mBinder.tv2.setText(projectEdTime);
                long beginTime = DateUtil.getTime(dataBean.getProjectBeginTime());
                long endTime = DateUtil.getTime(dataBean.getProjectEndTime());
                long currenTime = System.currentTimeMillis();
                long competleTime= currenTime-beginTime;
                long allTime= endTime-beginTime;
                double time =  ArithUtils.div(allTime,competleTime);
                BigDecimal bg = new BigDecimal(time);
                double f1 = bg.setScale(2, BigDecimal.ROUND_DOWN).doubleValue();
                int time1 = (int) (f1*100);
                progesss.setProgress(time1);
                progesssValue.setText(new StringBuffer().append(progesss.getProgress()).append("%"));
                setPosWay1();
                mBinder.tv4.setText(projectStatus);
                String stringName1;
                String stringName2 ;
                String stringName3 ;
                String stringName4 ;
                if (projectId==261||projectId==262||projectId==263||projectId==264){
                    stringName1 = "唐波";
                    stringName2 = "唐波";
                    stringName3 = "杨超";
                    stringName4 = "杜工";
                }else if (projectId==176){
                    stringName1 = "唐波";
                    stringName2 = "唐波";
                    stringName3 = "杨超";
                    stringName4 = "暂无";
                }else if (projectId==249||projectId==260||projectId==273){
                    stringName1 = "张金龙";
                    stringName2 = "张金龙";
                    stringName3 = "张金龙";
                    stringName4 = "暂无";
                }else if (projectId==309){
                    stringName1 = "汤工";
                    stringName2 = "汤工";
                    stringName3 = "彭学先";
                    stringName4 = "暂无";
                }else {
                    stringName1 = "暂无";
                    stringName2 = "暂无";
                    stringName3 = "暂无";
                    stringName4 = "暂无";
                }
                String stringNameOne = "项目经理:"+ stringName1;
                String stringNameTwo = "技术员:"+ stringName2;
                String stringNameThree = "实施人员:"+ stringName3;
                String stringNameFour = "业主:"+ stringName4;
                mBinder.tv5.setText(stringNameOne);
                mBinder.tv6.setText(stringNameTwo);
                mBinder.tv7.setText(stringNameThree);
                mBinder.tv8.setText(stringNameFour);
                break;
                default:
                    break;
        }

    }

    @Override
    public void onError(String errorMsg, int code,int tag) {

    }

    private void setPosWay1() {
        progesssValue.post(() -> setPos());
    }

    /**
     * 设置进度显示在对应的位置
     */
    private void setPos() {
        int w = ((TextViewActivity)UI).getWindowManager().getDefaultDisplay().getWidth();
        Log.e("w=====", "" + w);
        ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) progesssValue.getLayoutParams();
        int pro = progesss.getProgress();
        int tW = progesssValue.getWidth();
        if (w * pro / 100 + tW * 0.3 > w) {
            params.leftMargin = (int) (w - tW * 1.1);
        } else if (w * pro / 100 < tW * 0.7) {
            params.leftMargin = 0;
        } else {
            params.leftMargin = (int) (w * pro / 100 - tW * 0.7);
        }
        progesssValue.setLayoutParams(params);

    }

}
