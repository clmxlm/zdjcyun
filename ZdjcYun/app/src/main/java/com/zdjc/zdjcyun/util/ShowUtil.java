package com.zdjc.zdjcyun.util;

import android.app.Activity;
import android.view.WindowManager;

public class ShowUtil {

    /**
     * 设置屏幕透明度
     * @param activity
     */
    public static void setWindowBack(Activity activity, float bgAlpha) {
        WindowManager.LayoutParams lp = activity.getWindow().getAttributes();
        lp.alpha = bgAlpha;// 0.0~1.0
        activity.getWindow().setAttributes(lp);
    }
}
