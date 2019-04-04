package com.zdjc.zdjcyun.network.request;

import android.support.annotation.NonNull;

import com.blankj.utilcode.utils.NetworkUtils;
import com.zdjc.zdjcyun.app.BaseApplication;
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
    public Observable<DeepDispalcementEntity> projectDeepDispalcementDetail(Map<String, String> params) {
        return RetrofitManager.getInstance().getAppService().projectDeepDispalcementDetail(getCacheControl(),"Bearer"+" "+getToken(), params);
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

    @Override
    public Observable<PageReportEntity> reportMsg(Map<String, String> params) {
        return RetrofitManager.getInstance().getAppService().getPageReport(getCacheControl(),"Bearer"+" "+getToken(), params);
    }

    @Override
    public Observable<ProjecTypeEntity> beginViewMsg(Map<String, String> params) {
        return RetrofitManager.getInstance().getAppService().beginViewMsg(getCacheControl(),"Bearer"+" "+getToken(), params);
    }

    @Override
    public Observable<ProjectListEntity> typeProjectList(Map<String, String> params) {
        return RetrofitManager.getInstance().getAppService().typeProjectList(getCacheControl(),"Bearer"+" "+getToken(), params);
    }

    @Override
    public Observable<ProjectListEntity> searchProjectList(Map<String, String> params) {
        return RetrofitManager.getInstance().getAppService().searchProjectList(getCacheControl(),"Bearer"+" "+getToken(), params);
    }

    @Override
    public Observable<TypeProjectEntity> projectTypeMsg(Map<String, String> params) {
        return RetrofitManager.getInstance().getAppService().projectTypeMsg(getCacheControl(),"Bearer"+" "+getToken(), params);
    }

    @Override
    public Observable<ProjectContactEntity> projectCotacteMsg(Map<String, String> params) {
        return RetrofitManager.getInstance().getAppService().projectContact(getCacheControl(),"Bearer"+" "+getToken(), params);
    }

    @Override
    public Observable<MeasuringPointEntity> measuringPointMsg(Map<String, String> params) {
        return RetrofitManager.getInstance().getAppService().measuringPoint(getCacheControl(),"Bearer"+" "+getToken(), params);
    }

    @Override
    public Observable<ImageEntity> projectOnSiteImage(Map<String, String> params) {
        return RetrofitManager.getInstance().getAppService().projectOnSiteImage(getCacheControl(),"Bearer"+" "+getToken(), params);
    }

    @Override
    public Observable<AlarmEntity> homeAlarmCounts(Map<String, String> params) {
        return RetrofitManager.getInstance().getAppService().homeAlarmCounts(getCacheControl(),"Bearer"+" "+getToken(), params);
    }

    @Override
    public Observable<ProjectTotalStatusEntity> queryProjectStatusCount(Map<String, String> params) {
        return RetrofitManager.getInstance().getAppService().queryProjectStatusCount(getCacheControl(),"Bearer"+" "+getToken(), params);
    }

    @Override
    public Observable<AlarmNewsEntity> queryAlarmProject(Map<String, String> params) {
        return RetrofitManager.getInstance().getAppService().queryAlarmProject(getCacheControl(),"Bearer"+" "+getToken(), params);
    }

    @Override
    public Observable<WillProjectedEntity> queryWillProject(Map<String, String> params) {
        return RetrofitManager.getInstance().getAppService().queryWillProject(getCacheControl(),"Bearer"+" "+getToken(), params);
    }

    @Override
    public Observable<MonitorViewEntity> queryMonitorView(Map<String, String> params) {
        return RetrofitManager.getInstance().getAppService().queryMonitorView(getCacheControl(),"Bearer"+" "+getToken(), params);
    }

    @Override
    public Observable<ProjectManageEntity> queryProjects(Map<String, String> params) {
        return RetrofitManager.getInstance().getAppService().queryProjects(getCacheControl(),"Bearer"+" "+getToken(), params);
    }

    @Override
    public Observable<UpdatePasswordEntity> updatePassword(Map<String, String> params) {
        return RetrofitManager.getInstance().getAppService().updatePassword(getCacheControl(),"Bearer"+" "+getToken(), params);
    }

    @Override
    public Observable<BasicInformationEntity> querySector(Map<String, String> params) {
        return RetrofitManager.getInstance().getAppService().querySector(getCacheControl(),"Bearer"+" "+getToken(), params);
    }

    @Override
    public Observable<MemberMsgEntity> querySectorMember(Map<String, String> params) {
        return RetrofitManager.getInstance().getAppService().querySectorMember(getCacheControl(),"Bearer"+" "+getToken(), params);
    }

    @Override
    public Observable<DeviceEntity> queryDeviceInfo(Map<String, String> params) {
        return RetrofitManager.getInstance().getAppService().queryDeviceInfo(getCacheControl(),"Bearer"+" "+getToken(), params);
    }

    @Override
    public Observable<SensorEntity> querySensorInfos(Map<String, String> params) {
        return RetrofitManager.getInstance().getAppService().querySensorInfos(getCacheControl(),"Bearer"+" "+getToken(), params);
    }

    @Override
    public Observable<ImageListEntity> queryImageNames(Map<String, String> params) {
        return RetrofitManager.getInstance().getAppService().queryImageNames(getCacheControl(),"Bearer"+" "+getToken(), params);
    }

    @Override
    public Observable<PictureEntity> queryImageType(Map<String, String> params) {
        return RetrofitManager.getInstance().getAppService().queryImageType(getCacheControl(),"Bearer"+" "+getToken(), params);
    }

    @Override
    public Observable<AlarmCountEntity> queryAlarmCount(Map<String, String> params) {
        return RetrofitManager.getInstance().getAppService().queryAlarmCount(getCacheControl(),"Bearer"+" "+getToken(), params);
    }

    @Override
    public Observable<AlarmDetailEntity> queryAlarmInfo(Map<String, String> params) {
        return RetrofitManager.getInstance().getAppService().queryAlarmInfo(getCacheControl(),"Bearer"+" "+getToken(), params);
    }

    @Override
    public Observable<AlarmDetailEntity> querySearchAlarmInfo(Map<String, String> params) {
        return RetrofitManager.getInstance().getAppService().querySearchAlarmInfo(getCacheControl(),"Bearer"+" "+getToken(), params);
    }

    @Override
    public Observable<UpdatePasswordEntity> confirmAlarmInfo(Map<String, String> params) {
        return RetrofitManager.getInstance().getAppService().confirmAlarmInfo(getCacheControl(),"Bearer"+" "+getToken(), params);
    }

    @Override
    public Observable<DataMonitoringImagesEntity> queryImagesMonitorPoint(Map<String, String> params) {
        return RetrofitManager.getInstance().getAppService().queryImagesMonitorPoint(getCacheControl(),"Bearer"+" "+getToken(), params);
    }

    @Override
    public Observable<MonitorTypeNameEntity> queryMonitorTypeName(Map<String, String> params) {
        return RetrofitManager.getInstance().getAppService().queryMonitorTypeName(getCacheControl(),"Bearer"+" "+getToken(), params);
    }

    @Override
    public Observable<MonitorPointName> queryMonitorPointName(Map<String, String> params) {
        return RetrofitManager.getInstance().getAppService().queryMonitorPointName(getCacheControl(),"Bearer"+" "+getToken(), params);
    }

    @Override
    public Observable<ComparisonDataEntity> queryComparisonData(Map<String, String> params) {
        return RetrofitManager.getInstance().getAppService().queryComparisonData(getCacheControl(),"Bearer"+" "+getToken(), params);
    }

    @Override
    public Observable<ComparisonGPSDataEntity> queryComparisonNormTwoData(Map<String, String> params) {
        return RetrofitManager.getInstance().getAppService().queryComparisonNormTwoData(getCacheControl(),"Bearer"+" "+getToken(), params);
    }

    @Override
    public Observable<ComparisonDataEntity> queryComparisonNormThreeData(Map<String, String> params) {
        return RetrofitManager.getInstance().getAppService().queryComparisonData(getCacheControl(),"Bearer"+" "+getToken(), params);
    }

    @Override
    public Observable<ComparisonDataEntity> queryComparisonNormFourData(Map<String, String> params) {
        return RetrofitManager.getInstance().getAppService().queryComparisonData(getCacheControl(),"Bearer"+" "+getToken(), params);
    }

    @Override
    public Observable<SensorDataEntity> querySensorData(Map<String, String> params) {
        return RetrofitManager.getInstance().getAppService().querySensorData(getCacheControl(),"Bearer"+" "+getToken(), params);
    }

    @Override
    public Observable<GPSNormEntity> queryNormTwoSensorData(Map<String, String> params) {
        return RetrofitManager.getInstance().getAppService().queryNormTwoSensorData(getCacheControl(),"Bearer"+" "+getToken(), params);
    }

    @Override
    public Observable<DeepDataEntity> queryNormThreeSensorData(Map<String, String> params) {
        return RetrofitManager.getInstance().getAppService().queryNormThreeSensorData(getCacheControl(),"Bearer"+" "+getToken(), params);
    }

    @Override
    public Observable<SensorDataEntity> queryNormFourSensorData(Map<String, String> params) {
        return RetrofitManager.getInstance().getAppService().queryNormFourSensorData(getCacheControl(),"Bearer"+" "+getToken(), params);
    }

    @Override
    public Observable<TerminalAndSensorEntity> queryTerminalAndSensor(Map<String, String> params) {
        return RetrofitManager.getInstance().getAppService().queryTerminalAndSensor(getCacheControl(),"Bearer"+" "+getToken(), params);
    }

    @Override
    public Observable<HazardsEntity> getHazards(Map<String, String> params) {
        return RetrofitManager.getInstance().getAppService().getHazards(getCacheControl(),"Bearer"+" "+getToken(), params);
    }

    @Override
    public Observable<UpdatePasswordEntity> addHazards(Map<String, String> params) {
        return RetrofitManager.getInstance().getAppService().addHazards(getCacheControl(),"Bearer"+" "+getToken(), params);
    }

    @Override
    public Observable<MonitorUnitEntity> queryMonitorUnit(Map<String, String> params) {
        return RetrofitManager.getInstance().getAppService().queryMonitorUnit(getCacheControl(),"Bearer"+" "+getToken(), params);
    }

    @Override
    public Observable<VersionEntity> queryVersion(Map<String, String> params) {
        return RetrofitManager.getInstance().getAppService().queryVersion(getCacheControl(),"Bearer"+" "+getToken(), params);
    }

    @Override
    public Observable<MapTypeEntity> queryMapType(Map<String, String> params) {
        return RetrofitManager.getInstance().getAppService().queryMapType(getCacheControl(),"Bearer"+" "+getToken(), params);
    }

    @Override
    public Observable<NormDeepEntity> queryDeepData(Map<String, String> params) {
        return RetrofitManager.getInstance().getAppService().queryDeepData(getCacheControl(),"Bearer"+" "+getToken(), params);
    }

    @Override
    public Observable<DocumentEntity> getDocuments(Map<String, String> params) {
        return RetrofitManager.getInstance().getAppService().getDocuments(getCacheControl(),"Bearer"+" "+getToken(), params);
    }
}
