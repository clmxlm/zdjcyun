package com.zdjc.zdjcyun.network;


import com.zdjc.zdjcyun.mvp.entity.AlarmCountEntity;
import com.zdjc.zdjcyun.mvp.entity.AlarmDetailEntity;
import com.zdjc.zdjcyun.mvp.entity.AlarmEntity;
import com.zdjc.zdjcyun.mvp.entity.AlarmNewsEntity;
import com.zdjc.zdjcyun.mvp.entity.AllProjectListEntity;
import com.zdjc.zdjcyun.mvp.entity.BasicInformationEntity;
import com.zdjc.zdjcyun.mvp.entity.ComparisonDataEntity;
import com.zdjc.zdjcyun.mvp.entity.ComparisonGPSDataEntity;
import com.zdjc.zdjcyun.mvp.entity.CurveDetailEntity;
import com.zdjc.zdjcyun.mvp.entity.DataMonitoringImagesEntity;
import com.zdjc.zdjcyun.mvp.entity.DeepDataEntity;
import com.zdjc.zdjcyun.mvp.entity.DeepDispalcementEntity;
import com.zdjc.zdjcyun.mvp.entity.DeviceEntity;
import com.zdjc.zdjcyun.mvp.entity.DocumentEntity;
import com.zdjc.zdjcyun.mvp.entity.GPSNormEntity;
import com.zdjc.zdjcyun.mvp.entity.HazardsEntity;
import com.zdjc.zdjcyun.mvp.entity.ImageEntity;
import com.zdjc.zdjcyun.mvp.entity.ImageListEntity;
import com.zdjc.zdjcyun.mvp.entity.MapTypeEntity;
import com.zdjc.zdjcyun.mvp.entity.MeasuringPointEntity;
import com.zdjc.zdjcyun.mvp.entity.MemberMsgEntity;
import com.zdjc.zdjcyun.mvp.entity.MonitorPointName;
import com.zdjc.zdjcyun.mvp.entity.MonitorTypeNameEntity;
import com.zdjc.zdjcyun.mvp.entity.MonitorUnitEntity;
import com.zdjc.zdjcyun.mvp.entity.MonitorViewEntity;
import com.zdjc.zdjcyun.mvp.entity.NormDeepEntity;
import com.zdjc.zdjcyun.mvp.entity.PageReportEntity;
import com.zdjc.zdjcyun.mvp.entity.PersonMessageEntity;
import com.zdjc.zdjcyun.mvp.entity.PictureEntity;
import com.zdjc.zdjcyun.mvp.entity.ProjecTypeEntity;
import com.zdjc.zdjcyun.mvp.entity.ProjectContactEntity;
import com.zdjc.zdjcyun.mvp.entity.ProjectDetailEntity;
import com.zdjc.zdjcyun.mvp.entity.ProjectListEntity;
import com.zdjc.zdjcyun.mvp.entity.ProjectManageEntity;
import com.zdjc.zdjcyun.mvp.entity.ProjectTotalStatusEntity;
import com.zdjc.zdjcyun.mvp.entity.SensorDataEntity;
import com.zdjc.zdjcyun.mvp.entity.SensorEntity;
import com.zdjc.zdjcyun.mvp.entity.TerminalAndSensorEntity;
import com.zdjc.zdjcyun.mvp.entity.TypeProjectEntity;
import com.zdjc.zdjcyun.mvp.entity.UpdatePasswordEntity;
import com.zdjc.zdjcyun.mvp.entity.UserBean;
import com.zdjc.zdjcyun.mvp.entity.UserEntity;
import com.zdjc.zdjcyun.mvp.entity.VersionEntity;
import com.zdjc.zdjcyun.mvp.entity.WarningEntity;
import com.zdjc.zdjcyun.mvp.entity.WillProjectedEntity;

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

    @GET("project/queryProjectType")
    Observable<ProjecTypeEntity> beginViewMsg(@Header("Cache-Control") String cacheControl,
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

    @GET("androids/projectContact")
    Observable<ProjectContactEntity> projectContact(@Header("Cache-Control") String cacheControl,
                                                    @Header("Authorization") String token,
                                                    @QueryMap(encoded = true) Map<String, String> params);

    @GET("androids/detections")
    Observable<MeasuringPointEntity> measuringPoint(@Header("Cache-Control") String cacheControl,
                                                    @Header("Authorization") String token,
                                                    @QueryMap(encoded = true) Map<String, String> params);

    @GET("androids/onsiteImg")
    Observable<ImageEntity> projectOnSiteImage(@Header("Cache-Control") String cacheControl,
                                           @Header("Authorization") String token,
                                           @QueryMap(encoded = true) Map<String, String> params);

    @GET("alarm/queryAlarmErrorCounts")
    Observable<AlarmEntity> homeAlarmCounts(@Header("Cache-Control") String cacheControl,
                                            @Header("Authorization") String token,
                                            @QueryMap(encoded = true) Map<String, String> params);

    @GET("project/queryProjectStatusCount")
    Observable<ProjectTotalStatusEntity> queryProjectStatusCount(@Header("Cache-Control") String cacheControl,
                                                                 @Header("Authorization") String token,
                                                                 @QueryMap(encoded = true) Map<String, String> params);

    @GET("alarm/queryAlarmProject")
    Observable<AlarmNewsEntity> queryAlarmProject(@Header("Cache-Control") String cacheControl,
                                                  @Header("Authorization") String token,
                                                  @QueryMap(encoded = true) Map<String, String> params);

    @GET("project/queryWillProject")
    Observable<WillProjectedEntity> queryWillProject(@Header("Cache-Control") String cacheControl,
                                                     @Header("Authorization") String token,
                                                     @QueryMap(encoded = true) Map<String, String> params);

    @GET("project/queryMonitorView")
    Observable<MonitorViewEntity> queryMonitorView(@Header("Cache-Control") String cacheControl,
                                                   @Header("Authorization") String token,
                                                   @QueryMap(encoded = true) Map<String, String> params);

    @GET("project/queryProjects")
    Observable<ProjectManageEntity> queryProjects(@Header("Cache-Control") String cacheControl,
                                                  @Header("Authorization") String token,
                                                  @QueryMap(encoded = true) Map<String, String> params);

    @POST("user/updatePassword")
    Observable<UpdatePasswordEntity> updatePassword(@Header("Cache-Control") String cacheControl,
                                                    @Header("Authorization") String token,
                                                    @QueryMap(encoded = true) Map<String, String> params);

    @GET("project/querySector")
    Observable<BasicInformationEntity> querySector(@Header("Cache-Control") String cacheControl,
                                                     @Header("Authorization") String token,
                                                     @QueryMap(encoded = true) Map<String, String> params);

    @GET("sector/querySectorMemberAndroid")
    Observable<MemberMsgEntity> querySectorMember(@Header("Cache-Control") String cacheControl,
                                            @Header("Authorization") String token,
                                            @QueryMap(encoded = true) Map<String, String> params);

    @GET("device/queryDeviceInfo")
    Observable<DeviceEntity> queryDeviceInfo(@Header("Cache-Control") String cacheControl,
                                               @Header("Authorization") String token,
                                               @QueryMap(encoded = true) Map<String, String> params);

    @GET("device/querySensorInfos")
    Observable<SensorEntity> querySensorInfos(@Header("Cache-Control") String cacheControl,
                                              @Header("Authorization") String token,
                                              @QueryMap(encoded = true) Map<String, String> params);

    @GET("sector/queryImageNamesAndorid")
    Observable<ImageListEntity> queryImageNames(@Header("Cache-Control") String cacheControl,
                                                 @Header("Authorization") String token,
                                                 @QueryMap(encoded = true) Map<String, String> params);

    @GET("sector/queryImageType")
    Observable<PictureEntity> queryImageType(@Header("Cache-Control") String cacheControl,
                                              @Header("Authorization") String token,
                                              @QueryMap(encoded = true) Map<String, String> params);

    @GET("alarm/queryAlarmCount")
    Observable<AlarmCountEntity> queryAlarmCount(@Header("Cache-Control") String cacheControl,
                                                 @Header("Authorization") String token,
                                                 @QueryMap(encoded = true) Map<String, String> params);

    @GET("alarm/queryAlarmInfo")
    Observable<AlarmDetailEntity> queryAlarmInfo(@Header("Cache-Control") String cacheControl,
                                                  @Header("Authorization") String token,
                                                  @QueryMap(encoded = true) Map<String, String> params);

    @POST("alarm/querySearchAlarmInfo")
    Observable<AlarmDetailEntity> querySearchAlarmInfo(@Header("Cache-Control") String cacheControl,
                                                 @Header("Authorization") String token,
                                                 @QueryMap(encoded = true) Map<String, String> params);

    @GET("alarm/confirmAlarmInfo")
    Observable<UpdatePasswordEntity> confirmAlarmInfo(@Header("Cache-Control") String cacheControl,
                                                 @Header("Authorization") String token,
                                                 @QueryMap(encoded = true) Map<String, String> params);


    @GET("sector/queryImagesMonitorPoint")
    Observable<DataMonitoringImagesEntity> queryImagesMonitorPoint(@Header("Cache-Control") String cacheControl,
                                                                   @Header("Authorization") String token,
                                                                   @QueryMap(encoded = true) Map<String, String> params);

    @GET("common/queryMonitorTypeName")
    Observable<MonitorTypeNameEntity> queryMonitorTypeName(@Header("Cache-Control") String cacheControl,
                                                           @Header("Authorization") String token,
                                                           @QueryMap(encoded = true) Map<String, String> params);

    @GET("point/queryMonitorPointName")
    Observable<MonitorPointName> queryMonitorPointName(@Header("Cache-Control") String cacheControl,
                                                       @Header("Authorization") String token,
                                                       @QueryMap(encoded = true) Map<String, String> params);

    @GET("sector/queryComparisonData")
    Observable<ComparisonDataEntity> queryComparisonData(@Header("Cache-Control") String cacheControl,
                                                         @Header("Authorization") String token,
                                                         @QueryMap(encoded = true) Map<String, String> params);

    @GET("sector/queryComparisonData")
    Observable<ComparisonGPSDataEntity> queryComparisonNormTwoData(@Header("Cache-Control") String cacheControl,
                                                                   @Header("Authorization") String token,
                                                                   @QueryMap(encoded = true) Map<String, String> params);

    @GET("sector/queryComparisonData")
    Observable<ComparisonDataEntity> queryComparisonNormThreeData(@Header("Cache-Control") String cacheControl,
                                                         @Header("Authorization") String token,
                                                         @QueryMap(encoded = true) Map<String, String> params);

    @GET("sector/queryComparisonData")
    Observable<ComparisonDataEntity> queryComparisonNormFourData(@Header("Cache-Control") String cacheControl,
                                                         @Header("Authorization") String token,
                                                         @QueryMap(encoded = true) Map<String, String> params);

    @GET("sector/querySensorData")
    Observable<SensorDataEntity> querySensorData(@Header("Cache-Control") String cacheControl,
                                                 @Header("Authorization") String token,
                                                 @QueryMap(encoded = true) Map<String, String> params);

    @GET("sector/querySensorData")
    Observable<GPSNormEntity> queryNormTwoSensorData(@Header("Cache-Control") String cacheControl,
                                                     @Header("Authorization") String token,
                                                     @QueryMap(encoded = true) Map<String, String> params);

    @GET("sector/querySensorData")
    Observable<DeepDataEntity> queryNormThreeSensorData(@Header("Cache-Control") String cacheControl,
                                                        @Header("Authorization") String token,
                                                        @QueryMap(encoded = true) Map<String, String> params);

    @GET("sector/querySensorData")
    Observable<SensorDataEntity> queryNormFourSensorData(@Header("Cache-Control") String cacheControl,
                                                 @Header("Authorization") String token,
                                                 @QueryMap(encoded = true) Map<String, String> params);

    @GET("sector/queryTerminalAndSensor")
    Observable<TerminalAndSensorEntity> queryTerminalAndSensor(@Header("Cache-Control") String cacheControl,
                                                               @Header("Authorization") String token,
                                                               @QueryMap(encoded = true) Map<String, String> params);

    @POST("hazards/getHazards")
    Observable<HazardsEntity> getHazards(@Header("Cache-Control") String cacheControl,
                                                     @Header("Authorization") String token,
                                                     @QueryMap(encoded = true) Map<String, String> params);

    @POST("hazards/addHazards")
    Observable<UpdatePasswordEntity> addHazards(@Header("Cache-Control") String cacheControl,
                                         @Header("Authorization") String token,
                                         @QueryMap(encoded = true) Map<String, String> params);

    @GET("sector/queryMonitorUnit")
    Observable<MonitorUnitEntity> queryMonitorUnit(@Header("Cache-Control") String cacheControl,
                                                   @Header("Authorization") String token,
                                                   @QueryMap(encoded = true) Map<String, String> params);

    @GET("project/queryVersion")
    Observable<VersionEntity> queryVersion(@Header("Cache-Control") String cacheControl,
                                           @Header("Authorization") String token,
                                           @QueryMap(encoded = true) Map<String, String> params);

    @GET("common/queryMapType")
    Observable<MapTypeEntity> queryMapType(@Header("Cache-Control") String cacheControl,
                                           @Header("Authorization") String token,
                                           @QueryMap(encoded = true) Map<String, String> params);

    @GET("data/queryDeepData")
    Observable<NormDeepEntity> queryDeepData(@Header("Cache-Control") String cacheControl,
                                             @Header("Authorization") String token,
                                             @QueryMap(encoded = true) Map<String, String> params);

    @POST("document/getDocuments")
    Observable<DocumentEntity> getDocuments(@Header("Cache-Control") String cacheControl,
                                             @Header("Authorization") String token,
                                             @QueryMap(encoded = true) Map<String, String> params);

}
