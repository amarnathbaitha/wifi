package com.example.emb_amarbai.mywifi;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.WifiManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    //https://stackoverflow.com/questions/24827470/how-to-make-a-service-to-check-wifi-state
    TextView WifiState;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        WifiState = (TextView)findViewById(R.id.wifistate);
        this.registerReceiver(this.WifiStateChangedReceiver, new IntentFilter(WifiManager.WIFI_STATE_CHANGED_ACTION));

    }


    private BroadcastReceiver WifiStateChangedReceiver = new BroadcastReceiver(){
        @Override
        public void onReceive(Context context, Intent intent) {

            int extraWifiState = intent.getIntExtra(WifiManager.EXTRA_WIFI_STATE ,
                    WifiManager.WIFI_STATE_UNKNOWN);

            switch(extraWifiState){
                case WifiManager.WIFI_STATE_DISABLED:
                    WifiState.setText("WIFI STATE DISABLED");
                    break;
                case WifiManager.WIFI_STATE_DISABLING:
                    WifiState.setText("WIFI STATE DISABLING");
                    break;
                case WifiManager.WIFI_STATE_ENABLED:
                    WifiState.setText("WIFI STATE ENABLED");
                    WifiManager wifiManager = (WifiManager)context.getSystemService(Context.WIFI_SERVICE);
                    wifiManager.setWifiEnabled(false);
                    break;
                case WifiManager.WIFI_STATE_ENABLING:
                    WifiState.setText("WIFI STATE ENABLING");
                    break;
                case WifiManager.WIFI_STATE_UNKNOWN:
                    WifiState.setText("WIFI STATE UNKNOWN");
                    break;
            }

        }};
}
