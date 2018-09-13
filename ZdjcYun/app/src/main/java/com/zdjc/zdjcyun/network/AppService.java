package com.zdjc.zdjcyun.network;


import com.zdjc.zdjcyun.mvp.entity.AllProjectListEntity;
import com.zdjc.zdjcyun.mvp.entity.BeginEntity;
import com.zdjc.zdjcyun.mvp.entity.CurveDetailEntity;
import com.zdjc.zdjcyun.mvp.entity.DeepDispalcementEntity;
import com.zdjc.zdjcyun.mvp.entity.PageReportEntity;
import com.zdjc.zdjcyun.mvp.entity.PersonMessageEntity;
import com.zdjc.zdjcyun.mvp.entity.ProjectDetailEntity;
import com.zdjc.zdjcyun.mvp.entity.ProjectListEntity;
import com.zdjc.zdjcyun.mvp.entity.TypeProjectEntity;
import com.zdjc.zdjcyun.mvp.entity.UserBean;
import com.zdjc.zdjcyun.mvp.entity.UserEntity;
import com.zdjc.zdjcyun.mvp.entity.WarningEntity;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;

/**
 * Created by ali on 2017/2/16.
 */

public interface AppService {

    /**
     * 首先这里就是接口列表了，相当于所有请求都在这里
     */
    /**
     * 登录
     *
     * @param cacheControl 缓存
     * @return Observable
     * @Header("Cache-Control") String cacheControl是缓存 />
     */
    @POST("token/login")
    Observable<UserEntity> loginUser(@Header("Cache-Control") String cacheControl,
                                     @QueryMap(encoded = true) Map<String, String> params);
    @DELETE("token/logout")
    Observable<UserEntity> loginOut(@Header("Cache-Control") String cacheControl,
                                                   @Header("Authorization") String token,
                                                   @QueryMap(encoded = true) Map<String, String> params);
    @GET("project/getAllProject")
    Observable<AllProjectListEntity> projectList(@Header("Cache-Control") String cacheControl,
                                                 @Header("Authorization") String token,
                                                 @QueryMap(encoded = true) Map<String, String> params);
    @GET("androidPro/monitorType")
    Observable<ProjectDetailEntity> projectDetail(@Header("Cache-Control") String cacheControl,
                                                  @Header("Authorization") String token,
                                                  @QueryMap(encoded = true) Map<String, String> params);
    @GET("androidPro/querySenData")
    Observable<CurveDetailEntity> projectCurveDetail(@Header("Cache-Control") String cacheControl,
                                                     @Header("Authorization") String token,
                                                     @QueryMap(encoded = true) Map<String, String> params);

    @GET("androids/queryGraDatasPro")
    Observable<DeepDispalcementEntity> projectDeepDispalcementDetail(@Header("Cache-Control") String cacheControl,
                                                          @Header("Authorization") String token,
                                                          @QueryMap(encoded = true) Map<String, String> params);

    @GET("alarm/queryAlarm")
    Observable<WarningEntity> warningDetail(@Header("Cache-Control") String cacheControl,
                                            @Header("Authorization") String token,
                                            @QueryMap(encoded = true) Map<String, String> params);

    @GET("alarm/updateAlarm")
    Observable<UserBean> warningChange(@Header("Cache-Control") String cacheControl,
                                       @Header("Authorization") String token,
                                       @QueryMap(encoded = true) Map<String, String> params);


    @GET("report/getAllFileMassage")
    Observable<PageReportEntity> getPageReport(@Header("Cache-Control") String cacheControl,
                                               @Header("Authorization") String token,
                                               @QueryMap(encoded = true) Map<String, String> params);

    @GET("user/findUser")
    Observable<PersonMessageEntity> personerMsg(@Header("Cache-Control") String cacheControl,
                                                @Header("Authorization") String token,
                                                @QueryMap(encoded = true) Map<String, String> params);

    @GET("androids/home")
    Observable<BeginEntity> beginViewMsg(@Header("Cache-Control") String cacheControl,
                                        @Header("Authorization") String token,
                                        @QueryMap(encoded = true) Map<String, String> params);

    @GET("androids/projectType")
    Observable<TypeProjectEntity> projectTypeMsg(@Header("Cache-Control") String cacheControl,
                                                 @Header("Authorization") String token,
                                                 @QueryMap(encoded = true) Map<String, String> params);


    @GET("androids/projects")
    Observable<ProjectListEntity> typeProjectList(@Header("Cache-Control") String cacheControl,
                                               @Header("Authorization") String token,
                                               @QueryMap(encoded = true) Map<String, String> params);

    @GET("androids/likeProjects")
    Observable<ProjectListEntity> searchProjectList(@Header("Cache-Control") String cacheControl,
                                                  @Header("Authorization") String token,
                                                  @QueryMap(encoded = true) Map<String, String> params);
}
