package com.zdjc.zdjcyun.event;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.widget.DrawerLayout;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.zdjc.zdjcyun.R;

/**
 * 弹窗视图
 */
public class ConfirmPopWindow extends PopupWindow{
    private Context context;
    public TextView tv_monitorPoint, tv_all;
    public ConfirmPopWindow(Context context) {
        super(context);
        this.context = context;
        initalize();
    }
 
    private void initalize() {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.pop_window, null);
        tv_monitorPoint = view.findViewById(R.id.tv_monitorPoint);
        tv_all = view.findViewById(R.id.tv_all);
        setContentView(view);
        initWindow();
    }
 
    private void initWindow() {
        DisplayMetrics d = context.getResources().getDisplayMetrics();
        this.setWidth((int) (d.widthPixels * 0.35));
        this.setHeight(DrawerLayout.LayoutParams.WRAP_CONTENT);
        this.setFocusable(true);
        this.setOutsideTouchable(true);
        this.update();
        //实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(0x00000000);
        //设置SelectPicPopupWindow弹出窗体的背景
        this.setBackgroundDrawable(dw);
        backgroundAlpha((Activity) context, 0.8f);
        this.setOnDismissListener(() -> backgroundAlpha((Activity) context, 1f));
    }
 
    /**
     * 设置添加屏幕的背景透明度
     * @param context
     * @param bgAlpha
     */
    public void backgroundAlpha(Activity context, float bgAlpha) {
        WindowManager.LayoutParams lp = context.getWindow().getAttributes();
        lp.alpha = bgAlpha;
        context.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        context.getWindow().setAttributes(lp);
    }
 
    public void showAtBottom(View view) {
        //弹窗位置设置
        showAsDropDown(view);
        //showAtLocation(view, Gravity.TOP | Gravity.RIGHT, 10, 110);//有偏差
    }

 
}