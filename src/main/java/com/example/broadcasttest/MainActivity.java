package com.example.broadcasttest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.p2p.WifiP2pManager;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private IntentFilter filter;
    private NetworkChangeReceiver changeReceiver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        filter=new IntentFilter();
        filter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        changeReceiver=new NetworkChangeReceiver();
        registerReceiver(changeReceiver,filter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(changeReceiver);
    }
    public class NetworkChangeReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            ConnectivityManager connectivityManager=(ConnectivityManager)
                    getSystemService(Context.CONNECTIVITY_SERVICE);
            //getSystemService方法只能做MainActivity中调用。
            NetworkInfo info=connectivityManager.getActiveNetworkInfo();
            if(info!=null&&info.isAvailable())
            {
                Toast.makeText(context,"network is available",Toast.LENGTH_SHORT).show();
            }
            else
            {
                Toast.makeText(context,"network is unavailable",Toast.LENGTH_SHORT).show();
            }

        }
    }

}
