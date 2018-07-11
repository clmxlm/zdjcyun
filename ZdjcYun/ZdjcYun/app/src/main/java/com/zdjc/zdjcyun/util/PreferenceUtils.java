package com.zdjc.zdjcyun.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;


public class PreferenceUtils {

	private static SharedPreferences sp;
	private static String DEFAULT_SHARED="cheers";
	private static SharedPreferences getPreferences(Context context) {
		if (sp == null) {
			sp = context.getSharedPreferences(DEFAULT_SHARED, Context.MODE_PRIVATE);
		}
		return sp;
	}
	/**
	 * 获得boolean类型的信息,如果没有返回false
	 * 
	 * @param context
	 * @param key
	 * @return
	 */
	public static boolean getBoolean(Context context, String key) {
		return getBoolean(context, key, false);
	}

	/**
	 * 获得boolean类型的信息
	 * 
	 * @param context
	 * @param key
	 * @param defValue：没有时的默认值
	 * @return
	 */
	public static boolean getBoolean(Context context, String key, boolean defValue) {
		SharedPreferences sp = getPreferences(context);
		return sp.getBoolean(key, defValue);
	}

	/**
	 * 设置boolean类型的 配置数据
	 * 
	 * @param context
	 * @param key
	 * @param value
	 */
	public static void putBoolean(Context context, String key, boolean value) {
		SharedPreferences sp = getPreferences(context);
		Editor edit = sp.edit();
		edit.putBoolean(key, value);
		edit.commit();
	}

	/**
	 * 获得string类型的信息,如果没有返回null
	 * 
	 * @param context
	 * @param key
	 * @return
	 */
	public static String getString(Context context, String key) {
		return getString(context, key, null);
	}

	/**
	 * 获得String类型的信息
	 * 
	 * @param context
	 * @param key
	 * @param defValue
	 *            ： 没有时的默认值
	 * @return
	 */
	public static String getString(Context context, String key, String defValue) {
		SharedPreferences sp = getPreferences(context);
		return sp.getString(key, defValue);
	}

	/**
	 * 设置String类型的 配置数据
	 * 
	 * @param context
	 * @param key
	 * @param value
	 */
	public static void putString(Context context, String key, String value) {
		SharedPreferences sp = getPreferences(context);
		Editor edit = sp.edit();
		edit.putString(key, value);
		edit.commit();
	}

	/**
	 * 获得int类型的信息,如果没有返回-1
	 * 
	 * @param context
	 * @param key
	 * @return
	 */
	public static int getInt(Context context, String key) {
		return getInt(context, key, -1);
	}

	/**
	 * 获得int类型的信息
	 * 
	 * @param context
	 * @param key
	 * @param defValue
	 *            ： 没有时的默认值
	 * @return
	 */
	public static int getInt(Context context, String key, int defValue) {
		SharedPreferences sp = getPreferences(context);
		return sp.getInt(key, defValue);
	}

	/**
	 * 设置int类型的 配置数据
	 * 
	 * @param context
	 * @param key
	 * @param value
	 */
	public static void putInt(Context context, String key, int value) {
		SharedPreferences sp = getPreferences(context);
		Editor edit = sp.edit();
		edit.putInt(key, value);
		edit.commit();
	}

	/**
	 * 设置long类型的 配置数据
	 *
	 * @param context
	 * @param key
	 * @param value
	 */
	public static void putLong(Context context, String key, long value) {
		SharedPreferences sp = getPreferences(context);
		Editor edit = sp.edit();
		edit.putLong(key, value);
		edit.commit();
	}

	/**
	 * 获得int类型的信息
	 *
	 * @param context
	 * @param key
	 * @param defValue
	 *            ： 没有时的默认值
	 * @return
	 */
	public static long getLong(Context context, String key, long defValue) {
		SharedPreferences sp = getPreferences(context);
		return sp.getLong(key, defValue);
	}

	/**
	 * 清空SharedPreferences
	 * @param context
	 */
	public static void clear(Context context){
		SharedPreferences preferences = getPreferences(context);
		preferences.edit().clear().apply();
	}


//	/**
//	 * 获取版本号
//	 */
//	public static String getVersionName(){
//		return PreferenceUtils.getString(LocationApplication.getInstance(),"versionCode");
//	}
//
//	/**
//	 * 获取版本号
//	 */
//	public static String getDeviceToken(){
//		return PreferenceUtils.getString(LocationApplication.getInstance(),"device_token");
//	}
	/**
	 * userId
	 */
	private static final String USERID = "userId";
	public static void setUserId(int value){
		if(null != sp){
			sp.edit().putInt(USERID, value).commit();
		}
	}

	public static int getuserId(){
		if(null != sp){
			return sp.getInt(USERID,0);
		}
		return -1000;
	}


	/**
	 * keys
	 */
	private static final String KEYS = "keys";
	public static void setKeys(String value){
		if(null != sp){
			sp.edit().putString(KEYS, value).commit();
		}
	}

	public static String getKeys(){
		if(null != sp){
			return sp.getString(KEYS,"");
		}
		return "";
	}


	/**
	 * loginFor
	 */
	private static final String LOGINFOR = "loginFor";
	public static void setLoginFor(String value){
		if(null != sp){
			sp.edit().putString(LOGINFOR, value).commit();
		}
	}

	public static String getLoginFor(){
		if(null != sp){
			return sp.getString(LOGINFOR, "");
		}
		return "";
	}

	/**
	 * isLogin
	 */
	private static final String ISLOGIN = "isLogin";
	public static void setIsLogin(boolean value){
		if(null != sp){
			sp.edit().putBoolean(ISLOGIN, value).commit();
		}
	}

	public static boolean getIsLogin(){
		if(null != sp){
			return sp.getBoolean(ISLOGIN, false);
		}
		return false;
	}

	/**
	 * isFBLogout
	 */
	private static final String ISFBLOGOUT = "isFBLogout";
	public static void setIsFBLogout(boolean value){
		if(null != sp){
			sp.edit().putBoolean(ISFBLOGOUT, value).commit();
		}
	}

	public static boolean getIsFBLogout(){
		if(null != sp){
			return sp.getBoolean(ISFBLOGOUT, true);
		}
		return true;
	}

	/**
	 * isSettingHeadImage
	 */
	private static final String ISSETTINGHEADIMAGE = "isSettingHeadImage";
	public static void setIsSettingHeadImage(boolean value){
		if(null != sp){
			sp.edit().putBoolean(ISSETTINGHEADIMAGE, value).commit();
		}
	}

	public static boolean getIsSettingHeadImage(){
		if(null != sp){
			return sp.getBoolean(ISSETTINGHEADIMAGE, true);
		}
		return true;
	}

}
