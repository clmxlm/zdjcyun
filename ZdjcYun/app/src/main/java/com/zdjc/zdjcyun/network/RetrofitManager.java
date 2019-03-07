package com.zdjc.zdjcyun.network;

import com.blankj.utilcode.utils.LogUtils;
import com.blankj.utilcode.utils.NetworkUtils;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.zdjc.zdjcyun.BuildConfig;
import com.zdjc.zdjcyun.app.BaseApplication;
import com.zdjc.zdjcyun.util.ConstantUtil;

import java.io.File;
import java.io.IOException;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.blankj.utilcode.utils.LogUtils.i;
import static com.zdjc.zdjcyun.app.Constant.HTTP_URL;

/**
 * Created by ali on 2016/10/16.
 */

public class RetrofitManager {

    private static volatile OkHttpClient sOkHttpClient;

    private final AppService mMallService;
    public static RetrofitManager retrofitManager;

    public RetrofitManager() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(HTTP_URL)
                .client(getOkHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        mMallService = retrofit.create(AppService.class);
    }

    private OkHttpClient getOkHttpClient() {
        if (sOkHttpClient == null) {
            synchronized (RetrofitManager.class) {
                Cache cache = new Cache(new File(BaseApplication.getContext().getCacheDir(), "HttpCache"),
                        1024 * 1024 * 100);
                if (sOkHttpClient == null) {
                    OkHttpClient.Builder builder = new OkHttpClient.Builder().cache(cache)
                            .connectTimeout(20, TimeUnit.SECONDS)
                            .readTimeout(20, TimeUnit.SECONDS)
                            .writeTimeout(20, TimeUnit.SECONDS)
                            .addInterceptor(mRewriteCacheControlInterceptor)
                            .addNetworkInterceptor(mRewriteCacheControlInterceptor)
                            .addInterceptor(mLoggingInterceptor);
                    if (BuildConfig.DEBUG) {
                        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
                        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
                        builder.addInterceptor(interceptor);
                    }
                    sOkHttpClient=builder.build();
                }
            }
        }
        return sOkHttpClient;
    }

    /**
     * 云端响应头拦截器，用来配置缓存策略
     * Dangerous interceptor that rewrites the server's cache-control header.
     */
    private final Interceptor mRewriteCacheControlInterceptor = chain -> {
        /**
         * 这个内部类是拦截器。
         * request可以拦截自己请求的参数
         */
        Request request = chain.request();
        if (!NetworkUtils.isConnected()) {
            request = request.newBuilder()
                    .cacheControl(CacheControl.FORCE_CACHE)
                    .build();

            LogUtils.d("no network");
        }
        Response originalResponse=null;

        if (request.body() != null || request.header("Authorization") != null) {

        }
//        request.header("Authorization", "Bearer " + mOAuthToken)
//                .header("Gdata-version", "2")
//                .build();
        /**
         * 这里是执行了请求，拿到了响应数据了。然后可以拦截响应数据
         */
        originalResponse =  chain.proceed(request);

        if (NetworkUtils.isConnected()) {
            //有网的时候读接口上的@Headers里的配置，你可以在这里进行统一的设置
            String cacheControl = request.cacheControl().toString();
            /**
             * 加入缓存策略，有网路哦就直接返回服务器的最新 数据
             */
            return originalResponse.newBuilder()
                    .header("Cache-Control", cacheControl)
                    .removeHeader("Pragma")
                    .build();
        } else {
            /**
             * 这里是没有网络。返回缓存数据，配置的是2天有效
             */
            return originalResponse.newBuilder()
                    .header("Cache-Control", "public, only-if-cached, max-stale=" + ConstantUtil.sCACHE_STALE_SEC)
                    .removeHeader("Pragma")
                    .build();
        }
    };

    private final Interceptor mLoggingInterceptor = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            long t1 = System.nanoTime();
            i(String.format("Sending request %s on %s%n%s", request.url(), chain.connection(), request.headers()));

            Response response = chain.proceed(request);
            long t2 = System.nanoTime();
            i(String.format(Locale.getDefault(), "Received response for %s in %.1fms%n%s",
                    response.request().url(), (t2 - t1) / 1e6d, response.headers()));
            return response;
        }
    };

    public static RetrofitManager getInstance() {
        if (retrofitManager == null) {
            retrofitManager = new RetrofitManager();
            return retrofitManager;
        }
        return retrofitManager;
    }

    public AppService getAppService() {
        if (mMallService != null) {
            return mMallService;
        }
        return null;
    }
}
