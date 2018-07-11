package com.zdjc.zdjcyun.util;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;

/**
 * 获得屏幕相关的辅助类
 * Dye-Duanxinyuan
 * 2015年2月4日 上午10:13:25
 * V2.0
 */
public class ScreenUtil {
    private ScreenUtil() {
        /* cannot be instantiated */
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    /**
     * 获得屏幕高度
     * @param context
     * @return
     */
    public static int getScreenWidth(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        return outMetrics.widthPixels;
    }

    /**
     * 获得屏幕宽度
     * @param context
     * @return
     */
    public static int getScreenHeight(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        return outMetrics.heightPixels;
    }

    /**
     * 获取屏幕密度（0.75 / 1.0 / 1.5）
     * @param context
     * @return
     */
    protected float getDensity(Context context) {
        DisplayMetrics metric = new DisplayMetrics();
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        wm.getDefaultDisplay().getMetrics(metric);
        return metric.density;
    }

//	/**
//	 * 获得状态栏的高度
//	 * @param context
//	 * @return
//	 */
//	public static int getStatusHeight(Context context){
//
//		int statusHeight = -1;
//		try{
//			Class<?> clazz = Class.forName("com.android.internal.R$dimen");
//			Object object = clazz.newInstance();
//			int height = int.parseInt(clazz.getField("status_bar_height").get(object).toString());
//			statusHeight = context.getResources().getDimensionPixelSize(height);
//		} catch (Exception e){
//			e.printStackTrace();
//		}
//		return statusHeight;
//	}

//	/**
//	 * 获取当前屏幕截图，包含状态栏
//	 * @param activity
//	 * @return
//	 */
//	public static Bitmap snapShotWithStatusBar(Activity activity){
//		View view = activity.getWindow().getDecorView();
//		view.setDrawingCacheEnabled(true);
//		view.buildDrawingCache();
//		Bitmap bmp = view.getDrawingCache();
//		int width = getScreenWidth(activity);
//		int height = getScreenHeight(activity);
//		Bitmap bp = null;
//		bp = Bitmap.createBitmap(bmp, 0, 0, width, height);
//		view.destroyDrawingCache();
//		return bp;
//	}

//	/**
//	 * 获取当前屏幕截图，不包含状态栏
//	 * @param activity
//	 * @return
//	 */
//	public static Bitmap snapShotWithoutStatusBar(Activity activity){
//		View view = activity.getWindow().getDecorView();
//		view.setDrawingCacheEnabled(true);
//		view.buildDrawingCache();
//		Bitmap bmp = view.getDrawingCache();
//		Rect frame = new Rect();
//		activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);
//		int statusBarHeight = frame.top;
//
//		int width = getScreenWidth(activity);
//		int height = getScreenHeight(activity);
//		Bitmap bp = null;
//		bp = Bitmap.createBitmap(bmp, 0, statusBarHeight, width, height- statusBarHeight);
//		view.destroyDrawingCache();
//		return bp;
//	}

//	/**
//	 * 屏幕是否亮的
//	 * 返回true，则表示屏幕“亮”了，否则屏幕“暗”了。
//	 * @return
//	 */
//	public boolean isScreen(Context context) {
//		PowerManager pm = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
//		return pm.isScreenOn();
//	}
//	
}
