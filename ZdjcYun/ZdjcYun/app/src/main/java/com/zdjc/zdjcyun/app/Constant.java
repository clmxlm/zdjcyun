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
 * @author yuyh.
 * @date 16/8/5.
 */
public class Constant {

    /**
     * 服务器地址http://123.207.88.210:8080/
     * 本地10.88.89.193
     */
    public static String HTTP_URL = null;


    static {
        if (BuildConfig.DEBUG) {
            HTTP_URL = "http://123.207.88.210:8080/";
        } else {
            HTTP_URL = "http://123.207.88.210:8080/";
        }
    }

    public static final String DB_NAME = "weidu.db";



}
