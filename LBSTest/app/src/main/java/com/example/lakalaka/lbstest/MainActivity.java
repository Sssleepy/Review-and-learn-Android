package com.example.lakalaka.lbstest;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.TextureMapView;
import com.baidu.mapapi.model.LatLng;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    public LocationClient mLocationClient;
    private TextView postionText;
    private Context mcontext;
    private Button internet;
    private Button gps;
    private TextureMapView mapView;
    private BaiduMap baiduMap=null;
    private boolean isFirstLocate=true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mcontext=this;
        mLocationClient=new LocationClient(getApplicationContext());
        mLocationClient.registerLocationListener(new MyLocationListener());
        SDKInitializer.initialize(getApplicationContext());
        mapView= (TextureMapView) findViewById(R.id.bmapView);
        setContentView(R.layout.activity_main);
        internet = (Button) findViewById(R.id.btn_internet);
        gps = (Button) findViewById(R.id.btn_gps);

        postionText= (TextView) findViewById(R.id.position_text_view);
        List<String> permissionList=new ArrayList<>();
        if(ContextCompat.checkSelfPermission(mcontext, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED){
            permissionList.add(Manifest.permission.ACCESS_FINE_LOCATION);
        }
        if(ContextCompat.checkSelfPermission(mcontext,Manifest.permission.READ_PHONE_STATE)
                !=PackageManager.PERMISSION_GRANTED){
            permissionList.add(Manifest.permission.READ_PHONE_STATE);
        }
        if(ContextCompat.checkSelfPermission(mcontext,Manifest.permission.WRITE_EXTERNAL_STORAGE)
                !=PackageManager.PERMISSION_GRANTED){
            permissionList.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }
        if(!permissionList.isEmpty()){
            String[] permissions=permissionList.toArray(new String[permissionList.size()]);
            ActivityCompat.requestPermissions(MainActivity.this,permissions,1);
        }else{
            internet.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    requestLocation(1);
                }
            });
            gps.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    requestLocation(2);
                }
            });


        }
    }

    private void navigateTo(BDLocation location){
        if(isFirstLocate){
            LatLng ll=new LatLng(location.getLatitude(),location.getLongitude());
            MapStatusUpdate update= MapStatusUpdateFactory.newLatLng(ll);
            baiduMap.animateMapStatus(update);
            update=MapStatusUpdateFactory.zoomTo(16f);
            baiduMap.animateMapStatus(update);
            isFirstLocate=false;
        }
    }
    public void requestLocation(int a) {
        switch(a){
            case 1:
                initLocation();
            break;
            case 2:
                initLocation1();
            default:
            break;
         }


        mLocationClient.start();
    }
    private void initLocation(){

        LocationClientOption option=new LocationClientOption();
        option.setScanSpan(2000);
        option.setIsNeedAddress(true);
        mLocationClient.setLocOption(option);
    }
    private void initLocation1(){


        LocationClientOption option=new LocationClientOption();
        option.setLocationMode(LocationClientOption.LocationMode.Device_Sensors);
        option.setScanSpan(2000);
        option.setIsNeedAddress(true);
        mLocationClient.setLocOption(option);
    }

//    @Override
//    protected void onResume() {
//        super.onResume();
//        mapView.onResume();
//    }
//
//    @Override
//    protected void onPause() {
//        super.onPause();
//        mapView.onPause();
//    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mLocationClient.stop();
        mapView.onDestroy();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch(requestCode){
            case 1:
                if(grantResults.length>0){
                    for(int result:grantResults){
                        if(result!=PackageManager.PERMISSION_GRANTED){
                            Toast.makeText(mcontext, "必须同意所有权限才能使用本程序", Toast.LENGTH_SHORT).show();
                            finish();
                            return;
                        }
                    }
                    baiduMap=mapView.getMap();

                }else{
                    Toast.makeText(mcontext, "发生未知错误", Toast.LENGTH_SHORT).show();
                }
                requestLocation(1);
            break;
            default:

         }

    }
    public class MyLocationListener implements BDLocationListener{

        @Override
        public void onReceiveLocation(BDLocation bdLocation) {
//            if(bdLocation.getLocType()==BDLocation.TypeGpsLocation||bdLocation.getLocType()
//                    ==BDLocation.TypeNetWorkLocation){
//                navigateTo(bdLocation);
//            }
            StringBuilder currentPostion=new StringBuilder();
            currentPostion.append("维度:").append(bdLocation.getLatitude()).append("\n");
            currentPostion.append("经线:").append(bdLocation.getLongitude()).append("\n");
//            currentPostion.append("国家:").append(bdLocation.getCountry()).append("\n");
//            currentPostion.append(" 省 :").append(bdLocation.getProvince()).append("\n");
//            currentPostion.append(" 市 :").append(bdLocation.getCity()).append("\n");
//            currentPostion.append(" 区 :").append(bdLocation.getDistrict()).append("\n");
//            currentPostion.append("街道:").append(bdLocation.getStreet()).append("\n");
            currentPostion.append("您现在所在的位置是:").append("\n3")
                    .append(bdLocation.getCountry())
                    .append(bdLocation.getProvince())
                    .append(bdLocation.getCity())
                    .append(bdLocation.getDistrict())
                    .append(bdLocation.getStreet()).append("\n");
            currentPostion.append("定位方式:");
            if(bdLocation.getLocType()==BDLocation.TypeGpsLocation){
                currentPostion.append("GPS");
            }else if(bdLocation.getLocType()==BDLocation.TypeNetWorkLocation){
                currentPostion.append("网络");
            }else if(bdLocation.getLocType()==BDLocation.TypeGpsLocation&&bdLocation.getLocType()==BDLocation.TypeGpsLocation){
                currentPostion.append("GPS+网络");
            }
            postionText.setText(currentPostion);
        }
    }
}
