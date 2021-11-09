package com.adi.weatherapiapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.adi.weatherapiapp.retrofit.ApiService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.adi.weatherapiapp.MainActivity.ALERTS;
import static com.adi.weatherapiapp.MainActivity.API_KEY;
import static com.adi.weatherapiapp.MainActivity.AQI;
import static com.adi.weatherapiapp.MainActivity.DAYS;

public class DetailHistory extends AppCompatActivity {

    private RecyclerView rvReportHourly;
    private ImageView btnBack;
    private TextView tvCity, tvDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_history);

        btnBack=findViewById(R.id.dt_btn_back);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        tvCity=findViewById(R.id.dt_tv_place);
        tvCity.setText(getIntent().getStringExtra("city"));
        tvDate=findViewById(R.id.dt_tv_date);
        tvDate.setText(getIntent().getStringExtra("date"));

        String latlon=getIntent().getStringExtra("latlon");

        rvReportHourly=findViewById(R.id.rv_list);
        rvReportHourly.setHasFixedSize(true);
        int index=getIntent().getIntExtra("index",0);

        String date_history=getIntent().getStringExtra("date_history");

        ApiService.endPoint().getDataHistory(API_KEY, latlon,date_history )
                .enqueue(new Callback<Model>() {
                    @Override
                    public void onResponse(Call<Model> call, Response<Model> response) {
                        Model.Forecast forecast = response.body().getForecast();
                        List<Model.Forecast.Forecastday.Hour> listHourly = forecast.getForecastday().get(index).getHour();

                        rvReportHourly.setLayoutManager(new LinearLayoutManager(DetailHistory.this, LinearLayoutManager.VERTICAL, false));
                        ForecastAdapter forecastAdapter=new ForecastAdapter(listHourly);
                        rvReportHourly.setAdapter(forecastAdapter);
                    }

                    @Override
                    public void onFailure(Call<Model> call, Throwable t) {
                        Log.d("DetailForecast", t.toString());
                    }
                });
    }
}