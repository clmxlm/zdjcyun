package com.zdjc.zdjcyun.mvp.ui.activities;

import android.view.KeyEvent;

import com.zdjc.zdjcyun.R;
import com.zdjc.zdjcyun.base.BaseActivity;
import com.zdjc.zdjcyun.databinding.ActivityDataMonitoringBinding;
import com.zdjc.zdjcyun.databinding.ActivityPdfBinding;
import com.zdjc.zdjcyun.mvp.viewmodel.impl.DataMonitoringActivityModel;
import com.zdjc.zdjcyun.mvp.viewmodel.impl.PDFModel;

public class DataMonitoringActivity extends BaseActivity<ActivityDataMonitoringBinding, DataMonitoringActivityModel>{


    @Override
    public int getLayoutId() {
        setWindowStatusBarColor(this, R.color.theme_color);
        return R.layout.activity_data_monitoring;
    }

    @Override
    public void initView() {

    }

    /**
     * 对返回键进行监听
     * @param keyCode
     * @param event
     * @return
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            finish();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

}
