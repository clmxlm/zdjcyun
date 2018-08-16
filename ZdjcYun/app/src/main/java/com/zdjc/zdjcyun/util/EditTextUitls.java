package com.zdjc.zdjcyun.util;

import android.widget.EditText;
import android.widget.FrameLayout;

/**
 * Created by lenovo on 2016/9/14.
 */
public class EditTextUitls {

    //        下面的代码为 EditTextView 的展示以及背景动画
    public static void delectEt(EditText et , FrameLayout ft, EditTextHolder.OnEditTextFocusChangeListener listener){
        new EditTextHolder(et, ft, null)
                .setmOnEditTextFocusChangeListener(listener);
        et.setSelection(et.getText().length());
    }

}
