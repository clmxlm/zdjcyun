package com.zdjc.zdjcyun.network.request;

import android.support.annotation.NonNull;

import com.blankj.utilcode.utils.NetworkUtils;
import com.zdjc.zdjcyun.app.BaseApplication;
import com.zdjc.zdjcyun.mvp.entity.AllProjectListEntity;
import com.zdjc.zdjcyun.mvp.entity.CurveDetailEntity;
import com.zdjc.zdjcyun.mvp.entity.PersonMessageEntity;
import com.zdjc.zdjcyun.mvp.entity.ProjectDetailEntity;
import com.zdjc.zdjcyun.mvp.entity.UserBean;
import com.zdjc.zdjcyun.mvp.entity.UserEntity;
import com.zdjc.zdjcyun.mvp.entity.WarningEntity;
import com.zdjc.zdjcyun.network.RetrofitManager;
import com.zdjc.zdjcyun.util.ConstantUtil;
import com.zdjc.zdjcyun.util.PreferenceUtils;

import java.util.Map;

import io.reactivex.Observable;

import static com.zdjc.zdjcyun.util.ConstantUtil.sCACHE_CONTROL_AGE;


/**
 * Created by ali on 2017/2/16.
 */

public class HttpRequestImpl implements IHttpRequest {

    public static HttpRequestImpl httpRequest;

    public static HttpRequestImpl getInstance() {
        if (httpRequest == null) {
            httpRequest = new HttpRequestImpl();
            return httpRequest;
        }
        return httpRequest;
    }

    /**
     * 根据网络状况获取缓存的策略
     */
    @NonNull
    private String getCacheControl() {
        return NetworkUtils.isConnected() ? sCACHE_CONTROL_AGE : ConstantUtil.sCACHE_CONTROL_CACHE;
    }

    /**
     * 获得Token
     */
    @NonNull
    private String getToken() {
        return PreferenceUtils.getString(BaseApplication.getContext(),"token");

    }

    @Override
    public Observable<UserEntity> loginUser(Map<String, String> params) {
        return RetrofitManager.getInstance().getAppService().loginUser(getCacheControl(), params);
    }

    @Override
    public Observable<UserEntity> loginOut(Map<String, String> params) {
        return RetrofitManager.getInstance().getAppService().loginOut(getCacheControl(),"Bearer"+" "+getToken(), params);
    }

    @Override
    public Observable<AllProjectListEntity> projectList(Map<String, String> params) {
        return RetrofitManager.getInstance().getAppService().projectList(getCacheControl(),"Bearer"+" "+getToken(), params);
    }

    @Override
    public Observable<ProjectDetailEntity> projectDetail(Map<String, String> params) {
        return RetrofitManager.getInstance().getAppService().projectDetail(getCacheControl(),"Bearer"+" "+getToken(), params);
    }

    @Override
    public Observable<CurveDetailEntity> projectCurveDetail(Map<String, String> params) {
        return RetrofitManager.getInstance().getAppService().projectCurveDetail(getCacheControl(),"Bearer"+" "+getToken(), params);
    }

    @Override
    public Observable<WarningEntity> warningDetail(Map<String, String> params) {
        return RetrofitManager.getInstance().getAppService().warningDetail(getCacheControl(),"Bearer"+" "+getToken(), params);
    }

    @Override
    public Observable<UserBean> warningChange(Map<String, String> params) {
        return RetrofitManager.getInstance().getAppService().warningChange(getCacheControl(),"Bearer"+" "+getToken(), params);
    }

    @Override
    public Observable<PersonMessageEntity> personerMsg(Map<String, String> params) {
        return RetrofitManager.getInstance().getAppService().personerMsg(getCacheControl(),"Bearer"+" "+getToken(), params);
    }
}
