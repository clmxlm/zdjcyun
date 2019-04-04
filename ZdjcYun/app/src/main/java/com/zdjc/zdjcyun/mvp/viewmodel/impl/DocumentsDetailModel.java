package com.zdjc.zdjcyun.mvp.viewmodel.impl;


import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.TimePicker;

import com.blankj.utilcode.utils.ToastUtils;
import com.github.jdsjlzx.interfaces.OnItemClickListener;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.zdjc.zdjcyun.R;
import com.zdjc.zdjcyun.app.Constant;
import com.zdjc.zdjcyun.base.BaseModel;
import com.zdjc.zdjcyun.databinding.FragmentAlarmDetailBinding;
import com.zdjc.zdjcyun.databinding.FragmentDocumentsDetailBinding;
import com.zdjc.zdjcyun.event.DocumentDownloadDialog;
import com.zdjc.zdjcyun.event.PullLoadMoreRecyclerView;
import com.zdjc.zdjcyun.mvp.entity.AlarmDetailEntity;
import com.zdjc.zdjcyun.mvp.entity.DocumentEntity;
import com.zdjc.zdjcyun.mvp.presenter.impl.AlarmDetailPresenterImpl;
import com.zdjc.zdjcyun.mvp.presenter.impl.DocumentsDetailPresenterImpl;
import com.zdjc.zdjcyun.mvp.ui.activities.ProjectManageDetailActivity;
import com.zdjc.zdjcyun.mvp.ui.activities.SplashActivity;
import com.zdjc.zdjcyun.mvp.ui.adapter.AlarmDetailRecycViewAdapter;
import com.zdjc.zdjcyun.mvp.ui.adapter.DocumentsDetailRecycViewAdapter;
import com.zdjc.zdjcyun.mvp.viewmodel.IAlarmDetailModel;
import com.zdjc.zdjcyun.mvp.viewmodel.IDocumentsDetailModel;
import com.zdjc.zdjcyun.util.DownloadUtil;
import com.zdjc.zdjcyun.util.HttpDownloader;
import com.zdjc.zdjcyun.util.OpenFileUtils;
import com.zdjc.zdjcyun.util.PreferenceUtils;
import com.zdjc.zdjcyun.util.ScreenUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static com.zdjc.zdjcyun.util.DateUtil.getDateToString;
import static com.zdjc.zdjcyun.util.DateUtil.getDateToStringss;


public class DocumentsDetailModel extends BaseModel<FragmentDocumentsDetailBinding, DocumentsDetailPresenterImpl> implements IDocumentsDetailModel {


    private String id, userId;
    private DocumentsDetailRecycViewAdapter documentsDetailRecycViewAdapter;
    private int page = 1;
    private int tagPosition;
    private String startTimeS;
    private String endTimeS;
    private ArrayList<DocumentEntity.DataBean> data;
    private String downUrl;
    public int positionN;
    private ProgressDialog progressDialog;

    @SuppressLint("HandlerLeak")
    private  Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            ocumentDownloadDialog((String) msg.obj);
        }
    };

    @Override
    public void onCreate() {
        id = PreferenceUtils.getInt(getContext(), "sectorId") + "";
        userId = PreferenceUtils.getInt(getContext(), "userId") + "";

        startTimeS = getDateToStringss(System.currentTimeMillis()-7200000*12*7);
        endTimeS = getDateToStringss(System.currentTimeMillis());
        mBinder.btnStartTime.setText(startTimeS);
        mBinder.btnEndTime.setText(endTimeS);

        getDocuments(page);
        intView();
    }

    private void intView() {

        documentsDetailRecycViewAdapter = new DocumentsDetailRecycViewAdapter(getContext());
        mBinder.recycleView.setAdapter(documentsDetailRecycViewAdapter);
        mBinder.recycleView.setPullRefreshEnable(false);
        mBinder.recycleView.setIsLoadMore(false);
        mBinder.recycleView.setGridLayout(1);
        mBinder.recycleView.setOnPullLoadMoreListener(new PullLoadMoreRecyclerView.PullLoadMoreListener() {
            @Override
            public void onRefresh() {
                setRefresh();
            }

            @Override
            public void onLoadMore() {
                getData();
            }
        });


        documentsDetailRecycViewAdapter.setOnItemClickListener((view, position) -> {
            switch (view.getId()) {
                case R.id.btn_download:
                    downUrl = Constant.IMAGE_URL+data.get(position).getPath();
                    positionN = position;
                    downloadDocument();
                    break;
                default:
                    break;
            }
        });

        mBinder.btnSearch.setOnClickListener(v -> {
            page = 1;
            getSearchDocuments(mBinder.et1.getText().toString(), mBinder.et2.getText().toString());
        });

        mBinder.btnStartTime.setOnClickListener(v -> showDialogTwo());

        mBinder.btnEndTime.setOnClickListener(v -> showDialogTwo());
    }

    public void downloadDocument(){
        RxPermissions rxPermission = new RxPermissions(((Activity)getContext()));
        rxPermission
                .requestEach(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .subscribe(permission -> {
                    if (permission.granted) {
                        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                            // TODO: Consider calling
                            //    ActivityCompat#requestPermissions
                            // here to request the missing permissions, and then overriding
                            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                            //                                          int[] grantResults)
                            // to handle the case where the user grants the permission. See the documentation
                            // for ActivityCompat#requestPermissions for more details.
                            return;
                        }
                        downFile(downUrl);
                        Log.d("cc", permission.name + " is granted.");
                    } else if (permission.shouldShowRequestPermissionRationale) {
                        // 用户拒绝了该权限，没有选中『不再询问』（Never ask again）,那么下次再次启动时，还会提示请求权限的对话框
                        Log.d("cc", permission.name + " is denied. More info should be provided.");
                    } else {
                        // 用户拒绝了该权限，并且选中『不再询问』
                        Log.d("cc", permission.name + " is denied.");
                    }
                });
    }

    public String getSDPath(){
        File sdDir = null;
        boolean sdCardExist = Environment.getExternalStorageState()
                //判断sd卡是否存在
                .equals(android.os.Environment.MEDIA_MOUNTED);
        if(sdCardExist)
        {
            //获取跟目录
            sdDir = Environment.getExternalStorageDirectory();
        }
        return sdDir.toString()+"/zdjc/";
    }

    @Override
    public void onBeforeRequest(int tag) {
        UI.showWaitDialog();
    }

    @Override
    public void onSuccess(Object bean, int tag) {
        switch (tag) {
            case 1:
                data = (ArrayList<DocumentEntity.DataBean>) bean;
                if (data.size() > 0) {
                    documentsDetailRecycViewAdapter.setDataList(data);
                }else {
                    ToastUtils.showLongToast("暂时没有文档");
                }
                if (mBinder.recycleView != null) {
                    mBinder.recycleView.setPullLoadMoreCompleted();
                }
                break;
            case 2:
                data = (ArrayList<DocumentEntity.DataBean>) bean;
                if (data.size() > 0) {
                    documentsDetailRecycViewAdapter.setDataList(data);
                }
                if (mBinder.recycleView != null) {
                    mBinder.recycleView.setPullLoadMoreCompleted();
                }
                break;
            case 3:
                //确认成功之后的处理然后adapter那里刷新对应的位置
                documentsDetailRecycViewAdapter.notifyItemChanged(tagPosition, "zdjc");
                break;
            default:
                break;
        }
    }

    @Override
    public void onError(String errorMsg, int code, int tag) {

    }

    private void getDocuments(int page) {
        Map<String, String> map = new HashMap<>(0);
        map.put("sectorId", id);
        map.put("beginTime", startTimeS);
        map.put("endTime", endTimeS);
        map.put("name", "");
        map.put("type", "");
        mControl.getQueryDocuments(DocumentsDetailModel.this, map, 1);
    }

    private void getSearchDocuments(String documentName, String documentType) {
        Map<String, String> map = new HashMap<>(0);
        if("等级三".equals(documentType)){
            documentType = "三";
        }else if ("等级二".equals(documentType)){
            documentType = "二";
        }else if ("等级一".equals(documentType)){
            documentType = "一";
        }
        map.put("name", documentName);
        map.put("type", "");
        map.put("sectorId", id);
        map.put("beginTime", startTimeS);
        map.put("endTime", endTimeS);
        mControl.getQueryDocuments(this, map, 2);
    }

    private void getData() {
        new Handler().postDelayed(() -> {
            page++;
            getDocuments(page);
        }, 1000);
    }

    private void setRefresh() {
        page = 1;
        getDocuments(page);
    }

    /**
     * 时间选择框
     */
    private void showDialogTwo() {
        View view = LayoutInflater.from((getContext())).inflate(R.layout.dialog_date, null);
        final DatePicker startTimeD = view.findViewById(R.id.st);
        final DatePicker endTimeD = view.findViewById(R.id.et);
        final TextView tvSt = view.findViewById(R.id.tv_st);
        final TextView tvEt = view.findViewById(R.id.tv_et);
        final TimePicker timePicker = view.findViewById(R.id.myTimePicker);
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setView(view);
        tvSt.setText("开始时间");
        tvEt.setText("结束时间");
        timePicker.setVisibility(View.GONE);
        startTimeD.setVisibility(View.VISIBLE);
        endTimeD.updateDate(startTimeD.getYear(), startTimeD.getMonth(), 01);
        builder.setPositiveButton("确定", (dialog, which) -> {
            int month = startTimeD.getMonth() + 1;
            String stringStartMonth;
            String strinStartDayOfMonth;
            if (month<10){
                stringStartMonth = "0" + String.valueOf(month);
            }else {
                stringStartMonth = String.valueOf(month);
            }
            if (startTimeD.getDayOfMonth()<10){
                strinStartDayOfMonth = "0" + String.valueOf(startTimeD.getDayOfMonth());
            }else {
                strinStartDayOfMonth = String.valueOf(startTimeD.getDayOfMonth());
            }
            String st = "" + startTimeD.getYear() + "-" + stringStartMonth + "-" + strinStartDayOfMonth;

            int month1 = endTimeD.getMonth() + 1;
            String stringEndMonth;
            String strinEndDayOfMonth;
            if (month1<10){
                stringEndMonth = "0" + String.valueOf(month1);
            }else {
                stringEndMonth = String.valueOf(month1);
            }
            if (endTimeD.getDayOfMonth()<10){
                strinEndDayOfMonth = "0" + String.valueOf(endTimeD.getDayOfMonth());
            }else {
                strinEndDayOfMonth = String.valueOf(endTimeD.getDayOfMonth());
            }
            String et = "" + endTimeD.getYear() + "-" + stringEndMonth + "-" + strinEndDayOfMonth;
            startTimeS = st+" 00:00:00";
            endTimeS = et+" 00:00:00";
            mBinder.btnStartTime.setText(startTimeS);
            mBinder.btnEndTime.setText(endTimeS);
            getDocuments(page);
        });
        builder.setNegativeButton("取消", null);
        AlertDialog dialog = builder.create();
        dialog.show();
        //自动弹出键盘问题解决
        dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
    }

    /**
     * 文件下载
     *
     * @param url
     */
    public void downFile(String url) {
        progressDialog = new ProgressDialog(getContext());
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.setTitle("正在下载");
        progressDialog.setMessage("请稍后...");
        progressDialog.setProgress(0);
        progressDialog.setMax(100);
        progressDialog.show();
        progressDialog.setCancelable(false);
        DownloadUtil.get().download(url, getSDPath(),data.get(positionN).getName(), new DownloadUtil.OnDownloadListener() {
            @Override
            public void onDownloadSuccess(File file) {
                if (progressDialog != null && progressDialog.isShowing()) {
                    progressDialog.dismiss();
                }
                Message message = new Message();
                message.obj = getSDPath();
                handler.sendMessage(message);

            }

            @Override
            public void onDownloading(int progress) {
                progressDialog.setProgress(progress);
            }

            @Override
            public void onDownloadFailed(Exception e) {
                //下载异常进行相关提示操作
            }
        });
    }

    /**
     * 项目文库下载完成提示框并且打开下载的文件
     */
    public void ocumentDownloadDialog(String address) {
        final Dialog dialog = new Dialog(getContext(), R.style.dialog);
        // 通过LayoutInflater来加载一个xml的布局文件作为一个View对象
        View view = LayoutInflater.from(getContext()).inflate(R.layout.document_dialog, null);
        // 设置我们自己定义的布局文件作为弹出框的Content
        dialog.setContentView(view);
        dialog.show();

        dialog.setOnKeyListener((dialog1, keyCode, event) -> true);

        // 适配屏幕 screenAdaptive
        WindowManager.LayoutParams lp = dialog.getWindow().getAttributes();
        lp.width = 5 * ScreenUtil.getScreenWidth(getContext()) / 8;
        lp.height = FrameLayout.LayoutParams.WRAP_CONTENT;
        dialog.getWindow().setAttributes(lp);
        //点击对话框外部对话框不消失
        dialog.setCanceledOnTouchOutside(true);

        Button btnYes = view.findViewById(R.id.btn_btn_yes);
        TextView tvDocumentAddress = view.findViewById(R.id.tv_document_address);
        tvDocumentAddress.setText("文件管理下的存储或者SD卡下的zdjc文件夹下面");
        btnYes.setOnClickListener(v -> {
            RxPermissions rxPermission = new RxPermissions(((Activity)getContext()));
            rxPermission
                    .requestEach(Manifest.permission.READ_EXTERNAL_STORAGE)
                    .subscribe(permission -> {
                        if (permission.granted) {
                            if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                                // TODO: Consider calling
                                //    ActivityCompat#requestPermissions
                                // here to request the missing permissions, and then overriding
                                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                                //                                          int[] grantResults)
                                // to handle the case where the user grants the permission. See the documentation
                                // for ActivityCompat#requestPermissions for more details.
                                return;
                            }
                            File file = new File(getSDPath()+"/"+data.get(positionN).getName());
                            try {
                                OpenFileUtils.openFile(getContext(), file);
                            } catch (Exception e) {
                                ToastUtils.showLongToast("无可用打开方式");
                                e.printStackTrace();
                            }
                            Log.d("cc", permission.name + " is granted.");
                        } else if (permission.shouldShowRequestPermissionRationale) {
                            // 用户拒绝了该权限，没有选中『不再询问』（Never ask again）,那么下次再次启动时，还会提示请求权限的对话框
                            Log.d("cc", permission.name + " is denied. More info should be provided.");
                        } else {
                            // 用户拒绝了该权限，并且选中『不再询问』
                            Log.d("cc", permission.name + " is denied.");
                        }
                    });
            dialog.dismiss();
        });


    }
}
