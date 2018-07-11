package com.zdjc.zdjcyun.base;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.ViewDataBinding;
import android.graphics.Typeface;
import android.support.v4.app.Fragment;
import android.view.View;

import com.blankj.utilcode.utils.ToastUtils;
import com.flyco.tablayout.CommonTabLayout;
import com.zdjc.zdjcyun.R;
import com.zdjc.zdjcyun.network.RequestCallBack;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;

import static com.zdjc.zdjcyun.util.AppUtils.getAssets;

/**
 * Created by ali on 2017/2/15.
 */

public abstract class BaseModel<T extends ViewDataBinding, M extends IBaseControl> extends BaseObservable implements IModel, RequestCallBack {

    protected IModelActivitiy UI = null;
    protected T mBinder = null;
    protected M mControl = null;
    protected CommonTabLayout commonTabLayout;
    protected String[] mMonths = new String[] {
            "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Okt", "Nov", "Dec"
    };
    protected Typeface mTfLight;
    protected ArrayList<Fragment> mFragments = new ArrayList<>();
    public View mNotWorkView;//无网络图片

    public void setView(IModelActivitiy activity) {

        UI = activity;
        mBinder = (T) UI.getBinder();
        mNotWorkView = View.inflate(UI.getConText(), R.layout.network_error, null);

        Type genType = getClass().getGenericSuperclass();
        Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
        Class<M> bizClass = (Class) params[1];
        try {
            mControl = bizClass.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onCreate() {
        mTfLight = Typeface.createFromAsset(getAssets(), "OpenSans-Light.ttf");

    }

    @Override
    public void onStart() {

    }

    @Override
    public void onResume() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void onDestroy() {
        if(mControl!=null){
            mControl.disposableCancel();
        }
        if(UI!=null){
            UI.hideWaitDialog();
        }
    }

    protected float getRandom(float range, float startsfrom) {
        return (float) (Math.random() * range) + startsfrom;
    }

    @Override
    public void beforeRequest(int tag) {

        onBeforeRequest(tag);
    }

    @Override
    public void success(Object data, int tag) {
        UI.hideWaitDialog();
        onSuccess(data, tag);
    }

    @Override
    public void error(String errorMsg,int tag) {
        UI.hideWaitDialog();
        onError(errorMsg,tag);
        ToastUtils.showShortToast(errorMsg);
    }


    /**
     * 请求开始
     */
    public abstract void onBeforeRequest(int tag);

    /**
     * 请求成功
     *
     * @param tag  标签
     * @param bean 实体类对象
     */
    protected  void onSuccess(Object bean, int tag){

    }


    /**
     * 请求成功
     *
     * @param tag  标签
     * @param bean 实体类对象
     */
    protected void onSuccess(String answerId,Object bean, int tag){

    }

    /**
     * 请求失败
     */
    public abstract void onError(String errorMsg ,int tag);


    public Context getContext() {
        return UI.getConText();
    }



}
