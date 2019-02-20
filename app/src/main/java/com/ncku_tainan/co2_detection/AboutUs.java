package com.ncku_tainan.co2_detection;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class AboutUs extends AppCompatActivity {

    private ImageButton Back_button;
    private ImageView wiki_image;
    private ImageView FB_image;
    private ImageView IG_image;
    private TextView Description_textView;
    private Switch notification_switch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);
        getSupportActionBar().hide(); //隱藏標題
        //getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN); //隱藏狀態

        final GlobalVariable checkSwitch = (GlobalVariable)getApplicationContext();
        notification_switch = (Switch) findViewById(R.id.notification_switch);

        if(checkSwitch.getCheckSwitch()) {
            Log.d("asd", "onCreate() returned: " + checkSwitch.getCheckSwitch());
            notification_switch.setChecked(true);
            checkSwitch.setCheckSwitch(true);
            Intent intent = new Intent(AboutUs.this, MyService.class);
            startService(intent);
        } else {
            Log.d("asd", "onCreate() returned: " + checkSwitch.getCheckSwitch());
            notification_switch.setChecked(false);
            checkSwitch.setCheckSwitch(false);
            Intent intent = new Intent(AboutUs.this, MyService.class);
            stopService(intent);
        }

        notification_switch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    Log.d("asd", "onCreate() returned: " + checkSwitch.getCheckSwitch());
                    notification_switch.setChecked(true);
                    checkSwitch.setCheckSwitch(true);
                    Toast.makeText(getApplicationContext(), "Open notification", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(AboutUs.this, MyService.class);
                    startService(intent);
                } else {
                    Log.d("asd", "onCreate() returned: " + checkSwitch.getCheckSwitch());
                    notification_switch.setChecked(false);
                    checkSwitch.setCheckSwitch(false);
                    Toast.makeText(getApplicationContext(), "Close notification", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(AboutUs.this, MyService.class);
                    stopService(intent);
                }
            }
        });

        Description_textView = findViewById(R.id.Description_textView);
        String source = "Project description\n" +
                "We designed a device piping the CO2 and converted such carbon source to biomass by integrated a non-native Calvin–Benson–Bassham cycle into E. coli. " +
                "“Of course” is a biological approach to “off” CO2 emission through the RuBisCO and PRK genes from Synchococcus sp, " +
                "which encode for major enzymes involved in carbon fixation, are incorporated into our chassis. " +
                "Industrial gases enter the pipe (inlet) at the bottom, flow through a ceramic nozzle and mix with liquids contained E. coli that " +
                "consume CO2 in the cylindrical container as well as sensing by Pasr promoter for determining the CO2 concentration " +
                "with corresponding value of pH in terms of sfGFP. \n" +
                "Device description\n" +
                "We add the carbon dioxide concentration sensor, temperature sensor and pH sensor to our device. " +
                "Sensor will send the measured data to our database, and use the app to receive the data in the database.\n";
        Description_textView.setText(source);

        wiki_image = findViewById(R.id.wiki_image);
        wiki_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url="http://2018.igem.org/Team:NCKU_Tainan";
                Intent ie = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(ie);
            }
        });
        FB_image = findViewById(R.id.FB_image);
        FB_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url="https://www.facebook.com/igem.ncku/";
                Intent ie = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(ie);
            }
        });
        IG_image = findViewById(R.id.IG_image);
        IG_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url="https://www.instagram.com/igemncku/";
                Intent ie = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(ie);
            }
        });

        Back_button = findViewById(R.id.Back_button);
        Back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(AboutUs.this, Home.class);
                startActivity(intent);
            }
        });
    }
}
