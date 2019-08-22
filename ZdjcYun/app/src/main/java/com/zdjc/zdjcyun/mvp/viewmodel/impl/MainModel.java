package com.zdjc.zdjcyun.mvp.viewmodel.impl;


import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.blankj.utilcode.utils.LogUtils;
import com.blankj.utilcode.utils.ToastUtils;
import com.vector.update_app.UpdateAppBean;
import com.vector.update_app.UpdateAppManager;
import com.vector.update_app.UpdateCallback;
import com.vector.update_app.service.DownloadService;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.Permission;
import com.yanzhenjie.permission.PermissionListener;
import com.zdjc.zdjcyun.R;
import com.zdjc.zdjcyun.app.BaseApplication;
import com.zdjc.zdjcyun.app.Constant;
import com.zdjc.zdjcyun.base.BaseModel;
import com.zdjc.zdjcyun.databinding.ActivityMainBinding;
import com.zdjc.zdjcyun.event.ConfirmPopWindow;
import com.zdjc.zdjcyun.mvp.entity.AlarmEntity;
import com.zdjc.zdjcyun.mvp.entity.PersonMessageEntity;
import com.zdjc.zdjcyun.mvp.entity.ProjecTypeEntity;
import com.zdjc.zdjcyun.mvp.entity.VersionEntity;
import com.zdjc.zdjcyun.mvp.presenter.impl.MainPresenterImpl;
import com.zdjc.zdjcyun.mvp.ui.activities.ChangePasswordActivity;
import com.zdjc.zdjcyun.mvp.ui.activities.LoginActivity;
import com.zdjc.zdjcyun.mvp.ui.activities.MainActivity;
import com.zdjc.zdjcyun.mvp.ui.activities.ProjectListActivity;
import com.zdjc.zdjcyun.mvp.ui.activities.WarningMessageActivity;
import com.zdjc.zdjcyun.mvp.ui.adapter.ProjectTypeRecycViewAdapter;
import com.zdjc.zdjcyun.mvp.viewmodel.IMainModel;
import com.zdjc.zdjcyun.util.ImageLoaderUtils;
import com.zdjc.zdjcyun.util.PreferenceUtils;
import com.zdjc.zdjcyun.util.UpdateAppHttpUtil;
import com.zdjc.zdjcyun.widget.HProgressDialogUtils;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.jpush.android.api.JPushInterface;


public class MainModel extends BaseModel<ActivityMainBinding,MainPresenterImpl> implements IMainModel{


    private List<ProjecTypeEntity.DataBean>  homeViewData;
    private ProjectTypeRecycViewAdapter projectTypeRecycViewAdapter;
    private DrawerLayout drawerLayout;
    private String token;
    private ConfirmPopWindow confirmPopWindow;
    private boolean isShowDownloadProgress = true;

    @Override
    public void onCreate() {
        confirmPopWindow = new ConfirmPopWindow((MainActivity)UI);
        // 初始化布局 View视图
        initViews();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    private void initViews() {

        token = PreferenceUtils.getString(getContext(),"token");
        Map<String,String> map = new HashMap<>(0);
        map.put("token",token);
        mControl.getHomeAlarmCounts(this,map,4);

        getProjectType("0");

        drawerLayout = mBinder.drawer;
        mBinder.include.imgbtnLeft.setVisibility(View.VISIBLE);
        mBinder.include.imgbtnLeft.setOnClickListener(v -> drawerLayout.openDrawer(GravityCompat.START));
        mBinder.include.tvTitle.setOnClickListener(v -> confirmPopWindow.showAtBottom(v));
        projectTypeRecycViewAdapter = new ProjectTypeRecycViewAdapter((MainActivity) UI);

        if ("".equals(token)){
            mBinder.includeLeft.tvToken.setText("请先登录");
            ImageLoaderUtils.imageDisPlayRes(R.mipmap.username,mBinder.includeLeft.ivLogout);
        }else {
            mBinder.includeLeft.tvToken.setText("退出登录");
            ImageLoaderUtils.imageDisPlayRes(R.mipmap.logout,mBinder.includeLeft.ivLogout);
        }
        mBinder.includeLeft.rlLogout.setOnClickListener(v -> {
            if (!"".equals(token)){
                Map<String,String> map1 = new HashMap<>(0);
                map1.put("zdjc","zdjc");
                mControl.loginOut(MainModel.this, map1,3);
            }else {
                ((MainActivity)UI).intent2Activity(LoginActivity.class);
                drawerLayout.closeDrawer(GravityCompat.START);
                ((MainActivity)UI).finish();
            }
        });

        mBinder.includeLeft.rlChangePassword.setOnClickListener(v -> {
            ((MainActivity)UI).intent2Activity(ChangePasswordActivity.class);
            drawerLayout.closeDrawer(GravityCompat.START);

        });

        mBinder.llAlarm.setOnClickListener(v -> {
            ((MainActivity)UI).intent2Activity(WarningMessageActivity.class);
        });

        confirmPopWindow.tv_monitorPoint.setOnClickListener(v -> {
            getProjectType("0");
            mBinder.include.tvTitle.setText("监测中");
            confirmPopWindow.dismiss();
        });
        confirmPopWindow.tv_all.setOnClickListener(v -> {
            getProjectType("1");
            mBinder.include.tvTitle.setText("全部");
            confirmPopWindow.dismiss();
        });


        /**
         * 版本更新
         */
        mBinder.includeLeft.rlNewVersion.setOnClickListener(v -> {
            if (!PreferenceUtils.getBoolean(getContext(),"newVersion")) {
                calendarApply();
                drawerLayout.closeDrawer(GravityCompat.START);
            }else {
                ToastUtils.showShortToast("已经是最新版本了！");
            }
        });

    }

    private void checkVersion() {
        String uu = PreferenceUtils.getString(getContext(),"apkUrl");
        String path = Environment.getExternalStorageDirectory().getAbsolutePath();
        Map<String, String> params = new HashMap<>(0);
        params.put("appType",61+"");
        params.put("version",getVersionName());
        Map<String, String> headers = new HashMap<>(0);
        headers.put("Authorization","Bearer"+" "+PreferenceUtils.getString(BaseApplication.getContext(),"token"));
        new UpdateAppManager
                .Builder()
                //必须设置，当前Activity
                .setActivity((MainActivity) UI)
                //必须设置，实现httpManager接口的对象
                .setHttpManager(new UpdateAppHttpUtil())
                //必须设置，更新地址
                .setUpdateUrl(Constant.HTTP_URL +"project/queryVersion")
                .setHeaders(headers)
                //以下设置，都是可选
                //设置请求方式，默认get
                .setPost(true)
                //添加自定义参数，默认version=1.0.0（app的versionName）；apkKey=唯一表示（在AndroidManifest.xml配置）
                .setParams(params)
                //设置apk下砸路径，默认是在下载到sd卡下/Download/1.0.0/test.apk
                .setTargetPath(path)
                //设置appKey，默认从AndroidManifest.xml获取，如果，使用自定义参数，则此项无效
                .setAppKey("ab55ce55Ac4bcP408cPb8c1Aaeac179c5f6f")
                .build()
                //检测是否有新版本
                .checkNewApp(new UpdateCallback() {
                    /**
                     * 解析json,自定义协议
                     * @param json 服务器返回的json
                     * @return UpdateAppBean
                     */
                    @Override
                    protected UpdateAppBean parseJson(String json) {
                        UpdateAppBean updateAppBean = new UpdateAppBean();
                        if (!PreferenceUtils.getBoolean(getContext(),"newVersion")) {
                            updateAppBean.setUpdate("Yes");
                            updateAppBean.setConstraint(false);
                            updateAppBean
                                    .setNewVersion(PreferenceUtils.getString(getContext(),"version"))
                                    .setUpdateLog(PreferenceUtils.getString(getContext(),"versionDescription"))
                                    .setApkFileUrl(Constant.APK_URL+uu);
                        }else {
                            updateAppBean.setUpdate("No");
                        }
                        return updateAppBean;
                    }

                    /**
                     * 有新版本
                     *
                     * @param updateApp        新版本信息
                     * @param updateAppManager app更新管理器
                     */
                    @Override
                    public void hasNewApp(UpdateAppBean updateApp, UpdateAppManager updateAppManager) {
                        //强制更新，
                        if (updateApp.isConstraint()) {
                            //自定义对话框
                            showDiyDialog(updateApp, updateAppManager);
                        } else {

                        }
                        //自定义对话框
                        showDiyDialog(updateApp, updateAppManager);
                    }

                    /**
                     * 网络请求之前
                     */
                    @Override
                    public void onBefore() {
                        LogUtils.i("before");
                    }

                    /**
                     * 网路请求之后
                     */
                    @Override
                    public void onAfter() {

                    }

                });
    }

    /**
     * 初始化要显示的数据
     */
    private void initData() {

        RecyclerView topRecyclerView = mBinder.topRecyclerView;
        RecyclerView pdfRecyclerView = mBinder.pdfRecyclerView;

        topRecyclerView.setLayoutManager(new GridLayoutManager(getContext(),1));
        projectTypeRecycViewAdapter.setDataList(homeViewData);
        topRecyclerView.setAdapter(projectTypeRecycViewAdapter);


        projectTypeRecycViewAdapter.setOnItemClickListener((view, position) -> {
            if ("".equals(PreferenceUtils.getString(getContext(),"token"))){
                ((MainActivity) UI).intent2Activity(LoginActivity.class);
                ((MainActivity) UI).finish();
            }else {
                if (homeViewData.get(position).getProjectTotalCount()>0){
                    ((MainActivity) UI).intent2Activity(ProjectListActivity.class);
                    PreferenceUtils.putInt(getContext(),"projectType",homeViewData.get(position).getScId());
                    PreferenceUtils.putString(getContext(),"projectName",homeViewData.get(position).getItemName());
                }else {
                    ToastUtils.showShortToast("该类型下暂无监测项目");
                }
            }
        });

    }

    @Override
    public void onBeforeRequest(int tag) {
        UI.showWaitDialog();
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onSuccess(Object bean, int tag) {
        switch (tag) {
            case 1:
                homeViewData = (List<ProjecTypeEntity.DataBean>) bean;
                initData();
                if (PreferenceUtils.getString(getContext(),"uesrName")!=null){
                    Map<String,String> map = new HashMap<>(0);
                    map.put("userName", PreferenceUtils.getString(getContext(),"uesrName"));
                    mControl.getPersonMsg(this,map,2);
                }

                HashMap<String, String> paramsVersion = new HashMap<>(0);
                paramsVersion.put("appType",61+"");
                paramsVersion.put("version",getVersionName());
                mControl.queryVersion(this,paramsVersion,5);
                break;
            case 2:
                PersonMessageEntity.DataBean dataBean = (PersonMessageEntity.DataBean)bean;
                String userName = getContext().getResources().getString(R.string.user_name)+" "+
                        dataBean.getUserName();
                String personertName = getContext().getResources().getString(R.string.personert_name)+" "+
                        dataBean.getRealName();
                String personertPhone = getContext().getResources().getString(R.string.personert_phone)+" "+
                        dataBean.getPhone();
                String personertEmail = getContext().getResources().getString(R.string.personert_email)+" "+
                        dataBean.getEmail();
                String personertCompany = getContext().getResources().getString(R.string.personert_company)+" "+
                        dataBean.getCompany();

                mBinder.includeLeft.tvUser.setText(userName);
                mBinder.includeLeft.tvUsername.setText(personertName);
                mBinder.includeLeft.tvPhone.setText(personertPhone);
                mBinder.includeLeft.tvEmail.setText(personertEmail);
                mBinder.includeLeft.tvCompany.setText("湖南中大检测技术集团有限公司");
                PreferenceUtils.putString(getContext(),"userName",dataBean.getUserName());
                PreferenceUtils.putInt(getContext(),"userId",dataBean.getUserId());
                break;
            case 3:
                PreferenceUtils.putInt(getContext(),"projectPosition",0);
                PreferenceUtils.putInt(getContext(),"onePosition",0);
                PreferenceUtils.putInt(getContext(),"twoPosition",0);
                PreferenceUtils.putString(getContext(),"startTime",null);
                PreferenceUtils.putString(getContext(),"endTime",null);
                PreferenceUtils.putString(getContext(),"token","");
                PreferenceUtils.putString(getContext(),"uesrName",null);
                JPushInterface.stopPush(getContext());
                ((MainActivity)UI).intent2Activity(LoginActivity.class);
                ((MainActivity)UI).finish();
                break;
            case 4:
                AlarmEntity.DataBean alarmBean = (AlarmEntity.DataBean)bean;
                mBinder.tvSensorErrorCount.setText(alarmBean.getSensorErrorCount()+"");
                mBinder.tvTerminalErrorCount.setText(alarmBean.getTerminalErrorCount()+"");
                mBinder.tvProjectAlarm.setText(alarmBean.getProjectAlarmCount()+"");
                mBinder.tvAlarmOne.setText(alarmBean.getLevelOneCount()+"");
                mBinder.tvAlarmTwo.setText(alarmBean.getLevelTwoCount()+"");
                mBinder.tvAlarmThree.setText(alarmBean.getLevelThreeCount()+"");

                break;
            case 5:
                VersionEntity.DataBean dataVersion = (VersionEntity.DataBean)bean;
                PreferenceUtils.putBoolean(getContext(),"newVersion",dataVersion.getNewVersion());
                PreferenceUtils.putBoolean(getContext(),"forcedUpdate",dataVersion.isForcedUpdate());
                PreferenceUtils.putString(getContext(),"version",dataVersion.getVersion());
                PreferenceUtils.putString(getContext(),"apkUrl",dataVersion.getUrl());
                PreferenceUtils.putString(getContext(),"versionDescription",dataVersion.getVersionDescription());

                if (!PreferenceUtils.getBoolean(getContext(),"newVersion")){
                    if (!PreferenceUtils.getBoolean(getContext(),"newVersion")){
                        mBinder.include.imgbtnLeft.setNum(1);
                        mBinder.includeLeft.ivRedPoint.setVisibility(View.VISIBLE);
                        mBinder.includeLeft.tvNewVersion.setText("新版本"+"("+getVersionName()+")");
                    }else {
                        mBinder.include.imgbtnLeft.setNum(-1);
                        mBinder.includeLeft.ivRedPoint.setVisibility(View.GONE);
                        mBinder.includeLeft.tvNewVersion.setText("最新版本("+getVersionName()+")");
                    }
                    if (!PreferenceUtils.getBoolean(getContext(),"newVersion")) {
                        calendarApply();
                    }
                }else {
                    calendarApply();
                }


                break;
                default:
                    break;
        }
    }

    @Override
    public void onError(String errorMsg,int code, int tag) {

    }

    private void getProjectType(String type) {
        Map<String,String> map = new HashMap<>(0);
        map.put("token",token);
        map.put("type",type);
        mControl.getHomeViewMsg(this,map,1);
    }

    @Override
    public void onResume() {

    }

    /**
     * 获取当前程序的版本名
     */
    private String getVersionName(){
        //获取packagemanager的实例
        PackageManager packageManager = getContext().getPackageManager();
        //getPackageName()是你当前类的包名，0代表是获取版本信息
        PackageInfo packInfo = null;
        try {
            packInfo = packageManager.getPackageInfo(getContext().getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        Log.e("TAG","版本号"+packInfo.versionCode);
        Log.e("TAG","版本名"+packInfo.versionName);
        return packInfo.versionName;
    }

    /**
     * 自定义对话框
     *
     * @param updateApp
     * @param updateAppManager
     */
    private void showDiyDialog(final UpdateAppBean updateApp, final UpdateAppManager updateAppManager) {
        String targetSize = updateApp.getTargetSize();
        String updateLog = updateApp.getUpdateLog();
        String msg = "";
        if (!TextUtils.isEmpty(targetSize)) {
            msg = "新版本大小：" + targetSize + "\n\n";
        }
        if (!TextUtils.isEmpty(updateLog)) {
            msg += updateLog;
        }
        String stringTitle = "";
        String stringBtn = "";
        boolean isShow = true;
        if (!PreferenceUtils.getBoolean(getContext(),"newVersion")){
            stringTitle = "是否升级到"+getVersionName()+"?";
            stringBtn = "暂不升级";
            isShow = true;
        }else {
            stringTitle = "请升级到"+getVersionName()+"!";
            stringBtn = "";
            isShow = false;
        }
        new AlertDialog.Builder(getContext())
                .setTitle(String.format(stringTitle, updateApp.getNewVersion()))
                .setMessage(msg)
                .setPositiveButton("升级", (dialog, which) -> {
                    //显示下载进度
                    if (isShowDownloadProgress) {
                        updateAppManager.download(new DownloadService.DownloadCallback() {
                            @Override
                            public void onStart() {
                                HProgressDialogUtils.showHorizontalProgressDialog(((MainActivity)UI), "下载进度", false);
                            }
                            /**
                             * 进度
                             * @param progress  进度 0.00 -1.00 ，总大小
                             * @param totalSize 总大小 单位B
                             */
                            @Override
                            public void onProgress(float progress, long totalSize) {
                                HProgressDialogUtils.setProgress(Math.round(progress * 100));
                            }
                            /**
                             *
                             * @param total 总大小 单位B
                             */
                            @Override
                            public void setMax(long total) {
                            }
                            @Override
                            public boolean onFinish(File file) {
                                HProgressDialogUtils.cancel();
                                return true;
                            }
                            @Override
                            public void onError(String msg1) {
                                Toast.makeText(((MainActivity)UI), msg1, Toast.LENGTH_SHORT).show();
                                HProgressDialogUtils.cancel();
                            }
                            @Override
                            public boolean onInstallAppAndAppOnForeground(File file) {
                                return false;
                            }
                        });
                    } else {
                        //不显示下载进度
                        updateAppManager.download();
                    }
                    dialog.dismiss();
                })
                .setNegativeButton(stringBtn, (dialog, which) -> dialog.dismiss())
                .setCancelable(isShow)
                .create()
                .show();
    }

    private void calendarApply() {
        AndPermission.with(getContext())
                .permission(Permission.STORAGE)
                .callback(permissionListener)
                .rationale((requestCode, rationale) -> {
                    // 这里的对话框可以自定义，只要调用rationale.resume()就可以继续申请。
                    AndPermission.rationaleDialog(getContext(), rationale).show();
                })
                .start();
    }
    /**
     * 回调监听。
     */
    private PermissionListener permissionListener = new PermissionListener() {
        @Override
        public void onSucceed(int requestCode, @NonNull List<String> grantPermissions) {
                checkVersion();
        }
        @Override
        public void onFailed(int requestCode, @NonNull List<String> deniedPermissions) {
            // 用户否勾选了不再提示并且拒绝了权限，那么提示用户到设置中授权。
            if (AndPermission.hasAlwaysDeniedPermission(getContext(), deniedPermissions)) {
                // 第一种：用默认的提示语。
                ToastUtils.showLongToast("版本更新必须读写您的SD卡，请在侧边栏更新版本！");
            }
        }
    };

}
