/**
 * Copyright 2016 JustWayward Team
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.zdjc.zdjcyun.app;


import com.zdjc.zdjcyun.BuildConfig;


/**
 * @author zj.
 * @date 18/5/5.
 */
public class Constant {

    /**
     * 生产环境服务器地址http://123.207.88.210:8180/
     * 测试环境服务器地址http://123.207.88.210:8081/
     * 三版本测试环境服务器地址http://123.207.88.210:8180/
     * 三版本测试环境本地服务器地址http://10.88.89.170:8180/
     * 本地10.88.89.170:8180/徐善斌
     * 本地10.88.89.73:8180/茂平
     * 本地10.88.89.206:8180/胡超
     * 本地10.88.89.193:8180/孔成
     * 本地10.88.89.101:8180/邓泽民
     * 本地10.88.89.212:3000/彭思齐（尾矿库）
     * 新版本地192.168.10.18:8180
     * 新版图片本地服务器地址http://113.247.236.85:8100/
     */
    public static String HTTP_URL = null;

    public static String IMAGE_URL = null;
    public static String APK_URL = null;
    public static String WEB_SOCKET = "ws://123.207.88.210:8180/websocket";


    static {
        if (BuildConfig.DEBUG) {
            HTTP_URL = "http://123.207.88.210:8180/";
        } else {
            HTTP_URL = "http://123.207.88.210:8180/";
        }
        IMAGE_URL = "http://123.207.88.210/";
//        IMAGE_URL = "http://113.247.236.85:8100";

        APK_URL = "http://123.207.88.210";
    }

    public static final String DB_NAME = "weidu.db";

}
