package com.zdjc.zdjcyun.mvp.entity;



public class SersonDataBeanSelect {
    private final static int PLATFORMS_FACEBOOK = 1;
    private final static int PLATFORMS_INSTAGRAM = 2;
    private final static int PLATFORMS_GOOGLE_PHOTOS = 3;
    private final static int PLATFORMS_GOOGLEDRIVE = 4;
    private final static int PLATFORMS_FLICKR = 5;
    private final static int PLATFORMS_ONEDRIVE = 6;
  
    public static SersonData DifferentSersonData(int flag) throws Exception {
        SersonData sersonData = null;
        switch (flag){
            case PLATFORMS_FACEBOOK:
                sersonData = new SensorDataEntity();
                break;
            case PLATFORMS_INSTAGRAM:
                sersonData = new SensorDataEntity();
                break;
            case PLATFORMS_GOOGLE_PHOTOS:
                sersonData = new SensorDataEntity();
                break;
            case PLATFORMS_GOOGLEDRIVE:
                sersonData = new SensorDataEntity();
                break;
            case PLATFORMS_FLICKR:
                sersonData = new SensorDataEntity();
                break;
            case PLATFORMS_ONEDRIVE:
                sersonData = new SensorDataEntity();
                break;
            default:  
                throw new Exception("UNDEFINED FLAG");
        }  
        return sersonData;
    }

}