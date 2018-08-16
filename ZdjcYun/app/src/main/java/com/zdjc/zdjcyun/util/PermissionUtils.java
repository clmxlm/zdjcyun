package com.zdjc.zdjcyun.util;

import android.app.Activity;
import android.text.TextUtils;

import com.blankj.utilcode.utils.LogUtils;
import com.tbruyelle.rxpermissions2.Permission;
import com.tbruyelle.rxpermissions2.RxPermissions;

import static android.Manifest.permission.ACCESS_COARSE_LOCATION;
import static android.Manifest.permission.ACCESS_FINE_LOCATION;
import static android.Manifest.permission.CALL_PHONE;
import static android.Manifest.permission.CAMERA;
import static android.Manifest.permission.READ_CONTACTS;
import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.READ_PHONE_STATE;
import static android.Manifest.permission.RECORD_AUDIO;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;


/**
 * Created by iCong on 2016/9/17.
 */
public class PermissionUtils {

    private static RxPermissions rxPermission;

    private static final String TAG = "RxPermissionTest";

    public PermissionUtils(Activity context) {
        if (context == null) {
            throw new NullPointerException("Context not null");
        }
        if (rxPermission == null) {
            rxPermission =new RxPermissions(context);
        }
    }

    public static PermissionUtils getInstance(Activity context) {
        return new PermissionUtils(context);
    }

    public void setPermission(String permission, Listener listener) {
        if (TextUtils.isEmpty(permission)) {
            throw new NullPointerException("permission not null");
        }
        if (listener == null) {
            throw new NullPointerException("PermissionListener not null");
        }
        try {
            if (TextUtils.equals(permission, CAMERA)) {
                rxPermission.requestEach(CAMERA).subscribe(listener::isPermission);
            } else if (TextUtils.equals(permission, CALL_PHONE)) {
                rxPermission.requestEach(CALL_PHONE).subscribe(listener::isPermission);
            } else if (TextUtils.equals(permission, WRITE_EXTERNAL_STORAGE)) {
                rxPermission.requestEach(WRITE_EXTERNAL_STORAGE).subscribe(listener::isPermission);
            } else if (TextUtils.equals(permission, READ_EXTERNAL_STORAGE)) {
                rxPermission.requestEach(READ_EXTERNAL_STORAGE).subscribe(listener::isPermission);
            } else if (TextUtils.equals(permission, READ_CONTACTS)) {
                rxPermission.requestEach(READ_CONTACTS).subscribe(listener::isPermission);
            } else if (TextUtils.equals(permission, ACCESS_COARSE_LOCATION)) {
                rxPermission.requestEach(ACCESS_COARSE_LOCATION).subscribe(listener::isPermission);
            }else if (TextUtils.equals(permission, ACCESS_FINE_LOCATION)) {
                rxPermission.requestEach(ACCESS_FINE_LOCATION).subscribe(listener::isPermission);
            } else if (TextUtils.equals(permission, READ_PHONE_STATE)) {
                rxPermission.requestEach(READ_PHONE_STATE).subscribe(listener::isPermission);
            }else if (TextUtils.equals(permission, RECORD_AUDIO)) {
                rxPermission.requestEach(RECORD_AUDIO).subscribe(listener::isPermission);
            } else {
                throw new IllegalArgumentException("Please enter the correct permissions");
            }
        }catch (Exception e){
            LogUtils.i(e.getMessage());
        }

    }

    public interface Listener {
        void isPermission(Permission permission);
    }
}
