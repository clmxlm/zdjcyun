package com.zdjc.zdjcyun.util;

import android.app.Activity;
import android.graphics.Point;
import android.util.DisplayMetrics;
import android.view.Display;


/**
 * 适配屏幕大小的工具
 */
public class ZoomUtils {
    public final static int INDEX_W = 720; //宽基数
    public final static int INDEX_H = 1280; //高基数

    public static int W = INDEX_W;    //当前屏幕宽
    public static int H = INDEX_H; //当前屏幕高

    public static float scaledDensity; //转换用的屏幕缩放比

    /**
     * 初始化
     */
    public static void init(Activity at) {
        DisplayMetrics m_dm = new DisplayMetrics();
        //获取屏幕分辨率
        at.getWindowManager().getDefaultDisplay().getMetrics(m_dm);
        W = m_dm.widthPixels;
        H = m_dm.heightPixels;
        scaledDensity = m_dm.scaledDensity;
    }


    /**
     * 得到适配好的宽
     */
    public static int w(float w) {
        if (w == 0) {
            return 0;
        }
        return (int) ((w / (float) INDEX_W) * W);
    }

    /**
     * 得到适配好的高
     */
    public static int h(float h) {
        if (h == 0) {
            return 0;
        }
        return (int) ((h / (float) INDEX_H) * H);
    }

    /**
     * 将dip或dp转换为px
     */
    public static int dip2px(float dip) {
        return (int) (dip * scaledDensity + 0.5f);
    }

    /**
     * 将px转换为dip
     */
    public static int px2dip(float px) {
        return (int) (px / scaledDensity + 0.5f);
    }


    /**
     * 测量用将dip或dp转换为px
     */
    public static void m_dip2px(float dip) {
    }

    /**
     * 获取手机屏幕的高度
     *
     * @param activity Activity
     * @return 返回手机屏幕的高度
     */
    public static int getHeight(Activity activity) {
        Display display = activity.getWindowManager().getDefaultDisplay(); //Activity#getWindowManager()
        Point size = new Point();
        display.getSize(size);
        int height = size.y;//获取屏幕的高度
        return height;
    }


}
