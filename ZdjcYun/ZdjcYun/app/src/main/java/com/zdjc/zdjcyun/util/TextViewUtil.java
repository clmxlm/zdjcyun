package com.zdjc.zdjcyun.util;

import android.graphics.Paint;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.UnderlineSpan;
import android.widget.TextView;


import com.zdjc.zdjcyun.app.BaseApplication;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * ClassName：TextViewUtil<p>
 * Author：Oubowu<p>
 * Fuction：TextView工具类<p>
 * CreateDate：2015/7/24 10:18<p>
 * UpdateAuthor：<p>
 * UpdateDate：<p>
 */
public class TextViewUtil {

    //给TextView设置部分大小
    public static void setPartialSize(TextView tv, int start, int end, int textSize) {
        String s = tv.getText().toString();
        Spannable spannable = new SpannableString(s);
        spannable.setSpan(new AbsoluteSizeSpan(textSize), start, end,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        tv.setText(spannable);
    }

    //给TextView设置部分颜色
    public static void setPartialColor(TextView tv, int start, int end, int textColor) {
        String s = tv.getText().toString();
        Spannable spannable = new SpannableString(s);
        spannable.setSpan(new ForegroundColorSpan(textColor), start, end,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        tv.setText(spannable);
    }

    //给TextView设置部分字体大小和颜色
    public static void setPartialSizeAndColor(TextView tv, int start, int end, int textSize, int textColor) {
        String s = tv.getText().toString();
        Spannable spannable = new SpannableString(s);
        spannable.setSpan(new AbsoluteSizeSpan(textSize, false), start, end,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannable.setSpan(new ForegroundColorSpan(textColor), start, end,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        tv.setText(spannable);
    }

    //给TextView设置下划线
    public static void setUnderLine(TextView tv) {
        if (tv.getText() != null) {
            String udata = tv.getText().toString();
            SpannableString content = new SpannableString(udata);
            content.setSpan(new UnderlineSpan(), 0, udata.length(), 0);
            tv.setText(content);
            content.setSpan(new UnderlineSpan(), 0, udata.length(), 0);
        } else {
            tv.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
        }
    }

    //取消TextView的置下划线
    public static void clearUnderLine(TextView tv) {
        tv.getPaint().setFlags(0);
    }

    //半角转换为全角
    public static String ToDBC(String input) {
        char[] c = input.toCharArray();
        for (int i = 0; i < c.length; i++) {
            if (c[i] == 12288) {
                c[i] = (char) 32;
                continue;
            }
            if (c[i] > 65280 && c[i] < 65375) c[i] = (char) (c[i] - 65248);
        }
        return new String(c);
    }

    //去除特殊字符或将所有中文标号替换为英文标号
    public static String replaceCharacter(String str) {
        str = str.replaceAll("【", "[").replaceAll("】", "]").replaceAll("！", "!")
                .replaceAll("：", ":").replaceAll("（", "(").replaceAll("（", ")");// 替换中文标号
        String regEx = "[『』]"; // 清除掉特殊字符
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(str);
        return m.replaceAll("").trim();
    }

    /**
     * 单独设置内部字体颜色
     *
     * @param text
     * @param keyworld
     * @return
     */
    public static SpannableStringBuilder getSpannableTextColor(String text, String keyworld,int textColor) {
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(text);
        int spanStartIndex = text.indexOf(keyworld);//*第一个出现的索引位置
        while (spanStartIndex != -1) {
            int spacEndIndex = spanStartIndex + keyworld.length();
            spannableStringBuilder.setSpan(new ForegroundColorSpan(textColor), spanStartIndex, spacEndIndex, Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
            spanStartIndex = text.indexOf(keyworld, spanStartIndex + 1);//*从这个索引往后开始第一个出现的位置
        }
        return spannableStringBuilder;
    }

    /**
     * 单独设置内部字体颜色
     *
     * @param text
     * @param keyworld
     * @return
     */
    public static SpannableStringBuilder getSpannableTextColor(SpannableStringBuilder stringBuilder, String text, String keyworld,int textColor) {
        int spanStartIndex = text.indexOf(keyworld);//*第一个出现的索引位置
        while (spanStartIndex != -1) {
            int spacEndIndex = spanStartIndex + keyworld.length();
            stringBuilder.setSpan(new ForegroundColorSpan(textColor), spanStartIndex, spacEndIndex, Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
            spanStartIndex = text.indexOf(keyworld, spanStartIndex + 1);//*从这个索引往后开始第一个出现的位置
        }
        return stringBuilder;
    }


    public static String getString(int id) {
        return BaseApplication.getContext().getString(id);
    }



}