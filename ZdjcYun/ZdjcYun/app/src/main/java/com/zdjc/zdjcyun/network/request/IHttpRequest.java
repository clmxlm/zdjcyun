package com.zdjc.zdjcyun.network.request;


import com.zdjc.zdjcyun.mvp.entity.AllProjectListEntity;
import com.zdjc.zdjcyun.mvp.entity.CurveDetailEntity;
import com.zdjc.zdjcyun.mvp.entity.PersonMessageEntity;
import com.zdjc.zdjcyun.mvp.entity.ProjectDetailEntity;
import com.zdjc.zdjcyun.mvp.entity.UserBean;
import com.zdjc.zdjcyun.mvp.entity.UserEntity;
import com.zdjc.zdjcyun.mvp.entity.WarningEntity;

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

}
