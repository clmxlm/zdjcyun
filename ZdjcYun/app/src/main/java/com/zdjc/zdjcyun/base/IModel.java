package com.zdjc.zdjcyun.base;

/**
 * Created by ali on 2017/2/15.
 */

public interface IModel {
    void onCreate();
    void onStart();
    void onResume();
    void onPause();
    void onStop();
    void onDestroy();
}
