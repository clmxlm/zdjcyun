package com.zdjc.zdjcyun.util;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.PopupWindow;

import com.zdjc.zdjcyun.R;

import java.lang.reflect.Method;


public class PopWindowUtil {
    @SuppressLint("StaticFieldLeak")
    private static Activity context;

    public static void popWindow(View v,Activity activity) {
        context = activity;
        View view = LayoutInflater.from(activity).inflate(R.layout.popwindow_layout, null);
        //设置屏幕的高度和宽度
        final PopupWindow pop = new PopupWindow(view, ScreenUtil.getScreenWidth(activity), ScreenUtil.getScreenHeight(activity)*3/10);
        //如果不设置背景颜色的话，无法是pop dimiss掉。
        pop.setBackgroundDrawable(activity.getResources().getDrawable(R.drawable.popupwindow_background));
        pop.setOutsideTouchable(true); pop.setAnimationStyle(R.style.MyPopupWindow_anim_style);



        /** * 设置popwindow的弹出的位置. *
         1：首先要判断是否有navigation bar。如果有的的话，要把他们的高度给加起来。 * *
         2：showAtLocation（）；是pop相对于屏幕而言的。 * *
         3：如果是 pop.showAsDropDown();则是相对于你要点击的view的位置。设置的坐标。
         */
        if(checkDeviceHasNavigationBar2(activity)){
            int heigth_tobottom =getNavigationBarHeight();
            pop.showAtLocation(v, Gravity.BOTTOM,0,heigth_tobottom);
        }else {
            pop.showAtLocation(v, Gravity.BOTTOM,0,0);
        }
        //设置 背景的颜色为 0.5f 的透明度
        backgroundAlpha(0.5f);
        pop.setOnDismissListener(() -> backgroundAlpha(1.0f));

    }

    private static void backgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = context.getWindow().getAttributes();
        lp.alpha = bgAlpha;
        context.getWindow().setAttributes(lp);
    }

    /**
     * /获取是否存在虚拟按键 NavigationBar：如果是有就返回true,如果是没有就是返回的false。第二种方法
     */
    private static boolean checkDeviceHasNavigationBar2(Context context) {
        boolean hasNavigationBar = false;
        Resources rs = context.getResources();
        int id = rs.getIdentifier("config_showNavigationBar", "bool", "android");
        if (id > 0) {
            hasNavigationBar = rs.getBoolean(id);
        }
        try {
            Class systemPropertiesClass = Class.forName("android.os.SystemProperties");
            Method m = systemPropertiesClass.getMethod("get", String.class);
            String navBarOverride = (String) m.invoke(systemPropertiesClass, "qemu.hw.mainkeys");
            if ("1".equals(navBarOverride)) {
                hasNavigationBar = false;
            } else if ("0".equals(navBarOverride)) {
                hasNavigationBar = true;
            }
        } catch (Exception e) {
        }
        return hasNavigationBar;
    }

    /**
     * 获取navigationbar的高度。
     */
    private static int getNavigationBarHeight() {
        Resources resources = context.getResources();
        int resourceId = resources.getIdentifier("navigation_bar_height", "dimen", "android");
        int height = resources.getDimensionPixelSize(resourceId);
        return height;
    }
}
