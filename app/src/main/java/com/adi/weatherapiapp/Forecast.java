package com.adi.weatherapiapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.TextureView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.adi.weatherapiapp.retrofit.ApiService;
import com.bumptech.glide.Glide;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.adi.weatherapiapp.MainActivity.ALERTS;
import static com.adi.weatherapiapp.MainActivity.API_KEY;
import static com.adi.weatherapiapp.MainActivity.AQI;
import static com.adi.weatherapiapp.MainActivity.DAYS;

public class Forecast extends AppCompatActivity {

    private ImageView imgCondition1, imgCondition2, imgCondition3, btnBack;
    private TextView tvDate1, tvDate2, tvDate3, tvCity;
    private TextView tvCondition1, tvCondition2, tvCondition3;
    private TextView tvMaxTemp1,tvMaxTemp2, tvMaxTemp3, tvMinTemp1, tvMinTemp2, tvMinTemp3, tvAvgTemp1,
    tvAvgTemp2, tvAvgTemp3;
    private TextView tvMaxWind1, tvMaxWind2, tvMaxWind3, tvPrecip1, tvPrecip2, tvPrecip3;
    private CardView cv1, cv2, cv3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forecast);

        btnBack=findViewById(R.id.fc_btn_back);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        tvCity=findViewById(R.id.fc_tv_place);

        imgCondition1=findViewById(R.id.fc_img_condition1);
        tvCondition1=findViewById(R.id.fc_tv_condition1);
        tvDate1=findViewById(R.id.fc_tv_date1);
        tvMaxTemp1=findViewById(R.id.fc_tv_maxtemp1);
        tvMinTemp1=findViewById(R.id.fc_tv_mintemp1);
        tvAvgTemp1=findViewById(R.id.fc_tv_avgtemp1);
        tvMaxWind1=findViewById(R.id.fc_tv_maxwind1);
        tvPrecip1=findViewById(R.id.fc_tv_precip1);

        imgCondition2=findViewById(R.id.fc_img_condition2);
        tvCondition2=findViewById(R.id.fc_tv_condition2);
        tvDate2=findViewById(R.id.fc_tv_date2);
        tvMaxTemp2=findViewById(R.id.fc_tv_maxtemp2);
        tvMinTemp2=findViewById(R.id.fc_tv_mintemp2);
        tvAvgTemp2=findViewById(R.id.fc_tv_avgtemp2);
        tvMaxWind2=findViewById(R.id.fc_tv_maxwind2);
        tvPrecip2=findViewById(R.id.fc_tv_precip2);

        imgCondition3=findViewById(R.id.fc_img_condition3);
        tvCondition3=findViewById(R.id.fc_tv_condition3);
        tvDate3=findViewById(R.id.fc_tv_date3);
        tvMaxTemp3=findViewById(R.id.fc_tv_maxtemp3);
        tvMinTemp3=findViewById(R.id.fc_tv_mintemp3);
        tvAvgTemp3=findViewById(R.id.fc_tv_avgtemp3);
        tvMaxWind3=findViewById(R.id.fc_tv_maxwind3);
        tvPrecip3=findViewById(R.id.fc_tv_precip3);

        cv1=findViewById(R.id.cv_fc_1);
        cv2=findViewById(R.id.cv_fc_2);
        cv3=findViewById(R.id.cv_fc_3);

        String city=getIntent().getStringExtra("city");
        tvCity.setText(city);
        String latlon=getIntent().getStringExtra("latlon");
        getApiToView(latlon, city);
    }

    private void getApiToView(String latitudeLongitude, String nameCity) {
        ApiService.endPoint().getDataWeather(API_KEY, latitudeLongitude, DAYS, AQI, ALERTS)
                .enqueue(new Callback<Model>() {
                    @Override
                    public void onResponse(Call<Model> call, Response<Model> response) {
                        Model.Forecast forecast = response.body().getForecast();
                        String condition1=forecast.getForecastday().get(0).getDay().getCondition().getText();
                        String date1=forecast.getForecastday().get(0).getDate();
                        float maxTemp1=forecast.getForecastday().get(0).getDay().getMaxtemp_c();
                        float minTemp1=forecast.getForecastday().get(0).getDay().getMintemp_c();
                        float avgTemp1=forecast.getForecastday().get(0).getDay().getAvgtemp_c();
                        float maxWind1=forecast.getForecastday().get(0).getDay().getMaxwind_kph();
                        float precip1=forecast.getForecastday().get(0).getDay().getTotalprecip_mm();
                        String img_condition1=forecast.getForecastday().get(0).getDay().getCondition().getIcon();
                        String new_img_condition1="https:"+img_condition1.replace("64x64","128x128");



                        cv1.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent(Forecast.this, DetailForecast.class);
                                intent.putExtra("date",forecast.getForecastday().get(0).getDate());
                                intent.putExtra("city",getIntent().getStringExtra("city"));
                                intent.putExtra("latlon", latitudeLongitude);
                                intent.putExtra("index", 0);
                                startActivity(intent);

                            }
                        });
                        cv2.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent(Forecast.this, DetailForecast.class);
                                intent.putExtra("date",forecast.getForecastday().get(1).getDate());
                                intent.putExtra("city",getIntent().getStringExtra("city"));
                                intent.putExtra("latlon", latitudeLongitude);
                                intent.putExtra("index", 1);
                                startActivity(intent);

                            }
                        });
                        cv3.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent(Forecast.this, DetailForecast.class);
                                intent.putExtra("date",forecast.getForecastday().get(2).getDate());
                                intent.putExtra("city",getIntent().getStringExtra("city"));
                                intent.putExtra("latlon", latitudeLongitude);
                                intent.putExtra("index", 2);
                                startActivity(intent);

                            }
                        });


                        tvCondition1.setText(condition1);
                        tvMaxTemp1.setText(condition1);
                        tvDate1.setText(date1);
                        tvMaxTemp1.setText(String.valueOf(maxTemp1)+"°C");
                        tvMinTemp1.setText(String.valueOf(minTemp1)+"°C");
                        tvAvgTemp1.setText(String.valueOf(avgTemp1)+"°C");
                        tvMaxWind1.setText(String.valueOf(maxWind1)+" km/h");
                        tvPrecip1.setText(String.valueOf(precip1)+" mm");
                        Glide.with(Forecast.this).load(new_img_condition1).into(imgCondition1);


                        String condition2=forecast.getForecastday().get(1).getDay().getCondition().getText();
                        String date2=forecast.getForecastday().get(1).getDate();
                        float maxTemp2=forecast.getForecastday().get(1).getDay().getMaxtemp_c();
                        float minTemp2=forecast.getForecastday().get(1).getDay().getMintemp_c();
                        float avgTemp2=forecast.getForecastday().get(1).getDay().getAvgtemp_c();
                        float maxWind2=forecast.getForecastday().get(1).getDay().getMaxwind_kph();
                        float precip2=forecast.getForecastday().get(1).getDay().getTotalprecip_mm();
                        String img_condition2=forecast.getForecastday().get(0).getDay().getCondition().getIcon();
                        String new_img_condition2="https:"+img_condition2.replace("64x64","128x128");

                        tvCondition2.setText(condition2);
                        tvMaxTemp2.setText(condition2);
                        tvDate2.setText(date2);
                        tvMaxTemp2.setText(String.valueOf(maxTemp2)+"°C");
                        tvMinTemp2.setText(String.valueOf(minTemp2)+"°C");
                        tvAvgTemp2.setText(String.valueOf(avgTemp2)+"°C");
                        tvMaxWind2.setText(String.valueOf(maxWind2)+" km/h");
                        tvPrecip2.setText(String.valueOf(precip2)+" mm");
                        Glide.with(Forecast.this).load(new_img_condition2).into(imgCondition2);

                        String condition3=forecast.getForecastday().get(2).getDay().getCondition().getText();
                        String date3=forecast.getForecastday().get(2).getDate();
                        float maxTemp3=forecast.getForecastday().get(2).getDay().getMaxtemp_c();
                        float minTemp3=forecast.getForecastday().get(2).getDay().getMintemp_c();
                        float avgTemp3=forecast.getForecastday().get(2).getDay().getAvgtemp_c();
                        float maxWind3=forecast.getForecastday().get(2).getDay().getMaxwind_kph();
                        float precip3=forecast.getForecastday().get(2).getDay().getTotalprecip_mm();
                        String img_condition3=forecast.getForecastday().get(0).getDay().getCondition().getIcon();
                        String new_img_condition3="https:"+img_condition3.replace("64x64","128x128");

                        tvCondition3.setText(condition3);
                        tvMaxTemp3.setText(condition3);
                        tvDate3.setText(date3);
                        tvMaxTemp3.setText(String.valueOf(maxTemp3)+"°C");
                        tvMinTemp3.setText(String.valueOf(minTemp3)+"°C");
                        tvAvgTemp3.setText(String.valueOf(avgTemp3)+"°C");
                        tvMaxWind3.setText(String.valueOf(maxWind3)+" km/h");
                        tvPrecip3.setText(String.valueOf(precip3)+" mm");
                        Glide.with(Forecast.this).load(new_img_condition3).into(imgCondition3);
                    }

                    @Override
                    public void onFailure(Call<Model> call, Throwable t) {
                        Log.d("MainActivity", t.toString());
                    }
                });
    }
}