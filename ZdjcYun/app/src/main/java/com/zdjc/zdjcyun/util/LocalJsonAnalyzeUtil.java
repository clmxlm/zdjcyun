package com.zdjc.zdjcyun.util;

import android.content.Context;
import android.content.res.AssetManager;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by zhongjie on 2018/6/1.
 * description:获取本地Json文件并 解析
 */
public class LocalJsonAnalyzeUtil {

    public static String getJson(Context context, String fileName) {
        StringBuilder stringBuilder = new StringBuilder();
        //获得assets资源管理器
        AssetManager assetManager = context.getAssets();
        //使用IO流读取json文件内容
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(
                    assetManager.open(fileName), "utf-8"));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }

    public static <T> T JsonToObject(String json, Class<T> type) {
        Gson gson = new Gson();
        return gson.fromJson(json, type);
    }

    public static <T> T JsonToObject(Context context, String fileName, Class<T> type) {
        Gson gson = new Gson();
        return gson.fromJson(getJson(context, fileName), type);
    }

    /**
     * 获取json文件内容
     *
     * @param asset
     * @return
     */
    public static String getArea(AssetManager asset) {
        InputStream is;
        try {
            is = asset.open("bar_chart.json");
            byte[] byte_lh = new byte[is.available()];
            is.read(byte_lh);
            String s = new String(byte_lh, "UTF-8");
            return s;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }



}
