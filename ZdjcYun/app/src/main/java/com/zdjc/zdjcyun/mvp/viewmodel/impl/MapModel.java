package com.zdjc.zdjcyun.mvp.viewmodel.impl;


import android.view.View;
import android.widget.RadioButton;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.model.LatLng;
import com.blankj.utilcode.utils.ToastUtils;
import com.github.jdsjlzx.interfaces.OnItemClickListener;
import com.zdjc.zdjcyun.R;
import com.zdjc.zdjcyun.app.BaseApplication;
import com.zdjc.zdjcyun.base.BaseModel;
import com.zdjc.zdjcyun.databinding.ActivityMapBinding;
import com.zdjc.zdjcyun.map.clusterutil.clustering.ClusterItem;
import com.zdjc.zdjcyun.map.clusterutil.clustering.ClusterManager;
import com.zdjc.zdjcyun.mvp.entity.AllProjectListEntity;
import com.zdjc.zdjcyun.mvp.presenter.impl.MapPresenterImpl;
import com.zdjc.zdjcyun.mvp.ui.activities.MapActivity;
import com.zdjc.zdjcyun.mvp.ui.activities.ProjectDetailActivity;
import com.zdjc.zdjcyun.mvp.viewmodel.IMapModel;
import com.zdjc.zdjcyun.util.PreferenceUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class MapModel extends BaseModel<ActivityMapBinding, MapPresenterImpl> implements IMapModel,
        OnItemClickListener,BaiduMap.OnMapLoadedCallback, View.OnClickListener{

    /**
     * 每个界面有自己的Presenter
     */
    private BaiduMap mBaiduMap;
    private MapStatus ms;
    private ClusterManager<MyItem> mClusterManager;
    private List<AllProjectListEntity.DataBean> dataBeanList;

    @Override
    public void onCreate() {
        Map<String,String> map = new HashMap<>(0);
        map.put("projectId", PreferenceUtils.getInt(getContext(),"projectId",0)+"");
        mControl.getProjectList(this,map,1);
        initListener();
    }

    @Override
    public void onBeforeRequest(int tag) {
        UI.showWaitDialog();
    }

    @Override
    public void onSuccess(Object bean, int tag) {
        switch (tag) {
            case 1:
                dataBeanList = (List<AllProjectListEntity.DataBean>) bean;
                if (dataBeanList.size()==0){
                    ToastUtils.showLongToast("该项目没有数据");
                }else {
                    initData(dataBeanList);
                }
                break;

                default:
                    break;
        }
    }

    @Override
    public void onError(String errorMsg, int code,int tag) {

    }

    public void initData(List<AllProjectListEntity.DataBean> dataBeanList) {
        mBaiduMap = mBinder.bmapView.getMap();

        ms = new MapStatus.Builder().target(new LatLng(Double.parseDouble(dataBeanList.get(0).getProjectLatitude()),
                Double.parseDouble(dataBeanList.get(0).getProjectLongitude()))).zoom(6).build();

        mBaiduMap.setOnMapLoadedCallback(this);
        mBaiduMap.animateMapStatus(MapStatusUpdateFactory.newMapStatus(ms));
        mBaiduMap.setMapStatus(MapStatusUpdateFactory.newMapStatus(new MapStatus.Builder().zoom(4).build()));
        // 定义点聚合管理类ClusterManager
        mClusterManager = new ClusterManager<>(getContext(), mBaiduMap);
        // 添加Marker点
        addMarkers(dataBeanList);
        // 设置地图监听，当地图状态发生改变时，进行点聚合运算
        mBaiduMap.setOnMapStatusChangeListener(mClusterManager);
        // 设置maker点击时的响应
        mBaiduMap.setOnMarkerClickListener(mClusterManager);

        mClusterManager.setOnClusterClickListener(cluster -> {
            ToastUtils.showLongToast("有" + cluster.getSize() + "个点");
            return false;
        });

        mClusterManager.setOnClusterItemClickListener(item -> {
            for (int i = 0; i < dataBeanList.size(); i++) {
                if (item.getPosition().latitude==Double.parseDouble(dataBeanList.get(i).getProjectLatitude())
                        &&item.getPosition().longitude==Double.parseDouble(dataBeanList.get(i).getProjectLongitude())){
                    PreferenceUtils.putInt(BaseApplication.getContext(),"projectId",dataBeanList.get(i).getProjectId());
                    PreferenceUtils.putString(getContext(),"projectName",dataBeanList.get(i).getProjectName());
                    ((MapActivity)UI).intent2Activity(ProjectDetailActivity.class);
                    break;
                }
            }
            return false;
        });
    }

    /**
     * 向地图添加Marker点
     */
    public void addMarkers(List<AllProjectListEntity.DataBean> dataBeanList) {
        List<LatLng> latLngs = new ArrayList<>();
        List<MyItem> items = new ArrayList<>();

        // 添加Marker点
        for (AllProjectListEntity.DataBean dataBean : dataBeanList) {
            latLngs.add(new LatLng(Double.parseDouble(dataBean.getProjectLatitude()),
                    Double.parseDouble(dataBean.getProjectLongitude())));
        }

        for (int i = 0; i < latLngs.size(); i++) {
//            // 构建文字option对象
            items.add(new MyItem(latLngs.get(i),i));
        }
        mClusterManager.addItems(items);
    }

    @Override
    public void onItemClick(View view, int position) {

    }

    @Override
    public void onClick(View view) {
        boolean checked = ((RadioButton) view).isChecked();
        switch (view.getId()) {
            case R.id.normal:
                if (checked) {
                    mBaiduMap.setMapType(BaiduMap.MAP_TYPE_NORMAL);
                }
                break;
            case R.id.statellite:
                if (checked) {
                    mBaiduMap.setMapType(BaiduMap.MAP_TYPE_SATELLITE);
                }
                break;
            default:
                break;
        }
    }

    /**
     * 每个Marker点，包含Marker点坐标以及图标
     */
    public class MyItem implements ClusterItem {

        private final LatLng mPosition;
        private  int makePosition;

        public MyItem(LatLng latLng, int position) {
            mPosition = latLng;
            makePosition = position;
        }

        @Override
        public LatLng getPosition() {
            return mPosition;
        }

        @Override
        public BitmapDescriptor getBitmapDescriptor() {
            if (dataBeanList.get(makePosition).getAlCount()==0){
                return BitmapDescriptorFactory
                        .fromResource(R.drawable.icon_gcoding_green);
            }else {
                return BitmapDescriptorFactory
                        .fromResource(R.drawable.icon_gcoding);
            }
        }
    }

    @Override
    public void onMapLoaded() {
        // TODO Auto-generated method stub
        ms = new MapStatus.Builder().zoom(6).build();
        mBaiduMap.animateMapStatus(MapStatusUpdateFactory.newMapStatus(ms));
    }

    @Override
    public void initListener() {
        mBinder.normal.setOnClickListener(this);
        mBinder.statellite.setOnClickListener(this);
    }
}
