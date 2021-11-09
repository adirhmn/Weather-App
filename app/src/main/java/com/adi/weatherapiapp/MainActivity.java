package com.adi.weatherapiapp;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.adi.weatherapiapp.retrofit.ApiService;
import com.bumptech.glide.Glide;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.io.IOException;
import java.text.DateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    public static final String API_KEY = "1d23362da6a849279f593746213110";
    public static final String DAYS = "3";
    public static final String AQI = "yes";
    public static final String ALERTS = "no";
    private FusedLocationProviderClient fusedLocationProviderClient;
    private TextView tvNameCity, tvTemperature, tvCondition, tvDay, tvDate, tvWind, tvPresssure,
    tvPrecip, tvHumidity, tvCloud, tvGust, tvLastDate, tvLastTime;
    private CardView btnGetAqi;
    private ImageView imgCondition, btnMenu;
    private SwipeRefreshLayout refresh;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        tvNameCity=findViewById(R.id.main_tv_place);
        tvTemperature=findViewById(R.id.main_tv_temp);
        tvCondition=findViewById(R.id.main_tv_condition);
        tvDay=findViewById(R.id.main_tv_day);
        tvDate=findViewById(R.id.main_tv_date);
        tvWind=findViewById(R.id.main_tv_wind);
        tvPresssure=findViewById(R.id.main_tv_pressure);
        tvPrecip=findViewById(R.id.main_tv_precip);
        tvHumidity=findViewById(R.id.main_tv_humidity);
        tvCloud=findViewById(R.id.main_tv_cloud);
        tvGust=findViewById(R.id.main_tv_gust);
        tvLastDate=findViewById(R.id.main_tv_lu_date);
        tvLastTime=findViewById(R.id.main_tv_lu_time);
        imgCondition=findViewById(R.id.main_tv_imgcondition);
        btnGetAqi=findViewById(R.id.main_btn_getaqi);
        btnMenu=findViewById(R.id.main_btn_menu);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            tvDay.setText(getDay());
            tvDate.setText(getDate());
        }


        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        getLocation();

        refresh=findViewById(R.id.main_refresh);
        refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    tvDay.setText(getDay());
                    tvDate.setText(getDate());
                }


                fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(MainActivity.this);
                getLocation();
                refresh.setRefreshing(false);
            }
        });


    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    private String getDate() {
        LocalDate currentDate = LocalDate.now();
        String date = String.valueOf(currentDate.getMonth())+", "+ String.valueOf(currentDate.getDayOfMonth())+" "+String.valueOf(currentDate.getYear());
        return date;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private String getDay() {
        LocalDate currentDate = LocalDate.now();
        String day = String.valueOf(currentDate.getDayOfWeek());
        return day;
    }

    private void getApiToView(String latitudeLongitude, String nameCity) {
        ApiService.endPoint().getDataWeather(API_KEY, latitudeLongitude, DAYS, AQI, ALERTS)
                .enqueue(new Callback<Model>() {
                    @Override
                    public void onResponse(Call<Model> call, Response<Model> response) {
                        Model.Current current = response.body().getCurrent();
                        float wind = current.getWind_kph();
                        float pressure = current.getPressure_mb();
                        float precipitation = current.getPrecip_mm();
                        int humidity = current.getHumidity();
                        int cloud = current.getCloud();
                        float gust = current.getGust_kph();
                        String condition = current.getCondition().getText();
                        float temperature = current.getTemp_c();
                        String lastUpdated = current.getLast_updated();
                        String[] lastDateTime = lastUpdated.split(" ");
                        String img_condition=current.getCondition().getIcon();
                        String new_img_condition="https:"+img_condition.replace("64x64","128x128");

                        tvWind.setText(String.valueOf(wind)+" km/h");
                        tvPresssure.setText(String.valueOf(pressure)+" mb");
                        tvPrecip.setText(String.valueOf(precipitation)+" mm");
                        tvHumidity.setText(String.valueOf(humidity));
                        tvCloud.setText(String.valueOf(cloud));
                        tvGust.setText(String.valueOf(gust)+" km/h");
                        tvCondition.setText(condition);
                        tvTemperature.setText(String.valueOf(temperature)+"°C");
                        tvLastDate.setText(lastDateTime[0]);
                        tvLastTime.setText(lastDateTime[1]);
                        Glide.with(MainActivity.this).load(new_img_condition).into(imgCondition);


                        float co = current.getAir_quality().getCo();
                        float o3 = current.getAir_quality().getO3();
                        float so2 = current.getAir_quality().getSo2();
                        float pm2_5 = current.getAir_quality().getPm2_5();
                        float pm10 = current.getAir_quality().getPm10();
                        float usEpaIndex = current.getAir_quality().getUs_epa_index();
                        float ukDefraIndex = current.getAir_quality().getGb_defra_index();

                        btnGetAqi.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent=new Intent(MainActivity.this, AirQuality.class);
                                intent.putExtra("co", co);
                                intent.putExtra("o3", o3);
                                intent.putExtra("so2", so2);
                                intent.putExtra("pm2_5", pm2_5);
                                intent.putExtra("pm10",pm10);
                                intent.putExtra("usEpaIndex",usEpaIndex);
                                intent.putExtra("ukDefraIndex",ukDefraIndex);
                                intent.putExtra("city",nameCity);
                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                                    intent.putExtra("day", getDay());
                                    intent.putExtra("date", getDate());
                                }
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

    private void getLocation() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainActivity.this
                    , new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 44);
            checkGPS();
        }else{
            checkGPS();
            fusedLocationProviderClient.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {
                @Override
                public void onComplete(@NonNull Task<Location> task) {
                    Location location = task.getResult();
                    if (location != null) {
                        try {
                            //Initialize geocoder
                            Geocoder geocoder = new Geocoder(MainActivity.this,
                                    Locale.getDefault());
                            //Initialize address list
                            List<Address> addresses = geocoder.getFromLocation(
                                    location.getLatitude(), location.getLongitude(), 1
                            );
                            btnMenu.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    PopupMenu dropDownMenu = new PopupMenu(getApplicationContext(), btnMenu);
                                    dropDownMenu.getMenuInflater().inflate(R.menu.main_menu, dropDownMenu.getMenu());
                                    dropDownMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                                        @Override
                                        public boolean onMenuItemClick(MenuItem item) {
                                            if(item.getItemId()==R.id.menu_forecast){
                                                Intent intent= new Intent(MainActivity.this, Forecast.class);
                                                intent.putExtra("latlon", String.valueOf(addresses.get(0).getLatitude())+","+String.valueOf(addresses.get(0).getLongitude()));
                                                intent.putExtra("city", addresses.get(0).getSubAdminArea());
                                                startActivity(intent);
                                            }else if(item.getItemId()==R.id.menu_history){
                                                Intent intent= new Intent(MainActivity.this, History.class);
                                                intent.putExtra("latlon", String.valueOf(addresses.get(0).getLatitude())+","+String.valueOf(addresses.get(0).getLongitude()));
                                                intent.putExtra("city", addresses.get(0).getSubAdminArea());
                                                startActivity(intent);
                                            }
                                            return true;
                                        }
                                    });
                                    dropDownMenu.show();
                                }
                            });

                            //setCity
                            String name_city = addresses.get(0).getSubAdminArea();
                            tvNameCity.setText(name_city);
                            String latLon=String.valueOf(addresses.get(0).getLatitude())+","+String.valueOf(addresses.get(0).getLongitude());
                            getApiToView(latLon, name_city);


                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
        }



     }



    public void checkGPS() {
        final LocationManager manager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        if (!manager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            buildAlertMessageNoGps();

        }
    }

    private void buildAlertMessageNoGps() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Untuk dapat mengakses aplikasi ini, aktifkan lokasi perangakat, Apakah Anda akan mengaktifkannya?")
                .setCancelable(false)
                .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                    public void onClick(final DialogInterface dialog, final int id) {
                        finish();
                        startActivity(new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                    }
                })
                .setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                    public void onClick(final DialogInterface dialog, final int id) {
                        finish();
                    }
                });
        final AlertDialog alert = builder.create();
        alert.show();
    }

}