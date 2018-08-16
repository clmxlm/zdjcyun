package com.zdjc.zdjcyun.util;

import com.blankj.utilcode.utils.SPUtils;

/**
 * 用户信息sp工具类
 */
public class UserUtil {

    public static void setIMEI(String imei) {
        SPUtils spUtils = new SPUtils(SPkeyConstantUtil.SSP_KEY);
        spUtils.putString("imei", imei);
    }

    public static String getIMEI() {
        SPUtils spUtils = new SPUtils(SPkeyConstantUtil.SSP_KEY);
        return spUtils.getString("imei");
    }

    public static void setUserId(String userId) {
        SPUtils spUtils = new SPUtils(SPkeyConstantUtil.SSP_KEY);
        spUtils.putString("userId", userId);
    }

    public static String getUserId() {
        SPUtils spUtils = new SPUtils(SPkeyConstantUtil.SSP_KEY);
        return spUtils.getString("userId");
    }

    public static void setUserName(String userName) {
        SPUtils spUtils = new SPUtils(SPkeyConstantUtil.SSP_KEY);
        spUtils.putString("userName", userName);
    }

    public static String getUserName() {
        SPUtils spUtils = new SPUtils(SPkeyConstantUtil.SSP_KEY);
        return spUtils.getString("userName");
    }

    public static void setAccount(String account) {
        SPUtils spUtils = new SPUtils(SPkeyConstantUtil.SSP_KEY);
        spUtils.putString("account", account);
    }

    public static String getAccount() {
        SPUtils spUtils = new SPUtils(SPkeyConstantUtil.SSP_KEY);
        return spUtils.getString("account");
    }

    public static void setIsLogin(boolean isLogin) {
        SPUtils spUtils = new SPUtils(SPkeyConstantUtil.SSP_KEY);
        spUtils.putBoolean("isLogin", isLogin);
    }

    public static boolean getIsLogin() {
        SPUtils spUtils = new SPUtils(SPkeyConstantUtil.SSP_KEY);
        return spUtils.getBoolean("isLogin", false);
    }

    public static void setIsFirst(boolean existData) {
        SPUtils spUtils = new SPUtils(SPkeyConstantUtil.SSP_KEY);
        spUtils.putBoolean("isFirst", existData);
    }

    public static boolean getIsFirst() {
        SPUtils spUtils = new SPUtils(SPkeyConstantUtil.SSP_KEY);
        return spUtils.getBoolean("isFirst", true);
    }

    public static String getRepeatContent(String repeatKey) {
        SPUtils spUtils = new SPUtils(SPkeyConstantUtil.SSP_KEY);
        return spUtils.getString(repeatKey, "");
    }

    public static void putRepeatContent(String repeatKey, String content) {
        SPUtils spUtils = new SPUtils(SPkeyConstantUtil.SSP_KEY);
        spUtils.putString(repeatKey, content);
    }

}
