package com.adi.weatherapiapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class AirQuality extends AppCompatActivity {

    private TextView dayDate, CO, NO2, O3, SO2, PM25, PM10, USEpa, UKDef, city;
    private ImageView btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_air_quality);

        btnBack=findViewById(R.id.aq_btn_back);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        city=findViewById(R.id.aq_tv_place);
        dayDate=findViewById(R.id.aq_tv_daydate);
        CO=findViewById(R.id.aq_tv_co);
        NO2=findViewById(R.id.aq_tv_no2);
        O3=findViewById(R.id.aq_tv_o3);
        SO2=findViewById(R.id.aq_tv_so2);
        PM25=findViewById(R.id.aq_tv_pm25);
        PM10=findViewById(R.id.aq_tv_pm10);
        USEpa=findViewById(R.id.aq_tv_usepa);
        UKDef=findViewById(R.id.aq_tv_ukdefra);

        city.setText(getIntent().getStringExtra("city"));
        dayDate.setText(getIntent().getStringExtra("day") +" "+ getIntent().getStringExtra("date"));
        CO.setText(String.valueOf(getIntent().getFloatExtra("co", 0))+" μg/m3");
        NO2.setText(String.valueOf(getIntent().getFloatExtra("no2", 0))+" μg/m3");
        O3.setText(String.valueOf(getIntent().getFloatExtra("o3", 0))+" μg/m3");
        SO2.setText(String.valueOf(getIntent().getFloatExtra("so2", 0))+" μg/m3");
        PM25.setText(String.valueOf(getIntent().getFloatExtra("pm25", 0))+" μg/m3");
        PM10.setText(String.valueOf(getIntent().getFloatExtra("pm10", 0))+" μg/m3");
        USEpa.setText(String.valueOf(getIntent().getFloatExtra("usEpaIndex", 0)));
        UKDef.setText(String.valueOf(getIntent().getFloatExtra("ukDefraIndes", 0)));

    }
}