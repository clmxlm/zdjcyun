package com.zdjc.zdjcyun.mvp.viewmodel.impl;


import android.content.Intent;
import android.view.View;

import com.github.jdsjlzx.interfaces.OnItemClickListener;
import com.zdjc.zdjcyun.R;
import com.zdjc.zdjcyun.base.BaseModel;
import com.zdjc.zdjcyun.databinding.FragmentPersonerBinding;
import com.zdjc.zdjcyun.mvp.entity.PersonMessageEntity;
import com.zdjc.zdjcyun.mvp.presenter.impl.PersonerPresenterImpl;
import com.zdjc.zdjcyun.mvp.ui.activities.LoginActivity;
import com.zdjc.zdjcyun.mvp.ui.fragment.PersonnerFragment;
import com.zdjc.zdjcyun.mvp.viewmodel.IPersonerModel;
import com.zdjc.zdjcyun.util.PreferenceUtils;

import java.util.HashMap;
import java.util.Map;

import cn.jpush.android.api.JPushInterface;

/**
 * Created by ali on 2017/2/20.
 */

public class PersonerModel extends BaseModel<FragmentPersonerBinding,PersonerPresenterImpl> implements IPersonerModel,
        OnItemClickListener,View.OnClickListener{


    /**
     * 没个界面有自己的Presenter
     */

    @Override
    public void onCreate() {
        inData();
        initListener();
    }

    private void inData() {
        Map<String,String> map = new HashMap<>();
        map.put("userName", PreferenceUtils.getString(getContext(),"uesrName"));
        mControl.getPersonMsg(this,map,2);
    }

    @Override
    public void onBeforeRequest(int tag) {
        UI.showWaitDialog();
    }

    @Override
    public void onSuccess(Object bean, int tag) {
        switch (tag) {
            case 1:
                PreferenceUtils.putInt(getContext(),"projectPosition",0);
                PreferenceUtils.putInt(getContext(),"onePosition",0);
                PreferenceUtils.putInt(getContext(),"twoPosition",0);
                PreferenceUtils.putString(getContext(),"startTime",null);
                PreferenceUtils.putString(getContext(),"endTime",null);
                PreferenceUtils.putString(getContext(),"token","logout");
                JPushInterface.stopPush(getContext());
                Intent intent = new Intent();
                //SoilsenerActivity.class为想要跳转的Activity
                intent.setClass(getContext(), LoginActivity.class);
                getContext().startActivity(intent);
                PersonnerFragment personnerFragment = (PersonnerFragment) UI;
                personnerFragment.getActivity().finish();

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

                mBinder.tvUser.setText(userName);
                mBinder.tvUsername.setText(personertName);
                mBinder.tvPhone.setText(personertPhone);
                mBinder.tvEmail.setText(personertEmail);
                mBinder.tvCompany.setText(personertCompany);
                break;
        }
    }

    @Override
    public void onError(String errorMsg, int tag) {

    }

    @Override
    public void onResume() {
        //"http://120.79.156.20/pic/zd.jpg"
//        ImageLoaderUtils.imageDisPlayResByApp(R.mipmap.login_background,mBinder.ivPersonBackground);
        Map<String,String> map = new HashMap<>();
        map.put("userName", PreferenceUtils.getString(getContext(),"uesrName"));
        mControl.getPersonMsg(this,map,2);
    }

    @Override
    public void onItemClick(View view, int position) {

    }

    @Override
    public void initListener() {
        mBinder.btnLoginOut.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_login_out:
                Map<String,String> map = new HashMap<>();
                map.put("dd","dd");
                mControl.loginOut(this,map,1);
                break;
            default:
                break;
        }
    }
}
