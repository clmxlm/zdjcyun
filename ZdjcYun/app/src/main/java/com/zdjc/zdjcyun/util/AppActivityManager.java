package com.zdjc.zdjcyun.util;

import android.app.Activity;
import android.content.Context;

import com.zdjc.zdjcyun.mvp.ui.activities.LoginActivity;
import com.zdjc.zdjcyun.mvp.ui.activities.MainActivity;

import java.util.Stack;

/**
 * @author ClmXlm
 * @create 2019/2/28
 * @Describe/**
 * 应用程序Activity管理类：用于Activity管理和应用程序退出
 */

public class AppActivityManager {

    private static Stack<Activity> activityStack;

    private static AppActivityManager instance;

    private AppActivityManager() {
    }

    /**
     * 单一实例
     */
    public static AppActivityManager getAppManager() {
        if( instance == null ) {
            instance = new AppActivityManager();
        }
        return instance;
    }

    /**
     * 添加Activity到堆栈
     */
    public void addActivity( Activity activity ) {
        if( activityStack == null ) {
            activityStack = new Stack<Activity>();
        }
        activityStack.add( activity );
    }

    /**
     * 获取当前Activity（堆栈中最后一个压入的）
     */
    public Activity currentActivity() {
        Activity activity = activityStack.lastElement();
        return activity;
    }

    /**
     * 结束当前Activity（堆栈中最后一个压入的）
     */
    public void finishActivity() {
        Activity activity = activityStack.lastElement();
        finishActivity( activity );
    }

    /**
     * 结束指定的Activity
     */
    public void finishActivity( Activity activity ) {
        if( activity != null ) {
            activityStack.remove( activity );
            activity.finish();
            activity = null;
        }
    }

    /**
     * 结束指定类名的Activity
     */
    public void finishActivity( Class<?> cls ) {
        for( int i = 0; i < activityStack.size(); i++ ) {
            Activity activity = activityStack.get( i );
            if( activity.getClass().equals( cls ) ) {
                activityStack.remove( activity );
                i--;
                activity.finish();
                activity = null;
            }
        }
    }

    /**
     * 结束所有Activity
     */
    public void finishAllActivity() {
        for( int i = 0, size = activityStack.size(); i < size; i++ ) {
            if( null != activityStack.get( i ) ) {
                Activity activity = activityStack.get( i );
                if( !activity.isFinishing() ) {
                    activity.finish();
                }
            }
        }
        activityStack.clear();
    }

    /**
     * 移除所有的界面(不包括主页)
     */
    public void finishAllActivityNoMain() {
        for( int i = 0, size = activityStack.size(); i < size; i++ ) {
            if( null != activityStack.get( i ) ) {
                Activity activity = activityStack.get( i );
                if( !activity.isFinishing() && !activity.getClass().equals( MainActivity.class ) ) {
                    activity.finish();
                }
            }
        }
        activityStack.clear();
    }
    /**
     * 移除所有的界面(不包括登录界面)
     */
    public void finishAllActivityNoLogin() {
        for( int i = 0, size = activityStack.size(); i < size; i++ ) {
            if( null != activityStack.get( i ) ) {
                Activity activity = activityStack.get( i );
                if( !activity.isFinishing() && !activity.getClass().equals( LoginActivity.class ) ) {
                    activity.finish();
                }
            }
        }
        activityStack.clear();
    }

    /**
     * 退出应用程序
     */
    public void AppExit( Context context ) {
        try {
            finishAllActivity();
            /*
             * Intent intent = new Intent(context, PhoneBrandActivity.class);
             * PendingIntent restartIntent = PendingIntent.getActivity( context,
             * 0, intent, Intent.FLAG_ACTIVITY_NEW_TASK); //退出程序 AlarmManager
             * mgr =
             * (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
             * mgr.set(AlarmManager.RTC, System.currentTimeMillis() + 1000,
             * restartIntent); // 1秒钟后重启应用
             */
            // 杀死该应用进程
            android.os.Process.killProcess( android.os.Process.myPid() );
            System.exit( 0 );
        }
        catch( Exception e ) {
        }
    }

}
