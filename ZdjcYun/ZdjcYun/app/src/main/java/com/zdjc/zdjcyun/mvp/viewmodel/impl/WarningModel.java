package com.zdjc.zdjcyun.mvp.viewmodel.impl;


import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.WindowManager;

import com.bin.david.form.core.SmartTable;
import com.bin.david.form.core.TableConfig;
import com.bin.david.form.data.CellInfo;
import com.bin.david.form.data.format.bg.BaseBackgroundFormat;
import com.bin.david.form.data.format.bg.BaseCellBackgroundFormat;
import com.bin.david.form.data.format.bg.ICellBackgroundFormat;
import com.bin.david.form.data.style.FontStyle;
import com.bin.david.form.utils.DensityUtils;
import com.blankj.utilcode.utils.ToastUtils;
import com.flyco.tablayout.CommonTabLayout;
import com.github.jdsjlzx.interfaces.OnItemClickListener;
import com.zdjc.zdjcyun.R;
import com.zdjc.zdjcyun.base.BaseModel;
import com.zdjc.zdjcyun.databinding.FragmentWarningMessageBinding;
import com.zdjc.zdjcyun.mvp.entity.AlarmListBean;
import com.zdjc.zdjcyun.mvp.entity.WarningEntity;
import com.zdjc.zdjcyun.mvp.presenter.impl.WarningPresenterImpl;
import com.zdjc.zdjcyun.mvp.ui.fragment.WarningMessageFragment;
import com.zdjc.zdjcyun.mvp.viewmodel.IWarningMessageModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


/**
 * Created by ali on 2017/2/20.
 */

public class WarningModel extends BaseModel<FragmentWarningMessageBinding,WarningPresenterImpl> implements IWarningMessageModel,
        OnItemClickListener,View.OnClickListener{

    private SmartTable<AlarmListBean> smartTable;
    private int page = 1;
    private ArrayList<Integer> unconfirmed = new ArrayList<>();
    private ArrayList<Integer> confirmed = new ArrayList<>();
    private int rowPosition;
    private int totalPageNum;
    private CommonTabLayout commonTabLayout;

    @Override
    public void onCreate() {
        inData();
        initListener();
    }

    private void inData() {
        smartTable = (SmartTable<AlarmListBean>)mBinder.warningTable;
        smartTable.getConfig().setShowTableTitle(false);
        getData(false);
    }

    public void getData(boolean isPush) {
        if (isPush){
            page = 1;
        }
        Map<String,String> map = new HashMap<>();
        map.put("pageNum", page + "");
        map.put("pageSize", 20 + "");
        mControl.getWarningList(this,map,1);
    }

    public void getView(CommonTabLayout commonTabLayout){
        this.commonTabLayout = commonTabLayout;
    }

    @Override
    public void onBeforeRequest(int tag) {
        UI.showWaitDialog();
    }

    @Override
    public void onSuccess(Object bean, int tag) {
        switch (tag) {
            case 1:
                WarningEntity.DataBean dataBean = (WarningEntity.DataBean) bean;
                //推送过来告警信息
                if (commonTabLayout!=null){
                    commonTabLayout.showMsg(2, dataBean.getTotal());
                    commonTabLayout.setMsgMargin(2, -5, 5);
                }
                totalPageNum = ((dataBean.getTotal()-1)/20)+1;
                ArrayList<AlarmListBean> alarmList = dataBean.getAlarmList();
                if (alarmList.size() ==0){
                    mBinder.rlTable.setVisibility(View.GONE);
                    mBinder.llWarning.setVisibility(View.VISIBLE);
                }else {
                    confirmed.clear();
                    unconfirmed.clear();
                    mBinder.rlTable.setVisibility(View.VISIBLE);
                    mBinder.llWarning.setVisibility(View.GONE);
                    for (int i = 0; i < alarmList.size(); i++) {
                        if ("已确认".equals(alarmList.get(i).getAlarmStatusName())){
                            confirmed.add(i);
                        }else if ("未确认".equals(alarmList.get(i).getAlarmStatusName())){
                            unconfirmed.add(i);
                        }
                    }
                    smartTable.setData(alarmList);
                    mBinder.tvPageNumber.setText((page+"/"+totalPageNum));
                    setData();
                }
                break;
            case 2:
                confirmed.add(rowPosition);
                getData(false);
                break;
        }
    }

    @Override
    public void onError(String errorMsg, int tag) {

    }


    @Override
    public void onItemClick(View view, int position) {

    }

    @Override
    public void initListener() {
        mBinder.left.setOnClickListener(this);
        mBinder.right.setOnClickListener(this);
    }



    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.left:
                if (page>1){
                    page--;
                    getData(false);
                }else {
                    ToastUtils.showLongToast("已经是第一页了");
                }
                mBinder.tvPageNumber.setText((page+"/"+totalPageNum));
                break;
            case R.id.right:
                if (page<totalPageNum){
                    page++;
                    getData(false);
                }else {
                    ToastUtils.showLongToast("已经是最后一页了");
                }
                mBinder.tvPageNumber.setText((page+"/"+totalPageNum));
                break;
            default:
                break;
        }
    }


    @Override
    public void onResume() {
        getData(false);
    }

    public void setData(){
        FontStyle.setDefaultTextSize(DensityUtils.sp2px(getContext(),15)); //设置全局字体大小
        WarningMessageFragment warningMessageFragment = (WarningMessageFragment)UI;
        WindowManager wm = warningMessageFragment.getActivity().getWindowManager();
        int screenWith = wm.getDefaultDisplay().getWidth();
        smartTable.getConfig().setMinTableWidth(screenWith); //设置最小宽度 屏幕宽度

        smartTable.getConfig().setColumnTitleBackground(
                new BaseBackgroundFormat(getContext().getResources().getColor(R.color.theme_color)));

        FontStyle fontTitleStyle = new FontStyle();
        fontTitleStyle.setTextColor(getContext().getResources().getColor(R.color.white));

        smartTable.getConfig().setColumnTitleStyle(fontTitleStyle);


        smartTable.getConfig().setColumnTitleBackground(
                new BaseBackgroundFormat(getContext().getResources().getColor(R.color.theme_color))
        );
        smartTable.getConfig().setContentBackground(
                new BaseBackgroundFormat(getContext().getResources().getColor(R.color.theme_color)));

        smartTable.getConfig().setYSequenceBackground(
                new BaseBackgroundFormat(getContext().getResources().getColor(R.color.theme_color)));

        smartTable.getConfig().setShowXSequence(false);

        ICellBackgroundFormat<CellInfo> backgroundFormat2 = new BaseCellBackgroundFormat<CellInfo>() {
            @Override
            public int getBackGroundColor(CellInfo position) {
                if(position.row%2 == 0){
                    return ContextCompat.getColor(getContext(),R.color.theme_deep_color);
                }
                return TableConfig.INVALID_COLOR;

            }


            @Override
            public int getTextColor(CellInfo position) {
                if(confirmed.contains(position.row)) {
                    return ContextCompat.getColor(getContext(), R.color.white);
                }else{
                    return ContextCompat.getColor(getContext(), R.color.timepicker_toolbar_bg);
                }
            }
        };
        smartTable.getConfig().setContentCellBackgroundFormat(backgroundFormat2);



        smartTable.getTableData().setOnRowClickListener((column, alarmListBean, col, row) -> {
            if (!confirmed.contains(row)){
                Map<String,String> map = new HashMap<>();
                map.put("alarmId", alarmListBean.getAlarmId()+"");
                mControl.changeWarningMsg(this,map,2);
                rowPosition = row;
            }
        });
    }
}
