package com.zdjc.zdjcyun.util;

import java.math.BigDecimal;
import java.text.MessageFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * 功能：日期格式化
 * Created by 吴佰双 on 2015/5/20 09:54
 * Mail:wbshuang09@163.com
 */
public class DateUtil {

    public static final SimpleDateFormat DATE_FORMAT_DATE1 = new SimpleDateFormat("yyyyMMdd");

    public static final SimpleDateFormat DEFAULT_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public static final SimpleDateFormat DATE_FORMAT_DATE = new SimpleDateFormat("yyyy-MM-dd");
    public static final SimpleDateFormat DATE_FORMAT_DATE_DOT = new SimpleDateFormat("yyyy.MM.dd");
    public static final SimpleDateFormat DATE_FORMAT_DATE_CN = new SimpleDateFormat("yyyy年MM月dd日");
    public static final SimpleDateFormat DISPLAY_FORMAT = new SimpleDateFormat("MM/dd HH:mm");
    public static final SimpleDateFormat DATE_FORMAT_TIME = new SimpleDateFormat("yyyy-MM-dd  HH:mm aa");
    public static final SimpleDateFormat DEFAULT_DATE_FORMAT1 = new SimpleDateFormat("yyyyMMddHHmmss");
    public static final SimpleDateFormat DATE_FORMAT_TIME_DOT = new SimpleDateFormat("yyyy.MM.dd - HH:mm aa", Locale
            .US);
    public static final SimpleDateFormat DATE_FORMAT_DOT = new SimpleDateFormat("yyyy.MM.dd - HH:mm");

    public static final SimpleDateFormat DATE_FORMAT_DATE2 = new SimpleDateFormat("yyMM");
    private DateUtil() {
    }

    /**
     * 传入自1970的毫秒数，得到的日期格式字符串
     *
     * @param timeInMillis
     * @param dateFormat
     * @return
     */
    public static String getTime(long timeInMillis, SimpleDateFormat dateFormat) {
        return dateFormat.format(new Date(timeInMillis));
    }

    public static String getTime(long timeInMillis) {
        return DEFAULT_DATE_FORMAT.format(new Date(timeInMillis));
    }

    /**
     * get current time in milliseconds
     *
     * @return
     */
    public static long getCurrentTimeInLong() {
        return System.currentTimeMillis();
    }

    /**
     * get current time in milliseconds, format is {@link #DEFAULT_DATE_FORMAT}
     *
     * @return
     */
    public static String getCurrentTimeInString() {
        return getTime(getCurrentTimeInLong());
    }

    /**
     * 字符串转换成日期
     *
     * @param str
     * @return date
     */
    public static Date strToDate(String str, SimpleDateFormat format) {
        Date date = null;
        try {
            date = format.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    public static int getWeekDay(long date){
        Calendar c = Calendar.getInstance();
        c.setTime(new Date(date));
        int dayForWeek = 0;

        if(c.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY){
            dayForWeek = 7;
        }else{
            dayForWeek = c.get(Calendar.DAY_OF_WEEK) - 1;
        }
        return dayForWeek;
    }

    /**
     * 年月日转成毫秒值
     *
     * @param data 要转换的毫秒数
     * @return
     * @author baishuang.wu
     * @date 2014-3-13 上午11:52:06
     */

    public static long formatDuring(String data) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        long millionSeconds = 0;//毫秒
        try {
            millionSeconds = sdf.parse(data).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return millionSeconds;
    }

    public static String getDateToString(long milSecond) {
        Date date = new Date(milSecond);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        return format.format(date);
    }

    public static String getDateToStringss(long milSecond) {
        Date date = new Date(milSecond);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(date);
    }

    /**
     * 将Date转成指定格式
     * @param date
     * @return
     */

    public static String getTime(Date date,SimpleDateFormat format) {//可根据需要自行截取数据显示
        return format.format(date);
    }


    /**
     * 以友好的方式展示时间
     *
     * @param date
     * @return
     */
    public static String getFriendlyTime(Date date) {
        if (date == null) {
            return "时间转换异常";
        }
        String friendlyTime = "";
        Calendar cal = Calendar.getInstance();

        // 判断是否是同一天
        String curDate = DATE_FORMAT_DATE.format(cal.getTime());
        String paramDate = DATE_FORMAT_DATE.format(date);
        if (curDate.equals(paramDate)) {
            int hour = (int) ((cal.getTimeInMillis() - date.getTime()) / 3600000);
            if (hour == 0) {
                friendlyTime = Math.max((cal.getTimeInMillis()
                        - date.getTime()) / 60000, 1) + "分钟前";
            } else {
                friendlyTime = hour + "小时前";
            }

            return friendlyTime;
        }

        long lt = date.getTime() / 86400000;
        long ct = cal.getTimeInMillis() / 86400000;
        int days = (int) (ct - lt);
        int currentMinutes = cal.get(Calendar.MINUTE);
        int currentHours = cal.get(Calendar.HOUR);
        String minutes = currentMinutes >= 10 ? currentMinutes + "" : "0" + currentMinutes;
        if (days == 0) {
            int hour = (int) ((cal.getTimeInMillis() - date.getTime()) / 3600000);
            if (hour == 0) {
                friendlyTime = Math.max((cal.getTimeInMillis() - date.getTime()) / 60000, 1) +
                        "分钟前";
            } else {
                friendlyTime = hour + "小时前";
            }
        } else if (days == 1) {
            friendlyTime = MessageFormat.format("昨天 {0}:{1}", currentHours, minutes);
        } else if (days == 2) {
            friendlyTime = MessageFormat.format("前天 {0}:{1}", currentHours, minutes);
        } else if (days > 2) {
            friendlyTime = DATE_FORMAT_DATE_CN.format(date);
        }
        return friendlyTime;
    }


    /**
     * 提供（相对）精确的除法运算。当发生除不尽的情况时，由scale参数指
     * 定精度，以后的数字四舍五入。
     * @param v1 被除数
     * @param v2 除数
     * @param scale 表示表示需要精确到小数点以后几位。
     * @return 两个参数的商
     */
    public static double div(double v1,double v2,int scale){
        if(scale<0){
            throw new IllegalArgumentException(
                    "The scale must be a positive integer or zero");
        }
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.divide(b2,scale, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    /**
     * 提供精确的小数位四舍五入处理。
     * @param v 需要四舍五入的数字
     * @param scale 小数点后保留几位
     * @return 四舍五入后的结果
     */
    public static double round(double v,int scale){
        if(scale<0){
            throw new IllegalArgumentException("The scale must be a positive integer or zero");
        }
        BigDecimal b = new BigDecimal(Double.toString(v));
        BigDecimal one = new BigDecimal("1");
        return b.divide(one,scale,BigDecimal.ROUND_HALF_UP).doubleValue();
    }
}
