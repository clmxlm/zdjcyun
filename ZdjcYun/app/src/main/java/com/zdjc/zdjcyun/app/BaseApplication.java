package com.zdjc.zdjcyun.app;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

import com.baidu.mapapi.SDKInitializer;
import com.blankj.utilcode.utils.CrashUtils;
import com.blankj.utilcode.utils.LogUtils;
import com.blankj.utilcode.utils.ToastUtils;
import com.blankj.utilcode.utils.Utils;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;
import com.zdjc.zdjcyun.BuildConfig;

import cn.jpush.android.api.JPushInterface;


/**
 * Created by ali on 2017/2/15.
 */

public class BaseApplication extends Application {

    private static BaseApplication mContext;
    private RefWatcher refWatcher;



    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;

        /**
         * 初始化工具类包
         */
        Utils.init(this);
        initJpush();
        initBaiduMap();
//        initDB();
        initLeakCanary();
        initLogUtils();
        initCrashUtils();

    }

    private void initJpush() {
        // 设置开启日志,发布时请关闭日志
        JPushInterface.setDebugMode(true);
        //初始化极光推送sdk
        JPushInterface.init(this);
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    private void initBaiduMap() {
         //在使用SDK各组件之前初始化context信息，传入ApplicationContext
        //注意该方法要再setContentView方法之前实现
        SDKInitializer.initialize(this);
    }

//    private void initDB() {
//        GreenDaoHelper.initDatabase(this);
//    }

    /**
     * 初始化全局崩溃日志收集
     */
    private void initCrashUtils() {
        //启动本地日志
        CrashUtils.getInstance().init();
    }

    /**
     * 初始化日志类
     */
    private void initLogUtils() {
        LogUtils.Builder builder = LogUtils.getBuilder();
        builder.setTag("ques")
                .setLogSwitch(true)
                .setLog2FileSwitch(false)
                .create();
        ToastUtils.init(false);
    }

    /**
     * 获取ApplicationContext
     *
     * @return
     */
    public static BaseApplication getContext() {
        return mContext;
    }

    /**
     * 初始化内存溢出监测工具
     */
    private void initLeakCanary() {
        if (BuildConfig.DEBUG) {
            refWatcher = LeakCanary.install(this);
        } else {
            refWatcher = installLeakCanary();
        }
    }

    /**
     * 监控Activity和Fragment
     *
     * @return
     */
    public static RefWatcher getRefWatcher() {
        return getContext().refWatcher;
    }


    /**
     * release版本使用此方法
     */
    /**
     * release版本使用此方法
     */
    protected RefWatcher installLeakCanary() {
        return RefWatcher.DISABLED;
    }

    @Override
    public void onLowMemory() {
        System.gc();
        super.onLowMemory();
    }

    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
    }
}
