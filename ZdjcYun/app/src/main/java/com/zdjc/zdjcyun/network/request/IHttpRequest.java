package com.zdjc.zdjcyun.network.request;


import com.zdjc.zdjcyun.mvp.entity.AlarmCountEntity;
import com.zdjc.zdjcyun.mvp.entity.AlarmDetailEntity;
import com.zdjc.zdjcyun.mvp.entity.AlarmEntity;
import com.zdjc.zdjcyun.mvp.entity.AlarmNewsEntity;
import com.zdjc.zdjcyun.mvp.entity.AllProjectListEntity;
import com.zdjc.zdjcyun.mvp.entity.BasicInformationEntity;
import com.zdjc.zdjcyun.mvp.entity.ComparisonDataEntity;
import com.zdjc.zdjcyun.mvp.entity.CurveDetailEntity;
import com.zdjc.zdjcyun.mvp.entity.DataMonitoringImagesEntity;
import com.zdjc.zdjcyun.mvp.entity.DeepDispalcementEntity;
import com.zdjc.zdjcyun.mvp.entity.DeviceEntity;
import com.zdjc.zdjcyun.mvp.entity.HazardsEntity;
import com.zdjc.zdjcyun.mvp.entity.ImageEntity;
import com.zdjc.zdjcyun.mvp.entity.ImageListEntity;
import com.zdjc.zdjcyun.mvp.entity.MeasuringPointEntity;
import com.zdjc.zdjcyun.mvp.entity.MemberMsgEntity;
import com.zdjc.zdjcyun.mvp.entity.MonitorPointName;
import com.zdjc.zdjcyun.mvp.entity.MonitorTypeNameEntity;
import com.zdjc.zdjcyun.mvp.entity.MonitorUnitEntity;
import com.zdjc.zdjcyun.mvp.entity.MonitorViewEntity;
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

/**
 * Created by ali on 2017/2/16.
 */

public interface IHttpRequest {

    /**
     * 用户登录
     *
     * @return
     */
    Observable<UserEntity> loginUser(Map<String, String> params);

    /**
     * 用户注销
     *
     * @return
     */
    Observable<UserEntity> loginOut(Map<String, String> params);

    /**
     * 项目列表
     *
     * @return
     */
    Observable<AllProjectListEntity> projectList(Map<String, String> params);

    /**
     * 项目详情
     *
     * @return
     */
    Observable<ProjectDetailEntity> projectDetail(Map<String, String> params);

    /**
     * 项目曲线详情
     *
     * @return
     */
    Observable<CurveDetailEntity> projectCurveDetail(Map<String, String> params);

    /**
     * 项目深部位移曲线详情
     *
     * @return
     */
    Observable<DeepDispalcementEntity> projectDeepDispalcementDetail(Map<String, String> params);

    /**
     * 项目告警详情
     *
     * @return
     */
    Observable<WarningEntity> warningDetail(Map<String, String> params);
    /**
     * 项目告警修改
     *
     * @return
     */
    Observable<UserBean> warningChange(Map<String, String> params);

    /**
     * 用户信息
     *
     * @return
     */
    Observable<PersonMessageEntity> personerMsg(Map<String, String> params);

    /**
     * 拉取项目报告
     *
     * @return
     */
    Observable<PageReportEntity> reportMsg(Map<String, String> params);

    /**
     * 拉取2.0首页数据
     *
     * @return
     */
    Observable<ProjecTypeEntity> beginViewMsg(Map<String, String> params);

    /**
     * 拉取2.0项目集合数据
     *
     * @return
     */
    Observable<ProjectListEntity> typeProjectList(Map<String, String> params);

    /**
     * 拉取2.0项目搜索集合数据
     *
     * @return
     */
    Observable<ProjectListEntity> searchProjectList(Map<String, String> params);

    /**
     * 拉取2.0登录之后的项目类型
     *
     * @return
     */
    Observable<TypeProjectEntity> projectTypeMsg(Map<String, String> params);

    /**
     * 拉取项目信息和人信息
     *
     * @return
     */
    Observable<ProjectContactEntity> projectCotacteMsg(Map<String, String> params);

    /**
     * 拉取项目测点详细信息
     *
     * @return
     */
    Observable<MeasuringPointEntity> measuringPointMsg(Map<String, String> params);

    /**
     * 拉取项目现场图片信息
     *
     * @return
     */
    Observable<ImageEntity> projectOnSiteImage(Map<String, String> params);

    /**
     * 拉取首页告警统计
     *
     * @return
     */
    Observable<AlarmEntity> homeAlarmCounts(Map<String, String> params);

    /**
     * 拉取项目统计
     *
     * @return
     */
    Observable<ProjectTotalStatusEntity> queryProjectStatusCount(Map<String, String> params);

    /**
     * 拉取项目告警新闻
     *
     * @return
     */
    Observable<AlarmNewsEntity> queryAlarmProject(Map<String, String> params);

    /**
     * 拉取即将完成项目列表
     *
     * @return
     */
    Observable<WillProjectedEntity> queryWillProject(Map<String, String> params);

    /**
     * 拉取对应项目类型下的项目列表
     *
     * @return
     */
    Observable<MonitorViewEntity> queryMonitorView(Map<String, String> params);

    /**
     * 拉取对应项目类型下的区间列表
     *
     * @return
     */
    Observable<ProjectManageEntity> queryProjects(Map<String, String> params);

    /**
     * 修改密码
     *
     * @return
     */
    Observable<UpdatePasswordEntity> updatePassword(Map<String, String> params);

    /**
     * 拉取对应项目类型下的项目基本信息
     *
     * @return
     */
    Observable<BasicInformationEntity> querySector(Map<String, String> params);

    /**
     * 拉取对应项目类型下的项目人员信息
     *
     * @return
     */
    Observable<MemberMsgEntity> querySectorMember(Map<String, String> params);

    /**
     * 拉取对应项目类型下的项目设备信息（终端）
     *
     * @return
     */
    Observable<DeviceEntity> queryDeviceInfo(Map<String, String> params);

    /**
     * 拉取对应项目类型下的项目设备信息（传感器）
     *
     * @return
     */
    Observable<SensorEntity> querySensorInfos(Map<String, String> params);

    /**
     * 拉取对应项目类型下的项目图片和图纸
     *
     * @return
     */
    Observable<ImageListEntity> queryImageNames(Map<String, String> params);

    /**
     * 拉取对应项目类型下的项目图片和图纸(可以放大缩小的原图)
     *
     * @return
     */
    Observable<PictureEntity> queryImageType(Map<String, String> params);

    /**
     * 拉取所有项目告警粗略统计
     *
     * @return
     */
    Observable<AlarmCountEntity> queryAlarmCount(Map<String, String> params);

    /**
     * 拉取所有项目告警详细统计
     *
     * @return
     */
    Observable<AlarmDetailEntity> queryAlarmInfo(Map<String, String> params);

    /**
     * 拉取所有搜索条件下项目告警详细统计
     *
     * @return
     */
    Observable<AlarmDetailEntity> querySearchAlarmInfo(Map<String, String> params);

    /**
     * 确认告警信息
     *
     * @return
     */
    Observable<UpdatePasswordEntity> confirmAlarmInfo(Map<String, String> params);

    /**
     * 查询布点图，图片信息及图下测点信息
     *
     * @return
     */
    Observable<DataMonitoringImagesEntity> queryImagesMonitorPoint(Map<String, String> params);

    /**
     * 对比数据下的指标集合
     *
     * @return
     */
    Observable<MonitorTypeNameEntity> queryMonitorTypeName(Map<String, String> params);

    /**
     * 对比数据下的指标集合下的测点集合
     *
     * @return
     */
    Observable<MonitorPointName> queryMonitorPointName(Map<String, String> params);

    /**
     * 对比数据下的指标集合下的测点集合选择之后拉取数据
     *
     * @return
     */
    Observable<ComparisonDataEntity> queryComparisonData(Map<String, String> params);

    /**
     * 点击图片上的圆点请求指标下单个测点的曲线信息
     *
     * @return
     */
    Observable<SensorDataEntity> querySensorData(Map<String, String> params);

    /**
     * 点击图片上的圆点请求指标下单个测点的文字信息
     *
     * @return
     */
    Observable<TerminalAndSensorEntity> queryTerminalAndSensor(Map<String, String> params);

    /**
     * 拉取危险源
     *
     * @return
     */
    Observable<HazardsEntity> getHazards(Map<String, String> params);

    /**
     * 添加危险源
     *
     * @return
     */
    Observable<UpdatePasswordEntity> addHazards(Map<String, String> params);

    /**
     * 拉取地铁模块所有指标对应的单位数据
     *
     * @return
     */
    Observable<MonitorUnitEntity> queryMonitorUnit(Map<String, String> params);

    /**
     * 拉取版本信息
     *
     * @return
     */
    Observable<VersionEntity> queryVersion(Map<String, String> params);


}
