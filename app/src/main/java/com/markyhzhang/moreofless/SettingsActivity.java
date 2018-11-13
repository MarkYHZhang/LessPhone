package com.markyhzhang.moreofless;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.BatteryManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

public class SettingsActivity extends AppCompatActivity{

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //set status bar color
        //todo customizable color
        Window window = getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(ContextCompat.getColor(getApplicationContext(), R.color.paper));
        window.setNavigationBarColor(ContextCompat.getColor(getApplicationContext(), R.color.paper));


        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_PORTRAIT);
        //Remove notification bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.setContentView(R.layout.activity_settings);

        //todo customizable color
        findViewById(R.id.settings_layout).setBackgroundColor(getResources().getColor(R.color.paper));

    }

    private HashMap<String, Integer> dictNametoID = new HashMap<String, Integer>(){{
        int count = 0;
        put("appButtonView1", ++count);
        put("appButtonView2", ++count);
        put("appButtonView3", ++count);
        put("appButtonView4", ++count);
        put("phoneButtonView", ++count);
        put("messageButtonView", ++count);
        put("cameraButtonView", ++count);
    }};

    private HashMap<Integer, String> dictIDtoName = new HashMap<Integer, String>(){{
        int count = 0;
        put(++count, "appButtonView1");
        put(++count, "appButtonView2");
        put(++count, "appButtonView3");
        put(++count, "appButtonView4");
        put(++count, "phoneButtonView");
        put(++count, "messageButtonView");
        put(++count, "cameraButtonView");
    }};

    public void performActionSettingActivity(View v){

        TextView tv= (TextView) v;
        int id = Integer.parseInt(getResources().getResourceEntryName(tv.getId()).substring(7));

    }
}
