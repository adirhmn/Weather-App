package com.adi.weatherapiapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.adi.weatherapiapp.retrofit.ApiService;
import com.bumptech.glide.Glide;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.adi.weatherapiapp.MainActivity.ALERTS;
import static com.adi.weatherapiapp.MainActivity.API_KEY;
import static com.adi.weatherapiapp.MainActivity.AQI;
import static com.adi.weatherapiapp.MainActivity.DAYS;

public class History extends AppCompatActivity {

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
        setContentView(R.layout.activity_history);

        btnBack=findViewById(R.id.ht_btn_back);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        tvCity=findViewById(R.id.ht_tv_place);

        imgCondition1=findViewById(R.id.ht_img_condition1);
        tvCondition1=findViewById(R.id.ht_tv_condition1);
        tvDate1=findViewById(R.id.ht_tv_date1);
        tvMaxTemp1=findViewById(R.id.ht_tv_maxtemp1);
        tvMinTemp1=findViewById(R.id.ht_tv_mintemp1);
        tvAvgTemp1=findViewById(R.id.ht_tv_avgtemp1);
        tvMaxWind1=findViewById(R.id.ht_tv_maxwind1);
        tvPrecip1=findViewById(R.id.ht_tv_precip1);

        imgCondition2=findViewById(R.id.ht_img_condition2);
        tvCondition2=findViewById(R.id.ht_tv_condition2);
        tvDate2=findViewById(R.id.ht_tv_date2);
        tvMaxTemp2=findViewById(R.id.ht_tv_maxtemp2);
        tvMinTemp2=findViewById(R.id.ht_tv_mintemp2);
        tvAvgTemp2=findViewById(R.id.ht_tv_avgtemp2);
        tvMaxWind2=findViewById(R.id.ht_tv_maxwind2);
        tvPrecip2=findViewById(R.id.ht_tv_precip2);

        imgCondition3=findViewById(R.id.ht_img_condition3);
        tvCondition3=findViewById(R.id.ht_tv_condition3);
        tvDate3=findViewById(R.id.ht_tv_date3);
        tvMaxTemp3=findViewById(R.id.ht_tv_maxtemp3);
        tvMinTemp3=findViewById(R.id.ht_tv_mintemp3);
        tvAvgTemp3=findViewById(R.id.ht_tv_avgtemp3);
        tvMaxWind3=findViewById(R.id.ht_tv_maxwind3);
        tvPrecip3=findViewById(R.id.ht_tv_precip3);

        cv1=findViewById(R.id.cv_ht_1);
        cv2=findViewById(R.id.cv_ht_2);
        cv3=findViewById(R.id.cv_ht_3);

        String city=getIntent().getStringExtra("city");
        tvCity.setText(city);
        String latlon=getIntent().getStringExtra("latlon");

        ApiService.endPoint().getDataHistory(API_KEY, latlon, getDatePast(-1))
                .enqueue(new Callback<Model>() {
                    @Override
                    public void onResponse(Call<Model> call, Response<Model> response) {
                        Model.Forecast history = response.body().getForecast();
                        String condition1=history.getForecastday().get(0).getDay().getCondition().getText();
                        String date1=history.getForecastday().get(0).getDate();
                        float maxTemp1=history.getForecastday().get(0).getDay().getMaxtemp_c();
                        float minTemp1=history.getForecastday().get(0).getDay().getMintemp_c();
                        float avgTemp1=history.getForecastday().get(0).getDay().getAvgtemp_c();
                        float maxWind1=history.getForecastday().get(0).getDay().getMaxwind_kph();
                        float precip1=history.getForecastday().get(0).getDay().getTotalprecip_mm();
                        String img_condition=history.getForecastday().get(0).getDay().getCondition().getIcon();
                        String new_img_condition="https:"+img_condition.replace("64x64","128x128");

                        tvCondition1.setText(condition1);
                        tvMaxTemp1.setText(condition1);
                        tvDate1.setText(date1);
                        tvMaxTemp1.setText(String.valueOf(maxTemp1)+"°C");
                        tvMinTemp1.setText(String.valueOf(minTemp1)+"°C");
                        tvAvgTemp1.setText(String.valueOf(avgTemp1)+"°C");
                        tvMaxWind1.setText(String.valueOf(maxWind1)+" km/h");
                        tvPrecip1.setText(String.valueOf(precip1)+" mm");
                        Glide.with(History.this).load(new_img_condition).into(imgCondition1);

                        cv1.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent(History.this, DetailHistory.class);
                                intent.putExtra("date",history.getForecastday().get(0).getDate());
                                intent.putExtra("city",getIntent().getStringExtra("city"));
                                intent.putExtra("latlon", latlon);
                                intent.putExtra("index", 0);
                                intent.putExtra("date_history", getDatePast(-1));
                                startActivity(intent);

                            }
                        });

                    }

                    @Override
                    public void onFailure(Call<Model> call, Throwable t) {
                        Log.d("MainActivity", t.toString());
                    }
                });

        ApiService.endPoint().getDataHistory(API_KEY, latlon, getDatePast(-2))
                .enqueue(new Callback<Model>() {
                    @Override
                    public void onResponse(Call<Model> call, Response<Model> response) {
                        Model.Forecast history = response.body().getForecast();
                        String condition2=history.getForecastday().get(0).getDay().getCondition().getText();
                        String date2=history.getForecastday().get(0).getDate();
                        float maxTemp2=history.getForecastday().get(0).getDay().getMaxtemp_c();
                        float minTemp2=history.getForecastday().get(0).getDay().getMintemp_c();
                        float avgTemp2=history.getForecastday().get(0).getDay().getAvgtemp_c();
                        float maxWind2=history.getForecastday().get(0).getDay().getMaxwind_kph();
                        float precip2=history.getForecastday().get(0).getDay().getTotalprecip_mm();
                        String img_condition=history.getForecastday().get(0).getDay().getCondition().getIcon();
                        String new_img_condition="https:"+img_condition.replace("64x64","128x128");

                        tvCondition2.setText(condition2);
                        tvMaxTemp2.setText(condition2);
                        tvDate2.setText(date2);
                        tvMaxTemp2.setText(String.valueOf(maxTemp2)+"°C");
                        tvMinTemp2.setText(String.valueOf(minTemp2)+"°C");
                        tvAvgTemp2.setText(String.valueOf(avgTemp2)+"°C");
                        tvMaxWind2.setText(String.valueOf(maxWind2)+" km/h");
                        tvPrecip2.setText(String.valueOf(precip2)+" mm");
                        Glide.with(History.this).load(new_img_condition).into(imgCondition1);


                        cv2.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent(History.this, DetailHistory.class);
                                intent.putExtra("date",history.getForecastday().get(0).getDate());
                                intent.putExtra("city",getIntent().getStringExtra("city"));
                                intent.putExtra("latlon", latlon);
                                intent.putExtra("index", 0);
                                intent.putExtra("date_history", getDatePast(-2));
                                startActivity(intent);

                            }
                        });

                    }

                    @Override
                    public void onFailure(Call<Model> call, Throwable t) {
                        Log.d("MainActivity", t.toString());
                    }
                });

        ApiService.endPoint().getDataHistory(API_KEY, latlon, getDatePast(-3))
                .enqueue(new Callback<Model>() {
                    @Override
                    public void onResponse(Call<Model> call, Response<Model> response) {
                        Model.Forecast history = response.body().getForecast();
                        String condition3=history.getForecastday().get(0).getDay().getCondition().getText();
                        String date3=history.getForecastday().get(0).getDate();
                        float maxTemp3=history.getForecastday().get(0).getDay().getMaxtemp_c();
                        float minTemp3=history.getForecastday().get(0).getDay().getMintemp_c();
                        float avgTemp3=history.getForecastday().get(0).getDay().getAvgtemp_c();
                        float maxWind3=history.getForecastday().get(0).getDay().getMaxwind_kph();
                        float precip3=history.getForecastday().get(0).getDay().getTotalprecip_mm();


                        tvCondition3.setText(condition3);
                        tvMaxTemp3.setText(condition3);
                        tvDate3.setText(date3);
                        tvMaxTemp3.setText(String.valueOf(maxTemp3)+"°C");
                        tvMinTemp3.setText(String.valueOf(minTemp3)+"°C");
                        tvAvgTemp3.setText(String.valueOf(avgTemp3)+"°C");
                        tvMaxWind3.setText(String.valueOf(maxWind3)+" km/h");
                        tvPrecip3.setText(String.valueOf(precip3)+" mm");
                        String img_condition=history.getForecastday().get(0).getDay().getCondition().getIcon();
                        String new_img_condition="https:"+img_condition.replace("64x64","128x128");
                        Glide.with(History.this).load(new_img_condition).into(imgCondition1);
                        
                        cv3.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent(History.this, DetailHistory.class);
                                intent.putExtra("date",history.getForecastday().get(0).getDate());
                                intent.putExtra("city",getIntent().getStringExtra("city"));
                                intent.putExtra("latlon", latlon);
                                intent.putExtra("index", 0);
                                intent.putExtra("date_history", getDatePast(-3));
                                startActivity(intent);

                            }
                        });

                    }

                    @Override
                    public void onFailure(Call<Model> call, Throwable t) {
                        Log.d("MainActivity", t.toString());
                    }
                });

    }

    private String getDatePast(int amount) {
        Calendar calendar=Calendar.getInstance();
        calendar.setTime(calendar.getTime());
        calendar.add(Calendar.DATE, amount);
        Date datepast=calendar.getTime();
        SimpleDateFormat formatter=new SimpleDateFormat("yyyy-MM-dd");
        String datepast_formatted=formatter.format(datepast);
        return datepast_formatted;
    }
}