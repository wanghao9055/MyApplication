package com.example.wanghao.myapplication;


import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBar;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;



public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Handler handler =new Handler(){
        public void handleMessage(Message message){
            TextView textView=(TextView)findViewById(R.id.text_view);
            String location=(String)message.obj;
            if(location==null){
                location="北京市";
                Toast.makeText(MainActivity.this,"定位服务未开启",Toast.LENGTH_SHORT).show();
            }
            else{
                Toast.makeText(MainActivity.this,"定位服务开启",Toast.LENGTH_SHORT).show();
            }
            textView.setText(location);
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
         setContentView(R.layout.activity_main);
         final  TextView textView=(TextView)findViewById(R.id.text_view);
        //自定义ActionBar
         ActionBar actionBar = getSupportActionBar();
        if(actionBar==null){
            Toast.makeText(this,"wrong",Toast.LENGTH_SHORT).show();
        }
        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        actionBar.setCustomView(R.layout.actionbar);

        //定位部分

        final Button locate=(Button) findViewById(R.id.locate);
        locate.setOnClickListener(this);
        new Thread(new Runnable() {
            @Override
            public void run() {
                Mylocate mylocate=new Mylocate(MainActivity.this);
                String location =mylocate.getProvince();
                Message message=new Message();
                message.obj=location;
                handler.sendMessage(message);

            }
        }).start();



      /*  Mylocate mylocate=new Mylocate(this);
        String location =mylocate.getCity();
        if(location==null){
            location="北京市";
            Toast.makeText(this,"定位服务未开启",Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(this,"定位服务开启",Toast.LENGTH_SHORT).show();
        }
        textView.setText(location);

        Button locate=(Button) findViewById(R.id.locate);
        locate.setOnClickListener(this);*/
    }

    @Override
    /*
    监听按键
     */
    public void onClick(View view){
        switch (view.getId()){
            case R.id.locate:{
                /*
                定位
                 */
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Mylocate mylocate=new Mylocate(MainActivity.this);
                        String location =mylocate.getProvince();
                        Message message=new Message();
                        message.obj=location;
                        handler.sendMessage(message);

                    }
                }).start();}break;
            default:break;

        }

    }
    /*String locate() {
        LocationManager locationManager;
        //设置定位方式：网络定位
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        String provider;
        provider = LocationManager.NETWORK_PROVIDER;
        String add = "北京";
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
            //Geocoder根据经纬度返回城市名
            Geocoder ge = new Geocoder(this);
            try {
                addList = ge.getFromLocation(lat, lng, 1);
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (addList != null && addList.size() > 0) {
                for (int i = 0; i < addList.size(); i++) {
                    ad = addList.get(i);
                }
                add = ad.getLocality();
            }
            else{
                add="定位服务未开启";
            }
        }
        return add;
    }*/




    }

