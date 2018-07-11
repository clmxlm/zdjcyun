package com.zdjc.zdjcyun.util;

import android.content.Context;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.zdjc.zdjcyun.R;

public class ToastView {


    private Toast mToast;
//    private String message;
    private Handler mHandler = new Handler();
    private boolean canceled = true;

    public ToastView(Context context,String time,String total_change,String single_change,String speed_change) {
//        message = time;
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        //自定义布局
        View view = inflater.inflate(R.layout.toast_view, null);
        //自定义toast文本
        TextView tt_time = view.findViewById(R.id.tt_time);
        TextView tt_total_change = view.findViewById(R.id.tt_total_change);
        TextView tt_single_change = view.findViewById(R.id.tt_single_change);
        TextView tt_speed_change = view.findViewById(R.id.tt_speed_change);
        tt_time.setText(time);
        tt_total_change.setText(total_change);
        tt_single_change.setText(single_change);
        tt_speed_change.setText(speed_change);
        Log.i("ToastUtil", "Toast start...");
        if (mToast == null) {
            mToast = new Toast(context);
            Log.i("ToastUtil", "Toast create...");
        }
        //设置toast居中显示
        mToast.setGravity(Gravity.BOTTOM, 0, 0);
        mToast.setDuration(Toast.LENGTH_LONG);
        mToast.setView(view);
    }

    /**
     * 自定义居中显示toast
     */
    public void show() {
        mToast.show();
        Log.i("ToastUtil", "Toast show...");
    }

    /**
     * 自定义时长、居中显示toast
     * @param duration
     */
    public void show(int duration) {
        TimeCount timeCount = new TimeCount(duration, 500);
        Log.i("ToastUtil", "Toast show...");
        if (canceled) {
            timeCount.start();
            canceled = false;
            showUntilCancel();
        }
    }

    /**
     * 隐藏toast
     */
    private void hide() {
        if (mToast != null) {
            mToast.cancel();
        }
        canceled = true;
        Log.i("ToastUtil", "Toast that customed duration hide...");
    }

    private void showUntilCancel() {
        if (canceled) { //如果已经取消显示，就直接return
            return;
        }
        mToast.show();
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Log.i("ToastUtil", "Toast showUntilCancel...");
                showUntilCancel();
            }
        }, Toast.LENGTH_LONG);
    }

    /**
     *  自定义计时器
     */
    private class TimeCount extends CountDownTimer {

        public TimeCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval); //millisInFuture总计时长，countDownInterval时间间隔(一般为1000ms)
        }

        @Override
        public void onTick(long millisUntilFinished) {
//            tt_time.setText(message);
        }

        @Override
        public void onFinish() {
            hide();
        }
    }


}
