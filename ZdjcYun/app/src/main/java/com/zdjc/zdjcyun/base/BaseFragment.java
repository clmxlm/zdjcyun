package com.zdjc.zdjcyun.base;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.zdjc.zdjcyun.app.BaseApplication;
import com.zdjc.zdjcyun.util.DialogUtil;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;


/**
 * Created by ali on 2017/2/23.
 */

public abstract class BaseFragment<T extends ViewDataBinding, M extends BaseModel> extends Fragment implements IModelActivitiy<T> {
    public T mBinder = null;
    public M mModel = null;
    private DialogUtil dialogUtil;
    protected Activity activity;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Type genType = getClass().getGenericSuperclass();
        Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
        Class<M> bizClass = (Class) params[1];
        try {
            mModel = bizClass.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (java.lang.InstantiationException e) {
            e.printStackTrace();
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mBinder == null) {
            mBinder = DataBindingUtil.inflate(LayoutInflater.from(getActivity()), getLayoutId(), null, false);
            initView();
        }
        mModel.setView(this);
        mModel.onCreate();
        return mBinder.getRoot();
    }

    public abstract int getLayoutId();

    public abstract void initView();

    @Override
    public Context getConText() {
        return activity;
    }

    @Override
    public T getBinder() {
        return mBinder;
    }

    @Override
    public void onStart() {
        super.onStart();
        mModel.onStart();
    }


    @Override
    public void onResume() {
        super.onResume();
        mModel.onResume();
    }

    @Override
    public void onStop() {
        super.onStop();
        mModel.onStop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        BaseApplication.getRefWatcher().watch(getActivity());
        mModel.onDestroy();
    }

    /**
     * Deprecated on API 23
     * Use onAttachToContext instead
     */
    @SuppressWarnings("deprecation")
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            onAttachToContext(activity);
        }
    }

    /**
     * 因为在fragment里面直接getactivity获取是不安全的会出现空指针有时候，在低内存的时候复现100%
     * 这样获取activity不会出现异常getactivity报空
     * @param context
     */
    protected void onAttachToContext(Context context) {
        activity=(Activity) context;

        //do something
    }

    @TargetApi(23)
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        onAttachToContext(context);
    }

    /**
     * 懒加载过
     */
    private boolean isLazyLoaded;

    private boolean isPrepared;


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        isPrepared = true;
        //只有Fragment onCreateView好了，
        //另外这里调用一次lazyLoad(）
        lazyLoad();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        lazyLoad();
    }

    /**
     * 调用懒加载
     */

    private void lazyLoad() {
        if (getUserVisibleHint() && isPrepared && !isLazyLoaded) {
            onLazyLoad();
            isLazyLoaded = false;
        }else {
            if(!getUserVisibleHint() && isPrepared){
                onInvisible();
            }
        }

    }

    /**
     * 可见
     */
    protected void onLazyLoad(){

    }

    /**
     * 不可见
     */
    protected void onInvisible() {

    }


    /**
     * 显示进度条
     */
    @Override
    public void showWaitDialog() {
        if (dialogUtil == null) {
            dialogUtil = new DialogUtil(getActivity());
        }
        dialogUtil.show();
    }

    /**
     * 隐藏进度条
     */
    @Override
    public void hideWaitDialog() {
        if (dialogUtil != null) {
            dialogUtil.dismiss();
        }
    }


}
