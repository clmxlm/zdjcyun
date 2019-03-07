package com.zdjc.zdjcyun.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.blankj.utilcode.utils.ToastUtils;
import com.githang.statusbar.StatusBarCompat;
import com.zdjc.zdjcyun.R;
import com.zdjc.zdjcyun.app.BaseApplication;
import com.zdjc.zdjcyun.app.SystemStatusManager;
import com.zdjc.zdjcyun.mvp.ui.activities.LoginActivity;
import com.zdjc.zdjcyun.mvp.ui.activities.MainActivity;
import com.zdjc.zdjcyun.mvp.ui.activities.SplashActivity;
import com.zdjc.zdjcyun.util.DialogUtil;
import com.zdjc.zdjcyun.util.MyUtils;
import com.zdjc.zdjcyun.util.NetworkUtils;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;


public abstract class BaseActivity<T extends ViewDataBinding, M extends BaseModel> extends AppCompatActivity implements IModelActivitiy<T> {

    public T mBinder = null;
    public M mModel = null;
    private DialogUtil dialogUtil;
    private SystemStatusManager tintManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mBinder = DataBindingUtil.setContentView(this, getLayoutId());

        Type genType = getClass().getGenericSuperclass();
        Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
        Class<M> bizClass = (Class) params[1];
        try {
            mModel = bizClass.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        mModel.setView(this);
        mModel.onCreate();
        init();
        initView();

        Window window = getWindow();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
                    | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        }
        // 虚拟导航键设置颜色
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            if (this instanceof LoginActivity){
                window.setNavigationBarColor(getResources().getColor(R.color.white_touming));
            }else if (this instanceof SplashActivity){
                window.setNavigationBarColor(getResources().getColor(R.color.white_touming));
            }else {
                window.setNavigationBarColor(getResources().getColor(R.color.theme_color));
            }

        }

        if (!NetworkUtils.isNetworkAvailable(this)){
            ToastUtils.showShortToast("请检查网络设置！");
        }

    }
    protected void init() {
        if (this instanceof LoginActivity) {
            // 设置状态栏的颜色
            StatusBarCompat.setStatusBarColor(this, getResources().getColor(R.color.white_touming), false);
        } else if (this instanceof SplashActivity){
            StatusBarCompat.setStatusBarColor(this, getResources().getColor(R.color.white_touming), false);
        }else if (this instanceof MainActivity){
            // 设置状态栏的颜色
            StatusBarCompat.setStatusBarColor(this, getResources().getColor(R.color.theme_color), false);
        }

    }

    public abstract int getLayoutId();

    @Override
    public T getBinder() {
        return mBinder;
    }

    @Override
    public Context getConText() {
        return this;
    }

    /**
     * 初始化 视图
     */
    public abstract void initView();


    @Override
    protected void onStart() {
        super.onStart();
        mModel.onStart();
    }


    @Override
    protected void onResume() {
        super.onResume();
        mModel.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mModel.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mModel.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mModel.onDestroy();
        BaseApplication.getRefWatcher().watch(this);
        MyUtils.fixInputMethodManagerLeak(this);
    }

    /**
     * 跳转activity
     *
     * @param tarActivity 指定的activity
     */
    public void intent2Activity(Class<? extends Activity> tarActivity) {
        Intent intent = new Intent(getApplicationContext(), tarActivity);
        startActivity(intent);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

    }

    /**
     * 显示进度条
     */
    @Override
    public void showWaitDialog() {
//        if (hasWindowFocus()) {
            if (dialogUtil == null) {
                dialogUtil = new DialogUtil(this);
            }
            dialogUtil.show();
//        }
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

    public static void setWindowStatusBarColor(Activity activity, int colorResId) {
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                Window window = activity.getWindow();
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                window.setStatusBarColor(activity.getResources().getColor(colorResId));

                //底部导航栏
                //window.setNavigationBarColor(activity.getResources().getColor(colorResId));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private int getStatusBarHeight() {
        int result = 0;
        int resId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resId > 0) {
            result = getResources().getDimensionPixelSize(resId);
        }
        return result;
    }

}
