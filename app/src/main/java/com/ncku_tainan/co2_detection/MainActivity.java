package com.ncku_tainan.co2_detection;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity{

    private ImageButton CO2_concentrration_button;
    private ImageButton pH_button;
    private ImageButton temperature_button;
    private TextView concentration_textView;
    private TextView ph_textView;
    private TextView temperature_textView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide(); //隱藏標題
        //getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN); //隱藏狀態

        CO2_concentrration_button = findViewById(R.id.CO2_concentrration_button);
        pH_button = findViewById(R.id.pH_button);
        temperature_button = findViewById(R.id.temperature_button);
        concentration_textView = findViewById(R.id.concentration_textView);
        ph_textView = findViewById(R.id.ph_textView);
        temperature_textView = findViewById(R.id.temperature_textView);

        CO2_concentrration_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this , Concentration.class);
                startActivity(intent);
            }
        });
        concentration_textView.setText(Html.fromHtml("CO<sub>2</sub> concentration") );
        concentration_textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this , Concentration.class);
                startActivity(intent);
            }
        });
        pH_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this , pH_value.class);
                startActivity(intent);
            }
        });
        ph_textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this , pH_value.class);
                startActivity(intent);
            }
        });
        temperature_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this , Temperature.class);
                startActivity(intent);
            }
        });
        temperature_textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this , Temperature.class);
                startActivity(intent);
            }
        });
    }
}
