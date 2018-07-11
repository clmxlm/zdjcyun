package com.zdjc.zdjcyun.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Created by lenovo on 2017/3/24.
 */

public class GsonUtils {
    private static Gson mInstance;

    public static Gson getInstance()
    {
        if (mInstance == null)
        {
            synchronized (GsonUtils.class)
            {
                if (mInstance == null)
                {
                    mInstance= new GsonBuilder().serializeNulls().create();
                }
            }
        }
        return mInstance;
    }
}
