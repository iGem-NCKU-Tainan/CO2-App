package com.ncku_tainan.co2_detection;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

public class Home extends AppCompatActivity {

    private ImageButton start_button;
    private ImageButton Aboutus_button;
    private ImageButton switch_button;
    private ImageView logo_image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        final GlobalVariable checkSwitch = (GlobalVariable)getApplicationContext();
        if(checkSwitch.getCheckSwitch()) {
            Log.d("asd", "onCreate() returned: " + checkSwitch.getCheckSwitch());
            Intent intent = new Intent(Home.this, MyService.class);
            startService(intent);
        } else {
            Log.d("asd", "onCreate() returned: " + checkSwitch.getCheckSwitch());
            Intent intent = new Intent(Home.this, MyService.class);
            stopService(intent);
        }

        ConnectivityManager cm;
        cm = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo NetInfo = cm.getActiveNetworkInfo();

        if (NetInfo == null) {
            Toast.makeText(getApplicationContext(), "Offline status", Toast.LENGTH_SHORT).show();
        } else {
            if (NetInfo.isConnected()) {
                Toast.makeText(getApplicationContext(), "Connect to the internet", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getApplicationContext(), "Offline", Toast.LENGTH_SHORT).show();
            }
        }

        getSupportActionBar().hide(); //隱藏標題
        //getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN); //隱藏狀態

        start_button = findViewById(R.id.start_button);
        Aboutus_button = findViewById(R.id.Aboutus_button);
        logo_image = findViewById(R.id.logo_image);
        switch_button= findViewById(R.id.switch_button);

        logo_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url="http://2018.igem.org/Team:NCKU_Tainan";
                Intent ie = new Intent(Intent.ACTION_VIEW,Uri.parse(url));
                startActivity(ie);
            }
        });

        start_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(Home.this , MainActivity.class);
                startActivity(intent);
            }
        });
        Aboutus_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(Home.this , AboutUs.class);
                startActivity(intent);
            }
        });
        switch_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent();
                    intent.setClass(Home.this , Switch.class);
                    startActivity(intent);
                }
            });
        switch_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(Home.this , Switch.class);
                startActivity(intent);
            }
        });
    }
}
