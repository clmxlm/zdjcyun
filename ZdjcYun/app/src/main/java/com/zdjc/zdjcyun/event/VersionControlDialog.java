package com.zdjc.zdjcyun.event;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.view.Display;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.blankj.utilcode.utils.ToastUtils;
import com.zdjc.zdjcyun.R;
import com.zdjc.zdjcyun.app.Constant;
import com.zdjc.zdjcyun.util.PreferenceUtils;
import com.zdjc.zdjcyun.util.ScreenUtil;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @author ClmXlm
 * @create 2019/2/28
 * @Describe
 */
public class VersionControlDialog {

    private Context context;
    private static DownloadProgressButton btnEditionYes;

    /**
     * 版本必须更新提醒对话框
     */
    public void myEditionDialog(final Activity activity, final int updated) {
        final Dialog dialog = new Dialog(activity, R.style.dialog);
        // 通过LayoutInflater来加载一个xml的布局文件作为一个View对象
        View view = LayoutInflater.from(activity).inflate(R.layout.editiondialog, null);
        // 设置我们自己定义的布局文件作为弹出框的Content
        dialog.setContentView(view);
        dialog.show();
        dialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                return true;
            }
        });
        // 适配屏幕 screenAdaptive
        WindowManager wm = activity.getWindowManager();
        Display display = wm.getDefaultDisplay();
        WindowManager.LayoutParams lp = dialog.getWindow().getAttributes();
        lp.width = 7 * display.getWidth() / 8;
        lp.height = FrameLayout.LayoutParams.WRAP_CONTENT;
        dialog.getWindow().setAttributes(lp);
        //点击对话框外部对话框不消失
        dialog.setCanceledOnTouchOutside(false);

        TextView tv_edition_yes = (TextView) view.findViewById(R.id.tv_edition_yes);
        tv_edition_yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }

    /**
     * 版本更新提醒对话框
     */
    public void noMustBeEditionDialog(final Context context) {
        final Dialog dialog = new Dialog(context, R.style.dialog);
        this.context = context;
        // 通过LayoutInflater来加载一个xml的布局文件作为一个View对象
        View view = LayoutInflater.from(context).inflate(R.layout.editionnotmustbedialog, null);
        // 设置我们自己定义的布局文件作为弹出框的Content
        dialog.setContentView(view);
        dialog.show();

        dialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                return true;
            }
        });

        // 适配屏幕 screenAdaptive
        WindowManager.LayoutParams lp = dialog.getWindow().getAttributes();
        lp.width = 5 * ScreenUtil.getScreenWidth(context) / 8;
        lp.height = FrameLayout.LayoutParams.WRAP_CONTENT;
        dialog.getWindow().setAttributes(lp);
        //点击对话框外部对话框不消失
        dialog.setCanceledOnTouchOutside(false);

        btnEditionYes =  view.findViewById(R.id.btn_edition_yes);
        Button btnEditionRemindLater =  view.findViewById(R.id.btn_edition_remindLater);
        Button btnEditionNo =  view.findViewById(R.id.btn_edition_no);


        btnEditionYes.setShowBorder(false);
        btnEditionYes.setCurrentText("升级");
        btnEditionYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                loadNewVersionProgress();
                showTheButton();
            }
        });

        btnEditionRemindLater.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        btnEditionNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }

    /**
     * 下载新版本程序
     */
    private  void loadNewVersionProgress() {
        final   String uri= Constant.APK_URL+ PreferenceUtils.getString(context,"apkUrl");
        //启动子线程下载任务
        new Thread(){
            @Override
            public void run() {
                try {
                    File file = getFileFromServer(uri);
                    sleep(2000);
                    installApk(file);
                } catch (Exception e) {
                    //下载apk失败
                    Looper.prepare();
                    ToastUtils.showShortToast("下载新版本失败");
                    e.printStackTrace();
                    Looper.loop();
                }
            }}.start();
    }

    /**
     * 安装apk
     */
    private  void installApk(File file) {
        Intent intent = new Intent();
        //执行动作
        intent.setAction(Intent.ACTION_VIEW);
        //执行的数据类型
        intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
        context.startActivity(intent);
    }

    /**
     * 从服务器获取apk文件的代码
     * 传入网址uri，进度条对象即可获得一个File文件
     * （要在子线程中执行）
     */
    private  File getFileFromServer(String uri) throws Exception{
        //如果相等的话表示当前的sdcard挂载在手机上并且是可用的
        if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
            URL url = new URL(uri);
            HttpURLConnection conn =  (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(5000);
            ((Activity)context).runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    //获取到文件的大小
                    btnEditionYes.setMaxProgress(conn.getContentLength());
                }
            });

            InputStream is = conn.getInputStream();
            //当前时间的毫秒数
            long time= System.currentTimeMillis();
            File file = new File(Environment.getExternalStorageDirectory(), time+"zdjc.apk");
            FileOutputStream fos = new FileOutputStream(file);
            BufferedInputStream bis = new BufferedInputStream(is);
            byte[] buffer = new byte[1024];
            int len ;
            int total=0;
            while((len =bis.read(buffer))!=-1){
                fos.write(buffer, 0, len);
                total+= len;
                int finalTotal = total;
                ((Activity)context).runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //获取当前下载量
                        btnEditionYes.setProgressText("下载中", btnEditionYes.getProgress() + finalTotal);
                    }
                });

            }
            fos.close();
            bis.close();
            is.close();
            return file;
        }
        else{
            return null;
        }
    }

    private  void showTheButton() {

        if (btnEditionYes.getProgress() + 10 > 100) {
            btnEditionYes.setState(DownloadProgressButton.STATE_FINISH);

            btnEditionYes.setCurrentText("安装中");
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    btnEditionYes.setState(DownloadProgressButton.STATE_NORMAL);
                    btnEditionYes.setCurrentText("打开");
                }
            }, 2000);

        }

        if (btnEditionYes.getState() == DownloadProgressButton.STATE_NORMAL
                || btnEditionYes.getState() == DownloadProgressButton.STATE_PAUSE) {
            btnEditionYes.setState(DownloadProgressButton.STATE_DOWNLOADING);
            loadNewVersionProgress();
            return;
        }

        if (btnEditionYes.getState() == DownloadProgressButton.STATE_DOWNLOADING) {
            btnEditionYes.setState(DownloadProgressButton.STATE_PAUSE);
            btnEditionYes.setCurrentText("继续");
        }
    }

}
