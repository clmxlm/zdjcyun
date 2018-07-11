package com.zdjc.zdjcyun.event;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.ScrollView;
import android.widget.TextView;

import com.bin.david.form.core.SmartTable;
import com.zdjc.zdjcyun.R;
import com.zdjc.zdjcyun.util.ScreenUtil;


public class BottomPopWindow extends PopupWindow {

    public final LinearLayout ll_description;
    public ScrollView sl_tablelayout;
    public final TextView tv_project_name,tv_detection_indicator,tv_surveys_point_name,tv_terminal_number,tv_sensor_number,tv_sate;
    private final View v_tou;
    public  SmartTable table;
    private Activity activity;

    public BottomPopWindow(final Activity context) {
        this.activity = context;
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View conentView = inflater.inflate(R.layout.pop_project_bottom, null);
        // 设置SelectPicPopupWindow的View
        this.setContentView(conentView);
        // 设置SelectPicPopupWindow弹出窗体的宽
        this.setWidth(ScreenUtil.getScreenWidth(context));
        // 设置SelectPicPopupWindow弹出窗体的高
        this.setHeight(ScreenUtil.getScreenHeight(context));
        // 设置SelectPicPopupWindow弹出窗体可点击
        this.setFocusable(true);

        this.setOutsideTouchable(true);
        // 刷新状态
        this.update();
        // 实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(0000000000);
        // 点back键和其他地方使其消失,设置了这个才能触发OnDismisslistener ，设置其他控件变化等操作
        this.setBackgroundDrawable(dw);


        // mPopupWindow.setAnimationStyle(android.R.style.Animation_Dialog);
        // 设置SelectPicPopupWindow弹出窗体动画效果
        this.setAnimationStyle(R.style.AnimationPreview2);
        sl_tablelayout = conentView.findViewById(R.id.sl_tablelayout);
        ll_description = conentView.findViewById(R.id.ll_description);
        tv_project_name = conentView.findViewById(R.id.tv_project_name);
        tv_detection_indicator = conentView.findViewById(R.id.tv_detection_indicator);
        tv_surveys_point_name = conentView.findViewById(R.id.tv_surveys_point_name);
        tv_terminal_number = conentView.findViewById(R.id.tv_terminal_number);
        tv_sensor_number = conentView.findViewById(R.id.tv_sensor_number);
        tv_sate = conentView.findViewById(R.id.tv_sate);
        table = conentView.findViewById(R.id.table);
        v_tou = conentView.findViewById(R.id.v_tou);
//        table.setZoom(true,0.5f,3f);

        v_tou.setOnClickListener(view -> dismiss());
    }

    public void showPopupWindow(View parent) {
        if (!this.isShowing()) {
            this.showAsDropDown(parent);
        } else {
            this.dismiss();
        }
    }
    /**
     * 设置添加屏幕的背景透明度
     * @param bgAlpha
     */
    public void backgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = activity.getWindow().getAttributes();
                lp.alpha = bgAlpha; //0.0-1.0
        activity.getWindow().setAttributes(lp);
    }

}
