package com.adi.weatherapiapp.retrofit;

import com.adi.weatherapiapp.Model;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiEndPoint {

    @GET("forecast.json")
    Call<Model> getDataWeather(@Query("key") String key,
                               @Query("q") String latlon,
                               @Query("days") String days,
                               @Query("aqi") String aqi,
                               @Query("alerts") String alerts);

    @GET("history.json")
    Call<Model> getDataHistory(@Query("key") String key,
                               @Query("q") String latlon,
                               @Query("dt") String date);
}
