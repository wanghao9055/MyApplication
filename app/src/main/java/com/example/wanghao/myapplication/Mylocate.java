package com.example.wanghao.myapplication;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import java.util.List;

/**
 * Created by wanghao on 2015/12/22.
 */
public class Mylocate {

    Address address;
    Context context ;

    public Mylocate(Context context){
        this.context =context;
        address=locate();
    }
    /*
     返回城市名
     */
    public String getCity(){
        if(address!=null){
        String city=address.getLocality();
        if(city!=null){
            return city;
        }
        }
        return null;
    }

    public String getProvince(){
        if(address!=null){
            String province=address.getAdminArea();
            if(province!=null){
                return province;
            }
        }
        return null;
    }



    public Address locate(){
        LocationManager locationManager;
        //设置定位方式：网络定位
        locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        String provider;
        provider = LocationManager.NETWORK_PROVIDER;

        double lat;
        double lng;
        Address ad = null;

        Location location = null;
        try {
            location = locationManager.getLastKnownLocation(provider);
        } catch (SecurityException e) {
            e.printStackTrace();
        }

        if (location != null) {
            lat = location.getLatitude();
            lng = location.getLongitude();
            List<Address> addList = null;
            //Geocoder根据经纬度返回Address类
            Geocoder ge = new Geocoder(context);
            try {
                addList = ge.getFromLocation(lat, lng, 1);
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (addList != null && addList.size() > 0) {
                for (int i = 0; i < addList.size(); i++) {
                    ad = addList.get(i);
                }
                    return ad;
            }
        }
        return null;
    }

}
