package com.markyhzhang.moreofless;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.BatteryManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    SharedPreferences storage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        storage = getSharedPreferences("storage", MODE_PRIVATE);

        //makes a clean status bar
//        this.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR | View.SYSTEM_UI_FLAG_LOW_PROFILE | View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR);

        //set status bar color
        //todo customizable color
        Window window = getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(ContextCompat.getColor(getApplicationContext(), R.color.paper));
        window.setNavigationBarColor(ContextCompat.getColor(getApplicationContext(), R.color.paper));

        this.registerReceiver(new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                int level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, 0);
                ((TextView)findViewById(R.id.batteryView)).setText(String.valueOf(level) + "%");
            }
        }, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));


        SharedPreferences.Editor editor = storage.edit();
        editor.putString("APPSTR1","Browser");
        editor.putString("PKGSTR1","com.android.chrome");
        editor.apply();


        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_PORTRAIT);
//Remove notification bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.setContentView(R.layout.activity_main);

        //todo customizable color
        findViewById(R.id.main_layout).setBackgroundColor(getResources().getColor(R.color.paper));
        ((TextView)findViewById(R.id.dateView)).setText(new SimpleDateFormat("EEE, MMM dd", Locale.getDefault()).format(new Date()));


        for (int i = 1; i <= 7; i++) {
            TextView tw = findViewById(getResources().getIdentifier(dictIDtoName.get(i),"id",getPackageName()));

            AppAction appAction = AppManager.get(storage, i);

            if (appAction==null)
                tw.setText("");
            else
                tw.setText(appAction.getNameString());
        }
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

    public void performActionMainActivity(View v)
    {
        TextView tv= (TextView) v;

        if (tv.getText().equals("Settings")){
            startActivity(new Intent(this, SettingsActivity.class));
            return;
        }

        int id = dictNametoID.get(getResources().getResourceEntryName(tv.getId()));

        AppAction appAction = AppManager.get(storage,id);
        if (appAction==null)return;
        Intent launchIntent = getPackageManager().getLaunchIntentForPackage(appAction.getPackageString());
        startActivity(launchIntent);

    }


//    @Override
//    public void onWindowFocusChanged(boolean hasFocus) {
//        super.onWindowFocusChanged(hasFocus);
//        if (hasFocus)
//            this.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR  | View.SYSTEM_UI_FLAG_LOW_PROFILE);
//    }
}
