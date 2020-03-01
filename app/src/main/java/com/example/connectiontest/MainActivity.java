package com.example.connectiontest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView textView,txtssid,txtSpeed,txtip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        textView = findViewById(R.id.txtmain);
        txtssid = findViewById(R.id.txtssid);
        txtSpeed = findViewById(R.id.txtspeed);
        txtip = findViewById(R.id.txtip);

        getWifiInformation();

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,ClockActivity.class);
                startActivity(intent);
            }
        });

        final Handler ha=new Handler();
        ha.postDelayed(new Runnable() {

            @Override
            public void run() {
                //call function
                checkConnection();
                getWifiLevel();
                getWifiInformation();

                ha.postDelayed(this, 10000);
            }
        }, 10000);
    }

    public void getWifiInformation()
    {
        WifiManager wifiManager = (WifiManager) getApplicationContext().getSystemService(WIFI_SERVICE);
        WifiInfo wifiInfo = wifiManager.getConnectionInfo();
        int ipp = wifiInfo.getIpAddress();
        String ip = String.valueOf(ipp);
        String ssid = wifiInfo.getSSID();
        txtssid.setText("");
        txtssid.setText("SSID : "+ssid);
        txtip.setText("");
        txtip.setText("IP : "+ip);

    }

    public void getWifiLevel()
    {
        WifiManager wifiManager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        int linkSpeed = wifiManager.getConnectionInfo().getRssi();
        int level = WifiManager.calculateSignalLevel(linkSpeed, 5);
        String speed = String.valueOf(level);
        txtSpeed.setText("");
        txtSpeed.setText("Speed : " + speed + "Mbps");

    }

    private void checkConnection() {
        boolean connected = false;
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
            //we are connected to a network
            connected = true;
            textView.setText("Connected");

        } else {
            connected = false;
            textView.setText("Disconnected");
        }
    }
}
